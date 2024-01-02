import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;

public class ScoreCalculator {

    private static final String URL = "https://elastic.snaplogic.com/api/1/rest/slsched/feed/Partners/AllDatum/Entrevista_Integracion/LeeArchivoNombresTask";
    private static final String BEARER_TOKEN = "h8JLQvfj5Yl1iQeOvBT43d17RoDBO6UQ";
    private static  HttpURLConnection connection;
    
    public static void main(String[] args) {
	String data = fetchDataFromWebService(URL);

	Parser parser = new Parser();
	parser.setString(data);
	JsonNode json = parser.getJsonFromString();
	
	Calculator calculator = new Calculator(json);

	int totalNameScore = calculator.getTotalScore();
	
	System.out.printf("This is the result %s\n", totalNameScore);
	
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
	    
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    
	    //authentication
	    connection.setRequestProperty("Authorization", "Bearer " + BEARER_TOKEN);
	    
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

}
