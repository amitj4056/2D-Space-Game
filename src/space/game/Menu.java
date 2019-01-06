/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author amitj4056
 */
public class Menu {
    public Rectangle playButton  = new Rectangle(SpaceGame.WIDTH/2+120,150,100,50);
        public Rectangle helpButton  = new Rectangle(SpaceGame.WIDTH/2+120,250,100,50);
            public Rectangle quitButton  = new Rectangle(SpaceGame.WIDTH/2+120,350,100,50);


    public void render(Graphics g)
    {
        Graphics2D  g2d= (Graphics2D)g;
        Font font = new Font("arial",Font.BOLD,50);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("2D SPACE GAME", SpaceGame.WIDTH/2,100);
        
         Font font1 = new Font("arial",Font.BOLD,30);
         g.setFont(font1);
        g.drawString("Play",playButton.x+5,playButton.y+35);
        g.drawString("Help",playButton.x+5,playButton.y+135);
        g.drawString("Exit",playButton.x+5,playButton.y+235);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
    
}
