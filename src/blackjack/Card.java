package blackjack;

public class Card {
	private String _suit;
	private int _value;
	
	/**
	 * Constructor for a Card object
	 * @param suit
	 * @param value
	 */
	Card(String suit, int value){
		_suit = suit;
		_value = value;
	}
	
	public String toString() {
		return _suit + " "+ _value;
	}
	
	String getSuit() {
		return _suit;
	}
	
	int getScore() {
		if(_value > 10) {
			return 10;
		} if(_value == 1) { //account for ace
			return 11;
		}
		return _value;
	}
	
	int getValue() {
		
		return _value;
	}
	
	void setSuit(String suit) {
		_suit = suit;
	}
	
	void setValue(int value) {
		_value = value;
	}
}
