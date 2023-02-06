package model;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * Concrete class that defines behavior specific to the pencil Shape. Creates a new Path2D
 * object every time the mouse is pressed so that changes to color or thickness do not
 * alter the qualities of previous Shapes.
 * 
 * @author JJ Coldiron
 * @version 1.0
 */
public class PencilTool extends AbstractTool {
	
	// INSTANCE FIELDS
	/** The current Path being drawn by the user. */
	Path2D.Double myShape;
	
	
	@Override
	public Shape getShape() {
		
		return myShape;
	}
	
	@Override
	public void setStartPoint(final Point thePoint) {
		
		myShape = new Path2D.Double();
		myShape.moveTo(thePoint.getX(), thePoint.getY());
	}
	
	@Override
	public void setEndPoint(final Point thePoint) {
		
		myShape.lineTo(thePoint.getX(), thePoint.getY());
	}

	@Override
	public String getName() {
		
		return "Pencil";
	}

}
