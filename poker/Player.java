package JavaPoker.poker;

import java.util.ArrayList;

import JavaPoker.poker.Card;

public class Player
{
	
// Attributes
	
	private String Name;
	
	private int Stack;
	
	private boolean Dealer;
	
	private boolean SmallBlind;
	
	private boolean BigBlind;
	
	private boolean AllIn;
	
	private boolean Folded;
	
	private boolean Winner;
	
	private boolean Out;
	
	private int Bet;
	
	private ArrayList<Card> PlayerHand;
	//Two cards - for player to ask for (need to figure out how to let players ask for at any time)
	
	private ArrayList<Card> PlayerHoldings;
	//Two cards plus community cards - for computation purposes
	
	private double HandStrength;
	
	private int SidePot;
	
	private String HandName;
	
// End Attributes
	
	
	
	
	
// Constructor
	
	public Player(String n)
	{
		Name = n;
		Stack = 500;
		Dealer = false;
		SmallBlind = false;
		BigBlind = false;
		AllIn = false;
		Folded = false;
		Winner = false;
		Bet = 0;
		PlayerHand = new ArrayList<Card>();
		PlayerHoldings = new ArrayList<Card>();
		HandStrength = 0;
		SidePot = 0;
		HandName = "";
	}
	
// End Constructor

	
	
	
	
// Setters

	public void SetDealer()
	{
		System.out.println(Name + " is now the dealer");
		Dealer = true;
		SmallBlind = false;
		BigBlind = false;
	}
	
	public void SetSmallBlind()
	{
		System.out.println(Name + " is now the small blind");
		SmallBlind = true;
		BigBlind = false;
		Dealer = false;
		Bet = 2;
	}
	
	public void SetBigBlind()
	{
		System.out.println(Name + " is now the big blind");
		BigBlind = true;
		Dealer = false;
		SmallBlind = false;
		Bet = 5;
	}
	
	public void SetWinner()
	{
		Winner = true;
	}
	
	public void SetFold()
	{
		//Used for marking purposes for awarding pots
		Folded = true;
	}
	
	public void SetSidePot(int s)
	{
		SidePot = s;
	}
	
// End Setters
	
	
	
	
	
// Getters

	public boolean IsDealer()
	{
		return Dealer;
	}
	
	public boolean IsSmallBlind()
	{
		return SmallBlind;
	}
	
	public boolean IsBigBlind()
	{
		return BigBlind;
	}
	
	public boolean IsFolded()
	{
		return Folded;
	}
	
	public boolean IsWinner()
	{
		return Winner;
	}
	
	public boolean IsAllIn()
	{
		return AllIn;
	}
	
	public ArrayList<Card> GetPlayerHoldings()
	{
		return PlayerHoldings;
	}
	
	public int GetStack()
	{
		return Stack;
	}
	
	public int GetBet()
	{
		return Bet;
	}
	
	public String GetName()
	{
		return Name;
	}
	
	public double GetHandStrength()
	{
		return HandStrength;
	}
	
	public String GetHandName()
	{
		return HandName;
	}
	
	public int GetSidePot()
	{
		return SidePot;
	}
	
// End Getters
	
	
	
	
	
// Resetters
	
	public void ResetHoldingsAndHand()
	{
		PlayerHoldings.clear();
		PlayerHand.clear();
	}
	
	public void ResetStatus()
	{
		Dealer = false;
		SmallBlind = false;
		BigBlind = false;
		Folded = false;
		Winner = false;
		AllIn = false;
		SidePot = 0;
	}
	
// End Resetters
	
	

	
	
// Hand Strength Computation
	
	public void AddToPlayerHandAndHoldings(Card c)
	{
		PlayerHand.add(c);
		PlayerHoldings.add(c);
	}
	
