
package space.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements BulletEntity {
    
    SpaceGame game;
    BufferedImage bulletImage;
    public Bullet(int x,int y,SpaceGame game)
    {
        super(x,y,game);
        this.game = game;
        bulletImage = SpaceGame.loader.loadImage("/home/amitj4056/NetBeansProjects/Spacegame/res/bullet.png");
    }
    @Override
    public void tick()
    {
      y -=10;
      if(Physics.collision(this, game.enemyList,SpaceGame.c)){
          {
              SpaceGame.c.removeEntity(this);
              game.enemyKilled++;
              if(game.enemyCount==game.enemyKilled)
              {
                  game.enemyCount +=2;
                  game.enemyKilled=0;
                  if(game.enemyCount>=7)
                      game.enemyCount = 6;
                  SpaceGame.c.createEnemy(game.enemyCount);
              }
              System.out.println("COLLISION");
          }
      }
       
    }
    @Override
    public void render(Graphics g)
    {
       //  System.out.println("jbsdaskjdajs");
        g.drawImage(bulletImage, x, y, null);
    }
    @Override
   public int getY()
   {
       return y;
   }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Rectangle getBounds()
    {
        return   new Rectangle(x,y,20,30);
    }
}
