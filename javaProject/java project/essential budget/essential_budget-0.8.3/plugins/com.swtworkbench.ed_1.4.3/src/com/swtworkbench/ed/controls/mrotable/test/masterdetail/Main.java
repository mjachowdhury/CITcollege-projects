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
package com.swtworkbench.ed.controls.mrotable.test.masterdetail;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.swtutils.framework.SWTApplication;

/**
 * Class Main.
 * 
 * @author djo
 */
public class Main extends SWTApplication {
    
	/* (non-Javadoc)
	 * @see com.swtworkbench.swtutils.framework.SWTSnippet#setupUI(org.eclipse.swt.widgets.Shell)
	 */
	protected void setupUI(Shell parent) {
        People people = new People();

		setExitOnUncaughtException(true);
        
        // Define the layout
        parent.setLayout(new FillLayout());
        SashForm body = new SashForm(parent, SWT.VERTICAL);
        PersonMRO peopleEditor = new PersonMRO(body, SWT.BORDER);
        PersonMRO acquaintancesEditor = new PersonMRO(body, SWT.BORDER);
        
        // Hook up the data
        try {
            peopleEditor.edit(people, "People");
            acquaintancesEditor.setInputProperty("Acquaintances");
            acquaintancesEditor.setMaster(peopleEditor);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
	}
    
    public static void main(String[] args) {
		new Main();
	}
}
