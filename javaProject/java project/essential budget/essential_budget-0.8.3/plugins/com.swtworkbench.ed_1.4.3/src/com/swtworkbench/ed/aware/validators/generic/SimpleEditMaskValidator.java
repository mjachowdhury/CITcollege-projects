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

import com.swtworkbench.ed.aware.maskededit.EditMask;
import com.swtworkbench.ed.aware.maskededit.MaskFormatException;
import com.swtworkbench.ed.aware.validator.IEditMaskValidator;
import com.swtworkbench.ed.aware.validator.IValidator;
import com.swtworkbench.ed.aware.validator.ValidatorRegistry;

/**
 * Class SimpleEditMaskValidator.  Represents a validator that inherits its default
 * validation behavior from the default validator for the specified data type
 * and just adds an edit mask to it.<p>
 * 
 * Use this class when you just want to add edit mask capability to the default
 * validator's behavior.
 * 
 * @author daveo
 */
public class SimpleEditMaskValidator implements IEditMaskValidator {
    
    private IValidator parent;
    private final EditMask editMask;
    private String help = null;
    
    /**
     * Constructor SimpleEditMaskValidator.  Construct a validator that inherits its 
     * default validation behavior from the default validator for the specified data 
     * type and just adds an edit mask to it.<p>
     * 
     * Use this class when you just want to add edit mask capability to the default
     * validator's behavior.
     * 
     * @param toValidate The class needing validation services
     * @param editMask The edit mask string
     * @throws MaskFormatException If the edit mask string has a syntax error
     */
    public SimpleEditMaskValidator(Class toValidate, String editMask) throws MaskFormatException {
        parent = ValidatorRegistry.get(toValidate);
        if (parent == null)
            parent = new NullValidator();
        this.editMask = new EditMask(editMask);
    }

    /**
     * Constructor SimpleEditMaskValidator.  Construct a validator that inherits its 
     * default validation behavior from the default validator for the specified data 
     * type and just adds an edit mask to it.<p>
     * 
     * Use this class when you just want to add edit mask capability to the default
     * validator's behavior.
     * 
     * @param toValidate The class needing validation services
     * @param editMask The edit mask string
     * @param help The help message
     * @throws MaskFormatException If the edit mask string has a syntax error
     */
    public SimpleEditMaskValidator(Class toValidate, String editMask, String help) throws MaskFormatException {
        parent = ValidatorRegistry.get(toValidate);
        if (parent == null)
            parent = new NullValidator();
        this.editMask = new EditMask(editMask);
        this.help = help;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.IEditMaskValidator#getEditMask()
     */
    public EditMask getEditMask() {
        return editMask;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        return parent.validatePartial(value);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        return parent.validateComplete(value);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.IValidator#getHelp()
     */
    public String getHelp() {
        if (help != null)
            return help;
        else
            return parent.getHelp();
    }

}
