package translator;

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

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TranslateServlet")
public class TranslateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String textToTranslate = request.getParameter("textToTranslate");
        String languageSelect = request.getParameter("languageSelect");

        // Build the request to the translation API
        HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "ea86ec0756msh17e532df1e1c9c9p170c20jsnebad0933d91c")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        "source_language=en&target_language=" + languageSelect + "&text=" + textToTranslate))
                .build();

        try {
            // Send the request to the translation API
            HttpResponse<String> apiResponse = HttpClient.newHttpClient().send(apiRequest,
                    HttpResponse.BodyHandlers.ofString());

            // Extract the translated text from the API response
            String translatedText = apiResponse.body();

            // Set the response content type
            response.setContentType("text/html");

            // Write the translated text to the response
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Translated Text</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<style>");
            out.println("body { background-color: #f8f9fa; }"); // Set a light background color
            out.println(".container { max-width: 500px; }"); // Set the maximum width of the container
            out.println(".center-block { margin: 0 auto; margin-top: 50px; }"); // Center the block horizontally
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Bootstrap-styled container
            out.println(
                    "<div class='container mt-5 border border-secondary p-5 bg-white rounded shadow center-block' style='transform: translateY(20%)';>");
            out.println("<h2 class=\"text-center text-primary mb-2\">Translated Text:</h2>");
            out.println("<p class=\"text-center mt-4\"> The entered string is: <b> " + textToTranslate + "</b></p>");

            // Invoke the method to decode and print translated text
            decodeAndPrintTranslatedText(translatedText, out, textToTranslate, languageSelect);

            // Insert into the database

            out.println("</div>"); // End of container

            out.println("</body>");
            out.println("</html>");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error communicating with the translation API");
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void decodeAndPrintTranslatedText(String jsonString, PrintWriter writer, String textToTranslate, String languageSelect) throws JSONException, ClassNotFoundException, SQLException {
        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(jsonString);

        // Extract the "data" object
        JSONObject dataObject = jsonObject.getJSONObject("data");

        // Extract the "translatedText" field from the "data" object
        String resultStr = dataObject.getString("translatedText");

        // Print the result
        writer.println("<p class='text-center'>Translated Text: <b>" + resultStr + "</b></p>");
        writer.println("<div class='d-flex justify-content-center mx-auto'>");
        writer.print("<a href='/Translator/index.html' class='btn btn-primary mx-2 px-5'>Go back</a>");
        writer.print("<a href='/Translator/TranslateRecordServlet' class='btn btn-success mx-2 px-5'>View all Records</a>");
        writer.println("</div>");

        // Insert into the database
        insertIntoDatabase(textToTranslate, languageSelect, resultStr);
    }

    private void insertIntoDatabase(String originalStr, String lang, String resultStr) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tempdb";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO translator (originalStr, lang, resultStr) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, originalStr);
                preparedStatement.setString(2, lang);
                preparedStatement.setString(3, resultStr);

                int i = preparedStatement.executeUpdate();

                if (i > 0) {
                    System.out.println("Record added in database");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately, log or throw if necessary
        }
    }

}
