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

import com.swtworkbench.ed.aware.marshaller.MarshallException;
import com.swtworkbench.ed.aware.validator.IValidator;

/**
 * Class CurrencyValidator.  We'll treat doubles as currency in this
 * application.  In practice, you could pick any type to treat as currency
 * or even create your own type.  All you have to do is define marshallers
 * and a validator for your type.
 * 
 * @author daveo
 */
public class CurrencyValidator implements IValidator {
    private String2CurrencyMarshaller marshaller = new String2CurrencyMarshaller();
    
    private String help;
    
    private final String HELP = "Please enter a currency value appropriate for your locale";

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        help=HELP;
        
        // Allow the empty string
        if (value.equals(""))
            return true;
            
        // Allow anything that parses as a double
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e1) {
            // Just continue...
        }
        
        // And allow anything that parses as currency
        try {
            marshaller.convert(value);
        } catch (MarshallException e) {
            help = e.getCause().getMessage();
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        help=HELP;
        
        // Allow anything that parses as a double
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e1) {
            // Just continue...
        }
        
        // Allow anything that parses as currency
        try {
            marshaller.convert(value);
        } catch (MarshallException e) {
            help = e.getCause().getMessage();
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.validator.IValidator#getHelp()
     */
    public String getHelp() {
        return help;
    }

}
