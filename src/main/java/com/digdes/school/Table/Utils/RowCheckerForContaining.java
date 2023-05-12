package com.digdes.school.Table.Utils;

import java.util.HashMap;
import java.util.Map;

// класс для проерки на заполненность строки (чтобы в незаполненные строки добавлять null)
public class RowCheckerForContaining{
    private Map<String,Boolean> rowContaining;
    public RowCheckerForContaining(Map<String,String> types){
        rowContaining = new HashMap<>();
        for (var el : types.entrySet()){
            rowContaining.put(el.getKey(),false);
        }
    }
    public void insertValue(String columnName){
        rowContaining.put(columnName,true);
    }
    public void insertDefaultValues(Map<String,Object> row){
        for(var el: rowContaining.entrySet()){
            if(!el.getValue()){
                row.put(el.getKey(),null);
            }
        }
    }
}