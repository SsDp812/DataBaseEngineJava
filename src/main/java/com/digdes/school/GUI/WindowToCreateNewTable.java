package com.digdes.school.GUI;

import com.digdes.school.GUI.Listeners.backButtonListenerToMainMenu;
import com.digdes.school.SaverAndReaderDataBases.SaverForBases;
import com.digdes.school.Table.TableCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WindowToCreateNewTable extends JFrame {
    private JLabel labelForTableName;
    private JTextField tableName;
    private JButton backButton;
    private JLabel labelForNewColumnName;
    private JTextField newColumnName;
    private JComboBox types;

    private JTextArea info;
    private JButton addColumn;

    private JButton createTable;

    private Map<String,String> collTypes;
    public WindowToCreateNewTable() throws HeadlessException {
        super("New Table");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,200,500,520);
        collTypes = new HashMap<>();
        Container container = this.getContentPane();
        backButton = new JButton("<Back|");
        backButton.setBounds(10,10,70,30);
        backButton.addActionListener(new backButtonListenerToMainMenu(this));
        labelForTableName = new JLabel("Input name for new table:");
        labelForTableName.setBounds(10,50,200,40);
        tableName = new JTextField();
        tableName.setBounds(10,100,200,40);
        labelForNewColumnName = new JLabel("Input name for new column: ");
        labelForNewColumnName.setBounds(10,150,200,40);
        newColumnName = new JTextField("");
        newColumnName.setBounds(10,200,200,40);
        String[] typesNames = new String[]{"String","Long","Double","Boolean"};
        types = new JComboBox(typesNames);
        types.setBounds(10,250,200,40);
        addColumn = new JButton("Add new column!");
        addColumn.setBounds(10,300,200,40);
        addColumn.addActionListener(new addColumnListener());
        info = new JTextArea("");
        info.setBounds(10,400,480,100);
        container.add(labelForNewColumnName);
        container.add(newColumnName);
        container.add(addColumn);
        container.add(info);
        container.add(types);
        container.add(backButton);
        container.add(tableName);
        container.add(labelForTableName);
        createTable = new JButton("Create table!");
        createTable.setBounds(10,350,200,40);
        createTable.addActionListener(new createTableListener(this));
        container.add(createTable);
        this.setVisible(true);

    }

    class addColumnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = newColumnName.getText();
            String type1 = types.getSelectedItem().toString();
            collTypes.put(name,type1);
            String text = info.getText();
            text += "\n";
            text += (name + " : " + type1);
            info.setText(text);
        }
    }

    class createTableListener implements ActionListener{
        private JFrame thisFrame;

        public createTableListener(JFrame thisFrame) {
            this.thisFrame = thisFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SaverForBases.saveTable(tableName.getText(),new TableCollection(collTypes));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            MainWindow window = new MainWindow();
            thisFrame.dispose();
        }
    }
}
