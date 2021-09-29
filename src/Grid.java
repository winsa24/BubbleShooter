import java.awt.Color;
import java.awt.Dimension;
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
	
	
	public Grid() {
		this.setPreferredSize(new Dimension(600,300));
		this.setBackground(BSColor.blackCherry);
		this.setBorder(BorderFactory.createLineBorder(BSColor.trypanBlue, 2));
		
		GridUI canvas = new GridUI(this);
		GridModel model = new GridModel(this);
		
		for(int i = 0; i < 200; i+= r) {
			if(i/10%2 == 1) {
				for(int j = 0; j < 600 - r; j+= r) {
					canvas.add(new Bubble(j + r/2, i, r));
				}
			}	
			else {
				for(int j = 0; j < 600; j+= r) {
					canvas.add(new Bubble(j, i, r));
				}
			}
		}
		
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
				
			}
		});
		

		this.add(canvas);
	}
	
	public List<Bubble> getBubbles(){
		return this.bubbles;
	}
}
