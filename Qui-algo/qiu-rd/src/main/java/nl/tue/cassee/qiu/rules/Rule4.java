package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.List;

import nl.tue.cassee.qiu.input.ParsedSentence;

/**
 * Rule4_1 and Rule4_2 as described in the paper by Qiu etal.
 */
public class Rule4 {
    public static List<String> process(List<String> opinions, ParsedSentence sentence) {
        var extracted = new ArrayList<String>();

        /**
         * Rule4_1
         */
        for(var dependency : sentence.getDependencies()) {
            if(dependency.getRelName().equals(NlpSets.ConJName)) {
                    if(NlpSets.OpinionTags.contains(dependency.getSource().getTag()) &&
                        opinions.contains(dependency.getTarget().getWord())) {

                        extracted.add(dependency.getSource().getWord());
                    }
                    if(NlpSets.OpinionTags.contains(dependency.getTarget().getTag()) &&
                        opinions.contains(dependency.getSource().getWord())) {

                        extracted.add(dependency.getTarget().getWord());
                    }
                }
        }

        /**
         * Rule4_2
         */
         for(var dependencyOne : sentence.getDependencies()) {
             for(var dependencyTwo : sentence.getDependencies()) {
                 if(dependencyOne == dependencyTwo) {
                     continue;
                 }

                 if(opinions.contains(dependencyOne.getSource().getWord()) &&
                    NlpSets.OpinionTags.contains(dependencyTwo.getSource().getTag()) &&
                    dependencyOne.getTarget().getWord().equals(dependencyTwo.getTarget().getWord()) &&
                    NlpSets.RelEqual(dependencyOne.getRelName(), dependencyTwo.getRelName())) {

                        extracted.add(dependencyTwo.getSource().getWord());
                    }
             }
         }

        return extracted;
    }
}