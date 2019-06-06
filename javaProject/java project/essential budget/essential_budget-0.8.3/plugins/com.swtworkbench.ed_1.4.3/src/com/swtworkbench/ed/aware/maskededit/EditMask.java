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
package com.swtworkbench.ed.aware.maskededit;

import com.swtworkbench.ed.aware.maskededit.internal.MaskMachine;

/**
 * Class EditMask.  Represents an edit mask.  Edit masks are strings that:<p>
 * <ul>
 * <li>Represent sets of valid values much like regular expressions do
 * <li>Can represent a smaller set of values than regular expressions
 * <li>But enable the edit control to visually assist the user while entering data
 * </ul>
 * 
 * Edit masks are broken down into tokens, each of which represents zero
 * or more characters of a certain type.  The sequence of tokens represents
 * the sequence of characters that are allowed in an edit mask.<p>
 *  
 * A single edit mask token is represented by a single character in the edit 
 * mask unless it has a quantifier following it.  In that case, the token is 
 * the single character plus the quantifier.<p>
 * 
 * The following are the rules for translating an edit mask token into a 
 * regular expression matching the set of values in the position of the
 * edit mask token are as follows:<p>
 * 
 * - Some characters (tokens) are reserved "words"<br>
 * - Reserved words translate into Perl-style regexes<br>
 * - The following are the reserved words:<p>
 * 
 * # - shorthand for /^[0-9]$/ - Matches digits<br>
 * A - shorthand for /^[A-Z]$/ - Matches uppercase letters<br>
 * a - shorthand for /^[A-Za-Z]$/ - Matches upper or lower case letters<br>
 * n = shorthand for /^[A-Za-z0-9]$/ - Matches alphanumeric characters<p>
 * 
 * Everything else is a literal.  Literals match exactly their own character.<p>
 * 
 * Reserved words can be turned into literals by preceeding them with a 
 * backslash.  Use two backslashes to denote a literal backslash.<p>
 * 
 * Any reserved word or literal can have a quantifier following it indicating
 * how many of the reserved word or literal are allowed.  These have the 
 * following form:<p>
 * 
 * {min,max} A quantifier saying the minimum/maximum number of the previous allowed<p>
 * 
 * Here are some sample edit masks:<p>
 *
 * ###-##-####<p>
 * 
 * (###) ###-####<p>
 * 
 * #{1,2}/#{1,2}/####<p>
 * 
 * \####<p>
 * 
 * The last one matches a literal pound sign followed by exactly 3 digits<p>
 * 
 * @author daveo
 */
public class EditMask {
    
    private final String mask;
    private final MaskMachine maskMachine;
    
    /**
     * Constructor EditMask.  Construct an EditMask object, passing the
     * edit mask string.  The prompt char defaults to "_".
     * 
     * @param mask
     * 
     * @throws MaskFormatException if there is a syntax error in the mask
     */
    public EditMask(String mask) throws MaskFormatException {
        this.mask = mask;
        this.maskMachine = new MaskMachine(mask);
    }
    
    /**
     * Constructor EditMask.  Construct an EditMask object, passing the
     * edit mask string and the character used in the edit mask prompt
     * to denote a wildcard character position.
     * 
     * @param mask
     * @param promptChar
     * 
     * @throws MaskFormatException if there is a syntax error in the mask
     */
    public EditMask(String mask, char promptChar) throws MaskFormatException {
        this.mask = mask;
        this.maskMachine = new MaskMachine(mask, promptChar);
    }
    
    /**
     * @return Returns the edit mask string.
     */
    public String getMask() {
        return mask;
    }

    /**(Non-API)
     * Method getMaskStateMachine.  
     * 
     * @return the state machine used to evaluate and process keystrokes
     * during edit mask-managed entry.
     */
    public MaskMachine getMaskStateMachine() {
        return maskMachine;
    }
}
