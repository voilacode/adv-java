package paytm2;

import java.io.IOException;
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
@WebServlet("/Deposit")
public class Deposit extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String account=req.getParameter("accountNumber");
		String amount=req.getParameter("money");
		double money1=Double.parseDouble(amount);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
			PreparedStatement pr=con.prepareStatement("select balence from user where account=?");
			pr.setString(1, account);
			ResultSet r=pr.executeQuery();
			while(r.next()) {
				double money=r.getDouble("balence");
				if(money1>0) {
					PreparedStatement pr1=con.prepareStatement("update user set balence=? where account=?");
					pr1.setDouble(1, money1);
					pr1.setString(2, account);
					pr1.executeUpdate();
				}
				RequestDispatcher rd=req.getRequestDispatcher("Accounts.jsp");
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
