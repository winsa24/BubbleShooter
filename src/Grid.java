import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Grid extends JPanel{
	private static int r = 30; //bubble size
	private List<Bubble> bubbles = new ArrayList<>();
	private GridUI ui = new GridUI();
	private GridModel model = new GridModel(this);
	private double fireDegree=10;

	public Grid() {
		
		ui.setupUI(this);
	    loadNewGrid();
	}

	public void loadNewGrid() {
		bubbles.clear();
		for(int i = 0; i < 200; i+= r) {
			if(i/10%2 == 1) {
				for(int j = 0; j < 600 - r; j+= r) {
					this.add(new Bubble(j + r/2, i, r));
				}
			}	
			else {
				for(int j = 0; j < 600; j+= r) { 
					this.add(new Bubble(j, i, r));
				}
			}
		}
		repaint();
	}

	
	public List<Bubble> getBubbles(){
		return this.bubbles;
	}
	
	public void add(Bubble bubble) {
		bubbles.add(bubble);
	}
	public void elimate(List<Bubble> sbs) {
		Timer timer = new Timer(1000/100,new ActionListener() {
	        int currentFrame = 0;
	        public void actionPerformed(ActionEvent e) {

	            if (currentFrame < 5) {
	            	currentFrame++;
	            	if(sbs.size() > 2) {
		    			for(Bubble bubble: sbs) {
		    				bubble.setX(bubble.getX() + 1);
		    			}
		    			// repaint
		    			repaint();
		    		}
	            }
	            else if(currentFrame >= 5 && currentFrame < 10) { 
	            	currentFrame++;
	            	if(sbs.size() > 2) {
		    			for(Bubble bubble: sbs) {
		    				bubble.setX(bubble.getX() - 1);
		    			}
		    			// repaint
		    			repaint();
		    		}
	            }
	            else {
	            	((Timer)e.getSource()).stop();
	            	System.out.println("before: " + bubbles.size() + ", all:" + bubbles.get(0));
	            	System.out.println("sbs size: " + sbs.size());
	        		if(sbs.size() > 2) {
	        			// delete in sequence
	        			bubbles.remove(bubbles.size() - 1);
	        			for(int i = bubbles.size() - 2; i >= 0; i--)
	        	        {
	        	        	for(int j = sbs.size() - 1; j >= 0; j--) {
	        					if(bubbles.get(i) == sbs.get(j)) {
	        						bubbles.remove(i);
	        					}
	        				}
	        	            
	        	        }
	        			System.out.println("after: " + bubbles.size() + "all:" + bubbles.get(0));
	        			repaint();
	        		}
	            }

	        }});
		timer.start();

	}
	
	public Bubble addFireBubble(Bubble hittedBubble, Color c) {
		Bubble hitBubble = null;
		
		if(hittedBubble != null) {
			int fbx = 0;
			if(hittedBubble.getX() < 300) {
				fbx = (int)(hittedBubble.getX() / r) * r + r / 2 + ((hittedBubble.getY() / r % 2) * r/2);
			}else {
				fbx = (int)(hittedBubble.getX() / r) * r - r / 2 + ((hittedBubble.getY() / r % 2) * r/2);
			}
			int fby = (int)(hittedBubble.getY() / r) * r + r;

			hitBubble = new Bubble(fbx, fby, r, c);
			bubbles.add(hitBubble);
			
			
			List<Bubble> sbs = model.checkSurroundings(hitBubble);
//			System.out.println("first round surrounding same color bubbles: " + sbs);
			
			int size = sbs.size() - 1;
			while(sbs.size() != size) {
				size = sbs.size();
				for(Bubble bubble: sbs) {
					List<Bubble> newsbs = model.checkSurroundings(bubble);					
					// combine two list without duplicate child
					Set<Bubble> set = new HashSet<>(sbs);
			        set.addAll(newsbs);
			        sbs = new ArrayList<>(set);
				}
			}
//			System.out.println("final surroundings: " + sbs);
			
			if(hitBubble.getColor() == sbs.get(0).getColor()) {
				elimate(sbs);
			}
			this.repaint();
		}
		
		return hitBubble;
	}
	
	public double getFireDegree() {
		return fireDegree;
	}
	public void setFireDegree(double Degree) {
		this.fireDegree=Degree;
	}
	public Bubble getHitBubble(double degree) {
		double dx=Math.cos(Math.toRadians(degree)) ;
        double dy=Math.sin(Math.toRadians(degree)) ;
        int initX = (int)(315+80*dx);
        int initY = (int)(500-80*dy);
        Bubble hitBubble = new Bubble(2000,2000,r,r);
		for (int i = bubbles.size()-1; i>0;--i) {
			int bubbleY =bubbles.get(i).getY();
			int x = (int) (((initY-bubbleY)/Math.tan(Math.toRadians(degree))) + initX);
			System.out.println("x"+bubbles.get(i).getX());
			System.out.println(":>>>>:" + x);
			if(bubbles.get(i).contains(new Point(x,bubbleY))) {
				if(bubbleY>hitBubble.getY()) 
				hitBubble =  bubbles.get(i);
			}
				
		}
		return hitBubble;
	}
	
	public void paintComponent(Graphics g1d) {
		super.paintComponent(g1d);
		this.ui.paint(g1d,this);
	}
	

}
