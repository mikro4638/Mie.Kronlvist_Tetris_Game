package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

/**
 * Class representing the Tetris game model that implements ViewableTetrisModel and ControllableTetrisModel.
 */
public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private TetrisBoard board;
    private TetrominoFactory tFactory;
    private Tetromino fallingTetro;
    private GameState state;
    private int score;
    private int totalRowsRemoved;

    /**
     * Constructs a TetrisModel object.
     *
     * @param board a TetrisBoard object representing the board.
     * @param tFactory a TetrominoFactory for generating tetrominoes.
     */
    public TetrisModel(TetrisBoard board, TetrominoFactory tFactory) {
        this.board = board;
        this.tFactory = tFactory;
        this.fallingTetro = tFactory.getNext().shiftedToTopCenterOf(board);
        this.state = GameState.ACTIVE_GAME;
        this.score = 0;
        this.totalRowsRemoved = 0;
    }

    
    @Override
    public GridDimension getDimension() {
        return board;
    }

    
    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

   
    @Override
    public Iterable<GridCell<Character>> fallingPiece() {
        return fallingTetro;
    }

    /**
     * Checks if a given position is legal, i.e., within the board and without collisions.
     *
     * @param currentTetro the Tetromino to be checked.
     * @return true if the position is legal, otherwise false.
     */
    public boolean isLegalPosition(Tetromino currentTetro) {
        for (GridCell<Character> cell : currentTetro) {
            CellPosition cellPosition = cell.pos();
            if (!board.positionIsOnGrid(cellPosition) || board.get(cellPosition) != '-') {
                return false;
            }
        }
        return true;
    }

    
    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino newTetro = fallingTetro.shiftedBy(deltaRow, deltaCol);
        if (isLegalPosition(newTetro)) {
            fallingTetro = newTetro;
            return true;
        }
        return false;
    }

    
    @Override
    public boolean rotateTetromino() {
        Tetromino newTetro = fallingTetro.rotatedCopy();
        if (isLegalPosition(newTetro)) {
            fallingTetro = newTetro;
            return true;
        }
        return false;
    }

    /**
     * Increases the score by a given number of points.
     *
     * @param points the number of points to be added.
     */
    public void increaseScore(int points) {
        score += points;
    }

    
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Removes full rows from the board and updates the score.
     */
    private void removeFullRowsAndUpdateScore() {
        int rowsRemoved = board.removeFullRows();
        if (rowsRemoved > 0) {
            totalRowsRemoved += rowsRemoved;
            switch (rowsRemoved) {
                case 1:
                    increaseScore(100);
                    break;
                case 2:
                    increaseScore(300);
                    break;
                case 3:
                    increaseScore(500);
                    break;
                case 4:
                    increaseScore(800);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Calculates the level based on the total number of rows removed.
     *
     * @return the current level.
     */
    private int calculateLevel() {
        return totalRowsRemoved / 3;
    }

    /**
     * Generates and places a new tetromino on the board.
     * If it cannot be placed, sets the game state to GAME_OVER.
     */
    public void getNewTetromino() {
        Tetromino nextTetro = tFactory.getNext().shiftedToTopCenterOf(board);
        if (!isLegalPosition(nextTetro)) {
            state = GameState.GAME_OVER;
        } else {
            fallingTetro = nextTetro;
        }
    }

    /**
     * Places the current falling tetromino on the board and retrieves a new one.
     */
    public void newFallingTetrominoToBoard() {
        for (GridCell<Character> cell : fallingTetro) {
            CellPosition cellPosition = cell.pos();
            board.set(cellPosition, cell.value());
        }
        getNewTetromino();
    }

    
    @Override
    public void dropTetromino() {
        Tetromino newTetro = fallingTetro.shiftedBy(1, 0);
        while (isLegalPosition(newTetro)) {
            fallingTetro = newTetro;
            newTetro = newTetro.shiftedBy(1, 0);
        }
        newFallingTetrominoToBoard();
        removeFullRowsAndUpdateScore();
    }

    
    @Override
    public GameState getGameState() {
        return state;
    }

    
    @Override
    public int getTickTime() {
        int level = calculateLevel();
        return Math.max(100, 500 - level * 50);
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        board.clear();
        state = GameState.ACTIVE_GAME;
        score = 0;
        totalRowsRemoved = 0;
        getNewTetromino();
    }

    
    @Override
    public void clockTick() {
        if (!moveTetromino(1, 0)) {
            newFallingTetrominoToBoard();
            removeFullRowsAndUpdateScore();
        }
    }
}
