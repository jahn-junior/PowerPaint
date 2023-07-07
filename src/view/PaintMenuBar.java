package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.PaintGUI.ToolAction;

/**
 * JMenuBar containing all of the options listed within the code specifications. The more
 * notable of these include "Color..." and "Fill Color..." options which presents a 
 * JColorChooser prompt, a "Clear" button to clear all previously drawn shapes, and 
 * JRadioButtons for DrawingTool selection.
 * 
 * @author JJ Coldiron (jj.coldiron@outlook.com)
 * @version 1.0
 */
public class PaintMenuBar extends JMenuBar implements PropertyChangeListener {

    // CONSTANTS
    /** Auto-generated serial version UID for object serialization. */
    private static final long serialVersionUID = 6144640517682185162L;
    
    /** Default stroke width for drawing prior to user-prompted width change. */
    private static final int DEFAULT_THICKNESS = 3;
    
    /** The maximum allowed thickness for stroke width. */
    private static final int MAX_THICKNESS = 15;
    
    /** Distance between major ticks for JSlider readability. */
    private static final int SLIDER_SCALE = 5;
    
    /** Off-screen point for clearing the user's active tool from the DrawingPanel. */
    private static final Point CLEAR = new Point(-100, -100);
    
    /** "W" logo to be displayed in the "About..." JOptionPane. */
    private static final ImageIcon LOGO = new ImageIcon("./icons/brush_logo.png");
    
    
    // INSTANCE FIELDS
    /** List containing the ToolActions for each DrawingTool Object. */
    private final List<ToolAction> myActions;
    
    /** DrawingPanel so that the previously-drawn PaintShapes can be accessed and cleared. */
    private final PaintPanel myPanel;
    
    /** The currently selected outline color for the PaintShape to be drawn. */
    private Color myColor;
    
    /** The currently selected fill color for the PaintShape to be drawn. */
    private Color myFillColor;
    
    /** Checkbox that allows the user to choose whether or not their shapes are filled. */
    private final JCheckBoxMenuItem myFillCheckBox;
    
    /** The button used to clear all previously drawn PaintShapes. */
    private final JMenuItem myClearButton;
    
    /** ButtonGroup to ensure that the JRadioButtonMenuItems perform as intended. */
    private final ButtonGroup myGroup;
    
    
    /**
     * Constructor method to initialize fields to parameters or reasonable initial values.
     * Calls private helper method to set up components of the JMenuBar.
     * 
     * @param theActions
     * @param thePanel
     */
    protected PaintMenuBar(final List<ToolAction> theActions, final PaintPanel thePanel) {
        
        super();
        myActions = theActions;
        myPanel = thePanel;
        myColor = Color.BLACK;
        myFillColor = Color.BLACK;
        myFillCheckBox = new JCheckBoxMenuItem("Fill");
        myClearButton = new JMenuItem("Clear");
        myGroup = new ButtonGroup();
        setup();
    }
    
    /**
     * Private helper method to set up components and listeners within the JMenuBar. Includes
     * 6 anonymous inner listener classes that are then attached to their respective menu
     * items.
     */
    private void setup() {
        
        final JMenu optionMenu = new JMenu("Options");
        final JMenu thickSubmenu = new JMenu("Thickness");
        final JSlider slider = new JSlider(0, 0, MAX_THICKNESS, DEFAULT_THICKNESS);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(SLIDER_SCALE);
        slider.setLabelTable(slider.createStandardLabels(SLIDER_SCALE));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = slider.getValue();
                myPanel.setStrokeWidth(value);
            }
        });
        
        thickSubmenu.add(slider);
        
        final JMenuItem drawColor = new JMenuItem("Draw Color...");
        
        drawColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myColor = JColorChooser.showDialog(myPanel, "Draw Color", myColor, true);
                if (myColor != null) {
                	myPanel.setPaintColor(myColor);	
                }
            }
        });
        
        final JMenuItem fillColor = new JMenuItem("Fill Color...");
        
        fillColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myFillColor = JColorChooser.showDialog(myPanel, "Fill Color", myFillColor,
                                                       true);
                if (myFillColor != null) {
                    myPanel.setFillColor(myFillColor);
                }
            }
        });
        
        myFillCheckBox.setEnabled(false);
        
        myFillCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.setFillStatus(myFillCheckBox.getState());
            }
        });
        
        optionMenu.add(thickSubmenu);
        optionMenu.addSeparator();
        optionMenu.add(drawColor);
        optionMenu.add(fillColor);
        optionMenu.addSeparator();
        optionMenu.add(myFillCheckBox);
        optionMenu.addSeparator();
        
        myClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.getShapes().clear();
                myPanel.getActiveTool().setStartPoint(CLEAR);
                myPanel.getActiveTool().setEndPoint(CLEAR);
                myPanel.repaint();
                myClearButton.setEnabled(false);
            }
        });
        
        myClearButton.setEnabled(false);
        optionMenu.add(myClearButton);
        
        final JMenu toolMenu = new JMenu("Tools");
        for (final ToolAction action: myActions) {
            final AbstractButton button = new JRadioButtonMenuItem(action);
            myGroup.add(button);
            toolMenu.add(button);
        }
        
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About...");
        
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myPanel, "JJ Coldiron\n" +
                                              "Email: jj.coldiron@outlook.com\n" +
                                              "GitHub: https://github.com/jahn-junior",
                                              "PowerPaint", JOptionPane.PLAIN_MESSAGE, 
                                              LOGO);
            }
        });
        
        helpMenu.add(about);
        add(optionMenu);
        add(toolMenu);
        add(helpMenu);
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        
    	// Enables the Clear button once a shape is drawn.
        if (theEvent.getPropertyName().equals("shapes")) {
            myClearButton.setEnabled(true);
        } else {
        	// Enables/Disables the Fill checkbox depending on the Shape being drawn.
            myFillCheckBox.setEnabled(!theEvent.getPropertyName().equals("Line") &&
                                      !theEvent.getPropertyName().equals("Pencil"));
        }
    }
}
