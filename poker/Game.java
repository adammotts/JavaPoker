package JavaPoker.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game
{
	
// Attributes
	
	private int NumberOfPlayers;
	
	private ArrayList<Player> Players;
	
	private ArrayList<Card> CommunityCards;
	
	private HashMap<String, Player> PlayerNames;
	
	private int DealerIndex;
	
	private int SmallBlindIndex;
	
	private int BigBlindIndex;
	
	private int RoundCounter;
	
	private int PotSize;
	
	private int NumberOfPlayersLeftToAct;
	
	private int NumberOfPlayersFolded;
	
	private int RoundBet;
	
	private int Action;
	
	private boolean RoundInProgress;
	
	private Deck GameDeck;
	
	private Scanner scanner;
	
// End Attributes
	
	
	
	
	
// Constructor
	
	public Game()
	{
		Players = new ArrayList<>();
		CommunityCards = new ArrayList<>();
		PlayerNames = new HashMap<>();
		RoundCounter = 0;
		PotSize = 0;
		RoundBet = 0;
		Action = 0;
		RoundInProgress = true;
		GameDeck = new Deck();
		scanner = new Scanner(System.in);
		
		System.out.println("How many players will be playing?");
		NumberOfPlayers = scanner.nextInt();
		NumberOfPlayersLeftToAct = NumberOfPlayers;
		NumberOfPlayersFolded = 0;
		
		for (Integer i = 0; i < NumberOfPlayers; i++)
		{
			System.out.println("Enter the name of Player " + (i+1));
			String name = scanner.next();
			Player p = new Player(name);
			Players.add(p);
			PlayerNames.put(name, p);
		}
		
		System.out.println("Who will be starting dealer?");
		String starter = scanner.next();
		DealerIndex = Players.indexOf(PlayerNames.get(starter)) - 1;
	}

// End Constructor

	
	
	
	
// Resetters
	
	public void ResetGameStatus()
	{
		for (int i = 0; i < Players.size(); i++)
		{
			Players.get(i).ResetHoldingsAndHand();
			Players.get(i).ResetStatus();
		}
		
		CommunityCards.clear();
		GameDeck.NewDeck();
	}
	
	public void ResetBets()
	{
		for (int i = 0; i < Players.size(); i++)
		{
			Players.get(i).BetSubtracted();
		}
	}
	
	public void MoveRoles()
	{
		do
		{
			DealerIndex = (DealerIndex + 1) % NumberOfPlayers;
		}
		while (Players.get(DealerIndex).IsOut());
		Players.get(DealerIndex).SetDealer();
		
		do
		{
			SmallBlindIndex = (DealerIndex + 1) % NumberOfPlayers;
		}
		while (Players.get(SmallBlindIndex).IsOut());
		Players.get(SmallBlindIndex).SetSmallBlind();
		
		do
		{
			BigBlindIndex = (SmallBlindIndex + 1) % NumberOfPlayers;
		}
		while (Players.get(BigBlindIndex).IsOut());
		Players.get(BigBlindIndex).SetBigBlind();
	}
	
// End Resetters

	
	
	
	
// Cosmetics
	
	public void PrintHands()
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (!Players.get(i).IsFolded())
			{
				System.out.print(Players.get(i).GetName() + " has ");
				Players.get(i).PrintHand();
				System.out.println();
			}
		}
	}
	
	public void PrintStacks()
	{
		for (int i = 0; i < Players.size(); i++)
		{
			System.out.println(Players.get(i).GetName() + " has " + Players.get(i).GetStack());
		}
	}
	
// End Cosmetics
	
	
	
	
	
// Cards
	
	public void DealHands()
	{
		for (int c = 1; c <= 2; c++)
		{
			int i = (DealerIndex + 1) % NumberOfPlayers;
			do
			{
				System.out.println(Players.get(i).GetName() + " was dealt a card");
				Players.get(i).AddToPlayerHandAndHoldings(GameDeck.Deal());
				i = (i + 1) % NumberOfPlayers;
			}
			while (i != (DealerIndex + 1) % NumberOfPlayers);
		}
	}
	
	public void FlopCards()
	{
		for (int c = 1; c <= 3; c++)
		{
			CommunityCards.add(GameDeck.Deal());
		}
		
		System.out.print("The board is ");
		
		for (int i = 0; i < CommunityCards.size(); i++)
		{
			CommunityCards.get(i).PrintCard();
		}
		
		System.out.println();
	}
	
	public void TurnAndRiverCards()
	{
		CommunityCards.add(GameDeck.Deal());
		
		System.out.print("The board is ");
		
		for (int i = 0; i < CommunityCards.size(); i++)
		{
			CommunityCards.get(i).PrintCard();
		}
		
		System.out.println();
	}
	
