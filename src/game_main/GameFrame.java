package game_main;

import javax.swing.*;


public class GameFrame extends JFrame {
    GameMenu menu;
    public static final int MAX_LEVEL = 2;
    private static int Level = 1;
    public GameFrame(){
        menu = new GameMenu(this);
        this.add(menu);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TankGame");
        this.setVisible(true);
    }
    public int getLevel(){
        return Level;
    }
    public void upLevel(){
        Level++;
    }
    public void resetLevel(){
        Level = 1;
    }
}
