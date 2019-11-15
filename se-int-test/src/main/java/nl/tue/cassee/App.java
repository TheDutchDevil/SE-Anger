package nl.tue.cassee;

import java.io.File;

import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * This program takes as input a source directory, for this directory it loads all text files, and
 * parses them according to the dataset format of Hu and Liu etal. 
 * 
 * After reading in the dataset, it processes them using de Stanford dependency parser, then it
 * runs the datasets through the algorithm of Qiu etal. 
 * 
 * Afterwards, it compares the output returned with the number of features defined in the dataset,
 * reporting the precision and recall. 
 */
public final class App {

    @Option(names={"--input"}, paramLabel = "SCANDIRECTORY", description = "The directory which should be scanned for dataset files")
    public File scanDirectory;

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        var app = new App();

        new CommandLine(app).parseArgs(args);

        if(app.scanDirectory == null) {
            System.err.println("No input provided");
        }
        else if(!app.scanDirectory.exists()) {
            System.err.println("Input directory does not exist");
        } else if(!app.scanDirectory.isDirectory()){
            System.err.println("Input directory is not actually a directory");
        } else {
            DatasetProcessor.Process(app.scanDirectory);
        }
    }
}
