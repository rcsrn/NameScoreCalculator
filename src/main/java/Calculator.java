import java.util.ArrayList;
import java.util.Comparator;
import java.text.Collator;
import com.fasterxml.jackson.databind.JsonNode;

public class Calculator {
    
    private ArrayList<String> name_list;
    private int total_score;

    public Calculator(JsonNode input) {
	this.name_list = new ArrayList<String>();

	// It is necessary to traverse twice because input is a list of jsons.
	for (JsonNode names: input) {
	    for (JsonNode name : names) {
		name_list.add(name.toString());
	    }
	}

	System.out.println("UNSORTED LIST: ------------------------");
	System.out.println(name_list.toString());
	System.out.println("------------- ------------------------");    
	
	name_list.sort(new Comparator<String>() {
		@Override public int compare(String s1, String s2) {
		    Collator collator = Collator.getInstance();
		    return collator.compare(s1, s2);
		}
	    });

	System.out.println("SORTED LIST: ------------------------");
	System.out.println(name_list.toString());
	System.out.println("------------- ------------------------");    
    }

    public int getTotalScore() {
	return 1;
    }
    
    
    
}
