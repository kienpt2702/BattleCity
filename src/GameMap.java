import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;


public class GameMap {
    ArrayList<int[]> posList = new ArrayList<>(5);
    Grid grid;
    int width, height;
    HashSet<Integer> blockPosition = new HashSet<>();
    int[][] grids = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 2, 0, 2, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 4, },
            { 2, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 2, },
            { 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 2, 2, 2, 2, 0, 0, 0, 4, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 2, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 2, },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, },
            { 4, 0, 2, 0, 0, 0, 0, 2, 0, 4, 0, 2, 0, 0, 0, 0, 0, 2, 0, 4, }
    };
    public GameMap(MyPanel panel) {
        grid = new Grid();
        width = grids[0].length;
        height = grids.length;
        for(int i = 0; i < grids.length; i++){
            for(int j = 0; j < grids[i].length; j++){
                if(grids[i][j] == 2){
                    new Block(panel,j,i);
                    blockPosition.add(j+1+ grids[i].length*i);
                }
                if(grids[i][j] == 3) {
                    new Flag(panel,j,i);
                    blockPosition.add(j+1+ grids[i].length*i);
                }
                if(grids[i][j] == 4){
                    posList.add(new int[]{j,i});
                }
            }
        }
    }
    public void loadMap(Graphics2D g2d) {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++){
                int value = grids[i][j];
                grid.paint(g2d, j*30, i*30, value);
            }
        }
    }
}
