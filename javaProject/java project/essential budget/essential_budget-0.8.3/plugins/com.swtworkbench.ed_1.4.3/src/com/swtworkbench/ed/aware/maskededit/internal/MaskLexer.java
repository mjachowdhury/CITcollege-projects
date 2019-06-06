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

import org.eclipse.swt.graphics.Point;

import com.swtworkbench.ed.aware.maskededit.MaskFormatException;

/**
 * Class MaskLexer.    An edit mask token represents a single logical "position"
 * within an edit mask.  It is always a single character in the edit mask
 * unless it has a quantifier following it.  In that case, it is a single
 * character plus the quantifier.<p>
 * 
 * Rules for translating a mask to a regex for each character position:<p>
 * 
 * - Some characters or character sequences (tokens) are reserved "words"<br>
 * - Reserved words translate into Perl-style regexes<br>
 * - Reserved words:<p>
 * 
 * # - shorthand for /^[0-9]$/<br>
 * A - shorthand for /^[A-Z]$/<br>
 * a - shorthand for /^[A-Za-Z]$/<br>
 * n = shorthand for /^[A-Za-z0-9]$/<br>
 * {min,max} A quantifier saying the minimum/maximum number of the previous allowed<p>
 * 
 * Everything else is a literal, which is translated in to the regex (where
 * the literal here is denoted by [L]): /^[L]$/<p>
 * 
 * Reserved words can be turned into literals by preceeding them with a 
 * backslash.  Use two backslashes to denote a literal backslash.<p>
 * 
 * Sample edit masks:<p>
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
 * @author djo
 */
public class MaskLexer {
    
    private String input;
    
    private int position=0;
    
    private String[] reservedWord = {"#", "A", "a", "n"};
    private String[] reservedRegex = {"/^[0-9]$/", "/^[A-Z]$/", "/^[A-Za-z]$/", "/^[A-Za-z0-9]$/"};
    
    private String[] whiteSpace = {" ", "\t", "\r", "\n", "\f"};
    //private String[] delimeter = {"-", ","};
    private String[] digit = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    public MaskLexer(String mask) {
        input=mask;
    }
    
    private int offset(String nextChar, String[] charList) {
        for (int i = 0; i < charList.length; i++) {
            if (charList[i].equals(nextChar))
                return i;
        }
        return -1;
    }
    
    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
        return position < input.length();
    }
    
    /**
     * Method next.  Return the next token.
     * @return
     * @throws MaskFormatException
     */
    public MaskToken next() throws MaskFormatException {
        if (!hasNext())
            return null;
        
        boolean forceLiteral = false;
        
        String nextChar = getNextChar();
        ++position;
        if (nextChar.equals("\\")) {
            forceLiteral = true;
            nextChar = getNextChar();
            ++position;
        }

        Point quantifier = parseQuantifier();
        int reservedOffset = offset(nextChar, reservedWord);
        
        MaskToken result;
        if (quantifier != null) {
            if (reservedOffset > -1 && !forceLiteral) {
                result = new MaskToken(reservedRegex[reservedOffset], quantifier.x, quantifier.y);
            } else {
                result = new MaskToken(makeRegex(nextChar), nextChar, quantifier.x, quantifier.y);
            }
        } else {
            if (reservedOffset > -1 && !forceLiteral) {
                result = new MaskToken(reservedRegex[reservedOffset]);
            } else {
                result = new MaskToken(makeRegex(nextChar), nextChar);
            }
        }
        
        return result;
    }
    
    private String makeRegex(String charString) {
        StringBuffer regex = new StringBuffer("/^[");
        regex.append(charString);
        regex.append("]$/");
        return regex.toString();
    }
    
    private Point parseQuantifier() throws MaskFormatException {
        if (!hasNext())
            return null;
        
        String nextChar = getNextChar();
        if (nextChar.equals("{")) {
            ++position;
            ignoreWhitespace();
            int lowerBound=parseInteger();
            ignoreWhitespace();
            nextChar = getNextChar();
            if (nextChar.equals(","))
                ++position;
            else
                throw new MaskFormatException(position + ": ',' expected");
            ignoreWhitespace();
            int upperBound=parseInteger();
            ignoreWhitespace();
            nextChar = getNextChar();
            if (nextChar.equals("}"))
                ++position;
            else
                throw new MaskFormatException(position + ": '}' expected");
            return new Point(lowerBound, upperBound);
        } else 
            return null;
    }
    
    private int parseInteger() throws MaskFormatException {
        int firstPosition = position;
        String nextChar = getNextChar();
        while (offset(nextChar, digit) >= 0) {
            ++position;
            nextChar = getNextChar();
        }
        if (position == firstPosition) 
            throw new MaskFormatException(position + ": Integer expected");
        String theInt = input.substring(firstPosition, position);
        return Integer.parseInt(theInt);
    }
    
    private void ignoreWhitespace() {
        String nextChar = getNextChar();
        while (offset(nextChar, whiteSpace) >= 0) {
            ++position;
            nextChar = getNextChar();
        }
    }
    
    private String getNextChar() {
        if (position >= input.length())
            return "";
        return input.substring(position, position+1);
    }
    
}
