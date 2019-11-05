package nl.tue.cassee.qiu.input;

import org.junit.Test;

public class DependencyInstanceParserTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testNotImplemented() {
        DependencyInstanceParser.parseDependencyString("Some");
    }
}