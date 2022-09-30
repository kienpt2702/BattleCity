package game_controllers.util;

import game_elements.game_character.Bullet;
import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion {
    GamePanel panel;
    int x, y, count;
    BufferedImage image;
    Bullet bullet;
    public Explosion(Bullet bullet){
        this.bullet = bullet;
//        this.panel = myPanel;
//        this.x = x;
//        this.y = y;
//        panel.explosions.add(this);
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics2D g2d, int x, int y){
        try {
            image = ImageIO.read(new File("png/Explosion/"+count+".png"));
        } catch (IOException e) {
        }
        g2d.drawImage(image,x,y,null);
        count++;
        if(count > 2) bullet.getDamage();
    }
}
