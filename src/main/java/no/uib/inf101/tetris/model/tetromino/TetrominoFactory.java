package no.uib.inf101.tetris.model.tetromino;

/**
 * A factory for generating Tetromino objects.
 */
public interface TetrominoFactory {
    
    /**
     * Generates and returns the next Tetromino.
     *
     * @return the next Tetromino object.
     */
    Tetromino getNext();
}
