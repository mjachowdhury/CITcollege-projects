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
package com.swtworkbench.ed.controls.mrotable;

import org.eclipse.swt.widgets.Composite;

/**
 * Class IPartControlFactory. An interface for objects that define a 
 * public Composite createPartControl(Composite parent) method.
 * 
 * <p>In order for objects to be bound to controls, they must also define
 * an ID that will be accessed using getData(String) on the SWT object.
 * The key for the ID is "binding" and the value must be a String containing
 * the name of the property to which the object should be bound.</p>
 * 
 * @author djo
 */
public interface IPartControlFactory {
	 public Composite createPartControl(Composite parent);
}
