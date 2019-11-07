package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.List;

import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Implementation of Rule3_1 and Rule3_2
 */
public class Rule3 {
    public static List<String> process(List<String> features, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        /**
         * Rule3_1
         */
        for(var dependency : sentence.getDependencies()) {
            if(dependency.getRelName().equals(NlpSets.ConJName) &&
                NlpSets.FeatureTags.contains(dependency.getSource().getTag()) &&
                features.contains(dependency.getTarget().getWord())) {

                    extracted.add(dependency.getSource().getWord());
                }
        }

        /**
         * Rule3_2
         */
         for(var dependencyOne : sentence.getDependencies()) {
             for(var dependencyTwo : sentence.getDependencies()) {
                 if(dependencyOne == dependencyTwo) {
                     continue;
                 }

                 if(features.contains(dependencyOne.getSource().getWord()) &&
                    NlpSets.FeatureTags.contains(dependencyTwo.getSource().getTag()) &&
                    dependencyOne.getTarget().getWord().equals(dependencyTwo.getTarget().getWord()) &&
                    NlpSets.RelEqual(dependencyOne.getRelName(), dependencyTwo.getRelName())) {

                        extracted.add(dependencyTwo.getSource().getWord());
                    }
             }
         }

        return extracted;
    }
}