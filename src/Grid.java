import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grid {
    BufferedImage image, image2, deathFlag;
    public Grid(){
        try {
            image = ImageIO.read(new File("png/Grid/grid0.png"));
            image2 = ImageIO.read(new File("png/Grid/grid1.png"));
            deathFlag = ImageIO.read(new File("png/deadflag.png"));
        } catch (IOException e) {
        }
    }
    public void paint(Graphics2D g2d, int x, int y, int gridNum){
        switch (gridNum){
            case 0:
                g2d.drawImage(image,x,y,null);
                break;
            case 1:
                g2d.drawImage(image2,x,y,null);
                break;
            case -1:
                g2d.drawImage(deathFlag,x,y,null);
                break;
        }
    }
}
