
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

abstract public class RandomObject {
    Random random;
    GamePanel panel;
    BufferedImage image;
    int x,y;
    public RandomObject(GamePanel panel){
        random = new Random();
        this.panel = panel;
        setImage();
        x = random.nextInt(500);
        y = random.nextInt(500);
        panel.randomObjects.add(this);
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y,image.getWidth(), image.getHeight());
    }
    abstract public void setImage();
    abstract public void effect(Character character);
    public void damaged(){
        panel.trash.add(this);
        panel.controlMusic.add("Battle City (MP3)/SFX hitGift.wav");

    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
}
