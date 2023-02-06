package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * Interface defining shared behavior of DrawingTool objects.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public interface DrawingTool {
    
    /**
     * Returns the Shape that this tools draws.
     * 
     * @return The Shape that was drawn.
     */
    Shape getShape();
    
    /**
     * Returns the name of the current DrawingTool.
     * 
     * @return The name of the DrawingTool.
     */
    String getName();
    
    /**
     * Sets the start point for the Shape drawn by this tool.
     * 
     * @param thePoint The Point at which the Shape starts.
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets the end point for the Shape drawn by this tool.
     * 
     * @param thePoint The Point at which the Shape ends.
     */
    void setEndPoint(Point thePoint);

}
