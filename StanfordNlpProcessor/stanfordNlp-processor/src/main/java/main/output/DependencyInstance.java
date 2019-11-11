package main.output;

/**
 * Defines an edge in a dependency graph from a target node
 * to a source node, with a certain label. 
 */
public class DependencyInstance {

    /**
     * Name of the relation as defined by StanfordCoreNLP.
     * 
     * BE AWARE, this string can contain characters such as ':'
     */
    private final String relName;

    private final Node target;
    private final Node source;

    public DependencyInstance(String relName, Node source, Node target) {
        this.relName = relName;
        this.source = source;
        this.target = target;
    }

    public String getRelName() {
        return this.relName;
    }

    public Node getSource() {
        return this.source;
    }

    public Node getTarget() {
        return this.target;
    }

    public static class Node {
        private final String word;
        private final String tag;

        public Node(String word, String tag) {
            this.word = word;
            this.tag = tag; 
        }

        public String getWord() {
            return this.word;
        }

        public String getTag() {
            return this.tag;
        }
    }
}