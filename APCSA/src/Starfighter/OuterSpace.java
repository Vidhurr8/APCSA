package Starfighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private ShieldShip ship2;
	private Alien alienOne;
	private Alien alienTwo;
	private PowerUp pu;

	// uncomment once you are ready for this part
	
	private ArrayList<Alien> aliens;
	private ArrayList<Ammo> shots;
	private ArrayList<Ammo> alienShots;
	
	private int ALIEN_ATTACK; // 2 seconds
	private Timer addAlienTimer;
	private int lives;
	private int kills;
	private int speed;

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other stuff
		lives = 500;
		kills = 0;
		ship = new Ship(400, 400, 4);
		ship2 = new ShieldShip(0, 1000, 0);
		pu = new PowerUp(200, 200, 0);
		aliens = new ArrayList<Alien>();
		aliens.add(new Alien(250, 50, 2));
		aliens.add(new Alien(450, 50, 2));
		aliens.add(new Alien(650, 50, 2));
		aliens.add(new Alien(950, 50, 2));
		shots = new ArrayList<Ammo>();
		alienShots = new ArrayList<Ammo>();
		speed = 2;
		ALIEN_ATTACK = 2000;
		setBackground(Color.WHITE);
		setVisible(true);

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
		
		startAttack();
		moreAliens();
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);
		graphToBack.setColor(Color.WHITE);
		graphToBack.drawString("Lives: " + lives / 100, 25, 75);
		graphToBack.drawString("Kills: " + kills, 25, 100);

		if(keys[0] == true)
		{
			ship.move("LEFT");
			ship2.move("LEFT");
		}

		//add code to move stuff
		if (keys[1] == true) 
		{
			if (ship.getX() < 730 || ship2.getX() < 730) 
			{
				ship.move("RIGHT");
				ship2.move("RIGHT");
			}
		}
		if (keys[2] == true) 
		{
			if (ship.getY() > 10 || ship2.getY() > 10) 
			{
				ship.move("DOWN");
				ship2.move("DOWN");
			}
		}
		if (keys[3] == true) 
		{
			if (ship.getY() < 500 || ship2.getY() < 500) 
			{
				ship.move("UP");
				ship2.move("UP");
			}
		}
		if (keys[4] == true) 
		{
			Ammo shot = new Ammo(ship.getX() + 28, ship.getY(), 5);
			Ammo shot2 = new Ammo(ship2.getX() + 28, ship2.getY(), 5);
			shots.add(shot);
			shots.add(shot2);
			keys[4] = false;
		}
		
		if (lives < 0) 
		{
			for (int j = 0; j < aliens.size(); j++) 
			{
				Alien a = aliens.get(j);
				a.setSpeed(0);
				a.setPos(1000, 0);
			}
			
			for (int j = 0; j < alienShots.size(); j++) 
			{
				Ammo s = alienShots.get(j);
				s.setSpeed(0);
				s.setPos(1000, 0);
			}
			
			ship.setSpeed(0);
			graphToBack.setColor(Color.WHITE);
			graphToBack.drawString("Game Over", 200, 300);
			ship.setPos(1000, 0);
			pu.setPos(0, 1000);
		}

		//add in collision detection
		for (int j = 0; j < aliens.size(); j++) 
		{
			Alien a = aliens.get(j);
			a.draw(graphToBack);
			if (a.getX() <= 1000) 
			{
				if (a.getX() > 900)
					a.setX(-20);
				if (a.getX() + 100 >= ship.getX() && a.getX() <= ship.getX() + 70 && a.getY() + 44 >= ship.getY()
						&& a.getY() <= ship.getY() + 43) 
				{
					lives--;
				}
			}
			a.move("RIGHT");
			for (int i = 0; i < shots.size(); i++) 
			{
				Ammo s = shots.get(i);
				if (a.getX() >= s.getX() && a.getX() <= s.getX() + 100 && a.getY() >= s.getY()
						&& a.getY() <= s.getY() + 80) 
				{
					aliens.remove(a);
					shots.remove(s);
					kills++;
				}
			}
		}
		
		if (pu.getX() + 100 >= ship.getX() && pu.getX() <= ship.getX() + 70 && pu.getY() + 44 >= ship.getY()
				&& pu.getY() <= ship.getY() + 43) 
		{
			pu.setPos(0, 1000);
			ship2.setPos(ship.getX(), ship.getY());
			ship2.setSpeed(ship.getSpeed());
			ship.setPos(1000, 0);
			ship.setSpeed(0);
		}
		
		for (int i = 0; i < alienShots.size(); i++) 
		{
			Ammo s = alienShots.get(i);
			if (ship.getX() >= s.getX() && ship.getX() <= s.getX() + 100 && ship.getY() >= s.getY()
					&& ship.getY() <= s.getY() + 80) 
			{
				alienShots.remove(s);
				lives--;
			}
		}
		
		for (int i = 0; i < shots.size(); i++) 
		{
			Ammo s = shots.get(i);
			s.draw(graphToBack);
			s.move("DOWN");
		}
		for (int i = 0; i < shots.size(); i++) 
		{
			Ammo s = shots.get(i);
			if (s.getY() <= 1) 
			{
				shots.remove(s);
			}
		}
		
		for (int i = 0; i < alienShots.size(); i++) 
		{
			Ammo s = alienShots.get(i);
			s.draw(graphToBack);
			s.move("DOWN");
		}
		for (int i = 0; i < alienShots.size(); i++) 
		{
			Ammo s = alienShots.get(i);
			if (s.getY() >= 500) 
			{
				shots.remove(s);
			}
		}
		
		ship.draw(graphToBack);
		ship2.draw(graphToBack);
		pu.draw(graphToBack);
		twoDGraph.drawImage(back, null, 0, 0);
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}
	
	public void moreAliens() 
	{

		int x = (int) (Math.random() * getWidth());
		int y = (int) (Math.random() * (getHeight()));

		if (addAlienTimer.isRunning()) 
		{
			aliens.add(new Alien(x, y, speed));
			for (int j = 0; j < aliens.size(); j++) 
			{
				Alien a = aliens.get(j);
				Ammo shot = new Ammo(a.getX() + 40, a.getY() + 80, -3);
				alienShots.add(shot);
			}
			
		}
	}
	
	public void startAttack() 
	{
		if (addAlienTimer == null) 
		{
			addAlienTimer = new Timer(ALIEN_ATTACK, new OuterSpace.TimerHandler());
			addAlienTimer.start();
		} 
		else 
		{
			if (!addAlienTimer.isRunning()) 
			{
				addAlienTimer.restart();
			}
		}
	}
	
	private class TimerHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent actionEvent) 
		{
			moreAliens();
		}
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
           repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}
