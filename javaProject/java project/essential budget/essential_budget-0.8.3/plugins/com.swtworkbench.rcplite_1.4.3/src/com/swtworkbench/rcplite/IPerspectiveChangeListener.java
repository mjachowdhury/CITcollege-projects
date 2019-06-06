/*
 * This file is part of com.swtworkbench.rcplite.
 *
 * com.swtworkbench.rcplite is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.rcplite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.rcplite; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.rcplite;

/**
 * Class IPerspectiveChangeListener.  An interface for objects that need to
 * listen to perspective change events.
 * 
 * @author daveo
 */
public interface IPerspectiveChangeListener {
    /**
     * Method perspectiveChange.  Indicates that the active perspective wants
     * to change.  Returns true if this is permitted, false otherwise.
     * 
     * @param current The name of the current perspective
     * @param previous The name of the previous perspective
     * @return true if this is permitted, false otherwise.
     */
    public boolean perspectiveChange(String current, String previous);
}
