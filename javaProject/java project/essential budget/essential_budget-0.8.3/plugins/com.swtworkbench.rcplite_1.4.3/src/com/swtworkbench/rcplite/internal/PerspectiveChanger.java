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
package com.swtworkbench.rcplite.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;

import com.swtworkbench.rcplite.IPerspectiveChangeListener;

/**
 * Class PerspectiveChanger.  A perspective changer object.
 * 
 * @author daveo
 */
public class PerspectiveChanger extends Composite {
    public PerspectiveChanger(Composite parent, int style) {
        super(parent, style);
        setLayout(new PerspectiveChangerLayout());
    }

    class PerspectiveChangerLayout extends Layout {
        final int margin = 3;           // The space between buttons

        /* (non-Javadoc)
         * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
         */
        protected Point computeSize(
            Composite composite,
            int wHint,
            int hHint,
            boolean flushCache) 
        {
            int width = 1; int height = 1;
            
            Control[] children = composite.getChildren();
            for (int i = 0; i < children.length; i++) {
                Button child = (Button) children[i];
                Point preferredSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                if (height < preferredSize.y) height = preferredSize.y;
                width += preferredSize.x + margin;
            }
            return new Point(width, height);
        }

        /* (non-Javadoc)
         * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
         */
        protected void layout(Composite composite, boolean flushCache) {
            int left = margin;
            int height = composite.getBounds().height;
            
            Control[] children = composite.getChildren();
            for (int i = 0; i < children.length; i++) {
                Button child = (Button) children[i];
                Point preferredSize = child.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
                child.setBounds(new Rectangle(left, (height - preferredSize.y) / 2, preferredSize.x, preferredSize.y));
                left += preferredSize.x + margin;
            }
        }
    }
    
    protected HashMap perspectives = new HashMap();
    
    // The current and previously-current perspective.
    protected String current;
    private String previous;

