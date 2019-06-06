/*
 * This file is part of com.swtworkbench.essentialbudget.
 *
 * com.swtworkbench.essentialbudget is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.essentialbudget is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.essentialbudget; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.essentialbudget.model.typehandling;

import java.text.NumberFormat;

import com.swtworkbench.ed.aware.marshaller.IDataMarshaller;
import com.swtworkbench.ed.aware.marshaller.MarshallException;

/**
 * Class Currency2StringMarshaller.  We'll treat doubles as currency in this
 * application.  In practice, you could pick any type to treat as currency
 * or even create your own type.  All you have to do is define marshallers
 * and a validator for your type.
 * 
 * @author daveo
 */
public class Currency2StringMarshaller implements IDataMarshaller {

    /* (non-Javadoc)
     * @see net.sf.sweet_swt.xswt.dataparser.IDataParser#parse(java.lang.String)
     */
    public Object convert(Object source) throws MarshallException {
        Double value = (Double) source;
        NumberFormat f = NumberFormat.getCurrencyInstance();
        return f.format(value);
    }
}
