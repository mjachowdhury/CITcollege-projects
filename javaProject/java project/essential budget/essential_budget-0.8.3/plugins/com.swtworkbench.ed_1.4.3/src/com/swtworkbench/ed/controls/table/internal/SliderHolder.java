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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Slider;

/** (non-API)
 * Class SliderHolder.  Holds, shows, hides, and provides access to a slider.<p>
 * 
 * Designed to be used in a GridLayout.
 * 
 * @author daveo
 */
public class SliderHolder extends Composite {
    public SliderHolder(Composite parent, int style) {
        super(parent, SWT.NULL);
        slider = new Slider(this, style);
        setLayout(new FillLayout());
        setVisible(true);
        setTabList(new Control[] {});
    }
    
    private final Slider slider;
    private boolean visible;
    
    /**
     * @return Returns the slider.
     */
    public Slider getSlider() {
        return slider;
    }

    /**
     * @return Returns if the slider is visible.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible Sets if the slider is visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
        GridData gd = new GridData();
        if ((slider.getStyle() & SWT.VERTICAL) != 0) {
            gd.grabExcessVerticalSpace=true;
            gd.verticalAlignment=GridData.FILL;
            if (!visible) {
                gd.widthHint=0;
            }
        } else {
            gd.grabExcessHorizontalSpace=true;
            gd.horizontalAlignment=GridData.FILL;
            if (!visible) {
                gd.heightHint=0;
            }
        }
        setLayoutData(gd);
        slider.setEnabled(visible);
        getParent().layout(true);
    }

}
