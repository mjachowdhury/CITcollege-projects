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

import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.validator.IValidator;
import com.swtworkbench.ed.aware.validator.ValidatorRegistry;
import com.swtworkbench.ed.aware.validators.generic.ReadOnlyValidator;

/**
 * Class Property.  Reflects on an arbitrary object and tries to discover
 * JavaBeans-style property methods for a specific property name.  It then
 * becomes a wrapper interface for that property on that object.
 * 
 * @author daveo
 */
public class Property {

    /**
     * The object to which this property applies
     */
    protected Object input;
    
    /**
     * Method getInput.  Returns the input object.
     * @return The input object
     */
    public Object getInput() {
        return input;
    }
    
    /**
     * The name of this property
     */
    protected String propertyName;
    
    /**
     * Method getPropertyName.  Returns the name of the property being edited
     * @return The property name string
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Constructor Property.  Construct a property object.
     * 
     * @param input The object on which the property should be found
     * @param propertyName The name of the property
     */
    public Property(Object input, String propertyName) {
        this.input = input;
        this.propertyName = propertyName;
        getMethods();
    }
    
    /**
     * Method getMethods.  Retrieve the data-aware (JavaBeans Plus) methods from 
     * the new input object.  We only support RW or read-only properties.  Write-only 
     * properties are not supported.
     * 
     * @throws IllegalArgumentException if the property does not exist on the object
     * in at least read-only form.
     */
    private void getMethods() {
        get = Reflect.getMethod(input, "get" + propertyName, new Class[] {});
        if (get == null)
            get = Reflect.getMethod(input, "is" + propertyName, new Class[] {});
        
        if (get != null) {
            set = Reflect.getMethod(input, "set" + propertyName, new Class[] {get.getReturnType()});
            validator = Reflect.getMethod(input, "get" + propertyName + "Validator", new Class[] {});
            addChangeListener = Reflect.getMethod(input, "add" + propertyName + "Listener", new Class[] {PropertyChangeListener.class});
            removeChangeListener = Reflect.getMethod(input, "remove" + propertyName + "Listener", new Class[] {PropertyChangeListener.class});
            validValues = Reflect.getMethod(input, "get" + propertyName + "ValidValues", new Class[] {});
            choices = Reflect.getMethod(input, "get" + propertyName + "Choices", new Class[] {});
            getObjectFactory = Reflect.getMethod(input, "get" + propertyName + "ObjectFactory", new Class[] {});
            getChangeListener = Reflect.getMethod(input, "get" + propertyName + "ChangeListener", new Class[] {});
        } else
            throw new IllegalArgumentException("Property: " + propertyName + " not supported on this object");
    }
    
    
    
    // Reflected method implementations //
    
    private Method addChangeListener;
    
    /**
     * Method addChangeListener.  If the underlying property is bound, adds a
     * change listener to the underlying property.  Otherwise, does nothing.
     * 
     * @param l
     */
    public void addChangeListener(PropertyChangeListener l) {
        if (addChangeListener == null) return;
        if (!addChangeListener.isAccessible())
            addChangeListener.setAccessible(true);
        try {
            addChangeListener.invoke(input, new Object[] {l});
        } catch (Exception e) {}
    }
    
    private Method removeChangeListener;

    /**
     * Method removeChangeListener.  If the underlying property is bound, removes
     * a change listener from the underlying property.  Otherwise, does nothing.
     * 
     * @param l
     */
    public void removeChangeListener(PropertyChangeListener l) {
        if (removeChangeListener == null) return;
        if (!removeChangeListener.isAccessible())
            removeChangeListener.setAccessible(true);
        try {
            removeChangeListener.invoke(input, new Object[] {l});
        } catch (Exception e) {}
    }
    
    // Introspected methods by type
    protected Method get = null;
    
    /**
     * Method get.  Calls the underlying property's getter and returns the result.
     * @return Object the result
     */
    public Object get() {
        if (get == null) return null;
        if (!get.isAccessible())
            get.setAccessible(true);
        Object result = null;
        try {
            result = get.invoke(input, new Object[] {});
        } catch (Exception e) {
        }
        return result;
    }
    
    protected Method set = null;
    
    /**
     * Method set.  Call the underlying property's setter.
     *   
     * @param newValue  The new value to set on the property.
     */
    public void set(Object newValue) {
        if (set == null) return;
        if (!set.isAccessible())
            set.setAccessible(true);
        try {
            set.invoke(input, new Object[] {newValue});
        } catch (Exception e) {
        }
    }
    
