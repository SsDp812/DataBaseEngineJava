package com.digdes.school.Table.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//класс пара (ключ и значение)
public class Pair{
    public String name;
    public Object value;
    public Pair(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
    public static boolean containsInList(List<Pair> list, String name){
        for(var el : list){
            if(Objects.equals(el.getName(),name)){
                return true;
            }
        }
        return false;
    }
    public static Object getNewValue(List<Pair> list,String name){
        for(var el : list){
            if(Objects.equals(el.getName(),name)){
                return el.getValue();
            }
        }
        return null;
    }

    //функция для разбиения на пару ключ и значение
    public static List<Pair> splitToKeyAndValue(String valuesInString, Map<String,String> columnsTypes){
        String[] reqArr = valuesInString.split(", ");
        List<Pair> tempRow = new ArrayList<>();
        for(var el : reqArr){
            String name = el.split(" = ")[0];
            Object value = el.split(" = ")[1];
            tempRow.add(new Pair(name,value));
        }
        for(var el : tempRow){
            el.name = Filter.deleteQuotes(el.name);
            if(columnsTypes.containsKey(el.name)){
                if(Objects.equals(columnsTypes.get(el.name),"String")){
                    el.value = Filter.deleteQuotes(el.value.toString());
                }

            }
        }
        return tempRow;
    }
}