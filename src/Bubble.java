import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bubble {
	private int x;
	private int y;
	private int r;
	private static Color[] bubbleColors = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.PINK, Color.ORANGE};
	private Color color;
	private static Random random = new Random();
	
	public Bubble(int x, int y, int r) {
		this(x, y, r, bubbleColors[random.nextInt(5)]);
	}
	
	public Bubble(int x, int y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
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
	
}
