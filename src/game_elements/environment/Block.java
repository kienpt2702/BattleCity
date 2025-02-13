package game_elements.environment;

import game_elements.game_character.Bullet;
import game_elements.game_character.TankCharacter;
import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block{
    int coorX, coorY;
    int x,y;
    int health;
    GamePanel panel;
    public BufferedImage image;
    public Block(GamePanel panel, int x, int y){
        setImage();
        // in 2D array-> reversed
        coorX = y;
        coorY = x;
        this.x = x*image.getWidth();
        this.y = y*image.getHeight();
        this.panel = panel;
        health = 2;
        this.panel.blocks.add(this);
    }
    public void setImage(){
        try {
            image = ImageIO.read(new File("png/Grid/brick.png"));
        } catch (IOException e) {
        }
    }
    public void getDamage(){
        health--;
        if(health <= 0) {
            panel.gameMap.grids[coorX][coorY] = 0;
            panel.trash.add(this);
        }
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x+7,y+7,image.getWidth()-7,image.getHeight()-7);
    }
    public Rectangle2D getRectangle2D(boolean bullet){
        return new Rectangle2D.Double(x,y,image.getWidth(),image.getHeight());
    }
    public boolean collision(TankCharacter character){
        return panel.collision(character.getRectangle2D(), this.getRectangle2D());
    }
    public boolean collision(Bullet bullet){
        return panel.collision(bullet.getRectangle2D(), this.getRectangle2D());
    }
}
