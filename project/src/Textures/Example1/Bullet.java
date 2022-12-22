package Textures.Example1;

import javax.media.opengl.GL;

public class Bullet {
    int x,y;
    String direction;
    Bullet(int x,int y,String direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public void DrawBullet(GL gl, float scale,int textures[]) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length-2]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (50.0) - 0.9, y / (50.0) - 0.9, 0);
        gl.glScaled(0.01 * scale, 0.01 * scale, 1);
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
}
