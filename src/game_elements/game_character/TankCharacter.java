package game_elements.game_character;

import game_elements.environment.Block;
import game_elements.environment.RandomObject;
import game_main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class TankCharacter {
    public boolean slowDown;
    public int x, y, speedX, speedY, immortalTime;
    public int count, direction;
    public int reloadTime;
    public GamePanel panel;
    public HashMap<Integer, ArrayList<BufferedImage>> allImage;
    public ArrayList<BufferedImage> imageDirection;
    public BufferedImage image;
    public Bullet bullet;
    public TankCharacter(GamePanel panel){
        this.panel = panel;
        panel.characters.add(this);
        immortalTime = 100;
        allImage = new HashMap<>(4);
        setImage();
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    public void move() {
        immortalTime--;
        if(!collision()){
            if(slowDown){
                x += (speedX/2);
                y += (speedY/2);
                slowDown = false;
            }
            else{
                x += speedX;
                y += speedY;
            }
        }
        if (++count >=2){
            count = 0;
        }
        reloadTime--;
    }
    public void condition(){
        if(x + speedX > 600 - image.getWidth() || x + speedX < 0){
            speedX = 0;
        }
        if(y + speedY > 600 - image.getHeight() || y + speedY < 0){
            speedY = 0;
        }
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x+speedX,y+speedY, image.getWidth(), image.getHeight());
    }
    public boolean collision(){
        for(TankCharacter character : panel.characters){
            if(this != character){
                if(panel.collision(this.getRectangle2D(), character.getRectangle2D())){
                    return true;
                }
            }
        }
        for(Block block: panel.blocks){
            if(block.collision(this)){
                return true;
            }
        }
        for(RandomObject randomObject: panel.randomObjects){
            if(panel.collision(this.getRectangle2D(), randomObject.getRectangle2D())){
                panel.trash.add(randomObject);
                randomObject.effect(this);
                break;
            }
        }
        return false;
    }
    abstract public void setImage();
    abstract public void fire();
    abstract public void damaged();
    public void increaseHealth(){}
    public void changeSpeed(){
        switch (direction) {
            case 0 -> {
                speedY = 2;
                speedX = 0;
            }
            case 1 -> {
                speedX = -2;
                speedY = 0;
            }
            case 2 -> {
                speedX = 2;
                speedY = 0;
            }
            case 3 -> {
                speedY = -2;
                speedX = 0;
            }
        }
    }
}
