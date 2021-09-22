import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShooterUI {

	public void installUI(Shooter shooter) {
		shooter.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				shooter.fire();
				shooter.setDegree(shooter.getDegree()+20);
				shooter.repaint();
				}
			});
		shooter.setPreferredSize(new Dimension(30,80));
		shooter.setMaximumSize(new Dimension(30,80));
	}
	
	public void paint(Graphics2D pen, Shooter shooter) {
		pen.setColor(Color.lightGray);
		pen.rotate(Math.toRadians(shooter.getDegree()));
		pen.fillRect(0, 0, shooter.getWidth(), shooter.getHeight());
	}
}

