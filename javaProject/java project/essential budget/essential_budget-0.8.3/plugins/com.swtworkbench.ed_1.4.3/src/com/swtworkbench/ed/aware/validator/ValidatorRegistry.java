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
package com.swtworkbench.ed.aware.validator;

import java.util.HashMap;

import com.swtworkbench.ed.aware.validators.DoubleValidator;
import com.swtworkbench.ed.aware.validators.FloatValidator;
import com.swtworkbench.ed.aware.validators.IntegerValidator;
import com.swtworkbench.ed.aware.validators.LongValidator;
import com.swtworkbench.ed.aware.validators.generic.RegexValidator;


/**
 * Class Validator.  Assists in validating strings as compatible with arbitrary types.
 * 
 * @author daveo
 */
public final class ValidatorRegistry {
    
    static {
        dataParsers = new HashMap();
        
        registerValidator(Boolean.TYPE, 
            new RegexValidator("/^$|^t$|^tr$|^tru$|^true$|^f$|^fa$|^fal$|^fals$|^false$/", 
                "/^true$|^false$/", 
                "Please enter either 'true' or 'false'"));
        registerValidator(Character.TYPE, 
            new RegexValidator("/^$|^.$/", "/./", "Please enter a single character"));

        registerValidator(Double.TYPE, new DoubleValidator());
        registerValidator(Float.TYPE, new FloatValidator());
        registerValidator(Integer.TYPE, new IntegerValidator());
        registerValidator(Long.TYPE, new LongValidator());
    }

    private static HashMap dataParsers;
    
    /**
     * Method registerValidator.  Register a class that can validate values of
     * the Java type represented by Class klass.
     * 
     * @param klass The Class object representing the type that will be returned
     * @param dataParser The object that can convert a String to that type
     */
    public static void registerValidator(Class klass, IValidator validator) {
        dataParsers.put(klass, validator);
    }
    
    /**
     * Method getValidator.  Return a validator for a given type.
     * 
     * @param klass  The Class for which to return a validator.
     * @return The IValidator or an instance of the ReadOnlyValidator if there is no 
     * validator registered for klass.
     */
    public static IValidator get(Class klass) {
        return (IValidator) dataParsers.get(klass);
    }
    
}


