package no.uib.inf101.tetris.view;

import java.awt.Color;

// Grensesnitt for fargetema i Tetris-visningen
public interface ColorTheme {
    // Metode for å få fargen til en blokk av et spesifikt type
    Color getBlockColor(char blockType);
    
    // Metode for å få fargen til en celle med et spesifikt innhold
    Color getCellColor(char cellContents);
    
    // Metode for å få bakgrunnsfargen
    Color getBackgroundColor();
    
    // Metode for å få fargen til rammen
    Color getFrameColor();
    
    // Metode for å få fargen til skriftteksten
    Color getTextColor(); 
    
    // Metode for å få fargen til "Game Over"-meldingen
    Color getGameOverColor();
}
