package paytm2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/createaccount")
public class createaccount extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		PrintWriter p=res.getWriter();
		String name=req.getParameter("accountHolderName");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String date=req.getParameter("dob");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
			PreparedStatement pr=con.prepareStatement("select mobile from user where mobile=?");
			pr.setString(1, mobile);
			ResultSet r=pr.executeQuery();
			if(r.next()) {
				String msg="This mobile number is already registerd with another account!\n use new One";
				req.setAttribute("msg", msg);
				RequestDispatcher rd=req.getRequestDispatcher("createaccount.jsp");
				rd.forward(req, res);
			}else {
				String demo="910000";
				int num=count();
				String account=demo+num;
				PreparedStatement pr1=con.prepareStatement("insert into user(name,mobile,email,gender,birthday,account) values(?,?,?,?,?,?)");
				pr1.setString(1, name);
				pr1.setString(2, mobile);
				pr1.setString(3, email);
				pr1.setString(4,gender);
				pr1.setString(5, date);
				pr1.setString(6, account);
				int i=pr1.executeUpdate();
				if(i>=0) {
					RequestDispatcher rd=req.getRequestDispatcher("Accounts.jsp");
					rd.forward(req, res);
				}else {
					p.println("failed");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static int count() {
		Random ra=new Random();
		int num=ra.nextInt(10000);
		int count=0;
		int temp=num;
		while(temp!=0) {
			temp/=10;
			count++;
		}
		if(count==4) {
			return num;
		}else {
			return num*10;
		}
	}
}
