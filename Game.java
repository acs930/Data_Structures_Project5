import java.util.Scanner;


public class Game {
  private int numPlayers;
	private Deck theGameDeck;
	private Hand[] players;
	
	
	public Game()
	{
		numPlayers = 0;
		theGameDeck = new Deck();
		players = new Hand[numPlayers];
	}
	
	public Game(int newNumPlayers)
	{
		numPlayers = newNumPlayers;
		theGameDeck = new Deck();
		players = new Hand[numPlayers];
	}
	
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	public Deck getDeck()
	{
		return theGameDeck;
	}
	
	public Hand[] getPlayers()
	{
		return players;
	}
	public Hand getPlayer(int i)
	{
		return players[i];
	}
	
	public void dealCards(Hand[] thePlayers)
	{
		Hand tempHand = new Hand();
		for(int i = 0; i < thePlayers.length; i++)
		{
			thePlayers[i] = theGameDeck.deal(5, theGameDeck);
			//System.out.println(theGameDeck.getDeck().getNumCards());
		}
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
	
	public void dealOne(Hand playerHand)
	{
		//System.out.println(theGameDeck.getDeck().getNumCards());
		int r = (int) (Math.random()*1000)% theGameDeck.getDeck().getNumCards();
		Card tempCard = theGameDeck.getDeck().remove(r);
		playerHand.getTheHand().addTop(tempCard.getFace(), tempCard.getSuit());
		//playerHand.getTheHand().setNumCards(playerHand.getTheHand().getNumCards()+1);
		//System.out.println(playerHand.getTheHand().getNumCards()+1);		
	}
	
	public void removeCard(Hand playerHand, int position)
	{
		playerHand.getTheHand().remove(position);
		//playerHand.
	}
	
	public void exchangeCards(Hand playerHand, int position)
	{
		this.removeCard(playerHand,position);
		this.dealOne(playerHand);
	}
	
	public void checkHand(Hand playerHand)
	{
		int[] valueArray = new int[15];
		int[] suitArray = new int[4];
		for(int i =0 ; i < valueArray.length; i++)
		{
			valueArray[i] = 0;
		}
		Card tempCard;
		tempCard = playerHand.getTheHand().getTopCard();
		System.out.println(tempCard.getFace());
		for(int i = 0; i < playerHand.getTheHand().getNumCards(); i++)
		{
			switch(tempCard.getFace())
			{
			case "2": valueArray[2]++; break;
			case "3": valueArray[3]++; break;
			case "4": valueArray[4]++; break;
			case "5": valueArray[5]++; break;
			case "6": valueArray[6]++; break;
			case "7": valueArray[7]++; break;
			case "8": valueArray[8]++; break;
			case "9": valueArray[9]++; break;
			case "10": valueArray[10]++; break;
			case "Jack": valueArray[11]++; break;
			case "Queen": valueArray[12]++; break;
			case "King": valueArray[13]++; break;
			case "Ace": valueArray[14]++; break;
			}
			
			switch(tempCard.getSuit())
			{
			case "Spade": suitArray[0]++; break;
			case "Heart": suitArray[1]++; break;
			case "Diamond": suitArray[2]++; break;
			case "Club": suitArray[3]++; break;
			}
			
			tempCard = tempCard.getNext();
			
		
		}
		
		
		whatYouHave(valueArray, suitArray, playerHand);
		/*for(int i = 0; i < suitArray.length; i++)
		{
			System.out.println("Case: " + i + " is " + suitArray[i]);
		}*/
		
	}
	
	public void whatYouHave(int[] faceArray, int[] suitArray, Hand playerHand)
	{
		String newString = "";
		
		int straightCount = 0;
		/*
		 * high card
		 * pair
		 * 2 pair
		 * 3 of kind
		 * straight
		 * flush
		 * full house
		 * 4 of a kind
		 * straight flush		
		 */
		for(int i = 0; i < faceArray.length; i++)
		{
			switch(faceArray[i])
			{
			case 2:  newString += "Pair of " + faceArray[i] + "s"; playerHand.getScore()[1]++; break;
			case 3:  newString += "3 of a kind of " + faceArray[i] + "s"; playerHand.getScore()[3]++; break;
			case 4:  newString += "4 of a kind of " + faceArray[i] + "s"; playerHand.getScore()[7]++; break;
			}
			
			if(playerHand.getScore()[1] == 2)
			{
				playerHand.getScore()[2]++;
			}
			
			if((playerHand.getScore()[1] == 1) && (playerHand.getScore()[3] == 1))
			{
				playerHand.getScore()[6]++;
			}
			
			/*if(faceArray[i] == faceArray[i-1])
			{
				straightCount++;
			}*/
		}
		
		if(straightCount == 5)
		{
			playerHand.getScore()[4]++;
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(suitArray[i] == 5)
			{
				playerHand.getScore()[5]++;
			}
		}
		System.out.println(scoreToString(playerHand));
	}
	
	public String scoreToString(Hand player)
	{
		String yourScore = "";
		for(int i = 0; i < 9; i++)
		{
			if(player.getScore()[i] == 1)
			{
				switch(player.getScore()[i])
				{
				case 1:  yourScore += "Pair";break;
				case 2:  yourScore += "2 Pair";break;
				case 3:  yourScore += "3 of a kind";break;
				case 4:  yourScore += "Straight";break;
				case 5:  yourScore += "flush";break;
				case 6:  yourScore += "Full House";break;
				case 7:  yourScore += "4 of a kind";break;
				
					
				}
			}
		}
		
		return yourScore;
	}
	
	public static void main(String[] args)
	{
		boolean gameState = true;
		int userChoice = 0;
		int uc1, uc2, uc3 = 0; //for exchanging cards
		Scanner input = new Scanner(System.in);
		//Deck myDeck = new Deck();
		Game newGame = new Game(4);
		
		
		
		int numCardsInHand = 5;	
		if(gameState == true)
		{
			newGame.dealCards(newGame.getPlayers());
		}
		System.out.println("HONEST POKER");
		System.out.println("You are playing 5 card draw poker with 3 other players\n" +
				"Your opponents can not exchange cards cause they don't know how to play\n" +
				"You, however are allowed to exchange up to 3\n" +
				" if you would like to exchange them please indicate this by the number of the cards position\n" +
				"Here is your hand:\n");
		
		System.out.println(newGame.getPlayer(0).getTheHand().toString());
		
		System.out.println("what do you want to do:");
		System.out.println("1: exchange card/s\n" +
				"2: Stand\n");
		
		
		userChoice = input.nextInt();
	
		switch(userChoice)
		{
		case 1:
			System.out.println("How many cards would you like to exchange:");
			userChoice = input.nextInt();
			switch(userChoice)
			{
			case 1:
				System.out.println("which cards by place:");
				uc1 = input.nextInt();
				newGame.exchangeCards(newGame.getPlayer(0), uc1);break;
			case 2:
				System.out.println("which cards by place:");
				uc1 = input.nextInt();
				uc2 = input.nextInt();
				newGame.exchangeCards(newGame.getPlayer(0), uc1);
				newGame.exchangeCards(newGame.getPlayer(0), uc2); break;
			case 3:
				System.out.println("which cards by place:");
				uc1 = input.nextInt();
				uc2 = input.nextInt();
				uc3 = input.nextInt();
				newGame.exchangeCards(newGame.getPlayer(0), uc1);
				newGame.exchangeCards(newGame.getPlayer(0), uc2);
				newGame.exchangeCards(newGame.getPlayer(2), uc3);break;			
			}break;
		case 2:
			System.out.println("ok");
		}
		
		//newGame.checkHand(newGame.getPlayer(0));
		//System.out.println(newGame.scoreToString(newGame.getPlayer(0)));
		
		
	System.out.println("You have: \n" + newGame.getPlayer(0).toString());
	System.out.println("Player 2 has \n" + newGame.getPlayer(1).toString() + "\n\n\n");
	System.out.println("Player 3 has \n" + newGame.getPlayer(2).toString() + "\n\n\n");
	System.out.println("Player 4 has \n" + newGame.getPlayer(3).toString() + "\n\n\n");
	System.out.println("Did you win? (1 for yes, 0 for no)");

	userChoice = input.nextInt();
	
	if(userChoice == 0)
	{
		System.out.println("You Lost, better luck next time.");
	}
	else if (userChoice == 1)
	{
		System.out.println("You Won!! I hope you weren't lying.");
	}
	else
	{
		System.out.println("Invalid answer.  I guess you lost.");
	}
	
	
	}
	

}

//honest poker.
