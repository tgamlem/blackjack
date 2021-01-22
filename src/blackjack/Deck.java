package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deck;
	private int nextCard;

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

	Card getCard() {
		return deck.get(nextCard++);	
	}
	
	public String toString() {
		String details = "Next Card is " + nextCard +"\n";
		for(Card c : deck) {
			details += c.toString() + "\n";
		}
		return details;
	}
};
