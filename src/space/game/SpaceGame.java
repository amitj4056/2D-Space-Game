
package space.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SpaceGame extends Canvas implements Runnable{
    public static final  int WIDTH = 420;
    public static final int HEIGHT = WIDTH/12*9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Space Game";
    private boolean running  = false;
    private Thread thread;
    private BufferedImage player=null,image = null; 
    public static BufferedImageLoader  loader = new BufferedImageLoader();
    private Player  p;
    protected static Controller c;
     public int enemyCount=1;
    public int enemyKilled = 0;
    public static int HEALTH = 100;
    public static int SCORE = 0;
    public LinkedList<BulletEntity>bulletList;
    public LinkedList<EnemyEntity>enemyList;
    private Menu menu =new Menu();
    public static enum STATE{
        GAME,
        MENU,
        END
    };
    public static STATE state = STATE.MENU;

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }
   
    
   
   
    private  synchronized void start()
    {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
        
    }
    private synchronized void stop() 
    {
        if(!running)
            return;
        running  = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SpaceGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
        
    }
    void init()
    {
        requestFocus();
        image = this.loader.loadImage("/home/amitj4056/NetBeansProjects/Spacegame/res/space.png");
        System.out.println("Image loaded");
        
        addKeyListener(new KeyInput(this));
        try{
        addMouseListener(new MouseInput());
        }
        catch(Exception w)
        {
            System.out.println(w);
        }
        p = new Player(200,200,this);
        c= new Controller(this);
      c.createEnemy(enemyCount);
      
      enemyList = c.getEnemyE();
      bulletList = c.getBulletE();
        
        
    }
    @Override
    public void run() {
        init();
        long lastTime   = System.nanoTime();
        final double  amountOfTicks = 60;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        
            while(running)
            {
                long now =System.nanoTime();
                delta += (now -lastTime)/ns;
                lastTime = now;
                if(delta>=1)
                {
                    delta= delta-1.0;
                    tick();
                    updates++;
                }
                render();
                frames++;
                if(System.currentTimeMillis()-timer>1000)
                {
                    timer += 1000;
               //  System.out.println(updates + " ticks, FPS "+ frames);
                    updates = frames=0;
                }
            }
           stop();
        }
    private void tick()
    {
        if(state==STATE.GAME)
        {
        p.tick();
        c.tick();
        }
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g =  bs.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
         if(state==STATE.GAME)
         {
            p.render(g);
            c.render(g);
             Font font = new Font("arial",Font.BOLD,30);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("HEALTH: "+HEALTH+"", 10,25);
             g.drawString("SCORE: "+SCORE+"", 620,25);
             if(HEALTH<=0)
             {
                 state = STATE.END;
             }
         }
         else if(state==STATE.MENU)
         {
             menu.render(g);
         }
         else
         {
             String msg = "Score = "+SCORE; 
        
         String[] options = new String[] {"Play Again","Exit"};
    int response = JOptionPane.showOptionDialog(null, "Message", "Title",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    if(response==0)
    {
        HEALTH=100;
        SCORE=0;
        enemyCount=1;
        enemyKilled=0;
        state = STATE.GAME;
    }
    else
        System.exit(1);

         }
        
        g.dispose();
        bs.show();
        return;
        
        
    }
     public void keyPressed(KeyEvent e)
        {
            
          int k = e.getKeyCode();
          if(state==STATE.GAME)
              
          {        if(k== KeyEvent.VK_LEFT)
                    {
                        p.setVelX(-4);
                    } else if(k== KeyEvent.VK_RIGHT)
                    {
                        p.setVelX(4);
                    } else if(k== KeyEvent.VK_UP)
                    {
                        p.setVelY(-4);
                    }
                    else if(k== KeyEvent.VK_DOWN)
                    {
                        p.setVelY(4);
                    }
                    else if(k==KeyEvent.VK_SPACE)
                    {
                        c.addEntity(new Bullet(p.getX(),p.getY(),this));
                    }
        }
        }
        public void keyReleased(KeyEvent e)
        {
             int k = e.getKeyCode();
          if(k== KeyEvent.VK_LEFT)
          {
              p.setVelX(0);
          } else if(k== KeyEvent.VK_RIGHT)
          {
              p.setVelX(0);
          } else if(k== KeyEvent.VK_UP)
          {
              p.setVelY(0);
          }
          else if(k== KeyEvent.VK_DOWN)
          {
              p.setVelY(0);
          }
        }
     public static void main(String[] args) {
        SpaceGame game  =  new SpaceGame();
        game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        JFrame frame  = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
        
        
    }
    
}
