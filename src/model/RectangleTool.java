package model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Concrete class that defines behavior specific to the rectangle Shape. This implementation
 * ensures that the ellipse can drawn in all four directions and remain visible to the user.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class RectangleTool extends AbstractTool {
    
    @Override
    public Shape getShape() {
        
        final double base = getEndPoint().getX() - getStartPoint().getX();
        final double height = getEndPoint().getY() - getStartPoint().getY();
        
        // If drawn down and to the left
        Rectangle2D.Double result = new Rectangle2D.Double(getStartPoint().getX(),
                                                           getStartPoint().getY(),
                                                           base, height);
        // If drawn up and to the right
        if (base < 0 && height < 0) {
            result = new Rectangle2D.Double(getEndPoint().getX(), getEndPoint().getY(), 
                                            base * -1, height * -1);
        // If drawn up and to the right
        } else if (height < 0) {
            result = new Rectangle2D.Double(getStartPoint().getX(), getEndPoint().getY(),
                                            base, height * -1);
        // If drawn down and to the left
        } else if (base < 0) {
            result = new Rectangle2D.Double(getEndPoint().getX(), getStartPoint().getY(),
                                            base * -1, height);
        }
        
        return result;
    }
    
    @Override
    public String getName() {
        return "Rectangle";
    }

}
