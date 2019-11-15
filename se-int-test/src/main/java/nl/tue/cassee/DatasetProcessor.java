package nl.tue.cassee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nl.tue.cassee.input.Dataset;
import nl.tue.cassee.reader.DatasetExtractor;
import nl.tue.cassee.reader.DatasetFinder;

public class DatasetProcessor {
    public static void Process(File sourceDir) {
        if(sourceDir == null || !sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new IllegalArgumentException("SourceDir is invalid");
        }

        var rawDatasets = DatasetFinder.findDatasetsInFolder(sourceDir);

        List<Dataset>  datasets = new ArrayList<>();

        for(var rawDataset : rawDatasets) {
            datasets.add(DatasetExtractor.getDatasetForFile(rawDataset));
        }
    }
}