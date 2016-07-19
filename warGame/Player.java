package warGame;

import java.util.Scanner;

public class Player {
	
	private final String name;
	private final  int age;
	private final String sex;
	private Player opponent;
	private Cards cards;
	private int hand[] = new int[26]; 
	private int wonCards[] = new int[52];
	private boolean isWinner = false;
	static int nextCardToPlay = 0;
	private boolean turn = true;
	private int nextCardToTake = 0;
	
	Player(String name,int age,String sex){
		Scanner sc = new Scanner(System.in);
		if(!name.isEmpty()){
			this.name = name;
		}
		else{
			do{
				System.out.println("Invalid name, try again: ");
				name = sc.nextLine();
			}while(name.isEmpty());
			this.name = name;
		}
		if(age < 0 || age > 100){
			do{
				System.out.println("Invalid age, try again: ");
				age = sc.nextInt();
			}while(age < 0 || age > 100);
			this.age = age;
		}
		else{
			this.age = age;
		}
		if(!sex.isEmpty()){
			this.sex = sex;
		}
		else{
			do{
				System.out.println("Invalid sex, try again: ");
				sex = sc.nextLine();
			}while(sex.isEmpty());
			this.sex = name;
		}
	}
	
	void setOpponent(Player opponent){
		if(opponent.equals(this)){
			System.out.println("You can't play with yourself");
			return;
		}
		else{
			System.out.println(this.name + " will play against " + opponent.name);
			this.opponent = opponent;
			opponent.opponent = this;
		}
	}
	
	void getDeck(Cards cards){
		if(opponent == null){
			System.out.println("Chose an opponent first");
			return;
		}
		System.out.println("You and your opponent now have a deck of cards.");
		this.cards = cards;
		opponent.cards = cards;
	}
	
	void shuffleCards(int times){
		if(cards == null){
			System.out.println("You should get a deck to shuffle it");
			return;
		}
		else{
			cards.shuffle(times);
			System.out.println("Shuffeling ....");
		}
	}
	
	void handCards(){
		if(cards == null){
			System.out.println("You should find cards first!");
			return;
		}
		else{
			cards.setCards(hand);
			opponent.cards.setCards(opponent.hand);
			System.out.println("You and your opponent now have cards in your hands!");
		}
	}
	
	void playCards(){
		if(this.isWinner || opponent.isWinner){
			System.out.println("The game has ended!!!");
			return;
		}
		if(!turn){
			System.out.println("The other player should play first now");
			return;
		}
		if(nextCardToPlay == 26){
			int wonCardsAmm1 = 0;
			int wonCardsAmm2 = 0;
			for(int i = 0;i < wonCards.length;i++){
				if(this.wonCards[i] > 0){
					wonCardsAmm1++;
				}
				if(opponent.wonCards[i] > 0){
					wonCardsAmm2++;
				}
			}
			if(wonCardsAmm1 > wonCardsAmm2){
				this.isWinner = true;
				System.out.println(this.name + " has won!!!");
			}
			if(wonCardsAmm2 > wonCardsAmm1){
				opponent.isWinner = true;
				System.out.println(opponent.name + " has won!!");
			}
			else{
				this.isWinner = true;
				opponent.isWinner = true;
				System.out.println("It's a tie!");
			}
			return;
		}
		else{
			this.turn = false;
			opponent.turn = true;
			int color = hand[nextCardToPlay] % 10;
			int cardNumber = (hand[nextCardToPlay] - color)/10;
			if(cardNumber > 10){
				System.out.println(this.name + " plays: " + getCard(cardNumber) + " of " + getColor(color));
			}
			else{
				System.out.println(this.name + " plays: " + cardNumber + " of " + getColor(color));

			}
			int oppColor = opponent.hand[opponent.nextCardToPlay] % 10;
			int oppCardNumber = (opponent.hand[opponent.nextCardToPlay] - oppColor)/10;
			if(oppCardNumber > 10){
				System.out.println(opponent.name + " plays: " + getCard(oppCardNumber) + " of " + getColor(oppColor));
			}
			else{
				System.out.println(opponent.name + " plays: " + oppCardNumber + " of " + getColor(oppColor));

			}
			if(cardNumber < oppCardNumber){
				opponentTakeCards();
				return;
			}
			
			if(cardNumber > oppCardNumber){
				iTakeCards();
				return;
			}

			if(cardNumber == oppCardNumber){
				if(nextCardToPlay >= 23){
					System.out.println("No more cards no one takes this hand.");
					nextCardToPlay = 26;
					return;
				}
				System.out.println("War!!!!");
				War();
			}

		}
	}
	
