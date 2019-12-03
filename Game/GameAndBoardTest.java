package Game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameAndBoardTest {

	private Game game;	
	
	
	@Before
	public void setUp() {
		
		game = new Game();
	
		
	}
	
	@Test
	public void testGameSetBoard() {
		
		try {
			Board board = new Board();
			
			game.setBoard(board);
			
			assertNotNull(game.getBoard());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBoardConstructorAndGetGameBoard() {
		
		try {
			Board board = new Board();
			
			Piece actualPiece = PieceFactory.getLance("down", "");
			
			Piece expectedPiece = board.getGameBoard()[0][0];
			
			assertEquals(expectedPiece.getId(), actualPiece.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}