import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MyPanel extends JPanel implements Runnable{
    boolean exit;
    Thread thread;
    Player player;
    ControlObject controlObject;
    ArrayList<Character> list = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Explosion> explosions = new ArrayList<>();
    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList trash = new ArrayList<>();
    GameMap gameMap = new GameMap(this);
    boolean isRunning;
    public MyPanel(){
        controlObject = new ControlObject(this);
//        controlObject.init();
        this.setPreferredSize(new Dimension(600,650));
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                player.playerPressed(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                        isRunning = !isRunning;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        exit = true;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                player.playerReleased();
            }
        });
        this.setFocusable(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        controlObject.paint(g2d);
        g2d.drawString(""+controlObject.totalEnemy,10,10);
    }
    public void update(){
        controlObject.update();
    }

    public boolean collison(Rectangle2D thisObject, Rectangle2D otherObject){
        return thisObject.intersects(otherObject);
    }
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (!exit){
            Thread.onSpinWait();
            while (isRunning){
                this.update();
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                if(exit) break;
            }
        }
    }
}
