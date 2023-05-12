package com.digdes.school.SaverAndReaderDataBases;

import java.io.File;
import java.io.FileReader;

public class DeleterForBases {
    public static void deleteTable(String tableName){
        tableName = "./" + tableName + ".txt";
        File table = new File(tableName);
        System.out.println("1");
        if(table.exists()){
            System.out.println("2");
            table.delete();
        }
    }
}
