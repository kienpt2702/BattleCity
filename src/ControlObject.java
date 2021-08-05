import java.awt.*;

public class ControlObject {
    ControlLife controlLife;
    MyPanel panel;
    int totalEnemy = 6;
    int enemyLeft;
    private static final int ENEMY_ON_STAGE = 5;
    public ControlObject(MyPanel panel){
        this.panel = panel;
        this.init();
        controlLife = new ControlLife(this);
    }
    public void init(){
        panel.player = new Player(panel);
        for(int i =0; i< ENEMY_ON_STAGE; i++) new Enemy(panel);
        enemyLeft = totalEnemy - ENEMY_ON_STAGE;
    }
    public void update(){
        for(Character character: panel.list){
            character.move();
        }
        for(Bullet bullet: panel.bullets){
            bullet.move();
        }
        this.removeTrash();
        if(panel.list.size()-1 < ENEMY_ON_STAGE && enemyLeft> 0 && panel.list.contains(panel.player)){
            new Enemy(panel);
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
    }
    private void removeTrash(){
        panel.list.removeIf(character -> panel.trash.contains(character));
        panel.bullets.removeIf(bullet->(bullet.x >=600 || bullet.x <=0 || bullet.y >=600 || bullet.y <=0 || panel.trash.contains(bullet)));
        panel.explosions.removeIf(explosion -> panel.trash.contains(explosion));
        panel.blocks.removeIf((block -> panel.trash.contains(block)));
        panel.trash.clear();
    }
}
