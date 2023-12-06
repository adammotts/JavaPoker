package JavaPoker.poker;

import java.util.HashMap;

public class Card
{
	
// Attributes
	
	private int Rank;
	
	private int Suit;
	
	private HashMap<Integer, String> CardRanks = new HashMap<Integer, String>();
	
	private HashMap<Integer, String> CardSuits = new HashMap<Integer, String>();
	
// End Attributes
	
	
	
	
	
// Constructor
	
	public Card(int r, int s)
	{
		Rank = r;
		Suit = s;
		
		for (Integer i = 2; i <= 10; i++)
		{
			CardRanks.put(i, i.toString());
		}
		
		CardRanks.put(11, "J");
		CardRanks.put(12, "Q");
		CardRanks.put(13, "K");
		CardRanks.put(14, "A");
		
		CardSuits.put(1, "♣️");
		CardSuits.put(10, "♦");
		CardSuits.put(100, "♥️");
		CardSuits.put(1000, "♠️");
	}
	
// End Constructor
	
	
	
	
	
// Informational
	
	public int GetRank()
	{
		return Rank;
	}
	
	public int GetSuit()
	{
		return Suit;
	}
	
// End Informational
	
	
	
	
	
// Cosmetics
	
	public void PrintCard()
	{
		System.out.print(CardRanks.get(Rank) + CardSuits.get(Suit) + " ");
	}
	
// End Cosmetics
	
}