package nl.tue.cassee;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.tue.cassee.qiu.Algorithm;
import nl.tue.cassee.qiu.input.ParsedSentence;
import nl.tue.cassee.qiu.input.SeedList;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.List;

/**
 * Hello world!
 */
public final class App {

    @Option(names = { "-f", "--file" }, paramLabel = "INPUTFILE", description = "Input for the algo")
    public File inputFile;

    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException {

        var app = new App();

        new CommandLine(app).parseArgs(args);

        if (app.inputFile.exists()) {

            var rawFile = readLineByLineJava8(app.inputFile);

            var objectMapper = new ObjectMapper();

            var sentArray = objectMapper.readValue(rawFile, ParsedSentence[].class);

            var sentences = new ArrayList<ParsedSentence>(Arrays.asList(sentArray));  

            var algo = new Algorithm();

            algo.run(SeedList.opinionSeeds, sentences);
        }
    }

    private static String readLineByLineJava8(File file) throws IOException 
    {
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
 
        return contentBuilder.toString();
    }
}
