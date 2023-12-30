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
@WebServlet("/BankTransfer")
public class BankTransfer extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter p=res.getWriter();
		String s_account=req.getParameter("senderAccount");
		String r_account=req.getParameter("receiverAccount");
		String amount=req.getParameter("amount");
		double money=Double.parseDouble(amount);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/paytm","root","8688744624");
			if(accountExists(con,s_account)&&accountExists(con,r_account)) {
				PreparedStatement pr=con.prepareStatement("select * from user where account=? or account=?");
				pr.setString(1, s_account);
				pr.setString(2, r_account);
				ResultSet r=pr.executeQuery();
				int count=0;
				while(r.next()) {
					if(r.getString("account").equals(s_account)) {
						if(r.getDouble("balence")>=money) {
							double available=r.getDouble("balence");
							available-=money;
							PreparedStatement pr1=con.prepareStatement("update user set balence=? where account=?");
							pr1.setDouble(1, available);
							pr1.setString(2, s_account);
							pr1.executeUpdate();
							count++;
						}
					}else if(r.getString("account").equals(r_account)) {
						double available=r.getDouble("balence");
						available+=money;
						PreparedStatement pr1=con.prepareStatement("update user set balence=? where account=?");
						pr1.setDouble(1, available);
						pr1.setString(2, r_account);
						pr1.executeUpdate();
						count++;
					}
				}
				if(count==2) {
					RequestDispatcher rd=req.getRequestDispatcher("Accounts.jsp");
					rd.forward(req, res);
				}else {
					p.println("Transfer Failed!");
				}
			}else {
				if(accountExists(con,s_account)==false) {
					p.println("Senders account number does not exist!");
				}else if(accountExists(con,r_account)==false) {
					p.println("Receivers account number does not exist!");
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
	// Helper method to check if an account exists
    private boolean accountExists(Connection con, String accountNumber) throws SQLException {
        PreparedStatement checkStmt = con.prepareStatement("SELECT 1 FROM user WHERE account = ?");
        checkStmt.setString(1, accountNumber);
        ResultSet checkResult = checkStmt.executeQuery();
        return checkResult.next();
    }
}
