package paytm2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/accessaccount")
public class accessaccount extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter p=res.getWriter();
		String mobile=req.getParameter("mobile");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
			PreparedStatement pr=con.prepareStatement("select mobile from user where mobile=?");
			pr.setString(1, mobile);
			ResultSet r=pr.executeQuery();
			if(r.next()) {
				RequestDispatcher rd=req.getRequestDispatcher("Accounts.jsp");
				rd.forward(req, res);
			}else {
				String msg="This mobile number is not registered with any account!";
				req.setAttribute("msg", msg);
				RequestDispatcher rd=req.getRequestDispatcher("accessaccount.jsp");
				rd.forward(req, res);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
