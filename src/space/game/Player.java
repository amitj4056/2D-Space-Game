
package space.game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements BulletEntity{
   
    private BufferedImage player;
    private int velX,velY;
    public Player(int x,int y,SpaceGame game)
    {
      super(x,y,game);
        player =SpaceGame.loader.loadImage("/home/amitj4056/NetBeansProjects/Spacegame/res/img.png");
    }
    public void tick()
    {
       x += velX;
       y += velY;
       if(x<=0)
           x=0;
       if(x>=800)
           x = 800;
       if(y<=0)
           y =0 ;
       if(y>=590)
           y = 590;
       if(Physics.collision(this,game.enemyList,SpaceGame.c))
       {
           SpaceGame.HEALTH  -= 10;
           game.enemyKilled++;
             if(game.enemyCount==game.enemyKilled)
              {
                  game.enemyCount +=2;
                  game.enemyKilled=0;
                  if(game.enemyCount>=7)
                      game.enemyCount = 6;
                  SpaceGame.c.createEnemy(game.enemyCount);
              }
       }
    }
    public void render(Graphics g)
    {

        g.drawImage(player,(int)x,(int)y,null);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y= y;
    }
     public void setVelX(int x)
    {
        this.velX = x;
    }
    public void setVelY(int y)
    {
        this.velY= y;
    }
    
    
}
