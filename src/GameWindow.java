import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;



public class GameWindow extends JFrame {
	
	private Loader loader = new Loader();
	private BubbleShooter bubbleShooter = new BubbleShooter(loader);
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,25));
	private JPanel bottomPanel = new JPanel();
	private Popup paramPopup;
	private int currentScore;
	
	public GameWindow(String title) {
		super(title);
		this.setupUI();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setupUI() {
		this.setPreferredSize(new Dimension(1000,800));
		this.setResizable(false);
		this.getContentPane().setLayout(new BorderLayout());
		
		this.setupParam();
		this.setupTop(topPanel);
		this.setupMainPanel(bubbleShooter);
		this.setupBottom(bottomPanel);
		
	
		this.pack();
		
	}

	private void setupTop(JPanel topPanel) {
		
		topPanel.setPreferredSize(new Dimension(1000,100));
		topPanel.setBackground(BSColor.trypanBlue);	
		topPanel.add(Box.createHorizontalStrut(10));

		
		JButton backButton = setupButton(topPanel, "return.png", "return_hover.png", 48,48);
		
		topPanel.add(Box.createHorizontalStrut(250));
		
		JLabel title = new JLabel("Happy Bubble Shooter");
		title.setFont(new Font("Calibri", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		topPanel.add(title);
		
		topPanel.add(Box.createHorizontalStrut(170));
		
		JButton resetButton = setupButton(topPanel, "reset.png", "reset_hover.png", 48,48);
		resetButton.addActionListener(e -> bubbleShooter.reset());
		
		JButton paramButton = setupButton(topPanel, "gear.png", "gear_hover.png", 48,48);
		paramButton.addActionListener(e -> paramPopup.show());
		
		this.getContentPane().add(topPanel,BorderLayout.NORTH);
	}

	private void setupMainPanel(BubbleShooter bubbleShooter) {
		
		this.getContentPane().add(bubbleShooter,BorderLayout.CENTER);
		
	}

	private void setupBottom(JPanel bottomPanel) {

		bottomPanel.setPreferredSize(new Dimension(1000,150));
		bottomPanel.setBackground(BSColor.trypanBlue);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,200,10));
		
		bottomPanel.add(loader);
			
		JLabel scoreLabel = new JLabel("YOUR SCORE : " + currentScore);
		scoreLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		scoreLabel.setForeground(Color.WHITE);
		bottomPanel.add(scoreLabel);
		
		this.getContentPane().add(bottomPanel,BorderLayout.SOUTH);
	}
	
	private void setupParam() {
		
	        JPanel optionPanel = new JPanel();
	        optionPanel.setBackground(BSColor.blackCherry);
	        optionPanel.add(new JLabel("Option Menu"));
	        optionPanel.setMinimumSize(new Dimension(400,500));
	 
	        this.paramPopup =  new PopupFactory().getPopup(this, optionPanel, 300, 200);
	     
	       
	}
	
	private JButton setupButton(JPanel panel, String icon, String iconHover, int w, int h) {
		ImageIcon iconImg = getIcon(icon,w,h);
		ImageIcon iconHoverImg = getIcon(iconHover,w,h);
		JButton button = new JButton(iconImg);
		
		button.setRolloverIcon(iconHoverImg);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setPreferredSize(new Dimension(w,h));
		button.setMargin(new Insets(20,20,20,20));
		button.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	playHoverSound();
		    }});
		
		panel.add(button);
		return button;
	}
	
	private ImageIcon getIcon(String iconPath, int w, int h) {
		try {
			Image icon = ImageIO.read(getClass().getResource("data/"+iconPath));
			icon = icon.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		} catch (IOException e) {
			System.out.println("Could not find icon");
			return null;
		}
	}
	
	private void playHoverSound() {
		 URL audio= this.getClass().getClassLoader().getResource("data/hover.wav");
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
