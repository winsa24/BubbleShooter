import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;



public class GridUI {
	
	public void setupUI(Grid grid) {
		grid.setPreferredSize(new Dimension(600,300));
		grid.setBackground(BSColor.blackCherry);
		//grid.setBorder(BorderFactory.createLineBorder(BSColor.trypanBlue, 2));	
		
	}

	public void paint(Graphics g1d, Grid grid) {
		
		List<Bubble> bubbles = grid.getBubbles();
		double degree= grid.getFireDegree();
		Graphics2D g = (Graphics2D) g1d;
		
		for (Bubble drawable : bubbles) {
			drawable.draw(g);
		}
		g.dispose();
	}
	
}
