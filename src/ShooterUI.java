import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShooterUI {

	public void installUI(Shooter shooter) {

		
		shooter.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				}
			});
		
		shooter.setPreferredSize(new Dimension(640,100));
		//shooter.setMaximumSize(new Dimension(30,80));
	}
	
	
	
	public void paint(Graphics2D pen, Shooter shooter) {
		pen.setColor(Color.lightGray);
		pen.rotate(Math.toRadians(90-shooter.getDegree()),335,100);
		 RenderingHints rh = pen.getRenderingHints ();                  //*From java2s tutorial webpage about rendering hints*
			rh.put (RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			pen.setRenderingHints (rh);
		pen.fillRect(320, 18, 25, 60);
		//pen.fill3DRect(120, 18, 25, 60,true);
	
		pen.dispose();
	}
}

