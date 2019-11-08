package nl.tue.cassee.qiu.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class containing the contents of sets of relations, 
 * and Pos tags that are maintained by the Qiu Algo. 
 */
public class NlpSets {    
    /**
    * The POS tag that the feature word should have to be considered a feature
    * according to rule1_1, and rule1_2.
    */
    public static List<String> FeatureTags = new ArrayList<>(Arrays.asList("NN", "NNS"));

    public static List<String> OpinionTags = new ArrayList<>(Arrays.asList("JJ", "JJR", "JJS"));

    /**
     * The class that the relation between opinion and target the the dependency should
     * have according to rule1_1 and rule1_2
     * 
     * Stanford core NLP does not know the class mod, therefore, we take all children as specified in the documentation.
     */
    public static List<String> RelationNames = new ArrayList<>(Arrays.asList("amod", "appos", "advcl", "pnmod", "subj", 
                                                                "det", "predet", "preconj", "vmod", 
                                                                "mwe", "mark", "advmod", "neg", "rcmod", "quantmod", 
                                                                "nn", "npadvmod", "tmod", "num", "number", "prep", "poss",
                                                                "possessive", "prt", "s", "obj", "obj2", "desc",
                                                                "nsubj", "csubj"));

    public static String ConJName = "conj";

    private static List<String> ObjSubNames = new ArrayList<>(Arrays.asList("dobj", "iobj", "pobj", "nsubj", "csubj"));

    private static List<String> ModNames = new ArrayList<>(Arrays.asList("amod", "appos", "advcl", "pnmod", "subj", 
                                                        "det", "predet", "preconj", "vmod", 
                                                        "mwe", "mark", "advmod", "neg", "rcmod", "quantmod", 
                                                        "nn", "npadvmod", "tmod", "num", "number", "prep", "poss",
                                                        "possessive", "prt"));


    /**
     * Unfortunately the paper by Qiu is hilariously unclear what it means
     * for two relations to be of the same kind. They state that it specifically means
     * that mod == pnmod, and that s or subj == obj.
     * 
     * However, we do not know if they determine any other classes to be equal to each other. 
     * 
     * The problem is further compounded by the fact that they use MiniPar for dependency
     * extraction, while we use StanfordCoreNLP. The old class list of MiniPar cannot be
     * found, and we already know that StanfordCoreNLP maintains a more specific classes.
     * T
     * Therefore, we have taken a best guess to reconstruct the equality fuction for
     * dependency classes. This function does the following:
     * 
     * Anything in obj or subj is considered equal to each other, furthermore, everything
     * in mod is equal to each other, and every identity dependency is equal.
     */
    public static boolean RelEqual(String left, String right) {

        if(left.equals(right)) {
            return true;
        }

        return (ModNames.contains(left) && ModNames.contains(right)) ||
                (ObjSubNames.contains(left) && ObjSubNames.contains(right));
    }
}