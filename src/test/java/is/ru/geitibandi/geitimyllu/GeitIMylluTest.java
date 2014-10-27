package is.ru.geitibandi.geitimyllu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.Before;

// Unit tests for the class GeitIMyllu
public class GeitIMylluTest {

    private GeitIMyllu game;

    @Before
    public void setUp() {
        game = new GeitIMyllu();
    }

    @Test
    public void gameNotNull() {
        assertNotNull(game);
    }

    @Test
    public void gameStateWhenNothingHasHappend() {
        final int value = 1;
        int result = game.getGameState();
       assertEquals(value, result);
    }

    @Test
    public void boardInitialzeTest() {
       final String value = "---------";
       String result = game.boardAsString();
       assertEquals(value, result);
    }

    @Test
    public void getCellFeatureTest() {
       final char value = '-';
       char result = game.getCell(0, 0);
       assertEquals(value, result);
    }

    @Test
    public void playExceptionIfOutOfBoard() {
        boolean thrown = false;
        try {
            game.play(1, 4);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void playExceptionIfCellOccupied() {
        boolean thrown = false;
        game.play(0, 0);
        try {
            game.play(0, 0);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void gameStateAfterOneTurn() {
        game.play(1, 1);
        assertEquals(2, game.getGameState());
    }

    @Test
    public void gameStateAfterTwoTurns() {
        game.play(0, 0);
        game.play(1, 1);
        assertEquals(1, game.getGameState());
    }

    @Test
    public void gameOverRow() {
        game.play(1, 0);
        game.play(0, 2);
        game.play(1, 1);
        game.play(2, 2);
        game.play(1, 2);
        assertTrue(game.checkRowsForWin());
    }

    @Test
    public void gameOverColum() {
        game.play(0, 1);
        game.play(1, 2);
        game.play(1, 1);
        game.play(2, 2);
        game.play(2, 1);
        assertTrue(game.checkColsForWin());
    }

    @Test
    public void gameOverDiag() {
        game.play(0, 0);
        game.play(1, 2);
        game.play(1, 1);
        game.play(0, 1);
        game.play(2, 2);
        assertTrue(game.checkDiagForWin());
    }

    @Test
    public void gameOverTest() {
        game.play(0, 0);
        game.play(2, 1);
        game.play(1, 1);
        game.play(1, 2);
        game.play(2, 2);
        assertTrue(game.gameWon());
    }

    @Test
    public void boardFullTest() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 1);
        game.play(1, 0);
        game.play(2, 0);
        game.play(1, 2);
        game.play(2, 2);
        game.play(2, 1);
        assertTrue(game.boardFull());
    }

    @Test
    public void playerMarkIfGameStateIs1() {
        final char value = 'X';
        char result = game.playerMark();
        assertEquals(value, result);
    }

    @Test
    public void playerMarkIfGameStateIs2() {
        game.play(1, 1);
        assertEquals('O', game.playerMark());
    }

    @Test
    public void playerMarkIfGameStateIs5() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 1);
        game.play(1, 0);
        game.play(2, 0);
        game.play(1, 2);
        game.play(2, 2);
        game.play(2, 1);
        assertEquals('-', game.playerMark());
    }

    @Test
    public void gameReset() {
        game.play(2, 1);
        game.play(1, 1);
        game.resetGame();
        assertEquals('-', game.getCell(1, 1));
    }

    @Test
    public void testGetTurnFunctionAfterOneTurn() {
        game.play(1, 1);
        assertEquals(1, game.getTurn());
    }

    @Test
    public void testGetCurrentPlayerFunction() {
        assertEquals('X', game.getCurrentPlayer());
    }

    @Test
    public void testGetPlayedGamesFunctionAfterOneReset() {
        game.resetGame();
        assertEquals(1, game.getPlayedGames());
    }

    @Test
    public void testGamesWonByPlayerXAfterOneWin() {
        game.play(0, 0);
        game.play(1, 0);
        game.play(0, 1);
        game.play(1, 1);
        game.play(0, 2);
        assertEquals(1, game.getGamesWonByPlayerX());
    }

    @Test
    public void testGamesWonByPlayerOAfterOneTie() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 1);
        game.play(1, 0);
        game.play(2, 0);
        game.play(1, 2);
        game.play(2, 2);
        game.play(2, 1);
        assertEquals(0, game.getGamesWonByPlayerO());
    }

    @Test
    public void testGameTiesAfterOneTie() {
        game.play(0, 0);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 1);
        game.play(1, 0);
        game.play(2, 0);
        game.play(1, 2);
        game.play(2, 2);
        game.play(2, 1);
        assertEquals(1, game.getTies());
    }
}
