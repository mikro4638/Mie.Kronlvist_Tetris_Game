package no.uib.inf101.grid.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.tetris.model.Tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {

//Test som sjekker at tetrominoen starter på riktig plass for O.
@Test
public void initialPositionOfO() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("O");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
}

//Test som sjekker at tetrominoen starter på riktig plass for I
@Test
public void initialPositionOfI() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
}

//Test som sjekker at tetrominoen flyttes riktig.
@Test
public void moveTetromino() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  model.moveTetromino(1, 0);
  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 3), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 6), 'I')));
}

//Test som sjekker at tetrominoen roteres riktig.
@Test
public void rotateTetromino() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  System.out.println("Tetromino før rotasjon:");
  for (GridCell<Character> gc : model.fallingPiece()) {
    System.out.println(gc);
  }

  model.moveTetromino(1, 0);
  model.rotateTetromino();

  System.out.println("Tetromino etter rotasjon:");
  for (GridCell<Character> gc : model.fallingPiece()) {
    System.out.println(gc);
  }

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(2, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(3, 4), 'I')));
}

//Test som sjekker at tetrominoen faller riktig.
@Test
public void dropTetromino() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  model.dropTetromino();
  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getTilesOnBoard()) {
    tetroCells.add(gc);
  }

  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(19, 3), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(19, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(19, 5), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(19, 6), 'I')));

}

//Test som som sjekker at tetrominoen faller i henhold til timeren. 
@Test
public void clockTick() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells1 = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells1.add(gc);
  }
  model.clockTick();
  List<GridCell<Character>> tetroCells2 = new ArrayList<>();
  for (GridCell<Character> gc : model.fallingPiece()) {
    tetroCells2.add(gc);
  }

  assertNotEquals(tetroCells1, tetroCells2);
  assertEquals(1, tetroCells2.get(0).pos().row());
  assertEquals(3, tetroCells2.get(0).pos().col());
  assertEquals(1, tetroCells2.get(1).pos().row());
  assertEquals(4, tetroCells2.get(1).pos().col());
  assertEquals(1, tetroCells2.get(2).pos().row());
  assertEquals(5, tetroCells2.get(2).pos().col());
  assertEquals(1, tetroCells2.get(3).pos().row());
  assertEquals(6, tetroCells2.get(3).pos().col());
}

}