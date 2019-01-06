
package space.game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author amitj4056
 */
public class Controller {
     private  LinkedList<BulletEntity> bulletE = new LinkedList<>();
     private  LinkedList<EnemyEntity> enemyE = new LinkedList<>();
    BulletEntity be;
    EnemyEntity ee;
     SpaceGame game;
     Random r =  new Random();
     public Controller(SpaceGame game)
     {
         this.game =game;         
     }
     public void tick()
     {
      // System.out.println(entities.size());
         
         for(int  i = 0;i<bulletE.size();i++)
         {
             be = bulletE.get(i);
             if(be.getY()<0)
                 removeEntity(be);
             be.tick();    
         }
          for(int  i = 0;i<enemyE.size();i++)
         {
             ee = enemyE.get(i);
             ee.tick();    
         }
         
     }
     public void render(Graphics g)
     {
              for(int  i = 0;i<bulletE.size();i++)
         {
             be = bulletE.get(i);
             be.render(g);    
         }
          for(int  i = 0;i<enemyE.size();i++)
         {
             ee = enemyE.get(i);
             ee.render(g);    
         }
      
     }
     public void addEntity(BulletEntity b)
     {
         System.out.println("added");
         bulletE.add(b);
         
     }
     public void removeEntity(BulletEntity b)
     {
         bulletE.remove(b);
     }
      public void addEntity(EnemyEntity b)
     {
         System.out.println("added");
         enemyE.add(b);
         
     }
     public void removeEntity(EnemyEntity b)
     {
         enemyE.remove(b);
     }
      public void createEnemy(int count)
    {
        for(int i = 0;i<count;i++)
        {
            int x =r.nextInt(800);
            System.out.println(x);
            addEntity(new Enemy(x,0,game));
        }
    }

    public LinkedList<BulletEntity> getBulletE() {
        return bulletE;
    }

    public LinkedList<EnemyEntity> getEnemyE() {
        return enemyE;
    }

 
   
}
