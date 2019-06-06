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
import java.math.BigDecimal;

import com.swtworkbench.essentialbudget.model.deltas.BudgetItemDelta;

/**
 * Class BudgetItem.  
 * 
 * @author daveo
 */
public class BudgetItem implements Serializable {
    private static final long serialVersionUID = 0L;

    protected final BudgetCategory category;

    public BudgetItem(BudgetCategory category) {
        this.category = category;
    }

    // ==== Property Budget ====

    private double budget;

    /**
     * Method getBudget.  Returns the current value of the Budget
     * property.
     * 
     * @return double 
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Method setBudget.  Sets the value of the Budget property.
     */
    public void setBudget(double newValue) {
        double oldDifference = getDifference();
        double oldValue = budget;
        this.budget = newValue;
        budgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
        BigDecimal delta = new BigDecimal(newValue).subtract(new BigDecimal(oldValue));
        BudgetAppData.choreographer().requestl(new BudgetItemDelta(this, category, delta));
        differenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldDifference),
            new Double(getDifference()));
    }

    protected PropertyChangeSupport budgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addBudgetListener.  Add a property change listener to the
     * Budget property.
     */
    public void addBudgetListener(PropertyChangeListener l) {
        budgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addBudgetListener.  Remove a property change listener from the
     * Budget property.
     */
    public void removeBudgetListener(PropertyChangeListener l) {
        budgetChangeSupport.removePropertyChangeListener("prop", l);
    }

    // ==== Property Actual ====

    private double actual;

    /**
     * Method getActual.  Returns the current value of the Actual
     * property.
     * 
     * @return double 
     */
    public double getActual() {
        return actual;
    }

    /**
     * Method setActual.  Sets the value of the Actual property.
     */
    public void setActual(double newValue) {
        double oldDifference = getDifference();
        double oldValue = actual;
        this.actual = newValue;
        actualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
        differenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldDifference),
            new Double(getDifference()));
    }

    protected PropertyChangeSupport actualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addActualListener.  Add a property change listener to the
     * Actual property.
     */
    public void addActualListener(PropertyChangeListener l) {
        actualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addActualListener.  Remove a property change listener from the
     * Actual property.
     */
    public void removeActualListener(PropertyChangeListener l) {
        actualChangeSupport.removePropertyChangeListener("prop", l);
    }

    // ==== Property Difference ====

    /**
     * Method getDifference.  Returns the current value of the Difference
     * property, which is calculated as Budget - Actual.
     * 
     * @return double 
     */
    public double getDifference() {
        BigDecimal budget = new BigDecimal(this.budget);
        BigDecimal actual = new BigDecimal(this.actual);
        if (BudgetAppData.getDefault().getBudget().isInflowCategory(category))
            return actual.subtract(budget).doubleValue();
        else
            return budget.subtract(actual).doubleValue();
    }

    protected PropertyChangeSupport differenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addDifferenceListener.  Add a property change listener to the
     * Difference property.
     */
    public void addDifferenceListener(PropertyChangeListener l) {
        differenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addDifferenceListener.  Remove a property change listener from the
     * Difference property.
     */
    public void removeDifferenceListener(PropertyChangeListener l) {
        differenceChangeSupport.removePropertyChangeListener("prop", l);
    }

}
