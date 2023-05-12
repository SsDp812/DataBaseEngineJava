package com.digdes.school.SaverAndReaderDataBases;

import com.digdes.school.Table.TableCollection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaverForBases {
    public static void saveTable(String tableName, TableCollection collection) throws IOException {
        List<Map<String,Object>> tableColl = collection.getTable();
        String path = "./" + tableName + ".txt";
        Path path1 = Paths.get(path);
        if(Files.exists(path1)){
            Files.delete(path1);
        }
        try{
            File table = new File(path);
            FileWriter writer = new FileWriter(path);
            StringBuilder builder = new StringBuilder("");
            Map<String,String> types = collection.getColumnsTypes();
            List<String> colNames = new ArrayList<>();
            for(var type : types.entrySet()){
                colNames.add(type.getKey());
                writer.append(type.getKey() + ":::" + type.getValue() + " ");
            }
            writer.append('\n');

            for(int i = 0; i < tableColl.size();i++){
                for(int j = 0; j < tableColl.get(i).size();j++){
                    builder.append(colNames.get(j));
                    builder.append(" = ");
                    builder.append(tableColl.get(i).get(colNames.get(j)));
                    builder.append(" ### ");
                }
                builder.append('\n');
            }

            writer.write(builder.toString());
            writer.close();

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
