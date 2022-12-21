package Textures.Example1;

import Textures.AnimListener;
import Textures.TextureReader;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;

public class AnimGLEventListener3 extends AnimListener {

    int animationIndex = 0;
    int animationIndexRoad = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int x = (maxWidth / 2)-5, y = (maxHeight / 2)-40;
    boolean start = false;
    int i = 0;
    int j = 4;

    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"road1.jpg","road2.jpg","road3.jpg","road4.jpg","carstright.png","carright.png","carleft.png"};
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
//        gl.glLoadIdentity();
        //gl.glOrtho(-maxWidth / 2, maxWidth / 2, -maxHeight / 2, maxHeight / 2, -1, 1);
    }

    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackground(gl, i);
        if(start){
            i=++i%4;
        }
//        if(start=false) {
//
//        }else{
//            for(int i = 0 ; i<4 ; i++){
//                DrawBackground(gl, i);
//                if(i==3){
//                    i=0;
//                }
//            }
//        }
        handleKeyPress();
//        animationIndex = animationIndex % 4;

//        DrawGraph(gl);
        DrawSprite(gl, x, y, j, 1);
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
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On


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


        gl.glDisable(GL.GL_BLEND);
    }

    /*
     * KeyListener
     */
    public void handleKeyPress() {
        if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            start=true;
//            if (x > 0) {
//                x--;
//            }
//            if (y > 0) {
//                y--;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4)+20;


        }
        else if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_UP)) {
//            if (x > 0) {
//                x--;
//            }
//            if (y < maxHeight - 10) {
//                y++;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4)+28;
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_UP)) {
//            if (x < maxWidth - 10) {
//                x++;
//            }
//            if (y < maxHeight - 10) {
//                y++;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4)+4;
            start=true;
        }else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (x < maxWidth - 10) {
//                x++;
//            }
//            if (y > 0) {
//                y--;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4)+12;
            start=true;
        }

        else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            start=true;
            if (x > 0) {
                x--;
            }
           j=6;
//            animationIndex++;
//            animationIndex = (animationIndex%4)+24;

        }
        else if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            start=true;
            if (x < maxWidth - 10) {
                x++;
            }
            j=5;
//            animationIndex++;
//            animationIndex = (animationIndex%4)+8;

        }
        else if (isKeyPressed(KeyEvent.VK_DOWN)) {
//            if (y > 0) {
//                y--;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4)+16;
            start=true;
        }
       else if (isKeyPressed(KeyEvent.VK_UP)) {
//            if (y < maxHeight - 10) {
//                y++;
//            }
//            animationIndex++;
//            animationIndex = (animationIndex%4);
            start=true;
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
            if (x > 0) {
                x--;
            }
            j=4;
//            animationIndex++;
//            animationIndex = (animationIndex%4)+24;

        }
        else if (!isKeyPressed(KeyEvent.VK_RIGHT)) {
            start=true;
            if (x < maxWidth - 10) {
                x++;
            }
            j=4;
//            animationIndex++;
//            animationIndex = (animationIndex%4)+8;

        }
        /////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener3());
    }
}
