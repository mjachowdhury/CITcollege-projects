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
package com.swtworkbench.ed.controls.mrotable.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Class TestCoolBar.
 * 
 * @author djo
 */
public class TestCoolBar {
	static int itemCount;
    static CoolItem createItem(CoolBar coolBar, int count) {
		ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);

		for (int i = 0; i < count; i++) {
			ToolItem item = new ToolItem(toolBar, SWT.PUSH);
			item.setText(itemCount++ + "");
		}

		toolBar.pack();
		Point size = toolBar.getSize();
		CoolItem item = new CoolItem(coolBar, SWT.NONE);
		item.setControl(toolBar);
		Point preferred = item.computeSize(size.x, size.y);
		item.setPreferredSize(preferred);
		return item;
	}

    public static void main(String[] args) {
        Display display = new Display ();
        Shell shell = new Shell (display);
        CoolBar bar = new CoolBar (shell, SWT.BORDER);
        for (int i=0; i<2; i++) {
            CoolItem item = new CoolItem (bar, SWT.NONE);
            Button button = new Button (bar, SWT.PUSH);
            button.setText ("Button " + i);
            Point size = button.computeSize (SWT.DEFAULT, SWT.DEFAULT);
            item.setPreferredSize (item.computeSize (size.x, size.y));
            item.setControl (button);
        }
        bar.pack();
        shell.open ();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
	}
//    static int itemCount;
//    static CoolItem createItem(CoolBar coolBar, int count) {
//        ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
//        
//        for (int i = 0; i < count; i++) {
//            ToolItem item = new ToolItem(toolBar, SWT.PUSH);
//            item.setText(itemCount++ +"");
//        }
//        
//        toolBar.pack();
//        Point size = toolBar.getSize();
//        CoolItem item = new CoolItem(coolBar, SWT.NONE);
//        item.setControl(toolBar);
//        Point preferred = item.computeSize(size.x, size.y);
//        item.setPreferredSize(preferred);
//        return item;
//    }
//
//    public static void main(String[] args) {
//        Display display = new Display();
//        final Shell shell = new Shell(display);
//        CoolBar coolBar = new CoolBar(shell, SWT.NONE);
//        createItem(coolBar, 3);
//        createItem(coolBar, 2);
//        createItem(coolBar, 3);
//        createItem(coolBar, 4);
//        int style = SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL;
//        Text text = new Text(shell, style);
//        FormLayout layout = new FormLayout();
//        shell.setLayout(layout);
//        FormData coolData = new FormData();
//        coolData.left = new FormAttachment(0);
//        coolData.right = new FormAttachment(100);
//        coolData.top = new FormAttachment(0);
//        coolBar.setLayoutData(coolData);
//        coolBar.addListener(SWT.Resize, new Listener() {
//            public void handleEvent(Event event) {
//                shell.layout();
//            }
//        });
//        FormData textData = new FormData();
//        textData.left = new FormAttachment(0);
//        textData.right = new FormAttachment(100);
//        textData.top = new FormAttachment(coolBar);
//        textData.bottom = new FormAttachment(100);
//        text.setLayoutData(textData);
//        shell.open();
//        while (!shell.isDisposed()) {
//            if (!display.readAndDispatch()) display.sleep();
//        }
//        display.dispose();
//    }

}
