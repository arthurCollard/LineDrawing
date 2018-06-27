/* written by A.J. Collard 
 * 
 * Lines in the Applet window appear as a 45 degree clockwise 
 * rotation of the x-y plane. Points in the Applet are put based
 * on i, j location in the window.
 * 
 */

import java.util.*;
import java.io.*;
import java.applet.*;				//Applet package handles window creation to draw in.
import java.awt.Graphics;			//https://books.trinket.io/thinkjava/appendix-b.html
import java.awt.Graphics2D;		


public class Line extends Applet{				// idea for Applet - https://www.youtube.com/watch?v=xXnd5ClFLXk
	
	public void paint(Graphics g) {
		
		/* Prompts the user for number of lines and which algorithm to use */
		System.out.println("Please enter how many lines to draw.");
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();
		System.out.println("Enter which algorithm to use:0 for basic line drawing or 1 for Bresenham's" );
		int alg = in.nextInt();
		
		//instantiate needed objects
		Graphics2D g2d = (Graphics2D) g;
		Random rand = new Random();
		double runtime, milli;
		double timeBefore, timeAfter; 
		
		timeBefore = System.currentTimeMillis();
		for(int i=0; i < lines; i++) {
			int x0 = rand.nextInt(1000);  	//generate random ints inside window size 
			int x1 = rand.nextInt(1000);
			int y0 = rand.nextInt(1000);
			int y1 = rand.nextInt(1000);



			
			int deltax = Math.abs(x1-x0);		//Change in x that determines method to call
			int deltay = Math.abs(y1-y0);		//Change in x that determines method to call

			/* Methods traverse scan lines bases on which axis changes more */
			/* Case handling */
			if ((deltay) == 0) {
				horizontal(x0,x1,y0,y1,g);
			} else if ((deltax) == 0) {
				vertical(x0,x1,y0,y1,g);
			} else if ((deltax) == (deltay)) {
				fourtyfive(x0,x1,y0,y1,g);
			} else if (((deltax) > (deltay)) && (alg == 1)) {
				Bresenham1(x0,x1,y0,y1,g);
			} else if (((deltay) > (deltax)) && (alg == 1)) {
				Bresenham2(x0,x1,y0,y1,g);
			} else if (((deltax) > (deltay)) && (alg == 0)) {
				basicLine1(x0,x1,y0,y1,g);
			} else if (((deltay) > (deltax)) && (alg == 0)) {
				basicLine2(x0,x1,y0,y1,g);
			}//else if
		}//for	
		milli = 1000.0;
		timeAfter = System.currentTimeMillis();
		runtime = (timeAfter - timeBefore) / milli;			//runtime in seconds
		System.out.println("Runtime: " + runtime);
	}//paint
	
	
	//fillRect(x,y,1,1) equivalent to putPixel()
	
	public static void vertical(int x0, int x1, int y0, int y1, Graphics g) {		
		int deltay = Math.abs(y1-y0); 				// gets the difference y0 and y1
		int y;
		if (y1 > y0) {								//two cases for vertical lines
			y=y0;
			for(int i=0; i < deltay; i++) {			//loops over y
				g.fillRect(x0, y, 1, 1); 			//acts as putpixel
				y++;
			}//for
		} else {									// second case y0 > y1
			y = y1;
			for(int i=0; i < deltay; i++) {
				g.fillRect(x0, y, 1, 1);
				y++;
			}
		}
	}//vertical - working
	
	public static void horizontal(int x0, int x1, int y0, int y1, Graphics g) {
		int deltax = Math.abs(x1-x0);				//gets difference in x
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
	}//horizontal - working
	
