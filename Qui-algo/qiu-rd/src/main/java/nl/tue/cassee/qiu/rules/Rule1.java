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

    public  static List<String> process(List<String> opinions, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        for (var dependency : sentence.getDependencies()) {
            /**
             * This condition satisifies Rule1_1
             */
            if(NlpSets.RelationNames.contains(dependency.getRelName()) &&
                opinions.contains(dependency.getSource().getWord()) &&
                NlpSets.FeatureTags.contains(dependency.getTarget().getTag())) {

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

                if(NlpSets.RelationNames.contains(dependencyOne.getRelName()) &&
                    NlpSets.RelationNames.contains(dependencyTwo.getRelName()) &&
                    dependencyOne.getTarget().getWord().equals(dependencyTwo.getTarget().getWord()) &&
                    opinions.contains(dependencyOne.getSource().getWord()) &&
                    NlpSets.FeatureTags.contains(dependencyTwo.getSource().getTag())) {
                    
                        extracted.add(dependencyOne.getTarget().getWord());
                }
            }
        }

        return extracted;
    }
}