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

import com.swtworkbench.ed.aware.marshaller.DataMarshallerRegistry;
import com.swtworkbench.ed.aware.marshaller.IDataMarshaller;
import com.swtworkbench.ed.aware.marshaller.MarshallException;

/**
 * Class Object2StringMarshaller.  
 * 
 * @author daveo
 */
public class Object2StringMarshaller implements IDataMarshaller {

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.marshaller.IDataMarshaller#convert(java.lang.Object)
     */
    public Object convert(Object source) throws MarshallException {
        if (source == null) return "";
        IDataMarshaller marshaller = DataMarshallerRegistry.getMarshaller(String.class, source.getClass());
        if (marshaller != null) return marshaller.convert(source);
        return null;
    }

}
