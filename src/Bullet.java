import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {
    int x, y, speedX, speedY, direction;
    BufferedImage image;
    MyPanel panel;
    Character source;
    boolean explode;
    public Bullet(MyPanel panel, int x, int y, int direction, Character source){
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.source = source;
        try {
            image = ImageIO.read(new File("png/Bullet/"+direction+".png"));
        } catch (IOException e) {
        }
        this.panel.bullets.add(this);
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image, x,y,null);
    }
    public void move(){
        switch (direction){
            case 0:
                speedY = 5;
                speedX = 0;
                break;
            case 1:
                speedX = -5;
                speedY = 0;
                break;
            case 2:
                speedX = 5;
                speedY = 0;
                break;
            case 3:
                speedY = -5;
                speedX = 0;
                break;
        }
        for(Character character: panel.list){
            if(collision(character)){
                if(this.source.getClass() != character.getClass()){
                    panel.trash.add(character);
                    if(character instanceof Enemy){
                        panel.controlObject.totalEnemy--;
                    }
                    explode = true;
                }
                panel.trash.add(this);
                break;
            }
        }
        for(Bullet bullet: panel.bullets){
            if(collision(bullet) && this != bullet){
                explode = true;
                panel.trash.add(bullet);
                panel.trash.add(this);
                break;
            }
        }
        for(Block block: panel.blocks){
            if(panel.collison(this.getRectangle2D(), block.getRectangle2D())){
                panel.trash.add(this);
                explode = true;
                block.getDamage();
                break;
            }
        }
        if(explode) new Explosion(panel,x,y);
        x += speedX;
        y += speedY;
    }
    public boolean collision(Character character){
        if(this.source == character){
            return false;
        }
        return panel.collison(this.getRectangle2D(), character.getRectangle2D());
    }
    public boolean collision(Bullet bullet){
        return panel.collison(this.getRectangle2D(), bullet.getRectangle2D());
    }

    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y,image.getWidth(), image.getHeight());
    }
}
