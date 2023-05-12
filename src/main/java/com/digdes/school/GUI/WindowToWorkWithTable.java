package com.digdes.school.GUI;

import com.digdes.school.GUI.Listeners.backButtonListenerToMainMenu;
import com.digdes.school.SaverAndReaderDataBases.ReaderForBases;
import com.digdes.school.SaverAndReaderDataBases.SaverForBases;
import com.digdes.school.Table.TableCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WindowToWorkWithTable extends JFrame {
    private JButton backButton;
    private JButton buttonToExecute;
    private JLabel labelForRequest;
    private JTextField requestField;
    private JLabel labelForResponse;
    private JTextArea responseField;
    private TableCollection collection;
    private String nameOfTable;
    public WindowToWorkWithTable(String colllectionName) throws Exception {
        super(colllectionName);
        nameOfTable = colllectionName;
        this.setBounds(800,200,320,445);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        collection = ReaderForBases.readTable(colllectionName);

        backButton = new JButton("<Back|");
        backButton.setBounds(10,10,70,30);
        backButton.addActionListener(new backButtonListenerToMainMenu(this));
        labelForRequest = new JLabel("Input SQL request:");
        labelForRequest.setBounds(100,15,120,40);


        requestField = new JTextField("");
        requestField.setBounds(10,70,300,40);


        labelForResponse = new JLabel("Response");
        labelForResponse.setBounds(125,175,70,40);

        buttonToExecute = new JButton("Execute!");
        buttonToExecute.setBounds(120,125,80,40);

        responseField = new JTextArea();
        responseField.setBounds(10,230,300,180);

        buttonToExecute.addActionListener(new ExecuteEvent());

        this.getContentPane().add(labelForRequest);
        this.getContentPane().add(requestField);
        this.getContentPane().add(labelForResponse);
        this.getContentPane().add(buttonToExecute);
        this.getContentPane().add(responseField);
        this.getContentPane().add(backButton);
        this.setLayout(null);
        this.setVisible(true);
    }

    private class ExecuteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String req = requestField.getText();
            try {
                responseField.setText(generateText(collection.execute(req),req));
                SaverForBases.saveTable(nameOfTable,collection);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
        public String generateText(List<Map<String,Object>> response,String req){
            StringBuilder res = new StringBuilder("");
            Map<String,Object> columnNames = null;
            res.append("[][][][][][][][][][][][][][][][][][][][][][][][]" + '\n');
            res.append(req).append('\n');
            res.append("================================================" + '\n');
            List<String> names = new ArrayList<>();
            if(response != null && response.size() != 0){
                columnNames = response.get(0);
                for(var name : columnNames.entrySet()){
                    names.add(name.getKey());
                }
            }else{
                res.append("NULL");
                return res.toString();
            }
            for (var n : names){
                res.append(n.toUpperCase(Locale.ROOT) + " | ");
            }
            res.append('\n');
            res.append("================================================" + '\n');
            for(var el : response){
                for(var n : names){
                    res.append(el.get(n)).append("   ");
                }
                res.append('\n');
                res.append("----------------------------------------"+ '\n');
            }
            res.append("[][][][][][][][][][][][][][][][][][][][][][][][]" + '\n');
            return res.toString();
        }
    }
}