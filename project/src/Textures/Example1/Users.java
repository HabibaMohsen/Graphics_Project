package Textures.Example1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Users extends JFrame implements ActionListener {

    JButton Ok = new JButton(" Ok ");

    ImageIcon image2 ;
    JLabel label2 ;
    JLabel name ;
    JLabel age ;
    TextField name_Test ;
    TextField Age_Test ;

    public Users(){
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

        image2 = new ImageIcon(this.getClass().getResource("—Pngtree—clean city driving school background_915449.jpg"));
        label2 = new JLabel(image2);
        label2.setSize(600,600);
        add(label2);
        name = new JLabel(" PLease Enter The Name :");
        age = new JLabel(" PLease Enter The Age :");
        name_Test = new TextField();
        Age_Test = new TextField();
        name.setBounds(250,150,300,50);
        age.setBounds(250,250,300,50);
        name_Test.setBounds(250,200,100,50);
        Age_Test.setBounds(250,300,100,50);
      label2.add(name);
      label2.add(age);
        label2.add(name_Test);
        label2.add(Age_Test);
        Ok.setBounds(250,350,100,50);
        label2.add(Ok);

        Ok.addActionListener(this);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Ok)) {
            new Type_Play();
        }

    }

}
