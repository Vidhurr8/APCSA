package Unit1;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

class Robot extends Canvas
{
   public Robot()    //constructor method - sets up the class
   {
      setSize(800,600);
      setBackground(Color.WHITE);   	
      setVisible(true);
   }

   public void paint( Graphics window )
   {
      window.setColor(Color.BLUE);

      window.drawString("Robot LAB ", 35, 35 );

      //call head method
      head(window);
      //call other methods
      upperBody(window);
      lowerBody(window);
   }

   public void head( Graphics window )
   {
      window.setColor(Color.YELLOW);

      window.fillOval(300, 100, 200, 100);

		//add more code here
      //draw left eye
      window.setColor(Color.BLACK);
      window.fillOval( 350, 125, 20, 20 );
      
      //draw right eye
      window.fillOval( 425, 125, 20, 20 );
      
      //draw mouth
      window.drawArc(350, 135, 95, 50, 0, -180);
				
   }

   public void upperBody( Graphics window )
   {
		//add more code here
	   window.setColor(Color.BLUE);
	   window.fillRect(325, 225, 150, 100);
	   window.setColor(Color.BLACK);
	   window.drawLine(325, 225, 275, 175);
	   window.drawLine(475, 225, 525, 175);
   }

   public void lowerBody( Graphics window )
   {

		//add more code here
	   window.setColor(Color.GRAY);
	   window.fillRect(325, 350, 150, 100);
	   window.setColor(Color.BLACK);
	   window.drawLine(325, 450, 250, 700);
	   window.drawLine(475, 450, 550, 700);

   }
}
