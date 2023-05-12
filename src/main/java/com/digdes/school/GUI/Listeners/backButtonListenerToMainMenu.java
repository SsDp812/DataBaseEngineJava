package com.digdes.school.GUI.Listeners;

import com.digdes.school.GUI.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class backButtonListenerToMainMenu implements ActionListener {
    private JFrame thisFrame;

    public backButtonListenerToMainMenu(JFrame thisFrame) {
        this.thisFrame = thisFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow window = new MainWindow();
        thisFrame.dispose();
    }
}
