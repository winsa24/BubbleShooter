import java.awt.*;
import javax.swing.*;

public class BubbleShooter extends JPanel{
	
	
	private Grid grid = new Grid();
	private Shooter shooter = new Shooter();
	
	
	public BubbleShooter() {
		this.setupUI();
		
		}
	
	private void setupUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(BSColor.blackCherry);
		
		this.setupShooter();
		
	}

	private void setupShooter() {
		JPanel shooterPanel = new JPanel();
		shooterPanel.setBackground(BSColor.blackCherry);
		shooterPanel.setPreferredSize(new Dimension(1000,80));
		shooterPanel.add(shooter);
		this.add(shooterPanel,BorderLayout.SOUTH);
		
	}
	
}
