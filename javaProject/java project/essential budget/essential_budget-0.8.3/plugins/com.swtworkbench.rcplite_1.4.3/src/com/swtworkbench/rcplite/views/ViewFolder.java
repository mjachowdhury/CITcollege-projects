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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.swtworkbench.swtutils.fromeclipse.CTabFolder;
import com.swtworkbench.swtutils.fromeclipse.CTabItem;

/**
 * Class ViewFolder.  A ViewFolder is like an Eclipse Editor region.  In order
 * to use a ViewFolder, one must instantiate ViewFolderPages inside it representing
 * each page in the ViewFolder.
 * 
 * @author daveo
 */
public class ViewFolder extends Part {

    /**
     * Constructor ViewFolder.  Conststruct a ViewFolder.
     * 
     * @param parent
     */
    public ViewFolder(Composite parent) {
        super(parent, SWT.BORDER);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createTopLeft(org.eclipse.swt.widgets.Composite)
     */
    protected Control createTopLeft(Composite parent) {
        return null;
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
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    protected Composite createPartControl(Composite parent) {
        contents = new CTabFolder(parent, SWT.NULL);
        return contents;
    }
    
    protected CTabFolder contents;

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#setActivated(int)
     */
    protected void setActivated(int focused) {
        Color fgColor;
        Color[] bgColors;
        int[] bgPercents;
    
        switch (focused) {
            case Part.FOCUS_TRUE:
                fgColor = COLOR_TITLE_FOREGROUND;
                bgColors = activeEditorGradient;
                bgPercents = activeEditorGradientPercentages;
                break;
            case Part.FOCUS_SHELL_LOST:
                fgColor = COLOR_TITLE_INACTIVE_FOREGROUND;
                bgColors = deactivatedEditorGradient;
                bgPercents = deactivatedEditorGradientPercentages;
                break;
            case Part.FOCUS_FALSE:
                fgColor = COLOR_LIST_FOREGROUND;
                bgColors = activeNoFocusEditorGradient;
                bgPercents = activeNoFocusEditorGradientPercentages;
                break;
            default :
                fgColor = null;
                bgColors = null;
                bgPercents = null;
                break;
        }
    
        if (contents != null) {
            contents.setSelectionForeground(fgColor);
            contents.setSelectionBackground(bgColors, bgPercents);
            contents.update();
        }
    }

    /**(Non-API)
     * Method add.  Add a ViewFolderPage to this ViewFolder.  This is called
     * automatically from ViewFolderPage's constructor.
     * 
     * @param page the ViewFolderPage to add.
     */
    public void add(ViewFolderPage page) {
        CTabItem item = new CTabItem(contents, SWT.NULL);
        item.setData(page);
        Composite pageContents = page.createPartControl(contents);
        item.setControl(pageContents);
        if (contents.getSelection() == null)
            contents.setSelection(item);
    }
    
    private CTabItem findControl(ViewFolderPage page) {
        CTabItem[] items = contents.getItems();
        for (int i = 0; i < items.length; i++) {
            ViewFolderPage candidate = (ViewFolderPage) items[i].getData();
            if (candidate == page) {
                return items[i];
            }
        }
        return null;
    }

    /**(Non-API)
     * Method disposePage.  Remove a ViewFolderPage from this ViewFolder.  This is
     * called automatically from ViewFolderPage's dispose() method.
     * 
     * @param page the ViewFolderPage to remove.
     */
    public void disposePage(ViewFolderPage page) {
        CTabItem item = findControl(page);
        if (item != null) {
            item.getControl().dispose();
            item.dispose();
        }
    }

    /**
     * Method updateTitleBar.  
     * @param page
     */
    public void updateTitleBar(ViewFolderPage page) {
        CTabItem item = findControl(page);
        if (item != null) {
            item.setText(page.getText());
            Image icon = page.getImage();
            if (icon != null)
                item.setImage(icon);
        }
    }

}
