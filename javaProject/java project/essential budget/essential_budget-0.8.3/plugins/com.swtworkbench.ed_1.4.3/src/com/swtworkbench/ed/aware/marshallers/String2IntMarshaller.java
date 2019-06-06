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

/**
 * Class IntDataParser.  
 * 
 * @author daveo
 */
public class String2IntMarshaller implements IDataMarshaller {

    /* (non-Javadoc)
     * @see net.sf.sweet_swt.xswt.dataparser.IDataParser#parse(java.lang.String)
     */
    public Object convert(Object source) throws MarshallException {
        Integer result = null;
        
        // Assume it's a real int first...
        try {
            int intermediate = Integer.parseInt((String)source);
            result = new Integer(intermediate);
        } catch (Exception e) {
            throw new MarshallException(e);
        }
        return result;
        // We couldn't parse it so we'll try to interpret it as an SWT constant
//        int intermediate = StyleMarshaller.parse(source);
//        return new Integer(intermediate);
    }
    
}
