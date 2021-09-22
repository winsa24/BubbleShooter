import java.awt.*;

import javax.swing.*;



public class GameWindow extends JFrame {
	
	private BubbleShooter bubbleShooter = new BubbleShooter();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	public GameWindow(String title) {
		super(title);
		this.setupUI();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setupUI() {
		this.getContentPane().setLayout(new BorderLayout());
		
		this.setupTop(topPanel);
		this.setupMainPanel(bubbleShooter);
		this.setupBottom(bottomPanel);
		
		this.setPreferredSize(new Dimension(1000,800));
		this.pack();
		
	}

	private void setupTop(JPanel topPanel) {
		
		topPanel.setPreferredSize(new Dimension(1000,100));
		topPanel.setBackground(BSColor.trypanBlue);
		
		JButton backButton = new JButton("Back to Menu");
		topPanel.add(backButton);
		
		JLabel title = new JLabel("Happy Bubble Shooter");
		title.setFont(new Font("Calibri", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		topPanel.add(title);
		
		JButton restartButton = new JButton("Restart");
		topPanel.add(restartButton);
		
		JButton paramsButton = new JButton("Params");
		topPanel.add(paramsButton);
		
		this.getContentPane().add(topPanel,BorderLayout.NORTH);
	}

	private void setupMainPanel(BubbleShooter bubbleShooter) {
		
		this.getContentPane().add(bubbleShooter,BorderLayout.CENTER);
		
	}

	private void setupBottom(JPanel bottomPanel) {

		bottomPanel.setPreferredSize(new Dimension(1000,150));
		bottomPanel.setBackground(BSColor.trypanBlue);
		this.getContentPane().add(bottomPanel,BorderLayout.SOUTH);
	}

}