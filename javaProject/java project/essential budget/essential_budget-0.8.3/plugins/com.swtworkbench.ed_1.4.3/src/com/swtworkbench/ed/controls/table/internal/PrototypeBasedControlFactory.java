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
package com.swtworkbench.ed.controls.table.internal;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

import com.swtworkbench.ed.controls.table.IEditControlFactory;

/**
 * Class PrototypeBasedControlFactory.  An IEditControlFactory that makes
 * copies of existing "prototype" edit controls that the designer put
 * in the table (presumably using a GUI builder or similar tool).
 */
public class PrototypeBasedControlFactory implements IEditControlFactory {
    protected ColumnEditorFactory[] columnEditorCreators = null;
        
    public PrototypeBasedControlFactory(Table table) {
        // Get the editor for each column
        // We assume that these are in columnar order...
        Control[] children = table.getChildren();
        columnEditorCreators = new ColumnEditorFactory[children.length];
        for (int i = 0; i < children.length; i++) {
            columnEditorCreators[i] = new ColumnEditorFactory(children[i]);
            children[i].dispose();
        }
            
        // Remove the prototype row
        table.remove(0);
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.navigators.IEditControlCreator#newInstance(org.eclipse.swt.widgets.Composite, int)
     */
    public Control newInstance(Composite parent, int column) {
        return columnEditorCreators[column].newInstance(parent);
    }
}
