import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Class designed to handle the received information as a json.
 * @author Rodrigo Casarin
 */
public class JsonHandler {

    private String input;
    
    /**
     * Constructor of the class. It creates a JsonHandler using
     * the received string
     */
    public void setString(String input) {
	this.input = input;
    }

    /**
     * Creates a json format from the string. 
     */
    public JsonNode getJsonFromString() {
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode json = null;
	try {
	    json = objectMapper.readTree(input);
	} catch (JsonProcessingException jpe) {
	    System.out.println("Something wrong occurred while parsing.");
	    System.exit(1);
	}
	return json;
    }

    /**
     * Returns the needed json payload to be sent to the target service
     * including the calculated result.
     */
    public String getJsonPayload(int result) {
	return String.format("{\"ResultadoObtenido\": %d}", result);
    }
}
