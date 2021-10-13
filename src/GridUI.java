import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;



public class GridUI {
	private int lineY = 400;
	
	public int getLineY() {
		return lineY;
	}
	
	public void setupUI(Grid grid) {
		grid.setPreferredSize(new Dimension(600,500)); 
		grid.setBackground(BSColor.blackCherry);		
	}

	public void paint(Graphics g1d, Grid grid) {
		
		List<Bubble> bubbles = grid.getBubbles();
		double degree= grid.getFireDegree();
		Graphics2D g = (Graphics2D) g1d;
		
		for (Bubble drawable : bubbles) {
			drawable.draw(g);
		}
		g.setStroke(new BasicStroke(5));
		g.setColor(Color.RED);
        g.drawLine(0, lineY, 600, lineY);
        
		g.dispose();
	}
	
}
