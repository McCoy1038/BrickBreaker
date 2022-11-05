import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    
    private int score = 0;
    private int totalBricks = 48;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private boolean play = false;
    private boolean pause = false;
    private MapGenerator map; 
    private Timer timer;
    private int delay = 8;

    public GamePlay(){
        map = new MapGenerator(4,12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){

        // Background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        
        // Drawing map
        map.draw((Graphics2D) g);

        //Setting Borders
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //Scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        //Paddle
        g.setColor(Color.cyan);
        g.fillRect(playerX, 550, 100, 8);
    
        //The Ball
        g.setColor(Color.green);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!", 260, 300);

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230, 350);
        }

        if(ballPosY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230, 350);
        }

        /*if(pause == true){

            play = false;
            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Paused", 190, 300);

        }
        else{
            pause = false;
            play = true;
        }
        */

        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
           
            play = false;
            pause = true;


        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            pause = false;
            if(playerX >= 600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            pause = false;
            if(playerX < 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play && !pause){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 48;
                map = new MapGenerator(4, 12);

                repaint();
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    public void moveRight(){
        play = true;
        playerX +=20;
    }

    public void moveLeft(){
        play = true;
        playerX -= 20;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play){
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))){
                ballYdir = -ballYdir;
                ballXdir = -2;
            }
            else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))){
                ballYdir = -ballYdir;
                ballXdir = ballXdir++;
            }
            else if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))){
                ballYdir = -ballYdir;
            }

            A: for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){

                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            score += 5;
                            totalBricks--;

                            if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;

            if(ballPosX < 0){
                ballXdir = -ballXdir;
            }
            if(ballPosY < 0){
                ballYdir = -ballYdir;
            }
            if(ballPosX > 670){
                ballXdir = -ballXdir;
            }

            repaint();
        }
    }
}
