package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

@SuppressWarnings("serial")
public class ThreadApplet extends Applet implements Runnable{
	Boolean choice;
	Thread thread1, thread2;
	int x1 = 50, y1 = 0;
	int x2 = 100, y2 = 0;
	public void init() {
		setSize(600, 600);
		thread1 = new Thread(this);
		thread2 = new Thread(this);
		thread1.start();
		thread2.start();		
	}
	
	public void paint(Graphics g) {
		
	}
	
	public void drawOval() {
		int random = new Random().nextInt(100);
		Graphics g = getGraphics();
		g.drawOval(random, random, 50, 50);
	}
	
	public void drawRect() {
		int random = new Random().nextInt(100);
		Graphics g = getGraphics();
		g.drawRect(random, random, 50, 100);
	}
	
	public void drawLine1() {
		Graphics g = getGraphics();
		g.setColor(Color.red);
		g.drawLine(x1, y1, x1, y1+10);
		y1 = y1+10;
	}
	
	public void drawLine2() {
		Graphics g = getGraphics();
		g.setColor(Color.blue);
		g.drawLine(x2, y2, x2, y2+10);
		y2 = y2+10;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (y2 < 500) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			if (Thread.currentThread() == thread1) {
//				drawOval();
				drawLine1();
			}
			if (Thread.currentThread() == thread2) {
//				drawRect();
				drawLine2();
			}
		}
	}
	
}
