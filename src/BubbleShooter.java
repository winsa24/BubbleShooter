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
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class BubbleShooter extends JPanel{
	
	private GameWindow window;
    private JLayeredPane lPanel = new JLayeredPane();
    private AnimPanel animationPanel = new AnimPanel();
    private JPanel mainPanel = new JPanel();
	private Grid grid = new Grid();
	private Shooter shooter = new Shooter(this);
	private Loader loader;
	private boolean isGamePaused = false;
	
	
	public BubbleShooter(GameWindow window, Loader loader) {
		this.window= window;
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
				if(!isGamePaused()) {
					setGamePaused(true);
					Bubble bubbleFired  = loader.fire();
					if(loader.isReloading()) {
						grid.newBubbleLine();
					}
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
					playSound("shoot.wav");
					animateBubble(bubbleFired,200,400,initX,initY,shooter.getDegree());
				}
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


	private boolean isGamePaused() {
		return isGamePaused;
	}

	public void setGamePaused(boolean isGamePaused) {
		this.isGamePaused = isGamePaused;
	}

	private void animateBubble(Bubble bubbleFired, int framesPerSec,int maxFrames,int initX, int initY, double degree) {
		Timer timer = new Timer(1000/framesPerSec,new ActionListener() {
	        int currentFrame = 0;
	        public void actionPerformed(ActionEvent e) {
	           double dx=Math.cos(Math.toRadians(degree)) ;
	           double dy=Math.sin(Math.toRadians(degree)) ;
	           Bubble bubbleHit = grid.getHitBubble(degree);

	           bubbleFired.setX((int)(initX + 12*currentFrame*dx));  
	           bubbleFired.setY((int)(initY - 12*currentFrame*dy));  
	           animationPanel.repaint();
	            if (currentFrame != maxFrames && bubbleFired.getY()>bubbleHit.getY() + 1.5 * bubbleHit.getR() )
	                currentFrame++;
	            	
	            else {
	                ((Timer)e.getSource()).stop();
	                registerHit(bubbleFired,bubbleHit);
	            }
	        }});
		timer.start();
	}
	
	private void registerHit(Bubble bubbleFired,Bubble bubbleHit) {
		bubbleFired.setVisible(false);
        int previousScore = window.getScore();
        window.setCurrentScore(grid.addFireBubble(bubbleHit, bubbleFired.getColor()));
        if(previousScore == window.getScore()) {
        	playSound("lock.wav");
        }
        else {playSound("pop.wav");}
      //  System.out.println("is game over? " + grid.checkIsGameover());
		grid.repaint();
		setGamePaused(false);
		if(grid.checkIsGameover()) {
			window.displayGameOver();
			playSound("gameover.wav");
			setGamePaused(true);
		}
		
	}
	private void playSound(String sound) {
		 URL audio= this.getClass().getClassLoader().getResource("data/"+sound);
        try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio);
			Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        FloatControl gainControl =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue((float)GameWindow.soundVolume); 
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
