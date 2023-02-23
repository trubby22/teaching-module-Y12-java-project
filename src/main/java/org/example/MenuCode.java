package org.example;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuCode extends JFrame {
    
    private JMenuBar mb;
    private JMenu m, y;
    private JMenuItem modStudents, checkAtt;

    public MenuCode() {
        mb = new JMenuBar();
        
        m = new JMenu("Menu");
        modStudents = new JMenuItem("Modify students");
        checkAtt = new JMenuItem("Check attendance ");
        checkAtt.addActionListener((ActionEvent e) -> {
            CheckAttendanceCode checkAttendance = new CheckAttendanceCode();
            checkAttendance.setVisible(true);
        });
        
        m.add(modStudents);
        m.add(checkAtt);
        
        mb.add(m);
        setJMenuBar(mb);

        setSize(500, 500);
        setVisible(true);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new MenuCode();
    }
}
