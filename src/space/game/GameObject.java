/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.game;

import java.awt.Rectangle;

public class GameObject {
    int x,y;
    SpaceGame game;
    public GameObject(int x,int y,SpaceGame game)
    {
        this.x = x;
        this.y = y;
        this.game =game;
    }
    public Rectangle getBounds()
    {
        return   new Rectangle(x,y,50,50);
    }
}
