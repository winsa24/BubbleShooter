import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class BubbleShooter extends JPanel{
	
	
	private Grid grid = new Grid();
	private Shooter shooter = new Shooter(this);
	private Loader loader;
	
	
	public BubbleShooter(Loader loader) {
		this.loader= loader;
		this.setupUI();
		}
	
	private void setupUI() {
		this.setLayout(new BorderLayout());
		this.setBackground(BSColor.blackCherry);
		this.setupGrid();
		this.setupShooter();
		
	}
	
	private void setupGrid() {
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(BSColor.blackCherry);
		gridPanel.setPreferredSize(new Dimension(1000,300));
		gridPanel.add(grid);
		this.add(gridPanel,BorderLayout.NORTH);
	}

	private void setupShooter() {
		JPanel shooterPanel = new JPanel();
		shooterPanel.setBackground(BSColor.blackCherry);
		shooterPanel.setPreferredSize(new Dimension(1000,80));
		
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				shooter.setDegree(getShooterDegree(e));
				shooter.repaint();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				shooter.fire();
				shooter.repaint();
				}
			});
		shooter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bubble bubbleFired  = loader.fire();
				System.out.println(bubbleFired.getColor()+" ball fired");
			}
		});
		
		shooterPanel.add(shooter);
		this.add(shooterPanel,BorderLayout.SOUTH);
		
	}
	
	private double getShooterDegree(MouseEvent e) {
		this.revalidate();
		double x = e.getX()-(this.getWidth()/2);
		double y = -e.getY()+this.getHeight();
		return Math.atan2(y,x)* 180 / Math.PI;
	}
	
}
