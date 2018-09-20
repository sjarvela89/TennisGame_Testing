import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Ignore
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}
	@Test
	public void testNoNullPoints() throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String i = game.getScore();
		//Assert
		assertNotNull(i);
	}
	@Test
	public void testPlayer2CanHaveAdvantage()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		String i = game.getScore();
		assertSame(i,"player2 has advantage");
		//This test caused failure. Fixes done into the code.
	}
	@Test
	public void testPlayer2CanWin53()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		String i = game.getScore();
		assertSame(i,"player2 wins");
	}
	@Test
	public void testPlayersCanPlayLongDeuces()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		String i = game.getScore();
		assertSame(i,"deuce");
	}
	@Test
	public void testPlayer1WinsWithCorrectAdvantage()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		String i = game.getScore();
		assertSame(i,"player1 wins");
	}
	@Test
	public void testPlayer1StaysLove()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		String i = game.getScore();
		System.out.println("PrintOfTheGetScore:"+i);
		//Assert
		assertEquals(i,"15 - love");
		//Act
		game.player2Scored();
		i = game.getScore();
		//Assert
		assertEquals(i,"30 - love");
		//Act
		game.player2Scored();
		i = game.getScore();
		//Assert
		assertEquals(i,"40 - love");
		//Act
		game.player2Scored();
		i = game.getScore();
		//Assert
		assertSame(i,"player2 wins");
		
	}
	@Test
	public void testPlayer2StaysLove()throws TennisGameException{
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		String i = game.getScore();
		System.out.println("PrintOfTheGetScorePlayer2Love:"+i);
		//Assert
		assertEquals(i,"love - 15");
		//Act
		game.player1Scored();
		i = game.getScore();
		//Assert
		assertEquals(i,"love - 30");
		//Act
		game.player1Scored();
		i = game.getScore();
		//Assert
		assertEquals(i,"love - 40");
		//Act
		game.player1Scored();
		i = game.getScore();
		//Assert
		assertSame(i,"player1 wins");
		
	}
}
