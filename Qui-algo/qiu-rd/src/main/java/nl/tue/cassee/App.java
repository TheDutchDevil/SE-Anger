package nl.tue.cassee;

import java.util.ArrayList;

import nl.tue.cassee.qiu.Algorithm;

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
        var algo = new Algorithm();

        algo.run(new ArrayList<>(), new ArrayList<>());
    }
}
