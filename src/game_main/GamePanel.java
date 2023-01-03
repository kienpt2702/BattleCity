package game_main;

import game_elements.game_character.Bullet;
import game_elements.game_character.Player;
import game_elements.game_character.TankCharacter;
import game_controllers.ControlMusic;
import game_controllers.ControlObject;
import game_elements.environment.Block;
import game_elements.environment.RandomObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePanel extends JPanel implements Runnable{
    public boolean exit,isRunning;
    public Thread thread;
    public ControlObject controlObject;
    public ControlMusic controlMusic;
    public ArrayList<TankCharacter> characters = new ArrayList<>(6);
    public ArrayList<Bullet> bullets = new ArrayList<>(50);
    public ArrayList<Block> blocks = new ArrayList<>(20);
    public HashSet trash = new HashSet();
    public ArrayList<RandomObject> randomObjects = new ArrayList<>();
    public GameMap gameMap;
    public Player player;
    public GameFrame myFrame;
    public GameMenu menu;
    public GamePanel(GameFrame myFrame){
        this.myFrame = myFrame;
        this.gameMap = new GameMap(this, myFrame.getLevel());
        this.controlObject = new ControlObject(this);
        this.player = controlObject.player;
        this.controlMusic = new ControlMusic();
        this.setPreferredSize(new Dimension(600,650));
        initKeyBinding();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        controlObject.paint(g2d);
    }
    public void update(){
        controlObject.update();
        controlMusic.update();
    }
    public boolean collision(Rectangle2D thisObject, Rectangle2D otherObject){
        return thisObject.intersects(otherObject);
    }
    public void startGame(){
        if(thread == null){
            menu = myFrame.menu;
            isRunning = true;
            controlMusic.init();
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run() {
        while (!exit){
            System.out.print("");
            if(isRunning){
                this.update();
                this.repaint();
                try {
                    Thread.sleep(13);
                } catch (InterruptedException e) {
                }
                if(exit) break;
            }
        }
    }
    public void endGame(){
        isRunning = false;
        controlMusic.stopMusic();
    }
    public void openMenu(){
        myFrame.add(menu);
        menu.setVisible(true);
    }
    public void popMenuToPlayAgain(){
        endGame();
        setVisible(false);
        menu.prepareLoseWindow();
        openMenu();
    }
    public void popMenuForNextMap(){
        endGame();
        setVisible(false);
        if(myFrame.getLevel() < GameFrame.MAX_LEVEL){
            menu.prepareNextLevelWindow();
        }
        else{
            myFrame.resetLevel();
            menu.resetMenu();
        }
        openMenu();
    }
    public class UpAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.direction = 3;
            player.changeSpeed();
            player.image = player.allImage.get(player.direction).get(0);
        }
    }
    public class DownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.direction = 0;
            player.changeSpeed();
            player.image = player.allImage.get(player.direction).get(0);
        }
    }
    public class LeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.direction = 1;
            player.changeSpeed();
            player.image = player.allImage.get(player.direction).get(0);
        }
    }
    public class RightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.direction = 2;
            player.changeSpeed();
            player.image = player.allImage.get(player.direction).get(0);
        }
    }
    public class PauseAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            controlMusic.stopMusic();
            setVisible(false);
            openMenu();
            isRunning = !isRunning;
        }
    }
    public class FireAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.fire();
        }
    }
    public class ReleasedMove extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.speedX = player.speedY = 0;
        }
    }
    private void initKeyBinding(){
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP"),"up");
        inputMap.put(KeyStroke.getKeyStroke("released UP"),"released");
        inputMap.put(KeyStroke.getKeyStroke("ENTER"),"enter");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"),"down");
        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "released");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"),"left");
        inputMap.put(KeyStroke.getKeyStroke("released LEFT"),"released");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"),"right");
        inputMap.put(KeyStroke.getKeyStroke("released RIGHT"),"released");
        inputMap.put(KeyStroke.getKeyStroke("A"),"fire");

        actionMap.put("up", new UpAction());
        actionMap.put("down", new DownAction());
        actionMap.put("left", new LeftAction());
        actionMap.put("right", new RightAction());
        actionMap.put("fire", new FireAction());
        actionMap.put("released", new ReleasedMove());
        actionMap.put("enter", new PauseAction());
    }

}
