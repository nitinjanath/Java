/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apcsa;

/**
 *
 * @author nitin
 */
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
public class Enemies
{
    private ArrayList<Enemy> enemies;
    private Person guy;
    private Enemy e;
    public Enemies(Person p)
    {
        enemies = new ArrayList<Enemy>();
        guy = p;
    }
    // DRAW AND COLLISION METHOD
    public void drawAndCollision(Graphics2D g) throws IOException
    {
        // ADD ENEMIES
        // REMOVE ENEMIES THAT GO OUT OF BOUND
        // GO THROUGH THE LIST OF ENEMIES AND CALL THEIR draw() and move()method and check if their collide with guy if so then call the GAMEOVER() METHOD
        // IF THE PLAYER REACHES THE GRASS ON THE BOTTOM SIDE CALL THE VICTORY() method        
        for (int i = 0; i < enemies.size(); i++) 
        {
            Enemy enemy = enemies.get(i);
            enemy.move();
            enemy.draw(g); 
            if (guy.collide(enemy.getX(), enemy.getY())) 
            {
                GAMEOVER(g);
            }
            if (enemy.getY() > Game.HEIGHT || enemy.getY() < 0) 
            {
                enemies.remove(i); 
                i--; 
            }
            if (enemy.getX() > Game.WIDTH || enemy.getX() < 0) {
                enemies.remove(i); 
                i--; 
            }
            if (guy.getY() > Game.HEIGHT - 50) 
            {
                VICTORY(g);
            }
        }
    }
    public void GAMEOVER(Graphics2D g)
    {
        Game.RUNNING = false;
        Font f = new Font("Dialog", Font.PLAIN, 24);
        g.setFont(f);
        g.drawString("GAME OVER!", 400, 300);
    }
    public void VICTORY(Graphics2D g)
    {
        Font f = new Font("Dialog", Font.PLAIN, 24);
        g.setFont(f);
        g.drawString("VICTORY!", 400, 300);
        Game.RUNNING = false;
    }
    // ADDS AN ENEMY TO THE ENEMY LIST
    public void addEnemy() throws IOException
    {
        boolean start = true;
        boolean finish = false;
        int totalSize = 20;
        
        if(enemies.size() < totalSize)
        {
            int mlt = 0;
            for(int i = 0; i < totalSize - enemies.size(); i++)
            {
                int spd = (int) (Math.random() * (5) + 1);
                if(spd != 0)
                {
                    if(start)
                    {
                        Enemy enemy1 = new Enemy(800, (int) (Math.random() * (350)) + 125,  -spd);
                        enemies.add(enemy1);
                        start = false;
                        finish = true;
                    }
                    else if(finish)
                    {
                        Enemy enemy1 = new Enemy(799,(int) (Math.random() * (350)) + 125,  spd);
                        enemies.add(enemy1);
                        start = true;
                        finish = false;
                    }
                    mlt += 50;
                }
                else
                    spd = (int) (Math.random() * ((5) + 1));
            }
        }
        /*enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 ,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 50,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 75,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 100,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 125,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 150,  3));
        enemies.add(new Enemy(1 + (int)(Math.random() * ((800 - 0) + 1)), 475 - 175,  3));*/
        
    }
    // RETURN HOW MANY ENEMIES THERE ARE
    public int numEnemies()
    {
        return enemies.size();
    }
}