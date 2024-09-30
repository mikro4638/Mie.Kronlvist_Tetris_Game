package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

/**
 * A factory that generates random Tetromino objects.
 */
public class RandomTetrominoFactory implements TetrominoFactory {
    private final Random random = new Random();

    /**
     * Generates and returns a random Tetromino.
     *
     * @return A randomly generated Tetromino.
     */
    @Override
    public Tetromino getNext() {
        char randomSymbol = "LJSZTIO".charAt(random.nextInt("LJSZTIO".length()));
        return Tetromino.newTetromino(randomSymbol);
    }
}


