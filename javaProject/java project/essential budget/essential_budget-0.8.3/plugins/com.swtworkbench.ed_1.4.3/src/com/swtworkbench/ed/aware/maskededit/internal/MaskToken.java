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
package com.swtworkbench.ed.aware.maskededit.internal;

import org.apache.oro.text.perl.Perl5Util;

/**
 * Class MaskToken.  Represents a single token in an edit mask plus the
 * regular expression that the token represents.  Also validates input
 * against itself and tracks what has been inputted against itself.
 *  
 * @author djo
 */
public class MaskToken {
    /*
     * If the token denotes a single literal character, this will be a string of
     * length 1 containing that literal character.  If the token represents
     * a character class, this field will be null.
     */
    public final String literal;
    
    /*
     * The regular expression that this token represents
     */
    public final String regex;
    
    /*
     * The minimum number of this character class allowed
     */
    public final int min;
    
    /*
     * The maximum number of this character class allowed
     */
    public final int max;

    /*
     * The part of the input that matches this MaskToken
     */
    private String input = "";
    
    /**
     * Constructor MaskToken.  A constructor for a MaskToken representing
     * a single character of some character class.
     * 
     * @param regex
     */
    public MaskToken(String regex) {
        this.regex=regex;
        this.literal=null;
        this.min=1;
        this.max=1;
    }
    
    /**
     * Constructor MaskToken.  A constructor for a MaskToken representing a
     * literal value.
     * 
     * @param regex
     * @param literal
     */
    public MaskToken(String regex, String literal) {
        this.regex=regex;
        this.literal=literal;
        this.min=1;
        this.max=1;
    }
    
    /**
     * Constructor MaskToken.  A constructor for a MaskToken representing a
     * character class with a quantifier.
     * 
     * @param regex
     * @param min
     * @param max
     */
    public MaskToken(String regex, int min, int max) {
        this.regex=regex;
        this.literal=null;
        this.min=min;
        this.max=max;
    }
    
    /**
     * Constructor MaskToken.  A constructor for a MaskToken representing a
     * literal value with a quantifier.
     * 
     * @param regex
     * @param literal
     * @param min
     * @param max
     */
    public MaskToken(String regex, String literal, int min, int max) {
        this.regex=regex;
        this.literal=literal;
        this.min=min;
        this.max=max;
    }

    /**
     * @return Returns the part of the input that matches this MaskToken
     */
    public String getInput() {
        return input;
    }

    /**
     * Method full.  Returns if this token is full (cannot accept any more
     * characters.
     * @return
     */
    public boolean isFull() {
        return input.length() >= max;
    }
    
    /**
     * Method isEmpty.  Returns if this token has not accepted any characters
     * @return
     */
    public boolean isEmpty() {
        return input.length() == 0;
    }
    
    /**
     * Method isValid.  Returns if this input token has accepted at least
     * min characters and no more than max characters.
     * 
     * @return
     */
    public boolean isValid() {
        return input.length() >= min && input.length() <= max;
    }
    
    private Perl5Util regexUtil = new Perl5Util();

    /**
     * Method append.  Try to append a character to this token.  Returns
     * if the append succeeded.
     * 
     * @param inputChar
     * @return
     */
    public boolean append(String inputChar) {
        if (isFull()) return false;
        
        if (regexUtil.match(regex, inputChar)) {
            input += inputChar;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method backspace.  Remove the last character from the input
     */
    public void backspace() {
        if (isEmpty()) return;
        input = input.substring(0, input.length()-1);
    }

    /**
     * Method clear.  Clear any input in this token.
     */
    public void clear() {
        input = "";
    }
}

