package nl.tue.cassee.qiu.input;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DependencyInstanceParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullThrowsError() {
        DependencyInstanceParser.parseDependencyString(null);
    }

    @Test
    public void testSingleDependency() {
        var rawDep = "amod(issue/NN-annoying/JJ)";

        var parsedDeps = DependencyInstanceParser.parseDependencyString(rawDep);

        assertEquals(1, parsedDeps.size());

        assertEquals("amod", parsedDeps.get(0).getRelName());

        assertEquals("issue", parsedDeps.get(0).getSource().getWord());
        assertEquals("NN", parsedDeps.get(0).getSource().getTag());
        
        assertEquals("annoying", parsedDeps.get(0).getTarget().getWord());
        assertEquals("JJ", parsedDeps.get(0).getTarget().getTag());
    }
}