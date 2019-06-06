/*
 * This file is part of com.swtworkbench.essentialbudget.
 *
 * com.swtworkbench.essentialbudget is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.essentialbudget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.essentialbudget; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.essentialbudget.model.typehandling;

import java.text.SimpleDateFormat;

import com.swtworkbench.ed.aware.maskededit.EditMask;
import com.swtworkbench.ed.aware.validator.IEditMaskValidator;

/**
 * Class DateValidator.  Validate in MM/DD/YYYY format.  This is supplied by
 * the application and not by the framework because it would need to be localized
 * to at least the locale, plus various industries handle dates somewhat 
 * differently.  So Essential Data provides the hooks to easily plug in your own
 * date validator and mashaller, while not necessarily supplying its own.
 * 
 * @author daveo
 */
public class DateValidator implements IEditMaskValidator {

    private static final String HELP = "Please enter a date in the form MM/DD/YYYY";
        
    private String help = HELP;


    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        help=HELP;
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        help=HELP;
        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            df.parse(value);
            return true;
        } catch (Exception e) {
            help="Invalid date: " + e.getMessage();
            return false;
        }
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#getHelp()
     */
    public String getHelp() {
        return help;
    }
    
    private final EditMask editMask = new EditMask("#{1,2}/#{1,2}/####");
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IEditMaskValidator#getEditMask()
     */
    public EditMask getEditMask() {
        return editMask;
    }
}