    protected Method validator = null;
    
    /**
     * Method validator.  Returns the validator for this property.  This method
     * first tries to retrieve a custom validator for the property.  Failing 
     * that, it looks up a validator in the validator registry based on the
     * property's type.
     * 
     * @return IValidator the validator.
     */
    public IValidator validator() {
        if (isReadOnly())
            return ReadOnlyValidator.getDefault();
            
        IValidator result = null;
        if (validator != null) {
            if (!validator.isAccessible())
                validator.setAccessible(true);
            try {
                result = (IValidator) validator.invoke(input, new Object[] {});
            } catch (Exception e) {
            }
        }
        if (result == null) {
            result = ValidatorRegistry.get(type());
        }
        return result;
    }
    
    
    private Method validValues = null;
    
    /**
     * Method validValues.  If a "values" method is present, the data type can 
     * be anything, and must match the data type stored in the Object[] by the 
     * "values" method.  The contents of this array overrides the data 
     * storage/retrieval algorithm used when there is a "choices" method alone.  
     * If a "choices" method does not exist, the choices are generated by 
     * applying the toString() method to the objects in the "values" array.<p>
     * 
     * A "values" method is required in a list box or combo box context in 
     * order to populate the set of choices.  If the toString() method cannot 
     * produce appropriate strings by itself, the "choices" method (described 
     * below) may be supplied to provide appropriate disply strings 
     * corresponding to the valid values returned by this method.<p>
     *
     * A "values" method is of the form:<p>
     * 
     * <code>Object[] get&lt;propertyName&gt;ValidValues</code>
     * 
     * @return Object[] The set of valid values for this property or null if
     * this set is undefined.
     */
    public Object[] validValues() {
        if (validValues == null) return null;
        if (!validValues.isAccessible())
            validValues.setAccessible(true);
        Object[] result = null;
        try {
            result = (Object[]) validValues.invoke(input, new Object[] {});
        } catch (Exception e) {
        }
        return result;
    }
    

    private Method choices = null;
    
    /**
     * Method choices.  Calls and returns the string choices from any 
     * "choices" method found in the JavaBean.  Returns null if no
     * "choices" method was found.<p>
     * 
     * A "choices" method is of the form:<p>
     * 
     * <code>String[] get&lt;propertyName&gt;Choices</code>
     * 
     * @return String[] The set of choice strings corresponding to the objects
     * returned by the validValues() method or null if this set is undefined.
     */
    public String[] choices() {
        if (choices == null) return null;
        if (!choices.isAccessible())
            choices.setAccessible(true);
        String[] result = null;
        try {
            result = (String[]) choices.invoke(input, new Object[] {});
        } catch (Exception e) {
        }
        return result;
    }

    private Method getObjectFactory = null;
    
    /**
     * Method getObjectFactory.  Returns the object factory associated with
     * the given property or null if there is none.
     * 
     * @return The IObjectFactory for the property or null if there is none
     */
    public IObjectFactory getObjectFactory() {
        if (getObjectFactory == null) return null;
        if (!getObjectFactory.isAccessible())
            getObjectFactory.setAccessible(true);
        IObjectFactory result = null;
        try {
            result = (IObjectFactory) getObjectFactory.invoke(input, new Object[] {});
        } catch (Exception e) {
        }
        return result;
    }
    

    private Method getChangeListener = null;

    /**
     * Method getChangeListener.  Returns the object factory associated with
     * the given property or null if there is none.
     * 
     * @return The IModelChangeListener for the property or null if there is none
     */
    public IModelChangeListener getChangeListener() {
        if (getChangeListener == null) return null;
        if (!getChangeListener.isAccessible())
            getChangeListener.setAccessible(true);
        IModelChangeListener result = null;
        try {
            result = (IModelChangeListener) getChangeListener.invoke(input, new Object[] {});
        } catch (Exception e) {
        }
        return result;
    }
    

    /**
     * Method type.  Returns this property's type.
     * 
     * @return Class the type of this property
     */
    public Class type() {
        return get.getReturnType();
    }

    /**
     * Method isValid.  Returns if this is a valid property.  ie: Were we
     * at least able to find a "getter".
     * @return true if get != null
     */
    public boolean isValid() {
        return get != null;
    }

    /**
     * Method isReadOnly.  Returns if this property is read-only.  ie: there
     * is no setter for this property.
     * @return true if setter == null; false otherwise
     */
    public boolean isReadOnly() {
        return set == null;
    }
}
