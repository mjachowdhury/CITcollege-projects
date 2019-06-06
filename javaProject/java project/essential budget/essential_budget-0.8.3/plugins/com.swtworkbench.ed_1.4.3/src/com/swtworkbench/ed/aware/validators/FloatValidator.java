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
package com.swtworkbench.ed.aware.validators;

import org.apache.oro.text.perl.Perl5Util;

import com.swtworkbench.ed.aware.validator.IValidator;

/**
 * Class DoubleValidator.  
 * 
 * @author daveo
 */
public class FloatValidator implements IValidator {

    private static final String HELP = "Please enter a number in the range " +
        Float.MIN_VALUE + ".." + Float.MAX_VALUE;
        
    private String help = HELP;

    private static Perl5Util perl = new Perl5Util();


    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        help=HELP;
        return perl.match("/^\\-?[0-9]*\\.?[0-9]*([0-9]+e\\-?([0-9]+\\.)?[0-9]*)?$/", value);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        help=HELP;
        try {
            Float.parseFloat(value);
            return true;
        } catch (Exception e) {
            help=e.getMessage();
            return false;
        }
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#getHelp()
     */
    public String getHelp() {
        return help;
    }

}
