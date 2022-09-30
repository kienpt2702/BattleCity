package game_controllers.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;
    File file;
    public boolean isPausing,isClosed;
    public Music(String pathName) {
        file = new File(pathName);
        AudioInputStream audio;
        try{
            audio = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        this.playMusic();
    }
    public void update(){
        if(!clip.isRunning() && !isPausing) {
            clip.close();
            isClosed = true;
        }
    }
    public void playMusic(){
        clip.start();
         isPausing = false;
    }
    public void stopMusic(){
        clip.stop();
        isPausing = true;
    }
}
