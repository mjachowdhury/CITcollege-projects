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
import java.util.Date;
import java.util.Iterator;

import com.swtworkbench.bus.Choreographer;
import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.essentialbudget.model.deltas.AccountTransactionDateDelta;
import com.swtworkbench.essentialbudget.model.deltas.BudgetItemDelta;
import com.swtworkbench.essentialbudget.model.deltas.CategoryTransactionDelta;

/**
 * Class BudgetCategory.  
 * 
 * @author daveo
 */
public class BudgetCategory implements Serializable {
    private static final long serialVersionUID = 0L;
    
    public BudgetCategory() {
        theThis = this;
        months = new BudgetItem[13];
        for (int i=0; i < months.length-1; ++i) {
            months[i] = new BudgetItem(this);
        }
        months[months.length-1] = new BudgetYearTotal(this);
        Choreographer c = BudgetAppData.choreographer();
        c.register(budgetChange, CategoryTransactionDelta.class);
        c.register(budgetChange, AccountTransactionDateDelta.class);
        c.register(budgetChange, BudgetItemDelta.class);
    }
    
    protected final BudgetCategory theThis;
    
    IActor budgetChange = new IActor() {
        public Object perform(Request r) throws Exception {
            if (r.action instanceof CategoryTransactionDelta) {
                CategoryTransactionDelta delta = (CategoryTransactionDelta) r.action;
                if (delta.parent.getAssignedTo() == theThis) {
                    // Update the YTDActual
                    BigDecimal ytdActual = new BigDecimal(getYTDActual());
                    ytdActual = ytdActual.add(delta.delta);
                    setYTDActual(ytdActual.doubleValue());
                    
                    // Update the proper month's YTDActual
                    int monthNumber = delta.parent.getParent().getDate().getMonth();
                    BigDecimal monthActual = new BigDecimal(getBudget()[monthNumber].getActual());
                    monthActual = monthActual.add(delta.delta);
                    getBudget()[monthNumber].setActual(monthActual.doubleValue());
                }
            } else if (r.action instanceof AccountTransactionDateDelta) {
                AccountTransactionDateDelta delta = (AccountTransactionDateDelta) r.action;
                for (Iterator i = delta.transaction.getSplits().iterator(); i.hasNext();) {
                    CategoryTransaction ct = (CategoryTransaction) i.next();
                    if (ct.getAssignedTo() == this) {
                        updateSplitAssignment(delta, ct);
                    }
                }
            } else if (r.action instanceof BudgetItemDelta) {
                BudgetItemDelta delta = (BudgetItemDelta) r.action;
                if (delta.owner == theThis) {
                    setYTDBudget(recalculateCurrentBudget(delta.owner.getBudget()));
                }
            }
            return null;
        }
    };
    
    /**
     * Method updateSplitAssignment.  The date just changed on on a split.
     * We need to reassign the "actual" numbers represented by that split.
     * 
     * @param delta
     * @param ct
     */
    protected void updateSplitAssignment(AccountTransactionDateDelta delta, CategoryTransaction ct) {
        if (delta.oldDate.getMonth() != delta.newDate.getMonth() ||
            delta.oldDate.getYear() != delta.newDate.getYear()) 
        {
            int oldYear = delta.oldDate.getYear() - 100 + 2000;
            int newYear = delta.newDate.getYear() - 100 + 2000;
            int curYear = BudgetAppData.getDefault().getBudget().getYear();
            int oldMonth = delta.oldDate.getMonth();
            int newMonth = delta.newDate.getMonth();
                    
            /*
             * Note that if we are dealing with a transaction moving to
             * or from another budget year, we don't try to reconcile 
             * the change with the other budget year, only with the current
             * year.
             */
            if (oldYear == curYear) {
                BigDecimal monthActual = new BigDecimal(getBudget()[oldMonth].getActual());
                monthActual = monthActual.subtract(new BigDecimal(ct.getAmount()));
                getBudget()[oldMonth].setActual(monthActual.doubleValue());
            }
                    
            if (newYear == curYear) {
                BigDecimal monthActual = new BigDecimal(getBudget()[newMonth].getActual());
                monthActual = monthActual.subtract(new BigDecimal(ct.getAmount()));
                getBudget()[newMonth].setActual(monthActual.doubleValue());
            }
        }
    }

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    private String name="";
    
    /**
     * Method getName.  
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.  
     * @param string
     */
    public void setName(String string) {
        String oldValue = name;
        name = string;
        pcs.firePropertyChange("Name", oldValue, name);
    }

    public void addNameListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("Name", l);
    }
    
    public void removeNameListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("Name", l);
    }
    
    private double YTDBudget=0;
    
    /**
     * Method recalculateCurrentBudget.  
     * @param items
     */
    protected double recalculateCurrentBudget(BudgetItem[] budget) {
        BigDecimal totalBudget = new BigDecimal(0);
        Date today = new Date();
        for (int i = 0; i <= today.getMonth(); i++) {
            totalBudget = totalBudget.add(new BigDecimal(budget[i].getBudget()));
        }
        return totalBudget.doubleValue();
    }

    /**
     * Method getYTDBudget.  This method forces an update after a restart.
     * We really should generate real-time month-change events and recalculate
     * within 30 seconds or whatever of a real month-change.
     * @return
     */
    public double getYTDBudget() {
        YTDBudget = recalculateCurrentBudget(months);
        return YTDBudget;
    }

    /**
     * Method setYTDBudget.  
     * @param f
     */
    protected void setYTDBudget(double f) {
        double oldDifference = getDifference();
        double oldValue = YTDBudget;
        YTDBudget = f;
        pcs.firePropertyChange("YTDBudget", new Double(oldValue), new Double(YTDBudget));
        differenceChangeSupport.firePropertyChange("prop", new Double(oldDifference), new Double(getDifference()));
    }

    public void addYTDBudgetListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("YTDBudget", l);
    }
    
    public void removeYTDBudgetListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("YTDBudgets", l);
    }
    
    private double YTDActual=0;

    /**
     * Method getYTDActual.  
     * @return
     */
    public double getYTDActual() {
        return YTDActual;
    }

    /**
     * Method setYTDActual.  
     * @param f
     */
    protected void setYTDActual(double newValue) {
        double oldDifference = getDifference();
        double oldValue = YTDActual;
        YTDActual = newValue;
        pcs.firePropertyChange("YTDActual", new Float(oldValue), new Float(YTDActual));
        differenceChangeSupport.firePropertyChange("prop", new Double(oldDifference), new Double(getDifference()));
    }

    public void addYTDActualListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("YTDActual", l);
    }
    
    public void removeYTDActualListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("YTDActual", l);
    }
    

    // ==== Property Difference ====

    /**
     * Method getDifference.  Returns the current value of the Difference
     * property, which is calculated as Budget - Actual.
     * 
     * @return double 
     */
    public double getDifference() {
        BigDecimal budget = new BigDecimal(this.YTDBudget);
        BigDecimal actual = new BigDecimal(this.YTDActual);
        if (BudgetAppData.getDefault().getBudget().isInflowCategory(this))
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

    // ==== Property Budget ====

    private BudgetItem[] months;
    
    /**
     * Method getBudget.  
     * @return
     */
    public BudgetItem[] getBudget() {
        return months;
    }

}


