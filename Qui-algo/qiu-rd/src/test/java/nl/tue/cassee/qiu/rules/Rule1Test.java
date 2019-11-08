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
    public void testRule1_1() {
        /**
         * The phone has a good screen
         */
        var rawDep = "det(phone/NN - The/DT); nsubj(has/VBZ - phone/NN); det(screen/NN - a/DT); amod(screen/NN - good/JJ); dobj(has/VBZ - screen/NN); punct(has/VBZ - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "the phone has a good screen.", parsed);

        var opinionList = new ArrayList<>(Arrays.asList("good"));

        var extracted = Rule1.process1(opinionList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("screen", extracted.get(0));
    }

    @Test
    public void testRule1_2() {
        var rawDep = "nsubj(player/NN - iPod/NN); cop(player/NN - is/VBZ); det(player/NN - the/DT); amod(player/NN - best/JJS); compound(player/NN - mp3/NN); punct(player/NN - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "iPod is the best mp3 player.", parsed);

        var opinionList = new ArrayList<>(Arrays.asList("best"));

        var extracted = Rule1.process2(opinionList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("iPod", extracted.get(0));
    }
}