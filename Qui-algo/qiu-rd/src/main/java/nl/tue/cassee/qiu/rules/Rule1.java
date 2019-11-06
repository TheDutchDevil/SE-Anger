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
     * have according to rule1_1 and rule1_2
     * 
     * Stanford core NLP does not know the class mod, therefore, we take all children as specified in the documentation.
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
            /**
             * This condition satisifies Rule1_1
             */
            if(depClasses.contains(dependency.getRelName()) &&
                opinions.contains(dependency.getSource().getWord()) &&
                dependency.getTarget().getTag().equals(featureTag)) {

                    extracted.add(dependency.getTarget().getWord());
            }
        }

        /**
         * This loop satifies Rule1_2
         */
        for(var dependencyOne : sentence.getDependencies()) {
            for(var dependencyTwo : sentence.getDependencies()) {
                if(dependencyOne == dependencyTwo) {
                    continue;
                }

                if(depClasses.contains(dependencyOne.getRelName()) &&
                    depClasses.contains(dependencyTwo.getRelName()) &&
                    dependencyOne.getTarget().getWord().equals(dependencyTwo.getTarget().getWord()) &&
                    opinions.contains(dependencyOne.getSource().getWord()) &&
                    dependencyTwo.getSource().getTag().equals(featureTag)
                ) {
                    extracted.add(dependencyOne.getTarget().getWord());
                }
            }
        }

        return extracted;
    }
}