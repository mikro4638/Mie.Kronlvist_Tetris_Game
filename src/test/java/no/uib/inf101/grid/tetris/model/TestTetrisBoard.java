package no.uib.inf101.grid.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import no.uib.inf101.grid.CellPosition;
import org.junit.jupiter.api.Test;
import no.uib.inf101.tetris.model.TetrisBoard;

/**
 * Testklasse for å teste TetrisBoard-funksjonalitet.
 */
public class TestTetrisBoard {

    /**
     * Tester fjerning av fulle rader fra brettet.
     */
    @Test
    public void testRemoveFullRows() {
        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 1), 'T');
        board.set(new CellPosition(4, 0), 'L');
        board.set(new CellPosition(4, 1), 'L');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(2, 0), 'L');

        assertEquals(3, board.removeFullRows());

        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "-T",
            "L-"
        });
        assertEquals(expected, board.prettyString());
    }
    
    /**
     * Tester at bunnen av brettet ikke fjernes.
     */
    @Test
    public void testKeepBottomRow() {
        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(4, 0), 'O');
        board.set(new CellPosition(4, 1), 'O');

        assertEquals(1, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "--",
                "--",
                "--"
        });
        assertEquals(expected, board.prettyString());
    }

    /**
     * Tester at toppen av brettet fjernes.
     */
    @Test
    public void testRemoveTopRow() {
        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(0, 0), 'O');
        board.set(new CellPosition(0, 1), 'O');
        board.set(new CellPosition(1, 0), 'O');
        board.set(new CellPosition(1, 1), 'O');

        assertEquals(2, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "--",
                "--",
                "--",
                "--",
                "--"
        });
        assertEquals(expected, board.prettyString());
    }

    /**
     * Tester at fjerning av fulle rader fungerer med ulik brettbredde.
     */
    @Test
    public void testDifferentWidthOnBoard() {
        TetrisBoard board = new TetrisBoard(4, 5);
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(1, 2), 'T');
        board.set(new CellPosition(1, 3), 'L');
        board.set(new CellPosition(1, 4), 'L');

        assertEquals(1, board.removeFullRows());

        String expected = String.join("\n", new String[] {
                "-----",
                "-----",
                "-----",
                "-----"
        });
        assertEquals(expected, board.prettyString());
    }
}
