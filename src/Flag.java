import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Flag extends Block{
    public Flag(MyPanel panel, int x, int y) {
        super(panel, x, y);
        health = 1;
    }
    @Override
    public void setUp(int x, int y){
        try {
            image = ImageIO.read(new File("C:\\Users\\Bach_Vuong\\IdeaProjects\\MyGame\\png\\flag.png"));
        } catch (IOException e) {
        }
        this.x = x*image.getWidth();
        this.y = y*image.getHeight();
    }
}
