package nl.tue.cassee.input;

import java.util.List;

public class Dataset {
    private List<String> sentences;
    private List<String> features; 

    public Dataset(List<String> sentences, List<String> features) {
        this.sentences = sentences;
        this.features = features; 
    }

    public List<String> getSentences() {
        return this.sentences;
    }

    public List<String> getFeatures() {
        return this.features;
    }
}