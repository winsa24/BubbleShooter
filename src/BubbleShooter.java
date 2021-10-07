import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class BubbleShooter extends JPanel{
	
    private JLayeredPane lPanel = new JLayeredPane();
    private AnimPanel animationPanel = new AnimPanel();
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
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//this.setBackground(BSColor.blackCherry);
		this.setupGrid();
		this.setupShooter();
		lPanel.setPreferredSize(new Dimension (1000,800));
		lPanel.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
		mainPanel.setBounds(0, 0,1000,515);
		
		animationPanel.setOpaque(false);
		animationPanel.setBounds(0, 0,1000,515);
		lPanel.add(animationPanel,1,JLayeredPane.PALETTE_LAYER);
		
		this.add(lPanel, BorderLayout.CENTER);
		
		
	}
	
	private void setupGrid() {
		JPanel gridPanel = new JPanel();
		
		gridPanel.setPreferredSize(new Dimension(1000,438));
		gridPanel.setBackground(BSColor.blackCherry);
		
		gridPanel.add(grid);
		mainPanel.add(gridPanel,BorderLayout.NORTH);
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
				double degree= shooter.getDegree();
				double dx= Math.cos(Math.toRadians(degree)) ;
		        double dy= Math.sin(Math.toRadians(degree)) ;
		        int initX = (int)(500+80*dx);
		        int initY = (int)(525-80*dy);
				bubbleFired.setX(initX);
				bubbleFired.setY(initY);
				bubbleFired.setFired(true);
				animationPanel.setBubbleFired(bubbleFired);
				animationPanel.repaint();
				grid.setFireDegree(degree);
				
				
				playShootSound();
				animateBubble(bubbleFired,100,400,initX,initY,shooter.getDegree());
				System.out.println(bubbleFired.getColor()+" ball fired at " + shooter.getDegree());
			}
		});
		
		shooterPanel.add(shooter);
		mainPanel.add(shooterPanel,BorderLayout.SOUTH);
		
	}
	
	public void reset() {
		grid.loadNewGrid();
		shooter.reset();
		loader.reset();
	}
	
	private double getShooterDegree(MouseEvent e) {
		this.revalidate();
		double x = e.getX()-(this.getWidth()/2)-20;
		double y = -e.getY()+this.getHeight()+27;
		return Math.atan2(y,x)* 180 / Math.PI;
	}
	
	private void animateBubble(Bubble bubbleFired, int framesPerSec,int maxFrames,int initX, int initY, double degree) {
		Timer timer = new Timer(1000/framesPerSec,new ActionListener() {
	        int currentFrame = 0;
	        public void actionPerformed(ActionEvent e) {
	           double dx=Math.cos(Math.toRadians(degree)) ;
	           double dy=Math.sin(Math.toRadians(degree)) ;
	           Bubble bubbleHit = grid.getHitBubble(degree);
	           bubbleFired.setX((int)(initX + 8*currentFrame*dx));  
	           bubbleFired.setY((int)(initY - 8*currentFrame*dy));  
	           animationPanel.repaint();
	            if (currentFrame != maxFrames && bubbleFired.getY()>bubbleHit.getY()+15)
	                currentFrame++;
	            	
	            else {
	                ((Timer)e.getSource()).stop();
	                bubbleFired.setVisible(false);
	                grid.addFireBubble(new Point(bubbleHit.getX(),bubbleHit.getY()), bubbleFired.getColor());
					grid.repaint();
	            }
	        }});
		timer.start();
	}
	
	private void playShootSound() {
		 URL audio= this.getClass().getClassLoader().getResource("data/shoot.wav");
        try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio);
			Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace(); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
