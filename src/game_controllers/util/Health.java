package game_controllers.util;

import game_elements.game_character.TankCharacter;
import game_elements.environment.RandomObject;
import game_main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Health extends RandomObject {
    public Health(GamePanel panel){
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
    public void effect(TankCharacter character) {
        character.increaseHealth();
        damaged();
    }
}