	private static final String getCard(int cardNumber){
		switch(cardNumber){
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		case 14:
			return "Ace";
		default:
			return "Something is wrong";
		}
	}
	
	private static final String getColor(int colorNumber){
		switch(colorNumber){
		case 1:
			return "Hearts";
		case 2:
			return "Diamonds";
		case 3:
			return "Clubs";
		case 4:
			return "Spades";
		default:
			return "Somtething is wrong";
		}
	}
	
	private void opponentTakeCards(){
		System.out.println(opponent.name + " wins this hand!");
		opponent.wonCards[opponent.nextCardToTake] = this.hand[nextCardToPlay];
		this.hand[nextCardToPlay] = 0;
		opponent.nextCardToTake++;
		opponent.wonCards[opponent.nextCardToTake] = opponent.hand[opponent.nextCardToPlay];
		opponent.hand[nextCardToPlay] = 0;
		opponent.nextCardToTake++;
		nextCardToPlay++;
		return;
	}
	
	private void iTakeCards(){
		System.out.println(this.name + " wins this hand!");
		this.wonCards[this.nextCardToTake] = opponent.hand[opponent.nextCardToPlay];
		opponent.hand[opponent.nextCardToPlay] = 0;
		this.nextCardToTake++;
		this.wonCards[this.nextCardToTake] = this.hand[this.nextCardToPlay];
		this.hand[nextCardToPlay] = 0;
		this.nextCardToTake++;
		nextCardToPlay++;
		return;
	}
	
	private void War(){
		nextCardToPlay += 3;
		int color1 = hand[nextCardToPlay] % 10;
		int cardNumber1 = (hand[nextCardToPlay] - color1)/10;
		if(cardNumber1 > 10){
			System.out.println(this.name + " 3rd card is: " + getCard(cardNumber1) + " of " + getColor(color1));
		}
		else{
			System.out.println(this.name + " 3rd card is: " + cardNumber1 + " of " + getColor(color1));

		}
		int oppColor1 = opponent.hand[opponent.nextCardToPlay] % 10;
		int oppCardNumber1 = (opponent.hand[opponent.nextCardToPlay] - oppColor1)/10;
		if(oppCardNumber1 > 10){
			System.out.println(opponent.name + " 3rd card is: " + getCard(oppCardNumber1) + " of " + getColor(oppColor1));
		}
		else{
			System.out.println(opponent.name + " 3rd card is: " + oppCardNumber1 + " of " + getColor(oppColor1));

		}
		
		if(cardNumber1 < oppCardNumber1){
			System.out.println(opponent.name + " wins this hand!");
			nextCardToPlay -= 3;
			for(int i = 0; i < 3;i++){
				opponent.wonCards[opponent.nextCardToTake] = this.hand[nextCardToPlay];
				this.hand[nextCardToPlay] = 0;
				opponent.nextCardToTake++;
				opponent.wonCards[opponent.nextCardToTake] = opponent.hand[opponent.nextCardToPlay];
				opponent.hand[nextCardToPlay] = 0;
				opponent.nextCardToTake++;
				nextCardToPlay++;
			}
			return;
		}
		
		if(cardNumber1 > oppCardNumber1){
			System.out.println(this.name + " wins this hand!");
			nextCardToPlay -= 3;
			for(int i = 0; i < 3;i++){
				this.wonCards[this.nextCardToTake] = opponent.hand[opponent.nextCardToPlay];
				opponent.hand[opponent.nextCardToPlay] = 0;
				this.nextCardToTake++;
				this.wonCards[this.nextCardToTake] = this.hand[this.nextCardToPlay];
				this.hand[nextCardToPlay] = 0;
				this.nextCardToTake++;
				nextCardToPlay++;
			}
			return;
		}
		if(cardNumber1 == oppCardNumber1){
			War();
		}
	}


}