// End Cards
	
	
	
	
	
// Facilitators
		
	/*
	public void RemoveBankruptPlayers()
	{
		for (int i = Players.size() - 1; i >= 0; i--)
		{
			if(Players.get(i).IsOut())
			{
				Players.remove(i);
			}
		}
	}
	*/
	
	public boolean CountFoldedPlayersAndCheckForWinner()
	{
		NumberOfPlayersFolded = 0;
		
		for (int i = 0; i < Players.size(); i++)
		{
			if (Players.get(i).IsFolded())
			{
				NumberOfPlayersFolded++;
			}
		}
		
		if (NumberOfPlayersFolded == NumberOfPlayers - 1)
		{
			RoundInProgress = false;
			return true;
		}
		
		return false;
	}
	
	public double FindStrongestHand()
	{
		double StrongestHand = 0;
		double HandStrength;
		
		for (int i = 0; i < Players.size(); i++)
		{
			HandStrength = Players.get(i).ComputeHandStrength(CommunityCards);
			//System.out.println(Players.get(i).GetHandStrength());
			if (HandStrength >= StrongestHand)
			{
				StrongestHand = HandStrength;
			}
		}
		
		return StrongestHand;
	}

	public int CountAndSetNumberOfWinners(double StrongestHand)
	{
		int NumberOfWinners = 0;
		for (int i = 0; i < Players.size(); i++)
		{
			if (!Players.get(i).IsFolded())
			{
				if (Players.get(i).GetHandStrength() == StrongestHand)
				{
					Players.get(i).SetWinner();
					NumberOfWinners++;
				}
			}
		}
		
		return NumberOfWinners;
	}
	
// End Facilitators
	
	
	
	
	
// Winnings

	public void SetMainPot()
	{
		//Helper method for SetSidePot
		for (int i = 0; i < Players.size(); i++)
		{
			PotSize += Players.get(i).GetBet();
		}
	}
	
	public void SetAllPots()
	{
		SetMainPot();
		
		for (int i = 0; i < Players.size(); i++)
		{
			if (Players.get(i).IsAllIn())
			{
				int IndividualSidePot = 0;
				
				for (int j = 0; j < Players.size(); j++)
				{
					if (Players.get(j).GetBet() < Players.get(i).GetBet())
					{
						IndividualSidePot += Players.get(j).GetBet();
					}
					
					else
					{
						IndividualSidePot += Players.get(i).GetBet();
					}
				}
				
				if (IndividualSidePot < PotSize && IndividualSidePot != 0)
				{
					Players.get(i).AddToSidePot(IndividualSidePot);
					System.out.println(Players.get(i).GetName() + " is fighting for a side pot of " + IndividualSidePot);
				}
			}
			
			else
			{
				Players.get(i).SetSidePot(PotSize);
			}
		}
	}
	
	public void AwardPotToWinnerOfFolds()
	{
		for (int i = 0; i < Players.size(); i++)
		{
			if (!Players.get(i).IsFolded())
			{
				Players.get(i).SetWinner();
				
				
				System.out.println(Players.get(i).GetName() + " wins a pot of $" + PotSize);
				Players.get(i).AddWinningsToStack(PotSize);
			}
		}
	}
	
	//Need to fix when there are only 2 people in an all-in run
	public void AwardPots()
	{
		double StrongestHand = FindStrongestHand();
		int NumberOfWinners;
		int TemporaryPot = 0;
		
		while (PotSize > 0)
		{
			NumberOfWinners = CountAndSetNumberOfWinners(StrongestHand);
			
			if (NumberOfWinners > 1)
			{
				int TasksPerformed = 0;
				
				while (TasksPerformed < NumberOfWinners)
				{
					for (int i = Players.size() - 1; i >= 0; i--)
					{
						if (Players.get(i).IsWinner() && !Players.get(i).IsFolded())
						{
							TemporaryPot = Players.get(i).GetSidePot();
							System.out.println(Players.get(i).GetName() + " splits a pot of $" + TemporaryPot + ", winning $" + TemporaryPot/NumberOfWinners + " with " + Players.get(i).GetHandName());
							Players.get(i).AddWinningsToStack(TemporaryPot/NumberOfWinners);
							Players.get(i).SetFold();
							TasksPerformed++;
						}
					}
				}
				
				PotSize -= TemporaryPot;
			}
			
			else
			{
				for (int i = 0; i < Players.size(); i++)
				{
					if (Players.get(i).IsWinner() && !Players.get(i).IsFolded())
					{
						TemporaryPot = Players.get(i).GetSidePot();
						System.out.println(Players.get(i).GetName() + " wins a pot of $" + TemporaryPot + " with " + Players.get(i).GetHandName());
						Players.get(i).AddWinningsToStack(TemporaryPot);
						Players.get(i).SetFold();
						
						PotSize -= TemporaryPot;
					}
				}
			}
		}
	}

