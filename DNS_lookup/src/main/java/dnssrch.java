import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;


@WebServlet("/dnssrch")
public class dnssrch extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PreparedStatement ps=null;
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        try {
            String domain = request.getParameter("domain");
            String apiUrl = "https://dns.google/resolve?name=" + domain;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader bufferedReader;
            if (responseCode == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder jsonResponse = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                jsonResponse.append(line);
            }

            System.out.println("API Response JSON: " + jsonResponse.toString());

            Gson gson = new Gson();
            JsonObject responseObject = gson.fromJson(jsonResponse.toString(), JsonObject.class);
            
            //store dns record into database
            storeDNSRecord(domain,responseObject.getAsJsonArray("Answer"));

            

            // Display the DNS lookup response
            displayDNSLookupResponse(pw, domain, responseObject.getAsJsonArray("Answer"));

        } catch (Exception e) {
            pw.println("<p class='text-danger'>Error: An unexpected error occurred</p>");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the DNS lookup form
        response.sendRedirect("Newfile.jsp");
    }
    
    private void storeDNSRecord(String domain,JsonArray answers) {
    	try {
    		
    		//Load the JDBC driver
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		//Connect to our database
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/caleb","root","ijustDh53@");
    		
    		//Insert dns record values in it
    		String insertQuery="insert into dns(domain,record_type,record_data,created_at)values(?,?,?,?)";
    		
    		PreparedStatement ps=con.prepareStatement(insertQuery);
    		
    		for(JsonElement jsonElement:answers) {
    			
    			JsonObject record=jsonElement.getAsJsonObject();
    			String recordType=record.get("type").getAsString();
    			String recordData=record.get("data").getAsString();
    			
    			ps.setString(1,domain);
    			ps.setString(2,recordType);
    			ps.setString(3,recordData);
    			ps.setTimestamp(4,new Timestamp(new Date().getTime()));
    			
    			ps.executeUpdate();
    			
    			}
    		
    		//close the database conection
    		con.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    		}
    }

    private void displayDNSLookupResponse(PrintWriter pw, String domain, JsonArray answers) {
        pw.println("<html><head><title>DNS Lookup Result</title>");
        pw.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        pw.println("</head><body class='bg-light'>");
        pw.println("<div class='container mt-5'>");
        pw.println("<h2 class='mb-4'>DNS Lookup Result for: " + domain + "</h2>");

        if (answers.size() > 0) {
            pw.println("<table class='table table-bordered table-striped'>");
            pw.println("<thead class='thead-dark'><tr><th>Record Type</th><th>Record Data</th></tr></thead><tbody>");

            for (JsonElement jsonElement : answers) {
                JsonObject record = jsonElement.getAsJsonObject();
                String recordType = record.get("type").getAsString();
                String recordData = record.get("data").getAsString();

                pw.println("<tr><td>" + recordType + "</td><td>" + recordData + "</td></tr>");
            }

            pw.println("</tbody></table>");
        } else {
            pw.println("<p class='alert alert-warning'>No DNS records found for the domain.</p>");
        }

        pw.println("</div>");
        pw.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>");
        pw.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>");
        pw.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
        pw.println("</body></html>");
    }
}
