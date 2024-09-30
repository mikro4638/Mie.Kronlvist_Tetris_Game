package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
 * Class representing the Tetris board.
 */
public class TetrisBoard extends Grid<Character> {

    /**
     * Constructs a Tetris board with the specified number of rows and columns.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols, '-');
    }

    /**
     * Formats the board as a string representation.
     *
     * @return a string representing the board
     */
    public String prettyString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < rows(); i++) {
            if (i > 0) {
                str.append("\n");
            }
            for (int j = 0; j < cols(); j++) {
                CellPosition pos = new CellPosition(i, j);
                Character value = get(pos);
                str.append(value);
            }
        }
        return str.toString();
    }

    /**
     * Clears the board by setting all cells to the empty character.
     */
    public void clear() {
        for (int i = 0; i < rows(); i++) {
            setRow(i, '-');
        }
    }

    /**
     * Checks if a given element exists in a specified row.
     *
     * @param row the row to check
     * @param element the element to check for
     * @return true if the element exists, false otherwise
     */
    public boolean elementExist(int row, char element) {
        for (int i = 0; i < this.cols(); i++) {
            if (get(new CellPosition(row, i)) == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets all cells in a row to a specific value.
     *
     * @param row the row to modify
     * @param value the value to set
     */
    public void setRow(int row, char value) {
        for (int i = 0; i < this.cols(); i++) {
            CellPosition pos = new CellPosition(row, i);
            set(pos, value);
        }
    }

    /**
     * Copies values from one row to another.
     *
     * @param fromRow the row to copy from
     * @param toRow the row to copy to
     */
    public void copyRow(int fromRow, int toRow) {
        for (int i = 0; i < this.cols(); i++) {
            set(new CellPosition(toRow, i), get(new CellPosition(fromRow, i)));
        }
    }

    /**
     * Removes all full rows from the board.
     *
     * @return the number of full rows that were removed
     */
    public int removeFullRows() {
        int rowsRemoved = 0;
        int a = this.rows() - 1;
        int b = this.rows() - 1;

        while (a >= 0) {
            while (b >= 0 && !elementExist(b, '-')) {
                rowsRemoved++;
                b--;
            }
            if (b >= 0) {
                copyRow(b, a);
            } else {
                setRow(a, '-');
            }
            a--;
            b--;
        }
        return rowsRemoved;
    }
}
