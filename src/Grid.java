import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grid {
    BufferedImage image;
    BufferedImage image2;
    public Grid(){
        try {
            image = ImageIO.read(new File("png/Grid/grid0.png"));
            image2 = ImageIO.read(new File("png/Grid/grid1.png"));
        } catch (IOException e) {
        }
    }
    public void paint(Graphics2D g2d, int x, int y, int gridNum){
        if(gridNum == 0) g2d.drawImage(image,x,y,null);
        if(gridNum == 1) g2d.drawImage(image2,x,y,null);
    }
}
