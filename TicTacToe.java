import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//Author Gabriel Brown

public class TicTacToe {

	public static int boardSize = 9;
	public static Scanner scan = new Scanner(System.in);
	public static char board[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static final char earthlingsTurn = 'X';
	public static final char artificialIntelligencesTurn = 'O';
	
	private Random mRand; 
	
	public TicTacToe() {
		
		
		mRand = new Random(); 
				
		char turn = earthlingsTurn;    
		int  win = 0;                
		
		while (win == 0)
		{	
			PrintBoard();

			if (turn == earthlingsTurn)
			{
				retrievePlayersMove();
				turn = artificialIntelligencesTurn;
			}
			else
			{
				retrieveAIMove();
				turn = earthlingsTurn;
			}	

			win = checkWin();
		}

		PrintBoard();

		System.out.println();
		if (win == 1)
			System.out.println("There is a tie!");
		else if (win == 2)
			System.out.println("YAY! The game is Done. " + earthlingsTurn + " is the winner!:-)");
		else if (win == 3)
			System.out.println("YAY! The game is Done. " + artificialIntelligencesTurn + " is the winner! :-)");
		else
			System.out.println("There is a logic problem!");
	}
	
	public void PrintBoard()	{
		
		System.out.println();
		System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
		System.out.println("***********");
		System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
		System.out.println("***********");
		System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
		System.out.println();
}
	// Check for a winner.  Return
	//  0 if no winner or tie yet
	//  1 if it's a tie
	//  2 if X won
	//  3 if O won
	public static int checkWin() {
		
		// Check horizontal wins
		for(int i = 0; i <= 6; i += 3) {
			if (board[i] == earthlingsTurn && 
				board[i+1] == earthlingsTurn &&
				board[i+2]== earthlingsTurn)
				return 2;
			if (board[i] == artificialIntelligencesTurn && 
				board[i+1]== artificialIntelligencesTurn && 
				board[i+2] == artificialIntelligencesTurn)
				return 3;
		}
	
		// Check vertical wins
		for(int i = 0; i <=2; i++) {
			if (board[i] == earthlingsTurn && 
				board[i+3] == earthlingsTurn && 
				board[i+6] == earthlingsTurn)
				return 2;
			if (board[0] == artificialIntelligencesTurn && 
				board[i+3] == artificialIntelligencesTurn && 
				board[i+6]== artificialIntelligencesTurn)
				return 3;
		}
	
		// Check for diagonal wins
		if ((board[0] == earthlingsTurn &&
				 board[4] == earthlingsTurn && 
				 board[8] == earthlingsTurn) ||
				(board[2] == earthlingsTurn && 
				 board[4] == earthlingsTurn &&
				 board[6] == earthlingsTurn))
				return 2;
			if ((board[0] == artificialIntelligencesTurn &&
				 board[4] == artificialIntelligencesTurn && 
				 board[8] == artificialIntelligencesTurn) ||
				(board[2] == artificialIntelligencesTurn && 
				 board[4] == artificialIntelligencesTurn &&
				 board[6] == artificialIntelligencesTurn))
				return 3;
	
		// Check for tie
		for (int i = 0; i < boardSize; i++) {
			if (board[i] != earthlingsTurn && board[i] != artificialIntelligencesTurn)
					return 0;
			}
		
			return 1;
		}
		
	
	
	
	void retrievePlayersMove() 
	{
		int move = -1;
		
		while (move == -1) {			
			try {
				System.out.print("Make your move: ");
			    move = scan.nextInt();
			
				while (move < 1 || move > boardSize || 
					   board[move-1] == earthlingsTurn || board[move-1] == artificialIntelligencesTurn) {
					
					if (move < 1 || move > boardSize)
						System.out.println("Please enter a move between 1 and " + boardSize + ".");
					else
						System.out.println("Sorry that space is ocupado, choose another!");
		
					System.out.print("Make your move: ");
				    move = scan.nextInt();
				}
			} 
			catch (InputMismatchException ex) {
				System.out.println("Enter a number between 1 and " + boardSize + ".");
				scan.next();  
				move = -1;
			}
		}

		board[move-1] = earthlingsTurn;
	}
	
	private void retrieveAIMove() 
	{
		int move;

		for (int i = 0; i < boardSize; i++) {
			if (board[i] != earthlingsTurn && board[i] != artificialIntelligencesTurn) {
				char curr = board[i];
				board[i] = artificialIntelligencesTurn;
				if (checkWin() == 3) {
					System.out.println("The A.I. is going to move to " + (i + 1));
					return;
				}
				else
					board[i] = curr;
			}
		}

		for (int i = 0; i < boardSize; i++) {
			if (board[i] != earthlingsTurn && board[i] != artificialIntelligencesTurn) {
				char curr = board[i];   
				board[i] = earthlingsTurn;
				if (checkWin() == 2) {
					board[i] = artificialIntelligencesTurn;
					System.out.println("The A.I. is going to move to " + (i + 1));
					return;
				}
				else
					board[i] = curr;
			}
		}

		do
		{
			move = mRand.nextInt(boardSize);
		} while (board[move] == earthlingsTurn || board[move] == artificialIntelligencesTurn);
			
		System.out.println("The A.I. is going to move to " + (move + 1));

		board[move] = artificialIntelligencesTurn;
	}	
	
	
	public static void main(String[] args) {		
		new TicTacToe();		
	}
}
