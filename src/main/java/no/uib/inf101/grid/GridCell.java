package no.uib.inf101.grid;

import java.util.Objects;

/**
 * Class representing a grid cell.
 *
 * @param <E> The type of the value held by the grid cell.
 */
public class GridCell<E> {
    private final CellPosition pos;
    private final E value;

    /**
     * Constructs a grid cell with the specified position and value.
     *
     * @param pos The position of the grid cell.
     * @param value The value held by the grid cell.
     */
    public GridCell(CellPosition pos, E value) {
        this.pos = pos;
        this.value = value;
    }

    /**
     * Retrieves the position of the grid cell.
     *
     * @return The position of the grid cell.
     */
    public CellPosition pos() {
        return pos;
    }

    /**
     * Retrieves the value held by the grid cell.
     *
     * @return The value held by the grid cell.
     */
    public E value() {
        return value;
    }

    /**
     * Retrieves the content of the grid cell.
     *
     * @return The value held by the grid cell.
     */
    public E getContent() {
        return value;
    }

    /**
     * Checks if this grid cell has the same position as another grid cell.
     *
     * @param other The other grid cell to compare with.
     * @return true if the positions are the same, false otherwise.
     */
    public boolean hasSamePosition(GridCell<E> other) {
        return this.pos().equals(other.pos());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridCell<?> gridCell = (GridCell<?>) o;
        return Objects.equals(pos, gridCell.pos()) && Objects.equals(value, gridCell.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, value);
    }
}
