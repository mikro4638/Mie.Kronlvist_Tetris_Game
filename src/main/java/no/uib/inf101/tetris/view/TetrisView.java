package no.uib.inf101.tetris.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Iterable;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

public class TetrisView extends JPanel {

  private ViewableTetrisModel model;
  public ColorTheme colortheme;
  private boolean welcomeScreen;

  private static final double OUTERMARGIN = 20;
  private static final double INNERMARGIN = 2;

  /*
   * Konstruktør for TetrisView
   * 
   * @param model ViewableTetrisModel som skal vises i viewet
   * 
   * @param colortheme ColorTheme som skal brukes til å tegne viewet
   * 
   * @param setBackGroundColor Color som skal brukes til å sette
   * bakgrunnsfargen til viewet
   * 
   * @param setPreferredSize Dimension som skal brukes til å sette størrelsen til
   * viewet
   */
  public TetrisView(ViewableTetrisModel model) {
      this.model = model;
      this.colortheme = new DefaultColorTheme();
      this.setBackground(colortheme.getBackgroundColor());
      this.setPreferredSize(new Dimension(400, 550));
      this.welcomeScreen = true;

      // Legg til en KeyListener for å håndtere tastetrykk
      this.addKeyListener(new TetrisKeyListener());
      this.setFocusable(true);
      this.requestFocusInWindow(); // Sørg for at panelet har fokus slik at tastetrykk blir fanget opp
  }

  /**
   * Metode som brukes for å tegne viewet
   */
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      
      if (!welcomeScreen) {
          drawGame(g2);
      } else {
          drawWelcomeScreen(g2);
      }
  }

  /**
   * Metode som tegner velkomstskjermen
   */
  private void drawWelcomeScreen(Graphics2D g2) {
    g2.setColor(Color.BLACK); // Endret fargen til svart
    g2.fillRect(0, 0, getWidth(), getHeight());

    g2.setColor(colortheme.getTextColor());
    g2.setFont(new Font("Arial", Font.BOLD, 30));
    drawCenteredString(g2, "Welcome to Tetris!", getHeight() / 2 - 50);
    drawCenteredString(g2, "Press any key to begin.", getHeight() / 2 + 50);
}

  /**
   * Metode som brukes for å tegne cellene i viewet
   */
  public static void drawCells(Graphics2D g2, Iterable<GridCell<Character>> iterable,
          CellPositionToPixelConverter converter, ColorTheme colorTheme) {
      for (GridCell<Character> cell : iterable) {
          Rectangle2D rectangle2d = converter.getBoundsForCell(cell.pos());
          Color color = colorTheme.getCellColor(cell.value());
          g2.setColor(color);
          g2.fill(rectangle2d);
      }
  }

  /**
   * Dette er metoden som tegner selve spillet
   * 
   * @param g2          en variabel for lerretet det skal tegnes på
   * @param model       er for å hente verdier fra modellen
   * @param colortheme  er for å hente farger fra ColorTheme
   * @param converter   er for å konvertere posisjonen til piksler
   * @param iterable    er for å iterere gjennom cellene
   * @param cell        er for å hente posisjonen til cellene
   * @param rectangle2d er for å tegne rektanglene
   */
  public void drawGame(Graphics2D g2) {
      double x = OUTERMARGIN;
      double y = OUTERMARGIN;
      double cellWidth = this.getWidth() - 2 * OUTERMARGIN;
      double cellHeight = this.getHeight() - 2 * OUTERMARGIN;

      Rectangle2D rectangle2d = new Rectangle2D.Double(x, y, cellWidth, cellHeight);

      g2.setColor(colortheme.getBackgroundColor());
      g2.fill(rectangle2d);

      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(rectangle2d, model.getDimension(),
              INNERMARGIN);

      drawCells(g2, model.getTilesOnBoard(), converter, colortheme);
      drawCells(g2, model.fallingPiece(), converter, colortheme);

      // Tegn poengsummen med større tekst
      g2.setColor(colortheme.getTextColor());
      g2.setFont(new Font("Arial", Font.BOLD, 30)); // Endret størrelsen til 30
      g2.drawString("Score: " + model.getScore(), (int) OUTERMARGIN, (int) (this.getHeight() - OUTERMARGIN));

      if (model.getGameState() == GameState.GAME_OVER) {
          g2.setColor(colortheme.getGameOverColor());
          g2.fill(rectangle2d);
          g2.setColor(colortheme.getTextColor());
          g2.setFont(new Font("Arial", Font.BOLD, 50));
          Inf101Graphics.drawCenteredString(g2, "GAME OVER", rectangle2d);
      }
  }

  /**
   * Metode for å tegne en tekst som er sentrert i panelet
   */
  private void drawCenteredString(Graphics2D g2, String text, int centerY) {
      FontMetrics fm = g2.getFontMetrics();
      int x = (getWidth() - fm.stringWidth(text)) / 2;
      g2.drawString(text, x, centerY);
  }

  /**
   * Indre klasse for å håndtere tastetrykk
   */
  private class TetrisKeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
          welcomeScreen = false; // Skjul velkomstskjermen når en tast er trykket
          repaint(); // Oppdater tegningen
      }
  }
}
