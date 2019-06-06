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

import java.util.LinkedList;

import com.swtworkbench.ed.aware.maskededit.MaskFormatException;
import com.swtworkbench.ed.aware.maskededit.MaskMatchException;

/**
 * Class MaskMachine.  MaskMachine keeps an ArrayList of MaskToken objects
 * corresponding to the sequence of characters that are allowed.  It also 
 * keeps track of the current data entry position within that list and the 
 * substring entered/validated so far.<p>
 * 
 * It can tell us on demand:<p>
 * 
 * - The current string (including mask-prompt characters)<br>
 * - The current completed string (if the only remaining characters are optional)<br>
 * - The current selection position within that string<br>
 * - If the current input is a valid completed string<p>
 * 
 * Masked editing is implemented as follows:<p>
 * 
 * - selection events asyncExec() runnables that reset the selection to the proper place<br>
 * - VerifyEvent always e.doit=false, but passes the keystroke to the mask edit handler<br>
 * - Once the mask edit handler processes the keystroke<p>
 * 
 * Keystroke handling works as follows:<p>
 * 
 * - If the keystroke was a backspace, the last character is deleted, the position is updated, 
 *    and the controls text and selection position updated (via asyncExec())<p>
 * - If the keystroke was a character:<p>
 * <pre>
 *    loop
 *      If the character does not match the next expected MaskToken, has that token matched at 
 *      least its minimum number of tokens?
 *          Yes: Move to the next expected MaskToken and continue;
 *          No: Indicate an invalid character and break
 *      If the character matches the next expected MaskToken:
 *          accept it and append it to the current valid substring
 *          If the current MaskToken is full, move current token pointer to next MaskToken
 *      else
 *          Indicate an invalid character and break
 *    endloop
 * </pre>
 * 
 * @author djo
 */
public class MaskMachine {
    
    private TokenList tokenList;
    private int entryToken=0;
    private String mask;
    
    private char promptChar = '_';
    
    /**
     * Constructor MaskMachine.  Construct an edit mask state machine.
     * Defaults to "_" as the prompt character.
     * 
     * @param editMask The edit mask to interpret
     * @throws MaskFormatException If the mask contains a syntax error
     */
    public MaskMachine(String editMask) throws MaskFormatException {
        parseEditMask(editMask);
    }
    
    /**
     * Constructor MaskMachine.  Construct an edit mask state machine.
     * 
     * @param editMask The edit mask to interpret
     * @param promptChar The character to use in the prompt to represent a character class
     * @throws MaskFormatException If the mask contains a syntax error
     */
    public MaskMachine(String editMask, char promptChar) throws MaskFormatException {
        parseEditMask(editMask);
        this.promptChar = promptChar;
    }
    
    /**
     * Method parseEditMask.  Use the MaskLexer to parse the edit mask
     * and build our LinkedList of MaskTokens that we'll use for our state
     * machine.
     * 
     * @param editMask The edit mask to parse
     * @throws MaskFormatException If a syntax error was detected during parse
     */
    private void parseEditMask(String editMask) throws MaskFormatException{
        this.mask = editMask;
        LinkedList tokens = new LinkedList();
        MaskLexer lexer = new MaskLexer(editMask);
        while (lexer.hasNext()) {
            tokens.addLast(lexer.next());
        }
        tokenList = new TokenList(tokens);
    }
    
    private char[] getPromptChars(int numChars) {
        char[] result = new char[numChars];
        for (int i=0; i < numChars; ++i) {
            result[i] = promptChar;
        }
        return result;
    }
    
    private StringBuffer getMaskedInputFromToken(MaskToken current, MaskToken lookahead) {
        StringBuffer result = new StringBuffer(current.getInput());
        // Deal with literals first
        if (current.getInput().equals("") && current.literal != null) {
            result.append(current.literal);
            return result;
        }
        // Then deal with regex input
        if (lookahead != null && lookahead.isEmpty())
            result.append(getPromptChars(current.max - result.length()));
        else if (lookahead == null && !current.isFull())
            result.append(getPromptChars(current.max - result.length()));
        return result;
    }
    
    /**
     * Method getMaskedInput.  
     * @return
     */
    public String getMaskedInput() {
        StringBuffer result = new StringBuffer();
        
        MaskToken current=null, next=null;
        
        for (tokenList.reset(); tokenList.hasNext();) {
            current = next;
            next = tokenList.next();
            if (current == null) continue;
            
            result.append(getMaskedInputFromToken(current, next));
        }
        
        current=next;
        next=null;
        result.append(getMaskedInputFromToken(current, next));
        
        return result.toString();
    }
    
