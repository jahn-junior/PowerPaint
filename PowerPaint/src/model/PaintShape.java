package model;

import java.awt.Color;
import java.awt.Shape;

/**
 * Object that stores the Shape, Color, and stroke width of a shape made within the
 * DrawingPanel of the program. Provides accessor methods so that these PaintShapes can be
 * reproduced each time paintComponent() is called within the DrawingPanel.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class PaintShape {

	// INSTANCE FIELDS
    /** The Shape drawn. */
    private final Shape myShape;
    
    /** The Color of the PaintShape. */
    private final Color myColor;
    
    /** The Color the PaintShape is filled with. */
    private final Color myFillColor;
    
    /** Whether or not the shape is filled. */
    private final boolean myFillStatus;
    
    /** The stroke width of the PaintShape. */
    private final int myWidth;
    
    
    /**
     * Constructor that stores initializes the instance fields of the PaintShape to the given
     * parameters.
     * 
     * @param theShape The Shape stored within the PaintShape Object.
     * @param theColor The Color of the PaintShape.
     * @param theWidth The stroke width of the PaintShape.
     */
    public PaintShape(final Shape theShape, final Color theColor, final Color theFillColor,
                      final boolean theFillStatus, final int theWidth) {
        myShape = theShape;
        myColor = theColor;
        myFillColor = theFillColor;
        myFillStatus = theFillStatus;
        myWidth = theWidth;
    }
    
    /**
     * Accessor method that provides the caller with the stored Shape.
     * 
     * @return The Shape stored within the PaintShape Object.
     */
    public Shape getShape() {
        
        return myShape;
    }
    
    /**
     * Accessor method that provides the caller with the Color of the PaintShape.
     * 
     * @return The Color of the PaintShape.
     */
    public Color getColor() {
        
        return myColor;
    }
    
    /**
     * Accessor method that provides the caller with the fill color of the PaintShape.
     * 
     * @return The fill color of the PaintShape.
     */
    public Color getFillColor() {
        
        return myFillColor;
    }
    
    /**
     * Accessor method that provides the caller with a boolean representing whether or not
     * the PaintShape is filled.
     * 
     * @return Whether or not the shape is filled.
     */
    public boolean isFilled() {
        
        return myFillStatus;
    }
    
    /**
     * Accessor method that provides the caller with the stroke width of the PaintShape.
     * 
     * @return The stroke width of the PaintShape.
     */
    public int getWidth() {
        
        return myWidth;
    }
}
