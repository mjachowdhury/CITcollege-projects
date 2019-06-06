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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Class ViewFolderPage.  A page inside a ViewFolder.  This class is intended
 * to be subclassed and createPartControl() overridden to provide your 
 * custom functionality.
 * 
 * @author daveo
 */
public abstract class ViewFolderPage {
    /**
     * Constructor ViewFolderPage.  Constructor for a ViewFolderPage.
     * 
     * @param parent The ViewFolder to contain this page
     * @param icon The icon for the tab or null if none
     * @param title The ViewFolder's title
     */
    public ViewFolderPage(final ViewFolder parent, Image icon, String title) {
        this.parent = parent;
        final ViewFolderPage theThis = this;
        this.image = icon;
        this.text = title;
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                parent.add(theThis);
                parent.updateTitleBar(theThis);
            }
        });
    }
    
    public void dispose() {
        parent.disposePage(this);
    }
    
    protected final ViewFolder parent;

    private String text = "";
    
    /**
     * Method setText.  Sets the page's folder tab caption
     * @param title A title
     */
    public void setText(String title) {
        this.text = title;
        parent.updateTitleBar(this);
    }

    /**
     * Method getText.  Gets the page's folder tab caption
     * @return The title
     */
    public String getText() {
        if (text != null)
            return text;
        else
            return "Untitled";
    }

    private Image image;

    /**
     * Method setImage.  Sets the page's folder tab icon.  If icon is null,
     * this does nothing.
     * 
     * @param icon The icon Image
     */
    public void setImage(Image icon) {
        this.image = icon;
        parent.updateTitleBar(this);
    }

    /**
     * Method getImage.  Returns the page's folder tab icon.
     * @return The icon Image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Method createPartControl.  Clients are required to override this
     * method in order to provide their UI.
     * 
     * @param parent The parent composite
     * @return The parent of the control hierarchy that was created
     */
    protected abstract Composite createPartControl(Composite parent);

}


