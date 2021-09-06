import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MyPanel extends JPanel implements Runnable{
    boolean exit;
    Thread thread;
    ControlObject controlObject;
    ControlMusic controlMusic;
    ArrayList<Character> list = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Explosion> explosions = new ArrayList<>();
    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList trash = new ArrayList<>();
    ArrayList<RandomObject> randomObjects = new ArrayList<>();
    GameMap gameMap;
    boolean isRunning;
    public MyPanel(){
        gameMap = new GameMap(this);
        controlObject = new ControlObject(this);
        controlMusic = new ControlMusic();
        this.setPreferredSize(new Dimension(600,650));
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

    public boolean collison(Rectangle2D thisObject, Rectangle2D otherObject){
        return thisObject.intersects(otherObject);
    }
    public void startGame(){
        if(thread == null){
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
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                if(exit) break;
            }
        }
        System.out.println("outLoop");
    }

    public void endGame(){
        isRunning = false;
        controlMusic.stopMusic();
    }
}
