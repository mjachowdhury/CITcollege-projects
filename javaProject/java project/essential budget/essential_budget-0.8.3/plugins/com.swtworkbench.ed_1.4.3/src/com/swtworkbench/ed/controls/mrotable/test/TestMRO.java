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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.ed.controls.mro.internal.MRO;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.mrotable.TableLayout;
import com.swtworkbench.swtutils.framework.SWTApplication;

/**
 * Class TestMRO.
 *
 * @author daveo
 */
public class TestMRO extends SWTApplication {
    
    private class HeaderFactory implements IPartControlFactory {
		public Composite createPartControl(Composite parent) {
            Composite header = new Composite(parent, SWT.NULL);
            header.setLayout(new TableLayout(new int[] {50, 50}, SWT.CENTER | SWT.BORDER));
            new Label(header, SWT.NULL).setText("Date");
            new Label(header, SWT.RIGHT).setText("Part number");
			return null;
		}
    }
    
    private class TestPart implements IPartControlFactory {
        public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
            part.setBackground(white);
            part.setLayout(new TableLayout(new int[] {50, 50}, SWT.CENTER | SWT.BORDER));
            Label label = new Label(part, SWT.NULL);
            label.setText("2/23/2002");
            label.setBackground(white);
            new Text(part, SWT.RIGHT);
            return part;
        }
    }
	
	/* (non-Javadoc)
	 * @see com.swtworkbench.swtutils.snippet.Snippet#setupUI(org.eclipse.swt.widgets.Shell)
	 */
	public void setupUI(Shell parent) {
        parent.setLayout(new FillLayout());
        MRO mro = new MRO(parent, SWT.NULL);
        mro.setHeaderFactory(new HeaderFactory());
        mro.setPartControlFactory(new TestPart());
        mro.setNumRows(20);
        mro.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
	}

	public static void main(String[] args) {
		new TestMRO();
	}
}


