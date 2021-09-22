import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Bubble {
	private int x;
	private int y;
	private static int r = 10;
	private Color color;
	private static Random random = new Random();
	
	public Bubble(int x, int y) {
		this(x, y, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	}
	
	public Bubble(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
//	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, r, r);
	}
	
}
