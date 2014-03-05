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
	
    private static final int MAX_GUESSES = 3;
    
    private int numGames = 0;
    public int numGuessesThis = 0;
    private int numGuessesAll = 0;
    private int bestScore = 0;
    private int goalNum = 0;
    private int yourGuess = 0;
    private String loseMessage = "Sorry, you lost this round";
    private String higherMessage = "It's higher";
    private String lowerMessage = "It's lower";
    private String playAgain = "";
    
	/**
	 * This is the main play method. It sends a message to the user that 
	 * a number between 1 and 100 has been chosen. It runs newGame() in a 
	 * while loop as long as the user responds "Y" to the question "Play Again".
	 * If user does not say "Y", then the system posts game results.
	 */
	public void play()
	{	
		newGame();
	}
	
	/**
	 * This method posts results of the game when called. First, it finds out if 
	 * the number of guesses for the game just won is less than or equal to the
	 * best score on record. If so, it changes the bestScore to numGuessesThis.
	 * The method then uses the numGames variable to post total games played. It 
	 * uses numGuessesAll/numGames to post Guesses/game. It posts the bestScore
	 * derived from the if statement at the top of this method.
	 * @return 
	 */
	private boolean gameResults() 
	{
		if (numGuessesThis <= bestScore)
		{
			bestScore = numGuessesThis;	
		}
		
		System.out.println("Your Overall Results:");
		System.out.println("Total Games: " + numGames);
		
		if (numGames != 0)
		{
			System.out.println("Guesses/game: " + (double)numGuessesAll/(double)numGames);
			System.out.println("Best Score:" + bestScore);
		}
		
		else 
		{
			printLine("Guess/game: N/A because you didn't finish your first game.");
			printLine("Best Score: No Best Score either.");
		}
		return false;
	}

	/**
	 * This method increases the number of games by one and resets the number of guesses
	 * for this game only to 0. The method then processes newGuess() as long as the
	 * number of guesses is less than or equal to 50. 
	 */
	private void newGame() 
	{
			while(playAgain() && numGuessesThis != MAX_GUESSES)
				{	
					System.out.println("I'm thinking of a number between 1 and 100...");
					goalNum = makeGoalNum();

					while(guessCheck())
					{
							System.out.print("");
					}
				}
			if (numGuessesThis != MAX_GUESSES)
			{
				gameResults();
			}
	}
	
	/**
	 * @return: returns goalNum if works, otherwise returns false.
	 * This method checks to see if guess works.
	 */
	private boolean guessCheck() 
	{	
		while (totalTest())
		{
			yourGuess = Guess();
			
			if (yourGuess > goalNum)
			{
				printLine(lowerMessage);
				System.out.println(goalNum + ", " + numGuessesThis + ", " + numGuessesAll + ", " + numGames);
				guessesLeft();
			}
			else if (yourGuess < goalNum)
			{
				printLine(higherMessage);
				System.out.println(goalNum + ", " + numGuessesThis + ", " + numGuessesAll + ", " + numGames);
				guessesLeft();
			}
			else
			{
				if (yourGuess == goalNum)
				{
					winProcess();
				}
			}
			return yourGuess != goalNum;
		}
		return false;
	}

	/**
	 * @return: returns true if guesses are less than max guesses, false if not.
	 */
	private boolean totalTest() 
	{
		if (numGuessesThis < MAX_GUESSES)
		{
			return true;
		}
		else 
		{
			printLine(loseMessage);
			return false;
		}
	}

	/**
	 * Prints line telling how many guesses are left.
	 */
	private void guessesLeft() 
	{	
		if (numGuessesThis < MAX_GUESSES)
		{
			printLine("You have " + (MAX_GUESSES - numGuessesThis) + " guesses left.");
		}
		else
		{

		}
	}

	/**
	 * Prints how many guesses it took to win and changes best score.
	 */
	private void winProcess() 
	{
		System.out.println("You guessed it in " + numGuessesThis + " guess(es)");
		if (bestScore == 0)
		{
			bestScore = numGuessesThis;
		}
		else if (numGuessesThis <= bestScore)
		{
			bestScore = numGuessesThis;	
		}
	}

	/**
	 * @return: returns random number that acts as goal number for guesser.
	 */
	private int makeGoalNum() 
	{
		return rGen.nextInt(100) + 1;
	}
	
	/**
	 * @return: returns yes answer if user wants to play again an increments game counter.
	 */
	public boolean playAgain()
	{
		playAgain = "";
		if (numGames == 0)
		{	
			playAgain = "y";
			if (playAgain.equals("y"))
				{
					numGames++;
				}
		return playAgain.equals("y");
		}
		else
		{
			playAgain = readLine("Want to play again? Y or N: ").toLowerCase().trim();

			if (playAgain.equals("y"))
			{
				numGames++;
				numGuessesThis = 0;
			}
		return playAgain.equals("y");
		}
	}
	
	//make a readLine method
	public String readLine(String prompt)
	{
		System.out.print(prompt);
		return in.next();
	}
	
	//printline method.
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
	
	/**
	 * @return: returns guess from user and increments counters.
	 */
	public int Guess()
	{
		numGuessesThis++;
		numGuessesAll++;
		return readInt("Your guess? ");
	}
	
	/**
	 * @param args: main run method.
	 */
	public static void main(String[] args)
	{
		GuessingGame program = new GuessingGame();
		program.play();
	}
}
