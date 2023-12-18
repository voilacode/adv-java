package translator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class TranslateRecordServlet
 */
public class TranslateRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Translator App Records</title>");
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
        out.println("<style>");
        out.println("body {");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 100vh;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='card text-center border-primary shadow'>");
        out.println("<div class='card-body'>");

        out.println("<h2 class='card-title'>Translator App Records</h2>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tempdb", "root", "root");
        	
        	PreparedStatement ps = con.prepareStatement("select * from translator");
        	ResultSet rs = ps.executeQuery();
        	out.println("<table class='table table-bordered'>");
        	out.println("<thead>");
        	out.println("<tr>");
        	out.println("<th>Original</th>");
        	out.println("<th>Language</th>");
        	out.println("<th>Result</th>");
        	out.println("</tr>");
        	out.println("</thead>");
        	out.println("<tbody>");
        	
        	while(rs.next()) {
        		String func = rs.getString("originalStr");
        		String angle = rs.getString("lang");
        		String result = rs.getString("resultStr");
        		
        		out.println("<tr>");
        		out.println("<td>" +func+"</td>");
        		out.println("<td>" +angle+"</td>");
        		out.println("<td>" +result+"</td>");
        		out.println("</tr>");

        	}
        	out.println("</tbody>");
        	out.println("</table>");
        	
	        out.print("<a href='/Translator/index.html' class='btn btn-primary m-2 px-5'>Go back</a>");

        	
        	out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        	con.close(); //close the sql connection        	
		} catch(Exception e) {
			out.println("Error: "+e.getMessage());
		}
	}

}
