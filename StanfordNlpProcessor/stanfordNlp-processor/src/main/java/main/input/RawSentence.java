package main.input;

public class RawSentence {
    private String id;
    private String sentence;

    public RawSentence(String id, String sentence) {
        this.id = id;
        this.sentence = sentence;
    }

    public String getId() {
        return this.id;
    }

    public String getSentence() {
        return this.sentence;
    }
}