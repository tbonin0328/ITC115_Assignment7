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
    private int firstAsk = 0;
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
		
		//firstAsk = Guess();
		//System.out.println(numGuessesThis);
		
		do 
		{
			newGame();
		}
		while (playAgain());
	}
	
	/**
	 * This method posts results of the game when called. First, it finds out if 
	 * the number of guesses for the game just won is less than or equal to the
	 * best score on record. If so, it changes the bestScore to numGuessesThis.
	 * The method then uses the numGames variable to post total games played. It 
	 * uses numGuessesAll/numGames to post Guesses/game. It posts the bestScore
	 * derived from the if statement at the top of this method.
	 */
	private void gameResults() 
	{
		if (numGuessesThis <= bestScore)
		{
			numGuessesThis = bestScore;	
		}
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
		//because new game has been selected, increment numGames for gameResults method.
		numGames++;
		//because new game has been selected, set number of guesses for this game back to zero.
		numGuessesThis = 0;
		//start the guess process.
		System.out.println("I'm thinking of a number between 1 and 100...");
		//process first game
		newGuess();
	}
	
	private void newGuess()
	{
		goalNum = makeGoalNum(); //20
		
		if (numGuessesThis <= 50)
		{
			while(true)
			{
				yourGuess = Guess(); //yourGuess = 6
	
				if (yourGuess == goalNum)
				{
					winProcess();
					break;
				}
				else if (yourGuess > goalNum)
				{
					printLine(lowerMessage);
					System.out.println(goalNum + ", " + numGuessesThis + ", " + numGuessesAll);
					guessesLeft(numGuessesThis);
				}
				else if (yourGuess < goalNum)
				{
					printLine(higherMessage);
					System.out.println(goalNum + ", " + numGuessesThis + ", " + numGuessesAll);
					guessesLeft(numGuessesThis);
				}
				else
				{
					printLine("Not a guess");
				}
			}
		}
		else 
		{
			printLine(loseMessage);
		}
	}

	private void guessesLeft(int numGuesses) 
	{	
		printLine("You have " + (MAX_GUESSES - numGuesses) + " guesses left.");
	}
	
	//private boolean firstAskTest()
	//{
		
	//}

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
		if (!answer.equals("y"))
		{
			gameResults();
			return false;
		}
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
		numGuessesThis++;
		numGuessesAll++;
		return readInt("Your guess? ");
	}
	
	public static void main(String[] args)
	{
		GuessingGame program = new GuessingGame();
		program.play();
	}
}
