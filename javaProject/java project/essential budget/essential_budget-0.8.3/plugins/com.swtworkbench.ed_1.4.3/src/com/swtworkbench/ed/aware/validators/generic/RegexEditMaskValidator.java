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

/**
 * Class RegexEditMaskValidator.  A validator that uses both regular expressions
 * and edit masks rather than procedural code to specify what is legal
 * in an uncompleted or completed field.
 * 
 * @author daveo
 */
public class RegexEditMaskValidator extends RegexValidator implements IEditMaskValidator {
    private final EditMask editMask;
    
    /**
     * Constructor RegexEditMaskValidator.  Constructs a validator that
     * uses regular expressions and edit masks rather than procedural code to specify what
     * is legal in an uncompleted or a completed field.
     * 
     * @param partial A String containing a Perl 5 regular expression describing 
     *   what is permissible in a partially complete field.
     * @param complete A String containing a Perl 5 regular expression describing 
     *   what is permissible in a fully completed and valid field.
     * @param editMask The edit mask that will be used to help the user enter the data
     * @param help A user-friendly help message that will be sent if the user
     *   tries to enter invalid data.
     * 
     * @throws MaskFormatException if the edit mask contains a syntax error.
     */
    public RegexEditMaskValidator(String partial, String complete, String editMask, String help) throws MaskFormatException {
        super(partial, complete, help);
        this.editMask = new EditMask(editMask);
    }

    /**
     * Constructor RegexEditMaskValidator.  Constructs a validator that
     * uses regular expressions and edit masks rather than procedural code to specify what
     * is legal in an uncompleted or a completed field.
     * 
     * @param partial A String containing a Perl 5 regular expression describing 
     *   what is permissible in a partially complete field.
     * @param complete A String containing a Perl 5 regular expression describing 
     *   what is permissible in a fully completed and valid field.
     * @param editMask The edit mask that will be used to help the user enter the data
     * @param help A user-friendly help message that will be sent if the user
     *   tries to enter invalid data.
     */
    public RegexEditMaskValidator(String partial, String complete, EditMask editMask, String help) {
        super(partial, complete, help);
        this.editMask = editMask;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.IEditMaskValidator#getEditMask()
     */
    public EditMask getEditMask() {
        return editMask;
    }

}
