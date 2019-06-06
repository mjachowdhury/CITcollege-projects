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
package com.swtworkbench.ed.aware.marshallers;

import com.swtworkbench.ed.aware.marshaller.IDataMarshaller;
import com.swtworkbench.ed.aware.marshaller.MarshallException;

public class Character2StringMarshaller implements IDataMarshaller {

    /* (non-Javadoc)
     * @see net.sf.sweet_swt.xswt.dataparser.IDataParser#parse(java.lang.Object)
     */
    public Object convert(Object source) throws MarshallException {
        Character value = (Character) source;
        return value.toString();
    }
}
