import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Explosion {
    MyPanel panel;
    int x, y, count;
    BufferedImage image;
    public Explosion(MyPanel myPanel, int x, int y){
        this.panel = myPanel;
        this.x = x;
        this.y = y;
        panel.explosions.add(this);
    }
    public void paint(Graphics2D g2d){
        try {
            image = ImageIO.read(new File("C:\\Users\\Bach_Vuong\\IdeaProjects\\MyGame\\png\\Explosion\\"+count+".png"));
        } catch (IOException e) {
        }
        g2d.drawImage(image,x,y,null);
        count++;
        if(count > 2) panel.trash.add(this);
    }
}
