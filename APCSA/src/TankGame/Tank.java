package TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Tank extends MovingThing
{
	private int speed;
	private Image imageUp;
	private Image imageDown;
	private Image imageLeft;
	private Image imageRight;
	private String direction;

	public Tank()
	{
		this(0,0,0);
	}

	public Tank(int x, int y)
	{
		this(x,y,0);
	}

	public Tank(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		try
		{
			//imageUp = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\VidhurWorkspace\\tankUp.png"));
			imageUp = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\APCSAGit\\APCSA\\src\\TankGame\\tankUp.png"));
			//imageDown = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\VidhurWorkspace\\tankDown.png"));
			imageDown = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\APCSAGit\\APCSA\\src\\TankGame\\tankDown.png"));
			//imageLeft = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\VidhurWorkspace\\tankLeft.png"));
			imageLeft = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\APCSAGit\\APCSA\\src\\TankGame\\tankLeft.png"));
			//imageRight = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\VidhurWorkspace\\tankRight.png"));
			imageRight = ImageIO.read(new File("\\Users\\raveendranv4629\\Desktop\\APCSAGit\\APCSA\\src\\TankGame\\tankRight.png"));
		}
		catch(Exception e)
		{
			//feel free to do something here
			System.out.println("Tank doesn't work!");
		}
	}


	public void setSpeed(int s)
	{
	   //add more code
		speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		if (direction == "UP")
		{
			window.drawImage(imageUp,getX(),getY(),80,80,null);
		}
		
		else if (direction == "DOWN")
		{
			window.drawImage(imageDown,getX(),getY(),80,80,null);
		}
		
		else if (direction == "LEFT")
		{
			window.drawImage(imageLeft,getX(),getY(),80,80,null);
		}
		
		else if (direction == "RIGHT")
		{
			window.drawImage(imageRight,getX(),getY(),80,80,null);
		}
	}
	
	public void setDirection(String s)
	{
		direction = s;
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public String toString()
	{
		return super.toString() + getSpeed();
	}
}