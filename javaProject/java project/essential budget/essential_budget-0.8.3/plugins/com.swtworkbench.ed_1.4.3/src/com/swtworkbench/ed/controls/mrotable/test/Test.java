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
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
/**
 * Class Test.
 * 
 * @author djo
 */
public class Test {

	private org.eclipse.swt.widgets.Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"

	private Button button = null;
	private Button button1 = null;
	private Text text = null;
	private Browser browser = null;
	private Label status = null;
	/**
	 * This method initializes browser	
	 *
	 */    
	private void createBrowser() {
		org.eclipse.swt.layout.GridData gridData1 = new org.eclipse.swt.layout.GridData();
		browser = new Browser(sShell, SWT.NONE);		   
		gridData1.horizontalSpan = 4;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		browser.setLayoutData(gridData1);
		browser.setBackground(org.eclipse.swt.widgets.Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_WHITE));
		browser.addStatusTextListener(new org.eclipse.swt.browser.StatusTextListener() { 
			public void changed(org.eclipse.swt.browser.StatusTextEvent e) {
                status.setText(e.text);
			}
		});
		browser.addLocationListener(new org.eclipse.swt.browser.LocationAdapter() { 
			public void changed(org.eclipse.swt.browser.LocationEvent e) {
                text.setText(e.location);
			}
		});
	}
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new org.eclipse.swt.widgets.Shell();		   
		org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
		org.eclipse.swt.layout.GridData gd = new org.eclipse.swt.layout.GridData();
		org.eclipse.swt.layout.GridData gridData3 = new org.eclipse.swt.layout.GridData();
		button = new Button(sShell, SWT.LEFT);
		button1 = new Button(sShell, SWT.RIGHT);
		text = new Text(sShell, SWT.BORDER);
		status = new Label(sShell, SWT.NONE);
		createBrowser();
		sShell.setLayout(gridLayout1);
		sShell.setText("SWT Web Browser");
		gridLayout1.numColumns = 4;
		gd.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gd.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gd.grabExcessHorizontalSpace = true;
		text.setLayoutData(gd);
		button.setText("<< Previous");
		button1.setText("Next >>");
		status.setText("");
		status.setLayoutData(gridData3);
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		sShell.setSize(new org.eclipse.swt.graphics.Point(726,616));
		sShell.addShellListener(new org.eclipse.swt.events.ShellAdapter() { 
			public void shellActivated(org.eclipse.swt.events.ShellEvent e) {
                if (text.getText().equals("")) {
                    browser.setUrl("http://www.coconut-palm-software.com");
                }
			}
		});
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() { 
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {    
				browser.forward();
			}
		});
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() { 
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {    
				browser.back();
			}
		});
		text.addKeyListener(new org.eclipse.swt.events.KeyAdapter() { 
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
                if (e.character == '\r' || e.character == '\n')
                	browser.setUrl(text.getText());
			}
		});
	}
    public static void main(String[] args) {
        /* Before this is run, be sure to set up the following in the launch configuration 
         * (Arguments->VM Arguments) for the correct SWT library path. 
         * The following is a windows example:
         * -Djava.library.path="installation_directory\plugins\org.eclipse.swt.win32_3.0.0\os\win32\x86"
         */
        org.eclipse.swt.widgets.Display display = org.eclipse.swt.widgets.Display.getDefault();     
        Test thisClass = new Test();
        thisClass.createSShell() ;
        thisClass.sShell.open();
        
        while (!thisClass.sShell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep ();
        }
        display.dispose();      
    }
}
