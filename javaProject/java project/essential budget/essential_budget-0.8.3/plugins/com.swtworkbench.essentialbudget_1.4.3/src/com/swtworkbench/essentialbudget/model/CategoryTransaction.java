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
package com.swtworkbench.essentialbudget.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Class CategoryTransaction.  
 * 
 * @author daveo
 */
public class CategoryTransaction implements Serializable {
    private static final long serialVersionUID = 0L;
    
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructor CategoryTransaction.  
     * 
     * @param transaction
     */
    public CategoryTransaction(AccountTransaction transaction) {
        this.parent = transaction;
    }
    
    // ==== Property Parent ====

    private final AccountTransaction parent;

    /**
     * Method getParent.  Returns the current value of the Parent
     * property.
     * 
     * @return AccountTransaction 
     */
    public AccountTransaction getParent() {
        return parent;
    }
    
    // ==== Property AssignedTo ====

    private Object assignedTo;

    /**
     * Method getAssignedTo.  Returns the current value of the AssignedTo
     * property.
     * 
     * @return Object 
     */
    public Object getAssignedTo() {
        return assignedTo;
    }

    /**
     * Method setAssignedTo.  Sets the value of the AssignedTo property.
     */
    public void setAssignedTo(Object newValue) {
        Object oldValue = assignedTo;
        this.assignedTo = newValue;
        assignedToChangeSupport.firePropertyChange(
            "prop",
            oldValue,
            newValue);
    }

    private PropertyChangeSupport assignedToChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addAssignedToListener.  Add a property change listener to the
     * AssignedTo property.
     */
    public void addAssignedToListener(PropertyChangeListener l) {
        assignedToChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addAssignedToListener.  Remove a property change listener from the
     * AssignedTo property.
     */
    public void removeAssignedToListener(PropertyChangeListener l) {
        assignedToChangeSupport.removePropertyChangeListener("prop", l);
    }


    // ==== Property Description ====

    private String description;

    /**
     * Method getDescription.  Returns the current value of the Description
     * property.
     * 
     * @return String 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription.  Sets the value of the Description property.
     */
    public void setDescription(String newValue) {
        String oldValue = description;
        this.description = newValue;
        descriptionChangeSupport.firePropertyChange(
            "prop",
            oldValue,
            newValue);
    }

    private PropertyChangeSupport descriptionChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addDescriptionListener.  Add a property change listener to the
     * Description property.
     */
    public void addDescriptionListener(PropertyChangeListener l) {
        descriptionChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addDescriptionListener.  Remove a property change listener from the
     * Description property.
     */
    public void removeDescriptionListener(PropertyChangeListener l) {
        descriptionChangeSupport.removePropertyChangeListener("prop", l);
    }


    // ==== Property Amount ====

    private double amount;

    /**
     * Method getAmount.  Returns the current value of the Amount
     * property.
     * 
     * @return double 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Method setAmount.  Sets the value of the Amount property.
     */
    public void setAmount(double newValue) {
        double oldValue = amount;
        this.amount = newValue;
        amountChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport amountChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addAmountListener.  Add a property change listener to the
     * Amount property.
     */
    public void addAmountListener(PropertyChangeListener l) {
        amountChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addAmountListener.  Remove a property change listener from the
     * Amount property.
     */
    public void removeAmountListener(PropertyChangeListener l) {
        amountChangeSupport.removePropertyChangeListener("prop", l);
    }

}
