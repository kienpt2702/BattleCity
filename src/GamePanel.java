import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    boolean exit,isRunning;
    Thread thread;
    ControlObject controlObject;
    ControlMusic controlMusic;
    ArrayList<Character> characters = new ArrayList<>(6);
    ArrayList<Bullet> bullets = new ArrayList<>(50);
//    ArrayList<Explosion> explosions = new ArrayList<>(50);
    ArrayList<Block> blocks = new ArrayList<>(20);
    ArrayList trash = new ArrayList<>();
    ArrayList<RandomObject> randomObjects = new ArrayList<>();
    GameMap gameMap;
    Player player;
    GameFrame myFrame;
    Menu menu;
    public GamePanel(GameFrame myFrame){
        this.myFrame = myFrame;
        gameMap = new GameMap(this, myFrame.getLevel());
        controlObject = new ControlObject(this);
        player = controlObject.player;
        controlMusic = new ControlMusic();
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