    public void clear() {
        for (tokenList.reset(); tokenList.hasNext();) {
            tokenList.next().clear();
        }
        entryToken=0;
    }
    
    /**
     * Method getInput.  
     * @return The input string as entered so far minus any "mask" characters
     */
    public String getInput() {
        StringBuffer result = new StringBuffer();
        
        for (tokenList.reset(); tokenList.hasNext();) {
            MaskToken current = tokenList.next();
            result.append(current.getInput());
        }
        
        return result.toString();
    }
    
    /**
     * Method setInput.  Sets the mask machine's input
     * @param newValue The mask machine's new value
     */
    public void setInput(String newValue) throws MaskMatchException {
        clear();
        
        for (int i=0; i < newValue.length(); ++i) {
            String character = newValue.substring(i, i+1);
            if (!appendNoLookahead(character))
                throw new MaskMatchException(i, mask, newValue);
        }
    }

    /**
     * Method appendNoLookahead.  
     * @param inputChar
     * @return
     */
    private boolean appendNoLookahead(String inputChar) {
        tokenList.setPosition(entryToken);
        if (!tokenList.hasNext())
            return false;
        
        int currentToken = entryToken;
        MaskToken current = tokenList.next();
        
        while (true) {
            // See if we can append to the current token
            if (current.append(inputChar)) {
                entryToken = currentToken;
                return true;
            }
            
            // Handle the case where there are optional characters in current,
            // so the character might be for a future token.
            if (current.isValid() && tokenList.hasNext()) {
                current = tokenList.next();
                ++currentToken;
            } else
                return false;
        }
    }

    /**
     * Method isValid.  Returns if the current input is a valid, complete
     * input as required by the edit mask.
     * 
     * @return
     */
    public boolean isValid() {
        for (tokenList.reset(); tokenList.hasNext();) {
            if (!tokenList.next().isValid())
                return false;
        }
        return true;
    }
    
    /**
     * Method isFull.  
     * @return
     */
    public boolean isFull() {
        tokenList.setPosition(tokenList.size()-1);
        MaskToken last = tokenList.next();
        return last.isFull();
    }
    
    /**
     * Method append.  
     * @param inputChar
     * @return
     */
    public boolean append(String inputChar) {
        tokenList.setPosition(entryToken);
        if (!tokenList.hasNext())
            return false;
        
        int currentToken = entryToken;
        MaskToken current = tokenList.next();
        
        while (true) {
            // See if we can append to the current token
            if (current.append(inputChar)) {
                entryToken = currentToken;
                
                // Check to see if the next token(s) are literals and automatically
                // enter them if they are
                if (current.isFull() && tokenList.hasNext()) {
                    ++currentToken;
                    current = tokenList.next();
                    while (current.literal != null) {
                        current.append(current.literal);    // Will succeed by definition
                        entryToken = currentToken;
                        if (!tokenList.hasNext()) break;
                        ++currentToken;
                        current = tokenList.next();
                    }
                }
                return true;
            }
            
            // Handle the case where there are optional characters in current,
            // so the keystroke might be for a future token.
            if (current.isValid() && tokenList.hasNext()) {
                current = tokenList.next();
                ++currentToken;
            } else
                return false;
        }
    }

    /**
     * Method backspace.  Remove the last character from the input
     */
    public void backspace() {
        if (entryToken >= 0) {
            tokenList.setPosition(entryToken);
            MaskToken current = tokenList.next();
            current.backspace();
            if (current.isEmpty() && entryToken > 0)
                entryToken--;
        }
    }
    
    /**
     * Method getPosition.  Returns the number of characters entered
     * into the mask which corresponds to the selection position in 
     * the edit control.
     * 
     * @return
     */
    public int getPosition() {
        int result=0;
        for (tokenList.reset(); tokenList.hasNext();) {
            MaskToken token = tokenList.next();
            result += token.getInput().length();
        }
        return result;
    }

    /**
     * Method getHelpMessage.  
     * @return
     */
    public String getHelpMessage(String fieldName) {
        return "Field " + fieldName + ":  \"" + getInput() + "\" does not match input mask " + mask;
    }

}


