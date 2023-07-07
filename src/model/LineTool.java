package model;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Concrete class that defines behavior specific to the line Shape.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class LineTool extends AbstractTool {

    @Override
    public Shape getShape() {
        
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

    @Override
    public String getName() {
        return "Line";
    }
}
