package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

/**
 * Grensesnitt for å vise selve brettet og tilhørende funksjonalitet i Tetris-spillet.
 */
public interface ViewableTetrisModel {

    /**
     * Finner dimensjonen til tetrominoene på brettet.
     * @return Dimensjonen til tetrominoene som et GridDimension-objekt.
     */
    GridDimension getDimension();

    /**
     * Henter alle cellene som utgjør brettet.
     * @return En itererbar samling av GridCell-objekter som representerer cellene på brettet.
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Henter alle cellene til den fallende brikken.
     * @return En itererbar samling av GridCell-objekter som representerer cellene til den fallende brikken.
     */
    Iterable<GridCell<Character>> fallingPiece();

    /**
     * Finner den nåværende posisjonen til den fallende brikken etter en potensiell bevegelse.
     * @param i Antall rader brikken skal flyttes i vertikal retning.
     * @param j Antall kolonner brikken skal flyttes i horisontal retning.
     * @return true hvis brikken kan flyttes til den nye posisjonen, false ellers.
     */
    boolean moveTetromino(int i, int j);

    /**
     * Henter en rotert kopi av den fallende brikken.
     * @return true hvis brikken kan roteres, false ellers.
     */
    boolean rotateTetromino();

    /**
     * Slipper den fallende brikken ned til bunnen av brettet.
     */
    void dropTetromino();

    /**
     * Returnerer tilstanden til spillet.
     * @return GameState som representerer om spillet er i gang, over eller i pause.
     */
    GameState getGameState();

    /**
     * Simulerer et klokkeslett i spillet.
     */
    void clockTick();

    /**
     * Henter gjeldende poengsum i spillet.
     * @return Den gjeldende poengsummen.
     */
    int getScore();
}
