
package space.game;

import java.util.LinkedList;
import static javax.swing.UIManager.get;

public class Physics {
    public static boolean collision (BulletEntity bulletEntity, LinkedList<EnemyEntity>enemyEntity,Controller c)
    {
        for(int i = 0;i<enemyEntity.size();i++)
        {
            if(bulletEntity.getBounds().intersects(enemyEntity.get(i).getBounds()))
            {
                c.removeEntity(enemyEntity.get(i));
                return true;
            }
        }
        return false;
    }
}
