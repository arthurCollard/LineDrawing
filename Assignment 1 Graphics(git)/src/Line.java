/* written by A.J. Collard */

import java.util.*;
import java.io.*;
import java.applet.*;
import java.awt.Canvas;				//AWT package handles window creation to draw in.
import java.awt.Graphics;			//https://books.trinket.io/thinkjava/appendix-b.html
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*;					


public class Line extends Applet{
	
	public void paint(Graphics g) {
		
		/* Prompts the user for number of lines and which algorithm to use */
		System.out.println("Please enter how many lines to draw.");
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();
		System.out.println("Enter which algorith to use:0 for basic line drawing or 1 for Bresenham's" );
		int alg = in.nextInt();
		
		//instantiate needed objects
		Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		
		for(int i=0; i < lines; i++) {
			//int x0 = rand.nextInt(1000); /* generate random ints inside window size */
			//int x1 = rand.nextInt(1000);
			//int y0 = rand.nextInt(1000);
			//int y1 = rand.nextInt(1000);
			
			int x0= 801;
			int x1= 400;
			int y0= 800; 
			int y1= 400;
			
			int deltax = Math.abs(x1-x0);
			int deltay = Math.abs(y1-y0);
			
			/* Case handling */
			if ((deltay) == 0) {
				horizontal(x0,x1,y0,y1,g);
			} else if ((deltax) == 0) {
				vertical(x0,x1,y0,y1,g);
			} else if ((deltax) == (deltay)) {
				fourtyfive(x0,x1,y0,y1,g);
			} else if (((deltax) > (deltay)) && (alg == 1)) {
				Bresenham1(x0,x1,y0,y1);
			} else if (((deltay) > (deltax)) && (alg == 1)) {
				Bresenham2(x0,x1,y0,y1);
			} else if (((deltax) > (deltay)) && (alg == 0)) {
				basicLine1(x0,x1,y0,y1,g);
			} else if (((y1-y0) > (x1-x0)) && (alg == 0)) {
				basicLine2(x0,x1,y0,y1);
			}
		}//for	
	}
	
	
	//fillRect(x,y,1,1) equivalent to putPixel()
	
	public static void vertical(int x0, int x1, int y0, int y1, Graphics g) {
		int deltay = Math.abs(y1-y0);
		int y;
		if (y1 > y0) {
			y=y0;
			for(int i=0; i < deltay; i++) {
				g.fillRect(x0, y, 1, 1); 			//acts as putpixel
				y++;
			}//for
		} else {
			y = y0;
			for(int i=0; i < deltay; i++) {
				g.fillRect(x0, y, 1, 1);
			}
		}
	}//vertical
	
	public static void horizontal(int x0, int x1, int y0, int y1, Graphics g) {
		int deltax = Math.abs(x1-x0);
		int x;
		if (x1 > x0) {
			x = x0;
			for(int i=0; i < deltax; i++) {
				g.fillRect(x, y0, 1, 1);
				x++;
			}
		} else {
			x = x0;
			for(int i=0; i < deltax; i++) {
				g.fillRect(x, y0, 1, 1);
				x--;
			}
		}
		
	}
	
	public static void fourtyfive(int x0, int x1, int y0, int y1, Graphics g) {
		int deltax = Math.abs(x1-x0);
		int x, y;
		if ((x1 > x0) && (y1 > y0)) {
			x = x0;
			y = y0;
			for(int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x++;
				y++;
			}//for
		} else if ((x1 > x0) && (y0 > y1)) {
			System.out.println("here");
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x++;
				y--;
			}//for
		} else if ((x0 > x1) && (y0 > y1)) {
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x--;
				y--;
			}//for
		} else {
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x--;
				y++;
			}//for
		}//else
	}
	
	public static void basicLine1(int x0, int x1, int y0, int y1, Graphics g) {
		float deltax = Math.abs(x1-x0);
		float deltay = Math.abs(y1-y0);
		float slope = deltay / deltax;
		int x, y;
		float try_y;
		System.out.println("Deltax: " + deltax + ", Deltay: " + deltay + ", slope: "+ slope);
		if ((x1 > x0) && (y1 > y0)) {
			for(int i=0; i < deltax; i++) {
				x = x0 + i;
				try_y = slope*i + y0;
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		} else if ((x0 > x1) && (y1 > y0)) {
			System.out.println("here");
			for(int i=0; i < deltax; i++) {
				x = x0 - i;
				try_y = slope*i + y0;
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		} else if ((x1 > x0) && (y0 > y1)) {
			System.out.println("here");
			for(int i=0; i < deltax; i++) {
				x = x0 + i;
				try_y = slope*i - y0;
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		} else {
			for(int i=0; i < deltax; i++) {
				x = x0 - i;
				try_y = slope*i - y0;
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		}
	}
	
	public static void basicLine2(int x0, int x1, int y0, int y1) {
		
		
	}
	
	public static void Bresenham1(int x0, int x1, int y0, int y1) {  //Deltax > Deltay
		int e, deltaY, deltaX, inc1, inc2, x, y;
		deltaY = y1 - y0;
		deltaX = x1 - x0;
		e = (2*deltaY) + deltaX;
		inc1 = 2*deltaY;
		inc2 = 2*deltaX;
		x = x0;
		y = y0;
		boolean loopctrl = true;
		
		while (loopctrl) {
			
		}//while
		
	}//Bresenham1
	
	public static void Bresenham2(int x0, int x1, int y0, int y1) {
		
		
	}
}
