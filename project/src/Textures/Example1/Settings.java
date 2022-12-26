package Textures.Example1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

/**
 * This is a basic JOGL . Feel free to reuse this code or modify it.
 */
public class Settings extends JFrame {
    ImageIcon image ;
    JLabel label ,label1,label2,label3,label4,label5,label6 ;
    String text1,text2,text3,text4,text5,text6 ;
    static FPSAnimator animator = null;

    public Settings() {
        super("Setting");
        JFrame j = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        image = new ImageIcon(this.getClass().getResource("pecture.jpg"));
        label = new JLabel(image);
        text1 ="  Hello ";
        text2="  it is Race Game ";
        text3="  ↓ Go back  ↑ to Moving Forward ";
        text4="  → Right   ← Left ";
        text5="  We have 3 levels in the game : easy , medium and hard . ";
        text6="  In order to win you must not crash into any car until the time is up . ";
        label1 = new JLabel(text1);
        label2 = new JLabel(text2);
        label3 = new JLabel(text3);
        label4 = new JLabel(text4);
        label5 = new JLabel(text5);
        label6 = new JLabel(text6);
        label1.setSize(450,50);
        label2.setSize(450,100);
        label3.setSize(450,150);
        label4.setSize(450,200);
        label5.setSize(450,250);
        label6.setSize(450,300);
        label.add(label1);
        label.add(label2);
        label.add(label3);
        label.add(label4);
        label.add(label5);
        label.add(label6);
        add(label);
        setLocationRelativeTo(this);
        setVisible(true);
        GLCanvas glcanvas = new GLCanvas();
        add(glcanvas, BorderLayout.CENTER);

    }
}
