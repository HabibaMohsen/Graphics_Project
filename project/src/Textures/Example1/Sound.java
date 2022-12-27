package Textures.Example1;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[]= new URL[30];
    public  Sound(){
        soundURL[0]= getClass().getResource("/sound/main.wav");
        soundURL[1]= getClass().getResource("/sound/tryagain.wav");
        soundURL[2]= getClass().getResource("/sound/win.wav");

    }
    public void setFile(int i) {
        try {
            AudioInputStream s1= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(s1);
        }

    catch(Exception e){ }}
    public void play(){
clip.start();
    }
    public void loop(){
clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
clip.stop();
    }
    public void playMusic(int i){
        setFile(i);
        play();
        loop();

    }
    public void stopMusic(){
        stop();

    }
    public  void playSE(int i){
        setFile(i);
        play();
    }
}
