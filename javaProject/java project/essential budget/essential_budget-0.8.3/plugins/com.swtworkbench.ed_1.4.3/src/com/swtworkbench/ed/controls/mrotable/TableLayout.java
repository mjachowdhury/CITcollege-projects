/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.controls.mrotable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;

/**
 * Instances of this class determine the size and position of the children of a
 * <code>Composite</code> by positioning them in cells that are a certain
 * percentage of the parent wide. The result of placing several
 * <code>Composite</code>s, each controlled by a TableLayout with identical
 * parameters is to form a table with the result.
 * 
 * @author daveo
 */
public class TableLayout extends Layout {
    
    /**
	 * Construct a TableLayout layout manager, supplying it with an int array of
	 * weights for each column in the table. Each weight describes the
	 * percentage of the table width that will be occupied by the corresponding
	 * column.
	 * 
	 * <p>
	 * The number of weights in the array must equal the number of children in
	 * the table and the weights must all add up to 100.
	 * </p>
	 * 
	 * @param weights
	 *            An int array specifying the percentage of the total space that
	 *            will be occupied by each column.
	 * 
	 * @param style
	 *            One of SWT.TOP, SWT.CENTER, or SWT.BOTTOM to indicate vertical
	 *            justification. If SWT.NULL or SWT.DEFAULT is passed,
	 *            SWT.CENTER is used. If SWT.BORDER is passed, grid lines are
	 *            drawn similar to SWT's Table control grid lines.
	 */
    public TableLayout(int[] weights, int style) {
        init(weights, style);
        gridLineColor = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
    }
    
    /**
	 * Construct a TableLayout layout manager, supplying it with an int array of
	 * weights for each column in the table. Each weight describes the
	 * percentage of the table width that will be occupied by the corresponding
	 * column.
	 * 
	 * <p>
	 * The number of weights in the array must equal the number of children in
	 * the table and the weights must all add up to 100.
	 * </p>
	 * 
	 * @param weights
	 *            An int array specifying the percentage of the total space that
	 *            will be occupied by each column.
	 * 
	 * @param style
	 *            One of SWT.TOP, SWT.CENTER, or SWT.BOTTOM to indicate vertical
	 *            justification. If SWT.NULL or SWT.DEFAULT is passed,
	 *            SWT.CENTER is used. SWT.BORDER is automatically added to the
	 *            style if this constructor is used.
     * 
	 * @param gridLineColor
	 *            The color that will be used to draw grid lines.
	 */
    public TableLayout(int[] weights, int style, Color gridLineColor) {
        init(weights, style | SWT.BORDER);
        this.gridLineColor = gridLineColor;
    }
    
    private void init(int[] weights, int style) {
        this.weights = weights;
        if (style == SWT.NULL || style == SWT.DEFAULT)
            style |= SWT.CENTER;
        this.style = style;
        
        // Error trap the style bits
        int validstyles = SWT.TOP | SWT.CENTER | SWT.BOTTOM | SWT.DEFAULT | SWT.BORDER;
        if ((style | validstyles) != validstyles)
            throw new IllegalArgumentException("Illegal style bit");
        
        // Error trap the weights
        int total = 0;
        for (int i = 0; i < weights.length; i++) {
            total += weights[i];
        }
        if (total != 100) 
            throw new IllegalArgumentException("Weights must add up to 100");
    }
    
    private int style;
    private int[] weights;
    private Point preferredSize = null;
    private final int BORDERWIDTH = 1;
    private Composite composite = null;

    /**
     * public field <code>horizontalSpacing</code>
     * 
     * <p>Indicates how many pixels of space should be added horizontally 
     * between cells.</p>
     */
    public int horizontalSpacing = 5;
    
