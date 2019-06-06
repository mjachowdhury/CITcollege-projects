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
package com.swtworkbench.essentialbudget.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.masterdetail.IDetail;
import com.swtworkbench.ed.aware.masterdetail.IMaster;
import com.swtworkbench.ed.aware.masterdetail.IMasterObjectChangeListener;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeAdapter;
import com.swtworkbench.ed.aware.masterdetail.MasterObjectChangeEvent;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.essentialbudget.model.Account;
import com.swtworkbench.essentialbudget.model.AccountTransaction;
import com.swtworkbench.essentialbudget.model.Budget;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.essentialbudget.model.BudgetCategory;
import com.swtworkbench.essentialbudget.model.CategoryTransaction;
import com.swtworkbench.essentialbudget.model.deltas.AccountTransactionDelta;
import com.swtworkbench.essentialbudget.model.deltas.CategoryTransactionDelta;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class CategoryPicker.  Shows the relevent categories for the current AccountTransaction
 * and allows the user to add them to the current list of CategoryTransactions.
 * 
 * @author daveo
 */
public class CategoryPicker extends Composite implements IDetail {
    
    /**
     * Constructor CategoryPicker.  Create a CategoryPicker object
     * 
     * @param parent
     * @param style
     */
    public CategoryPicker(Composite parent, int style) {
        super(parent, SWT.NULL);
        setLayout(new FillLayout());
        theList = new List(this, style | SWT.V_SCROLL);
        theList.addKeyListener(keyListener);
        theList.addMouseListener(mouseListener);
    }
    
    private List theList;

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDetail#setMaster(com.swtworkbench.ed.aware.interfaces.IMaster)
     */
    public void setMaster(IMaster master) {
        if (this.master != null) {
            this.master.removeMasterObjectChangeListener(masterChangeListener);
        }
        this.master = master;
        master.addMasterObjectChangeListener(masterChangeListener);
    }
    
    protected IMaster master;
    
    private AccountTransaction input = null;
    
    private IMasterObjectChangeListener masterChangeListener = new MasterObjectChangeAdapter() {
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.aware.interfaces.IMasterObjectChangeListener#masterObjectChanged(com.swtworkbench.ed.controls.table.RowChangeEvent)
         */
        public void masterObjectChanged(MasterObjectChangeEvent e) {
            try {
                setInput(master.getMasterObject(e.newSelection.y));
            } catch (UnableToSaveException e1) {
                Logger.log().error(e1, "This should never be thrown");
            }
        }
    };

    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IDetail#setInput(java.lang.Object)
     */
    public void setInput(Object bean) throws UnableToSaveException {
        if (input != null) {
            input.removeAmountInListener(amountChangeListener);
            input.removeAmountOutListener(amountChangeListener);
            Model.getDefault().removeModelChangeListener(input.getSplits(), splitsDeletedListener);
            removeSplitChangedListener(input.getSplits());
        }
        input = (AccountTransaction) bean;
        if (input != null) {
            input.addAmountInListener(amountChangeListener);
            input.addAmountOutListener(amountChangeListener);
            Model.getDefault().addModelChangeListener(input.getSplits(), splitsDeletedListener);
            addSplitChangedListener(input.getSplits());
        }
        
        updateContents();
    }
    
    /**
     * Method addSplitChangedListener.  
     * @param list
     */
    private void addSplitChangedListener(LinkedList splits) {
        for (Iterator i = splits.iterator(); i.hasNext();) {
            CategoryTransaction ct = (CategoryTransaction) i.next();
            ct.addAmountListener(splitChangedListener);
        }
    }

    /**
     * Method removeSplitChangedListener.  
     * @param list
     */
    private void removeSplitChangedListener(LinkedList splits) {
        for (Iterator i = splits.iterator(); i.hasNext();) {
            CategoryTransaction ct = (CategoryTransaction) i.next();
            ct.removeAmountListener(splitChangedListener);
        }
    }

