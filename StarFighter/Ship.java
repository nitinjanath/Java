package FinalProject;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Asmit and Agrim

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()
	{
		this(10,10,10,10,10);
	}

	public Ship(int x, int y)
	{
	   this(x,y,10,10,10);
	}

	public Ship(int x, int y, int s)
	{
	   this(x,y,10,10,s);
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			File file = new File("ship.jpg");
                        image = ImageIO.read(file);
		}
		catch(Exception e)
		{
			                 System.out.println(e);
		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void move(String direction)
	{
		if (direction == "LEFT")
                {
                    setX(getX()-speed);
                }
                if (direction == "RIGHT")
                {
                    setX(getX()+speed);
                }
                if (direction == "UP")
                {
                    setY(getY()+speed);
                }
                if (direction == "DOWN")
                {
                    setY(getY()-speed);
                }
	}

	public void draw( Graphics window )
	{
   	window.drawImage(image,getX(),getY(),getWidth(),getHeight(), null);
	}

	public String toString()
	{
		return super.toString() + " " +getSpeed();
	}
}
