package model;

import java.awt.Point;

/**
 * Abstract class defining default behavior of all DrawingTool objects.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public abstract class AbstractTool implements DrawingTool {
    
	// CONSTANTS
    /** Default Point for each DrawingTool (outside the view of the user). */
    private static final Point UNASSIGNED = new Point(-100, -100);
    
    
    // INSTANCE FIELDS
    /** The Point at which the Shape begins. */
    private Point myStartPoint;
    
    /** The Point at which the Shape ends. */
    private Point myEndPoint;
    
    
    /**
     * Abstract class constructor for for initializing start and end points offscreen.
     */
    protected AbstractTool() {
        
        myStartPoint = UNASSIGNED;
        myEndPoint = UNASSIGNED;
    }
    
    @Override
    public void setStartPoint(final Point thePoint) {
        
        myStartPoint = (Point) thePoint.clone();
    }

    @Override
    public void setEndPoint(final Point thePoint) {
        
        myEndPoint = (Point) thePoint.clone();
    }

    /**
     * Provides the caller with a clone of the Shape's start point to preserve encapsulation.
     * 
     * @return A clone of the Shape's start point.
     */
    protected Point getStartPoint() {

        return (Point) myStartPoint.clone();
    }

    /**
     * Provides the caller with a clone of the Shape's end point to preserve encapsulation.
     * 
     * @return A clone of the Shape's end point.
     */
    protected Point getEndPoint() {

        return (Point) myEndPoint.clone();
    }
}
