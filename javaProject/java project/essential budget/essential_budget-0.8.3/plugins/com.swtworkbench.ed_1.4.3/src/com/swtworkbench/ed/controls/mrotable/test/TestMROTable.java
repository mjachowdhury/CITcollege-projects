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

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.mrotable.MROTable;
import com.swtworkbench.ed.controls.mrotable.TableLayout;
import com.swtworkbench.swtutils.framework.SWTApplication;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class TestMROTable.
 * 
 * @author djo
 */
public class TestMROTable extends SWTApplication {
    
    private LinkedList todos;
    
    public LinkedList getToDos() {
        return todos;
    }
    
    public IObjectFactory getToDosObjectFactory() {
        return new IObjectFactory() {
			public NewObject getNewObject(Object collection) {
                int position = (int)(Math.random() * todos.size());
                Todo newTodo = new Todo(0, "", 0, 0);
                todos.add(position, newTodo);
				return new NewObject(newTodo, position);
			}
        };
    }
    
    private void initData() {
        todos = new LinkedList();
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
//        todos.add(new Todo(0, "Testzwwrf", 2, 13));
//        todos.add(new Todo(0, "Terewqstz", 5, 34));
//        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
//        todos.add(new Todo(0, "Testz", 4, 3));
//        todos.add(new Todo(0, "Tefdsastz", 4, 38));
        todos.add(new Todo(0, "Testzwwrf", 2, 13));
        todos.add(new Todo(0, "Terewqstz", 5, 34));
        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
        todos.add(new Todo(0, "Testz", 4, 3));
        todos.add(new Todo(0, "Tefdsastz", 4, 38));
        todos.add(new Todo(0, "Testzwwrf", 2, 13));
        todos.add(new Todo(0, "Terewqstz", 5, 34));
        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
        todos.add(new Todo(0, "Testz", 4, 3));
        todos.add(new Todo(0, "Tefdsastz", 4, 38));
        todos.add(new Todo(0, "Testzwwrf", 2, 13));
        todos.add(new Todo(0, "Terewqstz", 5, 34));
        todos.add(new Todo(0, "Tehjkjgjghstz",14, 32));
    }
    
    private Color white;
    private Color darkgray;

    private int[] getColumnWeights() {
        return new int[] {10, 70, 10, 10};
    }
    
    private class HeaderFactory implements IPartControlFactory {
        public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.CENTER, darkgray));

            new Label(part, SWT.RIGHT).setText("Priority");
            new Label(part, SWT.NULL).setText("Description");
            new Label(part, SWT.RIGHT).setText("Proposed");
            new Label(part, SWT.RIGHT).setText("Actual");
            
            return part;
        }
    }
    
    private class RowFactory implements IPartControlFactory {
        public Composite createPartControl(Composite parent) {
            // The row Composite
            Composite part = new Composite(parent, SWT.NULL);
            part.setBackground(white);
            part.setLayout(new TableLayout(getColumnWeights(), SWT.BORDER));

            // The actual SWT editor objects
            new Text(part, SWT.RIGHT).setData("boundTo", "Priority");
            new Text(part, SWT.NULL).setData("boundTo", "Description");
            new Text(part, SWT.RIGHT).setData("boundTo", "Proposed");
            new Text(part, SWT.RIGHT).setData("boundTo", "Actual");
            
            return part;
        }
    }
    

    /* (non-Javadoc)
	 * @see com.swtworkbench.swtutils.snippet.Snippet#setupUI(org.eclipse.swt.widgets.Shell)
	 */
	public void setupUI(Shell parent) {
        setExitOnUncaughtException(true);
        initData();
        white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
        darkgray = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);

        parent.setLayout(new FillLayout());

        // Make the actual table
        MROTable table = new MROTable(parent, SWT.BORDER);
        table.setHeaderFactory(new HeaderFactory());
        try {
            table.edit(this, "ToDos", new RowFactory());
        } catch (Exception e) {
            Logger.log().error(e, "Error creating MROTable");
        }
	}
    
    /* (non-Javadoc)
	 * @see com.swtworkbench.swtutils.framework.SWTApplication#afterClose()
	 */
	public void afterClose() {
        for (Iterator i = todos.iterator(); i.hasNext();) {
			Todo todo = (Todo) i.next();
            Logger.log().debug(TestMROTable.class, todo.toString());
		}
	}
    
	public static void main(String[] args) {
        new TestMROTable();
	}
}
