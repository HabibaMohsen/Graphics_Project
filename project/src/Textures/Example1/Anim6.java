package Textures.Example1;

import Textures.AnimListener;
import com.sun.opengl.util.*;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;

public class Anim6 extends JFrame {


    Anim6(){
        new Anim(new AnimGLEventListener3());

    }
    public Anim6(AnimListener aListener) {
        GLCanvas glcanvas;
        Animator animator;

        AnimListener listener = aListener;
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(100);
        animator.add(glcanvas);
        animator.start();

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
