package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.DrawingTool;
import model.LineTool;
import model.PaintShape;

/**
 * Canvas in which the user may draw shapes using the selected DrawingTool, color, and stroke
 * width.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class PaintPanel extends JPanel {
    
    // CONSTANTS
    /** Auto-generated UID for object serialization. */
    private static final long serialVersionUID = 962870856477771258L;

    /** Default Toolkit for utility purposes within the class. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** Screen size constant for proper window scaling. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** int constant for determining the size of the window. Window size = (1/SCALE). */
    private static final int SCALE = 3;
    
    /** Background color for the DrawingPanel. */
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    /** Default stroke width for drawing prior to user-prompted width change. */
    private static final int DEFAULT_THICKNESS = 3;
    
    
    // INSTANCE FIELDS
    /** Support for firing PropertyChangeEvents. */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
    
    /** The DrawingTool currently in use by the user. */
    private DrawingTool myActiveTool;
    
    /** List of previously drawn shapes so that graphics are persistent. */
    private final List<PaintShape> myDrawnShapes;
    
    /** The currently selected color for the PaintShape to be drawn. */
    private Color myColor;
    
    /** The currently selected fill color for the PaintShape to be drawn. */
    private Color myFillColor;
    
    /** Whether or not the PaintShape to be drawn will be filled. */
    private boolean myFillStatus;
    
    /** The currently selected stroke width for the PaintShape to be drawn. */
    private int myWidth;

    
    /**
     * Constructor method that initializes instance fields to reasonable initial values, 
     * and calls a private helper method to set up the size and features of the JPanel 
     * appropriately.
     */
    protected PaintPanel() {
        
        super();
        myDrawnShapes = new ArrayList<PaintShape>();
        myColor = Color.BLACK;
        myFillColor = Color.BLACK;
        myWidth = DEFAULT_THICKNESS;
        myActiveTool = new LineTool();
        panelSetup();
    }
    
    /**
     * Sets the size, cursor, and background color of the panel before adding the mouse
     * listener that allows for shapes to be drawn as a result of user input.
     */
    private void panelSetup() {
        
        setSize(SCREEN_SIZE.width / SCALE, SCREEN_SIZE.height / SCALE);
        final Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(cursor);
        setBackground(BACKGROUND_COLOR);
        final MouseAdapter listener = new ShapeListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
    
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (final PaintShape shape: myDrawnShapes) {
            if (shape.getWidth() > 0) {
                g2d.setPaint(shape.getColor());
                g2d.setStroke(new BasicStroke(shape.getWidth()));
                g2d.draw(shape.getShape());
                if (shape.isFilled()) {
                    g2d.setColor(shape.getFillColor());
                    g2d.fill(shape.getShape());
                }
            }
        }
        
        g2d.setPaint(myColor);
        g2d.setStroke(new BasicStroke(myWidth));
        
        if (myWidth > 0) {
            if (myFillStatus) {
                g2d.setColor(myColor);
                g2d.draw(myActiveTool.getShape());
                g2d.setColor(myFillColor);
                g2d.fill(myActiveTool.getShape());
            } else {
                g2d.draw(myActiveTool.getShape());
            }
        }
    }
    
    /**
     * Mutator method that sets the active DrawingTool to the DrawingTool provided in the
     * parameter. Fires a PropertyChange so that the "Fill" checkbox can be enabled/disabled
     * appropriately.
     * 
     * @param theTool The desired DrawingTool to be actively used within the panel.
     */
    protected void setCurrentTool(final DrawingTool theTool) {
        
        myPCS.firePropertyChange(theTool.getName(), myActiveTool, theTool);
        myActiveTool = theTool;
    }
    
    /**
     * Mutator method that sets the active paint color to the color provided in the
     * parameter.
     * 
     * @param theColor The desired color of the shape(s) to be drawn.
     */
    protected void setPaintColor(final Color theColor) {
        
        myColor = theColor;
    }
    
    /**
     * Mutator method that sets the active fill color to the color provided in the parameter.
     * 
     * @param theColor The desired fill color of the shape(s) to be drawn.
     */
    protected void setFillColor(final Color theColor) {
        
        myFillColor = theColor;
    }
    
    /**
     * Mutator method that sets the fill status to the provided parameter.
     * 
     * @param theBool Whether or not the drawn shape will be filled.
     */
    protected void setFillStatus(final boolean theBool) {
        
        myFillStatus = theBool;
    }
    
    /**
     * Mutator method that sets the stroke width to the width provided in the parameter.
     * 
     * @param theWidth The desired width of the shape(s) to be drawn.
     */
    protected void setStrokeWidth(final int theWidth) {
        
        myWidth = theWidth;
    }
    
    /**
     * Provides the caller with the tool actively being used for the drawing of PaintShapes.
     * 
     * @return The tool actively being used.
     */
    protected DrawingTool getActiveTool() {
        
        return myActiveTool;
    }
    
    /**
     * Provides the caller with the list of previously drawn PaintShapes.
     * 
     * @return The list of previously drawn PaintShapes.
     */
    protected List<PaintShape> getShapes() {
        
        return myDrawnShapes;
    }
    
    /**
     * Adds a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to add.
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    
    /**
     * Removes a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to remove.
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
    
    
    /**
     * Mouse listener for recording the points at which the mouse is pressed, dragged, and
     * released. These points are then relayed to the currently active DrawingTool so that
     * the shape may be displayed on screen.
     * 
     * @author JJ Coldiron (jj.coldiron@outlook.com)
     * @version 1.0
     */
    private class ShapeListener extends MouseAdapter {
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            
            myActiveTool.setStartPoint(theEvent.getPoint());
            myActiveTool.setEndPoint(theEvent.getPoint());
            repaint();
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            
            myActiveTool.setEndPoint(theEvent.getPoint());
            repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            
            myDrawnShapes.add(new PaintShape(myActiveTool.getShape(), myColor, myFillColor,
                                             myFillStatus, myWidth));
            myPCS.firePropertyChange("shapes", 0, myDrawnShapes.size());
        }
    }
}
