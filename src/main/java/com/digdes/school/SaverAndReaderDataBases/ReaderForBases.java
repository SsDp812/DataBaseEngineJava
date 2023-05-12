package com.digdes.school.SaverAndReaderDataBases;

import com.digdes.school.Main;
import com.digdes.school.Table.TableCollection;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReaderForBases {
    public static TableCollection readTable(String tableName) throws Exception {
        List<Map<String, Object>> table = new ArrayList<>();
        Map<String,String> colTypes = new HashMap<>();
        String path = "./" + tableName + ".txt";
        Path path1 = Paths.get(path);
        if(Files.exists(path1)){
            FileReader reader = new FileReader(path);
            Scanner scan = new Scanner(reader);
            String infoAboutTypes = scan.nextLine();
            String[] info = infoAboutTypes.split(" ");
            for(var inf : info){
                String[] arr = inf.split(":::");
                colTypes.put(arr[0],arr[1]);
            }
            while(scan.hasNextLine()){
                String row = scan.nextLine();
                StringBuilder builder1 = new StringBuilder(row);
                for(int k = 0; k < 4;k++){
                    builder1.deleteCharAt(builder1.length()-1);
                }
                row = builder1.toString();
                String[] values = row.split(" ### ");
                Map<String,Object> rowMap = new HashMap<>();
                for(var val : values){
                    String[] v = val.split(" = ");
                    rowMap.put(v[0].toString(),(Object)v[1]);
                }
                table.add(rowMap);
            }
            scan.close();
            reader.close();
            return new TableCollection(table,colTypes);
        }else{
            throw new Exception("table not found");
        }
    }
}
