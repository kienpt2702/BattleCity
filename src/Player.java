import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Character{
    boolean pressOtherKey;
    private int health;
    public Player(MyPanel panel){
        super(panel);
        health = 3;
        allImage = new HashMap<>();
        try {
            for(int i =0; i <=3; i++){
                imageDirection = new ArrayList<>();
                for(int j =0; j<=2; j++){
                    imageDirection.add(ImageIO.read(new File("png/"+i+"/"+j+".png")));
                }
                allImage.put(i, imageDirection);
            }
        } catch (IOException e) {
        }
        image = allImage.get(0).get(0);
    }
    public void playerPressed(KeyEvent e){
        int prevDirection = direction;
        boolean shot = false;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                direction = 3;
                break;
            case KeyEvent.VK_LEFT:
                direction = 1;
                break;
            case KeyEvent.VK_DOWN:
                direction = 0;
                break;
            case KeyEvent.VK_RIGHT:
                direction = 2;
                break;
            case KeyEvent.VK_A:
            // design fire option
                shot = true;
                fire();
                break;
            default:
                count = 0;
                pressOtherKey = true;
        }
        this.changeSpeed();
        if(shot) speedX = speedY = 0;
        if(pressOtherKey){
            speedX = speedY = 0;
            pressOtherKey = false;
        }
        if(prevDirection != direction){
            count = 0;
        }
        image = allImage.get(direction).get(0);
    }
    public void playerReleased(){
        speedX = 0;
        speedY = 0;
    }
    @Override
    public void damaged(){
        health--;
        if(health <=0) panel.trash.add(this);
        else x = y = 0;
    }
    @Override
    public void move(){
        this.condition();
        super.move();
    }
}
