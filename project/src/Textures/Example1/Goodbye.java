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
public class Goodbye extends JFrame {
    ImageIcon image ;
    JLabel label ;
    static FPSAnimator animator = null;

    public Goodbye() {
        super("Goodbye");
        JFrame j = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(325, 325);
        image = new ImageIcon(this.getClass().getResource("Goodbye.jpg"));
        label = new JLabel(image);
        label.setSize(600,600);
        add(label);
        setLocationRelativeTo(this);
        setVisible(true);
        GLCanvas glcanvas = new GLCanvas();
        add(glcanvas, BorderLayout.CENTER);
    }
}
