import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/Registration")
@MultipartConfig
public class Registration extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter p=res.getWriter();
		String id=req.getParameter("id");
		String name=req.getParameter("Name");
		String date=req.getParameter("DateOfBirth");
		String Gender=req.getParameter("Gender");
		String Email=req.getParameter("Email");
		String Phone=req.getParameter("Phone");
		String Username=req.getParameter("Username");
		String Password=req.getParameter("Password");
		String ConfirmPassword=req.getParameter("ConfirmPassword");
		Part profile=req.getPart("profile");
		InputStream i=profile.getInputStream();
		if(Password.equals(ConfirmPassword)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","8688744624");
				PreparedStatement pr=con.prepareStatement("insert into employee(id,name,dateofbirth,gender,email,phone,username,password,confirmpassword,profile) values(?,?,?,?,?,?,?,?,?,?)");
				pr.setString(1, id);
				pr.setString(2, name);
				pr.setString(3, date);
				pr.setString(4, Gender);
				pr.setString(5, Email);
				pr.setString(6, Phone);
				pr.setString(7, Username);
				pr.setString(8, Password);
				pr.setString(9, ConfirmPassword);
				pr.setBlob(10,i);
				int n=pr.executeUpdate();
				if(n>0) {
					p.println("Registration Successful!");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			p.println("Password not matched with confirmpassword!");
		}
	}
}
