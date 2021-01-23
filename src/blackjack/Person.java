package blackjack;

public class Person {
	private int _money;
	private int _bet;
	private Hand _hand;
	private int _wins;
	private int _pushes;
	private int _loses;

	Person(Card card1, Card card2) {
		_money = 50; // Start with 50 dollars
		_bet = 0;
		_hand = new Hand(card1, card2); // for now a person starts with an empty deck this way everyone can get a card
							// at the same time
		_wins = 0;
		_pushes = 0;
		_loses = 0;
	}
	Person(){
		_money = 50;
		_bet = 0;
		
		_hand = new Hand();
		_wins = 0;
		_pushes = 0;
		_loses = 0;
		
	}

	int getMoney() {
		return _money;
	}

	void bust() {
		// bust logic here
		_loses++;
		//_money -= _bet;
		clearHand();
		_bet = 0;
	}

	void win() {
		// win logic
		_money += _bet * 2;
		_wins++;
		clearHand();
		_bet = 0;
	}

	void removeFromGame() {
		// remove from game
		//only for multi-player
	}

	void push() {
		// push logic here
		_pushes++;
		_money += _bet;
		clearHand();
		_bet = 0;
	}

	public void clearHand() {
		_hand.clearHand();

	}

	Hand getHand() {
		return _hand;
	}

	int getBet() {
		return _bet;
	}
	
	int getLoses() {return _loses;}
	
	int getWins() {return _wins;}
	
	int getPushes() {return _pushes;}

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
	
	void addCard(Card card) {
		_hand.addCard(card);
	}
	
	void setHand(Hand hand) {
		_hand = hand;
	}

}
