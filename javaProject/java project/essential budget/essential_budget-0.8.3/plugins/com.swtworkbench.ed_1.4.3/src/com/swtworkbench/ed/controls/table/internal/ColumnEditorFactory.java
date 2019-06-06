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

import java.lang.reflect.Constructor;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.swtworkbench.ed.reflect.Reflect;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class ColumnEditorFactory.  A helper class that encapsulates the type and
 * style bits for a particular column's editors and can create new instances
 * of that editor on demand.
 * 
 * @author daveo
 */
public class ColumnEditorFactory {
    private Class editorClass;
    private int style;
    private String text;
    private Color background;
    private Constructor constructor;
        
    /**
     * Constructor ColumnEditorFactory.  Construct a ColumnEditorFactory based
     * on the specified prototype control.
     * 
     * @param control The prototype control to duplicate.
     */
    public ColumnEditorFactory(Control control) {
        editorClass = control.getClass();
        style = control.getStyle();
        text = (String) Reflect.invokei(control, "getText", new Object[] {});
        background = (Color) Reflect.invokei(control, "getBackground", new Object[] {});
        try {
            constructor = editorClass.getConstructor(new Class[] {Composite.class, Integer.TYPE});
        } catch (Exception e) {
            Logger.log().error(e, "Unable to get constructor for " + editorClass.getName());
        }
    }
    
    
    /**
     * Method newInstance.  Create a new instance of this column editor.
     * @param parent The SWT parent Composite
     * 
     * @return The edit control that was created or null on failure.  (Failures are also logged.)
     */
    public Control newInstance(Composite parent) {
        Control result = null;
        try {
            result = (Control) constructor.newInstance(new Object[] {parent, new Integer(style)});
            if (text != null)
                Reflect.invokei(result, "setText", new Object[] {text});
            if (background != null)
                Reflect.invokei(result, "setBackground", new Object[] {background});
        } catch (Exception e) {
            Logger.log().error(e, "Unable to construct a " + editorClass.getName());
        }
        return result;
    }
}
    
