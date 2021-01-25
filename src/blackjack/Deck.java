package blackjack;

import java.util.ArrayList;
import java.util.Random;
/**
 * Deck class which creates 52 cards and shuffles them also provides getters and setters and toString
 * @author zakrispierson
 *
 */
public class Deck {
	private ArrayList<Card> deck;
	private int nextCard;

	/**
	 * Constructor which create 52 cards and shuffles them
	 */
	Deck() {
		deck = new ArrayList<Card>();
		String suit = "";
		Card newCard = null;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if (i == 0) {
					suit = "Spade";
				} else if (i == 1) {
					suit = "Heart";
				} else if (i== 2) {
					suit = "Club";
				} else if(i==3) {
					suit = "Diamond";
				}
				newCard = new Card(suit, (j+1));
				deck.add(newCard);
			}
		}
		shuffle();
		nextCard = 0;

	}
	/**
	 * Shuffles the cards randomly
	 */
	void shuffle() {
		Random rnd = new Random();
		// For help with deck randomization I used
		// https://www.geeksforgeeks.org/shuffle-a-deck-of-cards-3/
		for (int i = 0; i < deck.size(); i++) {
			int j = i + rnd.nextInt(52 - i);
			Card temp = deck.get(j);
			deck.set(j, deck.get(i));
			deck.set(i, temp);

		}
	}
	/**
	 * Returns the next card in the deck
	 * @return Card
	 */
	Card getCard() {
		return deck.get(nextCard++);	
	}
	/**
	 * Prints all the cards in the deck used for debugging
	 */
	public String toString() {
		String details = "Next Card is " + nextCard +"\n";
		for(Card c : deck) {
			details += c.toString() + "\n";
		}
		return details;
	}
};
