package no.uib.inf101.grid;

/**
 * Record class representing a cell position in the grid.
 */
public record CellPosition(int row, int col) {

    /**
     * Retrieves the row of the cell position.
     *
     * @return The row of the cell position.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column of the cell position.
     *
     * @return The column of the cell position.
     */
    public int getColumn() {
        return col;
    }
}
