package nl.tue.cassee.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import nl.tue.cassee.input.Dataset;

public class DatasetExtractor {
    public static Dataset getDatasetForFile(File file) {

        
        List<String> lines = null;

        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println(String.format("Failed reading lines from dataset file %s", file.getAbsolutePath()));
            System.err.println(e);

            throw new RuntimeException(e);
        }

        List<String> reviewLines = new ArrayList<>();
        List<String> features = new ArrayList<>(); 

        for(var line : lines) {
            if(line.split("##").length >= 2) {
                var possibleTags = line.split("##")[0];
                var sentence = line.split("##")[1];

                reviewLines.add(sentence);

                if(possibleTags.length() > 0) {
                    for(var rawFeature : possibleTags.split(",")) {
                        features.add(rawFeature.split("\\[")[0].replace(" ", ""));
                    }
                }
            }
        }

        return new Dataset(reviewLines, features.stream().distinct().collect(Collectors.toList()));
    }
}