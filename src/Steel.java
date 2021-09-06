import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Steel extends Block{
    public Steel(MyPanel panel, int x, int y){
        super(panel,x,y);
    }
    @Override
    public void setImage(){
        try {
            image = ImageIO.read(new File("png/Grid/steel.png"));
        } catch (IOException e) {
        }
    }
    @Override
    public void getDamage(){
    }
}