    private ImageData convertDepth(ImageData mask) {
        //if (true) return mask;
        if (mask.depth == 1) return mask;
        PaletteData palette = new PaletteData(new RGB[] {new RGB(0, 0, 0), new RGB(255,255,255)});
        ImageData tempMask = new ImageData(mask.width, mask.height, 1, palette);
        /* Find index of black in mask palette */
        int blackIndex = 0;
        RGB[] rgbs = mask.getRGBs();
        if (rgbs != null) {
            while (blackIndex < rgbs.length) {
                if (rgbs[blackIndex].equals(palette.colors[0])) break;
                blackIndex++;
            }
            if (blackIndex == rgbs.length) SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        int[] pixels = new int[mask.width];
        for (int y = 0; y < mask.height; y++) {
            mask.getPixels(0, y, mask.width, pixels, 0);
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] == blackIndex) {
                    pixels[i] = 0;
                } else {
                    pixels[i] = 1;
                }
            }
            tempMask.setPixels(0, y, mask.width, pixels, 0);
        }
        return tempMask;
    }
    
    /**
     * Method add.  Add a new perspective to the perspective changer.
     * @param id The perspective's String ID
     * @param name The perspective name, for the button and/or tool tip
     * @param icon The perspective's icon or null if none.
     */
    public void add(String id, String caption, Image icon) {
        Button button = new Button(this, SWT.TOGGLE);
        button.setData(id);
        if (icon != null) {
            /*
             * FEATURE IN SWT:  Button can't display both an image and a 
             * text caption at the same time.  The workaround is to make 
             * your own image containing the icon and the caption and use
             * that instead.
             */
            
            // We still have to set the text in order for accelerator keys to
            // work correctly.  The text just won't be displayed once the 
            // image is set.
            button.setText(caption);
    
            // Figure out how big everything has to be
            Rectangle iconSize = icon.getBounds();
            
            GC gc = new GC(button);
            Point captionSize = gc.textExtent(caption, SWT.DRAW_MNEMONIC);
    
            Rectangle iconTotalSize = icon.getBounds();
            iconTotalSize.width += 4;
            iconTotalSize.width += captionSize.x;
            gc.dispose();
            
            // Draw the icon
            Image image = new Image(Display.getDefault(), iconTotalSize.width, iconTotalSize.height);
            gc = new GC(image);
            gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
            gc.setForeground(button.getForeground());
            gc.fillRectangle(iconTotalSize);
            if (iconSize.height > captionSize.y) {
                gc.drawImage(icon, 0, 0);
                gc.setFont(button.getFont());
                gc.drawText(caption, iconSize.width+2, iconSize.height-captionSize.y-(iconSize.height-captionSize.y)/2, SWT.DRAW_MNEMONIC | SWT.DRAW_TRANSPARENT);
            } else {
                gc.drawImage(icon, 0, captionSize.y-iconSize.height-(captionSize.y-iconSize.height)/2);
                gc.setFont(button.getFont());
                gc.drawText(caption, iconSize.width+2, 0, SWT.DRAW_MNEMONIC | SWT.DRAW_TRANSPARENT);
            }
            gc.dispose();
            
            // Draw the transparancy mask
            ImageData iconTransparancy = icon.getImageData().getTransparencyMask();
            Image iconTransparancyMask = new Image(Display.getDefault(), iconTransparancy);
            PaletteData palette = new PaletteData (
                new RGB [] {
                    new RGB (0, 0, 0),          // transparant pixels are white
                    new RGB (0xFF, 0xFF, 0xFF), // opaque pixels are black
                });
            ImageData maskData = new ImageData (iconTotalSize.width, iconTotalSize.height, 1, palette);
            Image mask = new Image (Display.getDefault(), maskData);
            gc = new GC (mask);
            gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
            gc.fillRectangle(0, 0, iconTotalSize.width, iconTotalSize.height);
            if (iconSize.height > captionSize.y) {
                gc.setBackground (Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
                gc.drawImage(iconTransparancyMask, 0, 0);
                gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
                gc.drawText(caption, iconSize.width+2, iconSize.height-captionSize.y-(iconSize.height-captionSize.y)/2, SWT.DRAW_MNEMONIC | SWT.DRAW_TRANSPARENT);
            } else {
                gc.setBackground (Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
                gc.drawImage(iconTransparancyMask, 0, captionSize.y-iconSize.height-(captionSize.y-iconSize.height)/2);
                gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
                gc.drawText(caption, iconSize.width+2, 0, SWT.DRAW_MNEMONIC | SWT.DRAW_TRANSPARENT);
            }
            gc.dispose();
    
            // Get the data for the image and mask so we can compose them into
            // the final icon...
            maskData = mask.getImageData ();
            mask.dispose();
    
            ImageData imageData = image.getImageData();
            image.dispose();

            /*
             * Feature in SWT: On Linux, the depth winds up getting set to something other
             * than 1 and there's an assert in the code to make sure it's 1.  The workaround
             * is to manually convert the bit-depth to 1 each time.  See bug #64266.
             */
            // Make the final image (including transparancy)
            Image iconPlusText = new Image(Display.getDefault(), imageData, convertDepth(maskData));
            button.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent e) {
                    Button b = (Button) e.widget;
                    Image image = b.getImage();
                    if (image != null)
                        image.dispose();
                }
            });
            
            button.setImage(iconPlusText);
        } else {
            button.setText(caption);
        }
        button.addSelectionListener(selectionListener);
        perspectives.put(id, button);
        getParent().layout(true);
        if (current == null) {
            current = id;
            button.setSelection(true);
        }
    }

    /**
     * Method remove.  Removes a perspective from the perspective switcher.
     * This does *not* dispose of the icon (in accordance with the "If you
     * created it, you dispose it" principle.
     * 
     * @param name The perspective name.
     */
    public void remove(String id) {
        Button button = (Button) perspectives.get(id);
        if (button != null) {
            perspectives.remove(id);
            button.dispose();
            getParent().layout(true);
        }
    }
    
    /**
     * Method select.  Sets the current perspective.
     * 
     * @param name The name of the perspective to select.
     * @return true if the perspective could be selected; false otherwise.
     */
    public void select(String id) {
        previous = current;
        current = id;
        Button prev = (Button) perspectives.get(previous);
        Button cur = (Button) perspectives.get(current);
        if (cur == null)
            return;
        if (prev != null)
            prev.setSelection(false);
        cur.setSelection(true);
    }
    
    /*
     * Manage the button selections
     */
    SelectionListener selectionListener = new SelectionListener() {
        public void widgetDefaultSelected(SelectionEvent e) {
            widgetSelected(e);
        }
        public void widgetSelected(SelectionEvent e) {
            final Button widget = (Button) e.widget;
            String id = (String) widget.getData();
            e.doit = fireChangeListeners(id, current);
            if (e.doit)
                select(id);
            else
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        widget.setSelection(false);
                    }
                });
        }
    };
    
    /**
     * Method getSelection.  Returns the currently-selected perspective.
     * 
     * @return The ID of the currently-selected perspective
     */
    public String getSelection() {
        return current;
    }
    
    private LinkedList changeListeners = new LinkedList();

    /**
     * Method addChangeListener.  
     * @param l
     */
    public void addChangeListener(IPerspectiveChangeListener l) {
        changeListeners.addLast(l);
    }

    /**
     * Method removeChangeListener.  
     * @param l
     */
    public void removeChangeListener(IPerspectiveChangeListener l) {
        changeListeners.remove(l);
    }
    
    protected boolean fireChangeListeners(String current, String previous) {
        boolean result = true;
        for (Iterator i = changeListeners.iterator(); i.hasNext();) {
            IPerspectiveChangeListener l = (IPerspectiveChangeListener) i.next();
            result = l.perspectiveChange(current, previous);
            if (!result) break;
        }
        return result;
    }

}
