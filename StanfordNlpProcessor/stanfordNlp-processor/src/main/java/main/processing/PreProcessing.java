package main.processing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation;
import main.input.RawSentence;
import main.output.DependencyInstance;
import main.output.ParsedSentence;
import picocli.CommandLine.Option;

public class PreProcessing {
    
    @Option(names={"-f", "--file"}, paramLabel = "INPUTFILE", description = "The input file containing sentence identifiers and sentences.")
    public File inputFile;

    @Option(names={"-o", "--output-file"}, paramLabel = "OUTPUTFILE", description = "The output file containing the parsed sentences")
    public File outputFile;

    public boolean checkConditions() {
        if(inputFile == null) {
            System.out.println("No value given for input file");
            return false;
        }

        if(outputFile == null) {
            System.out.println("No value given for output file");
            return false;
        }

        if(!inputFile.exists()) {
            System.out.print("input file does not exist");
            return false;
        }

        return true;
    }

    /**
     * Given an input and output file parses all sentences in the input
     * file and writes the output as a json file. 
     */
    public void process() {
        List<ParsedSentence> out = new ArrayList<>();

        var input = getRawSentences();

        System.out.println(String.format("Processing %d lines of input", input.size()));

        var pipeline = getPipeline();

        for(var sent : input) {

            Annotation document = new Annotation(sent.getSentence());
    
            // run all Annotators on this text
            pipeline.annotate(document);

            var nlpSentences = document.get(SentencesAnnotation.class);

            if(nlpSentences.size() != 1) {
                System.err.println(String.format("Stanford found more sentences than we did for %s, '%s'",
                sent.getId(), sent.getSentence()));

                System.exit(5);
            }

            SemanticGraph parsedSent = nlpSentences.get(0).get(EnhancedPlusPlusDependenciesAnnotation.class);            

            var depInstances = new ArrayList<DependencyInstance>();

            for(var dep : parsedSent.edgeListSorted()) {
                var depInstance = new DependencyInstance(dep.getRelation().toString(), 
                                        new DependencyInstance.Node(dep.getTarget().originalText(), dep.getTarget().get(PartOfSpeechAnnotation.class)), 
                                        new DependencyInstance.Node(dep.getSource().originalText(), dep.getSource().getString(PartOfSpeechAnnotation.class)));

                depInstances.add(depInstance);
            }


            out.add(new ParsedSentence(sent.getId(), sent.getSentence(), depInstances));
        }

        var rawOutValue = parsedSentencesToRawString(out);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(rawOutValue);
        
            writer.close();
        } catch(IOException ex) {
            System.err.println("Failed to write JSON string to output file");
            System.err.println(ex);
            System.exit(3);
        }
    }

    private StanfordCoreNLP getPipeline() {
        
        Properties props = new Properties();
        /**
         * List is based on: https://stanfordnlp.github.io/CoreNLP/annotators.html
         */
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        return new StanfordCoreNLP(props);
    }

    private List<RawSentence> getRawSentences() {
        CSVReader reader = null;

        boolean skippedHeader = false;

        var ret = new ArrayList<RawSentence>();

        try {
            reader = new CSVReader(new FileReader(inputFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(!skippedHeader) {
                    skippedHeader = true;
                    continue;
                }

                if(line.length < 2) {
                    throw new RuntimeException("Input file was misformed");
                }

                ret.add(new RawSentence(line[0], line[1]));
            }
        } catch (IOException e) {
            System.err.println("error while reading input csv");
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Could not close input file");
                System.exit(1);
            }
        }

        return ret;
    }

    private String parsedSentencesToRawString(List<ParsedSentence> sentences) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(sentences);
        } catch (JsonProcessingException e) {
            System.err.println("Could not serialize processed sentences to JSON");
            System.err.println(e);
            System.exit(2);

            return null;
        }
    }
}