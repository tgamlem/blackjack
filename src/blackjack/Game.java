package blackjack;

import java.util.ArrayList;
/**
 * This object runs the game and provides ways to update the state of Person, and Dealer
 * @author zakrispierson
 *
 */
public class Game {
	private Deck _deck;
	private ArrayList<Person> _players;
	private Dealer _dealer;
	
	/**
	 * Constructor for Game
	 * @param players - takes the number of players
	 */
	Game(int players){
		_dealer = new Dealer();
		_players = new ArrayList<Person>();
		for(int i = 0; i < players; i ++) {
			_players.add(new Person());
		}
		setHands(players); //set hands also sets the dealer's hand
	
	}
	
	
	/**
	 * Takes the number of players and gets a card from the _deck, then adds it to players hand
	 * @param player
	 */
	void playerHit(int player){
		Card nextCard = _deck.getCard();
		_players.get(player).addCard(nextCard);

	}
	/**
	 * takes the number of players and calls _dealer.play()
	 * @param player
	 */
	void playerStand(int player) {
		if (_players.get(player).getHand().calcScore() < 22) { // if player hasn't busted
			while(_dealer.play(_players.get(player).getHand().calcScore())) { //dealer plays
				_dealer.addCard(_deck.getCard()); //if dealer.play returns true the dealer gets another card added to their hand and the loop repeats
			}
		}
		score(); // the dealer is done playing so score is called
	}
	/**
	 * Scoring function
	 * Takes all players and checks their score against the dealers
	 * note we have not implemented multiplayer but this method should work for multiple players too
	 */
	void score() {
		for(Person p: _players) {
			if(_dealer.getHand().calcScore() > p.getHand().calcScore()) { //if dealer's hand is greater than players hand dealer and dealers hand is less than 21 dealer wins 
				if (_dealer.getHand().calcScore() > 21) {
					p.win();
				} else {
					p.bust();
				}
			} else if (_dealer.getHand().calcScore() < p.getHand().calcScore()) { //if player has larger hand and has not busted player wins
				if (p.getHand().calcScore() > 21) {
					p.bust();
				} else {
					p.win();
				}
			} else { //other wise its a push!
				p.push();
			}
		}
	}
	/**
	 * clears delear's hand
	 */
	void clearDealerHand() {
		_dealer.clearHand();
	}
	/**
	 * Set a particular players bet
	 * @param player
	 * @param bet
	 */
	void setBet(int player, int bet) {
		_players.get(player).setBet(bet);
	}
	/**
	 * Set a particular players hand
	 * Also creates a new _deck so this game does not support card counting
	 * @param players
	 */
	void setHands(int players) {
		_deck = new Deck();
		for(int i = 0; i < players; i++) {
			_players.get(i).setHand(new Hand(_deck.getCard(),_deck.getCard()));
		}
		_dealer.setHand(new Hand(_deck.getCard(), _deck.getCard()));
	}
	
	/**
	 * Takes the specified player and returns an ArrayList of that players cards 
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
	/**
	 * Returns an Arraylist<String> of the dealer's Cards
	 * used to get the file names of the cards in question
	 * @return
	 */
	ArrayList<String> dealersCards(){
		ArrayList<String> cardToString = new ArrayList<String>();
		for(Card c: _dealer.getHand().getCards()) {
			cardToString.add(c.toString());
		}
		return cardToString;
	}
	/**
	 * Returns a given players person object
	 * @param player
	 * @return
	 */
	Person getPlayer(int player) {
		return _players.get(player);
	}
	/**
	 * returns the dealer's score
	 * @return
	 */
	int getDealerScore() {
		return _dealer.getHand().calcScore();
	}

}

