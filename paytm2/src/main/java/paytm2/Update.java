package paytm2;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Update")
public class Update extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String number=req.getParameter("accountNumber");
		
    	
    	String name=req.getParameter("accountHolderName");
    	String gender=req.getParameter("age");
    	
		
		String email=req.getParameter("email");
		String phone=req.getParameter("phoneNumber");
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
			PreparedStatement pr=con.prepareStatement("update user SET name=?, gender=?, email=?, mobile=? WHERE account=?");
			pr.setString(1,name);
			pr.setString(2, gender);
			pr.setString(3, email);
			pr.setString(4, phone);
			pr.setString(5, number);
			pr.executeUpdate();
			RequestDispatcher rd=req.getRequestDispatcher("Accounts.jsp");
			rd.forward(req, res);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
