import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlLife {
    ControlObject controlObject;
    BufferedImage enemyCounter, playerCounter;
    public ControlLife(ControlObject controlObject){
        this.controlObject = controlObject;
        try {
            enemyCounter = ImageIO.read(new File("png/Life/enemy_counter.png"));
            playerCounter = ImageIO.read(new File("png/Life/life.png"));
        } catch (IOException e) {
        }
    }
    public void paint(Graphics2D g2d){
        for(int i = 0; i< controlObject.totalEnemy; i++){
            g2d.drawImage(enemyCounter,i* (enemyCounter.getWidth()+10),600,null);
        }
        for(int i = 0; i< controlObject.player.getHealth(); i++){
            g2d.drawImage(playerCounter,i* (playerCounter.getWidth()+10),630,null);
        }
    }
}
