package nl.tue.cassee.qiu.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import nl.tue.cassee.qiu.input.DependencyInstanceParser;
import nl.tue.cassee.qiu.input.ParsedSentence;

public class Rule4Test {
    @Test
    public void testRule4_1() {
        /**
         * The camera is amazing and easy to use
         */
        var rawDep = "det(camera/NN - The/DT); nsubj(amazing/JJ - camera/NN); nsubj(easy/JJ - camera/NN); cop(amazing/JJ - is/VBZ); cc(amazing/JJ - and/CC); conj:and(amazing/JJ - easy/JJ); punct(amazing/JJ - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "The camera is amazing and easy.", parsed);

        var featureList = new ArrayList<>(Arrays.asList("amazing"));

        var extracted = Rule4.process1(featureList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("easy", extracted.get(0));
    }

    @Test
    public void testRule4_1_reversed() {
        /**
         * The camera is amazing and easy to use
         */
        var rawDep = "det(camera/NN - The/DT); nsubj(amazing/JJ - camera/NN); nsubj(easy/JJ - camera/NN); cop(amazing/JJ - is/VBZ); cc(amazing/JJ - and/CC); conj:and(easy/JJ - amazing/JJ); punct(easy/JJ - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "The camera is easy and amazing.", parsed);

        var featureList = new ArrayList<>(Arrays.asList("amazing"));

        var extracted = Rule4.process1(featureList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("easy", extracted.get(0));
    }

    @Test
    public void testRule4_2() {
        /**
         * If you want to buy a sexy,cool, modern mp3 player you can choose iPod.
         */
        var rawDep = "mark(want/VBP - If/IN); nsubj(want/VBP - you/PRP); nsubj:xsubj(buy/VB - you/PRP); advcl:if(choose/VB - want/VBP); mark(buy/VB - to/TO); xcomp(want/VBP - buy/VB); det(player/NN - a/DT); amod(player/NN - sexy/JJ); punct(player/NN - ,/,); amod(player/NN - cool/JJ); punct(player/NN - ,/,); amod(player/NN - modern/JJ); compound(player/NN - mp3/NN); dobj(buy/VB - player/NN); nsubj(choose/VB - you/PRP); aux(choose/VB - can/MD); dobj(choose/VB - iPod/NNP); punct(choose/VB - ./.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "If you want to buy a sexy,cool, modern mp3 player you can choose iPod.", parsed);

        var featureList = new ArrayList<>(Arrays.asList("sexy"));

        var extracted = Rule4.process2(featureList, sentence);

        assertEquals(2, extracted.size());
        assertEquals("cool", extracted.get(0));
        assertEquals("modern", extracted.get(1));
    }
}