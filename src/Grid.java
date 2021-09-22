import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Grid extends JPanel{
	public Grid() {
		this.setPreferredSize(new Dimension(600,300));
		this.setBackground(Color.gray);
		GridUI canvas = new GridUI();
		for(int i = 0; i < 60; i+=10) {
			for(int j = 0; j < 600; j+=10) {
				canvas.add(new Bubble(j,i));
			}	
		}

		this.add(canvas);
	}
	
}
