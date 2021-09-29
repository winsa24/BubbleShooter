import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class BubbleShooter extends JPanel{
	
    private JLayeredPane lPanel = new JLayeredPane();
    private JPanel animationPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
	private Grid grid = new Grid();
	private Shooter shooter = new Shooter(this);
	private Loader loader;
	
	
	public BubbleShooter(Loader loader) {
		this.loader= loader;
		this.setupUI();
		}
	
	private void setupUI() {
		this.setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		
		//this.setBackground(BSColor.blackCherry);
		this.setupGrid();
		this.setupShooter();
	
		//lPanel.setPreferredSize(new Dimension (1000,800));
		//lPanel.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		//mainPanel.setBounds(0, 0,1000,515);
		//lPanel.add(animationPanel,1,0);
		this.add(mainPanel, BorderLayout.CENTER);
		
		
	}
	
	private void setupGrid() {
		JPanel gridPanel = new JPanel();
		gridPanel.setBackground(BSColor.blackCherry);
		gridPanel.setPreferredSize(new Dimension(1000,500));
		gridPanel.add(grid);
		mainPanel.add(gridPanel,BorderLayout.NORTH);
	}

	private void setupShooter() {
		JPanel shooterPanel = new JPanel();
		shooterPanel.setBackground(BSColor.blackCherry);
		shooterPanel.setPreferredSize(new Dimension(this.getWidth(),80));
		
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
				shooter.setFired(true);
				shooter.repaint();
				}
			});
		shooter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Bubble bubbleFired  = loader.fire();
				bubbleFired.setFired(true);
				bubbleFired.setX(120);
				bubbleFired.setY(-10);
				
				shooter.setFiredBubble(bubbleFired);
				grid.add(bubbleFired);
				grid.setFireDegree(shooter.getDegree());
				
				animateBubble(bubbleFired,100,400);
				System.out.println(bubbleFired.getColor()+" ball fired at " + shooter.getDegree());
			}
		});
		
		shooterPanel.add(shooter);
		mainPanel.add(shooterPanel,BorderLayout.SOUTH);
		
	}
	
	private double getShooterDegree(MouseEvent e) {
		this.revalidate();
		double x = e.getX()-(this.getWidth()/2);
		double y = -e.getY()+this.getHeight();
		return Math.atan2(y,x)* 180 / Math.PI;
	}
	
	private void animateBubble(Bubble bubbleFired, int framesPerSec,int maxFrames) {
		Timer timer = new Timer(1000/framesPerSec,new ActionListener() {
	        int currentFrame = 0;
	        public void actionPerformed(ActionEvent e) {
	           bubbleFired.setY(-10 - 3*currentFrame);
	           shooter.repaint();         
	           grid.repaint();
	            if (currentFrame != maxFrames)
	                currentFrame++;
	            	
	            else
	                ((Timer)e.getSource()).stop();
	        }});
		timer.start();
	}
	
	
}
