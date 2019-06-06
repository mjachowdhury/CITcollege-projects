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
package com.swtworkbench.ed.controls.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Class Navigator.  
 * 
 * @author daveo
 */
public class Navigator extends Composite {
    
    private CoolBar bar;
    
    public Navigator(Composite parent, int style) {
        super(parent, style);
        
        CoolBar bar = new CoolBar(this, SWT.BORDER);
        bar.setSize (200, 600);
        for (int i=0; i<=2; i++) {
            CoolItem item = new CoolItem (bar, SWT.NONE);
        }
        Button button = new Button(bar, SWT.PUSH);
        button.setText("FRED");
        button.pack();
        Point size = button.getSize();
        CoolItem [] items = bar.getItems();
        CoolItem item = items[0];
        item.setControl(button);
        item.setSize(item.computeSize (size.x, size.y));
    }
    
    public static void main(String[] args) {
        Display display;
        Shell shell;
    
        display = new Display();
        shell = new Shell(display);
        shell.setText("Navigator Test");
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.makeColumnsEqualWidth = false;
        shell.setLayout(layout);
        
        Navigator navigator = new Navigator(shell, SWT.NULL);

        shell.setSize(600, 600);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
