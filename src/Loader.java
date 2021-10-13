import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Loader extends JPanel{
	
	private ArrayList<Bubble> bubblesInLoader = new ArrayList<>();
	private int bubbleSize = 30;
	
	public Loader() {
		setPreferredSize(new Dimension(200,40));
		setBackground(BSColor.trypanBlue);
		
		for(int i = 8; i <bubbleSize*5; i+= bubbleSize+3) {
				bubblesInLoader.add(new Bubble(i, 5, bubbleSize));
			}
		}
	
	public void reload() {
		for(int i = 8; i <bubbleSize*5; i+= bubbleSize+3) {
			bubblesInLoader.add(new Bubble(i, 5, bubbleSize));
			
		}
		repaint();
	};
	
	public Bubble fire( ) {
		int bubblesLeft = bubblesInLoader.size();
		if (bubblesLeft>1) {
			Bubble bubble = bubblesInLoader.remove(bubblesLeft-1);
			repaint();
			return bubble;
			
		}
		else {
			Bubble bubble= bubblesInLoader.remove(0);
			reload();
			repaint();
			return bubble;
		}
	};
	
	public boolean isReloading() {
		return(bubblesInLoader.size()==5); 
	}
	public void reset() {
		bubblesInLoader.clear();
		reload(); 
	}
	
	@Override
	public void paintComponent(Graphics g1d) {
		super.paintComponent(g1d);
		Graphics2D g = (Graphics2D) g1d;
		g.setColor(BSColor.trypanBlue);
		g.drawRect(0, 0, this.getWidth(), getHeight());
		g.setStroke(new BasicStroke(2f));
		g.setColor(BSColor.blackCherry);
		RenderingHints rh = g.getRenderingHints ();                  
		rh.put(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints (rh);
		RoundRectangle2D.Float rec = new RoundRectangle2D.Float(2, 2, 174, 36, 35, 35);
		g.draw(rec);
		for (Bubble drawable : bubblesInLoader) {
			drawable.draw(g);
			
		}
		g.dispose();
	
	}
}

	

