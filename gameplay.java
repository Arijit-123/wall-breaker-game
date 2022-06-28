import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.Graphics2D;
//import java.util.Timer;

public class gameplay extends JPanel implements KeyListener, ActionListener {
    
    private boolean play= false;
    private int score=0;
    private int totalbricks=21;
    private Timer time;
    private int delay=8;
    private int playerX=310;
    private int ballposX=120;
    private int ballposY=350;
    private int ballxdir=2;
    private int ballydir=-2;
    private MapGenerator map;

  public gameplay()
  {
    map=new MapGenerator(3,7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    time=new Timer(delay,this);
     time.start();
  }
  public void paint(Graphics g)
  {
    g.setColor(Color.black);
    g.fillRect(1,1,692,592);
    
    map.draw((Graphics2D)g);

    g.setColor(Color.YELLOW);
    g.fillRect(0,0,3,592);
    g.fillRect(0,0,692,3);
    g.fillRect(691,0,3,592);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString(""+score,590,30);

    g.setColor(Color.GREEN);
    g.fillRect(playerX,550,100,8);

    g.setColor(Color.yellow);
    g.fillOval(ballposX,ballposY,20,20);
   if(ballposY>570)
   {
    play=false;
    ballxdir=0;
    ballydir=0;
    g.setColor(Color.RED);
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString("Game Over, your Score is:"+score,200,30);
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString("Press enter to restart",230,350);
   }
   if(totalbricks==0)
   {
    
    g.setFont(new Font("serif",Font.BOLD,25));
    g.drawString("You have won",210,330);
   }

    g.dispose();
  }
    @Override
    public void actionPerformed(ActionEvent e) {
       time.start();
       if(play)
       {
        if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
        {
          ballydir=-ballydir;
        }
        A:for(int i=0;i<map.map.length;i++)
        {
          for(int j=0;j<map.map[0].length;j++)
          {
            if(map.map[i][j]>0)
            {
              int brickX=j*map.brickWidth+80;
              int brickY=i*map.brickHeight+50;
              int brickWidth=map.brickWidth;
              int brickHeight=map.brickHeight;
                Rectangle rect= new Rectangle(brickX,brickY,brickWidth,brickHeight);
                Rectangle ballRect=new Rectangle(ballposX, ballposY, 20,20);
                Rectangle brickRect=rect;
                if(ballRect.intersects(brickRect))
                {
                  map.setBrickValue(0,i, j);
                  totalbricks--;
                  score+=5;
                
                if(ballposX + 19 <= brickRect.x || ballposX + 1>=brickRect.x+brickRect.width)
                {
                  ballxdir=-ballxdir;
                }
                else{
                  ballydir=-ballydir;
                }
               break A;
            }
          }  
        }
      } 
        
        

        ballposX+=ballxdir;
        ballposY+=ballydir;
        if(ballposX<0)
        {
          ballxdir=-ballxdir;
        }
        if(ballposY<0)
        {
          ballydir=-ballydir;
        }
        if(ballposX>670)
        {
          ballxdir=-ballxdir;
        }
       }
     repaint();
      } 

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600)
            {
              playerX=600;
            }
            else{
              moveRight();
            }
       }
       if(e.getKeyCode()==KeyEvent.VK_LEFT){
        if(playerX<10)
        {
          playerX=600;
        }
        else{
          moveLeft();
        }
      }
      if(e.getKeyCode()==KeyEvent.VK_ENTER)
      {
        if(!play)
        {
          play=true;
          ballposX=120;
          ballposY=350;
          ballxdir=-1;
          ballydir=-2;
          playerX=310;
          score=0;
          totalbricks=31;
          map=new MapGenerator(3, 7);
          repaint();
        }
      }
        
    }
     public void moveRight(){
      play=true;
      playerX+=20;
     }
     public void moveLeft(){
      play=true;
      playerX-=20;
     }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    
  } 
