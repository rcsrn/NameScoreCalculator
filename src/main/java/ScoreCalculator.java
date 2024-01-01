
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class ScoreCalculator {

    private static final String URL = "https://elastic.snaplogic.com/api/1/rest/slsched/feed/Partners/AllDatum/Entrevista_Integracion/LeeArchivoNombresTask";
    private static final String BEARER_TOKEN = "h8JLQvfj5Yl1iQeOvBT43d17RoDBO6UQ";
    
    public static void main(String[] args) {
	String data = fetchDataFromWebService(URL);
	System.out.printf("This is the response: %s \n", data);	
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
