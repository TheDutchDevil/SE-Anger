package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.List;

import nl.tue.cassee.qiu.input.DependencyInstance;
import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Rule1_1 and Rule 1_2 as discussed by the Qiu et al. paper. 
 */
public class Rule1 {
    public  static List<String> process(List<String> opinions, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        for (var dependency : sentence.getDependencies()) {
            
        }

        return extracted;
    }
}