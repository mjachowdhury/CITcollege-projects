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
package com.swtworkbench.ed.aware.fields;

import java.lang.reflect.Method;

import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.aware.interfaces.IAwareWidget;
import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.ed.reflect.Reflect;

/**
 * Class AwareFieldFactory.  Analyzes the Control that was passed and constructs
 * an appropritate IAwareWidget based on some heuristics. 
 * 
 * @author daveo
 */
public class AwareFieldFactory {
    /**
     * Method getFieldAdapter.  Analyzes the Control that was passed and constructs
     * an appropritate IAwareWidget based on some heuristics.<p>
     * 
     * Note that because you can't inherit from SWT controls, we are forced to
     * use reflection to look for common API names in order to determine the
     * class's capabilities.  Otherwise, we will incorrectly handle things that
     * inherit from Composite or Widget but actually act as a decorator for
     * some other SWT object.
     * 
     * @param dataPersister The IFieldAdapter required to construct the IAwareWidget
     * @param control The control to analyze
     * @return
     */
    public static IAwareWidget getFieldAdapter(IFieldAdapter dataPersister, Control control) {
        // Check boxes and the like...
        Method setSelection = Reflect.getMethod(control, "setSelection", new Class[] {Boolean.TYPE});
        if (setSelection != null)
            return new AwareBooleanField(dataPersister, control);

        // Single-select list boxes, radio groups...
        Method getSelectionIndices = Reflect.getMethod(control, "getSelectionIndices", new Class[] {});
        if (getSelectionIndices != null)
            return new AwareSelectorField(dataPersister, control);

        // Combo boxes...
        Method getSelectionIndex = Reflect.getMethod(control, "getSelectionIndex", new Class[] {});
        if (getSelectionIndex != null)
            return new AwareComboField(dataPersister, control);
            
        // Text fields and the like...
        return new AwareTextField(dataPersister, control);
    }
}
