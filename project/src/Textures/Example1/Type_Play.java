package Textures.Example1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Type_Play extends JFrame implements ActionListener {
    JButton Single_Player = new JButton(" Single_Player ");
    JButton Multi_Player = new JButton(" Multi_Player ");
    ImageIcon image2 ;
    JLabel label2 ;



    public Type_Play(){
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

        image2 = new ImageIcon(this.getClass().getResource("—Pngtree—clean city driving school background_915449.jpg"));
        label2 = new JLabel(image2);
        label2.setSize(600,600);
        add(label2);

        Single_Player.setBounds(200,150,200,50);
        label2.add(Single_Player);
        Multi_Player.setBounds(200,250,200,50);
        label2.add(Multi_Player);


        Single_Player.addActionListener(this);
        Multi_Player.addActionListener(this);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Single_Player)){
            new login ( );
        }
        else if (e.getSource().equals(Multi_Player)){
            new login2 ( );
        }


    }

}
