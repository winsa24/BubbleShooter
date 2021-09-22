import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Shooter extends JComponent{
	
	private ShooterUI ui;
	private ShooterModel model;
	private int degree;
	
	public Shooter() {
		this.model= new ShooterModel();
		this.ui= new ShooterUI();
		this.ui.installUI(this);
	}
	
	public int getDegree() {
		return degree;
	}
	
	public ShooterModel getModel(){
		return model;
	}
	
	public void fire() {
		this.model.fireActionListener();
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	
	public void paintComponent(Graphics pen) {
		this.ui.paint((Graphics2D) pen, this);
	}
	
	public void addActionListener(ActionListener listener) {
		this.model.addActionListener(listener);
	}
	
}
