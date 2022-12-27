package Textures.Example1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login2 extends JFrame implements ActionListener {
    JButton easy = new JButton(" Easy ");
    JButton medium = new JButton(" Medium ");
    JButton Hard = new JButton(" Hard ");

    ImageIcon image2 ;
    JLabel label2 ;



    public login2(){
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

        image2 = new ImageIcon(this.getClass().getResource("—Pngtree—clean city driving school background_915449.jpg"));
        label2 = new JLabel(image2);
        label2.setSize(600,600);
        add(label2);

        easy.setBounds(250,100,100,50);
        label2.add(easy);
        medium.setBounds(250,200,100,50);
        label2.add(medium);
        Hard.setBounds(250,300,100,50);
        label2.add(Hard);

        easy.addActionListener(this);
        medium.addActionListener(this);
        Hard.addActionListener(this);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(easy)){
            new Anim4 ( );
        }
        else if (e.getSource().equals(medium)){
            new Anim5 ();
        }
        else if (e.getSource().equals(Hard)){
            new Anim6 ();
        }

    }

}
