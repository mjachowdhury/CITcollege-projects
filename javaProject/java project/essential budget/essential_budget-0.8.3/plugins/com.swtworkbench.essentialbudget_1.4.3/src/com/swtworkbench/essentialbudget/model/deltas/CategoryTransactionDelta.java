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

import com.swtworkbench.essentialbudget.model.CategoryTransaction;

/**
 * Class CategoryTransactionDelta.  
 * 
 * @author daveo
 */
public class CategoryTransactionDelta {
    public CategoryTransactionDelta(CategoryTransaction parent, BigDecimal delta) {
        this.parent = parent;
        this.delta = delta;
    }
    public final CategoryTransaction parent;
    public final BigDecimal delta;
}
