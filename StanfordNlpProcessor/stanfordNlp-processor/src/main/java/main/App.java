package main;

import main.processing.PreProcessing;
import picocli.CommandLine;

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

        var worker = new PreProcessing();
        
        new CommandLine(worker).parseArgs(args);

        if(worker.checkConditions()) {
            worker.process();
        }
    }
}
