package blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;

public class BlackjackGUI implements MouseMotionListener {
	Game game = new Game(1);
	
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
	// labels to hold the text score of the dealer and player
	JLabel playerScore = new JLabel();
	JLabel dealerScore = new JLabel();
	// labels to show how much money a player has and how much their bet is
	JLabel bet = new JLabel();
	JLabel balance = new JLabel();
	JLabel wins = new JLabel();
	JLabel pushes = new JLabel();
	JLabel losses = new JLabel();
	
	Image cardBackImg =  Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/backCover.png"));
	
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
		
		setPlayerCards();
		setDealerCards();
		setBet();
		setHitBtn();
		setStandBtn();
		setScores();
		setBalance();
		setWinLoss();
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
	
	private void setPlayerCards() {
		Image cardImg;
		Border brd = BorderFactory.createLineBorder(Color.black, 2);
		ArrayList<String> cards = game.playersCards(0);
		
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel();
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			card = new JLabel();
			card.setBounds(450 + (i * 120), 380, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			panel.add(card);
		}
		
	}
	
	private void setDealerCards() {
		Image cardImg;
		Border brd = BorderFactory.createLineBorder(Color.black, 2);
		JLabel cardBack = new JLabel();
		
		ArrayList<String> cards = game.dealersCards();
		
		cardBackImg = cardBackImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
		cardBack.setBounds(450, 170, 100, 130);
		cardBack.setBorder(brd);
		cardBack.setIcon(new ImageIcon(cardBackImg));
		//cardBack.setVisible(false);
		panel.add(cardBack);
		
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel();
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			card = new JLabel();
			card.setBounds(450 + (i * 120), 170, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			panel.add(card);
		}
		
	}
	
	private void setScores() {
		// call dealer/player score methods
		// add Bust! to a losing score
		Person p = game.getPlayer(0);
		int d = game.getDealerScore();
		
		String pScore = "Player Score: " + p.getHand().calcScore();
		String dScore = "Dealer Score: " + d;
		
		dealerScore.setForeground(Color.WHITE);
		dealerScore.setText(dScore);
		playerScore.setForeground(Color.WHITE);
		playerScore.setText(pScore);
		
		dealerScore.setBounds(462, 40, 224, 51);
		dealerScore.setFont(font);
		playerScore.setBounds(462, 570, 237, 51);
		playerScore.setFont(font);
		
		dealerScore.setVisible(false);
		
		panel.add(dealerScore);
		panel.add(playerScore);
	}
	
	private void setWinLoss() {
		// call getter functions
		Person p1 = game.getPlayer(0);
		
		String w = "Wins: " + p1.getWins();
		String p = "Push: " + p1.getPushes();
		String l = "Busts: " + p1.getLoses();
		
		wins.setForeground(Color.WHITE);
		wins.setText(w);
		pushes.setForeground(Color.white);
		pushes.setText(p);
		losses.setForeground(Color.WHITE);
		losses.setText(l);
		
		wins.setBounds(851, 112, 135, 57);
		wins.setFont(font);
		pushes.setBounds(851, 167, 135, 57);
		pushes.setFont(font);
		losses.setBounds(851, 227, 135, 57);
		losses.setFont(font);
		
		panel.add(wins);
		panel.add(pushes);
		panel.add(losses);
	}
	
	private void setHitBtn() {
		hitBtn.setForeground(Color.WHITE);
		hitBtn.setBackground(Color.BLACK);
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// add another card
				// calculate player score
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
				// calculate player and dealer score
				// determine who won
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
		enterBet.setBounds(82, 549, 170, 39);
		panel.add(enterBet);
		enterBet.setLineWrap(true);
		enterBet.setFont(font);
		enterBetBtn.setForeground(Color.WHITE);
		
		enterBetBtn.setBackground(Color.BLACK);
		enterBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// use Player.setBet()
			}
		});
		enterBetBtn.addMouseMotionListener(this);
		enterBetBtn.setBounds(82, 614, 170, 51);
		enterBetBtn.setFont(font);
		panel.add(enterBetBtn);
	}
	
	private void setBalance() {
		bet.setForeground(Color.WHITE);
		// use getBalance and getBet from Person
		bet.setText("Bet: $");
		balance.setForeground(Color.WHITE);
		balance.setText("Balance: $");
		
		bet.setBounds(851, 563, 165, 44);
		bet.setFont(font);
		balance.setBounds(851, 617, 237, 44);
		balance.setFont(font);
		
		panel.add(bet);
		panel.add(balance);
	}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	
	public static void main(String[] args) {
		try {
			BlackjackGUI game = new BlackjackGUI();
		}
		catch (Exception ex) {;}

	}

}
