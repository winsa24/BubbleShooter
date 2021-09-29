import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class AnimPanel extends JPanel{
	Bubble bubbleFired;
	
	
	public void setBubbleFired(Bubble bubble) {
		this.bubbleFired=bubble;
	}
	
	public Bubble getBubbleFired() {
		return bubbleFired;
	}
	
	
	@Override
	public void paintComponent(Graphics g1d) {
		super.paintComponent(g1d);
		Graphics2D g = (Graphics2D) g1d;
		RenderingHints rh = g.getRenderingHints ();                  
		rh.put(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints (rh);
		if(bubbleFired!=null)
			bubbleFired.draw(g);
		
		g.dispose();
	
	}

}
