package Textures.Example1;

import Textures.CardsListener;
import com.sun.opengl.util.*;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;

public class Cards extends JFrame {

    public static void main(String[] args) {
        new Cards(new AnimGLEventListener5());
    }

    public Cards(CardsListener aListener) {
        GLCanvas glcanvas;
        Animator animator;

        CardsListener listener = aListener;
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Cards");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
