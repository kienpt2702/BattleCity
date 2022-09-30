package game_elements.game_character;

import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends TankCharacter {
    private int health;
    public Player(GamePanel panel){
        super(panel);
        health = 3;
        image = allImage.get(0).get(0);
    }
    public int getHealth(){return health;}
    @Override
    public void damaged(){
        if(immortalTime <= 0){
            health--;
            if(health <= 0) {
                panel.trash.add(this);
                panel.controlMusic.add("Battle City (MP3)/SFX destroy.wav");
                panel.controlObject.endGame = true;

            } else{
                x = y = 0;
                panel.controlMusic.add("Battle City (MP3)/SFX revive.wav");
                immortalTime = 100;
            }
        }
    }
    @Override
    public void move(){
        super.condition();
        super.move();
    }

    @Override
    public void setImage() {
        try {
            for(int i =0; i <=3; i++){
                imageDirection = new ArrayList<>();
                for(int j =0; j<=2; j++){
                    imageDirection.add(ImageIO.read(new File("png/"+i+"/"+j+".png")));
                }
                allImage.put(i, imageDirection);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void fire(){
        if(bullet == null && health > 0){
            bullet = new Bullet(panel,x+13,y+13,direction,this);
        }
    }
    @Override
    public void increaseHealth(){
        if(health < 3) health++;
    }
}
