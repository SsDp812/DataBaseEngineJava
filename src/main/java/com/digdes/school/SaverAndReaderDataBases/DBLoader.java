package com.digdes.school.SaverAndReaderDataBases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBLoader {
    public static List<String> loadTableNames(){
        File folder = new File("./");
        File[] files = folder.listFiles();
        List<String> names = new ArrayList<>();
        for(var file : files){
            if(file.getName().contains(".txt")){
                names.add(file.getName());
            }
        }
        return names;
    }
}
