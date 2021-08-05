import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlLife {
    ControlObject controlObject;
    int[] enemyLife, playerLife;
    BufferedImage enemyCounter, playerCounter;
    public ControlLife(ControlObject controlObject){
        this.controlObject = controlObject;
        enemyLife = new int[controlObject.totalEnemy];
        playerLife = new int[controlObject.panel.player.getHealth()];
        try {
            enemyCounter = ImageIO.read(new File("png/Life/enemy_counter.png"));
        } catch (IOException e) {
        }
    }
    public void paint(Graphics2D g2d){

    }
}
