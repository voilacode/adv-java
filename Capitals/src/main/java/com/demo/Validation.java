package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
@WebServlet("/Validation")
public class Validation extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		PrintWriter p=res.getWriter();
		String name=req.getParameter("country");
		String country=name.toLowerCase();
		String url="https://countriesnow.space/api/v0.1/countries/capital"+"?name="+country;
		HttpClient htc=HttpClient.newHttpClient();
		HttpRequest htr=HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		//Database Connections
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/capitals","root","8688744624");
			PreparedStatement pr1=con.prepareStatement("select country from demo where country=?");
			pr1.setString(1,country);
			ResultSet r=pr1.executeQuery();
			if(!r.next()) {
				PreparedStatement pr2=con.prepareStatement("insert into demo(country,capital) values(?,?)");
				try {
					HttpResponse<String> hts=htc.send(htr,HttpResponse.BodyHandlers.ofString());
					ObjectMapper m=new ObjectMapper();
					JsonNode js=m.readTree(hts.body());
					JsonNode arr=js.get("data");
					if(arr.isArray()) {
						for(JsonNode datanode:arr) {
							if(datanode.has("name")&&datanode.get("name").asText().equalsIgnoreCase(country)) {
								String capitalName=datanode.get("capital").asText();
								pr2.setString(1,country);
								pr2.setString(2,capitalName);
								int i=pr2.executeUpdate();
								if(i>0) {
									req.setAttribute("capital", capitalName);
									RequestDispatcher rd=req.getRequestDispatcher("Result.jsp");
									rd.forward(req, res);
								}
								
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				PreparedStatement pr3=con.prepareStatement("select capital from demo where country=?");
				pr3.setString(1,country);
				ResultSet r1=pr3.executeQuery();
				if(r1.next()) {
					String capital=r1.getString("capital");
					req.setAttribute("capital",capital);
					RequestDispatcher rd=req.getRequestDispatcher("Result.jsp");
					rd.forward(req, res);
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
