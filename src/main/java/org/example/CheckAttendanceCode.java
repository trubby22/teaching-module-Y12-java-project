package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class CheckAttendanceCode extends JFrame {

    private String[] names = {"Alice", "Bob", "Charlie"};
    private Map<String, Boolean> presence = new HashMap<>();

    private JLabel title = new JLabel("Check attendance");
    private JLabel student = new JLabel("Student");
    private JComboBox studentBox = new JComboBox();
    private JCheckBox presentBox = new JCheckBox();
    private JButton submit = new JButton("Submit");

    public static void main(String[] args) {
        new CheckAttendanceCode().setVisible(true);
    }

    public CheckAttendanceCode() {
        for (String name : this.names) {
            presence.put(name, false);
        }

        BoxLayout b = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(b);

        studentBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(names));
        studentBox.addActionListener((ActionEvent e) -> {
            int index = studentBox.getSelectedIndex();
            String name = names[index];
            boolean selected = presence.get(name);
            presentBox.setSelected(selected);
        });
        presentBox.addActionListener(new PresentBoxActionListener(
                names, presence, studentBox, presentBox
        ));
        submit.addActionListener((ActionEvent e) -> {
            printAttendance();
        });
        Component[] components = {
            Box.createRigidArea(new Dimension(10, 0)),
            student, 
            Box.createRigidArea(new Dimension(10, 0)),
            studentBox, 
            Box.createRigidArea(new Dimension(10, 0)),
            presentBox,
            Box.createRigidArea(new Dimension(10, 0))};
        Row row = new Row(components);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(title);
        add(row);
        add(submit);
        add(Box.createRigidArea(new Dimension(0, 10)));

        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void printAttendance() {
        String message = "";
        for (String name : names) {  // enhanced for-loop
            boolean present = presence.get(name);
            String status = present ? "present" : "absent";
            message += name + ": " + status + "\n";
        }
        System.out.print(message);
    }

}

class PresentBoxActionListener implements ActionListener {

    private String[] names;
    private Map<String, Boolean> presence;
    private JComboBox studentBox;
    private JCheckBox presentBox;

    public PresentBoxActionListener(
            String[] names,
            Map<String, Boolean> presence,
            JComboBox studentBox,
            JCheckBox presentBox) {
        this.names = names;
        this.presence = presence;
        this.studentBox = studentBox;
        this.presentBox = presentBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = studentBox.getSelectedIndex();
        String name = names[index];
        presence.put(name, presentBox.isSelected());
    }
}

class Row extends JPanel {

    public Row(Component[] components) {
        BoxLayout b = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(b);

        for (Component c : components) {
            add(c);
        }
    }
}
