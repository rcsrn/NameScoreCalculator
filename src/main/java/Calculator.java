import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.text.Collator;
import com.fasterxml.jackson.databind.JsonNode;

public class Calculator {
    
    private ArrayList<String> name_list;
    private HashMap<Character, Integer> alphabet;
    private int totalScore ;

    public Calculator(JsonNode input) {
	this.name_list = new ArrayList<String>();
	this.alphabet = new HashMap<>();

	// It is necessary to traverse twice because input is a list of jsons.
	for (JsonNode names: input) {
	    for (JsonNode name : names) {
		name_list.add(name.toString());
	    }
	}
	
	name_list.sort(new Comparator<String>() {
		@Override public int compare(String s1, String s2) {
		    Collator collator = Collator.getInstance();
		    return collator.compare(s1, s2);
		}
	    });

	int value = 1;
	for (char ch = 'A'; ch <= 'Z'; ch++) {
	    alphabet.put(ch, value);
	    value++;
	}
    }

    public int getTotalScore() {
	if (totalScore != 0)
	    return totalScore;

	for (String name : name_list) {
	    int alphabetical_value = getAlphabeticalValue(name);
	    int alphabetical_position = name_list.indexOf(name) + 1;
	    totalScore += alphabetical_value * alphabetical_position;
	}

	return totalScore;
    }
    
    private int getAlphabeticalValue(String name) {
	return 1;
    }
}
