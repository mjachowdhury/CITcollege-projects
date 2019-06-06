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
package com.swtworkbench.ed.reflect;

import java.lang.reflect.Method;

import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class Reflect.  
 * 
 * @author daveo
 */
public class Reflect {
    /**
     * Method getMethod.  Returns the Method object for a specific method on the
     * underlying control or null if it cannot be found for any reason.  
     * 
     * @param name The method's name (as a String)
     * @param args The method's argument list types (as a Class[])
     * @return Method the found Method or null if not found.
     */
    public static Method getMethod(Object subject, String name, Class[] args) {
        Class controlClass = subject.getClass();
        Method result = null;
        try {
            result = controlClass.getMethod(name, args);
        } catch (Exception e) {
        }
        return result;
    }
    
    /**
     * Method invokel. Invoke some method on the underlying control with the
     * specified arguments.  Returns null if any type of failure occurred and
     * logs any failure.
     *   
     * @param method The Method to invoke
     * @param args The arguments to pass to the method (Object[])
     * @return The result Object
     */
    public static Object invokel(Object subject, Method method, Object[] args) {
        Object result = null;
        try {
            result = method.invoke(subject, args);
        } catch (Exception e) {
            if (method != null)
                Logger.log().error(e, "Unable to invoke " + method.getName());
            else
                Logger.log().error(e, "Unable to invoke method: method object is null");
        }
        return result;
    }
    
    /**
     * Method invokei.  Invoke some named method on some object.  Ignore any
     * errors that occur and return null.
     * 
     * @param receiver The object receiving the message
     * @param method The Method object
     * @param args The arguments
     * @return The result object or null if there was no result or there was an error.
     */
    public static Object invokei(Object subject, Method method, Object[] args) {
        Object result = null;
        try {
            result = method.invoke(subject, args);
        } catch (Exception e) {
        }
        return result;
    }
    
    private static Class[] getParamTypes(Object[] args) {
        Class[] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getClass();
        }
        return paramTypes;
    }
    
    /**
     * Method invokei.  Invoke some named method on some object.  Ignore any
     * errors that occur.
     * 
     * @param receiver The object receiving the message
     * @param method The method or message name
     * @param args The arguments
     * @return The result object or null if there was no result or there was an error.
     */
    public static Object invokei(Object receiver, String method, Object[] args) {
        Object result = null;
        try {
            Method methodCaller = receiver.getClass().getMethod(method, getParamTypes(args));
            result = methodCaller.invoke(receiver, args);
        } catch (Exception e) {
        }
        return result;
    }
    
    /**
     * Method invokel.  Invoke some named method on some object.  Log any
     * errors that occur.
     * 
     * @param receiver The object receiving the message
     * @param method The method or message name
     * @param args The arguments
     * @return The result object or null if there was no result or there was an error.
     */
    public static Object invokel(Object receiver, String method, Object[] args) {
        Object result = null;
        try {
            Method methodCaller = receiver.getClass().getMethod(method, getParamTypes(args));
            result = methodCaller.invoke(receiver, args);
        } catch (Exception e) {
            Logger.log().error(e, "Unable to invoke: " + method + " on " + receiver.getClass().getName());
        }
        return result;
    }

}
