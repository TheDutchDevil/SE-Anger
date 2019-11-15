package nl.tue.cassee;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testApp() {
        String[] matches = Pattern.compile("^(\\w)+\\[(\\+|-)\\d\\]##", Pattern.MULTILINE)//"^(\\w)+1\\[")
                          .matcher("##i am still trying to resolve it four months later . \n" +
                          "[t] piece of crap .. . \n" +
                          "look[+2], feature[+2]##it looks great - and is loaded with features .\n" +
                          "player[-2][p]##unfortunately it turns out to be the \" disposable \" type . \n" +
                          "player[-2][u]##have had problems since the first day \n" +
                          "play[-2], dvd[-2]##- not playing some dvds and then finally after less than 60 days , it just would not recognize anything i pop in it .\n " +
                          "##save money on the long run - buy something decent once instead of buying cheap every month . \n" +
                          "[t] i 've had no problems . \n")
                          .results()
                          .map(MatchResult::group)
                          .toArray(String[]::new);
        
        for(var match : matches) {
            System.out.println(match);
        }
    }
}
