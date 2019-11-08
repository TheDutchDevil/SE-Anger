package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Implementation of Rule3_1 and Rule3_2
 */
public class Rule3 {
    public static List<String> process1(List<String> features, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        /**
         * Rule3_1 (we instantiate this rule in two 
         * directions)
         */
        for(var dependency : sentence.getDependencies()) {
            if(dependency.getRelName().equals(NlpSets.ConJName)) {
                if (NlpSets.FeatureTags.contains(dependency.getSource().getTag()) &&
                    features.contains(dependency.getTarget().getWord()))  {

                        extracted.add(dependency.getSource().getWord());
                    }
                if (NlpSets.FeatureTags.contains(dependency.getTarget().getTag()) &&
                    features.contains(dependency.getSource().getWord())) {

                        extracted.add(dependency.getTarget().getWord());
                    }
                }
        }

        return extracted.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> process2(List<String> features, ParsedSentence sentence) {

        var extracted = new ArrayList<String>();

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

         return extracted.stream().distinct().collect(Collectors.toList());
    }
}