/*
 * This file is part of com.swtworkbench.rcplite.
 *
 * com.swtworkbench.rcplite is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.rcplite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.rcplite; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.rcplite.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Class View.  Base class for all views in RCPLite.
 * 
 * @author daveo
 */
public abstract class View extends Part {
    
    /**
     * Constructor View.  Constructor for a View
     * 
     * @param parent The SWT parent
     * @param icon The view's icon or null if none
     * @param title The view's title
     */
    public View(Composite parent, Image icon, String title) {
        super(parent, SWT.BORDER);
        setText(title);
        setImage(icon);
    }
    
    protected CLabel titleLabel;
    
    /**
     * Method setText.  Sets the view's title bar text
     * @param title The title string
     */
    public void setText(String title) {
        titleLabel.setText(title);
    }
    
    /**
     * Method getText.  Returns the view's title bar text
     * @return The title string
     */
    public String getText() {
        return titleLabel.getText();
    }
    
    /**
     * Method setImage.  Sets the title bar icon.
     * @param icon The title bar icon
     */
    public void setImage(Image icon) {
        if (icon != null)
            titleLabel.setImage(icon);
    }
    
    /**
     * Method getImage.  Returns the title bar icon.
     * @return the titlebar icon
     */
    public Image getImage() {
        return titleLabel.getImage();
    }
    
    protected int showFocus = Part.FOCUS_FALSE;

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createTopLeft(org.eclipse.swt.widgets.Composite)
     */
    protected Control createTopLeft(Composite parent) {
        titleLabel = new CLabel(parent, SWT.SHADOW_NONE);
        return titleLabel;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createTopCenter(org.eclipse.swt.widgets.Composite)
     */
    protected Control createTopCenter(Composite parent) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createTopRight(org.eclipse.swt.widgets.Composite)
     */
    protected Control createTopRight(Composite parent) {
        return null;
    }


    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#setActivated(boolean)
     */
    protected void setActivated(int focused) {
        showFocus = focused;
        drawGradient();
    }


    /**
     * Method drawGradient.  Draws the (lack of) focus gradient.
     */
    protected void drawGradient() {
        if (titleLabel == null)// || viewToolBar == null || isvToolBar == null)
            return;

        switch (showFocus) {
            case Part.FOCUS_TRUE:
                titleLabel.setBackground(activeViewGradient, activeViewGradientPercentages);
                titleLabel.setForeground(COLOR_TITLE_FOREGROUND);
                break;
            case Part.FOCUS_SHELL_LOST:
                titleLabel.setBackground(deactivatedViewGradient, deactivatedViewGradientPercentages);
                titleLabel.setForeground(COLOR_TITLE_INACTIVE_FOREGROUND);
                break;
            case Part.FOCUS_FALSE:
                titleLabel.setBackground(null, null);
                titleLabel.setForeground(null);
                break;
        }

        titleLabel.update();
    }
}

