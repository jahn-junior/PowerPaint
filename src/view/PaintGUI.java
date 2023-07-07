package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.DrawingTool;
import model.EllipseTool;
import model.LineTool;
import model.PencilTool;
import model.RectangleTool;

/**
 * Presents the GUI for the PowerPaint application and creates the necessary components for
 * the program to be fully-functioned.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public final class PaintGUI extends JFrame {
    
    // CONSTANTS
    /** Auto-generated UID for object serialization. */
    private static final long serialVersionUID = 4669400407976423585L;

    /** Default Toolkit for utility purposes within the class. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** Screen size constant for proper window scaling. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** int constant for determining the size of the window. Window size = (1/SCALE). */
    private static final int SCALE = 3;
    
    
    // INSTANCE FIELDS
    /** List for storing the ToolActions for each DrawingTool Object. */
    private final List<ToolAction> myToolActions;
    
    /** Canvas in which the user can draw shapes. */
    private final PaintPanel myPanel;
    
    /** DrawingTool for creating lines within the DrawingPanel. */
    private final LineTool myLineTool;
    
    /** DrawingTool for creating rectangles within the DrawingPanel. */
    private final RectangleTool myRectangleTool;
    
    /** DrawingTool for creating ellipses within the DrawingPanel. */
    private final EllipseTool myEllipseTool;
    
    /** DrawingTool for creating free-form paths within the DrawingPanel. */
    private final PencilTool myPencilTool;
    
    /** Menu bar containing utility functions of the program. */
    private final PaintMenuBar myMenuBar;
    
    
    /**
     * Creates objects for instance fields and calls private helper methods for the creation
     * of ToolActions and window setup.
     */
    public PaintGUI() {
        
        super("PowerPaint");
        myToolActions = new ArrayList<ToolAction>(); 
        myLineTool = new LineTool();
        myPanel = new PaintPanel();
        myRectangleTool = new RectangleTool();
        myEllipseTool = new EllipseTool();
        myPencilTool = new PencilTool();
        
        createActions();
        myMenuBar = new PaintMenuBar(myToolActions, myPanel);
        start();
    }

    /**
     * Sets up the window size, close operation, image icon, JMenuBar, and JToolBar.
     */
    protected void start() {

        setSize(SCREEN_SIZE.width / SCALE, SCREEN_SIZE.height / SCALE);
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                            SCREEN_SIZE.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final ImageIcon icon = new ImageIcon("icons/brush_logo.png");
        setIconImage(icon.getImage());
        
        setJMenuBar(myMenuBar);
        add(new PaintToolBar(myToolActions), BorderLayout.SOUTH);
        add(myPanel, BorderLayout.CENTER);
        myPanel.setCurrentTool(myLineTool);
        myPanel.addPropertyChangeListener(myMenuBar);
        setVisible(true);
    }
    
    /**
     * Creates new ToolActions for each of the DrawingTools used within the PowerPaint
     * program.
     */
    private void createActions() {
        
        final Icon lineIcon = new ImageIcon("./icons/line_bw.gif");
        final Icon rectangleIcon = new ImageIcon("./icons/rectangle_bw.gif");
        final Icon ellipseIcon = new ImageIcon("./icons/ellipse_bw.gif");
        final Icon pencilIcon = new ImageIcon("./icons/pencil_bw.gif");
        
        myToolActions.add(new ToolAction("Line", lineIcon, myLineTool));
        myToolActions.add(new ToolAction("Rectangle", rectangleIcon, myRectangleTool));
        myToolActions.add(new ToolAction("Ellipse", ellipseIcon, myEllipseTool));
        myToolActions.add(new ToolAction("Pencil", pencilIcon, myPencilTool));
    }
    
    
    /**
     * Action containing all distinct data for each of the DrawingTools. In the program's
     * current state, these are the line, rectangle, ellipse, and pencil tools.
     * 
     * @author JJ Coldiron (jj.coldiron@outlook.com)
     * @version 1.0
     */
    public class ToolAction extends AbstractAction {
        
    	// CONSTANTS
        /** Auto-generated UID for object serialization. */
        private static final long serialVersionUID = 4469577438306642304L;
        
        
        // INSTANCE FIELDS
        /** The DrawingTool the Action is being tied to. */
        private final DrawingTool myTool;

        
        /**
         * Constructor that sets the name, icon, and DrawingTool for each ToolAction.
         * 
         * @param theName The name of the ToolAction.
         * @param theIcon The icon to be associated with the ToolAction.
         * @param theTool The tool to be associated with the ToolAction.
         */
        ToolAction(final String theName, final Icon theIcon, final DrawingTool theTool) {
            
            super(theName);

            putValue(Action.SMALL_ICON, theIcon);
            final ImageIcon icon = (ImageIcon) theIcon;
            final Image largeImage =
                icon.getImage().getScaledInstance(15, -1, Image.SCALE_SMOOTH);
            final ImageIcon largeIcon = new ImageIcon(largeImage);
            putValue(Action.LARGE_ICON_KEY, largeIcon);
            
            // Ensures that the ToolBar and MenuBar tool buttons are synchronized.
            putValue(Action.SELECTED_KEY, true);
            
            myTool = theTool;

        }
        
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            
            myPanel.setCurrentTool(myTool);
        }
    }
}
