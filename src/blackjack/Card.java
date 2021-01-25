package blackjack;
/**
 * The card object contains details of the card like _suit and _value
 *  card objects are used by the Hand class, Person class, and Dealer class.
 * @author zakrispierson
 *
 */
public class Card {
	private String _suit;
	private int _value;
	
	/**
	 * Constructor for a Card object
	 * @param suit String
	 * @param value int
	 */
	Card(String suit, int value){
		_suit = suit;
		_value = value;
	}
	/**
	 * Returns the value of the card as a string
	 * in "suit#" format note for face cards we assigned them integer values
	 */
	public String toString() {
		return _suit + _value;
	}
	/**
	 * Return's the suit of the card as a String
	 * @return _suit 
	 */
	String getSuit() {
		return _suit;
	}
	/**
	 * Returns the card's score.
	 * In BlackJack Ace can be 11 or 1 so we account for this with an if statement
	 * All face cards have a value of 10
	 * @return int
	 */
	int getScore() {
		if(_value > 10) {
			return 10;
		} if(_value == 1) { //account for ace
			return 11;
		}
		return _value;
	}
	/**
	 * returns the integer value of the card this is distinct from its score 
	 * @return int
	 */
	int getValue() {
		
		return _value;
	}
	/**
	 * Set the suit of the card
	 * @param suit
	 */
	void setSuit(String suit) {
		_suit = suit;
	}
	/**
	 * Set the value of the card
	 * @param value
	 */
	void setValue(int value) {
		_value = value;
	}
}
