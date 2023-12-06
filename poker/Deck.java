package JavaPoker.poker;

import java.util.ArrayList;

public class Deck
{
	
// Attributes
	
	private ArrayList<Card> Cards;
	
// End Attributes
	
	
	
	
	
// Constructor
	
	public Deck()
	{
		Cards = new ArrayList<Card>();
		
		for (Integer r = 2; r <= 14; r++)
		{	
			for (int s = 0; s < 4; s++)
			{
				Card c = new Card(r, (int)(Math.pow(10, s)));
				Cards.add(c);	
			}
		}
	}
	
// End Constructor

	
	
	
	
// Informational
	
	public void NewDeck()
	{
		Cards.clear();
		
		for (int r = 2; r <= 14; r++)
		{	
			for (int s = 0; s < 4; s++)
			{
				Card c = new Card(r, (int)(Math.pow(10, s)));
				Cards.add(c);
			}
		}
	}
	
	public Card Deal()
	{
		Card c = Cards.remove((int)(Math.random()*Cards.size()));
		return c;
	}
	
// End Informational
	
}