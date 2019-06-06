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
import java.util.ListIterator;

/**
 * Class TokenList.  
 * 
 * @author daveo
 */
public class TokenList {

    private LinkedList tokens;
    private ListIterator tokenIterator;
    private int position;

    public TokenList(LinkedList tokenList) {
        this.tokens = tokenList;
        reset();
    }
    
    public int size() {
        return tokens.size();
    }
    
    public boolean hasNext() {
        return tokenIterator.hasNext();
    }
    
    public MaskToken next() {
        ++position;
        return (MaskToken) tokenIterator.next();
    }
    
    public boolean hasPrevios() {
        return tokenIterator.hasPrevious();
    }
    
    public MaskToken previous() {
        --position;
        return (MaskToken) tokenIterator.previous();
    }
    
    public void reset() {
        tokenIterator = tokens.listIterator();
        position=0;
    }
    
    public void setPosition(int position) {
        while (this.position < position)
            next();
        while (this.position > position)
            previous();
    }
    
    public int position() {
        return position;
    }
    
    public void skipForward(int amount) {
        while (amount > 0) {
            next();
            --amount;
        }
    }
    
    public void skipBackward(int amount) {
        while (amount > 0) {
            previous();
            --amount;
        }
    }
}


