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
package com.swtworkbench.ed.controls.mrotable.test;

import com.swtworkbench.ed.aware.validator.IValidator;
import com.swtworkbench.ed.aware.validators.generic.SimpleEditMaskValidator;

/**
 * Class Todo. A simple Todo class.
 * 
 * @author djo
 */
public class Todo {
    
	/**
	 * @param priority
	 * @param description
	 * @param proposed
	 * @param actual
	 */
	public Todo(int priority, String description, float proposed, float actual) {
		this.priority = priority;
		this.description = description;
		this.proposed = proposed;
		this.actual = actual;
	}

    private int priority;
    private String description;
    private float proposed;
    private float actual;
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
        return priority + " " + description + " " + proposed + " " + actual;
	}

	/**
	 * @return Returns the actual.
	 */
	public float getActual() {
		return actual;
	}
	/**
	 * @param actual The actual to set.
	 */
	public void setActual(float actual) {
		this.actual = actual;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    public IValidator getDescriptionValidator() {
        return new SimpleEditMaskValidator(String.class, "a{1,20}");
    }
	/**
	 * @return Returns the priority.
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority The priority to set.
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return Returns the proposed.
	 */
	public float getProposed() {
		return proposed;
	}
	/**
	 * @param proposed The proposed to set.
	 */
	public void setProposed(float proposed) {
		this.proposed = proposed;
	}
}
