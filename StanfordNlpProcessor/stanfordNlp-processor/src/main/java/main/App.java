package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "I am submitting a simple patch to solve this annoying issue";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        System.out.println(document);

        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        for(CoreMap sentence: sentences) {

            // this is the Stanford dependency graph of the current sentence
            SemanticGraph dependencies = sentence.get(EnhancedPlusPlusDependenciesAnnotation.class);

            var rawEdges = new ArrayList<String>();

            for(var dep : dependencies.edgeListSorted()) {
                rawEdges.add(String.format("%s(%s/%s - %s/%s)", dep.getRelation(), 
                                dep.getSource().originalText(), 
                                dep.getSource().get(PartOfSpeechAnnotation.class),
                                dep.getTarget().originalText(),
                                dep.getTarget().get(PartOfSpeechAnnotation.class)));
            }

            System.out.println(rawEdges.stream().collect(Collectors.joining("; ")));
        }
    }
}
