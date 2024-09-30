package no.uib.inf101.tetris;

import javax.swing.JFrame;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * Main class to launch the Tetris game application.
 */
public class TetrisMain {
    /**
     * The title of the main window.
     */
    public static final String WINDOW_TITLE = "INF101 Tetris";

    /**
     * The main entry point of the Tetris application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        TetrisBoard board = new TetrisBoard(15, 10);
        TetrominoFactory tFactory = new RandomTetrominoFactory();
        TetrisModel model = new TetrisModel(board, tFactory);
        TetrisView view = new TetrisView(model);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.addKeyListener(new TetrisController(model, view));

        frame.pack();
        frame.setVisible(true);
    }
}
