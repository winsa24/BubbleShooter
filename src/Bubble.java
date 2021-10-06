import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bubble {
	private int index;
	private int x;
	private int y;
	private int r;
	private static Color[] bubbleColors = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.PINK, Color.ORANGE};
	private boolean isFired;
	private Color color;
	private static Random random = new Random();
	
	public Bubble(int index, int x, int y, int r) {
		this(index, x, y, r, bubbleColors[random.nextInt(5)]);
		
	}
	
	public Bubble(int index, int x, int y, int r, Color color) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.r = r; //diameter
		this.color = color;
	}
	
	public Bubble(int x, int y, int r) {
		this(x, y, r, bubbleColors[random.nextInt(5)]);
		
	}
	
	public Bubble(int x, int y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r; //diameter
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	public int getIndex() {
		return this.index;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getR() {
		return this.r;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public void setR(int r) {
		this.r=r;
	}

	
public boolean isFired() {
		return isFired;
	}

	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}

	//	@Override
	public void draw(Graphics2D g) {
		RenderingHints rh = g.getRenderingHints ();                  
		rh.put(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints (rh);
		
		/*if(isFired) {*/
			g.setColor(color);
			g.fillOval(x, y, r, r);
		/*}
		else {
		if (color== Color.RED) {
			Image img = getImage("red.png");
			g.drawImage(img, x, y, r, r, null);
		}
		if (color== Color.ORANGE) {
			Image img = getImage("orange.png");
			g.drawImage(img, x, y, r, r, null);
		}
		if (color== Color.YELLOW) {
			Image img = getImage("yellow.png");
			g.drawImage(img, x, y, r, r, null);
		}
		if (color== Color.PINK) {
			Image img = getImage("purple.png");
			g.drawImage(img, x, y, r, r, null);
		}
		if (color== Color.GREEN) {
			Image img = getImage("green.png");
			g.drawImage(img, x, y, r, r, null);
		}
		if (color== Color.BLUE) {
			Image img = getImage("blue.png");
			g.drawImage(img, x, y, r, r, null);
		}
		}*/

	}
	
	public boolean contains(Point p) {
		double px = p.getX();
		double py = p.getY();
		if(px > this.x && px < this.x + this.r && py > this.y && py < this.y + this.r) {
//			System.out.println(">>>>>" + p);
			return true;
		}else {
			return false;
		}
	}
	private Image getImage(String iconPath) {
		try {
			Image icon = ImageIO.read(getClass().getResource("data/"+iconPath));
			icon = icon.getScaledInstance(r, r,  java.awt.Image.SCALE_SMOOTH);
			return icon;
		} catch (IOException e) {
			System.out.println("Could not find icon");
			return null;
		}
	}
	
	
}
