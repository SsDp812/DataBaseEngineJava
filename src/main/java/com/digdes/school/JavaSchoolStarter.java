package com.digdes.school;
import com.digdes.school.Interfaces.DataBaseInterface;
import com.digdes.school.Utils.Filter;
import com.digdes.school.Utils.Pair;
import com.digdes.school.Utils.RequestNormalizer;
import com.digdes.school.Utils.RowCheckerForContaining;

import java.util.*;

import static com.digdes.school.Utils.Filter.deleteQuotes;
import static com.digdes.school.Utils.Filter.filter;

public class JavaSchoolStarter implements DataBaseInterface {
    //сама таблица в виде списка хеш-таблиц
    private List<Map<String,Object>> table;
    private Map<String,String> columnsTypes;

    //конструктор без параметров для создания таблицы
    public JavaSchoolStarter() throws Exception {
        columnsTypes = new HashMap<>();
        table = new ArrayList<>();
        generateTable();
    }
    //режим работы для демонстрации примера
    public enum config{
        EXAMPLE
    }
    //конструктор для создания таблицы из примера
    public JavaSchoolStarter(config conf){
        table = new ArrayList<>();
        columnsTypes = new HashMap<>();
        if(conf == config.EXAMPLE){
            columnsTypes.put("id","Long");
            columnsTypes.put("lastname","String");
            columnsTypes.put("age","Long");
            columnsTypes.put("cost","Double");
            columnsTypes.put("active","Boolean");
        }
    }

    //совершение запроса
    public List<Map<String, Object>> execute(String request) throws Exception{
        List<Map<String, Object>> response = null;
        request = RequestNormalizer.normolize(request);
        request = request.toLowerCase();
        String[] params = request.split(" ");
        String reqType = params[0];
        params[0] = "";
        String reqBody = String.join(" ",params);
        StringBuilder builder = new StringBuilder(reqBody);
        if(builder.length() != 0 && builder.charAt(0) == ' '){
            builder.deleteCharAt(0);
        }
        reqBody = builder.toString();
        response = switch (reqType) {
            case "insert" -> insert(reqBody);
            case "select" -> select(reqBody);
            case "delete" -> delete(reqBody);
            case "update" -> update(reqBody);
            default -> throw new Exception("Error operation!");
        };
        return response;
    }

    //вставка
    private List<Map<String,Object>> insert(String reqBody) throws Exception{
        String[] reqArr = reqBody.split(" ");
        List<Map<String,Object>> response = new ArrayList<>();
        if(Objects.equals(reqArr[0],"values")){
            reqArr[0] = "";
            StringBuilder builder = new StringBuilder(String.join(" ",reqArr));
            if(builder.charAt(0) == ' '){
                builder.deleteCharAt(0);
            }
            reqArr = (builder.toString()).split(", ");
            List<Pair> tempRow = new ArrayList<>();
            for(var el : reqArr){
                String name = el.split(" = ")[0];
                name = deleteQuotes(name);
                Object value = el.split(" = ")[1];
                if(columnsTypes.containsKey(name) && Objects.equals(columnsTypes.get(name),"String")){
                    value = deleteQuotes(value.toString());
                }
                tempRow.add(new Pair(name,value));
            }
            Map<String,Object> row = new HashMap<>();
            RowCheckerForContaining utilForRow = new RowCheckerForContaining(columnsTypes);
            for(var el : tempRow){
                StringBuilder buffer = new StringBuilder(el.getName());
                buffer.delete(0,0);
                buffer.delete(el.getName().length()-1, el.getName().length()-1);
                el.name = buffer.toString();
                utilForRow.insertValue(el.name);


                if(columnsTypes.containsKey(el.name)){
                    if(Objects.equals(columnsTypes.get(el.name),"String")){
                        buffer = new StringBuilder(el.getValue().toString());
                        buffer.delete(0,0);
                        buffer.delete(el.getValue().toString().length()-1, el.getValue().toString().length()-1);
                        Object val = buffer.toString();
                        el.value = val;
                    }
                    row.put(el.name,el.value);
                }else{
                    throw new Exception("Error column name!");
                }
            }
            utilForRow.insertDefaultValues(row);
            if(row != null){
                table.add(row);
            }
            response.add(row);
            return response;
        }else {
            throw new Exception("Error insert (not values)");
        }
    }

    //получение
    private List<Map<String,Object>> select(String reqBody) throws Exception{
        if(reqBody.length() != 0){
            String[] reqArr = reqBody.split(" ");
            if(Objects.equals(reqArr[0],"where")){
                reqArr[0] = "";
                reqBody = String.join(" ", reqArr);
                List<Map<String,Object>> response;
                response = filter(reqBody,columnsTypes,table);
                return response;

            }else{
                throw new Exception("Error select request");
            }
        }else{
            return table;
        }
    }

    //удаление
    private List<Map<String,Object>> delete(String reqBody) throws Exception{
        if(reqBody.length() != 0){
            String[] reqArr = reqBody.split(" ");
            if(Objects.equals(reqArr[0],"where")){
                reqArr[0] = "";
                reqBody = String.join(" ", reqArr);
                List<Map<String,Object>> response;
                response = Filter.filter(reqBody,columnsTypes,table);
                if(response != null){
                    for(var row : response){
                        table.remove(row);
                    }
                    return response;
                }
                return null;
            }else{
                throw new Exception("Error delete request");
            }
        }else{
            return table;
        }
    }
    //обновление
    public List<Map<String,Object>> update(String reqBody) throws Exception{
        String[] reqArr = reqBody.split(" ");
        List<Map<String,Object>> response;
        if(Objects.equals(reqArr[0],"values")){
            reqArr[0] = "";
            reqBody = String.join(" ",reqArr);
            if(reqBody.contains(" where ")){
                reqArr = reqBody.split(" where ");
                if(reqArr.length > 1){
                    response = Filter.filter(reqArr[1],columnsTypes,table);
                }else{
                    throw new Exception("Not found filter after where");
                }
            }else{
                response = table;
            }
            List<Pair> newValues = Pair.splitToKeyAndValue(reqArr[0],columnsTypes);
            if(response != null){
                for(var row : response){
                    for(var el : row.entrySet()){
                        if(Pair.containsInList(newValues, el.getKey())){
                            row.put(el.getKey(),Pair.getNewValue(newValues,el.getKey()));
                        }
                    }
                }
            }
            return response;
        }else{
            throw new Exception("Error update request");
        }
    }


    //функция для генерации таблицы
    private void generateTable() throws Exception{
        Scanner in = new Scanner(System.in);
        columnsTypes = new HashMap<>();
        System.out.println("How many columns will be in table?: ");
        int count = Integer.parseInt(in.nextLine());
        for(int i = 0; i < count;i++){
            System.out.println("Name of column #" + (i+1)+"?: ");
            String name = in.nextLine().toLowerCase();
            System.out.println("Choose column type:");
            System.out.println("1 - Long");
            System.out.println("2 - Double");
            System.out.println("3 - Boolean");
            System.out.println("4 - String");
            System.out.println("Type: ");
            String type = in.nextLine();
            switch (Integer.parseInt(type)) {
                case 1 -> columnsTypes.put(name, "Long");
                case 2 -> columnsTypes.put(name, "Double");
                case 3 -> columnsTypes.put(name, "Boolean");
                case 4 -> columnsTypes.put(name, "String");
                default -> throw new Exception("Error type!");
            }
        }
    }
}
