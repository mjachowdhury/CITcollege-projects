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

import com.swtworkbench.essentialbudget.model.BudgetCategory;

/**
 * Class IBudgetCategoryRow.  
 * 
 * @author daveo
 */
public interface IBudgetCategoryRow {
    public BudgetCategory getBudgetCategory();
    public String getName();
    
    public double get00Budget();
    public void set00Budget(double amount);
    public void add00BudgetListener(PropertyChangeListener l);
    public void remove00BudgetListener(PropertyChangeListener l);
    public double get00Actual();
    public void set00Actual(double amount);
    public void add00ActualListener(PropertyChangeListener l);
    public void remove00ActualListener(PropertyChangeListener l);
    public double get00Diff();
    public void add00DiffListener(PropertyChangeListener l);
    public void remove00DiffListener(PropertyChangeListener l);

    public double get01Budget();
    public void set01Budget(double amount);
    public void add01BudgetListener(PropertyChangeListener l);
    public void remove01BudgetListener(PropertyChangeListener l);
    public double get01Actual();
    public void set01Actual(double amount);
    public void add01ActualListener(PropertyChangeListener l);
    public void remove01ActualListener(PropertyChangeListener l);
    public double get01Diff();
    public void add01DiffListener(PropertyChangeListener l);
    public void remove01DiffListener(PropertyChangeListener l);

    public double get02Budget();
    public void set02Budget(double amount);
    public void add02BudgetListener(PropertyChangeListener l);
    public void remove02BudgetListener(PropertyChangeListener l);
    public double get02Actual();
    public void set02Actual(double amount);
    public void add02ActualListener(PropertyChangeListener l);
    public void remove02ActualListener(PropertyChangeListener l);
    public double get02Diff();
    public void add02DiffListener(PropertyChangeListener l);
    public void remove02DiffListener(PropertyChangeListener l);

    public double get03Budget();
    public void set03Budget(double amount);
    public void add03BudgetListener(PropertyChangeListener l);
    public void remove03BudgetListener(PropertyChangeListener l);
    public double get03Actual();
    public void set03Actual(double amount);
    public void add03ActualListener(PropertyChangeListener l);
    public void remove03ActualListener(PropertyChangeListener l);
    public double get03Diff();
    public void add03DiffListener(PropertyChangeListener l);
    public void remove03DiffListener(PropertyChangeListener l);

    public double get04Budget();
    public void set04Budget(double amount);
    public void add04BudgetListener(PropertyChangeListener l);
    public void remove04BudgetListener(PropertyChangeListener l);
    public double get04Actual();
    public void set04Actual(double amount);
    public void add04ActualListener(PropertyChangeListener l);
    public void remove04ActualListener(PropertyChangeListener l);
    public double get04Diff();
    public void add04DiffListener(PropertyChangeListener l);
    public void remove04DiffListener(PropertyChangeListener l);

    public double get05Budget();
    public void set05Budget(double amount);
    public void add05BudgetListener(PropertyChangeListener l);
    public void remove05BudgetListener(PropertyChangeListener l);
    public double get05Actual();
    public void set05Actual(double amount);
    public void add05ActualListener(PropertyChangeListener l);
    public void remove05ActualListener(PropertyChangeListener l);
    public double get05Diff();
    public void add05DiffListener(PropertyChangeListener l);
    public void remove05DiffListener(PropertyChangeListener l);

    public double get06Budget();
    public void set06Budget(double amount);
    public void add06BudgetListener(PropertyChangeListener l);
    public void remove06BudgetListener(PropertyChangeListener l);
    public double get06Actual();
    public void set06Actual(double amount);
    public void add06ActualListener(PropertyChangeListener l);
    public void remove06ActualListener(PropertyChangeListener l);
    public double get06Diff();
    public void add06DiffListener(PropertyChangeListener l);
    public void remove06DiffListener(PropertyChangeListener l);

    public double get07Budget();
    public void set07Budget(double amount);
    public void add07BudgetListener(PropertyChangeListener l);
    public void remove07BudgetListener(PropertyChangeListener l);
    public double get07Actual();
    public void set07Actual(double amount);
    public void add07ActualListener(PropertyChangeListener l);
    public void remove07ActualListener(PropertyChangeListener l);
    public double get07Diff();
    public void add07DiffListener(PropertyChangeListener l);
    public void remove07DiffListener(PropertyChangeListener l);

    public double get08Budget();
    public void set08Budget(double amount);
    public void add08BudgetListener(PropertyChangeListener l);
    public void remove08BudgetListener(PropertyChangeListener l);
    public double get08Actual();
    public void set08Actual(double amount);
    public void add08ActualListener(PropertyChangeListener l);
    public void remove08ActualListener(PropertyChangeListener l);
    public double get08Diff();
    public void add08DiffListener(PropertyChangeListener l);
    public void remove08DiffListener(PropertyChangeListener l);

    public double get09Budget();
    public void set09Budget(double amount);
    public void add09BudgetListener(PropertyChangeListener l);
    public void remove09BudgetListener(PropertyChangeListener l);
    public double get09Actual();
    public void set09Actual(double amount);
    public void add09ActualListener(PropertyChangeListener l);
    public void remove09ActualListener(PropertyChangeListener l);
    public double get09Diff();
    public void add09DiffListener(PropertyChangeListener l);
    public void remove09DiffListener(PropertyChangeListener l);

    public double get10Budget();
    public void set10Budget(double amount);
    public void add10BudgetListener(PropertyChangeListener l);
    public void remove10BudgetListener(PropertyChangeListener l);
    public double get10Actual();
    public void set10Actual(double amount);
    public void add10ActualListener(PropertyChangeListener l);
    public void remove10ActualListener(PropertyChangeListener l);
    public double get10Diff();
    public void add10DiffListener(PropertyChangeListener l);
    public void remove10DiffListener(PropertyChangeListener l);

    public double get11Budget();
    public void set11Budget(double amount);
    public void add11BudgetListener(PropertyChangeListener l);
    public void remove11BudgetListener(PropertyChangeListener l);
    public double get11Actual();
    public void set11Actual(double amount);
    public void add11ActualListener(PropertyChangeListener l);
    public void remove11ActualListener(PropertyChangeListener l);
    public double get11Diff();
    public void add11DiffListener(PropertyChangeListener l);
    public void remove11DiffListener(PropertyChangeListener l);

    public double get12Plan();
    public void set12Plan(double amount);
    public void add12PlanListener(PropertyChangeListener l);
    public void remove12PlanListener(PropertyChangeListener l);
    public double get12Actual();
    public void set12Actual(double amount);
    public void add12ActualListener(PropertyChangeListener l);
    public void remove12ActualListener(PropertyChangeListener l);
    public double get12Diff();
    public void add12DiffListener(PropertyChangeListener l);
    public void remove12DiffListener(PropertyChangeListener l);

}
