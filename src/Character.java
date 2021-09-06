import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

abstract class Character {
    int x, y, speedX, speedY, immortalTime;
    int count, direction;
    int reloadTime;
    MyPanel panel;
    HashMap<Integer, ArrayList<BufferedImage>> allImage;
    ArrayList<BufferedImage> imageDirection;
    BufferedImage image;
    Bullet bullet;
    public Character(MyPanel panel){
        this.panel = panel;
        panel.list.add(this);
        immortalTime = 100;
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    public void move() {
        immortalTime--;
        int prevX = x;
        int prevY = y;
        x += speedX;
        y += speedY;
        if(collision()){
            x = prevX;
            y = prevY;
        }
        if (++count >2){
            count = 0;
        }
        reloadTime--;
    }
    public void condition(){
        if(x + speedX > panel.getWidth() - image.getWidth() || x + speedX < 0){
            speedX = 0;
        }
        if(y + speedY > panel.getHeight()-50 - image.getHeight() || y + speedY < 0){
            speedY = 0;
        }
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y, image.getWidth(), image.getHeight());
    }
    public boolean collision(){
        for(Character character : panel.list){
            if(this != character){
                if(panel.collison(this.getRectangle2D(), character.getRectangle2D())){
                    return true;
                }
            }
        }
        for(Block block: panel.blocks){
            if(panel.collison(this.getRectangle2D(),block.getRectangle2D())){
                return true;
            }
        }
        for(RandomObject randomObject: panel.randomObjects){
            if(panel.collison(this.getRectangle2D(), randomObject.getRectangle2D())){
                panel.trash.add(randomObject);
                randomObject.effect(this);
                break;
            }
        }
        return false;
    }
    abstract public void fire();
    abstract public void damaged();
    public void increaseHealth(){}
    public void changeSpeed(){
        switch (direction) {
            case 0:
                speedY = 2;
                speedX = 0;
                break;
            case 1:
                speedX = -2;
                speedY = 0;
                break;
            case 2:
                speedX = 2;
                speedY = 0;
                break;
            case 3:
                speedY = -2;
                speedX = 0;
                break;
        }
    }
}
