import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GridModel {
	private List<Bubble> bubbles = new ArrayList<>();
	
	public GridModel(Grid grid) {
		bubbles = grid.getBubbles();
		
	}
	
	public Bubble isSelected(Point p) {
		for(Bubble bubble: bubbles) {
			if(bubble.contains(p)) {
				return bubble;
			}
		}
		return null;
	}
	
	public List<Bubble> checkSurroundings(Bubble selectedBubble){
		List<Bubble> sbs = new ArrayList<>(); //surrounding bubbles
		sbs.add(selectedBubble);
		
		int x = selectedBubble.getX();
		int y = selectedBubble.getY();
		int r = selectedBubble.getR(); //diameter
		Point center = new Point(x + r/2, y + r/2); // selected bubble
		
		Point pl = new Point(center.x - r, center.y);
		Point pr = new Point(center.x + r, center.y);
		Point pul = new Point(center.x - r/2, center.y - r);
		Point pur = new Point(center.x + r/2, center.y - r);
		Point pdl = new Point(center.x - r/2, center.y + r);
		Point pdr = new Point(center.x + r/2, center.y + r);
		
		for(Bubble b: bubbles) {
			if(b.contains(pdr) || b.contains(pdl) || b.contains(pur) || b.contains(pul) || b.contains(pr) || b.contains(pl)) {
				if(b.getColor() == selectedBubble.getColor()) {
					sbs.add(b);
				}
			}
		}
		
		return sbs;
	}
	
}
