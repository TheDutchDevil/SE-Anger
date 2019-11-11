package nl.tue.cassee.qiu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tue.cassee.qiu.input.ParsedSentence;
import nl.tue.cassee.qiu.rules.Rule1;
import nl.tue.cassee.qiu.rules.Rule2;
import nl.tue.cassee.qiu.rules.Rule3;
import nl.tue.cassee.qiu.rules.Rule4;

/**
 * Implementation of the algorithm as proposed by Qiu et al in Opinion Word
 * Expansion and Target Extraction through Double Propagation.
 */
public class Algorithm {

    private final Logger Logger = LoggerFactory.getLogger(Algorithm.class);

    /***
     * Given the input of the algorithm start processing all the rules. 
     * @param seedList
     * @param parsedSentences
     * @return
     */
    public Output run(List<String> seedList, List<ParsedSentence> parsedSentences) {

        Logger.atInfo().log("Starting Qiu algorithm with {} seeds and {} sentences", seedList.size(), parsedSentences.size());

        List<String> opinionsDiscovered = new ArrayList<>(seedList);
        List<String> featuresDiscovered = new ArrayList<>();

        var newlyFound = 0;

        do {
            List<String> newFeatures = new ArrayList<>();                
            List<String> newOpinions = new ArrayList<>();

            newlyFound = 0;

            for(var sent : parsedSentences ) {
                Logger.atTrace().log("Processing sentence '{}'", sent.getSentence());

                newFeatures.addAll(Rule1.process1(opinionsDiscovered, sent));
                newFeatures.addAll(Rule1.process2(opinionsDiscovered, sent));
                newFeatures.addAll(Rule3.process1(opinionsDiscovered, sent));
                newFeatures.addAll(Rule3.process2(opinionsDiscovered, sent));

                newFeatures = newFeatures.stream().distinct().collect(Collectors.toList());

                newOpinions.addAll(Rule2.process1(featuresDiscovered, sent));
                newOpinions.addAll(Rule2.process2(featuresDiscovered, sent));
                newOpinions.addAll(Rule4.process1(featuresDiscovered, sent));
                newOpinions.addAll(Rule4.process2(featuresDiscovered, sent));

                newOpinions = newOpinions.stream().distinct().collect(Collectors.toList());
            }

            for(var feat : newFeatures) {
                if(!featuresDiscovered.contains(feat)) {
                    newlyFound++;
                    featuresDiscovered.add(feat);
                }
            }

            for(var opinion : newOpinions) {
                if(!opinionsDiscovered.contains(opinion)) {
                    newlyFound++;
                    opinionsDiscovered.add(opinion);
                }
            }
        } while(newlyFound > 0);

        System.out.println("Found features are: ");

        for(var feat : featuresDiscovered) {
            System.out.println(String.format("\t%s", feat));
        }

        return new Output();
    }

    static class Output {
        public List<String> features = new ArrayList<>();
        public List<String> opinionWords = new ArrayList<>();
    }
}