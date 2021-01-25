package blackjack;

/**
 * Dealer class deals with the details of the dealer for blackJack
 * @author zakrispierson
 *
 */
public class Dealer {
	//The Dealer's hand
	private Hand _hand;
	
	/**
	 * Constructor, takes two cards because the dealers starts out with two cards and adds them to _hand
	 * @param card1 Card object
	 * @param card2 Card object
	 */
	Dealer(Card card1, Card card2){
		_hand = new Hand(card1, card2);
	}
	/**
	 * Empty constructor for Dealer
	 */
	Dealer(){
		_hand = new Hand();
	}
	/**
	 * Checks if Dealer has a BlackJack and returns true if they do have a BlackJack
	 * 
	 * @return boolean
	 */
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
	
	/**
	 * takes a card and adds it to _hand
	 * @param card - Card object
	 */
	void addCard(Card card) {
		_hand.addCard(card);
	}
	/**
	 * Return's Dealer's Hand
	 * @return Hand _hand 
	 */
	Hand getHand() {
		return _hand;
	}
	/**
	 * Setter
	 * Takes a Hand object and sets the Dealer's hand as equal to it
	 * @param hand - Hand object
	 */
	void setHand(Hand hand) {
		_hand = hand;
	}
	
	/**
	 * Return's Dealer's Hand's score
	 * @return int DealerScore
	 */
	int calcScore() {
		return _hand.calcScore();
	}
	
	/**
	 * Clear's the hand of the dealer _hand becomes empty
	 */
	void clearHand() {
		_hand.clearHand();
	}

}
