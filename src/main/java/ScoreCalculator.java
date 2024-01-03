import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;

public class ScoreCalculator {

    private static final String SOURCE_URL = "https://elastic.snaplogic.com/api/1/rest/slsched/feed/Partners/AllDatum/Entrevista_Integracion/LeeArchivoNombresTask";
    private static final String TARGET_URL = "https://elastic.snaplogic.com/api/1/rest/slsched/feed/Partners/AllDatum/Entrevista_Integracion/VerificaResultadoEjercicioTecnico1ALLDATUMTask";
    private static final String SOURCE_TOKEN = "h8JLQvfj5Yl1iQeOvBT43d17RoDBO6UQ";
    private static final String TARGET_TOKEN = "giqJWNuzhOnDTYaa1Diy1jw7FQhqZSwl";
    
    public static void main(String[] args) {
	String data = fetchDataFromWebService(SOURCE_URL);

	JsonHandler handler = new JsonHandler();
	handler.setString(data);
	JsonNode json = handler.getJsonFromString();
	
	Calculator calculator = new Calculator(json);

	int totalNameScore = calculator.getTotalScore();
	
	System.out.printf("This is the result %s\n", totalNameScore);

	System.out.printf("Numero de nombres %s\n", json.size());
	
	// sendResultToWebService(totalNameScore);
    }

    private static String readFromInput(InputStream in) throws IOException {
	try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
	    StringBuilder response = new StringBuilder();
	    String line;

	    while ((line = reader.readLine()) != null) {
		response.append(line);
	    }

	    return response.toString();
	    
	} catch (IOException ioe) {
	    throw ioe;
	}
    }

    private static String fetchDataFromWebService(String urlString) {
	String response = "";
	
	try {
	    URL url = new URL(urlString + "?archivo=first_names&extension=txt");
	    
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    
	    //authentication
	    connection.setRequestProperty("Authorization", "Bearer " + SOURCE_TOKEN);
	    
	    int responseCode = connection.getResponseCode();
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) 
		response = readFromInput(connection.getInputStream());
	    else response = readFromInput(connection.getErrorStream());	    

	} catch (IOException ioe) {
	    System.out.println("It is not possible to establish a connection to server.");
	    System.exit(1);
	}

	return response;
    }

    private static void sendResultToWebService(int result) {
	try {
	    URL url = new URL(TARGET_URL);
	    // HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    // connection.setRequestMethod("POST");
	    // connection.setRequestProperty("Content-Type", "application/json");
            // connection.setRequestProperty("Authorization", "Bearer " + TARGET_TOKEN);
            // connection.setDoOutput(true);

	    // JsonHandler handler = new JsonHandler();
	    // String json = handler.getJsonPayload(result);

	    // try (OutputStream out = connection.getOutputStream()) {
	    // 	byte[] input = json.getBytes("utf-8");
	    // 	out.write(input, 0, input.length);
	    // }
	    
	    // int responseCode = connection.getResponseCode();
	    
	    // if (responseCode == HttpURLConnection.HTTP_OK) {
	    // 	System.out.println("Result successfully sent to the server.");
	    // } else {
	    // 	System.out.println("Failed to send result. Response code: " + responseCode);
	    // }
	    
	} catch (IOException ioe) {
	    System.out.println("It is not possible to establish a connection to server.");
	    System.exit(1);
	}
    }

}
