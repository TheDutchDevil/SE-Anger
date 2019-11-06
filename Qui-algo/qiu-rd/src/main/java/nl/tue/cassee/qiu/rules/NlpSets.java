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
                                                                "possessive", "prt", "s", "obj", "obj2", "desc"));

    public static String ConJName = "CONJ";
}