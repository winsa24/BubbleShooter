import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class ShooterUI {

	public void installUI(Shooter shooter) {
		shooter.setPreferredSize(new Dimension(30,80));
		shooter.setMaximumSize(new Dimension(30,80));
	}
	
	public void paint(Graphics2D pen, Shooter shooter) {
		pen.setColor(Color.lightGray);
		pen.fillRect(0, 0, shooter.getWidth(), shooter.getHeight());
	}
}

