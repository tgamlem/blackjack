package blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
/**
 *  a graphical user interface for the game of blackjack
 * @author zakrispierson and TylerGamlem
 *
 */
public class BlackjackGUI implements MouseMotionListener {
	// game object to keep track of all objects for blackjack instance
	Game game = new Game(1);
	
	// window for the GUI
	JFrame frame = new JFrame("Blackjack");
	// panel to display content within
	JPanel panel = new JPanel();
	// background of the panel
	JLabel background;
	// text area for player to enter their bet
	JTextArea enterBet;
	// buttons to play the game
	JButton enterBetBtn;
	JButton hitBtn;
	JButton standBtn;
	// default font
	Font font;
	// labels to hold the text score of the dealer and player
	JLabel playerScore;
	JLabel dealerScore;
	// labels to show how much money a player has and how much their bet is
	JLabel bet;
	JLabel balance;
	// labels to display wins and losses
	JLabel wins;
	JLabel pushes;
	JLabel losses;
	
	// lists of jlabel cards to keep track of what cards need to be printed and removed from the screen
	ArrayList<JLabel> playerCards = new ArrayList<JLabel>();
	ArrayList<JLabel> dealerCards = new ArrayList<JLabel>();
	
	// back side of a card
	Image cardBackImg =  Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/backCover.png"));
	JLabel cardBack = new JLabel();
		/**
		 * Initialize game
		 */
	public BlackjackGUI() {
		initialize();
	}
	/**
	 * Call this method to show UI
	 */
	public void showUI() {
		frame.setVisible(true);

	}
	
	/**
	 * initVars initializes the JLabels, JTextArea, JButtons, Image, and ArrayLists to new variables
	 * This is called whenever a new instance of game is created, or a new round of blackjack is played
	 */
	private void initVars() {
		// background of the panel
		background = new JLabel();
		
		// text area for player to enter their bet
		enterBet = new JTextArea();
		enterBet.setText("");
		
		// buttons to play the game
		enterBetBtn = new JButton("Enter Bet");
		hitBtn = new JButton("Hit");
		standBtn = new JButton("Stand");
		
		// default font
		font = new Font("Georgia", Font.PLAIN, 28);
		
		// labels to hold the text score of the dealer and player
		playerScore = new JLabel();
		dealerScore = new JLabel();
		
		// labels to show how much money a player has and how much their bet is
		bet = new JLabel();
		balance = new JLabel();
		
		// labels to display wins and losses
		wins = new JLabel();
		pushes = new JLabel();
		losses = new JLabel();
		
		// lists of jlabel cards to keep track of what cards need to be printed and removed from the screen
		playerCards = new ArrayList<JLabel>();
		dealerCards = new ArrayList<JLabel>();
		
		// back side of a card
		cardBackImg =  Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/backCover.png"));
		cardBack = new JLabel();
	}
	
