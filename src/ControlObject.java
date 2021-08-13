import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ControlObject {
    ControlLife controlLife;
    MyPanel panel;
    Random rand = new Random();
    int totalEnemy = 6;
    int enemyLeft;
    private static final int ENEMY_ON_STAGE = 5;
    Player player;
    public ControlObject(MyPanel panel){
        this.panel = panel;
        this.init();
        controlLife = new ControlLife(this);
    }
    public void init(){
        player = new Player(panel);
//        for(int i =0; i< ENEMY_ON_STAGE; i++) new Enemy(panel);
//        enemyLeft = totalEnemy - ENEMY_ON_STAGE;
        for(int[] coordinate: panel.gameMap.posList){
            new Enemy(panel,coordinate[0]*30,coordinate[1]*30);
        }
        enemyLeft = totalEnemy - panel.gameMap.posList.size();
    }
    public void update(){
        for(Character character: panel.list){
            character.move();
        }
        for(Bullet bullet: panel.bullets){
            bullet.move();
        }
        this.removeTrash();
        if(panel.list.size()-1 < ENEMY_ON_STAGE && enemyLeft> 0){
            ArrayList<int[]> position = panel.gameMap.posList;
            int[] randPos = position.get(rand.nextInt(position.size()));
            new Enemy(panel,randPos[0]*30, randPos[1]*30);
            enemyLeft--;
        }
    }
    public void paint(Graphics2D g2d){
        panel.gameMap.loadMap(g2d);
        for(Block block: panel.blocks){
            block.paint(g2d);
        }
        for(Character character: panel.list){
            character.paint(g2d);
        }
        for(Bullet bullet: panel.bullets){
            bullet.paint(g2d);
        }
        for(Explosion explosion: panel.explosions){
            explosion.paint(g2d);
        }
        controlLife.paint(g2d);
    }
    private void removeTrash(){
        panel.list.removeIf(character -> panel.trash.contains(character));
        panel.bullets.removeIf(bullet->(bullet.needToDelete()));
        panel.explosions.removeIf(explosion -> panel.trash.contains(explosion));
        panel.blocks.removeIf((block -> panel.trash.contains(block)));
        panel.trash.clear();
    }
}
