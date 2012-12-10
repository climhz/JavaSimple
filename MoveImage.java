package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class MoveImage extends Applet implements Runnable{
	Image image1, image2, image3, image4;
	int x1, y1, x2, y2, x3, y3, x4, y4;
	Thread thread;
	
	public void init() {
		setSize(600, 400);
		setBackground(Color.black);
		x1 = 30; y1 = 10;
//		x2 = 0; y2 = 0;
//		x2 = size().width - 100; y2 = 0;
//		x3 = 0; y3 = size().height -100;
//		x4 = size().width - 100; y4 = size().height - 100;
		thread = new Thread(this);
		thread.start();
		image1 = getImage(getCodeBase(), "pok.jpg");
//		image2 = getImage(getCodeBase(), "pok.jpg");
//		image3 = getImage(getCodeBase(), "pok.jpg");
//		image4 = getImage(getCodeBase(), "pok.jpg");
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform atr = g2d.getTransform();
		atr.rotate(10,x1, y1);
		g2d.setTransform(atr);
		g.drawImage(image1, x1, y1, this);
//		g.drawImage(image2, x2, y2, this);
//		g.drawImage(image3, x3, y3, this);
//		g.drawImage(image4, x4, y4, this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {}
			
			repaint();
			
			if (x1 == 0 && y1 > y2) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 += 10;
			} else if (x1 == 0 && y1 < y2) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 -= 10;
			} else if (x1 < x2 && y1 == 0) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 += 10;
			} else if (x1 > x2 && y1 == 0) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 += 10;
			} else if (x1 == size().width - 100 && y1 < y2) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 -= 10;
			} else if (x1 == size().width - 100 && y1 > y2) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 += 10;
			} else if (x1 < x2 && y1 == size().height - 100) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 -= 10;
			} else if (x1 > x2 && y1 == size().height - 100) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 -= 10;
			} else if (x1 > x2 && y1 > y2) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 += 10;
			} else if (x1 > x2 && y1 < y2) {
				x2 = x1;
				y2 = y1;
				x1 += 10; y1 -= 10;
			} else if (x1 < x2 && y1 > y2) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 += 10;
			} else if (x1 < x2 && y1 < y2) {
				x2 = x1;
				y2 = y1;
				x1 -= 10; y1 -= 10;
			} else {
				x1 += 10; y1 += 10;
			}
//			x2 -= 5; y2 += 5;
//			x3 += 5; y3 -= 5;
//			x4 -= 5; y4 -= 5;
		}		
	}
	
}
