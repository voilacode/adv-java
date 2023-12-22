package com.DictionaryServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/DictionaryServlet")
public class DictionaryServlet extends HttpServlet {
	private static final String API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/DictionaryApps";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String word = request.getParameter("word");
        String definition = fetchDefinitionFromAPI(word);

        if(definition !=null && !definition.isEmpty())
        {
        	saveDefinationToDatabase(word,definition);
        }

        request.setAttribute("word", word);
        request.setAttribute("definition", definition);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Dictionary.jsp");
        dispatcher.forward(request, response);
    }

  
    private String fetchDefinitionFromAPI(String word) {
        StringBuilder definition = new StringBuilder();
        try {
            URL url = new URL(API_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the API response using Gson library
                JsonElement jsonElement = JsonParser.parseString(response.toString());

                if (jsonElement.isJsonArray() && jsonElement.getAsJsonArray().size() > 0) {
                    JsonObject jsonObject = jsonElement.getAsJsonArray().get(0).getAsJsonObject();

                    // Check if the "meanings" key exists in the JSON response
                    JsonElement meaningsElement = jsonObject.get("meanings");
                    if (meaningsElement != null && meaningsElement.isJsonArray() && meaningsElement.getAsJsonArray().size() > 0) {
                        // Loop through meanings array
                        for (JsonElement meaning : meaningsElement.getAsJsonArray()) {
                            JsonObject meaningObject = meaning.getAsJsonObject();

                            // Extract part of speech
                            String partOfSpeech = meaningObject.get("partOfSpeech").getAsString();
                            definition.append("<b>Part of Speech:</b> ").append(partOfSpeech).append("<br>");

                            // Extract definitions
                            JsonElement definitionsElement = meaningObject.get("definitions");
                            if (definitionsElement != null && definitionsElement.isJsonArray() && definitionsElement.getAsJsonArray().size() > 0) {
                                JsonElement firstDefinition = definitionsElement.getAsJsonArray().get(0);
                                String definitionText = firstDefinition.getAsJsonObject().get("definition").getAsString();
                                definition.append("<b>Definition:</b> ").append(definitionText).append("<br>");
                            }

                            // Extract synonyms
                            JsonElement synonymsElement = meaningObject.get("synonyms");
                            if (synonymsElement != null && synonymsElement.isJsonArray() && synonymsElement.getAsJsonArray().size() > 0) {
                                JsonArray synonyms = synonymsElement.getAsJsonArray();
                                definition.append("<b>Synonyms: </b>").append(synonyms.toString()).append("<br>");
                            }

                            // Extract antonyms
                            JsonElement antonymsElement = meaningObject.get("antonyms");
                            if (antonymsElement != null && antonymsElement.isJsonArray() && antonymsElement.getAsJsonArray().size() > 0) {
                                JsonArray antonyms = antonymsElement.getAsJsonArray();
                                definition.append("<b>Antonyms:</b> ").append(antonyms.toString()).append("<br>");
                            }
                        }
                    }
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definition.toString();
    }

    
   private void saveDefinationToDatabase(String word,String definition)
   {
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD))
		{
		String query="INSERT INTO Dictionary VALUES (word,definition) VALUES (?,?)";
		try(PreparedStatement preparedStatement=connection.prepareStatement(query))
		{
			preparedStatement.setString(1, word);
			
			
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
    	
    
    
}
