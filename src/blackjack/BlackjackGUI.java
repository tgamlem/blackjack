package blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackjackGUI implements MouseMotionListener {
	// window for the GUI
	JFrame frame = new JFrame("Blackjack");
	// panel to display content within
	JPanel panel = new JPanel();
	// background of the panel
	JLabel background = new JLabel();
	// text area for player to enter their bet
	JTextArea enterBet = new JTextArea();
	// buttons to play the game
	JButton enterBetBtn = new JButton("Enter Bet");
	JButton hitBtn = new JButton("Hit");
	JButton standBtn = new JButton("Stand");
	// default font
	Font font = new Font("Georgia", Font.PLAIN, 30);
	
	JLabel playerScore = new JLabel();
	JLabel dealerScore = new JLabel();
	
	
	public BlackjackGUI() {
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame.setBounds(120, 20, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.addMouseMotionListener(this);
		panel.setLayout(null);
		
		setBet();
		setHitBtn();
		setStandBtn();
		setScores();
		setBackground();
	}
	
	private void setBackground() {
		Image backgroundImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/background.png"));
		backgroundImg = backgroundImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		background.setForeground(Color.WHITE);
		background.setIcon(new ImageIcon(backgroundImg));
		background.setBounds(0, 0, 1200, 800);
		panel.add(background);
	}
	
	private void setScores() {
		dealerScore.setForeground(Color.WHITE);
		// call dealer/player score methods
		dealerScore.setText("Dealer Score: ");
		playerScore.setForeground(Color.WHITE);
		playerScore.setText("Player Score: ");
		
		dealerScore.setBounds(456, 39, 224, 51);
		dealerScore.setFont(font);
		playerScore.setBounds(456, 582, 237, 51);
		playerScore.setFont(font);
		
		panel.add(dealerScore);
		panel.add(playerScore);
	}
	
	private void setHitBtn() {
		hitBtn.setForeground(Color.WHITE);
		hitBtn.setBackground(Color.BLACK);
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		hitBtn.addMouseMotionListener(this);
		hitBtn.setBounds(408, 643, 142, 51);
		hitBtn.setFont(font);
		panel.add(hitBtn);
	}
	
	private void setStandBtn() {
		standBtn.setForeground(Color.WHITE);
		standBtn.setBackground(Color.BLACK);
		standBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		standBtn.addMouseMotionListener(this);
		standBtn.setBounds(604, 643, 142, 51);
		standBtn.setFont(font);
		panel.add(standBtn);
	}
	
	private void setBet() {
		enterBet.setEditable(true);
		enterBet.setWrapStyleWord(true);
		enterBet.setBounds(61, 546, 170, 39);
		panel.add(enterBet);
		enterBet.setLineWrap(true);
		enterBet.setFont(font);
		enterBetBtn.setForeground(Color.WHITE);
		
		enterBetBtn.setBackground(Color.BLACK);
		enterBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		enterBetBtn.addMouseMotionListener(this);
		enterBetBtn.setBounds(61, 614, 170, 51);
		enterBetBtn.setFont(font);
		panel.add(enterBetBtn);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		try {
			BlackjackGUI game = new BlackjackGUI();
		}
		catch (Exception ex) {;}
	}

}
