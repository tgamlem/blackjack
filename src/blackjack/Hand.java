package blackjack;

import java.util.ArrayList;
/**
 * A collection of Cards held by the Dealer or Person
 * @author zakrispierson
 *
 */
public class Hand {
	private ArrayList<Card> hand;
	/**
	 * Empty constructor to start with an empty hand
	 */
	Hand(){
		hand = new ArrayList<Card>();
	}
	/**
	 * Hand constructor which starts with two cards both are used by the Person and Dealer classes.
	 * @param card1 Card object
	 * @param card2 Card Object
	 */
	Hand(Card card1, Card card2){
		hand = new ArrayList<Card>();
		hand.add(card1);
		hand.add(card2);		
	}
	
	/**
	 * Calculates the score of the Hand
	 * @return
	 */
	int calcScore() {
		int score = 0;
		for(Card c : hand) {
			//logic to handle ace scoring
			if(score + c.getScore() > 21 && c.getScore() == 11) {
				score += 1;
			} else {
			score += c.getScore();
		
			}
		}
	
		return score;
	}
	/**
	 * Return's the a string of the details of the card useful for debugging not used in our application
	 */
	public String toString() {
		String details = null;
		for(Card c : hand) {
			details += c.toString() + "\n";
		}
		return details;
	}
	
	
	/**
	 * Clears the Hand which is an ArrayList of cards .
	 */
	void clearHand() {
		hand.clear();
	}
	/**
	 * Takes a card and adds it to hand
	 * @param card
	 */
	void addCard(Card card) {
		hand.add(card);
	}
	/**
	 * Returns the hand of cards
	 * @return ArrayList<Card> hand
	 */
	ArrayList <Card> getCards(){
		return hand;
	}

}


