package Textures.Example1;

import Textures.AnimListener;
import Textures.TextureReader;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;

public class multiplayer extends AnimListener {
    int timer=0;
    int dirI=50;
    int mySpeedX=60;
    int mySpeedX2=30;
    int mySpeedY=10;
    int mySpeedY2=10;
    int speedEnemyY=-10;
    int speedEnemyY1=-10;
    int speedEnemyY2=-10;
    int speedEnemyY3=-10;
    int speedEnemyX=25;
    int speedEnemyX2=54;
    int speedEnemyX3=65;
    int dirJ=50;
    int maxEnemy=1;

    int[][] ENEMY = new int[dirI][dirJ];
    int maxWidth = 100;
    int maxHeight = 100;
    int x = (maxWidth / 2)-5, y = (maxHeight / 2)-40;
    boolean start = false;
    int i = 0;
    int enemyIndex=7;
    int j = 4;
    int j2=7;

    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"road1_2.jpg","road2_3.jpg","road3.jpg","road4_3.jpg","carstright.png","carright.png","carleft.png","carstright1.png","carright1.png","carleft1.png","carenemy_1.png","carenemy2.png","carenemy3.png","carenemy4.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void display(GLAutoDrawable gld) {
        timer++;
        if(timer==400){
            JOptionPane.showMessageDialog(null, "Congratulations you win..", "Congratulations you win..",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        DrawBackground(gl, i);
        if(start){
            i=++i%4;
        }
        handleKeyPress();
        if((Math.abs(mySpeedY-speedEnemyY1)<9&&Math.abs(mySpeedX-speedEnemyX)<8)||(Math.abs(mySpeedX-speedEnemyX2)<8&&Math.abs(mySpeedY-speedEnemyY2)<9)||((Math.abs(mySpeedX-speedEnemyX3)<8&&Math.abs(mySpeedY-speedEnemyY3)<9))){

            JOptionPane.showMessageDialog(null, "GameOver.", "GameOver",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);


        }
        if((Math.abs(mySpeedY2-speedEnemyY1)<9&&Math.abs(mySpeedX2-speedEnemyX)<8)||(Math.abs(mySpeedX2-speedEnemyX2)<8&&Math.abs(mySpeedY2-speedEnemyY2)<9)||((Math.abs(mySpeedX2-speedEnemyX3)<8&&Math.abs(mySpeedY2-speedEnemyY3)<9))){
            JOptionPane.showMessageDialog(null, "GameOver.", "GameOver",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);


        }
        DrawSprite(gl, mySpeedX, mySpeedY, j, 1);
        DrawSprite1(gl, mySpeedX2, mySpeedY2, j2, 1);


        if(start){
            DrawSprite(gl,speedEnemyX,speedEnemyY1,10,1);
            DrawSprite(gl,speedEnemyX2+1,speedEnemyY2,11,1);
            DrawSprite(gl,speedEnemyX3+1,speedEnemyY3,12,1);
            speedEnemyY=++speedEnemyY%135;
            speedEnemyY1=speedEnemyY-5;
            speedEnemyY2=speedEnemyY-20;
            speedEnemyY3=speedEnemyY-35;
            if(speedEnemyY>=133){
                speedEnemyX=(int)((Math.random()*40)+25);
                speedEnemyX2=(int)((Math.random()*40)+25);
                speedEnemyX3=(int)((Math.random()*40)+25);
            }

        }




////////////////////////////////////////////////////////////////////////////////////////
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, double scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (100 / 2.0) - 0.9, y / (100 / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawSprite1(GL gl, int x, int y, int index, double scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (100 / 2.0) - 0.9, y / (100 / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawBackground(GL gl, int animationIndexRoad) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[animationIndexRoad]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void RandomCar(GL gl, int x, int y, int index, double scale) {

    }
    public void handleKeyPress() {
        if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            start=true;
        }
        if (isKeyPressed(KeyEvent.VK_A)&&isKeyPressed(KeyEvent.VK_S)) {
            start=true;
        }
        else if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_UP)) {
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_UP)) {
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            start = true;
        }
        else if (isKeyPressed(KeyEvent.VK_A)&&isKeyPressed(KeyEvent.VK_W)) {
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_D)&&isKeyPressed(KeyEvent.VK_W)) {
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_D)&&isKeyPressed(KeyEvent.VK_S)) {
            start=true;
        }

        else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            start=true;
            if (mySpeedX > 25) {
                mySpeedX--;
            }
            j=6;
        }
        if (isKeyPressed(KeyEvent.VK_A)) {
            start=true;
            if (mySpeedX2 > 25) {
                mySpeedX2--;
            }
            j2=9;
        }
        else if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            start=true;
            if (mySpeedX < maxWidth - 35) {
                mySpeedX++;
            }
            j=5;
        }
        if (isKeyPressed(KeyEvent.VK_D)) {
            start=true;
            if (mySpeedX2 < maxWidth - 35) {
                mySpeedX2++;
            }
            j2=8;
        }
        else if (isKeyPressed(KeyEvent.VK_DOWN)) {
            start=true;
            if(mySpeedY>10){
                mySpeedY--;
            }
        }
        if (isKeyPressed(KeyEvent.VK_S)) {
            start=true;
            if(mySpeedY2>10){
                mySpeedY2--;
            }
        }
        else if (isKeyPressed(KeyEvent.VK_UP)) {
            start=true;
            if(mySpeedY<90){
                mySpeedY++;
                //speedEnemyY-=0.6;
            }
        }
        if (isKeyPressed(KeyEvent.VK_W)) {
            start=true;
            if(mySpeedY2<90){
                mySpeedY2++;
                //speedEnemyY-=0.6;
            }
        }

    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
        ///////////////////////////////////////////////////////////////////////////////
        if (!isKeyPressed(KeyEvent.VK_LEFT)) {
            start=true;
            if (mySpeedX > 25) {
                mySpeedX--;
            }
            j=4;

        }
        if (!isKeyPressed(KeyEvent.VK_A)) {
            start=true;
            if (mySpeedX2 > 25) {
                mySpeedX2--;
            }
            j2=7;

        }
        else if (!isKeyPressed(KeyEvent.VK_RIGHT)) {
            start=true;
            if (mySpeedX2 < maxWidth - 35) {
                mySpeedX2++;
            }
            j=4;

        }
        else if (!isKeyPressed(KeyEvent.VK_D)) {
            start=true;
            if (mySpeedX2 < maxWidth - 35) {
                mySpeedX2++;
            }
            j2=7;

        }
        /////////////////////////////////////////////////////////////////////////////////
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }


}
