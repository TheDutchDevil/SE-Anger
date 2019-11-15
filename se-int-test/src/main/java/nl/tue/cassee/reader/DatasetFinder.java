package nl.tue.cassee.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatasetFinder {
    public static List<File> findDatasetsInFolder(File parentDir) {

        var ret = new ArrayList<File>();

        for(var childFile : parentDir.listFiles()) {
            if(childFile.isFile() && childFile.getAbsolutePath().endsWith(".txt")) {
                ret.add(childFile);
            }
        }

        return ret;
    };
}