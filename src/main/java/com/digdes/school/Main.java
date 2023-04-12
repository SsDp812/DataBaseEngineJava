package com.digdes.school;

import com.digdes.school.GUI.GUIForJavaSchoolStarter;

public class Main {
    public static void main(String[] args) throws Exception {

        //для демонстрации и наглядного вывода результата в консоль был реализован клосс GUIForJavaSchoolStarter
        //также возможно работа напрямую с классом JavaSchoolStarter и его методом execute как указано в тз


        //автоматическая генерация таблицы как в примере задания и демонстрация работы запросов
        GUIForJavaSchoolStarter db = new GUIForJavaSchoolStarter(GUIForJavaSchoolStarter.tableConfig.DEFAULT);
        db.execute("INSERT VALUES 'age' = 30, 'lastname' = 'kira', 'active' = true");
        db.execute("INSERT VALUES 'lastname' = 'ivanov'");
        db.execute("INSERT VALUES 'age' = 45, 'lastname' = 'glebov'");
        db.execute("SELECT");
        db.execute("SELECT WHERE 'lastname' = 'ivanov'");
        db.execute("SELECT WHERE 'age' < 31");
        db.execute("SELECT WHERE 'age' > 37");
        db.execute("DELETE WHERE 'lastname' = 'ivanov'");
        db.execute("SELECT");
        db.execute("UPDATE VALUES 'lastname' = 'antonov' WHERE 'age' = 45");
        db.execute("SELECT");
        db.execute("INSERT VALUES 'age' = 52,           'lastname' = 'ignatov', 'active' = true");
        db.execute("SELECT WHERE 'age' > 42 AND 'active' = true");
        db.execute("SELECT WHERE 'age' > 48 OR 'active' =true");
        db.execute("SELECT WHERE 'lastname' like '%natov'");
        db.execute("SELECT WHERE 'lastname' ilike        '%nAtOv'");
        //работа с пользовательской таблицей (запросы вводятся через консоль)
        System.out.println("!!!CUSTOM MODE!!!");
        GUIForJavaSchoolStarter userDb = new GUIForJavaSchoolStarter(GUIForJavaSchoolStarter.tableConfig.CUSTOM);
        userDb.startCustomWork();
    }
}