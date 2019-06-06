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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Class BudgetYearTotal.  This is the 13th month of the year. ;-)
 * 
 * @author daveo
 */
public class BudgetYearTotal extends BudgetItem {
    private static final long serialVersionUID = 0L;

    /**
     * Constructor BudgetYearTotal.  
     * 
     * @param category
     */
    public BudgetYearTotal(BudgetCategory category) {
        super(category);
        TotalCalc addListener = new TotalCalc() {
            public void calc(BudgetItem item) {
                item.addBudgetListener(budgetChangeListener);
                item.addActualListener(actualChangeListener);
            }
        };
        forEach(addListener);
    }
    
    abstract class TotalCalc implements Serializable {
        public double result = 0;
        public abstract void calc(BudgetItem item);
        public void add(double value) {
            long r = (long)(result * 100);
            long v = (long)(value * 100);
            result = ((double)(r+v))/100;
        }
    }
    
    private TotalCalc forEach(TotalCalc result) {
        BudgetItem[] budget = category.getBudget();
        for (int i = 0; i < budget.length-1; i++) {
            result.calc(budget[i]);
        }
        return result;
    }

    public double calcBudget() {
        TotalCalc addTotal = new TotalCalc() {
            public void calc(BudgetItem item) {
                add(item.getBudget());
            }
        };
        return forEach(addTotal).result;
    }

    private class BudgetChangeListener implements PropertyChangeListener, Serializable {
        public void propertyChange(PropertyChangeEvent arg0) {
            setBudget(calcBudget());
        }
    }
    protected final PropertyChangeListener budgetChangeListener = new BudgetChangeListener();

    public double calcActual() {
        TotalCalc addTotal = new TotalCalc() {
            public void calc(BudgetItem item) {
                add(item.getActual());
            }
        };
        return forEach(addTotal).result;
    }

    private class ActualChangeListener implements PropertyChangeListener, Serializable {
        public void propertyChange(PropertyChangeEvent arg0) {
            setActual(calcActual());
        }
    }
    protected final PropertyChangeListener actualChangeListener = new ActualChangeListener();
    
}


