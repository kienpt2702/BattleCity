package game_elements.game_character;

import game_main.GamePanel;

public class FastTank extends Enemy{
    public FastTank(GamePanel panel, int x, int y) {
        super(panel, x, y);
    }
    @Override
    public void changeSpeed(){
        super.changeSpeed();
        speedX *= 1.5f;
        speedY *= 1.5f;
    }
}
