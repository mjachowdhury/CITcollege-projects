/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.swtworkbench.swtutils.fromeclipse;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.*;

public class CTabFolderEvent extends TypedEvent {
 	public Widget item;
 	public boolean doit;
 	
CTabFolderEvent(Widget w) {
	super(w);
}
}
