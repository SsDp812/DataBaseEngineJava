package com.digdes.school.GUI;

import com.digdes.school.GUI.Listeners.ListenerForDeleteTable;
import com.digdes.school.GUI.Listeners.ListenerToCreateNewTable;
import com.digdes.school.GUI.Listeners.buttonListenerToLoadTable;
import com.digdes.school.SaverAndReaderDataBases.DBLoader;
import com.digdes.school.SaverAndReaderDataBases.ReaderForBases;
import com.digdes.school.Table.TableCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JButton createNewTable;
    private JLabel savedTables;

    public MainWindow(){
        super("DataBase");
        makeBaseConfig();
        drawButtonsAndLoadTables();

    }
    public void makeBaseConfig(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(800,200,500,520);
        this.setVisible(true);
    }

    public void drawButtonsAndLoadTables(){
        Container container = this.getContentPane();
        createNewTable = new JButton("Create new table!");
        createNewTable.setBounds(190,20,120,40);
        createNewTable.addActionListener(new ListenerToCreateNewTable(this));
        container.add(createNewTable);
        savedTables = new JLabel("Saved tables: ");
        savedTables.setBounds(210,80,120,40);
        container.add(savedTables);
        int y = 140;
        int space = 10;
        int butH = 40;
        List<String> tables = DBLoader.loadTableNames();
        for(var el : tables){
            y+=space;
            StringBuilder builder = new StringBuilder(el);
            for(int i = 0; i < 4;i++){
                builder.deleteCharAt(builder.length() - 1);
            }
            JButton button = new JButton(builder.toString());
            JButton buttonDelete = new JButton("Delete");
            button.setBounds(105,y,140,butH);
            buttonDelete.setBounds(255,y,140,butH);
            buttonDelete.addActionListener(new ListenerForDeleteTable(this,builder.toString()));
            y+=butH;
            button.addActionListener(new buttonListenerToLoadTable(this));
            container.add(button);
            container.add(buttonDelete);
        }
    }

}
