/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author amitj4056
 */
public interface EnemyEntity {
       public void tick();
    public void render(Graphics g);
    public int getY();
    public int getX();
    public Rectangle getBounds();
}
