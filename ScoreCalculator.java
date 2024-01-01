import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class ScoreCalculator {

    private static final String URL = "https://elastic.snaplogic.com/api/1/rest/slsched/feed/Partners/AllDatum/Entrevista_Integracion/LeeArchivoNombresTask";
    private static final String BEARER_TOKEN = "h8JLQvfj5Yl1iQeOvBT43d17RoDBO6UQ";
    
    public static void main(String[] args) {
	try {
	    String data = fetchDataFromWebService(URL);
	    System.out.printf("This is the response: %s \n", data);
	} catch (Exception e) {
	    System.out.println("Error occurred:");
	    e.printStackTrace();
	    System.out.println("Error Message: " + e.getMessage());
	}
    }

    private static String fetchDataFromWebService(String urlString) throws Exception {
	URL url = new URL(urlString + "?archivo=first_names&extension=txt");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    // Set request method to GET
    connection.setRequestMethod("GET");

    // Set Bearer token for authentication
    connection.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);

    // Get the response code
    int responseCode = connection.getResponseCode();

    if (responseCode == HttpURLConnection.HTTP_OK) {
        // If the response code is OK, read the data from the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    } else {
        System.out.println("Failed to fetch data. Response Code: " + responseCode);

        // Print the response body for further analysis
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        StringBuilder errorResponse = new StringBuilder();
        String errorLine;

        while ((errorLine = errorReader.readLine()) != null) {
            errorResponse.append(errorLine);
        }

        errorReader.close();
        System.out.println("Error Response: " + errorResponse.toString());

        throw new RuntimeException("Failed to fetch data from the web service. Response Code: " + responseCode);
    }

        
    }

}
