import java.io.IOException;
import java.io.InputStream;
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

import com.mysql.cj.protocol.Resultset;
@WebServlet("/Profile")
public class Profile extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("id");
		
		
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","8688744624");
			PreparedStatement pr=con.prepareStatement("select * from employee where id=?");
			pr.setString(1,id);
			ResultSet r=pr.executeQuery();
			if(r.next()) {
				String id1=r.getString("id");
				String name=r.getString("name");
				String date=r.getString("dateofbirth");
				String gender=r.getString("gender");
				String email=r.getString("email");
				String phone=r.getString("phone");
				String username=r.getString("username");
				String password=r.getString("password");
				InputStream profile = r.getBinaryStream("profile");
                byte[] profileBytes = new byte[profile.available()];
                profile.read(profileBytes);
				req.setAttribute("id",id1);
				req.setAttribute("name",name);
				req.setAttribute("dateofbirth",date);
				req.setAttribute("gender",gender);
				req.setAttribute("email",email);
				req.setAttribute("phone",phone);
				req.setAttribute("username",username);
				req.setAttribute("password",password);
				req.setAttribute("profile",profileBytes);
				RequestDispatcher rd=req.getRequestDispatcher("Profile.jsp");
				rd.forward(req, res);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
