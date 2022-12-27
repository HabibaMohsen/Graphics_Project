package Textures.Example1;

import Textures.AnimListener;
import com.sun.opengl.util.*;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;

public class Anim4 extends JFrame {


    Anim4(){
        new Anim4(new multiplayer());

    }
    public Anim4(AnimListener aListener) {
        GLCanvas glcanvas;
        Animator animator;

        AnimListener listener = aListener;
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
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
