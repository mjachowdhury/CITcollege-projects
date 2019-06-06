
package com.swtworkbench.swtutils.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtworkbench.swtutils.framework.SWTApplication;

/**
 * Test the Button widget...
 * @author daveo
 */
public class ButtonTest extends SWTApplication {

    /* (non-Javadoc)
     * @see com.swtworkbench.swtutils.snippet.Snippet#setupUI(org.eclipse.swt.widgets.Shell)
     */
    public void setupUI(Shell parent) {
        parent.setLayout(new RowLayout());
        CButton button = new CButton(parent, SWT.NULL);
        button.setText("Hello, world");
        button = new CButton(parent, SWT.NULL);
        button.setText("Hello, world");
        button.setImage(new Image(Display.getDefault(), ButtonTest.class.getResourceAsStream("hello.gif")));
        button = new CButton(parent, SWT.NULL);
        button.setImage(new Image(Display.getDefault(), ButtonTest.class.getResourceAsStream("hello.gif")));
    }
    
    public static void main(String[] args) {
        new ButtonTest();
    }
}
