package com.mycompany.apcsa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nitin
 */
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.lang.*;
import java.awt.event.ActionListener;
public class MovingShapePanel extends JPanel implements Runnable 
{
    private Shape sh;
    private Shape []shapes;
    private Shape runs;
    private int size = 8;
    private int adjSpeed = 4;
    private boolean [] flg = new boolean[size];
    private boolean [] flgy = new boolean [size];
    private int [] idekX = new int[size];
    private int [] idekY = new int[size];

    public MovingShapePanel() 
    {
        setBackground(Color.WHITE);
        setVisible(true);
        //refer sh to a new Shape
        
        int cn = 750;
        shapes = new Shape[size];
        int addable = cn / size;
        //int add = 0;
        //int addy = 400;
        
        for(int i = 0; i < size; i++)
        {
            idekX[i] = 100;
            idekY[i] = 100;
            flg[i] = true;
            flgy[i] = true;
        }
        for(int i = 0; i < size; i++)
        {
            if (i != 0)
            {
                idekX[i] += addable;
                idekY[i] += addable;
                addable+=190;
            }
            Color randomColor = new Color(1 + (int)(Math.random() * ((255 - 1) + 1)),1 + (int)(Math.random() * ((255 - 1) + 1)),1 + (int)(Math.random() * ((255 - 1) + 1)));
            shapes[i] = new Shape(400, 200, 40, 50, randomColor,40, 20);
            //add += 400;
            //addy -= 200;

        }
        new Thread(this).start();
    }

    public void update(Graphics window) 
    {
        paint(window);
    }

    public void paint(Graphics window) 
    {
        window.setColor(Color.WHITE);
        window.fillRect(0, 0, getWidth(), getHeight());
        window.setColor(Color.BLUE);
        window.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        window.setFont(new Font("TAHOMA", Font.BOLD, 18));
        window.drawString("CREATE YOUR OWN SHAPE!", 40, 40);
        for(int i = 0; i<shapes.length; i++)
        {
            shapes[i].draw(window);
        }
        for(int i = 0 ; i < shapes.length; i++)
        {
            if (!(shapes[i].getX() >= 10 && shapes[i].getX() <= 730)) 
            {
                shapes[i].setXSpeed(-shapes[i].getXSpeed());
            }
            //add code to handle the top and bottom walls
            // added below 
           if(!(shapes[i].getY() >= 20 && shapes[i].getY() <= 530))
            {
                shapes[i].setYSpeed(-shapes[i].getYSpeed());
            }
        }
        for(int i = 0; i< shapes.length; i++)
                {
                    shapes[i].setX(idekX[i]);                
                    if(shapes[i].getX() <= 730 && flg[i])
                    {
                        shapes[i].setXSpeed( idekX[i] );
                        idekX[i]+=adjSpeed;
                    }
                    if (shapes[i].getX() > 730) 
                        flg[i] = false;
                    if(flg[i] == false)
                    {
                        shapes[i].setXSpeed(idekX[i] );
                        idekX[i] -=adjSpeed;
                    }
                    if(shapes[i].getX() < 30)
                        flg[i] = true;
                    // Y 
                    shapes[i].setY(idekY[i]);
                    if(shapes[i].getY() < 500 && flgy[i])
                    { 
                       idekY[i]+=adjSpeed;
                       shapes[i].setYSpeed(idekY[i]);
                    }
                    if(shapes[i].getY() >= 500)
                    {
                       flgy[i] = false;
                       idekY[i]-=adjSpeed;
                       shapes[i].setYSpeed(idekY[i]);
                    }
                    if(shapes[i].getY() < 30)
                       flgy[i] = true;
                    if(flgy[i] == false)
                    {
                       idekY[i]-=adjSpeed;
                       shapes[i].setYSpeed(idekY[i]);
                    }
                    if(flgy[i])
                    {
                        idekY[i]+=adjSpeed;
                        shapes[i].setYSpeed(idekY[i]);
                    }
                }
    }
    
    public void run() 
    {
        try 
        {
            while (true) 
            {
                
                
                    Thread.currentThread().sleep(10);
                    repaint();
                        //System.out.println("for loop");
            }
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
}