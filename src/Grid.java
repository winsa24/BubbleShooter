import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Grid extends JPanel{
	private static int r = 30; //bubble size
	
	public Grid() {
		this.setPreferredSize(new Dimension(600,300));
		this.setBackground(BSColor.blackCherry);
		this.setBorder(BorderFactory.createLineBorder(BSColor.trypanBlue, 2));
		
		GridUI canvas = new GridUI();
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

		this.add(canvas);
	}
	
}
