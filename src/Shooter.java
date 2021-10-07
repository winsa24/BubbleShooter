import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Shooter extends JComponent{
	
	private ShooterUI ui;
	private ShooterModel model;
	private Bubble firedBubble;
	private double degree;
	private BubbleShooter window;
	
	public Shooter(BubbleShooter window) {
		this.model= new ShooterModel();
		this.ui= new ShooterUI();
		this.ui.installUI(this);
		this.window = window;
	}
	
	public double getDegree() {
		return this.degree;
	}
	
	public ShooterModel getModel(){
		return this.model;
	}
	
	public int getWindowHeight() {
		return this.window.getHeight();
	}
	
	public int getWindowWidth() {
		return this.window.getWidth();
	}
		
	public void setDegree(double degree) {
		this.degree = degree;
	}
	
	public Bubble getFiredBubble() {
		return firedBubble;
	}

	public void setFiredBubble(Bubble firedBubble) {
		this.firedBubble = firedBubble;
	}

	public void paintComponent(Graphics pen) {
		this.ui.paint((Graphics2D) pen, this);
	}
	
	public void addActionListener(ActionListener listener) {
		this.model.addActionListener(listener);
	}
	public void fire() {
		this.model.fireActionListener();
	}
	
	public void reset() {
		this.degree=90;
		repaint(); 
	}
}
