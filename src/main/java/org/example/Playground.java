package org.example;

import java.awt.*;
import javax.swing.*;
import static javax.swing.WindowConstants.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Playground {
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        BoxLayout b = new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS);
        f.setLayout(b);
//        f.setLayout(null);
                
        f.add(new Fizz());
//        JPanel p =new JPanel();
//        p.setBorder(new EmptyBorder(200, 10, 10, 10));
        f.add(Box.createRigidArea(new Dimension(0, 100)));
//        f.add(p);
        f.add(new Fizz());
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class Fizz extends JPanel {

    public Fizz() {
        Bar bar = new Bar();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        final TextField tf = new TextField(bar.get());
//        tf.setSize(100, 100);
        Button b = new Button("Click Here");
//        tf.setSize(100, 100);


        b.addActionListener(new Foo(bar, tf));
        JPanel p =new JPanel();
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(b);
//        add(p);
        add(tf);
        setSize(150, 50);
    }
    
}

class Baz implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}

class Foo implements ActionListener {
    Bar bar;
    TextField tf;

    public Foo(Bar bar, TextField tf) {
        this.bar = bar;
        this.tf = tf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("hello");
        bar.inc();
        tf.setText(bar.get());
         }
}

class Bar {
    private String text = "";

    public Bar() {
    }
    
    public void inc() {
        text += ".";
    }
    
    public String get() {
        return text;
    }
}
