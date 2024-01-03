import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.text.Collator;
import com.fasterxml.jackson.databind.JsonNode;

public class Calculator {
    
    private ArrayList<String> name_list;
    private HashMap<Character, Integer> alphabet;
    private int totalScore;

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

	// Alphabet is created.
	int value = 1;
	for (char ch = 'A'; ch <= 'Z'; ch++) {
	    alphabet.put(ch, value);
	    value++;
	}
    }

    public int getTotalScore() {
	//The index of the names in the list.
	int index = 1;
	
	for (String name : name_list) {
	    
	    int alphabeticalValue = getAlphabeticalValue(name);	    
	    int alphabeticalPosition = index++;
	    totalScore += alphabeticalValue * alphabeticalPosition;
	}

	return totalScore;
    }
    
    private int getAlphabeticalValue(String name) {
	char[] chars = name.toCharArray();	
	int alphabetical_value = 0;
	
	for (int i = 1; i < chars.length - 1; i++) {
	    if (chars[i] == '\"')
		continue;	    
	    alphabetical_value += alphabet.get(chars[i]);
	}
	
	return alphabetical_value;
    }
}
