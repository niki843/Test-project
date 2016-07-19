package warGame;

import org.omg.Messaging.SyncScopeHelper;

public class NaMasata {
	
	public static void main(String[] args) {
		
		Player tison = new Player("Michael",50,"Male");
		
		Player ali = new Player("Mohamed",50,"Male");
		
		tison.setOpponent(ali);
		
		Cards deck1 = new Cards();
		
		tison.getDeck(deck1);
		
		ali.shuffleCards(100);
		
		tison.handCards();
		
		
		
		for(int i  = 0; i < 14; i++){
		tison.playCards();
		
		ali.playCards();
		}
		
	}

}
