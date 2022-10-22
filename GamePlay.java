import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    
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
    }

    @Override
    public void keyPressed(KeyEvent e){

    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void actionPerformed(ActionEvent e){

    }
}
