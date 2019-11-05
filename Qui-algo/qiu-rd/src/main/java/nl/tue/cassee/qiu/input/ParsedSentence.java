package nl.tue.cassee.qiu.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Input for the algorithm of Qiu, a parsed sentence contains both the original
 * sentence, but also the dependencies of the sentence itself, and the POS
 * tagging of the sentence.
 */
public class ParsedSentence {
    private String sentence;
    private List<Object> dependencies;
    private List<Object> posTags;

    public ParsedSentence() {
        dependencies = new ArrayList<>();
        posTags = new ArrayList<>();
    }

    public List<Object> getPosTags() {
        return posTags;
    }

    public String getSentence() {
        return this.sentence;
    }

    public List<Object> getDependencies() {
        return this.dependencies;
    }

}