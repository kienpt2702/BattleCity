import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Enemy extends Character{
    Random rand = new Random();
    int timeToChangeDirection;
    public Enemy(MyPanel panel){
        super(panel);
        reloadTime = 70;
        direction = rand.nextInt(3);
        this.changeSpeed();
        x = rand.nextInt(500);
        y = rand.nextInt(500);
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
        image = allImage.get(direction).get(0);
    }
    @Override
    public void move(){
        timeToChangeDirection++;
        if(timeToChangeDirection >= 100){
            timeToChangeDirection= 0;
            direction = rand.nextInt(3);
            changeSpeed();
        }
        this.condition();
        super.move();
        image = allImage.get(direction).get(0);
        this.fire();
    }
    @Override
    public void condition(){
        if(x + speedX > panel.getWidth() - image.getWidth() || x + speedX < 0){
            speedX *= -1;
            if(speedX > 0) direction = 2;
            else direction = 1;
            changeSpeed();
        }
        if(y + speedY > panel.getHeight() - image.getHeight() || y + speedY < 0){
            speedY *= -1;
            if(speedY > 0) direction = 0;
            else direction = 3;
            changeSpeed();
        }
    }
}
