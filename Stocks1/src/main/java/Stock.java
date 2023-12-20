import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Stock")
public class Stock extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stock = request.getParameter("stock");
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://latest-stock-price.p.rapidapi.com/price?Indices=" + stock))
                .header("X-RapidAPI-Key", "ea86ec0756msh17e532df1e1c9c9p170c20jsnebad0933d91c")
                .header("X-RapidAPI-Host", "latest-stock-price.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response1;
        try {
            response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
            response.setContentType("text/html");

            String result = response1.body();
            PrintWriter out = response.getWriter();
            storeStockData(result); // Store data into the database
            displayStockDetails(result, out);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void displayStockDetails(String result, PrintWriter out) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        out.println("<html>");
        out.println("<head><title>Stock Details</title></head>");
        out.println("<body>");

        // Create a table to display stock details
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Symbol</th>");
        out.println("<th>Open</th>");
        out.println("<th>Day High</th>");
        out.println("<th>Day Low</th>");
        out.println("<th>Last Price</th>");

        // Iterate through each stock in the result and add a row to the table
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stock = jsonArray.getJSONObject(i);
            out.println("<tr>");
            out.println("<td>" + stock.getString("symbol") + "</td>");
            out.println("<td>" + stock.getDouble("open") + "</td>");
            out.println("<td>" + stock.getDouble("dayHigh") + "</td>");
            out.println("<td>" + stock.getDouble("dayLow") + "</td>");
            out.println("<td>" + stock.getDouble("lastPrice") + "</td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    private void storeStockData(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        try {
            // JDBC Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
          
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bt02","root","root@0507");

            // Insert data into the database
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject stock = jsonArray.getJSONObject(i);
                insertStockData(connection, stock);
            }

            // Close the database connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertStockData(Connection connection, JSONObject stock) throws SQLException {
        String insertQuery = "INSERT INTO stock (symbol, open, dayHigh, dayLow, lastPrice) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, stock.getString("symbol"));
            preparedStatement.setDouble(2, stock.getDouble("open"));
            preparedStatement.setDouble(3, stock.getDouble("dayHigh"));
            preparedStatement.setDouble(4, stock.getDouble("dayLow"));
            preparedStatement.setDouble(5, stock.getDouble("lastPrice"));

            preparedStatement.executeUpdate();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
