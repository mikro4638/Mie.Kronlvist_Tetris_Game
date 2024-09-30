package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * Controller class for the Tetris game.
 */
public class TetrisController implements java.awt.event.KeyListener {

    private ControllableTetrisModel model;
    private TetrisView view;
    private Timer timer;
    private TetrisSong tetrisSong = new TetrisSong();
    private boolean gameStarted;

    /**
     * Creates a TetrisController.
     * 
     * @param model The Tetris model to control
     * @param view The view component for Tetris
     */
    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        view.setFocusable(true);
        view.addKeyListener(this);
        this.model = model;
        this.view = view;
        this.timer = new Timer(model.getTickTime(), this::clockTick);
        this.gameStarted = false;
        tetrisSong.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameStarted) {
            timer.start();
            gameStarted = true;
        } else if (model.getGameState() == GameState.GAME_OVER) {
            resetGame();
            gameStarted = false;
        } else {
            handleGameInput(e.getKeyCode());
        }
        view.repaint();
    }

    
    private void resetGame() {
        model.resetGame();
        gameStarted = false;
    }

    
    private void handleGameInput(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                model.moveTetromino(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                model.moveTetromino(0, 1);
                break;
            case KeyEvent.VK_DOWN:
                model.moveTetromino(1, 0);
                timer.restart();
                break;
            case KeyEvent.VK_UP:
                model.rotateTetromino();
                break;
            case KeyEvent.VK_SPACE:
                model.dropTetromino();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Method called on each clock tick.
     * 
     * @param e ActionEvent triggered on each tick
     */
    public void clockTick(ActionEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick();
            int tickTime = model.getTickTime();
            setDelay(tickTime);
            view.repaint();
        }
    }

    /**
     * Sets the delay between each tick.
     * 
     * @param delay The delay between ticks in milliseconds
     */
    public void setDelay(int delay) {
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }
}