    private PropertyChangeListener splitChangedListener = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent e) {
            CategoryTransaction ct = (CategoryTransaction) e.getSource();
            BigDecimal oldValue = new BigDecimal(((Double)e.getOldValue()).doubleValue());
            BigDecimal newValue = new BigDecimal(((Double)e.getNewValue()).doubleValue());
            BigDecimal delta = newValue.subtract(oldValue);
            if (ct.getAssignedTo() instanceof BudgetCategory) {
                BudgetAppData.choreographer().requestl(
                    new CategoryTransactionDelta(
                        ct,
                        delta));
            } else {
                Account account = (Account)ct.getAssignedTo();
                if (ct.getParent().getAmountIn() != 0)
                    BudgetAppData.choreographer().requestl(
                        new AccountTransactionDelta(
                            ct.getParent(),
                            account,
                            delta.multiply(new BigDecimal(-1))));
                else
                    BudgetAppData.choreographer().requestl(
                        new AccountTransactionDelta(
                            ct.getParent(),
                            account,
                            delta));
            }
            ct.getParent().refreshUnbudgetedAmount();
        }
    };
    
    private IModelChangeListener splitsDeletedListener = new ModelChangeAdapter() {
        public void objectDeleted(
            Object container,
            int position,
            Object removed) 
        {
            CategoryTransaction ct = (CategoryTransaction) removed;
            if (ct.getAssignedTo() instanceof BudgetCategory)
                BudgetAppData.choreographer().requestl(
                    new CategoryTransactionDelta(
                        ct,
                        new BigDecimal(ct.getAmount()).multiply(
                            new BigDecimal(-1))));
            else {
                if (ct.getParent().getAmountOut() > 0)
                    BudgetAppData.choreographer().requestl(
                        new AccountTransactionDelta(
                            ct.getParent(),
                            (Account) ct.getAssignedTo(),
                            new BigDecimal(ct.getAmount()).multiply(new BigDecimal(-1))));
                else
                    BudgetAppData.choreographer().requestl(
                        new AccountTransactionDelta(
                            ct.getParent(),
                            (Account) ct.getAssignedTo(),
                            new BigDecimal(ct.getAmount())));
            }
            ct.getParent().refreshUnbudgetedAmount();
        }
    };
    
    private PropertyChangeListener amountChangeListener = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent e) {
            updateContents();
        }
    };
    
    int count;

    /**
     * Method updateContents.  Update the List's contents.
     */
    protected void updateContents() {
        BudgetAppData rootObject = BudgetAppData.getDefault();
        Budget current = rootObject.getBudget();
        theList.removeAll();
        count=0;
        
        if (input == null)
            return;
        
        if (input.getAmountIn() != 0 && input.getAmountOut() != 0)
            return;
            
        if (rootObject.isAssetAccount(input.getOwner())) {
            if (input.getAmountIn() != 0) {
                addCategories(current, true);               // Paycheck
                addAccounts(rootObject.getLiabilities());   // Cash advance
                addAccounts(rootObject.getAssets());        // Transfer from another acct
            } else if (input.getAmountOut() != 0) {
                addCategories(current, false);              // Bought something
                addAccounts(rootObject.getLiabilities());   // Paid back credit account
            }
        } else {
            if (input.getAmountIn() != 0) {
                addAccounts(rootObject.getAssets());        // Paid back credit account
            } else if (input.getAmountOut() != 0) {
                addCategories(current, false);              // Purchased on credit
                addAccounts(rootObject.getAssets());        // Cash advance
            }
        }
    }
    
    private void addCategories(Budget budget, boolean isInflow) {
        if (budget==null) return;
        
        LinkedList result;
        if (isInflow)
            result = budget.getInflowCategories();
        else
            result = budget.getOutflowCategories();
            
        for (Iterator i = result.iterator(); i.hasNext();) {
            BudgetCategory budgetCategory = (BudgetCategory) i.next();
            theList.add(budgetCategory.getName());
            theList.setData(Integer.toString(count), budgetCategory);
            ++count;
        }
    }
    
    private void addAccounts(LinkedList accounts) {
        for (Iterator i = accounts.iterator(); i.hasNext();) {
            Account account = (Account) i.next();
            if (account == input.getOwner())
                continue;
            theList.add(account.getName());
            theList.setData(Integer.toString(count), account);
            ++count;
        }
    }
    
    private KeyListener keyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if (e.character == '\r')
                addCurrentChoice();
        }
    };
    
    private MouseListener mouseListener = new MouseAdapter() {
        public void mouseDoubleClick(MouseEvent e) {
            addCurrentChoice();
        }
    };
    
    private double getNonzeroInputAmount() {
        double result = input.getAmountOut();
        if (result != 0)
            return result;
        else
            return input.getAmountIn();
    }
    
    protected void addCurrentChoice() {
        LinkedList splits = input.getSplits();
        NewObject newSplit = Model.getDefault().create(splits);
        CategoryTransaction newTrans = (CategoryTransaction) newSplit.newObject;
        newTrans.addAmountListener(splitChangedListener);
        Object referrer = theList.getData(Integer.toString(theList.getSelectionIndex()));
        newTrans.setAssignedTo(referrer);
        if (splits.size() == 1)
            newTrans.setAmount(getNonzeroInputAmount());
    }

}