	public void StraightAndRoyalFlushChecker(int HigherEndOfStraight)
	{	
		int StraightSuitSum = 0;

		if (HigherEndOfStraight == 5)
		{
			for (int i = 0; i < PlayerHoldings.size(); i++)
			{	
				if (PlayerHoldings.get(i).GetRank() == 14)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 3)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 2)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 1)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
			}
		}
		
		else
		{
			for (int i = 0; i < PlayerHoldings.size(); i++)
			{	
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 4)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 3)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 2)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight - 1)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
				
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight)
				{
					StraightSuitSum += PlayerHoldings.get(i).GetSuit();
				}
			}
		}
		
		if (HigherEndOfStraight == 14)
		{
			if (StraightSuitSum >= 5000)
			{
				HandStrength = 10;
			}
			
			else if (StraightSuitSum % 1000 >= 500)
			{
				HandStrength = 10;
			}
			
			else if (StraightSuitSum % 100 >= 50)
			{
				HandStrength = 10;
			}
			
			else if (StraightSuitSum % 10 >= 5)
			{
				HandStrength = 10;
			}
		}
		
		else
		{
			if (StraightSuitSum >= 5000)
			{
				HandStrength = 9;
			}
			
			else if (StraightSuitSum % 1000 >= 500)
			{
				HandStrength = 9;
			}
			
			else if (StraightSuitSum % 100 >= 50)
			{
				HandStrength = 9;
			}
			
			else if (StraightSuitSum % 10 >= 5)
			{
				HandStrength = 9;
			}
		}
	}
	
	public int CardCounter(int r)
	{
		int NumberOfCard = 0;
		
		for (int i = 0; i < PlayerHoldings.size(); i++)
		{
			if (PlayerHoldings.get(i).GetRank() == r)
			{
				NumberOfCard++;
			}
		}
		
		return NumberOfCard;
	}
	
	public int GetMaxRank(ArrayList<Card> Cards)
	{
		int MaxRank = 0;
		int MaxRankIndex = -1;
		
		for (int i = 0; i < Cards.size(); i++)
		{
			if (Cards.get(i).GetRank() > MaxRank)
			{
				MaxRank = Cards.get(i).GetRank();
				MaxRankIndex = i;
			}
		}
		
		Cards.remove(MaxRankIndex);
		
		return MaxRank;
	}
	
	public int FlushChecker()
	{
		int SuitSum = 0;
		int SuitValue = 0;
		
		for (int i = 0; i < PlayerHoldings.size(); i++)
		{
			SuitSum += PlayerHoldings.get(i).GetSuit();
			
			if (SuitSum >= 5000)
			{
				HandStrength = 6;
				SuitValue = 1000;
			}
			
			else if (SuitSum % 1000 >= 500)
			{
				HandStrength = 6;
				SuitValue = 100;
			}
			
			else if (SuitSum % 100 >= 50)
			{
				HandStrength = 6;
				SuitValue = 10;
			}
			
			else if (SuitSum % 10 >= 5)
			{
				HandStrength = 6;
				SuitValue = 1;
			}
		}
		
		return SuitValue;
	}
	
	public void StraightChecker(int HigherEndOfStraight)
	{
		if (HigherEndOfStraight == 5)
		{
			for (int i = 0; i < PlayerHoldings.size(); i++)
			{	
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight)
				{
					for (int j = 0; j < PlayerHoldings.size(); j++)
					{
						if (PlayerHoldings.get(j).GetRank() == HigherEndOfStraight - 1)
						{
							for (int k = 0; k < PlayerHoldings.size(); k++)
							{
								if (PlayerHoldings.get(k).GetRank() == HigherEndOfStraight - 2)
								{
									for (int l = 0; l < PlayerHoldings.size(); l++)
									{
										if (PlayerHoldings.get(l).GetRank() == HigherEndOfStraight - 3)
										{
											for (int m = 0; m < PlayerHoldings.size(); m++)
											{
												if (PlayerHoldings.get(m).GetRank() == 14)
												{
													HandStrength = 5;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		else
		{
			for (int i = 0; i < PlayerHoldings.size(); i++)
			{	
				if (PlayerHoldings.get(i).GetRank() == HigherEndOfStraight)
				{
					for (int j = 0; j < PlayerHoldings.size(); j++)
					{
						if (PlayerHoldings.get(j).GetRank() == HigherEndOfStraight - 1)
						{
							for (int k = 0; k < PlayerHoldings.size(); k++)
							{
								if (PlayerHoldings.get(k).GetRank() == HigherEndOfStraight - 2)
								{
									for (int l = 0; l < PlayerHoldings.size(); l++)
									{
										if (PlayerHoldings.get(l).GetRank() == HigherEndOfStraight - 3)
										{
											for (int m = 0; m < PlayerHoldings.size(); m++)
											{
												if (PlayerHoldings.get(m).GetRank() == HigherEndOfStraight - 4)
												{
													HandStrength = 5;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public double ComputeHandStrength(ArrayList<Card> CommunityCards)
	{
		/*
		for (int i = 0; i < PlayerHoldings.size(); i++)
		{
			PlayerHoldings.get(i).PrintCard();
		}
		*/
		
		for (int i = 0; i < CommunityCards.size(); i++)
		{
			PlayerHoldings.add(CommunityCards.get(i));
		}
		
		/*
		for (int i = 0; i < PlayerHoldings.size(); i++)
		{
			PlayerHoldings.get(i).PrintCard();
		}
		*/
		
		//Royal Flush
		StraightAndRoyalFlushChecker(14);
		if (HandStrength == 10)
		{
			HandName = "a Royal Flush";
			return HandStrength;
		}
		
		//Straight Flush
		for (int i = 13; i >= 5; i--)
		{
			StraightAndRoyalFlushChecker(i);
			if (HandStrength == 9)
			{
				HandStrength += i/100.0;
				HandName = "a Straight Flush";
				return HandStrength;
			}
		}
		
		//Four of a Kind
		for (int i = 14; i >= 2; i--)
		{
			if (CardCounter(i) == 4)
			{
				HandStrength = 8 + i/100.0;
				
				ArrayList<Card> NotQuads = PlayerHoldings;
				
				for (int j = 0; j < NotQuads.size(); j++)
				{
					if (NotQuads.get(j).GetRank() == i)
					{
						NotQuads.remove(j);
						j--;
					}
				}
				
				HandStrength += GetMaxRank(NotQuads) / 10000.0;
				HandName = "Four of a Kind";
				return HandStrength;
			}
		}
		
		//Full House
		for (int i = 14; i >= 2; i--)
		{
			if (CardCounter(i) == 3)
			{
				for (int j = 14; j >= 2; j--)
				{
					if (CardCounter(j) >= 2 && j != i)
					{
						HandStrength = 7 + i/100.0 + j/10000.0;
						HandName = "a Full House";
						return HandStrength;
					}
				}
			}
		}
		
		//Flush
		FlushChecker();
		if (HandStrength == 6)
		{
			ArrayList<Card> FlushCards = PlayerHoldings;
			
			for (int i = 0; i < FlushCards.size(); i++)
			{
				if (FlushCards.get(i).GetSuit() != FlushChecker())
				{
					FlushCards.remove(i);
					i--;
				}
			}
			
			for (int i = 1; i <= 5; i++)
			{
				HandStrength += GetMaxRank(FlushCards) / Math.pow(100, i);
			}
			
			HandName = "a Flush";
			return HandStrength;
		}
		
		//Straight
		for (int i = 14; i >= 5; i--)
		{
			StraightChecker(i);
			if (HandStrength == 5)
			{
				HandStrength += i/100.0;
				HandName = "a Straight";
				return HandStrength;
			}
		}
		
		//Three of a Kind
		for (int i = 14; i >= 2; i--)
		{
			if (CardCounter(i) == 3)
			{
				HandStrength = 4 + i/100.0;
				
				ArrayList<Card> NotTrips = PlayerHoldings;
				
				for (int j = 0; j < NotTrips.size(); j++)
				{
					if (NotTrips.get(j).GetRank() == i)
					{
						NotTrips.remove(j);
						j--;
					}
				}
				
				for (int k = 1; k <= 2; k++)
				{
					HandStrength += GetMaxRank(NotTrips) / Math.pow(100, k+1);
				}
				
				HandName = "Three of a Kind";
				return HandStrength;
			}
		}
		
		//Two Pair
		for (int i = 14; i >= 2; i--)
		{
			if (CardCounter(i) == 2)
			{
				for (int j = 14; j >= 2; j--)
				{
					if (CardCounter(j) == 2 && j != i)
					{
						HandStrength = 3 + i/100.0 + j/10000.0;
						
						ArrayList<Card> NotTwoPair = PlayerHoldings;
						
						for (int k = 0; k < NotTwoPair.size(); k++)
						{
							if (NotTwoPair.get(k).GetRank() == i || NotTwoPair.get(k).GetRank() == j)
							{
								NotTwoPair.remove(k);
								k--;
							}
						}
						
						HandStrength += GetMaxRank(NotTwoPair) / 1000000.0;
						HandName = "a Two Pair";
						return HandStrength;
					}
				}
			}
		}
		
		//Pair
		for (int i = 14; i >= 2; i--)
		{
			if (CardCounter(i) == 2)
			{
				HandStrength = 2 + i/100.0;
				
				ArrayList<Card> NotPair = PlayerHoldings;
				
				for (int j = 0; j < NotPair.size(); j++)
				{
					if (NotPair.get(j).GetRank() == i)
					{
						NotPair.remove(j);
						j--;
					}
				}
				
				for (int k = 1; k <= 3; k++)
				{
					HandStrength += GetMaxRank(NotPair) / Math.pow(100, k+1);
				}
				
				HandName = "a Pair";
				return HandStrength;
			}
		}
		
		//High Card
		HandStrength = 1;
		for (int i = 1; i <= 5; i++)
		{
			HandStrength += GetMaxRank(PlayerHoldings) / Math.pow(100, i);
		}
		
		HandName = "High Card";
		return HandStrength;
	}

// End Hand Strength Computation
	
	
	

	
// Action
	
	public void Call(int b)
	{
		if (Stack <= b)
		{
			AllIn = true;
			Bet = Stack;
			System.out.println(Name + " is ALL IN for " + Bet + "!");
		}
		
		else
		{
			Bet = b;
			System.out.println(Name + " calls the previous bet of " + Bet);
		}
	}
	
	public int Check(int PreviousBet)
	{
		System.out.println(Name + " checks");
		return Bet;
	}
	
	public int Bet(int PreviousMaxBet, int b)
	{
		if (Stack <= b)
		{
			AllIn = true;
			Bet = Stack;
			System.out.println(Name + " is ALL IN for " + Bet + "!");
			return Bet;
		}
		
		else if (b >= 5)
		{
			Bet = b;
		}
		
		System.out.println(Name + " bets " + Bet);
		return b;
	}
	
	public int Raise(int PreviousMaxBet, int b)
	{
		if (Stack <= b)
		{
			AllIn = true;
			Bet = Stack;
			System.out.println(Name + " is ALL IN for " + Bet + "!");
			return Bet;
		}
		
		else if (b >= 2*PreviousMaxBet)
		{
			Bet = b;
		}
		
		System.out.println(Name + " raises to " + Bet);
		return b;
	}
	
	public void Fold()
	{
		System.out.println(Name + " folds");
		Folded = true;
	}
	
// End Action
	
	
	
	
	
// Action Conditionals
	
	public boolean CanCheck(int PreviousBet)
	{
		if (PreviousBet == 0 || (BigBlind && PreviousBet == 5))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean IsPlayerReadyPreFlop(int PreviousBet)
	{
		if ((!Folded && !AllIn && !Out) && (Bet < PreviousBet || (BigBlind && PreviousBet == 5)))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean IsPlayerReadyPostFlop(int PreviousBet)
	{
		if ((!Folded && !AllIn && !Out) && (Bet < PreviousBet || (Bet == 0 && Bet == PreviousBet)))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean IsOut()
	{
		if (Stack == 0)
		{
			Out = true;
		}
		return Out;
	}
	
// End Action Conditionals
	
	
	
	
	
// Winnings

	public void AddToSidePot(int s)
	{
		SidePot += s;
	}
	
	public void BetSubtracted()
	{
		Stack -= Bet;
		Bet = 0;
	}
	
	public void AddWinningsToStack(int Pot)
	{
		Stack += Pot;
	}
	
// End Winnings
	
	
	
	
	
// Cosmetics
	
	public void PrintHand()
	{
		for (int i = 0; i < PlayerHand.size(); i++)
		{
			PlayerHand.get(i).PrintCard();
		}
	}
	
// End Cosmetics
	
}
