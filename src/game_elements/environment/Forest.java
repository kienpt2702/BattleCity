package game_elements.environment;

import game_elements.game_character.Bullet;
import game_elements.game_character.TankCharacter;
import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Forest extends Block {

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
    public boolean collision(TankCharacter character){
        return false;
    }
    @Override
    public boolean collision(Bullet bullet){
        return false;
    }
}
