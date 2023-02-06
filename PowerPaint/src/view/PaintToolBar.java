package view;

import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import view.PaintGUI.ToolAction;

/**
 * JToolBar containing all of the DrawingTools for usage within the program. These are tied
 * to the DrawingTools in the accompanying PaintMenuBar through actions, meaning that if one
 * is selected in the PaintToolBar, it will also be selected in the PaintMenuBar
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class PaintToolBar extends JToolBar {
    
    // CONSTANTS
    /** Auto-generated serial version UID for object serialization. */
    private static final long serialVersionUID = -1173067301214856841L;
    
    
    // INSTANCE FIELDS
    /** List containing the ToolActions for each DrawingTool Object. */
    private final List<ToolAction> myActions;
    
    /** ButtonGroup to ensure that the JToggleButtons perform as intended. */
    private final ButtonGroup myGroup;

    
    /**
     * Constructor method for calling the super-constructor, setting instance fields to
     * reasonable values, and calling the private helper method for JToggleButton generation.
     * 
     * @param theActions The list of ToolActions so that the appropriate buttons can be
     * generated for them.
     */
    protected PaintToolBar(final List<ToolAction> theActions) {
        
        super();
        myActions = theActions;
        myGroup = new ButtonGroup();
        
        setup();
    }
    
    /**
     * Private helper method containing a single for-each loop to generate a button for each
     * ToolAction in the myActions instance field.
     */
    private void setup() {
        
        for (final ToolAction action: myActions) {
            final AbstractButton button = new JToggleButton(action);
            myGroup.add(button);
            add(button);
        }
    }
}
