import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sea extends Block{
    static BufferedImage sea;
    public Sea(GamePanel panel, int x, int y) {
        super(panel, x, y);
    }
    @Override
    public void setImage(){
        try {
            image = ImageIO.read(new File("png/Grid/deepsea.png"));
        } catch (IOException e) {
        }
        sea = image;
    }
    @Override
    public void paint(Graphics2D g2d){}
    @Override
    public boolean collision(Character character){
        if(super.collision(character)){
            character.slowDown = true;
        }
        return false;
    }
    @Override
    public boolean collision(Bullet bullet){
        if(super.collision(bullet)){
            bullet.slowDown = true;
        }
        return false;
    }
    public static void drawImage(Graphics2D g2d, int x, int y){
        g2d.drawImage(sea,x,y,null);
    }
}
