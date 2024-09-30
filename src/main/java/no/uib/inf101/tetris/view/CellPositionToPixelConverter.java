package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.CellPosition;
import java.awt.geom.Rectangle2D;
import no.uib.inf101.grid.GridDimension;

/**
 * En klasse for å konvertere celleposisjoner til piksler.
 */
public class CellPositionToPixelConverter {

    private GridDimension gridDimension;
    private Rectangle2D boundingBox;
    private double cellMargin;

    /**
     * Oppretter en CellPositionToPixelConverter.
     * @param boundingBox Boksen som cellene skal tegnes innenfor.
     * @param gridDimension Dimensjonene til rutenettet.
     * @param cellMargin Margen mellom cellene.
     */
    public CellPositionToPixelConverter(Rectangle2D boundingBox, GridDimension gridDimension, double cellMargin) {
        this.gridDimension = gridDimension;
        this.boundingBox = boundingBox;
        this.cellMargin = cellMargin;
    }

    /**
     * Oversetter en cellens posisjon til piksler.
     * @param cellPosition Posisjonen til cellen.
     * @return Et rektangel som representerer cellens posisjon og størrelse i piksler.
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        double cellWidth = (boundingBox.getWidth() - (double)(gridDimension.cols() + 1) * cellMargin) / (double)(gridDimension.cols());
        double cellHeight = (boundingBox.getHeight() - (double)(gridDimension.rows() + 1) * cellMargin) / (double)(gridDimension.rows());
        double x = boundingBox.getX() + cellMargin + (double)(cellPosition.col()) * (cellWidth + cellMargin);
        double y = boundingBox.getY() + cellMargin + (double)(cellPosition.row()) * (cellHeight + cellMargin);
        return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
    }
    
}