	public static void fourtyfive(int x0, int x1, int y0, int y1, Graphics g) {
		int deltax = Math.abs(x1-x0);
		int x, y;
		if ((x1 > x0) && (y1 > y0)) {					//case1
			x = x0;
			y = y0;
			for(int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);					//putpixel for the Applet
				x++;									
				y++;
			}//for
		} else if ((x1 > x0) && (y0 > y1)) {			//case2
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x++;
				y--;
			}//for
		} else if ((x0 > x1) && (y0 > y1)) {			//case3
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x--;
				y--;
			}//for
		} else {										//case4
			x = x0;
			y = y0;
			for (int i=0; i < deltax; i++) {
				g.fillRect(x, y, 1, 1);
				x--;
				y++;
				//System.out.println("x: " + x + " y: " + y); //to test the values
			}//for
		}//else
	}//fourtyfive - working
	
	public static void basicLine1(int x0, int x1, int y0, int y1, Graphics g) {
		float absdeltax = Math.abs(x1-x0);				//use for traversal
		float deltax = (x1-x0);
		float deltay = (y1-y0);
		float slope = deltay / deltax;					// m in y=mx+b
		int x, y;
		float try_y;									
		if ((x1 > x0) && (y1 > y0)) {					//case1
			for(int i=0; i < absdeltax; i++) {
				x = x0 + i;								//change in x is 1 since we traverse x axis
				try_y = slope*i + y0;					//change in y according to slope
				y = Math.round(try_y);					//round to nearest pixel
				g.fillRect(x,y,1,1);					//putpixel for applet
			}
		} else if ((x0 > x1) && (y1 > y0)) {			//case2
			for(int i=0; i < absdeltax; i++) {			
				x = x0 - i;
				try_y = -slope*i + y0;					//slope negative since x0 > x1
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		} else if ((x1 > x0) && (y0 > y1)) {			//case3
			for(int i=0; i < absdeltax; i++) {
				x = x0 + i;
				try_y = slope*i + y0;
				y = Math.round(try_y);					
				g.fillRect(x,y,1,1);
			}
		} else if ((x0 > x1) && (y0 > y1)){				//case4
			for(int i=0; i < absdeltax; i++) {
				x = x0 - i;
				try_y = -slope*i + y0;					//slope negative since x0>x1
				y = Math.round(try_y);
				g.fillRect(x,y,1,1);
			}
		}//else if
	}//basicline1 - working
	
	public static void basicLine2(int x0, int x1, int y0, int y1, Graphics g) {
		float absdeltay = Math.abs(y1-y0);				//use for traversal
		float deltax = (x1-x0);
		float deltay = (y1-y0);
		float slope = deltax / deltay;					// m in y=mx+b
		int x, y;	
		float try_x;
		if ((x1 > x0) && (y1 > y0)) {					//case1
			for(int i=0; i < absdeltay; i++) {			//traverse y axis
				y = y0 + i;								//y increments by 1 since we move along y-axis
				try_x = slope*i + x0;					//change x according to slope
				x = Math.round(try_x);					//round to nearest pixel
				g.fillRect(x,y,1,1);					//putpixel for applet
			}
		} else if ((x0 > x1) && (y1 > y0)) {			//case2
			for(int i=0; i < absdeltay; i++) {
				y = y0 + i;
				try_x = slope*i + x0;	
				x = Math.round(try_x);
				g.fillRect(x,y,1,1);
			}
		} else if ((x1 > x0) && (y0 > y1)) {			//case3
			for(int i=0; i < absdeltay; i++) {
				y = y0 - i;
				try_x = -slope*i + x0;					//slope negative for y0 > y1
				x = Math.round(try_x);
				g.fillRect(x,y,1,1);
			}
		} else if ((x0 > x1) && (y0 > y1)){				//case4
			for(int i=0; i < absdeltay; i++) {
				y = y0 - i;
				try_x = -slope*i + x0;					//slope negative for y0 > y1
				x = Math.round(try_x);
				g.fillRect(x,y,1,1);
			}
		}//else if
	}
	
	public static void Bresenham1(int x0, int x1, int y0, int y1, Graphics g) {  //Deltax > Deltay
		int e, deltaY, deltaX, inc1, inc2, x, y;
		deltaY = y1 - y0;
		deltaX = x1 - x0;
		e = (2*deltaY) - deltaX;
		inc1 = 2*deltaY;
		inc2 = 2*(deltaY-deltaX);
		x = x0;
		y = y0;
		if ((x1 > x0) && (y1 > y0)) { 					//case1
			while (true) {
				g.fillRect(x, y, 1, 1);					//putpixel for the applet
				if (e < 0) {							//if error value is less than 0
					e = e + inc1;						//increment 1 
				} else {								//if you overshot
					y = y + 1;							//go up to next scan line
					e = e + inc2;						//adjust the error back down
				}
				x = x + 1;								// move along scan line
				if (x > x1) {							
					break;								//stop traversal
				} else {
					continue;							//keep traversing
				}
			}//while
		} else if((x1 > x0) && (y0 > y1)) {				//case2
			y=y0;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e - inc1;
				} else {
					y = y - 1;							//start high and decrement y
					e = e + inc2;
				}
				x = x + 1;
				if (x > x1) {
					break;
				} else {
					continue;
				}
			}//while
		} else if ((x0 > x1) && (y1 > y0)){				//case3
			x = x1;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e + inc1;
				} else {
					y = y + 1;
					e = e - inc2;
				}
				x = x + 1;
				if (x > x0) {
					break;
				} else {
					continue;
				}
			}//while
		} else {										//case4
			x = x1;
			y = y1;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e - inc1;
				} else {
					y = y - 1;							//increment the same way as case2
					e = e - inc2;
				}
				x = x + 1;
				if (x > x0) {
					break;
				} else {
					continue;
				}
			}//while
		}
	}//Bresenham1
	
	public static void Bresenham2(int x0, int x1, int y0, int y1, Graphics g) {
		int e, deltaY, deltaX, inc1, inc2, x, y;
		deltaY = y1 - y0;
		deltaX = x1 - x0;
		e = (2*deltaX) - deltaY;
		inc1 = 2*deltaY;
		inc2 = 2*(deltaY)-deltaX;
		x = x0;
		y = y0;
		if ((x1 > x0) && (y1 > y0)) { 					//case1
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e + inc1;
				} else {
					x = x + 1;
					e = e - inc2;
				}
				y = y + 1;
				if (y > y1) {
					break;
				} else {
					continue;
				}
			}//while
		} else if((x1 > x0) && (y0 > y1)) {				//case2
			y=y1;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e - inc1;
				} else {
					x = x + 1;
					e = e + inc2;
				}
				y = y + 1;
				if (y > y0) {
					break;
				} else {
					continue;
				}
			}//while
		} else if ((x0 > x1) && (y1 > y0)){				//case3
			x = x0;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e + inc1;
				} else {
					x = x - 1;
					e = e - inc2;
				}
				y = y + 1;
				if (y > y1) {
					break;
				} else {
					continue;
				}
			}//while
		} else {										//case4
			x = x0;
			y = y0;
			while (true) {
				g.fillRect(x, y, 1, 1);
				if (e < 0) {
					e = e - inc1;
				} else {
					x = x - 1;
					e = e + inc2;
				}
				y = y - 1;
				if (y < y1) {
					break;
				} else {
					continue;
				}
			}//while
		}
	}//Bresenhams2
}
