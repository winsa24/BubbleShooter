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
				shooter.setDegree(shooter.getDegree());
				shooter.repaint();
				}
			});
		
		shooter.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				System.out.println("mouse at x: " + (e.getX()-135) + " y : "+ (-e.getY()+80)+ " degree : "+ Math.atan2(-e.getY()+80, e.getX()-125)* 180 / Math.PI);
				}
			});
		shooter.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				shooter.setDegree(90+ Math.atan2(e.getY()-80, e.getX()-135)* 180 / Math.PI);
				shooter.repaint();
			}
		});
		shooter.setPreferredSize(new Dimension(240,100));
		//shooter.setMaximumSize(new Dimension(30,80));
	}
	
	public void paint(Graphics2D pen, Shooter shooter) {
		pen.setColor(Color.lightGray);
		pen.rotate(Math.toRadians(shooter.getDegree()),135,100);
		pen.fillRect(120, 20, 30, 80);
		
	}
}

