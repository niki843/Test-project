package warGame;

import java.util.Random;

public class Cards {
	
	private static int[] cards = {21,31,41,51,61,71,81,91,101,111,121,131,141
						  ,22,32,42,52,62,72,82,92,102,112,122,132,142
						  ,23,33,43,53,63,73,83,93,103,113,123,133,143
						  ,24,34,44,54,64,74,84,94,104,114,124,134,144};
	
	
	
	void shuffle(int shuffles){
		int card1;
		int card2;
		Random r = new Random();
		for(int i = 0; i < shuffles;i++){
			card1 = r.nextInt(51);
			card2 = r.nextInt(51);
			if(card1 == card2){
				i--;
				continue;
			}
			else{
				int x = cards[card1];
				cards[card1] = cards[card2];
				cards[card2] = x;
			}
		}
	}
	
	void setCards(int[] newDeck){
		int j = 0;
		for (int i = 0; i < cards.length; i++) {
			if(j == newDeck.length){
				break;
			}
			if(cards[i] == 0){
				continue;
			}
			else{
				newDeck[j] = cards[i];
				cards[i] = 0;
				j++;
			}
		}
	}
	
}
