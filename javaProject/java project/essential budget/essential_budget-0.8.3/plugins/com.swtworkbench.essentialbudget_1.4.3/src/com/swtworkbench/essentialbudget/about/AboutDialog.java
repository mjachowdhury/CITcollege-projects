/*
 * This file is part of com.swtworkbench.essentialbudget.
 *
 * com.swtworkbench.essentialbudget is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.essentialbudget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.essentialbudget; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.essentialbudget.about;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * 
 * @author daveodell
 */
public class AboutDialog {

	private org.eclipse.swt.widgets.Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
    
	private Composite composite = null;
	private AboutPanel aboutPanel = null;
	private Button button = null;
    public Shell getShell() {
        return sShell;
    }

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell(SWT.APPLICATION_MODAL | SWT.SHELL_TRIM);		   
		GridData gridData19 = new org.eclipse.swt.layout.GridData();
		createComposite();
		button = new Button(sShell, SWT.NONE);
		sShell.setSize(new org.eclipse.swt.graphics.Point(604,356));
		sShell.setText("About");
		sShell.setLayout(new GridLayout());
		sShell.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/com/swtworkbench/essentialbudget/icons/budget_e.gif")));
		gridData19.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData19.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		button.setLayoutData(gridData19);
		button.setText("&Close");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() { 
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                sShell.close();
                sShell.dispose();
			}
		});
	}
	/**
	 * This method initializes composite	
	 *
	 */    
	private void createComposite() {
		GridData gridData16 = new org.eclipse.swt.layout.GridData();
		composite = new Composite(sShell, SWT.NONE);		   
		createAboutPanel();
		gridData16.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData16.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData16.grabExcessHorizontalSpace = true;
		gridData16.grabExcessVerticalSpace = true;
		composite.setLayoutData(gridData16);
		composite.setLayout(new FillLayout());
	}
	/**
	 * This method initializes aboutPanel	
	 *
	 */    
	private void createAboutPanel() {
		aboutPanel = new AboutPanel(composite, SWT.NONE);		   
	}

    /**
     * Open the About dialog box... 
     */
    public void open() {
        Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
                createSShell();
                sShell.pack(true);
                sShell.open();
			}
		});
    }
}
