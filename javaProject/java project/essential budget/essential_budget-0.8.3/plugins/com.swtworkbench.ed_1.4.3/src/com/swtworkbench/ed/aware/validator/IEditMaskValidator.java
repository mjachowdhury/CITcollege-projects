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
package com.swtworkbench.ed.aware.validator;

import com.swtworkbench.ed.aware.maskededit.EditMask;

/**
 * Class IEditMaskValidator.  Describes validators that supply an edit
 * mask for use when editing inside Text controls.
 * 
 * @author daveo
 */
public interface IEditMaskValidator extends IValidator {
    /**
     * Method getEditMask.  Returns the edit mask used for editing this
     * particular field or type.
     * 
     * @return An EditMask object
     */
    public EditMask getEditMask();
}
