import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Health extends RandomObject{
    public Health(MyPanel panel){
        super(panel);
    }
    @Override
    public void setImage() {
        try {
            image = ImageIO.read(new File("png/Life/life.png"));
        } catch (IOException e) {
        }
    }
    @Override
    public void effect(Character character) {
        character.increaseHealth();
        damaged();
    }
}
