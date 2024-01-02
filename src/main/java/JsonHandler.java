import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonHandler {

    private String input;

    public void setString(String input) {
	this.input = input;
    }
    
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
}
