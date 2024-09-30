package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

/**
 * Interface for controlling the Tetris game.
 */
public interface ControllableTetrisModel {

    /**
     * Moves the tetromino on the board.
     *
     * @param deltaRow The number of rows to move the tetromino.
     * @param deltaCol The number of columns to move the tetromino.
     * @return true if the tetromino was moved; false otherwise.
     */
    boolean moveTetromino(int deltaRow, int deltaCol);

    /**
     * Rotates the tetromino.
     *
     * @return true if the tetromino was rotated; false otherwise.
     */
    boolean rotateTetromino();

    /**
     * Drops the tetromino down on the board.
     */
    void dropTetromino();

    /**
     * Retrieves the current game state.
     *
     * @return The state of the game as a GameState object.
     */
    GameState getGameState();

    /**
     * Returns the time between each tick.
     *
     * @return The time between each tick in milliseconds.
     */
    int getTickTime();

    /**
     * Performs a tick in the game.
     */
    void clockTick();

    /**
     * Resets the game.
     */
    void resetGame();
}
