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
package com.swtworkbench.essentialbudget.model.deltas;

import java.math.BigDecimal;

import com.swtworkbench.essentialbudget.model.BudgetCategory;
import com.swtworkbench.essentialbudget.model.BudgetItem;

/**
 * Class BudgetItemDelta.  
 * 
 * @author daveo
 */
public class BudgetItemDelta {
    
    public BudgetItemDelta(BudgetItem budgetItem, BudgetCategory budgetCategory, BigDecimal delta) {
        this.budgetItem = budgetItem;
        this.owner = budgetCategory;
        this.delta = delta;
    }
    
    public final BudgetItem budgetItem;
    public final BudgetCategory owner;
    public final BigDecimal delta;
}

