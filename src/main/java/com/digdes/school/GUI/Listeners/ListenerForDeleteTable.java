package com.digdes.school.GUI.Listeners;

import com.digdes.school.GUI.MainWindow;
import com.digdes.school.SaverAndReaderDataBases.DeleterForBases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForDeleteTable implements ActionListener {
    private JFrame thisFrame;
    private String tableName;

    public ListenerForDeleteTable(JFrame thisFrame,String tableName) {
        this.thisFrame = thisFrame;
        this.tableName = tableName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DeleterForBases.deleteTable(tableName);
        MainWindow window = new MainWindow();
        thisFrame.dispose();
    }
}
