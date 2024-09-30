package no.uib.inf101.grid.tetris.model.Tetromino;

import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

/**
 * En klasse som henter og lager tilfeldige brikker.
 */
public class PatternedTetrominoFactory implements TetrominoFactory {
    
    private String pattern;
    private int index;
    
    public PatternedTetrominoFactory(String pattern) {
        this.pattern = pattern;
        this.index = 0;
    }
    
    @Override
    public Tetromino getNext() {
        char c = pattern.charAt(index);
        index = (index + 1) % pattern.length();
        return Tetromino.newTetromino(c);
    }   
}
