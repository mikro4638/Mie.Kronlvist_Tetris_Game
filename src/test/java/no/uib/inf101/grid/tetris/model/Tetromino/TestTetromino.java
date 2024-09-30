package no.uib.inf101.grid.tetris.model.Tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.grid.GridDimension;

/**
 * Testklasse for å teste Tetromino-objekter.
 */
public class TestTetromino {

    /**
     * Tester hashCode- og equals-metodene til Tetromino.
     */
    @Test
    public void testHashCodeAndEquals() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1, t2);
        assertEquals(s1, s2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, s1);
    }

    /**
     * Tester iterasjon av T-formede Tetromino-objekter.
     */
    @Test
    public void tetrominoIterationOfT() {
        Tetromino tetro = Tetromino.newTetromino('T').shiftedBy(10, 100);
        List<GridCell<Character>> objs = new ArrayList<>();

        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }

    /**
     * Tester iterasjon av S-formede Tetromino-objekter.
     */
    @Test
    public void tetrominoIterationOfS() {
        Tetromino tetro = Tetromino.newTetromino('S').shiftedBy(10, 100);
        List<GridCell<Character>> objs = new ArrayList<>();

        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
    }

    /**
     * Tester dobbel bevegelse av Tetromino-objekter.
     */
    @Test
    public void testDoubleMove() {
        Tetromino tetro = Tetromino.newTetromino('T').shiftedBy(5, 5);
        Tetromino singleMove = tetro.shiftedBy(1, 0);
        Tetromino doubleMove = tetro.shiftedBy(2, 0);

        assertEquals(doubleMove.getPosition().col() - tetro.getPosition().col(), 2 * (singleMove.getPosition().col() - tetro.getPosition().col()));
        assertEquals(doubleMove.getPosition().row() - tetro.getPosition().row(), 2 * (singleMove.getPosition().row() - tetro.getPosition().row()));
    }

    /**
     * Tester plassering av Tetromino-objekter på brettet.
     */
    @Test
    public void testShiftedToTopCenterOf() {
        Tetromino tetromino4x4 = Tetromino.newTetromino('T');
        GridDimension gridDimension4x4 = new GridDimension() {
            @Override
            public int rows() {
                return 4;
            }

            @Override
            public int cols() {
                return 4;
            }

            @Override
            public int getRowCount() {
                return 4;
            }
        };

        Tetromino shiftedToTopCenterOf4x4 = tetromino4x4.shiftedToTopCenterOf(gridDimension4x4);

        assertEquals(1, shiftedToTopCenterOf4x4.getPosition().col());

        Tetromino tetromino3x3 = Tetromino.newTetromino('T').shiftedBy(0, 0);
        GridDimension gridDimension3x3 = new GridDimension() {
            @Override
            public int rows() {
                return 3;
            }

            @Override
            public int cols() {
                return 3;
            }

            @Override
            public int getRowCount() {
                return 3;
            }
        };

        Tetromino shiftedToTopCenterOf3x3 = tetromino3x3.shiftedToTopCenterOf(gridDimension3x3);

        assertEquals(0, shiftedToTopCenterOf3x3.getPosition().col());
    }
}

