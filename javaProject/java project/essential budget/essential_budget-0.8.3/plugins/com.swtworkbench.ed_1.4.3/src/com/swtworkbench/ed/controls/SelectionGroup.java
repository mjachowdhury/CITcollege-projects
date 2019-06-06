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
package com.swtworkbench.ed.controls;

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Class SelectionGroup.  A radio button group or checkbox group object.
 * 
 * @author daveo
 */
public class SelectionGroup extends Composite {

    /**
     * Constructor SelectionGroup.  Construct a SelectionGroup object.<p>
     * 
     * In addition to the styles recognized by Composite, SelectionGroup
     * recognizes the following styles:<p>
     * 
     * SWT.MULTI - Multi-select (checkbox group)<br>
     * SWT.HORIZONTAL - Lay out contents horizontally<br>
     * SWT.VERTICAL - Lay out contents vertically (the default)<p>
     * 
     * (Note that HORIZONTAL and VERTICAL have been repurposed from their
     * original definition as a part of Scrollable)<p>
     * 
     * Internally, SelectionGroup uses a RowLayout to position its child
     * controls.  Child control positions may be fine-tuned by manipulating
     * this layout manager.
     * 
     * @param parent The parent control
     * @param style The style bits
     */
    public SelectionGroup(Composite parent, int style) {
        super(parent, style & (SWT.MULTI|SWT.BORDER|SWT.NO_BACKGROUND|SWT.NO_FOCUS|SWT.NO_MERGE_PAINTS|SWT.NO_REDRAW_RESIZE|SWT.NO_RADIO_GROUP));
        RowLayout layout = new RowLayout();
        if ((style & SWT.HORIZONTAL) != 0)
            layout.type = SWT.HORIZONTAL;
        else
            layout.type = SWT.VERTICAL;
        setLayout(layout);
    }
    
    private boolean isMultiSelect() {
        if ((getStyle() & SWT.MULTI) != 0)
            return true;
        else
            return false;
    }
    
    private LinkedList items = new LinkedList();

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        for (Iterator i = items.iterator(); i.hasNext();) {
            Button element = (Button) i.next();
            element.removeSelectionListener(selectionListener);
        }
        super.dispose();
    }
    
    /**
     * Method add.  
     * @param itemText
     */
    public void add(String itemText) {
        int buttonStyle = SWT.RADIO;
        if (isMultiSelect())
            buttonStyle = SWT.CHECK;
        Button newButton = new Button(this, buttonStyle);
        newButton.setText(itemText);
        newButton.addSelectionListener(selectionListener);
        items.add(newButton);
        pack();
    }
    
    /**
     * Method removeAll.  
     */
    public void removeAll() {
        for (Iterator i = items.iterator(); i.hasNext();) {
            Button element = (Button) i.next();
            element.dispose();
        }
        items.clear();
    }
    
    /**
     * Method deselectAll.  
     */
    public void deselectAll() {
        for (Iterator i = items.iterator(); i.hasNext();) {
            Button element = (Button) i.next();
            element.setSelection(false);
        }
    }
    
    /**
     * Method setSelection.  
     * @param item
     */
    public void setSelection(int item) {
        Iterator iter = items.iterator();
        Button button = (Button) iter.next();
        while (item > 0) {
            button = (Button) iter.next();
            --item;
        }
        button.setSelection(true);
    }
    
    /**
     * Method setSelection.  
     * @param items
     */
    public void setSelection(int[] items) {
        for (int i = 0; i < items.length; i++) {
            setSelection(items[i]);
        }
    }

    /**
     * Method getSelectionIndex.  
     * @return
     */
    public int getSelectionIndex() {
        int result=0;
        for (Iterator i = items.iterator(); i.hasNext();) {
            Button element = (Button) i.next();
            if (element.getSelection())
                return result;
            ++result;
        }
        return -1;
    }
    
    /**
     * Method getSelection.  
     * @return
     */
    public String[] getSelection() {
        LinkedList strings = new LinkedList();
        for (Iterator i = items.iterator(); i.hasNext();) {
            Button element = (Button) i.next();
            if (element.getSelection())
                strings.add(element.getText());
        }
        String[] result = new String[strings.size()];
        int i=0;
        for (Iterator iter = strings.iterator(); iter.hasNext(); ++i) {
            String element = (String) iter.next();
            result[i] = element;
        }
        return result;
    }
    
    /**
     * Method getSelectionIndices.  
     * @return
     */
    public int[] getSelectionIndices() {
        LinkedList offsets = new LinkedList();
        int offset=0;
        for (Iterator i = items.iterator(); i.hasNext(); ++offset) {
            Button element = (Button) i.next();
            if (element.getSelection())
                offsets.add(new Integer(offset));
        }
        int[] result = new int[offsets.size()];
        int i=0;
        for (Iterator iter = offsets.iterator(); iter.hasNext(); ++i) {
            Integer element = (Integer) iter.next();
            result[i] = element.intValue();
        }
        return result;
    }
    
    private LinkedList selectionListeners = new LinkedList();
    
    /**
     * Method addSelectionListener.  
     * @param l
     */
    public void addSelectionListener(SelectionListener l) {
        selectionListeners.add(l);
    }
    
    /**
     * Method removeSelectionListener.  
     * @param l
     */
    public void removeSelectionListener(SelectionListener l) {
        selectionListeners.remove(l);
    }
    
    private SelectionListener selectionListener = new SelectionListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetSelected(SelectionEvent e) {
            fireSelectionListeners(e);
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
         */
        public void widgetDefaultSelected(SelectionEvent e) {
            fireDefaultSelectionListeners(e);
        }
    };

    protected void fireSelectionListeners(SelectionEvent rawEvent) {
        for (Iterator i = selectionListeners.iterator(); i.hasNext();) {
            SelectionListener listener = (SelectionListener) i.next();
            listener.widgetSelected(rawEvent);
        }
    }

    protected void fireDefaultSelectionListeners(SelectionEvent rawEvent) {
        for (Iterator i = selectionListeners.iterator(); i.hasNext();) {
            SelectionListener listener = (SelectionListener) i.next();
            listener.widgetDefaultSelected(rawEvent);
        }
    }
}


