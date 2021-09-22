import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		shooter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Shooter fired");
			}
		});
		
		shooterPanel.add(shooter);
		this.add(shooterPanel,BorderLayout.SOUTH);
		
	}
	
}
