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
                speedY = 3;
                speedX = 0;
                break;
            case 1:
                speedX = -3;
                speedY = 0;
                break;
            case 2:
                speedX = 3;
                speedY = 0;
                break;
            case 3:
                speedY = -3;
                speedX = 0;
                break;
        }
        for(Character character: panel.list){
            if(collision(character)){
                if(this.source.getClass() != character.getClass()){
                    character.damaged();
                    explode = true;
                }
                this.getDamage();
                break;
            }
        }
        for(Bullet bullet: panel.bullets){
            if(collision(bullet) && this != bullet){
                explode = true;
                this.getDamage();
                break;
            }
        }
        for(Block block: panel.blocks){
            if(this.collision(block)){
                this.getDamage();
                explode = true;
                block.getDamage();
                break;
            }
        }
        if(explode) new Explosion(panel,x,y);
        x += speedX;
        y += speedY;
    }
    private void getDamage(){
        panel.trash.add(this);
        this.source.bullet = null;
    }
    public boolean needToDelete(){
        if(x >= 600 || x <=0 || y<= 0|| y>=600 || panel.trash.contains(this)){
            this.source.bullet = null;
            return true;
        }
        return false;
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
    public boolean collision(Block block){
        return panel.collison(this.getRectangle2D(), block.getRectangle2D(true));
    }

    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y,image.getWidth(), image.getHeight());
    }
}