	/**
	 * initial function called when a BlackjackGUI is made. this initializes the JFrame, JPanel, and calls functions to 
	 * display the proper objects
	 */
	private void initialize() {
		initVars(); // initialize all variables
		
		// set up the frame and panel
		frame.setBounds(120, 20, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.addMouseMotionListener(this);
		panel.setLayout(null);
		
		setBet(); // display enter bet button and text area
		setBalance(); // display bank info
		setWinLoss(); // display win loss record
		setBackground(); // set the background to look like a casino table
	}
	
	/**
	 * initialize function that gets called when someone wants to play another hand of blackjack
	 * resets player/dealer hands, prints proper objects to the GUI
	 */
	private void reinitialize() {
		game.setHands(1); // set new hand for player and dealer
		
		// reset hands
		playerCards.clear();
		dealerCards.clear();
		
		initVars(); // initialize objects to be new objects
		
		setBet(); // display enter bet button and text area
		setBalance(); // display bank info
		setWinLoss(); // display win loss record
		setBackground(); // set the background to look like a casino table
	}
	
	/**
	 * remove everything from the JPanel
	 * gets called when reinitializing the window
	 */
	private void clearScreen() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * displays a dialog box to say whether a player has won, lost, or tied
	 * Gives the option to play again or restart
	 * @param message the string that gets displayed in the dialog box
	 */
	private void winLossDialog(String message) {
		Object[] options = {"Play Again", "Restart"}; // option labels to be displayed on buttons
		// push the dialog and record the input
		int decision = JOptionPane.showOptionDialog(null, message, "Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(cardBackImg), options, options[1]);

		if(decision == 0) { // Play again
			//erase screen and reinitialize gui
			clearScreen();
			reinitialize();
		} else if(decision == 1 || decision == -1) { // Restart or X button
			//erase screen and reinitialize gui and game
			clearScreen();
			game = new Game(1);
			reinitialize();
		} else { // Exception for anything else
			System.out.printf("something went wrong! %n decision value %d", decision);
		}
	}
	
	/**
	 * dialog box that tells a player they have lost and they are out of money
	 * only allows for the game to be reset
	 * @param message the string that gets displayed in the dialog box
	 */
	private void outOfMoney(String message) {
		Object[] options = {"Restart"}; // only option is restart
		// make the dialog
		JOptionPane.showOptionDialog(null, message, "Please Restart", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(cardBackImg), options, options[0]);
		
		// erase the screen, reinitialize the GUI and the game
		clearScreen();
		game = new Game(1);
		reinitialize();
	}
	
	/**
	 * dialog box that tells a player to enter a valid bet
	 * @param message the string to be displayed in the dialog box
	 */
	private void enterRealBet(String message) {
		Object[] options = {"Try Again"}; // try again is the only option
		
		// call the dialog box to display
		JOptionPane.showOptionDialog(null, message, "Enter Valid Bet", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(cardBackImg), options, options[0]);
		
		// reset the text area to empty
		enterBet.setText("");
	}
	
	/**
	 * display the background image on the screen
	 */
	private void setBackground() {
		// make the background image
		Image backgroundImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource("/background.png"));
		backgroundImg = backgroundImg.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		background.setForeground(Color.WHITE);
		background.setIcon(new ImageIcon(backgroundImg));
		background.setBounds(0, 0, 1200, 800); // set the size of the background to match the window
		panel.add(background); // add the background to the panel
	}
	
	/**
	 * display a player's cards on the screen
	 */
	private void setPlayerCards() {
		Image cardImg; // image to hold the correct image for each card
		Border brd = BorderFactory.createLineBorder(Color.black, 2); // border to make cards look nicer
		ArrayList<String> cards = game.playersCards(0); // get the player's cards from game
		int xPos = 50 * (cards.size() - 2); // var that tells how far left to move based on how many cards a player has
											// allows for cards to remain centered over hit and stand buttons
		
		// loop for as many cards as we have
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel(); // initialize card to a new JLabel
			
			// create the file path to the correct image
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			
			// make the card image
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			
			// set the position of the card
			card.setBounds(590 + (i * 120) - xPos, 380, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			
			// add the card to the list of player cards and to the panel
			playerCards.add(card);
			panel.add(playerCards.get(i));
		}
	}
	
	/**
	 * remove cards from the screen
	 * @param cards arraylist of JLabel cards
	 */
	private void removeCards(ArrayList<JLabel> cards) {
		int i = 0;
		int numCards = cards.size();
		while (i < numCards) {
			// remove all cards that have been printed to the screen
			cards.get(0).setVisible(false);
			panel.remove(cards.get(0)); // remove from the panel
			cards.remove(0); // remove from the arraylist
			i++;
		}
	}
	
	/**
	 * display the dealer's cards to the screen
	 */
	private void setDealerCards() {
		Image cardImg; // the image of each card
		Border brd = BorderFactory.createLineBorder(Color.black, 2); // border to print around each card
		
		ArrayList<String> cards = game.dealersCards(); // get the dealer's cards from game
		int xPos = 50 * (cards.size() - 2); // decide where the cards need to be printed
		
		// place the back side of the card over top of one of the dealer's cards
		cardBackImg = cardBackImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
		cardBack.setBounds(590, 170, 100, 130);
		cardBack.setBorder(brd);
		cardBack.setIcon(new ImageIcon(cardBackImg));
		panel.add(cardBack);
		
		// for all the cards in the dealer's hand
		for (int i = 0; i < cards.size(); i++) {
			JLabel card = new JLabel(); // make a new JLabel
			
			// determine the file path for the correct image
			String cardPath = "/";
			cardPath += cards.get(i) + ".png";
			
			// make the image and place it on the card
			cardImg = Toolkit.getDefaultToolkit().getImage(BlackjackGUI.class.getResource(cardPath));
			cardImg = cardImg.getScaledInstance(100, 130, Image.SCALE_SMOOTH);
			
			// set set the coordinates of each card
			card.setBounds(590 + (i * 120) - xPos, 170, 100, 130);
			card.setBorder(brd);
			card.setIcon(new ImageIcon(cardImg));
			
			dealerCards.add(card); // add the card to the dealer's arraylist
			panel.add(dealerCards.get(i)); // add the card to the panel
		}
	}
	
	/**
	 * display a player's score on the screen
	 */
	private void setPlayerScore() {
		// get the player
		Person p = game.getPlayer(0);
		
		// set a string to hold their score
		String pScore = "Player Score: " + p.getHand().calcScore();
		
		// set text and style
		playerScore.setForeground(Color.WHITE);
		playerScore.setText(pScore);
		
		// set the coordinates
		playerScore.setBounds(595, 571, 237, 51);
		playerScore.setFont(font);
		
		// add the score to the panel
		panel.add(playerScore);
	}
	
	/**
	 * display a dealer's score on the screen
	 */
	private void setDealerScore() {
		// get the dealer's score from the game
		int d = game.getDealerScore();
		
		// make a string holding the score
		String dScore = "Dealer Score: " + d;
		
		// set text and style
		dealerScore.setForeground(Color.WHITE);
		dealerScore.setText(dScore);
		dealerScore.setBounds(595, 60, 224, 51); // set the coordinates
		dealerScore.setFont(font);
		
		// add the score to the panel
		panel.add(dealerScore);
	}
	
	/**
	 * display the win/loss record of a player on the screen
	 */
	private void setWinLoss() {
		// get the player
		Person p1 = game.getPlayer(0);
		
		// get their win/loss record
		String w = "Wins: " + p1.getWins();
		String p = "Push: " + p1.getPushes();
		String l = "Losses: " + p1.getLoses();
		
		// set text and style
		wins.setForeground(Color.WHITE);
		wins.setText(w);
		pushes.setForeground(Color.white);
		pushes.setText(p);
		losses.setForeground(Color.WHITE);
		losses.setText(l);
		
		// set coordinates on the screen
		wins.setBounds(82, 79, 135, 57);
		wins.setFont(font);
		pushes.setBounds(82, 134, 135, 57);
		pushes.setFont(font);
		losses.setBounds(82, 194, 135, 57);
		losses.setFont(font);
		
		// add them to the panel
		panel.add(wins);
		panel.add(pushes);
		panel.add(losses);
	}
	
	/**
	 * display the hit button on the screen
	 * set functionality to call game methods when the button is pressed
	 */
	private void setHitBtn() {
		// set what the button looks like
		hitBtn.setForeground(Color.WHITE);
		hitBtn.setBackground(Color.BLACK);
		hitBtn.setOpaque(true);
		hitBtn.setBorderPainted(false);
		
		// set logic for when the button is pressed
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.playerHit(0); // call the game's playerHit method
				removeCards(playerCards); // remove the printed cards from the screen
				
				// display updated hand and score
				setPlayerCards();
				setPlayerScore();
				
				// check to see if the player has busted
				if (game.getPlayer(0).getHand().calcScore() > 21) {
					// display the dealer's cards and score
					setDealerCards();
					setDealerScore();
					
					// call game logic to reset the dealer hand and add a loss to the player who busts
					game.clearDealerHand();
					game.playerStand(0);
					
					// display the win loss record
					setWinLoss();
					
					// make the cover over the dealer's card disappear
					cardBack.setVisible(false);
					setBackground();
					
					// check to make sure a player still has money
					if (game.getPlayer(0).getMoney() == 0) {
						// if out of money, call that dialog
						outOfMoney("Bust! You are out of money!");
					} else {
						// call the normal dialog if they still have money
						winLossDialog("You Bust!");
					}
				// if the player has a blackjack, auto stand for them
				} else if (game.getPlayer(0).getHand().calcScore() == 21) {
					stand();
				}
				setBackground();
			}
		});
		
		// allow the button to be pressed
		hitBtn.addMouseMotionListener(this);
		
		// set screen location and font
		hitBtn.setBounds(541, 644, 142, 51);
		hitBtn.setFont(font);
		
		// add it to the panel
		panel.add(hitBtn);
	}
	
	/**
	 * display the stand button
	 * set up logic for what happens when the button is pressed
	 */
	private void setStandBtn() {
		// set what the button looks like
		standBtn.setForeground(Color.WHITE);
		standBtn.setBackground(Color.BLACK);
		standBtn.setOpaque(true); //code necessary for buttons to show up on mac
		standBtn.setBorderPainted(false); //https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
		
		// allow for the button to be pressed
		standBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stand(); // call the stand function when the button is pressed
			}
		});
		
		// allow for the button to be pressed
		standBtn.addMouseMotionListener(this);
		
		// set screen location and font
		standBtn.setBounds(737, 644, 142, 51);
		standBtn.setFont(font);
		
		// add it to the panel
		panel.add(standBtn);
	}
	
	/**
	 * stand calls all the proper game logic and updates the screen display to show end game logic
	 */
	private void stand() {
		int pScore = game.getPlayer(0).getHand().calcScore(); // a player's score
		boolean moneyFlag = false; // a flag to determine if the player is out of money
		game.playerStand(0); // game logic for standing
		cardBack.setVisible(false); // make the back side of the card invisible
		setPlayerCards(); // display the player's cards
		
		// display the dealer's cards and score
		removeCards(dealerCards);
		setDealerCards();
		setDealerScore();
		
		String dialog = ""; // message for the dialog box
		
		// if the dealer busts
		if (game.getDealerScore() > 21) {
			dialog = "You Win!";
		// if the dealer wins
		} else if (game.getDealerScore() > pScore && game.getDealerScore() < 22) {
			if (game.getPlayer(0).getMoney() == 0) {
				dialog = "You are out of money!";
				moneyFlag = true;
			} else {
				dialog = "You Lost!";
			}
		// if there is a tie
		} else if (game.getDealerScore() == pScore) {
			dialog = "Push!";
		// otherwise the player wins
		} else {
			dialog = "You Win!";
		}
		
		// clear dealer hand here
		game.clearDealerHand();
		
		// update display for win loss, balance, and background
		setWinLoss();
		setBalance();
		setBackground();
		
		// call the proper dialog based on if the player has money left
		if (moneyFlag) {
			outOfMoney(dialog);
		} else {
			winLossDialog(dialog);
		}
	}
	
	/**
	 * display the text area for the bet and the button to set your bet
	 */
	private void setBet() {
		// set up the text area and add it to the screen
		enterBet.setEditable(true);
		enterBet.setWrapStyleWord(true);
		enterBet.setBounds(82, 549, 170, 39);
		panel.add(enterBet);
		enterBet.setLineWrap(true);
		enterBet.setFont(font);
		enterBetBtn.setForeground(Color.WHITE);
		
		// set the display of the button
		enterBetBtn.setForeground(Color.WHITE);
		enterBetBtn.setBackground(Color.BLACK);
		enterBetBtn.setOpaque(true);
		enterBetBtn.setBorderPainted(false);
		// logic for what the button does when it is pressed
		enterBetBtn.addActionListener(new ActionListener() {
			int buttonClicks =0;
			public void actionPerformed(ActionEvent arg0) {
				//System.out.printf();
				if(buttonClicks < 1) {
				// get the text from the text area and parse it for an integer
				String b = enterBet.getText();
				// replace all non numbers with nothing, found at the link below
				b = b.replaceAll("[^0-9]", ""); // https://attacomsian.com/blog/java-extract-digits-from-string
				
				// check to make sure there is actually an integer amount in the bet
				// any string that does not have an integer in it will be "" by now since we remove all non integer values
				if (b.equals("")) {
					// call dialog that prompts user to enter a valid input
					enterRealBet("Please enter an integer value for your bet");
				} else {
					int bet = Integer.parseInt(b);
					// set the bet that has been entered
					game.setBet(0, bet);
					
					// update the display to allow for the game to be played
					setBalance();
					setPlayerCards();
					setDealerCards();
					setPlayerScore();
					setHitBtn();
					setStandBtn();
					setBackground();
					buttonClicks++;
					
				}
				}
			}
		});
		
		// allow the button to be pressed
		enterBetBtn.addMouseMotionListener(this);
		
		// set the coordinates of the screen and the font
		enterBetBtn.setBounds(82, 614, 170, 51);
		enterBetBtn.setFont(font);
		
		// add to the panel
		panel.add(enterBetBtn);
	}
	
	/**
	 * display the bet and balance amounts to the screen
	 */
	private void setBalance() {
		// get the player and their bet/balance
		Person p = game.getPlayer(0);
		String be = "Bet: $" + p.getBet();
		String ba = "Balance: $" + p.getMoney();
		
		// set bet and balance amounts
		bet.setText(be);
		bet.setForeground(Color.WHITE);
		balance.setForeground(Color.WHITE);
		balance.setText(ba);
		
		// set the font and screen position
		bet.setBounds(82, 414, 165, 44);
		bet.setFont(font);
		balance.setBounds(82, 468, 237, 44);
		balance.setFont(font);
		
		// display them on the screen
		panel.add(bet);
		panel.add(balance);
	}
	
	/**
	 * check if the mouse has dragged something
	 */
	public void mouseDragged(MouseEvent e) {}
	
	/**
	 * check if the mouse has moved something
	 */
	public void mouseMoved(MouseEvent e) {}
	
	/**
	 * Run game
	 * @param args main's parameters
	 */
	public static void main(String[] args) {
		try {
			BlackjackGUI game = new BlackjackGUI(); // GUI instance
			game.showUI();
		}
		catch (Exception ex) {;}
		
	}

}
