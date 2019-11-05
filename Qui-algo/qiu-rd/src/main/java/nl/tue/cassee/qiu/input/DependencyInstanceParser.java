package nl.tue.cassee.qiu.input;

import java.util.List;


/**
 * Given a list of strings in a dependency instance format
 * parses these to extract DependencyInstances.
 * 
 * Format should be '<RELNAME>(<SWORD>/<STAG>-<TWORD>/<TTAG>)'
 * where consecutive tags are seperated by semi-colons. 
 * 
 * SWORD = Source word,
 * STAG = Source tag,
 * TWORD = Target word,
 * TTAG = Target tag
 */
public class DependencyInstanceParser {

    public static List<DependencyInstance> parseDependencyString(String depString) {
        throw new UnsupportedOperationException();
    }

}