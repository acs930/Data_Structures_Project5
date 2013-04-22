
public class Hand extends PileOfCards{
  private int numCardsHand;
	private PileOfCards theHand;
	private int[] score;
	
	public Hand()
	{
		numCardsHand = 0;
		theHand = new PileOfCards();
		score = new int[9];
		for(int i= 0; i < 9; i++)
		{
			score[i] = 0;
		}
	}
	
	public Hand(int cardsHand)
	{
		numCardsHand  = cardsHand;
		theHand = new PileOfCards();
		score = new int[9];
		for(int i= 0; i < 9; i++)
		{
			score[i] = 0;
		}
	}
	
	public int getNumCards()
	{
		return numCardsHand;
	}
	
	public void lookHand()
	{
		
		System.out.println("You have the following cards:\n" );
		theHand.toString();
	}
	
	public int[] getScore()
	{
		return score;
	}
	
	public PileOfCards getTheHand()
	{
		return theHand;
	}

	
	public void setTheHand(PileOfCards newTheHand)
	{
		System.out.println(newTheHand.toString());

	}
	
	public String toString()
	{
		Card currentCard = theHand.getTopCard();
		
		String theString = "";
		
		for(int i = 0; i < theHand.getNumCards(); i++)
		{
			theString += "The card value is " + currentCard.getFace() + "\n" +
						 "The suit is " + currentCard.getSuit() + "s\n";
			
			currentCard = currentCard.getNext();			 
		}
		
		return theString;
	}
	
	public void getNewCard(int cardExchanged)
	{
		theHand.remove(cardExchanged);
		
	}

}
