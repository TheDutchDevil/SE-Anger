package nl.tue.cassee.qiu.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import nl.tue.cassee.qiu.input.DependencyInstanceParser;
import nl.tue.cassee.qiu.input.ParsedSentence;

public class Rule1Test {
    
    @Test
    public void testRule1() {
        /**
         * The phone has a good screen
         */
        var rawDep = "det(phone/NN - The/DT); nsubj(has/VBZ - phone/NN); det(screen/NN - a/DT); amod(screen/NN - good/JJ); dobj(has/VBZ - screen/NN); punct(has/VBZ - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "the phone has a good screen.", parsed);

        var opinionList = new ArrayList<>(Arrays.asList("good"));

        var extracted = Rule1.process(opinionList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("screen", extracted.get(0));
    }
}