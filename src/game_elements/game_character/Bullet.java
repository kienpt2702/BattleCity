package game_elements.game_character;

import game_controllers.util.Explosion;
import game_elements.environment.Block;
import game_elements.environment.RandomObject;
import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {
    int x, y, speedX, speedY, direction;
    Explosion explosion;
    BufferedImage image;
    GamePanel panel;
    TankCharacter source;
    public boolean slowDown, explode;
    public Bullet(GamePanel panel, int x, int y, int direction, TankCharacter source){
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.source = source;
        try {
            image = ImageIO.read(new File("png/Bullet/"+direction+".png"));
        } catch (IOException e) {
        }
        this.panel.bullets.add(this);
        explosion = new Explosion(this);
    }
    public void paint(Graphics2D g2d){
        if(!explode){
            g2d.drawImage(image,x,y,null);
        }
//        g2d.drawImage(image, x,y,null);
        else{
            explosion.paint(g2d,x,y);
        }
    }
    public void move(){
//        switch (direction){
//            case 0:
//                speedY = 4;
//                speedX = 0;
//                break;
//            case 1:
//                speedX = -4;
//                speedY = 0;
//                break;
//            case 2:
//                speedX = 4;
//                speedY = 0;
//                break;
//            case 3:
//                speedY = -4;
//                speedX = 0;
//                break;
//        }
//        if(!this.isExplode()){
//            if(slowDown){
//                x += speedX/2;
//                y += speedY/2;
//                slowDown = false;
//            }
//            else{
//                x += speedX;
//                y += speedY;
//            }
//        }
//        else{
//            new controller.util.Explosion(panel,x,y);
//        }
        if(!explode){
            switch (direction) {
                case 0 -> {
                    speedY = 4;
                    speedX = 0;
                }
                case 1 -> {
                    speedX = -4;
                    speedY = 0;
                }
                case 2 -> {
                    speedX = 4;
                    speedY = 0;
                }
                case 3 -> {
                    speedY = -4;
                    speedX = 0;
                }
            }
            if(slowDown){
                    x += speedX/2;
                    y += speedY/2;
                    slowDown = false;
            }
            else{
                    x += speedX;
                    y += speedY;
            }
            explode = isExplode();
        }
    }
    private boolean isExplode(){
        for(TankCharacter character: panel.characters){
            if(collision(character)){
//                this.getDamage();
                if(this.source.getClass() != character.getClass()){
                    character.damaged();
                    return true;
                }
                else{
                    this.getDamage();
                    return false;
                }
            }
        }
        for(Bullet bullet: panel.bullets){
            if(collision(bullet) && this != bullet){
                this.getDamage();
                bullet.getDamage();
                return false;
            }
        }
        for(Block block: panel.blocks){
            if(block.collision(this)){
                this.getDamage();
                block.getDamage();
                return true;
            }
        }
        for(RandomObject randomObject: panel.randomObjects){
            if(this.collision(randomObject)){
                randomObject.effect(this.source);
                this.getDamage();
                break;
            }
        }
        return false;
    }
    public void getDamage(){
        panel.trash.add(this);
        this.source.bullet = null;
    }
    public boolean needToDelete(){
        if(x >= 600 || x <=0 || y<= 0|| y>=600 || panel.trash.contains(this)){
            this.source.bullet = null;
            return true;
        }
        return false;
    }
    public boolean collision(TankCharacter character){
        if(this.source == character){
            return false;
        }
        return panel.collision(this.getRectangle2D(), character.getRectangle2D());
    }
    public boolean collision(Bullet bullet){
        return panel.collision(this.getRectangle2D(), bullet.getRectangle2D());
    }
    public boolean collision(RandomObject randomObject){
        return panel.collision(this.getRectangle2D(), randomObject.getRectangle2D());
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y,image.getWidth(), image.getHeight());
    }
}
