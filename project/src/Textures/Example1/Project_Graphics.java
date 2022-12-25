package Textures.Example1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Project_Graphics extends JFrame implements ActionListener {
ImageIcon image ;
JLabel label ;
    JButton Start = new JButton("Start");
    JButton Settings = new JButton("Settings");
    JButton End = new JButton("End");

    public static void  main(String[]args){
        new Project_Graphics();
    }

    public Project_Graphics (){
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

            image = new ImageIcon(this.getClass().getResource("—Pngtree—clean city driving school background_915449.jpg"));
            label = new JLabel(image);
            label.setSize(600,600);
            add(label);
            Start.setBounds(250,100,100,50);
            label.add(Start);
            Settings.setBounds(250,200,100,50);
            label.add(Settings);
            End.setBounds(250,300,100,50);
            label.add(End);
            Start.addActionListener(this);
            setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(Start)){
        new login();
    }
    }


    }
