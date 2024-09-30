package no.uib.inf101.tetris.view;

import java.awt.Color;

// Implementasjon av et fargetema for Tetris-spillet
public class DefaultColorTheme implements ColorTheme {

    // Returnerer bakgrunnsfargen (lys rosa)
    @Override
    public Color getBackgroundColor() {
        return new Color(255, 192, 203); // Lyserosa
    }

    // Returnerer fargen til en celle basert på innholdet
    @Override
    public Color getCellColor(char cellContents) {
        switch (cellContents) {
            case '-':
                return new Color(255, 192, 203); // Tom celle (lys rosa)
            case 'I':
                return new Color(173, 216, 230); // Tetromino I (lyseblå)
            case 'O':
                return new Color(255, 215, 0); // Tetromino O (gul)
            case 'T':
                return new Color(218, 112, 214); // Tetromino T (lilla)
            case 'S':
                return new Color(152, 251, 152); // Tetromino S (lys grønn)
            case 'Z':
                return new Color(240, 128, 128); // Tetromino Z (rosa)
            case 'J':
                return new Color(135, 206, 250); // Tetromino J (lys blå)
            case 'L':
                return new Color(255, 160, 122); // Tetromino L (lys korall)
            default:
                // Logger en melding hvis celletypen er ukjent og returnerer grå farge
                System.out.println("Unknown cell type: " + cellContents);
                return Color.GRAY;
        }
    }

    // Returnerer fargen til en blokk basert på typen (bruker samme farge som cellen)
    @Override
    public Color getBlockColor(char blockType) {
        return getCellColor(blockType);
    }

    // Returnerer en gjennomsiktig ramme (gjennomsiktig svart)
    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    // Returnerer en gjennomsiktig farge for "Game Over"-teksten (gjennomsiktig svart)
    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 0);
    }

    // Returnerer standardfargen for tekst (hvit)
    @Override
    public Color getTextColor() {
        return Color.WHITE;
    }
}
