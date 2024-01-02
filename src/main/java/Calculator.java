import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;


public class Calculator {
    
    private ArrayList<String> name_list;
    private int total_score;

    public Calculator(JsonNode input) {
	this.name_list = new ArrayList<String>();
    }

    public int getTotalScore() {
	return 1;
    }
    
    
    
}
