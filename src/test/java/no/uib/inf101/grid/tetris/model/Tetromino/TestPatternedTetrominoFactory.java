package no.uib.inf101.grid.tetris.model.Tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

/*
 * Test for PatternedTetrominoFactory for å sjekke om den returnerer riktig sekvens av brikker.
 */
public class TestPatternedTetrominoFactory {

    @Test
    public void testPatternedTetrominoFactory() {
        TetrominoFactory factory = new PatternedTetrominoFactory("TSZ");

        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
        assertEquals(Tetromino.newTetromino('Z'), factory.getNext());
        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
    }
}
