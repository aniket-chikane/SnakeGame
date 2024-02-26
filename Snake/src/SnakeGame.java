import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SnakeGame extends JPanel implements ActionListener,KeyListener{
    private class Tile{
        
        int x,y;
        Tile(int x,int y ){
            this.x = x;
            this.y =y;
        }
    }
  
    int  framewidth,frameheight;
    int tileSize = 20;
    //snake
    Tile SnakeHead;
    ArrayList<Tile> snakeBody;
    //food
    Tile food;
    Random random;
    //game logic
    Timer gameLoop;
    int valocityX,valocityY;
    boolean gameOver = false;
    boolean place = true; 

    public void snkgame(int framewidth,int frameheight){
        this.frameheight  = frameheight;
        this.framewidth = frameheight;
        setPreferredSize(new Dimension(this.framewidth,this.frameheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        SnakeHead = new Tile(5, 5);

        snakeBody = new ArrayList<Tile>();


        food = new Tile(0,0);
        random = new Random();
        placefood();
        valocityX = 0;
        valocityY=0;
        
        gameLoop = new Timer(150,this);
        gameLoop.start();
    }
    
    public void draw(Graphics g){
        //food
        g.setColor(Color.GREEN);
        g.fill3DRect(food.x*tileSize,food.y*tileSize, tileSize,tileSize,true);
        
        
        //snake head
        g.setColor(Color.BLUE);
        g.fill3DRect(SnakeHead.x*tileSize,SnakeHead.y*tileSize,tileSize,tileSize,true);
        g.setFont(new Font("Arial",Font.PLAIN,16));

        //snake body
        for(int i = 0;i<snakeBody.size();i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize,true);
           

        }
    }
    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
         //score
         if(gameOver){
            g.setFont(new Font("Arial",Font.PLAIN,50));
            g.drawString("Game over :"+ String.valueOf(snakeBody.size()),tileSize*7,tileSize*15);
   
        }  
        else {
            g.drawString("Score"+String.valueOf(snakeBody.size()),tileSize-16,tileSize);
        }
    }
 

    public void placefood(){
        
        food.x = random.nextInt(framewidth/tileSize);
        food.y = random.nextInt(frameheight/tileSize);
    }
    public void move(){
        //eat food
        if(collision(SnakeHead, food)){
            snakeBody.add(new Tile(food.x,food.y));
           placefood(); 
        }

        //game ovr
        for(int i = 0;i<snakeBody.size()-1;i++){
            Tile snakepart = snakeBody.get(i);
            // collide with head
            if(collision(SnakeHead,snakepart)){
                gameOver = true;
            }
        } 
        if(SnakeHead.x*tileSize<0 || SnakeHead.x*tileSize>framewidth ||
        SnakeHead.y*tileSize<0 || SnakeHead.y*tileSize>frameheight){
            gameOver =true;
        }
        //snake body
        for(int i = snakeBody.size()-1;i>=0;i--){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = SnakeHead.x;
                snakePart.y = SnakeHead.y;
            }
            else {
                Tile PrevSnakePart = snakeBody.get(i-1);
                snakePart.x = PrevSnakePart.x;
                snakePart.y = PrevSnakePart.y;

            }
        }


        //snake head
        SnakeHead.x +=valocityX;
        SnakeHead.y +=valocityY;

      

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
       if(gameOver){
        gameLoop.stop();

        
        
       }  
       repaint();     

    }
    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()== KeyEvent.VK_UP && valocityY!=1){
        valocityX = 0;
        valocityY=-1;
       }
       else if(e.getKeyCode() == KeyEvent.VK_W && valocityY !=1){
        valocityX = 0;
        valocityY = -1;
       }
       else if(e.getKeyCode() == KeyEvent.VK_DOWN && valocityY!=-1){
        valocityX = 0;
        valocityY = 1;
       }
       else if(e.getKeyCode() == KeyEvent.VK_S && valocityY!=-1){
        valocityX = 0;
        valocityY = 1;
       }
       else if(e.getKeyCode() == KeyEvent.VK_LEFT && valocityX !=1){
        valocityX = -1;
        valocityY = 0;
       }
       else if(e.getKeyCode() == KeyEvent.VK_A && valocityX !=1){
        valocityX = -1;
        valocityY = 0;
       }
       
       else if(e.getKeyCode() == KeyEvent.VK_RIGHT && valocityX !=-1){
        valocityX = 1;
        valocityY = 0;
       }
       else if(e.getKeyCode() == KeyEvent.VK_D && valocityX !=-1){
        valocityX = 1;
        valocityY = 0;
       }
       
    }



    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    
    
    
}
