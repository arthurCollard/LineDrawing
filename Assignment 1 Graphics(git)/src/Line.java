import java.util.*;
import java.io.*;



import java.awt.Canvas;
import java.awt.Graphics;			//https://books.trinket.io/thinkjava/appendix-b.html
import javax.swing.JFrame;			//AWT package handles window creation to draw in.
import java.awt.image.BufferedImage;

public class Line{
	public static void main(String[] args) {
		/* Prompts the user for number of lines and which algorithm to use */
		System.out.println("Please enter how many lines to draw.");
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();
		System.out.println("Enter which algorith to use:0 for basic line drawing or 1 for Bresenham's" );
		int alg = in.nextInt();
		
		window w = new window(); // creates a new window for lines
		
		Random rand = new Random();
		
		for(int i=0; i < lines; i++) {
			int x0 = rand.nextInt(1000); /* generate random ints inside window size */
			int x1 = rand.nextInt(1000);
			int y0 = rand.nextInt(1000);
			int y1 = rand.nextInt(1000);
			
			/* Case handling */
			if ((y1-y0) == 0) {
				horizontal(x0,x1,y0,y1);
			} else if ((x1-x0) == 0) {
				vertical(x0,x1,y0,y1);
			} else if ((x1-x0) == (y1-y0)) {
				fourtyfive(x0,x1,y0,y1);
			} else if (((x1-x0) > (y1-y0)) && (alg == 1)) {
				Bresenham1(x0,x1,y0,y1);
			} else if (((y1-y0) > (x1-x0)) && (alg == 1)) {
				Bresenham2(x0,x1,y0,y1);
			} else if (((x1-x0) > (y1-y0)) && (alg == 0)) {
				basicLine1(x0,x1,y0,y1);
			} else if (((y1-y0) > (x1-x0)) && (alg == 0)) {
				basicLine2(x0,x1,y0,y1);
			}
		}//for		
	}//main
	
	//fillRect(x,y,1,1) equivalent to putPixel()
	
	public static void vertical(int x0, int x1, int y0, int y1) {
		
		
	}
	
	public static void horizontal(int x0, int x1, int y0, int y1) {
		
		
	}
	
	public static void fourtyfive(int x0, int x1, int y0, int y1) {
		
		
	}
	
	public static void basicLine1(int x0, int x1, int y0, int y1) {
		
		
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
