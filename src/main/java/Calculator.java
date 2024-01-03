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
	System.out.println(alphabet);
    }

    public int getTotalScore() {
	for (String name : name_list) {
	    
	    System.out.printf("-------------------------------------------");
	    System.out.printf("Nombre: %s\n", name);
	
	    int alphabetical_value = getAlphabeticalValue(name);
	    
	    System.out.printf("Alphabetical value: %s\n", alphabetical_value);
	    
	    int alphabetical_position = name_list.indexOf(name) + 1;
	    System.out.printf("Position: %s\n", alphabetical_position);
	    
	    totalScore += alphabetical_value * alphabetical_position;

	    System.out.printf("score: %s\n",  alphabetical_value * alphabetical_position);
	    System.out.printf("total score: %s\n", totalScore);

	    System.out.printf("-------------------------------------------");
	}

	return totalScore;
    }
    
    private int getAlphabeticalValue(String name) {
	char[] chars = name.toCharArray();	
	int alphabetical_value = 0;

	System.out.printf("-------------------------------------------");
	System.out.printf("Nombre: %s\n", name);
	
	for (int i = 1; i < chars.length - 1; i++) {
	    if (chars[i] == '\"')
		continue;	    
	    alphabetical_value += alphabet.get(chars[i]);
	}
	
	return alphabetical_value;
    }
}
