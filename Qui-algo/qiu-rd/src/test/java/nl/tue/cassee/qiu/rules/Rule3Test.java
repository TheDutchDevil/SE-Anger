package nl.tue.cassee.qiu.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import nl.tue.cassee.qiu.input.DependencyInstanceParser;
import nl.tue.cassee.qiu.input.ParsedSentence;

public class Rule3Test {
    @Test
    public void testRule3_1() {
        /**
         * Does the player play dvd with audio and video?
         */
        var rawDep = "det(DVD/NN - the/DT); compound(DVD/NN - player/NN); compound(DVD/NN - play/NN); dobj(Does/VBZ - DVD/NN); case(audio/NN - with/IN); nmod:with(Does/VBZ - audio/NN); cc(audio/NN - and/CC); nmod:with(Does/VBZ - video/NN); conj:and(audio/NN - video/NN); punct(Does/VBZ - ?/.)";

        var parsed = DependencyInstanceParser.parseDependencyString(rawDep);

        var sentence = new ParsedSentence("1", "Does the player play dvd with audio and video?", parsed);

        var featureList = new ArrayList<>(Arrays.asList("audio"));

        var extracted = Rule3.process(featureList, sentence);

        assertEquals(1, extracted.size());
        assertEquals("video", extracted.get(0));
    }
}