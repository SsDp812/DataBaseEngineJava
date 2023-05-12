package com.digdes.school.Table.Utils;

import java.util.*;

public class Filter {
    //функция фильтра (для проверка определения условия)
    public static List<Map<String,Object>> filter(String filterString,Map<String,String> columnsTypes,List<Map<String,Object>> table) throws Exception {
        List<Map<String,Object>> response = null;
        if(filterString.contains(" and ")){
            String[] cond = filterString.split(" and ");
            List<Map<String,Object>> cond1 = checkCond(cond[0],columnsTypes,table);
            List<Map<String,Object>> cond2 = checkCond(cond[1],columnsTypes,table);
            cond1.retainAll(cond2);
            response = cond1;
        }else if(filterString.contains(" or ")){
            String[] cond = filterString.split(" or ");
            List<Map<String,Object>> cond1 = checkCond(cond[0],columnsTypes,table);
            List<Map<String,Object>> cond2 = checkCond(cond[1],columnsTypes,table);
            Set<Map<String,Object>> set = new HashSet<>();
            if(cond1 != null){
                set.addAll(cond1);
            }
            if(cond2 != null){
                set.addAll(cond2);
            }
            response = new ArrayList<>(set);
        }
        else{
            response = checkCond(filterString,columnsTypes,table);
        }
        return response;
    }

    //функция для нахождения строк подходящим по условию
    public static List<Map<String,Object>> checkCond(String cond,Map<String,String> columnsTypes,List<Map<String,Object>> table) throws Exception {
        List<Map<String,Object>> response = new ArrayList<>();
        String arr[];
        if(cond.contains(" = ")){
            arr = cond.split(" = ");
            arr[0] = deleteQuotes(arr[0]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String")){
                arr[1] = deleteQuotes(arr[1]);
            }
            for(var el : table){
                if(Objects.equals(el.get(arr[0]),arr[1])){
                    response.add(el);
                }
            }

        }else if(cond.contains((" != "))){
            arr = cond.split(" != ");
            arr[0] = deleteQuotes(arr[0]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String")){
                arr[1] = deleteQuotes(arr[1]);
            }
            for(var el : table){
                if(!Objects.equals(el.get(arr[0]),arr[1])){
                    response.add(el);
                }
            }

        }else if(cond.contains((" like "))){
            arr = cond.split(" like ");
            arr[0] = deleteQuotes(arr[0]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String")){
                arr[1] = deleteQuotes(arr[1]);
            }
            String[] subStrs = arr[1].split("%");
            for(var el : table){
                boolean flag = true;
                for(var substr : subStrs){
                    if(!el.get(arr[0]).toString().contains(substr)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    response.add(el);
                }
            }

        }else if(cond.contains((" ilike "))){
            arr = cond.split(" ilike ");
            arr[0] = deleteQuotes(arr[0]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String")){
                arr[1] = deleteQuotes(arr[1]);
            }
            arr[1] = arr[1].toLowerCase();
            String[] subStrs = arr[1].split("%");
            for(var el : table){
                boolean flag = true;
                for(var substr : subStrs){
                    if(!el.get(arr[0]).toString().contains(substr)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    response.add(el);
                }
            }

        }else if(cond.contains((" >= "))){
            arr = cond.split(" >= ");
            arr[0] = deleteQuotes(arr[0]);
            double numOfCond = Double.parseDouble(arr[1]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String") || Objects.equals(columnsTypes.get(arr[0]),"Boolean")){
                throw new Exception("Error operation for string or boolean!");
            }
            for(var el : table){
                if(el.get(arr[0]) != null) {
                    double temp = Double.parseDouble(el.get(arr[0]).toString());
                    if (temp >= numOfCond) {
                        response.add(el);
                    }
                }
            }

        }else if(cond.contains((" <= "))){
            arr = cond.split(" <= ");
            arr[0] = deleteQuotes(arr[0]);
            double numOfCond = Double.parseDouble(arr[1]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String") || Objects.equals(columnsTypes.get(arr[0]),"Boolean")){
                throw new Exception("Error operation for string or boolean!");
            }
            for(var el : table){
                if(el.get(arr[0]) != null) {
                    double temp = Double.parseDouble(el.get(arr[0]).toString());
                    if (temp <= numOfCond) {
                        response.add(el);
                    }
                }
            }

        }else if(cond.contains((" > "))){
            arr = cond.split(" > ");
            arr[0] = deleteQuotes(arr[0]);
            double numOfCond = Double.parseDouble(arr[1]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String") || Objects.equals(columnsTypes.get(arr[0]),"Boolean")){
                throw new Exception("Error operation for string or boolean!");
            }
            for(var el : table){
                if(el.get(arr[0]) != null) {
                    double temp = Double.parseDouble(el.get(arr[0]).toString());
                    if (temp > numOfCond) {
                        response.add(el);
                    }
                }
            }

        }else if(cond.contains((" < "))){
            arr = cond.split(" < ");
            arr[0] = deleteQuotes(arr[0]);
            double numOfCond = Double.parseDouble(arr[1]);
            if(Objects.equals(columnsTypes.get(arr[0]),"String") || Objects.equals(columnsTypes.get(arr[0]),"Boolean")){
                throw new Exception("Error operation for string or boolean!");
            }
            for(var el : table){
                if(el.get(arr[0]) != null) {
                    double temp = Double.parseDouble(el.get(arr[0]).toString());
                    if (temp < numOfCond) {
                        response.add(el);
                    }
                }
            }
        }
        return response;
    }

    //функция для удаления лишних ковычек
    public static String deleteQuotes(String str){
        StringBuilder builder = new StringBuilder(str);
        if(builder.charAt(0) == ' '){
            builder.deleteCharAt(0);
        }
        if(builder.charAt(0) == '\''){
            builder.deleteCharAt(0);
        }
        if(builder.charAt(builder.length()-1) == '\''){
            builder.deleteCharAt(builder.length()-1);
        }
        if(builder.charAt(builder.length()-1) == ' '){
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }
}
