package nl.tue.cassee.qiu;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tue.cassee.qiu.input.ParsedSentence;

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

        List<String> seedsDiscovered = new ArrayList<>();
        List<String> featuresDiscovered = new ArrayList<>();

        for(var sent : parsedSentences ) {
            Logger.atTrace().log("Processing sentence '{}'", sent.getSentence());
        }

        return new Output();
    }

    class Output {
        public List<String> features = new ArrayList<>();
        public List<String> opinionWords = new ArrayList<>();
    }
}