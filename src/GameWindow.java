import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;



public class GameWindow extends JFrame {
	
	public static int soundVolume=-4;
	private Loader loader = new Loader();
	private BubbleShooter bubbleShooter = new BubbleShooter(this,loader);
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,25));
	private JPanel bottomPanel = new JPanel();
	private JPanel gameOverPanel = new JPanel();
	private Popup paramPopup;
	private Popup gameOverPopup;
	private int currentScore;
	private JLabel yourScoreLabel = new JLabel("YOUR SCORE : " + currentScore);
	private JLabel scoreLabel = new JLabel("YOUR SCORE : " + currentScore);
	
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
		this.setupGameOver();
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
		backButton.addActionListener(e -> gameOverPopup.show());
		topPanel.add(Box.createHorizontalStrut(250));
		
		JLabel title = new JLabel("Happy Bubble Shooter");
		title.setFont(new Font("Calibri", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		topPanel.add(title);
		
		topPanel.add(Box.createHorizontalStrut(150));
		
		JButton resetButton = setupButton(topPanel, "reset.png", "reset_hover.png", 48,48);
		resetButton.addActionListener(e -> {bubbleShooter.reset();
		gameOverPopup.hide(); 
        this.gameOverPopup =  new PopupFactory().getPopup(this, gameOverPanel, 300, 200);
        bubbleShooter.setGamePaused(false);
        currentScore=0;
        repaint();
		});
		
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
			
		
		scoreLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		scoreLabel.setForeground(Color.WHITE);
		bottomPanel.add(scoreLabel);
		
		this.getContentPane().add(bottomPanel,BorderLayout.SOUTH);
	}
	
	private void setupParam() {
		
	        JPanel optionPanel = new JPanel();
	        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
	        optionPanel.setBorder(BorderFactory.createLineBorder(BSColor.blackCherry2, 5, true));
	        
	        JPanel optionMenuBar = new JPanel(new FlowLayout());
	        optionMenuBar.setBackground(BSColor.blackCherry2);
	        
	        optionMenuBar.add(Box.createHorizontalStrut(170));
	        JLabel optionLabel = (JLabel) optionMenuBar.add(new JLabel("Option Menu"));
	        optionLabel.setForeground(Color.WHITE);
	        optionLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
	        optionMenuBar.add(Box.createHorizontalStrut(130));
	        JButton closeButton = (JButton) optionMenuBar.add(new JButton("X"));
	        closeButton.addActionListener(e->{paramPopup.hide(); 
	        this.paramPopup =  new PopupFactory().getPopup(this, optionPanel, 300, 200);});
	        optionPanel.add(optionMenuBar);
	        
	        JPanel settingPanel =new JPanel();
	        GroupLayout layout = new GroupLayout(settingPanel);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);
	        settingPanel.setBackground(BSColor.trypanBlue);
	        setupVolumParam(settingPanel);
	        
	        
	        optionPanel.add(settingPanel);
	        this.paramPopup =  new PopupFactory().getPopup(this, optionPanel, 300, 200);
	     
	       
	}
	
	private void setupVolumParam(JPanel settingPanel) {
		settingPanel.add(Box.createVerticalStrut(50));
		JLabel volumeLabel = (JLabel) settingPanel.add(new JLabel("Sound Volume :"));
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        JSlider volumeSlider =(JSlider) settingPanel.add(new JSlider(-14,6,soundVolume));
        volumeSlider.setBackground(null);
        volumeSlider.addChangeListener(e->soundVolume=(int)((JSlider)e.getSource()).getValue());
	}
	
	public void setupGameOver() {
	     gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
	     gameOverPanel.setBorder(BorderFactory.createLineBorder(BSColor.blackCherry2, 5, true));
	     gameOverPanel.setBackground(BSColor.trypanBlue);
	     
	     JLabel gameOverLabel = (JLabel) gameOverPanel.add(new JLabel("GAME OVER"));
	     gameOverLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
	     gameOverLabel.setForeground(Color.WHITE);
	     
	     this.gameOverPopup =  new PopupFactory().getPopup(this, gameOverPanel, 300, 200);
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
	
	public void displayGameOver() {
		  
	    yourScoreLabel.setText("Your Score : " + currentScore);
	    yourScoreLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
	    yourScoreLabel.setForeground(Color.WHITE);
		gameOverPanel.add(yourScoreLabel);
		gameOverPopup.show();
	}
	
	private void playHoverSound() {
		 URL audio= this.getClass().getClassLoader().getResource("data/hover.wav");
         try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio);
			Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        FloatControl gainControl =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue((float)soundVolume); 
	        clip.start();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	public void setCurrentScore(int score) {
		this.currentScore+=calculateScore(score);
		scoreLabel.setText("YOUR SCORE : " + currentScore);
	}
	
	public int getScore() {
		return this.currentScore;
	}
	
	private int calculateScore(int score) {
		if (score>0) {
		return 30+20*(score-3);
		}
		else return 0;
	}
}
