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
package com.swtworkbench.essentialbudget.views.budgeteditor;

import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.swtworkbench.essentialbudget.model.BudgetCategory;
import com.swtworkbench.essentialbudget.model.BudgetItem;

/**
 * Class BudgetCategoryRow.  This class implements IBudgetCategoryRow indirectly
 * via dynamic proxies.  In order to get an IBudgetCategoryRow, call the
 * static create() method.
 * 
 * @author daveo
 */
public class BudgetCategoryRow implements InvocationHandler {
    /**
     * Method create.  Construct an IBudgetCategoryRow.
     * @param category The BudgetCategory to wrap.
     * @return the created IBudgetCategoryRow instance.
     */
    public static IBudgetCategoryRow create(BudgetCategory category) {
        IBudgetCategoryRow result =
            (IBudgetCategoryRow) Proxy.newProxyInstance(
                category.getClass().getClassLoader(),
                new Class[] { IBudgetCategoryRow.class },
                new BudgetCategoryRow(category));
        return result;
    }
    
    /**
     * Constructor BudgetCategoryRow.  Do not call this constructor directly.
     * Instead call the static create() method.
     * 
     * @param category
     */
    public BudgetCategoryRow(BudgetCategory category) {
        this.category = category;
    }
    
    private final BudgetCategory category;
    
    /**
     * Method monthFromMethodName.  Retrieves the month number from the method
     * name.
     * 
     * @param methodName The method name string
     * @return The month number
     */
    private int monthFromName(String methodName) {
        String result;
        if (methodName.substring(0, 2).equals("rem")) {
            result = methodName.substring(6, 7);
        } else {
            result = methodName.substring(0, 2);
        }
        return Integer.parseInt(result);
    }
    
    /**
     * Method methodFromName.  Get the method from the method name
     * @param methodName
     * @return
     */
    private String methodFromName(String methodName) {
        return methodName.substring(0, 2);
    }
    
    /**
     * Method fieldFromName.  Get the field to operate on from the method name
     * @param methodName
     * @return
     */
    private String fieldFromName(String methodName) {
        String result;
        if (methodName.substring(0, 2).equals("rem")) {
            result = methodName.substring(8, methodName.length());
        } else {
            result = methodName.substring(5, methodName.length());
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object subject, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        int month = monthFromName(methodName);
        
        if (methodName.equals("getName")) {
            return getName();
        } else if (methodName.equals("getBudgetCategory")) {
            return getBudgetCategory();
        } else if (methodFromName(methodName).equals("get")) {
            return get(month, fieldFromName(methodName));
        } else if (methodFromName(methodName).equals("set")) {
            set(month, fieldFromName(methodName), args);
        } else if (methodFromName(methodName).equals("add")) {
            add(month, fieldFromName(methodName), args);
        } else if (methodFromName(methodName).equals("rem")) {
            remove(month, fieldFromName(methodName), args);
        }
        return null;
    }
    
    // ---- Actual method implementations here ----
    
    private BudgetItem getItem(int month) {
        return category.getBudget()[month];
    }
    
    /**
     * Method add.  Add a property change listener
     * @param i
     * @param string
     * @param args
     */
    private void add(int month, String field, Object[] args) {
        BudgetItem item = getItem(month);
        if (field.equals("Budget")) {
            item.addBudgetListener((PropertyChangeListener)args[0]);
        } else if (field.equals("Actual")) {
            item.addActualListener((PropertyChangeListener)args[0]);
        } else if (field.equals("Diff")) {
            item.addDifferenceListener((PropertyChangeListener)args[0]);
        }
    }

    /**
     * Method remove.  Remove a property change listener
     * @param i
     * @param string
     * @param args
     */
    private void remove(int month, String field, Object[] args) {
        BudgetItem item = getItem(month);
        if (field.equals("Budget")) {
            item.removeBudgetListener((PropertyChangeListener)args[0]);
        } else if (field.equals("Actual")) {
            item.removeActualListener((PropertyChangeListener)args[0]);
        } else if (field.equals("Diff")) {
            item.removeDifferenceListener((PropertyChangeListener)args[0]);
        } else {
            throw new IllegalArgumentException("Unrecognized field: " + field);
        }
    }
    
    /**
     * Method get.  
     * @param i
     * @param string
     * @return
     */
    private Object get(int month, String field) {
        BudgetItem item = getItem(month);
        if (field.equals("Budget")) {
            return new Double(item.getBudget());
        } else if (field.equals("Actual")) {
            return new Double(item.getActual());
        } else if (field.equals("Diff")) {
            return new Double(item.getDifference());
        } else {
            throw new IllegalArgumentException("Unrecognized field: " + field);
        }
    }

    /**
     * Method set.  
     * @param i
     * @param string
     * @param args
     */
    private void set(int month, String field, Object[] args) {
        BudgetItem item = getItem(month);
        double value = ((Double)args[0]).doubleValue();
        if (field.equals("Budget")) {
            item.setBudget(value);
        } else if (field.equals("Actual")) {
            item.setActual(value);
        } else {
            throw new IllegalArgumentException("Unrecognized field to set: " + field);
        }
    }

    protected String getName() {
        return category.getName();
    }
    
    protected BudgetCategory getBudgetCategory() {
        return category;
    }

}


