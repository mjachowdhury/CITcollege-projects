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
import java.util.LinkedList;
import java.util.ListIterator;

import com.swtworkbench.bus.IActor;
import com.swtworkbench.bus.Request;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.essentialbudget.model.deltas.AccountTransactionDelta;

/**
 * Class Account.  
 * 
 * @author daveo
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 0L;
    
    private final Account theThis;
    protected Account getTheThis() {
        return theThis;
    }
    
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public String name = "";
    
    public Account() {
        BudgetAppData.choreographer().register(new AccountListener(this), AccountTransactionDelta.class);
        theThis = this;
    }
    
    class AccountListener implements IActor, Serializable {
        public AccountListener(Account owner) {
            this.owner = owner;
        }
        private final Account owner;
        public Object perform(Request r) throws Exception {
            AccountTransactionDelta delta = (AccountTransactionDelta) r.action;
            if (delta.owner == owner)
                updateBalance(delta.delta);
            return null;
        }
    }
    
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
    
    private double balance = 0;
    
    /**
     * Method getBalance.  
     * @return
     */
    public double getBalance() {
        return balance;
    }
    
    public void addBalanceListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("Balance", l);
    }
    
    public void removeBalanceListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("Balance", l);
    }
    
    /**
     * Method updateBalance.  Adds delta amount to the current balance.
     * @param delta The change in the account balance.
     */
    protected void updateBalance(BigDecimal delta) {
        double oldBalance = balance;
        if (BudgetAppData.getDefault().isAssetAccount(this)) {
            balance = delta.add(new BigDecimal(balance)).doubleValue();
        } else {
            delta = delta.multiply(new BigDecimal(-1));
            balance = delta.add(new BigDecimal(balance)).doubleValue();
        }
        pcs.firePropertyChange("Balance", new Double(oldBalance), new Double(balance));
    }

    protected LinkedList transactions = new LinkedList();
    
    /**
     * Method getTransactions.  
     * @return
     */
    public LinkedList getTransactions() {
        return transactions;
    }
    
    class TransactionFactory implements IObjectFactory, Serializable {
        public NewObject getNewObject(Object collection) {
            AccountTransaction result = new AccountTransaction(getTheThis());
            
            // Find the transaction's new location and add it
            int position=0;
            Date toResortDate = result.getDate();
            for (ListIterator i = transactions.listIterator(); i.hasNext();) {
                AccountTransaction element = (AccountTransaction) i.next();
                if (toResortDate.before(element.getDate())) {
                    i.previous();
                    i.add(result);
                    return new NewObject(result, position);
                }
                ++position;
            }

            transactions.addLast(result);
            return new NewObject(result, position);
        }
    }
    
    
    /**
     * Method getTransactionsObjectFactory.  Returns the object factory for
     * transactions...
     * 
     * @return
     */
    public IObjectFactory getTransactionsObjectFactory() {
        return new TransactionFactory();
    }
    
    /**
     * Method getTransactionsChangeListener.  
     * @return
     */
    public IModelChangeListener getTransactionsChangeListener() {
        return new ModelChangeAdapter() {
            public void objectDeleted(
                Object container,
                int position,
                Object removed) 
            {
                AccountTransaction t = (AccountTransaction) removed;
                t.setAmountIn(0);
                t.setAmountOut(0);
                
                LinkedList splits = t.getSplits();
                int p=0;
                for (Iterator i = t.getSplits().iterator(); i.hasNext();) {
                    CategoryTransaction ct = (CategoryTransaction) i.next();
                    Model.getDefault().fireObjectDeletedEvent(splits, p, ct);
                    ++p;
                }
            }
        };
    }
    
    /**
     * Method newTransaction.  Appends a new AccountTransaction to this account.
     * @return
     */
    public NewObject newTransaction() {
        return Model.getDefault().create(transactions);
    }

    /**
     * Method resort.  The specified AccountTransaction's date just changed
     * and it needs to be sorted to a new position in the Transactions
     * list.
     * 
     * @param transaction The AccountTransaction to re-sort.
     */
    public void resort(AccountTransaction toResort) {
//        // If this is the only element, no resorting is needed
//        if (transactions.size() == 1)
//            return;
//        
//        // Find the transaction and remove it
//        int position=0;
//        for (Iterator i = transactions.iterator(); i.hasNext();) {
//            AccountTransaction element = (AccountTransaction) i.next();
//            if (element == toResort) {
//                i.remove();
//                Model.getDefault().fireObjectDeletedEvent(transactions, position, toResort);
//                break;
//            }
//            ++position;
//        }
//        
//        // Find the transaction's new location and add it back
//        position=0;
//        Date toResortDate = toResort.getDate();
//        for (ListIterator i = transactions.listIterator(); i.hasNext();) {
//            AccountTransaction element = (AccountTransaction) i.next();
//            if (toResortDate.before(element.getDate())) {
//                i.previous();
//                i.add(toResort);
//                Model.getDefault().fireObjectCreatedEvent(transactions, new NewObject(toResort, position));
//                return;
//            }
//            ++position;
//        }
//        transactions.addLast(toResort);
//        Model.getDefault().fireObjectCreatedEvent(transactions, new NewObject(toResort, position));
    }

}

