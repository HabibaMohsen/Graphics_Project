package Textures.Example1;

import Textures.AnimListener;
import Textures.TextureReader;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class AnimGLEventListener extends AnimListener {

    String textureName = "Back.png";
    TextureReader.Texture texture;
    int textureIndex[] = new int[1];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    @Override
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        //gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
        
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        //number of textures, array to hold the indeces
        gl.glGenTextures(1, textureIndex, 0);

        try {
            texture = TextureReader.readTexture(assetsFolderName + "//" + textureName, true);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[0]);
            
//          mipmapsFromPNG(gl, new GLU(), texture[i]);
            new GLU().gluBuild2DMipmaps(
                    GL.GL_TEXTURE_2D,
                    GL.GL_RGBA, // Internal Texel Format,
                    texture.getWidth(), texture.getHeight(),
                    GL.GL_RGBA, // External format from image,
                    GL.GL_UNSIGNED_BYTE,
                    texture.getPixels() // Imagedata
            );
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();

        DrawBackground(gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);	// Turn Blending On
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex[0]);

        //gl.glScaled(0.5, 0.5, 0);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1f, -1f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();

        gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    public static void main(String[] args) {
        new Anim(new AnimGLEventListener());
    }

}