    /**
     * public field <code>verticalSpacing</code>
     * 
     * <p>Indicates how many pixels of space should be added vertically 
     * between cells.</p>
     */
    public int verticalSpacing = 1;

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
     */
    protected Point computeSize(Composite composite, int wHint, int hHint,
            boolean flushCache) 
    {
        // Error trap the weights
        Control[] children = composite.getChildren();
        if (weights.length != children.length) {
            throw new IllegalArgumentException("We have " + children.length + " children but " + weights.length + " weights");
        }
        
        // Now compute the size
        if (preferredSize == null || flushCache) {
            // Get the preferred sizes of each child widget
            Point[] preferredSizes = new Point[weights.length];
            for (int i = 0; i < children.length; i++) {
                preferredSizes[i] = children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT, flushCache);
            }
            
            // My preferred size is the one that lets each child have at least
            // its preferred size and satisfies my weights constraints.  Compute
            // the new preferred size by applying the weights constraints
            // to each child's preferred size
            
            // Add up the preferred sizes
            int width=0;
            int height=0;
            for (int i = 0; i < preferredSizes.length; i++) {
                if (preferredSizes[i].y > height)
                    height = preferredSizes[i].y;
                width += preferredSizes[i].x;
                width += BORDERWIDTH + 2*horizontalSpacing;
            }
            height += BORDERWIDTH + 2*verticalSpacing;
            
            // Compute the child widths based on the percentages and the 
            // current width
            int[] widths = new int[weights.length];
            for (int i = 0; i < widths.length; i++) {
                widths[i] = (int)(width * ((float)weights[i]/100));
            }
            
            // Find the child that has the biggest discrepancy between its
            // desired size and the size assigned percentage-wise
            int maxDelta = 0;
            for (int i = 0; i < preferredSizes.length; i++) {
                int delta = preferredSizes[i].x - widths[i];
                if (delta > maxDelta)
                    maxDelta = delta;
            }
            ++maxDelta;
            width = maxDelta * preferredSizes.length + width;
            
            preferredSize = new Point(width, height);
        }
        return preferredSize;
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
     */
    protected void layout(Composite composite, boolean flushCache) {
        // Set up border drawing if applicable
        if ((style & SWT.BORDER) != 0 && this.composite == null) {
            this.composite = composite;
            composite.addPaintListener(new TableLayoutPaintListener());
        }
        
        Control[] children = composite.getChildren();
        if (children.length != weights.length) {
            throw new IllegalArgumentException("The number of weights != number of childen");
        }
        Point totalSize = composite.getSize();

        int left=0;
        for (int i = 0; i < children.length; i++) {
            // Compute and set the child position and size
			Control child = children[i];
            Point childLocation = new Point(left + horizontalSpacing, 0);
            Point childSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            if (childSize.y > totalSize.y) {
                childLocation.y = 0;
            } else {
                if ((style & SWT.CENTER) != 0) {
                    childLocation.y = (int)(((float)totalSize.y - childSize.y) / 2);
                } else if ((style & SWT.BOTTOM) != 0) {
                    childLocation.y = totalSize.y - childSize.y - verticalSpacing - BORDERWIDTH;
                } else {
                    childLocation.y = verticalSpacing;
                }
            }
            childSize.x = (int)(totalSize.x * ((float)weights[i]/100) - BORDERWIDTH - 2*horizontalSpacing);
            child.setLocation(childLocation);
            child.setSize(childSize);
            
            // Now compute the next left position
            left = childLocation.x + childSize.x + BORDERWIDTH + horizontalSpacing;
		}
    }
    
    private Color gridLineColor;

    /**
     * @return Returns the gridLineColor.
     */
    public Color getGridLineColor() {
        return gridLineColor;
    }
    /**
     * @param gridLineColor The gridLineColor to set.
     */
    public void setGridLineColor(Color gridLineColor) {
        this.gridLineColor = gridLineColor;
    }
    
    /*
     * Draw grid lines if requested...
     */
    private class TableLayoutPaintListener implements PaintListener {
        public void paintControl(PaintEvent e) {
            e.gc.setForeground(gridLineColor);
            Control[] children = composite.getChildren();
            if (children.length != weights.length) {
                throw new IllegalArgumentException("The number of weights != number of childen");
            }
            Point totalSize = composite.getSize();
            
            int left=0;
            for (int i = 0; i < children.length; i++) {
                Control child = children[i];
                Point childLocation = new Point(left, 0);
                Point childSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                childSize.x = (int)(totalSize.x * ((float)weights[i]/100) - BORDERWIDTH);
                
                // Now compute the next left position
                left = childLocation.x + childSize.x + BORDERWIDTH;
                e.gc.drawLine(left-1, 0, left-1, totalSize.y);
            }
            e.gc.drawLine(0, totalSize.y-1, totalSize.x, totalSize.y-1);
        }
    }
}
