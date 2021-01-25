package blackjack;

public class Person {
	private int _money;
	private int _bet;
	private Hand _hand;
	private int _wins;
	private int _pushes;
	private int _loses;

	/**
	 * Constructor which takes two cards and creates a new Hand with those cards sets everything else to 0 except _money
	 * @param card1
	 * @param card2
	 */
	Person(Card card1, Card card2) {
		_money = 50; // Start with 50 dollars
		_bet = 0;
		_hand = new Hand(card1, card2); // for now a person starts with an empty deck this way everyone can get a card
							// at the same time
		_wins = 0;
		_pushes = 0;
		_loses = 0;
	}
	/**
	 * Empty constructor
	 */
	Person(){
		_money = 50;
		_bet = 0;
		
		_hand = new Hand();
		_wins = 0;
		_pushes = 0;
		_loses = 0;
		
	}
	/**
	 * Returns the amount of money as an integer
	 * @return int
	 */
	int getMoney() {
		return _money;
	}
	/**
	 * Call bust() when person busts
	 * increments losses and clears the person's hand
	 * set _bet = 0
	 */
	void bust() {
		_loses++;
		//_money -= _bet;
		clearHand();
		_bet = 0;
	}
	/**
	 * Call win() when the player wins
	 * doubles the bet and adds it to _money
	 * increments _wins
	 * clears hand
	 * sets _bet = 0
	 */
	void win() {
		_money += _bet * 2;
		_wins++;
		clearHand();
		_bet = 0;
	}
	/**
	 * Remove from game is to be implemented for multiplayer
	 * used if one player loses all their money but the other doesn't
	 */
	void removeFromGame() {
		// remove from game
		//only for multi-player
	}
	/**
	 * Updates person object for push much like win and bust methods
	 */
	void push() {
		// push logic here
		_pushes++;
		_money += _bet;
		clearHand();
		_bet = 0;
	}
	/**
	 * Clears the hand of the person
	 */
	public void clearHand() {
		_hand.clearHand();

	}
	/**
	 * returns the person's hand
	 * @return
	 */
	Hand getHand() {
		return _hand;
	}
	/**
	 * Return's the person's bet
	 * @return int -the bet
	 */
	int getBet() {
		return _bet;
	}
	/**
	 * returns the number of _loses
	 * @return int
	 */
	int getLoses() {return _loses;}
	/**
	 * returns the number of wins
	 * @return
	 */
	int getWins() {return _wins;}
	/**
	 * returns the number of ties/number of times a round has reuslted in a push
	 * @return
	 */
	int getPushes() {return _pushes;}
	/**
	 * Set bet takes a bet and makes sure it is a valid bet and sets it as the bet and also removes it from money
	 * @param bet
	 */
	void setBet(int bet) {
		if (bet > _money) { // all in if you bet more than you have
			_bet = _money;
			_money = 0;
			
		}
		else if (bet < 1) {
			_bet = 1;
			_money -= _bet;
		}
		else {
			_bet = bet;
			_money -= bet;
		}
	}
	/**
	 * Add a Card to person's hand
	 * @param card
	 */
	void addCard(Card card) {
		_hand.addCard(card);
	}
	/**
	 * Set a new Hand with this setter
	 * @param hand
	 */
	void setHand(Hand hand) {
		_hand = hand;
	}

}
