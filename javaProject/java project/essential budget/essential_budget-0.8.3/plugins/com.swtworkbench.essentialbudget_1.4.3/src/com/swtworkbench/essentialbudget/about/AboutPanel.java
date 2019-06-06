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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
/**
 * The About box panel.
 * @author daveo
 */
public class AboutPanel extends Composite {

	private Composite composite1 = null;
	private Label label3 = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label = null;
	private Label label4 = null;
	public AboutPanel(Composite parent, int style) {
		super(parent, style);
		initialize();
	}
	private void initialize() {
		GridData gridData7 = new org.eclipse.swt.layout.GridData();
		GridLayout gridLayout1 = new GridLayout();
		label3 = new Label(this, SWT.NONE);
		createComposite1();
		this.setLayout(gridLayout1);
		gridLayout1.numColumns = 1;
		label3.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/com/swtworkbench/essentialbudget/icons/logo.png")));
		label3.setLayoutData(gridData7);
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData7.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		this.setSize(new org.eclipse.swt.graphics.Point(516,301));
	}
	/**
	 * This method initializes composite1	
	 *
	 */    
	private void createComposite1() {
		GridData gridData22 = new org.eclipse.swt.layout.GridData();
		GridData gridData21 = new org.eclipse.swt.layout.GridData();
		GridData gridData13 = new org.eclipse.swt.layout.GridData();
		GridData gridData11 = new org.eclipse.swt.layout.GridData();
		GridData gridData2 = new org.eclipse.swt.layout.GridData();
		composite1 = new Composite(this, SWT.NONE);		   
		label1 = new Label(composite1, SWT.NONE);
		label = new Label(composite1, SWT.SEPARATOR | SWT.HORIZONTAL);
		label2 = new Label(composite1, SWT.WRAP);
		label4 = new Label(composite1, SWT.SEPARATOR | SWT.HORIZONTAL);
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		composite1.setLayoutData(gridData2);
		composite1.setLayout(new GridLayout());
		label1.setText("Copyright 2004 ASC Online, Inc.");
		label1.setLayoutData(gridData11);
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData11.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData11.grabExcessHorizontalSpace = false;
		gridData11.grabExcessVerticalSpace = false;
		label2.setText("Essential Budget is is free software; you can redistribute it and/or modify it under the terms of version 2 of the GNU General Public License as published by the Free Software Foundation.\n\nEssential Budget is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.\n\nYou should have received a copy of the GNU General Public License along with Essential Budget; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA");
		label2.setLayoutData(gridData13);
		gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData13.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData13.grabExcessHorizontalSpace = true;
		gridData13.grabExcessVerticalSpace = true;
		label.setText("Label");
		label.setLayoutData(gridData21);
		gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData21.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData21.grabExcessHorizontalSpace = true;
		label4.setText("Label");
		label4.setLayoutData(gridData22);
		gridData22.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData22.verticalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData22.grabExcessHorizontalSpace = true;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
