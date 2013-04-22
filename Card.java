
public class Card {
  private String face;
	private String suit;
	private Card next, previous;
	
	
	
	public Card()
	{
		face = "null";
		suit = "null";
		next = null;
		previous = null;
		
	}
	
	public Card(String theFace, String theSuit)
	{
		face = theFace;
		switch(theSuit)
		{
		case "Spade": suit = theSuit; break;
		case "Heart": suit = theSuit; break;
		case "Club": suit = theSuit; break;
		case "Diamond": suit = theSuit; break;
		}
		next = null;
		previous = null;
	}
	//Sets
	public void setFace(String newFace)
	{
		switch(newFace)
		{
		case "2": face = newFace; break;
		case "3": face = newFace; break;
		case "4": face = newFace; break;
		case "5": face = newFace; break;
		case "6": face = newFace; break;
		case "7": face = newFace; break;
		case "8": face = newFace; break;
		case "9": face = newFace; break;
		case "10": face = newFace; break;
		case "11": face = "Jack"; break;
		case "12": face = "Queen"; break;
		case "13": face = "King"; break;
		case "14": face = "Ace"; break;
		case "Jack": face = "Jack"; break;
		case "Queen": face = "Queen"; break;
		case "King": face = "King"; break;
		case "Ace": face = "Ace"; break;
		
		}
		
	}
	
	public void setSuit(String newSuit)
	{
		switch(newSuit)
		{
		case "Spade": suit = newSuit; break;
		case "Heart": suit = newSuit; break;
		case "Club": suit = newSuit; break;
		case "Diamond": suit = newSuit; break;
		}
	}
	
	public void setNext(Card nextCard)
	{
		next = nextCard;
	}
	
	public void setPrev(Card lastCard)
	{
		previous = lastCard;
	}
	//////////////////////////////////////////////////
	
	//Gets
	public String getFace()
	{
		return face;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public Card getNext()
	{
		return next;
	}
	
	public Card getLast()
	{
		return previous;
	}
	//////////////////////////////////////////////////////
	
	public String toString()
	{
		String theString;
		theString = "The value of the card is: " + face;
		theString += "\nThe suit of the card is: " + suit + "s\n";
		
		return theString;
		
	}
	
	
	public static void main(String[] args) { 
		   Card[] card = new Card[3]; // array of cards

		   card[0] = new Card("8", "Heart");
		   card[1] = new Card("11", "Spade");
		   card[2] = new Card();
		   card[0].setNext(card[1]);
		   card[1].setPrev(card[0]);
		   System.out.println(card[0].getNext());
		   System.out.println(card[1].getLast());
		 
		   for(int i = 0; i < 3; i++)
		   {
			   System.out.print(card[i].toString());
		   }

	}

	
}
