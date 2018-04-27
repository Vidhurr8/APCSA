package Starfighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


public class PowerUp extends MovingThing
{
	private int speed;
	private Image image;

	public PowerUp()
	{
		this(0,0,0);
	}

	public PowerUp(int x, int y)
	{
		this(x,y,0);
	}

	public PowerUp(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		try
		{
			image = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\VidhurWorkspace\\pu.jpg"));
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println("Power Up doesn't work!");
		}
	}

	public void setSpeed(int s)
	{
	   //add code
		speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),80,80,null);
	}

	public String toString()
	{
		return "";
	}
}