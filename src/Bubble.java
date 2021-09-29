import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		g.setColor(color);
		g.fillOval(x, y, r, r);
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
	
	
	
}