// End Winnings

	
	
	

// Action
	
	public void AskInputCall(int i, int PreFlop)
	{
		System.out.println("It's " + Players.get(i).GetName() + "'s turn to act.");
		
		while(true)
		{
			System.out.println("Would you like to (1)Call " + RoundBet + ", (2)Raise, or (3)Fold?");
			int c = scanner.nextInt();
			
			if (c == 1)
			{
				Players.get(i).Call(RoundBet);
				Action = RoundBet;
				break;
			}
			
			else if (c == 2)
			{
				while(true)
				{
					System.out.println("What would you like to raise to?");
					int r = scanner.nextInt();
					
					if (r >= 2*RoundBet - PreFlop || Players.get(i).GetStack() <= r)
					{
						Players.get(i).Raise(RoundBet, r);
						Action = r;
					}
					
					else
					{
						System.out.println("Please enter a valid raise");
					}
					
					break;
				}
				
				break;
			}
			
			else if (c == 3)
			{
				Players.get(i).Fold();
				Action = 0;
				break;
			}
			
			else
			{
				System.out.println("Please enter a valid option");
			}
		}
	}
	
	public void AskInputCheckPreFlop(int i)
	{
		System.out.println("It's " + Players.get(i).GetName() + "'s turn to act.");
		
		while(true)
		{
			System.out.println("Would you like to (1)Check, (2)Raise, or (3)Fold?");
			int c = scanner.nextInt();
			
			if (c == 1)
			{
				Players.get(i).Check(RoundBet);
				Action = RoundBet;
				break;
			}
			
			else if (c == 2)
			{
				while(true)
				{
					System.out.println("What would you like to raise to?");
					int r = scanner.nextInt();
					
					if (r >= 2*RoundBet - 5 || Players.get(i).GetStack() <= r)
					{
						Players.get(i).Bet(RoundBet, r);
						Action = r;
					}
					
					else
					{
						System.out.println("Please enter a valid raise");
					}
					
					break;
				}
				
				break;
			}
			
			else if (c == 3)
			{
				Players.get(i).Fold();
				Action = 0;
				break;
			}
			
			else
			{
				System.out.println("Please enter a valid option");
			}
		}
	}
	
	public void AskInputCheckPostFlop(int i)
	{
		System.out.println("It's " + Players.get(i).GetName() + "'s turn to act.");
		
		while(true)
		{
			System.out.println("Would you like to (1)Check, (2)Bet, or (3)Fold?");
			int c = scanner.nextInt();
			
			if (c == 1)
			{
				Players.get(i).Check(RoundBet);
				Action = RoundBet;
				break;
			}
			
			else if (c == 2)
			{
				while(true)
				{
					System.out.println("What would you like to raise to?");
					int r = scanner.nextInt();
					
					if (r >= 5 || Players.get(i).GetStack() <= r)
					{
						Players.get(i).Bet(RoundBet, r);
						Action = r;
					}
					
					else
					{
						System.out.println("Please enter a valid raise");
					}

					break;
				}
				
				break;
			}
			
			else if (c == 3)
			{
				Players.get(i).Fold();
				Action = 0;
				break;
			}
			
			else
			{
				System.out.println("Please enter a valid option");
			}
		}
	}
	
	public void AskInputNoRaise(int i)
	{
		while(true)
		{
			System.out.println("Would you like to (1)Call or (2)Fold?");
			int c = scanner.nextInt();
			
			if (c == 1)
			{
				Players.get(i).Call(RoundBet);
				Action = RoundBet;
			}
			
			else if (c == 2)
			{
				Players.get(i).Fold();
				Action = 0;
			}
			
			else
			{
				System.out.println("Please enter a valid option");
			}
		}
	}
	
	public void ActionInProgressPreFlop()
	{
		RoundBet = 5;
		NumberOfPlayersLeftToAct = NumberOfPlayers;
		while (NumberOfPlayersLeftToAct > 0 && RoundInProgress)
		{
			int i = (DealerIndex + 3) % NumberOfPlayers;
			do
			{
				Action = 0;
				
				if (Players.get(i).IsPlayerReadyPreFlop(RoundBet))
				{					
					if (Players.get(i).CanCheck(RoundBet))
					{
						AskInputCheckPreFlop(i);
					}
					
					else
					{
						AskInputCall(i, 5);
					}
				}
				
				if (CountFoldedPlayersAndCheckForWinner())
				{
					break;
				}
				
				if (Action > RoundBet)
				{
					RoundBet = Action;
					NumberOfPlayersLeftToAct = NumberOfPlayers;
				}
				
				else
				{
					NumberOfPlayersLeftToAct--;
				}
				
				i = (i + 1) % NumberOfPlayers;
			}
			while (i != (DealerIndex + 3) % NumberOfPlayers);
		}
	}
	
	public void ActionInProgressPostFlop()
	{
		RoundBet = 0;
		NumberOfPlayersLeftToAct = NumberOfPlayers;
		while (NumberOfPlayersLeftToAct > 0 && RoundInProgress)
		{
			int i = (DealerIndex + 1) % NumberOfPlayers;
			do
			{
				Action = 0;
				
				if (Players.get(i).IsPlayerReadyPostFlop(RoundBet))
				{					
					if (Players.get(i).CanCheck(RoundBet))
					{
						AskInputCheckPostFlop(i);
					}
					
					else
					{
						AskInputCall(i, 0);
					}
				}
				
				if (CountFoldedPlayersAndCheckForWinner())
				{
					break;
				}
				
				if (Action > RoundBet)
				{
					RoundBet = Action;
					NumberOfPlayersLeftToAct = NumberOfPlayers;
				}
				
				else
				{
					NumberOfPlayersLeftToAct--;
				}
				
				i = (i + 1) % NumberOfPlayers;
			}
			while (i != (DealerIndex + 1) % NumberOfPlayers);
		}
	}
	
	public void PreFlop()
	{
		ResetGameStatus();
		MoveRoles();
		DealHands();
		
		PrintHands();
		PrintStacks();
		
		ActionInProgressPreFlop();
		
		SetAllPots();
		ResetBets();
		
		if (RoundInProgress)
		{
			System.out.println("The pot size is $" + PotSize);
		}
		
		else
		{
			AwardPotToWinnerOfFolds();
		}
	}
	
	public void Flop()
	{
		if (RoundInProgress)
		{
			FlopCards();
			
			ActionInProgressPostFlop();
			
			SetAllPots();
			ResetBets();
			
			if (RoundInProgress)
			{
				System.out.println("The pot size is $" + PotSize);
			}
			
			else
			{
				AwardPotToWinnerOfFolds();
			}
		}
	}
	
	public void Turn()
	{
		if (RoundInProgress)
		{
			TurnAndRiverCards();
			
			ActionInProgressPostFlop();
			
			SetAllPots();
			ResetBets();
			
			if (RoundInProgress)
			{
				System.out.println("The pot size is $" + PotSize);
			}
			
			else
			{
				AwardPotToWinnerOfFolds();
			}
		}
	}
	
	public void River()
	{
		if (RoundInProgress)
		{
			TurnAndRiverCards();
			
			ActionInProgressPostFlop();			
			
			SetAllPots();
			ResetBets();
			
			if (RoundInProgress)
			{
				PrintHands();
				AwardPots();
			}
			
			else
			{
				AwardPotToWinnerOfFolds();
			}
			
			//RemoveBankruptPlayers();
		}
	}
	
// End Action
	

	
	
	
// Game
	
	public void StartGame()
	{	
		//Change number of players limit
		while (NumberOfPlayers <= 10)
		{
			System.out.println("Round " + (RoundCounter+1));
			
			PreFlop();
			Flop();
			Turn();
			River();
			
			RoundInProgress = true;
			RoundCounter++;
		}
	}
	
// End Game
	
}
