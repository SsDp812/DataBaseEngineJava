package com.digdes.school.GUI;

import com.digdes.school.JavaSchoolStarter;

import java.util.*;

public class GUIForJavaSchoolStarter {
    //коллекция для работы (таблица)
    private JavaSchoolStarter collection;

    //конструктор
    public GUIForJavaSchoolStarter(tableConfig config) throws Exception {
        if(config == tableConfig.CUSTOM){
            collection = new JavaSchoolStarter();
        }else if(config == tableConfig.DEFAULT){
            collection = new JavaSchoolStarter(JavaSchoolStarter.config.EXAMPLE);
        }
    }

    //функиця для ввода запросов с клавиатуры и их обработки
    public void startCustomWork() throws Exception {
        String sqlReq = "";
        System.out.println("Print exit() for stop!");
        Scanner scanner = new Scanner(System.in);
        while(!Objects.equals(sqlReq,"exit()")) {
            System.out.println("SQL: ");
            sqlReq =scanner.nextLine();
            this.execute(sqlReq);
        }
    }

    //отправка запроса к таблице, получение результата и его вывод
    public void execute(String req) throws Exception {
        List<Map<String,Object>> response = collection.execute(req);
        Map<String,Object> columnNames = null;
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println(req);
        System.out.println("================================================");
        List<String> names = new ArrayList<>();
        if(response != null && response.size() != 0){
            columnNames = response.get(0);
            for(var name : columnNames.entrySet()){
                names.add(name.getKey());
            }
        }else{
            System.out.println("NULL");
            System.out.println();
            System.out.println();
            System.out.println();
            return;
        }
        for (var n : names){
            System.out.print(n.toUpperCase(Locale.ROOT) + " | ");
        }
        System.out.println();
        System.out.println("================================================");
        for(var el : response){
           for(var n : names){
               System.out.print(el.get(n) + "   ");
           }
            System.out.println();
            System.out.println("----------------------------------------");
        }
        System.out.println("[][][][][][][][][][][][][][][][][][][][][][][][]");
        System.out.println();
        System.out.println();
        System.out.println();
    }
    //режимы работы (дефолтный - для демонстрации примера из задания и кастомный для создания любой таблицы)
    public enum tableConfig{
        DEFAULT,CUSTOM
    }
}
