package com.mycompany.apcsa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nitin
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class Person
{
    private ArrayList<Image> list;
    private int x, y, current;
    public Person(int x, int y) throws IOException
    {
        this.x=x;
        this.y=y;
        list = new ArrayList<Image>();
        //point the array list at a new ArrayList
        ArrayList<Image>nList = list;
        Image front = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\dude.gif"));
        list.add(front);
        Image up = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\dudeU.gif"));
        list.add(up);
        Image right = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\dudeR.gif"));
        list.add(right);
        Image left = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\dudeL.gif"));
        list.add(left);
        //add all images to the list
    }
    // will return true if person collides with another object
    public boolean collide(int x, int y)
    {
        Rectangle a = new Rectangle(this.x, this.y, 50, 50);
        Rectangle b = new Rectangle(x, y, 50, 50);
        return a.intersects(b);
    }
    public void drawPerson(Graphics2D g)
    {
        g.drawImage(list.get(current), x , y , null);
        //draw the current image on the screen
    }
    public Image getCurrent()
    {
        //return the current image
        return list.get(current);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void moveRight()
    {
        x += 10;
        current = 2;
        //finish
    }
    public void moveLeft()
    {
        //finish
        x -= 10;
        current = 3; 
    }
    public void moveUp()
    {
        //finish
        y -= 10; 
        current = 1;
    }
    public void moveDown()
    {
        //finish
        y += 10;
        current = 0;
    }
}

