
public class Deck extends PileOfCards{
  
	private int numCards;
	private PileOfCards theDeck;
	
	
	public Deck()
	{
		String[] suit = new String[52];
		String[] face = new String[52];
		int k = 2;
		for(int i = 0; i < suit.length; i++)
		{
			if((i < 13) && ( i>= 0))
			{
				suit[i] = "Spade";
			}
			
			else if((i < 26) && ( i>= 13))
			{
				suit[i] = "Heart";
			}
			
			else if((i < 39) && ( i>= 26))
			{
				suit[i] = "Diamond";
			}
			
			else if((i < 52) && ( i>= 39))
			{
				suit[i] = "Club";
			}
			
			
		}
		for(int i = 0; i < face.length; i++)
		{
			
			
			switch((i%13)+2)
			{
			case 2: face[i] = "2"; break;
			case 3: face[i] = "3"; break;
			case 4: face[i] = "4"; break;
			case 5: face[i] = "5"; break;
			case 6: face[i] = "6"; break;
			case 7: face[i] = "7"; break;
			case 8: face[i] = "8"; break;
			case 9: face[i] = "9"; break;
			case 10: face[i] = "10"; break;
			case 11: face[i] = "Jack"; break;
			case 12: face[i] = "Queen"; break;
			case 13: face[i] = "King"; break;
			case 14: face[i] = "Ace"; break;
			}
			
			//System.out.println(face[i]);
		}
		//numCards = 52;
		
		
		theDeck = new PileOfCards(face, suit, 52);
		//numCards = theDeck.getNumCards();
		//System.out.println(this.theDeck.getNumCards());
		
	}
	

	public void setNumCards(int numberCards)
	{
		numCards = numberCards;
	}

	public PileOfCards getDeck()
	{
		return theDeck;
	}
	
	public String toString()
	{
		Card currentCard = theDeck.getTopCard();
		
		String theString = "";
		
		for(int i = 0; i < theDeck.getNumCards(); i++)
		{
			theString += "The card value is " + currentCard.getFace() + "\n" +
						 "The suit is " + currentCard.getSuit() + "s\n";
			
			currentCard = currentCard.getNext();			 
		}
		
		return theString;
	}
	public Hand deal( int numHandCards, Deck theRealDeck)
	{
		
		
			//for(int j = 0; j < numPlayers.length; j++)
			//{
				PileOfCards tempHand = new PileOfCards();
				Hand alsoTempHand = new Hand();
				for (int i = 0; i < numHandCards; i++)
				{
					int r = (int) (Math.random()*1000)% theRealDeck.getDeck().getNumCards();
					Card tempCard = theRealDeck.getDeck().remove(r);

					tempHand.addTop(tempCard.getFace(), tempCard.getSuit());
				}

				copyList(tempHand, alsoTempHand.getTheHand());
		
			//}
			return alsoTempHand;
		
	}
	

	

	
	public static void main(String[] args)
	{
			Deck myDeck = new Deck();
			Hand[] players =  new Hand[3];
			int numCardsInHand = 5;
		//System.out.println(myDeck.getNumCards());
		players[0] = myDeck.deal(numCardsInHand, myDeck);
		System.out.println("Done");
		System.out.println(players[0].toString());
		//myDeck.dealOne(players[0]);
		System.out.println(players[0].toString());

		
		
	}

}
