import java.awt.*;
import javax.swing.*;

public class Shooter extends JComponent{
	
	private ShooterUI ui;
	private ShooterModel model;
	
	public Shooter() {
		this.model= new ShooterModel();
		this.ui= new ShooterUI();
		this.ui.installUI(this);
	}
	
	public void paintComponent(Graphics pen) {
		this.ui.paint((Graphics2D) pen, this);
	}

}
