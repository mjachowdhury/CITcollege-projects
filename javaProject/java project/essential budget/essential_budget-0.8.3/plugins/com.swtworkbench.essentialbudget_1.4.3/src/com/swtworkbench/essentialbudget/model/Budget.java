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
import java.util.Iterator;
import java.util.LinkedList;

import com.swtworkbench.ed.aware.messages.MessageSource;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;

/**
 * Class Budget.  A budget for a given year.
 * 
 * @author daveo
 */
public class Budget implements Serializable {
    private static final long serialVersionUID = 0L;

    // ==== Property Year ====

    private int year;

    /**
     * Method getYear.  Returns the current value of the Year
     * property.
     * 
     * @return int 
     */
    public int getYear() {
        return year;
    }

    /**
     * Method setYear.  Sets the value of the Year property.
     */
    public void setYear(int newValue) {
        int oldValue = year;
        this.year = newValue;
        yearChangeSupport.firePropertyChange(
            "prop",
            oldValue,
            newValue);
    }

    private PropertyChangeSupport yearChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYearListener.  Add a property change listener to the
     * Year property.
     */
    public void addYearListener(PropertyChangeListener l) {
        yearChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYearListener.  Remove a property change listener from the
     * Year property.
     */
    public void removeYearListener(PropertyChangeListener l) {
        yearChangeSupport.removePropertyChangeListener("prop", l);
    }
    

    private LinkedList inflowCategories = new LinkedList();
    private LinkedList outflowCategories = new LinkedList();
    private IObjectFactory categoryObjectFactory = new LinkedListObjectFactory(BudgetCategory.class);
    
    /**
     * Method getInflowCateogires.  Returns the inflow categories
     * @return LinkedList the inflow categories
     */
    public LinkedList getInflowCategories() {
        return inflowCategories;
    }
    
    /**
     * Method isInflowCategory.  Returns true if <code>category</code> is
     * an inflow category.
     * 
     * @param category The category to check.
     * @return true if <code>category</code> is found in the inflowCategories list.  false otherwise
     */
    public boolean isInflowCategory(BudgetCategory category) {
        for (Iterator i = inflowCategories.iterator(); i.hasNext();) {
            BudgetCategory element = (BudgetCategory) i.next();
            if (element == category)
                return true;
        }
        return false;
    }
    
    /**
     * Method getInflowCategoriesObjectFactory.  
     * @return
     */
    public IObjectFactory getInflowCategoriesObjectFactory() {
        return categoryObjectFactory;
    }
    
    /**
     * Method getInflowCategoriesChangeListener.  
     * @return
     */
    public IModelChangeListener getInflowCategoriesChangeListener() {
        return new ModelChangeAdapter() {
            public boolean checkObjectDeletion(
                Object container,
                int selection,
                Object toDelete) 
            {
                BudgetCategory category = (BudgetCategory) toDelete;
                if (category.getYTDActual() == 0 && category.getYTDBudget() == 0)
                    return true;
                else {
                    MessageSource.getDefault().sendMessage("Can't delete a category that has budget or transactions");
                    return false;
                }
            }
        };
    }
    
    /**
     * Method newInflowCategory.  Creates and returns a new inflow category  
     * @return NewObject a NewObject encapsulating the new object
     */
    public NewObject newInflowCategory() {
        return Model.getDefault().create(inflowCategories);
    }

    /**
     * Method getOutflowCategories.  
     * @return
     */
    public LinkedList getOutflowCategories() {
        return outflowCategories;
    }
    
    /**
     * Method getOutflowCategoriesObjectFactory.  
     * @return
     */
    public IObjectFactory getOutflowCategoriesObjectFactory() {
        return categoryObjectFactory;
    }
    
    /**
     * Method getOutflowCategoriesChangeListener.  
     * @return
     */
    public IModelChangeListener getOutflowCategoriesChangeListener() {
        return new ModelChangeAdapter() {
            public boolean checkObjectDeletion(
                Object container,
                int selection,
                Object toDelete) 
            {
                BudgetCategory category = (BudgetCategory) toDelete;
                if (category.getYTDActual() == 0 && category.getYTDBudget() == 0)
                    return true;
                else {
                    MessageSource.getDefault().sendMessage("Can't delete a category that has budget or transactions");
                    return false;
                }
            }
        };
    }
    
    /**
     * Method newOutflowCategory.  Creates and returns a new outflow category
     * @return NewObject a NewObject encapsulating the new object
     */
    public NewObject newOutflowCategory() {
        return Model.getDefault().create(outflowCategories);
    }

    // ==== Property YTDInflowBudget ====

    private double ytdInflowBudget;

    /**
     * Method getYTDInflowBudget.  Returns the current value of the YTDInflowBudget
     * property.
     * 
     * @return double 
     */
    public double getYTDInflowBudget() {
        return ytdInflowBudget;
    }

    /**
     * Method setYTDInflowBudget.  Sets the value of the YTDInflowBudget property.
     */
    public void setYTDInflowBudget(double newValue) {
        double oldValue = ytdInflowBudget;
        this.ytdInflowBudget = newValue;
        ytdInflowBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdInflowBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDInflowBudgetListener.  Add a property change listener to the
     * YTDInflowBudget property.
     */
    public void addYTDInflowBudgetListener(PropertyChangeListener l) {
        ytdInflowBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDInflowBudgetListener.  Remove a property change listener from the
     * YTDInflowBudget property.
     */
    public void removeYTDInflowBudgetListener(PropertyChangeListener l) {
        ytdInflowBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDInflowActual ====

    private double ytdInflowActual;

    /**
     * Method getYTDInflowActual.  Returns the current value of the YTDInflowActual
     * property.
     * 
     * @return double 
     */
    public double getYTDInflowActual() {
        return ytdInflowActual;
    }

    /**
     * Method setYTDInflowActual.  Sets the value of the YTDInflowActual property.
     */
    public void setYTDInflowActual(double newValue) {
        double oldValue = ytdInflowActual;
        this.ytdInflowActual = newValue;
        ytdInflowActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdInflowActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDInflowActualListener.  Add a property change listener to the
     * YTDInflowActual property.
     */
    public void addYTDInflowActualListener(PropertyChangeListener l) {
        ytdInflowActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDInflowActualListener.  Remove a property change listener from the
     * YTDInflowActual property.
     */
    public void removeYTDInflowActualListener(PropertyChangeListener l) {
        ytdInflowActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDInflowDifference ====

    private double ytdInflowDifference;

    /**
     * Method getYTDInflowDifference.  Returns the current value of the YTDInflowDifference
     * property.
     * 
     * @return double 
     */
    public double getYTDInflowDifference() {
        return ytdInflowDifference;
    }

    /**
     * Method setYTDInflowDifference.  Sets the value of the YTDInflowDifference property.
     */
    public void setYTDInflowDifference(double newValue) {
        double oldValue = ytdInflowDifference;
        this.ytdInflowDifference = newValue;
        ytdInflowDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdInflowDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDInflowDifferenceListener.  Add a property change listener to the
     * YTDInflowDifference property.
     */
    public void addYTDInflowDifferenceListener(PropertyChangeListener l) {
        ytdInflowDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDInflowDifferenceListener.  Remove a property change listener from the
     * YTDInflowDifference property.
     */
    public void removeYTDInflowDifferenceListener(PropertyChangeListener l) {
        ytdInflowDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDOutflowBudget ====

    private double ytdOutflowBudget;

    /**
     * Method getYTDOutflowBudget.  Returns the current value of the YTDOutflowBudget
     * property.
     * 
     * @return double 
     */
    public double getYTDOutflowBudget() {
        return ytdOutflowBudget;
    }

    /**
     * Method setYTDOutflowBudget.  Sets the value of the YTDOutflowBudget property.
     */
    public void setYTDOutflowBudget(double newValue) {
        double oldValue = ytdOutflowBudget;
        this.ytdOutflowBudget = newValue;
        ytdOutflowBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdOutflowBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDOutflowBudgetListener.  Add a property change listener to the
     * YTDOutflowBudget property.
     */
    public void addYTDOutflowBudgetListener(PropertyChangeListener l) {
        ytdOutflowBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDOutflowBudgetListener.  Remove a property change listener from the
     * YTDOutflowBudget property.
     */
    public void removeYTDOutflowBudgetListener(PropertyChangeListener l) {
        ytdOutflowBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDOutflowActual ====

    private double ytdOutflowActual;

    /**
     * Method getYTDOutflowActual.  Returns the current value of the YTDOutflowActual
     * property.
     * 
     * @return double 
     */
    public double getYTDOutflowActual() {
        return ytdOutflowActual;
    }

    /**
     * Method setYTDOutflowActual.  Sets the value of the YTDOutflowActual property.
     */
    public void setYTDOutflowActual(double newValue) {
        double oldValue = ytdOutflowActual;
        this.ytdOutflowActual = newValue;
        ytdOutflowActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdOutflowActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDOutflowActualListener.  Add a property change listener to the
     * YTDOutflowActual property.
     */
    public void addYTDOutflowActualListener(PropertyChangeListener l) {
        ytdOutflowActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDOutflowActualListener.  Remove a property change listener from the
     * YTDOutflowActual property.
     */
    public void removeYTDOutflowActualListener(PropertyChangeListener l) {
        ytdOutflowActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDOutflowDifference ====

    private double ytdOutflowDifference;

    /**
     * Method getYTDOutflowDifference.  Returns the current value of the YTDOutflowDifference
     * property.
     * 
     * @return double 
     */
    public double getYTDOutflowDifference() {
        return ytdOutflowDifference;
    }

    /**
     * Method setYTDOutflowDifference.  Sets the value of the YTDOutflowDifference property.
     */
    public void setYTDOutflowDifference(double newValue) {
        double oldValue = ytdOutflowDifference;
        this.ytdOutflowDifference = newValue;
        ytdOutflowDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdOutflowDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDOutflowDifferenceListener.  Add a property change listener to the
     * YTDOutflowDifference property.
     */
    public void addYTDOutflowDifferenceListener(PropertyChangeListener l) {
        ytdOutflowDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDOutflowDifferenceListener.  Remove a property change listener from the
     * YTDOutflowDifference property.
     */
    public void removeYTDOutflowDifferenceListener(PropertyChangeListener l) {
        ytdOutflowDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDDifferenceBudget ====

    private double ytdDifferenceBudget;

    /**
     * Method getYTDDifferenceBudget.  Returns the current value of the YTDDifferenceBudget
     * property.
     * 
     * @return double 
     */
    public double getYTDDifferenceBudget() {
        return ytdDifferenceBudget;
    }

    /**
     * Method setYTDDifferenceBudget.  Sets the value of the YTDDifferenceBudget property.
     */
    public void setYTDDifferenceBudget(double newValue) {
        double oldValue = ytdDifferenceBudget;
        this.ytdDifferenceBudget = newValue;
        ytdDifferenceBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdDifferenceBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDDifferenceBudgetListener.  Add a property change listener to the
     * YTDDifferenceBudget property.
     */
    public void addYTDDifferenceBudgetListener(PropertyChangeListener l) {
        ytdDifferenceBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDDifferenceBudgetListener.  Remove a property change listener from the
     * YTDDifferenceBudget property.
     */
    public void removeYTDDifferenceBudgetListener(PropertyChangeListener l) {
        ytdDifferenceBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDDifferenceActual ====

    private double ytdDifferenceActual;

    /**
     * Method getYTDDifferenceActual.  Returns the current value of the YTDDifferenceActual
     * property.
     * 
     * @return double 
     */
    public double getYTDDifferenceActual() {
        return ytdDifferenceActual;
    }

    /**
     * Method setYTDDifferenceActual.  Sets the value of the YTDDifferenceActual property.
     */
    public void setYTDDifferenceActual(double newValue) {
        double oldValue = ytdDifferenceActual;
        this.ytdDifferenceActual = newValue;
        ytdDifferenceActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdDifferenceActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDDifferenceActualListener.  Add a property change listener to the
     * YTDDifferenceActual property.
     */
    public void addYTDDifferenceActualListener(PropertyChangeListener l) {
        ytdDifferenceActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDDifferenceActualListener.  Remove a property change listener from the
     * YTDDifferenceActual property.
     */
    public void removeYTDDifferenceActualListener(PropertyChangeListener l) {
        ytdDifferenceActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property YTDDifferenceDifference ====

    private double ytdDifferenceDifference;

    /**
     * Method getYTDDifferenceDifference.  Returns the current value of the YTDDifferenceDifference
     * property.
     * 
     * @return double 
     */
    public double getYTDDifferenceDifference() {
        return ytdDifferenceDifference;
    }

    /**
     * Method setYTDDifferenceDifference.  Sets the value of the YTDDifferenceDifference property.
     */
    public void setYTDDifferenceDifference(double newValue) {
        double oldValue = ytdDifferenceDifference;
        this.ytdDifferenceDifference = newValue;
        ytdDifferenceDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport ytdDifferenceDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addYTDDifferenceDifferenceListener.  Add a property change listener to the
     * YTDDifferenceDifference property.
     */
    public void addYTDDifferenceDifferenceListener(PropertyChangeListener l) {
        ytdDifferenceDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addYTDDifferenceDifferenceListener.  Remove a property change listener from the
     * YTDDifferenceDifference property.
     */
    public void removeYTDDifferenceDifferenceListener(PropertyChangeListener l) {
        ytdDifferenceDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalInflowBudget ====

    private double totalInflowBudget;

    /**
     * Method getTotalInflowBudget.  Returns the current value of the TotalInflowBudget
     * property.
     * 
     * @return double 
     */
    public double getTotalInflowBudget() {
        return totalInflowBudget;
    }

    /**
     * Method setTotalInflowBudget.  Sets the value of the TotalInflowBudget property.
     */
    public void setTotalInflowBudget(double newValue) {
        double oldValue = totalInflowBudget;
        this.totalInflowBudget = newValue;
        totalInflowBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalInflowBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalInflowBudgetListener.  Add a property change listener to the
     * TotalInflowBudget property.
     */
    public void addTotalInflowBudgetListener(PropertyChangeListener l) {
        totalInflowBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalInflowBudgetListener.  Remove a property change listener from the
     * TotalInflowBudget property.
     */
    public void removeTotalInflowBudgetListener(PropertyChangeListener l) {
        totalInflowBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalInflowActual ====

    private double totalInflowActual;

    /**
     * Method getTotalInflowActual.  Returns the current value of the TotalInflowActual
     * property.
     * 
     * @return double 
     */
    public double getTotalInflowActual() {
        return totalInflowActual;
    }

    /**
     * Method setTotalInflowActual.  Sets the value of the TotalInflowActual property.
     */
    public void setTotalInflowActual(double newValue) {
        double oldValue = totalInflowActual;
        this.totalInflowActual = newValue;
        totalInflowActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalInflowActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalInflowActualListener.  Add a property change listener to the
     * TotalInflowActual property.
     */
    public void addTotalInflowActualListener(PropertyChangeListener l) {
        totalInflowActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalInflowActualListener.  Remove a property change listener from the
     * TotalInflowActual property.
     */
    public void removeTotalInflowActualListener(PropertyChangeListener l) {
        totalInflowActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalInflowDifference ====

    private double totalInflowDifference;

    /**
     * Method getTotalInflowDifference.  Returns the current value of the TotalInflowDifference
     * property.
     * 
     * @return double 
     */
    public double getTotalInflowDifference() {
        return totalInflowDifference;
    }

    /**
     * Method setTotalInflowDifference.  Sets the value of the TotalInflowDifference property.
     */
    public void setTotalInflowDifference(double newValue) {
        double oldValue = totalInflowDifference;
        this.totalInflowDifference = newValue;
        totalInflowDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalInflowDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalInflowDifferenceListener.  Add a property change listener to the
     * TotalInflowDifference property.
     */
    public void addTotalInflowDifferenceListener(PropertyChangeListener l) {
        totalInflowDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalInflowDifferenceListener.  Remove a property change listener from the
     * TotalInflowDifference property.
     */
    public void removeTotalInflowDifferenceListener(PropertyChangeListener l) {
        totalInflowDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalOutflowBudget ====

    private double totalOutflowBudget;

    /**
     * Method getTotalOutflowBudget.  Returns the current value of the TotalOutflowBudget
     * property.
     * 
     * @return double 
     */
    public double getTotalOutflowBudget() {
        return totalOutflowBudget;
    }

    /**
     * Method setTotalOutflowBudget.  Sets the value of the TotalOutflowBudget property.
     */
    public void setTotalOutflowBudget(double newValue) {
        double oldValue = totalOutflowBudget;
        this.totalOutflowBudget = newValue;
        totalOutflowBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalOutflowBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalOutflowBudgetListener.  Add a property change listener to the
     * TotalOutflowBudget property.
     */
    public void addTotalOutflowBudgetListener(PropertyChangeListener l) {
        totalOutflowBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalOutflowBudgetListener.  Remove a property change listener from the
     * TotalOutflowBudget property.
     */
    public void removeTotalOutflowBudgetListener(PropertyChangeListener l) {
        totalOutflowBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalOutflowActual ====

    private double totalOutflowActual;

    /**
     * Method getTotalOutflowActual.  Returns the current value of the TotalOutflowActual
     * property.
     * 
     * @return double 
     */
    public double getTotalOutflowActual() {
        return totalOutflowActual;
    }

    /**
     * Method setTotalOutflowActual.  Sets the value of the TotalOutflowActual property.
     */
    public void setTotalOutflowActual(double newValue) {
        double oldValue = totalOutflowActual;
        this.totalOutflowActual = newValue;
        totalOutflowActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalOutflowActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalOutflowActualListener.  Add a property change listener to the
     * TotalOutflowActual property.
     */
    public void addTotalOutflowActualListener(PropertyChangeListener l) {
        totalOutflowActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalOutflowActualListener.  Remove a property change listener from the
     * TotalOutflowActual property.
     */
    public void removeTotalOutflowActualListener(PropertyChangeListener l) {
        totalOutflowActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalOutflowDifference ====

    private double totalOutflowDifference;

    /**
     * Method getTotalOutflowDifference.  Returns the current value of the TotalOutflowDifference
     * property.
     * 
     * @return double 
     */
    public double getTotalOutflowDifference() {
        return totalOutflowDifference;
    }

    /**
     * Method setTotalOutflowDifference.  Sets the value of the TotalOutflowDifference property.
     */
    public void setTotalOutflowDifference(double newValue) {
        double oldValue = totalOutflowDifference;
        this.totalOutflowDifference = newValue;
        totalOutflowDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalOutflowDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalOutflowDifferenceListener.  Add a property change listener to the
     * TotalOutflowDifference property.
     */
    public void addTotalOutflowDifferenceListener(PropertyChangeListener l) {
        totalOutflowDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalOutflowDifferenceListener.  Remove a property change listener from the
     * TotalOutflowDifference property.
     */
    public void removeTotalOutflowDifferenceListener(PropertyChangeListener l) {
        totalOutflowDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalDifferenceBudget ====

    private double totalDifferenceBudget;

    /**
     * Method getTotalDifferenceBudget.  Returns the current value of the TotalDifferenceBudget
     * property.
     * 
     * @return double 
     */
    public double getTotalDifferenceBudget() {
        return totalDifferenceBudget;
    }

    /**
     * Method setTotalDifferenceBudget.  Sets the value of the TotalDifferenceBudget property.
     */
    public void setTotalDifferenceBudget(double newValue) {
        double oldValue = totalDifferenceBudget;
        this.totalDifferenceBudget = newValue;
        totalDifferenceBudgetChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalDifferenceBudgetChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalDifferenceBudgetListener.  Add a property change listener to the
     * TotalDifferenceBudget property.
     */
    public void addTotalDifferenceBudgetListener(PropertyChangeListener l) {
        totalDifferenceBudgetChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalDifferenceBudgetListener.  Remove a property change listener from the
     * TotalDifferenceBudget property.
     */
    public void removeTotalDifferenceBudgetListener(PropertyChangeListener l) {
        totalDifferenceBudgetChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalDifferenceActual ====

    private double totalDifferenceActual;

    /**
     * Method getTotalDifferenceActual.  Returns the current value of the TotalDifferenceActual
     * property.
     * 
     * @return double 
     */
    public double getTotalDifferenceActual() {
        return totalDifferenceActual;
    }

    /**
     * Method setTotalDifferenceActual.  Sets the value of the TotalDifferenceActual property.
     */
    public void setTotalDifferenceActual(double newValue) {
        double oldValue = totalDifferenceActual;
        this.totalDifferenceActual = newValue;
        totalDifferenceActualChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalDifferenceActualChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalDifferenceActualListener.  Add a property change listener to the
     * TotalDifferenceActual property.
     */
    public void addTotalDifferenceActualListener(PropertyChangeListener l) {
        totalDifferenceActualChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalDifferenceActualListener.  Remove a property change listener from the
     * TotalDifferenceActual property.
     */
    public void removeTotalDifferenceActualListener(PropertyChangeListener l) {
        totalDifferenceActualChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // ==== Property TotalDifferenceDifference ====

    private double totalDifferenceDifference;

    /**
     * Method getTotalDifferenceDifference.  Returns the current value of the TotalDifferenceDifference
     * property.
     * 
     * @return double 
     */
    public double getTotalDifferenceDifference() {
        return totalDifferenceDifference;
    }

    /**
     * Method setTotalDifferenceDifference.  Sets the value of the TotalDifferenceDifference property.
     */
    public void setTotalDifferenceDifference(double newValue) {
        double oldValue = totalDifferenceDifference;
        this.totalDifferenceDifference = newValue;
        totalDifferenceDifferenceChangeSupport.firePropertyChange(
            "prop",
            new Double(oldValue),
            new Double(newValue));
    }

    private PropertyChangeSupport totalDifferenceDifferenceChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addTotalDifferenceDifferenceListener.  Add a property change listener to the
     * TotalDifferenceDifference property.
     */
    public void addTotalDifferenceDifferenceListener(PropertyChangeListener l) {
        totalDifferenceDifferenceChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addTotalDifferenceDifferenceListener.  Remove a property change listener from the
     * TotalDifferenceDifference property.
     */
    public void removeTotalDifferenceDifferenceListener(PropertyChangeListener l) {
        totalDifferenceDifferenceChangeSupport.removePropertyChangeListener("prop", l);
    }

}
