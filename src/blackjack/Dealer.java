package blackjack;


public class Dealer {
	
	private Hand _hand;
	
	Dealer(Card card1, Card card2){
		_hand = new Hand(card1, card2);
	}
	Dealer(){
		_hand = new Hand();
	}
	
	boolean isBlackJack() {
		if(_hand.calcScore() == 21) {
			return true;
		}
		return false;
		
	}
	/**
	 * returns true if dealer asks for card
	 * false if dealer doesn't need card
	 * @param playerScore
	 * @return
	 */
	boolean play(int playerScore) {
		if(_hand.calcScore() <= playerScore && _hand.calcScore() < 17) {
			return true;
		}
		return false;
	}
	
	void addCard(Card card) {
		_hand.addCard(card);
	}
	
	Hand getHand() {
		return _hand;
	}
	
	void setHand(Hand hand) {
		_hand = hand;
	}
	
	
	int calcScore() {
		return _hand.calcScore();
	}
	
	void clearHand() {
		_hand.clearHand();
	}

}
