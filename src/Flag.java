import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Flag extends Block{
    public Flag(MyPanel panel, int x, int y) {
        super(panel, x, y);
        health = 1;
    }
    @Override
    public void setImage(){
        try {
            image = ImageIO.read(new File("png/flag.png"));
        } catch (IOException e) {
        }
    }
    @Override
    public void getDamage(){
        super.getDamage();
        panel.gameMap.grids[coorX][coorY] = -1;
    }
}
