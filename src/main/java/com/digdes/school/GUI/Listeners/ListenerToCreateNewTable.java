package com.digdes.school.GUI.Listeners;

import com.digdes.school.GUI.WindowToCreateNewTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerToCreateNewTable implements ActionListener {
    private JFrame thisFrame;

    public ListenerToCreateNewTable(JFrame thisFrame) {
        this.thisFrame = thisFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowToCreateNewTable window = new WindowToCreateNewTable();
        thisFrame.dispose();
    }
}
