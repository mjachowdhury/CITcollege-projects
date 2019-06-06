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

/**
 * Class IValidator.  Validates values to make sure they are within the set
 * of allowed values for a type or a field.<p>
 * 
 * Essential Data provides a set of default validators forthe standard Java
 * data types.  Clients may implement additional validators for their own purposes.  
 * 
 * @author daveo
 */
public interface IValidator {
    /**
     * Method validate.  Validate the specified string prefix.  The value does
     * not need to be complete, but all characters that are currently present
     * must be valid.<p>
     * 
     * Some things (like telephone number strings, for example), have to have
     * prefix substrings validated separately from the entire string.  For 
     * example, "(800) 555-12" is valid but not complete for a US phone number.  
     * The user is allowed to enter this, but is not allowed to leave the 
     * entry field until the rest of the value has been entered.  
     *   
     * @param value The String to be validated
     * @return True if the String is valid; false otherwise
     */
    public boolean validatePartial(String value);
    
    /**
     * Method validateComplete.  Validate the specified string as being complete
     * and valid.  The value may now be successfully and correctly stored
     * back into the object.
     * 
     * @param value The String to be validated
     * @return True if the value is valid; false otherwise
     */
    public boolean validateComplete(String value);
    
    /**
     * Method getHelp.  Returns the help text for this data type.
     * @return A help string.
     */
    public String getHelp();
}
