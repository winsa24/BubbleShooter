import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GridUI extends JPanel {
	private List<Bubble> bubbles = new ArrayList<>();

	public GridUI() {
		this.setPreferredSize(new Dimension(600, 300));
	}

	public void add(Bubble bubble) {
		bubbles.add(bubble);
	}

	public void paintComponent(Graphics g1d) {
		Graphics2D g = (Graphics2D) g1d;
		for (Bubble drawable : bubbles) {
			drawable.draw(g);
		}
	}
}
