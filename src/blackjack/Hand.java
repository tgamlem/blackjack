package blackjack;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	private int numCards;

	Hand(){
		hand = new ArrayList<Card>();
		numCards = 0;
	}
	Hand(Card card1, Card card2){
		hand = new ArrayList<Card>();
		hand.add(card1);
		hand.add(card2);
		numCards = 2; //Not zero indexed numCards starts at 1
		
	}
	
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
	
	public String toString() {
		String details = null;
		for(Card c : hand) {
			details += c.toString() + "\n";
		}
		details += "The number of cards in the hand is " + numCards;
		return details;
	}
	
	int getNumCards() {
		return numCards;
	}
	
	void clearHand() {
		hand.clear();
	}
	
	void addCard(Card card) {
		hand.add(card);
		numCards++;
	}
	
	ArrayList <Card> getCards(){
		return hand;
	}

}


