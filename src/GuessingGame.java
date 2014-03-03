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
		System.out.println("Best Score:" );
	}

	private void newGame() 
	{
		numGames++;
		numGuessesThis = 0;
		while (numGuessesThis <= 50)
		{
			newGuess();
		}
		numGuessesThis++;
	}
	
	private void newGuess()
	{
		goalNum = makeGoalNum();
		yourGuess = takeGuess();
		
		while (!testGuess(yourGuess, goalNum))
		{
			yourGuess = takeGuess();
			numGuessesThis++;
			numGuessesAll++;
			testGuess(yourGuess, goalNum);
		}
		winMessage();
		//put numGuessesThis in gameTotals array.
		
	}

	private boolean testGuess(int guess, int goal) 
	{
		return guess == goal;
	}

	private void winMessage() 
	{
		System.out.println("You guessed it in " + numGuessesThis + " guess(es)");
	}

	private int takeGuess(int guess, int goal) 
	{
		if (guess > goal)
		{
			printLine(lowerMessage);
			readInt("Your guess? ");
		}

		if (guess < goal);
		{
			printLine(higherMessage);
			takeGuess();
		}
		winMessage();
		printLine(loseMessage);
		
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
	
	public static void main(String[] args)
	{
		GuessingGame program = new GuessingGame();
		program.play();
	}
}
