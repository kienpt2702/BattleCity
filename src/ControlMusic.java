
import java.util.ArrayList;

public class ControlMusic {
    ArrayList<Music> musicList;
    ArrayList<Music> deletedMusic;
    Music intro,outro;
    public ControlMusic(){
        musicList = new ArrayList<>(4);
        deletedMusic = new ArrayList<>();
    }
    public void init(){
        intro = new Music("Battle City (MP3)/intro.wav");
        this.add(intro);
    }
    public void update(){
//        if(intro != null){
//            if(intro.isClosed){
//                System.out.println(true);
//                this.add("Battle City (MP3)/2.wav");
//                intro = null;
//            }
//        }
        for(Music music: musicList){
            music.update();
            if(!music.clip.isOpen()) deletedMusic.add(music);
        }
        musicList.removeIf(music -> deletedMusic.contains(music));
        deletedMusic.clear();
    }
    public void playMusic(){
        for(Music music: musicList){
            music.playMusic();
        }
    }
    public void stopMusic(){
        for(Music music: musicList){
            music.stopMusic();
        }
    }
    public void add(Music music){
        musicList.add(music);
    }
    public void add(String musicFile){
        musicList.add(new Music(musicFile));
    }
}
