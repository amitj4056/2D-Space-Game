
package space.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject implements EnemyEntity{
   
    SpaceGame game;
    BufferedImage enemyImage;
    Random r = new Random();
    private int speed = r.nextInt(3)+1;
    public Enemy(int x,int y , SpaceGame game)
    {
       super(x,y,game);
          enemyImage = SpaceGame.loader.loadImage("/home/amitj4056/NetBeansProjects/Spacegame/res/enemy.png");
          
    }
    public void render(Graphics g)
    {
        g.drawImage(enemyImage, x, y, null);
    }
    public void tick()
    {
       // System.out.println("tck for enemy");
        y += speed;
        if(y>=580)
        {
            x = r.nextInt(840);
            y=0;
        }
    }
     public int getY()
   {
       return y;
   }
     public void setY(int y)
     {
         this.y = y;
     }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Rectangle getBounds()
    {
        return   new Rectangle(x,y,50,50);
    }
}
