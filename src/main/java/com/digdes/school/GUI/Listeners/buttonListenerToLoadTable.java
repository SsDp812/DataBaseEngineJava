package com.digdes.school.GUI.Listeners;

import com.digdes.school.GUI.WindowToWorkWithTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonListenerToLoadTable implements ActionListener {
    private JFrame thisFrame;
    public buttonListenerToLoadTable(JFrame thisFrame) {
        this.thisFrame = thisFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            WindowToWorkWithTable toWorkWithTable = new WindowToWorkWithTable(e.getActionCommand());
            thisFrame.dispose();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}