import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
    
     JFrame frame = new JFrame();
     GamePlay gamePlay = new GamePlay();

     frame.setBounds(500,100,700,600);
     frame.setTitle("Brick Breaker");
     frame.setResizable(false);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(gamePlay);
     frame.setVisible(true);

     
    }
    
}
