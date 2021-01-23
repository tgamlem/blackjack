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
	
	ArrayList<JLabel> playerCards = new ArrayList<JLabel>();
	ArrayList<JLabel> dealerCards = new ArrayList<JLabel>();
	
	Image cardBackImg =  Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/backCover.png"));
	JLabel cardBack = new JLabel();
		
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
		int xPos = 50 * (cards.size() - 2);
		
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel();
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			card = new JLabel();
			card.setBounds(590 + (i * 120) - xPos, 380, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			panel.add(card);
			playerCards.add(card);
		}
		
	}
	
	private void removeCards(ArrayList<JLabel> cards, int numCards) {
		int i = 0;
		while (i < numCards - 1) {
			cards.get(0).setVisible(false);
			cards.remove(0);
			i++;
		}
			
	}
	
	private void setDealerCards() {
		Image cardImg;
		Border brd = BorderFactory.createLineBorder(Color.black, 2);
		
		ArrayList<String> cards = game.dealersCards();
		int xPos = 50 * (cards.size() - 2);
		
		cardBackImg = cardBackImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
		cardBack.setBounds(590, 170, 100, 130);
		cardBack.setBorder(brd);
		cardBack.setIcon(new ImageIcon(cardBackImg));
		panel.add(cardBack);
		
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel();
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			card = new JLabel();
			card.setBounds(590 + (i * 120) - xPos, 170, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			panel.add(card);
			dealerCards.add(card);
		}
		
	}
	
	private void setPlayerScore() {
		Person p = game.getPlayer(0);
		
		String pScore = "Player Score: " + p.getHand().calcScore();
		
		playerScore.setForeground(Color.WHITE);
		playerScore.setText(pScore);
		playerScore.setBounds(595, 571, 237, 51);
		playerScore.setFont(font);
		
		panel.add(playerScore);
	}
	
	private void setDealerScore() {
		int d = game.getDealerScore();
		
		String dScore = "Dealer Score: " + d;
		
		dealerScore.setForeground(Color.WHITE);
		dealerScore.setText(dScore);
		dealerScore.setBounds(595, 60, 224, 51);
		dealerScore.setFont(font);
		panel.add(dealerScore);
	}
	
	private void setWinLoss() {
		// call getter functions
		Person p1 = game.getPlayer(0);
		
		String w = "Wins: " + p1.getWins();
		String p = "Push: " + p1.getPushes();
		String l = "Losses: " + p1.getLoses();
		
		wins.setForeground(Color.WHITE);
		wins.setText(w);
		pushes.setForeground(Color.white);
		pushes.setText(p);
		losses.setForeground(Color.WHITE);
		losses.setText(l);
		
		wins.setBounds(82, 79, 135, 57);
		wins.setFont(font);
		pushes.setBounds(82, 134, 135, 57);
		pushes.setFont(font);
		losses.setBounds(82, 194, 135, 57);
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
				game.playerHit(0);
				removeCards(playerCards, game.getPlayer(0).getHand().getCards().size());
				setPlayerCards();
				setPlayerScore();
				setBackground();
			}
		});
		hitBtn.addMouseMotionListener(this);
		hitBtn.setBounds(541, 644, 142, 51);
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
				game.playerStand(0);
				cardBack.setVisible(false);
				setPlayerCards();
				removeCards(dealerCards, game.dealersCards().size());
				setDealerCards();
				setDealerScore();
				setWinLoss();
				setBalance();
				setBackground();
			}
		});
		standBtn.addMouseMotionListener(this);
		standBtn.setBounds(737, 644, 142, 51);
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
				String b = enterBet.getText();
				int bet = Integer.parseInt(b);
				game.setBet(0, bet);
				setBalance();
				setPlayerCards();
				setDealerCards();
				setPlayerScore();
				setHitBtn();
				setStandBtn();
				//TODO: figure out how to do this without having to call setBackground() again
				setBackground();
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
		Person p = game.getPlayer(0);
		String be = "Bet: $" + p.getBet();
		String ba = "Balance: $" + p.getMoney();
		bet.setText(be);
		balance.setForeground(Color.WHITE);
		balance.setText(ba);
		
		bet.setBounds(82, 414, 165, 44);
		bet.setFont(font);
		balance.setBounds(82, 468, 237, 44);
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
