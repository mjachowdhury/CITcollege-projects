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
package com.swtworkbench.ed.aware.validators.generic;

import com.swtworkbench.ed.aware.validator.IValidator;

/**
 * Class ReadOnlyValidator.  
 * 
 * @author djo
 */
public class ReadOnlyValidator implements IValidator {
    
    private static ReadOnlyValidator nullValidator = null;
    
    public static ReadOnlyValidator getDefault() {
        if (nullValidator == null)
            nullValidator = new ReadOnlyValidator();
        return nullValidator;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        // The control is read-only
        return false;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        // Whatever value it currently has is fine
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#getHelp()
     */
    public String getHelp() {
        return "This field is read-only";
    }

}
