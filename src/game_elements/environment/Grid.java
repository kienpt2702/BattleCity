package game_elements.environment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Grid {
    int width, height;
    BufferedImage image,deathFlag;
    public Grid(){
        try {
            image = ImageIO.read(new File("png/Grid/grid0.png"));
            deathFlag = ImageIO.read(new File("png/flag.png"));
        } catch (IOException e) {
        }
        width = image.getWidth();
        height = image.getHeight();
    }
    public void paint(Graphics2D g2d, int x, int y, int gridNum){
        g2d.drawImage(image,x,y,null);

    }
}
