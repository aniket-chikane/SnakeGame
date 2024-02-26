import javax.swing.*;
public class App {
    public boolean gamestr= true;
    
       JFrame frame  = new JFrame();
       
       
    public static void main(String[] args) throws Exception {
        App a = new App();
        int frameheight=600,framewidth=600;
        a.frame.setVisible(true);
        a.frame.setSize(frameheight,framewidth);
       a. frame.setLocationRelativeTo(null);
       a. frame.setResizable(false);
       SnakeGame s = new SnakeGame();
       a.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       s.snkgame(framewidth, frameheight);
      a.frame.add(s);
       a.frame.pack();
       s.requestFocus();

        
    }
    
}

