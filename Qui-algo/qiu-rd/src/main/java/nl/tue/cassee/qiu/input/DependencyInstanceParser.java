package nl.tue.cassee.qiu.input;

import java.util.ArrayList;
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
        if(depString == null) {
            throw new IllegalArgumentException("depstring is null");
        }

        var output = new ArrayList<DependencyInstance>();
        
        for (var dep : depString.split(";")) {
            var relName = dep.split("\\(")[0];

            var sourceRaw = dep.split("\\(")[1].split("\\-")[0];

            var sourceWord = sourceRaw.split("\\/")[0];
            var sourceTag = sourceRaw.split("\\/")[1];

            
            var targetRaw = dep.split("\\(")[1].split("\\-")[1];

            var targetWord = targetRaw.split("\\/")[0];
            var targetTag = targetRaw.split("\\/")[1].replace(")", "");

            output.add(new DependencyInstance(relName.trim(),  
                            new DependencyInstance.Node(targetWord.trim(), targetTag.trim()),
                            new DependencyInstance.Node(sourceWord.trim(), sourceTag.trim())));
        }

        return output;
    }

}