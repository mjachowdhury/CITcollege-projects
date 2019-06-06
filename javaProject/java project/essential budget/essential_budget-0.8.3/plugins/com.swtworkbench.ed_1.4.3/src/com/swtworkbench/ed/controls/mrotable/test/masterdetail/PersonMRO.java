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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.mrotable.MROTable;
import com.swtworkbench.ed.controls.mrotable.TableLayout;

/**
 * Class PersonMRO.
 * 
 * @author djo
 */
public class PersonMRO extends MROTable {

    final Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
    final Color darkgray = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);

    private int[] getColumnWeights() {
        return new int[] {50, 50};
    }

    IPartControlFactory headerFactory = new IPartControlFactory() {
        public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.CENTER, darkgray));

            new Label(part, SWT.NULL).setText("First Name");
            new Label(part, SWT.NULL).setText("Last Name");
            
            return part;
        }
    };

    IPartControlFactory rowFactory = new IPartControlFactory() {
        public Composite createPartControl(Composite parent) {
            // The row Composite
            Composite part = new Composite(parent, SWT.NULL);
            part.setBackground(white);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.BORDER));

            // The actual SWT editor objects
            new Text(part, SWT.NULL).setData("boundTo", "FirstName");
            new Text(part, SWT.NULL).setData("boundTo", "LastName");
            
            return part;
        }
    };

    public PersonMRO(Composite parent, int style) {
		super(parent, style);

        setHeaderFactory(headerFactory);
        
	}

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.controls.mrotable.MROTable#edit(java.lang.Object, java.lang.String, com.swtworkbench.ed.controls.mrotable.IPartControlFactory)
     */
    public void edit(Object input, String propertyName) throws SecurityException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, UnableToSaveException 
    {
        super.edit(input, propertyName, rowFactory);
    }
    
}
