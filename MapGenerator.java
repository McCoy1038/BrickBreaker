import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator() {
        
    }
    
    public MapGenerator(int row, int col) {
        map = new int[row][col];
        
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
    }

    public void draw(Graphics2D graphic){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0){
                    graphic.setColor(Color.white);
                    graphic.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    
}
