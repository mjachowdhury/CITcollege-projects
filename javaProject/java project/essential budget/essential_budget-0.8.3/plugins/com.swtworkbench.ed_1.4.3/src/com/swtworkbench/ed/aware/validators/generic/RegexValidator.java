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

import org.apache.oro.text.perl.Perl5Util;

import com.swtworkbench.ed.aware.validator.IValidator;

/**
 * Class RegexValidator.  Defines a validator in terms of Perl 5 regular 
 * expressions.  This classes uses the Apache ORO regular expression library
 * in order to be compatible with older JREs that did not supply regular
 * expression support.<p>
 * 
 * For more information on Perl 5 regular expressions, consult the Apache ORO 
 * documentation or any good book on Perl.
 * 
 * @author daveo
 */
public class RegexValidator implements IValidator {
    
    private Perl5Util regexUtil = new Perl5Util();
    
    private String partial;
    private String complete;
    
    private String help;
    
    /**
     * Constructor RegexValidator.  Constructs a RegexValidator, a validator that
     * uses regular expressions rather than procedural code to specify what
     * is legal in an uncompleted or a completed field.
     * 
     * @param partial A String containing a Perl 5 regular expression describing 
     *   what is permissible in a partially complete field.
     * @param complete A String containing a Perl 5 regular expression describing 
     *   what is permissible in a fully completed and valid field.
     * @param help A user-friendly help message that will be sent if the user
     *   tries to enter invalid data.
     */
    public RegexValidator(String partial, String complete, String help) {
        this.partial = partial;
        this.complete = complete;
        this.help = help;
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validatePartial(java.lang.String)
     */
    public boolean validatePartial(String value) {
        return regexUtil.match(partial, value);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#validateComplete(java.lang.String)
     */
    public boolean validateComplete(String value) {
        return regexUtil.match(complete, value);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.validator.api.IValidator#getHelp()
     */
    public String getHelp() {
        return help;
    }

}
