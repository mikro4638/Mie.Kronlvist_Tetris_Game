package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * Represents a Tetromino in the Tetris game.
 * Each Tetromino has a symbol, a shape, and a position.
 * Tetromino can be moved, rotated, and allows iteration over the cells it covers.
 */
public class Tetromino implements Iterable<GridCell<Character>> {
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    private Tetromino(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    /**
     * Retrieves the symbol of the Tetromino object.
     *
     * @return the symbol of the Tetromino.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Retrieves the shape of the Tetromino object.
     *
     * @return the shape of the Tetromino as a 2D boolean array.
     */
    public boolean[][] getShape() {
        return shape;
    }

    /**
     * Retrieves the position of the Tetromino object.
     *
     * @return the position of the Tetromino as a CellPosition.
     */
    public CellPosition getPosition() {
        return position;
    }

    /**
     * Creates a new Tetromino object based on the specified symbol.
     *
     * @param symbol the symbol for the Tetromino.
     * @return a new Tetromino object.
     * @throws IllegalArgumentException if the symbol is unknown.
     */
    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape;
        CellPosition position = new CellPosition(0, 0); // The position is always (0, 0) for new tetrominoes
        switch (symbol) {
            case 'T':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { false, true, false }
                };
                break;
            case 'S':
                shape = new boolean[][] {
                        { false, false, false },
                        { false, true, true },
                        { true, true, false }
                };
                break;
            case 'I':
                shape = new boolean[][] {
                        { true, true, true, true }
                };
                break;
            case 'L':
                shape = new boolean[][] {
                        { false, false, true },
                        { true, true, true }
                };
                break;
            case 'Z':
                shape = new boolean[][] {
                        { true, true, false },
                        { false, true, true },
                        { false, false, false }
                };
                break;
            case 'O':
                shape = new boolean[][] {
                        { true, true },
                        { true, true }
                };
                break;
            case 'J':
                shape = new boolean[][] {
                        { true, false, false },
                        { true, true, true },
                        { false, false, false }
                };
                break;
            default:
                throw new IllegalArgumentException("Unknown tetromino symbol: " + symbol);
        }
        return new Tetromino(symbol, shape, position);
    }

    /**
     * Moves the Tetromino object by a given row and column offset.
     *
     * @param deltaRow the row offset.
     * @param deltaCol the column offset.
     * @return a new Tetromino object with the updated position.
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(position.row() + deltaRow, position.col() + deltaCol);
        return new Tetromino(symbol, shape, newPosition);
    }

    /**
     * Moves the Tetromino object to the top and centers it horizontally.
     *
     * @param gridDimension the dimensions of the grid.
     * @return a new Tetromino object placed at the top of the grid.
     */
    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        int centerCol = gridDimension.cols() / 2;
        int deltaCol = centerCol - (shape[0].length / 2);
        return shiftedBy(0, deltaCol);
    }

    /**
     * Creates a rotated copy of the Tetromino object.
     *
     * @return a new Tetromino object that is a rotated copy.
     */
    public Tetromino rotatedCopy() {
        boolean[][] rotatedTetro = new boolean[this.shape[0].length][this.shape.length];
        for (int row = 0; row < this.shape.length; row++) {
            for (int col = 0; col < this.shape[0].length; col++) {
                rotatedTetro[col][this.shape.length - 1 - row] = this.shape[row][col];
            }
        }
        return new Tetromino(this.symbol, rotatedTetro, this.position);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        List<GridCell<Character>> cells = new ArrayList<>();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    int row = position.row() + i;
                    int col = position.col() + j;
                    cells.add(new GridCell<>(new CellPosition(row, col), symbol));
                }
            }
        }
        return cells.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tetromino)) return false;
        Tetromino tetromino = (Tetromino) o;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                Objects.equals(position, tetromino.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), position);
    }
}
