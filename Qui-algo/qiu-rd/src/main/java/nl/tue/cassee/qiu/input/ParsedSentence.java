package nl.tue.cassee.qiu.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Input for the algorithm of Qiu, a parsed sentence contains both the original
 * sentence, but also the dependencies of the sentence itself, and the POS
 * tagging of the sentence.
 */
public class ParsedSentence {
    /**
     * Unique identifier of the sentence, used to later
     * refer back to sentence.
     */
    private String id;
    private String sentence;
    private List<DependencyInstance> dependencies;

    public ParsedSentence(String id, String sentence, List<DependencyInstance> deps) {
        dependencies = deps;
        this.id = id;
        this.sentence = sentence;
    }

    public String getId() {
        return this.id;
    }

    public String getSentence() {
        return this.sentence;
    }

    public List<DependencyInstance> getDependencies() {
        return this.dependencies;
    }

}