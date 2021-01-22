package blackjack;

public class BlackjackGUI {

	public static void main(String[] args) {
		Deck test = new Deck();
		System.out.printf("%s", test.toString());
		System.out.printf("Check if getCard is working: %s %n", test.getCard().toString());
		System.out.printf("Check if getCard is working: %s %n", test.getCard().toString());
		test.shuffle();
		System.out.printf("%s %n %n", test.toString());
		System.out.printf("Check if getCard is working: %s %n", test.getCard().toString());
		System.out.printf("Check if getCard is working: %s %n", test.getCard().toString());
		

	}

}
