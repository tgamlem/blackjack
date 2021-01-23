package blackjack;

import java.util.ArrayList;

public class Game {
	private Deck _deck;
	private ArrayList<Person> _players;
	private Dealer _dealer;
	
	Game(int players){
		_dealer = new Dealer();
		_players = new ArrayList<Person>();
		for(int i = 0; i < players; i ++) {
			_players.add(new Person());
		}
		setHands(players);
	
	}
	
	

//	void playGame(){
//		
//	}
	
	void playerHit(int player){
		Card nextCard = _deck.getCard();
		_players.get(player).addCard(nextCard);

	}
	
	void playerStand(int player) {
		if (_players.get(player).getHand().calcScore() < 22) {
			while(_dealer.play(_players.get(player).getHand().calcScore())) {
				_dealer.addCard(_deck.getCard());
			}
		}
		score();
	}
	
	void score() {
		for(Person p: _players) {
			if(_dealer.getHand().calcScore() > p.getHand().calcScore()) {
				if (_dealer.getHand().calcScore() > 21) {
					p.win();
				} else {
					p.bust();
				}
			} else if (_dealer.getHand().calcScore() < p.getHand().calcScore()) {
				if (p.getHand().calcScore() > 21) {
					p.bust();
				} else {
					p.win();
				}
			} else {
				p.push();
			}
		}
	}
	
	void clearDealerHand() {
		_dealer.clearHand();
	}

	void setBet(int player, int bet) {
		_players.get(player).setBet(bet);
	}

	void setHands(int players) {
		_deck = new Deck();
		for(int i = 0; i < players; i++) {
//			Person player = new Person(_deck.getCard(),_deck.getCard());
			_players.get(i).setHand(new Hand(_deck.getCard(),_deck.getCard()));
//			
		}
//		_dealer = new Dealer(_deck.getCard(),_deck.getCard());
		_dealer.setHand(new Hand(_deck.getCard(), _deck.getCard()));
	}
	
	/**
	 * Takes the specified player and returns an arraylist of that players cards 
	 * @param player
	 * @return
	 */
	ArrayList<String> playersCards(int player){
		ArrayList<String> cardToString = new ArrayList<String>();
		for(Card c: _players.get(player).getHand().getCards()) {
			cardToString.add(c.toString());
		}
		return cardToString;
	}
	
	ArrayList<String> dealersCards(){
		ArrayList<String> cardToString = new ArrayList<String>();
		for(Card c: _dealer.getHand().getCards()) {
			cardToString.add(c.toString());
		}
		return cardToString;
	}
	Person getPlayer(int player) {
		return _players.get(player);
	}
	
	int getDealerScore() {
		return _dealer.getHand().calcScore();
	}

}

