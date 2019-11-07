package nl.tue.cassee.qiu.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NlpSetsTest {
    @Test
    public void testEqClasses1() {
        assertEquals(true, NlpSets.RelEqual("acomp", "acomp"));
    }

    @Test
    public void testEqClasses2() {
        assertEquals(false, NlpSets.RelEqual("acomp", "amod"));
    }

    @Test
    public void testEqClasses3() {
        assertEquals(true, NlpSets.RelEqual("amod", "quantmod"));
    }

    @Test
    public void testEqClasses4() {
        assertEquals(true, NlpSets.RelEqual("iobj", "csubj"));
    }

    @Test
    public void testEqClasses5() {
        assertEquals(true, NlpSets.RelEqual("dobj", "pobj"));
    }
}