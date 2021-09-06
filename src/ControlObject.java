import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ControlObject {
    ControlLife controlLife;
    MyPanel panel;
    Random rand = new Random();
    boolean endGame;
    int totalEnemy = 10;
    int enemyLeft, timeToDrop;
    private static final int ENEMY_ON_STAGE = 5;
    Player player;
    public ControlObject(MyPanel panel){
        this.panel = panel;
        this.init();
        controlLife = new ControlLife(this);
        timeToDrop = 500;
    }
    public void init(){
        player = new Player(panel);
        for(int[] coordinate: panel.gameMap.posList){
            new Enemy(panel,coordinate[0]*30,coordinate[1]*30);
        }
        enemyLeft = totalEnemy - panel.gameMap.posList.size();
    }
    public void update(){
        for(Character character: panel.list){
            character.move();
        }
        // use counting for loop to avoid concurrentModification????:
        for(int i=0; i< panel.bullets.size(); i++){
            panel.bullets.get(i).move();
        }
        this.removeTrash();
        if(panel.list.size()-1 < ENEMY_ON_STAGE && enemyLeft> 0){
            ArrayList<int[]> position = panel.gameMap.posList;
            int[] randPos = position.get(rand.nextInt(position.size()));
            new Enemy(panel,randPos[0]*30, randPos[1]*30);
            enemyLeft--;
        }
        this.generateRandomObject();
        if(totalEnemy <= 0){
            panel.endGame();
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
        for(RandomObject rand: panel.randomObjects){
            rand.paint(g2d);
        }
        controlLife.paint(g2d);

    }
    private void generateRandomObject(){
        timeToDrop--;
        if(timeToDrop <= 0){
            int randInt = rand.nextInt(1);
            switch (randInt){
                case 0 -> new Health(panel);
            }
            timeToDrop = 500;
        }
    }
    private void removeTrash(){
        panel.list.removeIf(character -> panel.trash.contains(character));
        panel.bullets.removeIf(bullet->(bullet.needToDelete()));
        panel.explosions.removeIf(explosion -> panel.trash.contains(explosion));
        panel.blocks.removeIf((block -> panel.trash.contains(block)));
        panel.randomObjects.removeIf(randomObject -> panel.trash.contains(randomObject));
        panel.trash.clear();
    }
}
