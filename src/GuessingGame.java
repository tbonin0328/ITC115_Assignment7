import java.util.Random;
import java.util.Scanner;

/**
 * @author thomasbonin
 * ITC 115, Assignment 6, Guessing Game
 */
public class GuessingGame 

{
	private Scanner in = new Scanner(System.in); 
	private Random rGen = new Random();
	
    private static final int MAX_GUESSES = 50;
    
    private int numGames = 0;
    private int numGuessesThis = 0;
    private int numGuessesAll = 0;
    private int bestScore = 50;
    private int goalNum = 0;
    private int yourGuess = 0;
    private String loseMessage = "Sorry, you lost this round";
    private String higherMessage = "It's higher";
    private String lowerMessage = "It's lower";
    
	/**
	 * This is the main play method. It sends a message to the user that 
	 * a number between 1 and 100 has been chosen. It runs newGame() in a 
	 * while loop as long as the user responds "Y" to the question "Play Again".
	 * If user does not say "Y", then the system posts game results.
	 */
	public void play()
	{
		System.out.println("I'm thinking of a number between 1 and 100...");
		
		do
		{
			newGame();
		}
		while(playAgain());
		gameResults();
	}
	
	/**
	 * This method posts results of the game when called. It uses the numGames
	 * variable to post total games played. It uses numGuessesAll/numGames to 
	 * post Guesses/game. To calculate best score, it takes the game results from the 
	 * array of results and finds the lowest number of guesses needed to win from the 
	 * numGuessesAll array.
	 */
	private void gameResults() 
	{
		System.out.println("Your Overall Results:");
		System.out.println("Total Games: " + numGames);
		System.out.println("Guesses/game: " + numGuessesAll/numGames);
		System.out.println("Best Score:" + bestScore);
	}

	/**
	 * This method increases the number of games by one and resets the number of guesses
	 * for this game only to 0. The method then processes newGuess() as long as the
	 * number of guesses is less than or equal to 50. 
	 */
	private void newGame() 
	{
		numGames++;
		numGuessesThis = 0;
		while (numGuessesThis <= 50)
		{
			newGuess();
		}
		
	}
	
	private void newGuess()
	{
		goalNum = makeGoalNum(); //20
		
		while (true)
		{
			yourGuess = Guess(); //yourGuess = 6
			numGuessesThis++;
			numGuessesAll++;
			
			if (yourGuess == goalNum)
			{
				winProcess();
				break;
			}
			else if (yourGuess > goalNum)
			{
				printLine(lowerMessage);
				System.out.println(goalNum);
				guessesLeft(numGuessesThis);
			}
			else
			{
				printLine(higherMessage);
				System.out.println(goalNum);
				guessesLeft(numGuessesThis);
			}
		}
		return;
	}

	private void guessesLeft(int numGuesses) 
	{	
		printLine("You have " + (MAX_GUESSES - numGuesses) + " guesses left.");
	}

	private boolean testGuess(int guess, int goal) 
	{
		return guess == goal;
	}

	private void winProcess() 
	{
		System.out.println("You guessed it in " + numGuessesThis + " guess(es)");
		if (numGuessesThis <= bestScore)
		{
			numGuessesThis = bestScore;	
		}
	}

	private int makeGoalNum() 
	{
		return rGen.nextInt(100) + 1;
	}
	
	public boolean playAgain()
	{
		String answer = readLine("Play again?").toLowerCase();
		return answer.equals("y");
	}
	
	//make a readLine method
	public String readLine(String prompt)
	{
		System.out.print(prompt);
		return in.nextLine();
	}
	
	public void printLine(String text)
	{
		System.out.println(text);
	}
	
	//make a readInt method
	public int readInt(String prompt)
	{
		System.out.print(prompt);
		return in.nextInt();
	}
	
	public int Guess()
	{
		return readInt("Your guess? ");
	}
	
	public static void main(String[] args)
	{
		GuessingGame program = new GuessingGame();
		program.play();
	}
}
