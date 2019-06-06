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
package com.swtworkbench.ed.aware.marshaller;

import java.util.HashMap;

import com.swtworkbench.ed.aware.marshallers.Boolean2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Character2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Double2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Float2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Int2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Long2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.Object2StringMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2BooleanMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2CharacterMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2DoubleMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2FloatMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2IntMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2LongMarshaller;
import com.swtworkbench.ed.aware.marshallers.NullMarshaller;
import com.swtworkbench.ed.aware.marshallers.String2ObjectMarshaller;


/**
 * Class DataMarshaller.  Assists in converting from Strings to arbitrary types.
 * 
 * @author daveo
 */
public final class DataMarshallerRegistry {
    
    static {
        dataParsers = new HashMap();

        registerMarshaller(String.class, Integer.TYPE, new Int2StringMarshaller());
        registerMarshaller(String.class, Long.TYPE, new Long2StringMarshaller());
        registerMarshaller(String.class, Boolean.TYPE, new Boolean2StringMarshaller());
        registerMarshaller(String.class, Character.TYPE, new Character2StringMarshaller());
        registerMarshaller(String.class, Double.TYPE, new Double2StringMarshaller());
        registerMarshaller(String.class, Float.TYPE, new Float2StringMarshaller());
        
        registerMarshaller(Integer.TYPE, String.class, new String2IntMarshaller());
        registerMarshaller(Long.TYPE, String.class, new String2LongMarshaller());
        registerMarshaller(Boolean.TYPE, String.class, new String2BooleanMarshaller());
        registerMarshaller(Character.TYPE, String.class, new String2CharacterMarshaller());
        registerMarshaller(Double.TYPE, String.class, new String2DoubleMarshaller());
        registerMarshaller(Float.TYPE, String.class, new String2FloatMarshaller());
        
        // The polymorphic marshaller
        registerMarshaller(String.class, Object.class, new Object2StringMarshaller());
        registerMarshaller(Object.class, String.class, new String2ObjectMarshaller());
    }

    private static HashMap dataParsers;
    
    /**
     * Method getTargetMap.  Get the map of data parsers that can convert to the
     * specified target class.  Create the map if it does not yet exist.
     * 
     * @param targetClass The class to which to convert.
     * @return The HashMap of data parsers that can convert to targetClass
     */
    private static HashMap getTargetMap(Class targetClass) {
        HashMap result = (HashMap)dataParsers.get(targetClass);
        if (result == null) {
            result = new HashMap();
            dataParsers.put(targetClass, result);
        }
        return result;
    }
    
    /**
     * Method registerMarshaller.  Register a class that can convert from a 
     * String to Java type represented by Class klass.
     * 
     * @param klass The Class object representing the type that will be returned
     * @param dataParser The object that can convert a String to that type
     */
    public static void registerMarshaller(Class targetClass, Class sourceClass, IDataMarshaller dataParser) {
        HashMap targetMap = getTargetMap(targetClass);
        targetMap.put(sourceClass, dataParser);
    }
    
    private static IDataMarshaller nullMarshaller = new NullMarshaller();
    
    /**
     * Method getMarshaller.  Return a marshaller for a given type.
     * 
     * @param klass  The Class for which to return a marshaller.
     * @return The IDataMarshaller or null if there is no marshaller registered for klass.
     */
    public static IDataMarshaller getMarshaller(Class targetClass, Class sourceClass) {
        if (targetClass.equals(sourceClass))
            return nullMarshaller;
        
        HashMap targetMap = getTargetMap(targetClass);
        return (IDataMarshaller) targetMap.get(sourceClass);
    }
    
}


