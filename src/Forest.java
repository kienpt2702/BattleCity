import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Forest extends Block{

    public Forest(GamePanel panel, int x, int y) {
        super(panel, x, y);
    }
    @Override
    public void setImage(){
        try {
            image = ImageIO.read(new File("png/Grid/forest.png"));
        } catch (IOException ignored) {
        }
    }
    @Override
    public boolean collision(Character character){
        return false;
    }
    @Override
    public boolean collision(Bullet bullet){
        return false;
    }
}
