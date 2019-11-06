package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.tue.cassee.qiu.input.DependencyInstance;
import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Rule1_1 and Rule 1_2 as discussed by the Qiu et al. paper. 
 */
public class Rule1 {
    /**
     * The class that the relation between opinion and target the the dependency should
     * have according to rule1_1
     * 
     * Stanford core NLP does not know the class mod, therefore, we take all children is specified in the documentation.
     */
    private static List<String> depClasses = new ArrayList<>(Arrays.asList("amod", "appos", "advcl", "pnmod", "subj", 
                                                    "det", "predet", "preconj", "vmod", 
                                                    "mwe", "mark", "advmod", "neg", "rcmod", "quantmod", 
                                                    "nn", "npadvmod", "tmod", "num", "number", "prep", "poss",
                                                    "possessive", "prt", "s", "obj", "obj2", "desc"));

    /**
     * The POS tag that the feature word should have to be considered a feature
     * according to rule1_1.
     */
    private static String featureTag = "NN";

    public  static List<String> process(List<String> opinions, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        for (var dependency : sentence.getDependencies()) {
            if(depClasses.contains(dependency.getRelName()) &&
                opinions.contains(dependency.getSource().getWord()) &&
                dependency.getTarget().getTag().equals(featureTag)) {

                    extracted.add(dependency.getTarget().getWord());
            }
        }

        return extracted;
    }
}