package no.uib.inf101.grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class representing a grid.
 *
 * @param <E> The type of elements in the grid.
 */
public class Grid<E> implements IGrid<E> {
    private final int numRows; 
    private final int numCols; 
    private final E defaultValue; 
    private final Object[][] gridArray; 

    /**
     * Constructor to create a grid with the specified number of rows and columns.
     *
     * @param numRows The number of rows in the grid.
     * @param numCols The number of columns in the grid.
     */
    public Grid(int numRows, int numCols) {
        this(numRows, numCols, null); // Use null as default value
    }

    /**
     * Constructor to create a grid with the specified number of rows, columns, and default value.
     *
     * @param numRows The number of rows in the grid.
     * @param numCols The number of columns in the grid.
     * @param defaultValue The default value for uninitialized grid cells.
     */
    public Grid(int numRows, int numCols, E defaultValue) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.defaultValue = defaultValue;
        this.gridArray = new Object[numRows][numCols];
        initializeGrid();
    }

    /**
     * Initializes the grid with default values.
     */
    private void initializeGrid() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                gridArray[i][j] = defaultValue;
            }
        }
    }


    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on the grid");
        }
        gridArray[pos.row()][pos.col()] = value;
    }

    
    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on the grid");
        }
        @SuppressWarnings("unchecked")
        E value = (E) gridArray[pos.row()][pos.col()];
        return value;
    }

    
    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    
    @Override
    public int rows() {
        return numRows;
    }

    
    @Override
    public int cols() {
        return numCols;
    }

    
    @Override
    public int getRowCount() {
        return rows();
    }

    
    @Override
    public Iterator<GridCell<E>> iterator() {
        return new GridIterator();
    }

    /**
     * Inner class representing an iterator for the grid.
     */
    private class GridIterator implements Iterator<GridCell<E>> {
        private int currentRow = 0; 
        private int currentCol = 0; 

        
        @Override
        public boolean hasNext() {
            return currentRow < numRows;
        }

        @Override
        public GridCell<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the grid");
            }

            CellPosition pos = new CellPosition(currentRow, currentCol);
            E value = get(pos);
            GridCell<E> cell = new GridCell<>(pos, value);

            currentCol++;
            if (currentCol == numCols) {
                currentCol = 0;
                currentRow++;
            }

            return cell;
        }
    }
}
