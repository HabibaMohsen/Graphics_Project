package Textures.Example1;

import Textures.AnimListener;
import Textures.TextureReader;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Objects;

public class AnimGLEventListener4 extends AnimListener {

    int animationIndex = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int x = maxWidth / 2, y = maxHeight / 2;
    int positionIndex=0;
    int triggered =0,triggerHunter=0;

    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {"Man1.png", "Man2.png", "Man3.png", "Man4.png","Man1_left.png", "Man2_left.png", "Man3_left.png", "Man4_left.png","Man1_right.png", "Man2_right.png", "Man3_right.png", "Man4_right.png","Man1_down.png", "Man2_down.png", "Man3_down.png", "Man4_down.png","Man1_topleft.png", "Man2_topleft.png", "Man3_topleft.png", "Man4_topleft.png","Man1_topright.png", "Man2_topright.png", "Man3_topright.png", "Man4_topright.png","Man1_downleft.png", "Man2_downleft.png", "Man3_downleft.png", "Man4_downleft.png","Man1_downright.png", "Man2_downright.png", "Man3_downright.png", "Man4_downright.png","bullet.png","Back.png"};
    String position[] = {"UP","UPRIGHT","RIGHT","DOWNRIGHT","DOWN","DOWNLEFT","LEFT","UPLEFT"};

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    ArrayList<Bullet> bullets = new ArrayList<>();

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
                texture[i] = TextureReader.readTexture(assetsFolderName + "//man//" + textureNames[i], true);
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

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackground(gl);
        handleKeyPress();
        DrawSprite(gl, x, y, animationIndex, 1);

        if (triggered>triggerHunter){
            bullets.add(new Bullet(x,y,position[positionIndex]));
            bullets.get(bullets.size()-1).DrawBullet(gl,1,textures);
            triggerHunter=triggered;
        }
        for (int i=0;i<bullets.size();i++) {
            if (bullets.get(i) != null){
                if (Objects.equals(bullets.get(i).direction, "UP")) {
                    bullets.get(i).y++;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "UPRIGHT")) {
                    bullets.get(i).y++;
                    bullets.get(i).x++;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "RIGHT")) {
                    bullets.get(i).x++;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "DOWNRIGHT")) {
                    bullets.get(i).y--;
                    bullets.get(i).x++;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "DOWN")) {
                    bullets.get(i).y--;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "DOWNLEFT")) {
                    bullets.get(i).y--;
                    bullets.get(i).x--;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "LEFT")) {
                    bullets.get(i).x--;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
                if (Objects.equals(bullets.get(i).direction, "UPLEFT")) {
                    bullets.get(i).x--;
                    bullets.get(i).y++;
                    bullets.get(i).DrawBullet(gl, 1, textures);
                }
            }
        }
//        DrawGraph(gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
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

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length-1]);	// Turn Blending On

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

    /*
     * KeyListener
     */
    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_UP) && isKeyPressed(KeyEvent.VK_LEFT)){
            if (y < maxHeight - 10) {
                y++;
            }
            if (x > 0){
                x--;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+16;
            positionIndex=7;
        }
        else if (isKeyPressed(KeyEvent.VK_UP) && isKeyPressed(KeyEvent.VK_RIGHT)){
            if (y < maxHeight - 10) {
                y++;
            }
            if (x < maxWidth - 10) {
                x++;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+20;
            positionIndex=1;
        }
        else if (isKeyPressed(KeyEvent.VK_DOWN) && isKeyPressed(KeyEvent.VK_RIGHT)){
            if (y > 0) {
                y--;
            }
            if (x < maxWidth - 10) {
                x++;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+28;
            positionIndex=3;
        }
        else if (isKeyPressed(KeyEvent.VK_DOWN) && isKeyPressed(KeyEvent.VK_LEFT)){
            if (y > 0) {
                y--;
            }
            if (x >0) {
                x--;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+24;
            positionIndex=5;
        }
        else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 0) {
                x--;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+4;
            positionIndex=6;
        }
        else if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth - 10) {
                x++;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+8;
            positionIndex=2;
        }
        else if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (y > 0) {
                y--;
            }
            animationIndex++;
            animationIndex = (animationIndex % 4)+12;
            positionIndex=4;
        }
        else if (isKeyPressed(KeyEvent.VK_UP)) {
            if (y < maxHeight - 10) {
                y++;
            }
            animationIndex++;
            animationIndex = animationIndex % 4;
            positionIndex=0;
        }

        if (isKeyPressed(KeyEvent.VK_SPACE)){
            triggered++;
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
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care 
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener4());
    }
}
