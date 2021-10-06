import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Grid extends JPanel{
	private static int r = 30; //bubble size
	private List<Bubble> bubbles = new ArrayList<>();
	private GridUI ui = new GridUI();
	private GridModel model = new GridModel(this);
	private double fireDegree=10;

	public Grid() {
		
		ui.setupUI(this);
		
	    loadNewGrid();
		
		this.addMouseListener(new MouseAdapter() {
			// Mouse handlers here
			@Override
			public void mouseClicked(MouseEvent e) {
				Bubble sb = model.isSelected(e.getPoint());
				System.out.println("selected bubble:" + sb);
				
				List<Bubble> sbs = model.checkSurroundings(sb);
				System.out.println("first round surrounding same color bubbles: " + sbs);
				
				int size = sbs.size() - 1;
				while(sbs.size() != size) {
					size = sbs.size();
					for(Bubble bubble: sbs) {
						List<Bubble> newsbs = model.checkSurroundings(bubble);
						System.out.println("new sbs: " + newsbs);
//						sbs.removeAll(newsbs);
//						sbs.addAll(newsbs);
						
						// combine two list without duplicate child
						Set<Bubble> set = new HashSet<>(sbs);
				        set.addAll(newsbs);
				        sbs = new ArrayList<>(set);
					}
				}
				System.out.println("final surroundings: " + sbs);
				
				elimate(sbs);
			}
		});
	}

	public void loadNewGrid() {
		bubbles.clear();
		for(int i = 0; i < 200; i+= r) {
			if(i/10%2 == 1) {
				for(int j = 0; j < 600 - r; j+= r) {
					this.add(new Bubble(j + r/2, i, r));
				}
			}	
			else {
				for(int j = 0; j < 600; j+= r) {
					this.add(new Bubble(j, i, r));
				}
			}
		}
		repaint();
	}

	
	public List<Bubble> getBubbles(){
		return this.bubbles;
	}
	
	public void add(Bubble bubble) {
		bubbles.add(bubble);
	}
	public void elimate(List<Bubble> sbs) {
		if(sbs.size() > 2) {
			for(Bubble bubble: sbs) {
				bubble.setColor(BSColor.blackCherry);
			}
			// repaint
			this.repaint();
		}
	}
	
	
	public double getFireDegree() {
		return fireDegree;
	}
	public void setFireDegree(double Degree) {
		this.fireDegree=Degree;
	}
	
	public void paintComponent(Graphics g1d) {
		super.paintComponent(g1d);
		this.ui.paint(g1d,this);
	}
}
