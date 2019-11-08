package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Rule 2 as given by Qiu etal.
 * 
 * Given known features attempts to extract unkown opinion words.
 */
public class Rule2 {

    public  static List<String> process1(List<String> features, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        for (var dependency : sentence.getDependencies()) {
            /**
             * This condition satisifies Rule2_1
             */
            if(NlpSets.RelationNames.contains(dependency.getRelName()) &&
                features.contains(dependency.getTarget().getWord()) &&
                NlpSets.OpinionTags.contains(dependency.getSource().getTag())) {

                    extracted.add(dependency.getSource().getWord());
            }
        }

        return extracted.stream().distinct().collect(Collectors.toList());
    }

    public  static List<String> process2(List<String> features, ParsedSentence sentence) {

        var extracted = new ArrayList<String>();

        /**
         * This loop satifies Rule2_2
         */
        for(var dependencyOne : sentence.getDependencies()) {
            for(var dependencyTwo : sentence.getDependencies()) {
                if(dependencyOne == dependencyTwo) {
                    continue;
                }

                if(NlpSets.RelationNames.contains(dependencyOne.getRelName()) &&
                    NlpSets.RelationNames.contains(dependencyTwo.getRelName()) &&
                    dependencyOne.getTarget().getWord().equals(dependencyTwo.getTarget().getWord()) &&
                    features.contains(dependencyTwo.getSource().getWord()) &&
                    NlpSets.OpinionTags.contains(dependencyOne.getSource().getTag())) {
                    
                        extracted.add(dependencyOne.getSource().getWord());
                }
            }
        }

        return extracted.stream().distinct().collect(Collectors.toList());
    }
}