
public class PileOfCards {
  
	private Card topCard;
	private Card bottomCard;
	private int numCards;
	
	public PileOfCards()
	{
		clear();
	}
	public PileOfCards(int numberCards)
	{
		clear();
		numCards = numberCards;
	}
	
	public PileOfCards(String[] theFace, String[] theSuit, int numberCards)
	{
		clear();
		
		for(int i = numberCards-1; i >= 0; i--)
		{
			addTop(theFace[i], theSuit[i]);
		}
		
	}
	
	public void clear()
	{
		topCard = null;
		bottomCard = null;
		numCards = 0;
	}
	
	/*public boolean addBottom(String theNewCardInfo)
	{
		Card newCard = new Card(theNewCardInfo, theNewCardInfo);
		
		if(isEmpty() == true)
		{
			topCard = newCard;
			bottomCard = newCard;
		}
		else
		{
			Card lastCard = bottomCard;
			lastCard.setNext(newCard);
			bottomCard = newCard;
			newCard.setPrev(lastCard);
			newCard.setNext(null);
		}
		
		numCards++;
		
		return true;
		
	}*/
	

	
	public boolean addTop(String theNewCardFace, String theNewCardSuit )
	{
		Card newCard = new Card(theNewCardFace, theNewCardSuit);
		if(isEmpty() == true)
		{
			topCard = newCard;
			bottomCard = newCard;
		}
		else
		{
			Card lastCard = topCard;
			newCard.setNext(lastCard);
			lastCard.setPrev(newCard);
			topCard = newCard;
			newCard.setPrev(null);			
		}
		
		numCards++;
		//System.out.println(numCards);
		return true;
	}
	public void setNumCards(int theNum)
	{
		numCards = theNum;
	}

	public static void copyList(PileOfCards copyFromPile, PileOfCards copyToPile)
	{
		copyToPile.clear();

		for(int i = 1; i < copyFromPile.getNumCards()+1; i++)
		{
			copyToPile.addTop(copyFromPile.getCardAt(i).getFace(), copyFromPile.getCardAt(i).getSuit());

		}
		copyFromPile.clear();
	}
	
	public void Shuffle(String[] theNewFace, String[] theNewSuit, int numberCards)
	{
		clear();
		
		String[] temp = new String[numberCards];
		String[] temp2 = new String[numberCards];
		
		for( int i = 0; i < numberCards/2; i ++)
		{
			temp[2*i] = theNewFace[i];
			temp2[2*i] = theNewSuit[i];
			temp[2*i+1] = theNewFace[i + numberCards/2];
			temp2[2*i+1] = theNewSuit[i + numberCards/2];
		/*int[] rArray = new int[numberCards];
		for(int i = 0; i < numberCards-1; i++)
		{
			int r = (int) (Math.random()*100) % numberCards;
			
			for(int j = 0; j < rArray.length; j++)//long way to ensure no duplicates
			{
				if(rArray[i] == r)
				{
					while(rArray[i] == r)
					{
						r = (int) (Math.random()*100) % numberCards;
					}
				}
			}
			rArray[i] = r;*/
		}
		theNewFace = temp;
		theNewSuit = temp2;
		for(int i = 0; i < numberCards; i++)
		{
			addTop(theNewFace[i], theNewSuit[i]);
		}
		
		
	}
	
	public boolean add(int newPosition,String theNewCardFace, String theNewCardSuit)
	{
		boolean success = true;
		
		if((newPosition >= 1) && (newPosition <= numCards+1))
		{
			Card theNewCard = new Card(theNewCardFace, theNewCardSuit);
			
			if(isEmpty() || (newPosition == 1))
			{
				//theNewCard.setNext(topCard);
				topCard = theNewCard;
				theNewCard.setPrev(null);
				theNewCard.setNext(topCard);
				
			}
			else
			{
				Card previousCard = getCardAt(newPosition - 1);
				Card nextCard = previousCard.getNext();
				theNewCard.setNext(nextCard);
				theNewCard.setPrev(previousCard);
				previousCard.setNext(theNewCard);
				nextCard.setPrev(theNewCard);
				
			}
			numCards++;
		}
		else
		{
			success = false;
		}
		
		return success;
	}
	
	
	public boolean isEmpty()
	{
		boolean emptyCheck;
		
		if(numCards == 0)
		{
			assert topCard == null;
			emptyCheck = true;
		}
		else
		{
			assert topCard != null;
			emptyCheck = false;
		}
		return emptyCheck;
	}
	
	public Card getCardAt(int position)
	{
		assert !isEmpty() && (1 <= position) && (position <= numCards);
		Card currentCard = topCard;
		
		for(int i = 1; i < position; i++)
		{
			currentCard = currentCard.getNext();
		}
		
		assert currentCard != null;
		return currentCard;
	}
	
	public String toString()
	{
		Card currentCard = topCard;
		String theString = "";
		
		for(int i = 0; i < numCards; i++)
		{
			theString += "The card value is " + currentCard.getFace() + "\n" +
						 "The suit is " + currentCard.getSuit() + "s\n";
			
			currentCard = currentCard.getNext();			 
		}
		
		return theString;
	}
	
	public Card remove(int position)
	{
		Card removedCard = new Card();
		if((position >= 1) && (position <= numCards))
		{
			assert !isEmpty();
			
			if(position == 1)
			{
				removedCard.setFace(topCard.getFace());
				removedCard.setSuit(topCard.getSuit());
				removedCard.setNext(null);
				removedCard.setPrev(null);
				topCard = topCard.getNext();
				//topCard.setPrev(null);
			}
			else if(position == numCards)
			{
				removedCard.setFace(bottomCard.getFace());
				removedCard.setSuit(bottomCard.getSuit());
				removedCard.setNext(null);
				removedCard.setPrev(null);
				bottomCard = bottomCard.getLast();
			}
			else
			{
				Card cardBefore = getCardAt(position-1);
				Card toBeRemoved = cardBefore.getNext();
				Card cardAfter = toBeRemoved.getNext();
				cardBefore.setNext(cardAfter);
				cardAfter.setPrev(cardBefore);
				removedCard.setFace(toBeRemoved.getFace());
				removedCard.setSuit(toBeRemoved.getSuit());
			}
			numCards--;
		}
		return removedCard;	
	}
	public int getNumCards()
	{
		return numCards;
	}
	
	public Card getTopCard()
	{
		return topCard;
	}
	
	public static void main(String[] args)
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
			
			//System.out.println(face[iPokemon]);
		}

		
		int size = 52;
		
		PileOfCards hi = new PileOfCards(face, suit, 52);
		hi.Shuffle(face, suit, 52);
		hi.Shuffle(face, suit, 52);
		hi.addTop("2", "Spade");
		//System.out.println(hi.toString());
		hi.remove(3);
		System.out.println(hi.toString());
		System.out.println(hi.getNumCards());
		
		
		
		
		
		
		
		
	}
	


}
