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

import com.swtworkbench.ed.aware.marshaller.MarshallException;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.ed.aware.validator.IValidator;
import com.swtworkbench.essentialbudget.model.deltas.AccountTransactionDateDelta;
import com.swtworkbench.essentialbudget.model.deltas.AccountTransactionDelta;
import com.swtworkbench.essentialbudget.model.typehandling.CurrencyValidator;
import com.swtworkbench.essentialbudget.model.typehandling.String2CurrencyMarshaller;

/**
 * Class AccountTransaction.  
 * 
 * @author daveo
 */
public class AccountTransaction implements Serializable {
    private static final long serialVersionUID = 0L;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructor AccountTransaction.  
     * 
     * @param account
     */
    public AccountTransaction(Account account) {
        owner = account;
        theThis = this;
    }
    
    protected final AccountTransaction theThis;
    
    private final Account owner;

    /**
     * Method getOwner.  
     * @return
     */
    public Account getOwner() {
        return owner;
    }
    
    private int checkNumber;
    
    /**
     * Method getCheckNumber.  
     * @return
     */
    public int getCheckNumber() {
        return checkNumber;
    }

    /**
     * Method setCheckNumber.  
     * @param i
     */
    public void setCheckNumber(int i) {
        int oldValue = checkNumber;
        checkNumber = i;
        pcs.firePropertyChange("CheckNumber", oldValue, checkNumber);
    }

    public void addCheckNumberListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("CheckNumber", l);
    }
    
    public void removeCheckNumberListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("CheckNumber", l);
    }
    
    private Date date = new Date();

    /**
     * Method getDate.  
     * @return
     */
    public Date getDate() {
        return date;
    }

    private boolean settingDate = false;

    /**
     * Method setDate.  
     * @param date
     */
    public void setDate(Date date) {
        if (settingDate) return;
        try {
            settingDate = true;
            Date oldValue = this.date;
            this.date = date;
            pcs.firePropertyChange("Date", oldValue, date);
            BudgetAppData.choreographer().requestl(new AccountTransactionDateDelta(this, owner, oldValue, date));
            if (oldValue.getDate() != date.getDate() ||
                oldValue.getMonth() != date.getMonth() ||
                oldValue.getYear() != date.getYear())
            {
                owner.resort(this);
            }
        } finally {
            settingDate = false;
        }
    }


    public void addDateListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("Date", l);
    }
    
    public void removeDateListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("Date", l);
    }
    
    private boolean cleared = false;
    
    /**
     * Method isCleared.  
     * @return
     */
    public boolean isCleared() {
        return cleared;
    }

    /**
     * Method setCleared.  
     * @param b
     */
    public void setCleared(boolean b) {
        boolean oldValue = cleared;
        cleared = b;
        pcs.firePropertyChange("Cleared", oldValue, cleared);
    }
    
    public void addClearedListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("Cleared", l);
    }
    
    public void removeClearedListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("Cleared", l);
    }
    
    private double amountIn = 0;

    /**
     * Method getAmountIn.  
     * @return
     */
    public double getAmountIn() {
        return amountIn;
    }

    /**
     * Method setAmountIn.  
     * @param f
     */
    public void setAmountIn(double newValue) {
        double oldUnbudgeted = getUnbudgeted();
        double oldValue = amountIn;
        BigDecimal delta = new BigDecimal(amountIn).subtract(new BigDecimal(newValue)).multiply(new BigDecimal(-1));
        amountIn = newValue;
        pcs.firePropertyChange("AmountIn", new Double(oldValue), new Double(amountIn));
        unbudgetedChangeSupport.firePropertyChange("prop", new Double(oldUnbudgeted), new Double(getUnbudgeted()));
        BudgetAppData.choreographer().requestl(new AccountTransactionDelta(this, owner, delta));
    }

    public void addAmountInListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("AmountIn", l);
    }
    
    public void removeAmountInListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("AmountIn", l);
    }
    
    public IValidator getAmountInValidator() {
        return new IValidator() {
            private final String HELP = "Only one of the 'amount in' or 'amount out' fields may contain a value";
            private String help;
            
            protected String2CurrencyMarshaller parser = new String2CurrencyMarshaller();
            private CurrencyValidator validator = new CurrencyValidator();
            
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#validatePartial(java.lang.String)
             */
            public boolean validatePartial(String value) {
                return validator.validatePartial(value);
            }
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#validateComplete(java.lang.String)
             */
            public boolean validateComplete(String value) {
                if (!validator.validateComplete(value))
                    return false;
                
                help=HELP;
                Double parsedValue;
                try {
                    parsedValue = (Double) parser.convert(value);
                } catch (MarshallException e) {
                    help = e.getCause().getMessage();
                    return false;
                }
                
                if (parsedValue.doubleValue() == 0)
                    return true;
                    
                if (getAmountOut() != 0)
                    return false;
                return true;
            }
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#getHelp()
             */
            public String getHelp() {
                return help;
            }
        };
    }
    
    private double amountOut = 0;
    
    /**
     * Method getAmountOut.  
     * @return
     */
    public double getAmountOut() {
        return amountOut;
    }

    /**
     * Method setAmountOut.  
     * @param f
     */
    public void setAmountOut(double newValue) {
        double oldUnbudgeted = getUnbudgeted();
        double oldValue = amountOut;
        BigDecimal delta = new BigDecimal(amountOut).subtract(new BigDecimal(newValue));
        amountOut = newValue;
        pcs.firePropertyChange("AmountOut", new Double(oldValue), new Double(amountOut));
        unbudgetedChangeSupport.firePropertyChange("prop", new Double(oldUnbudgeted), new Double(getUnbudgeted()));
        BudgetAppData.choreographer().requestl(new AccountTransactionDelta(this, owner, delta));
    }

    public void addAmountOutListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("AmountOut", l);
    }
    
    public void removeAmountOutListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("AmountOut", l);
    }
    
    public IValidator getAmountOutValidator() {
        return new IValidator() {
            private final String HELP = "Only one of the 'amount in' or 'amount out' fields may contain a value";
            private String help;
            
            private CurrencyValidator validator = new CurrencyValidator();
            protected String2CurrencyMarshaller parser = new String2CurrencyMarshaller();

            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#validatePartial(java.lang.String)
             */
            public boolean validatePartial(String value) {
                return validator.validatePartial(value);
            }
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#validateComplete(java.lang.String)
             */
            public boolean validateComplete(String value) {
                if (!validator.validateComplete(value))
                    return false;
                
                help=HELP;
                Double parsedValue;
                try {
                    parsedValue = (Double) parser.convert(value);
                } catch (MarshallException e) {
                    help = e.getCause().getMessage();
                    return false;
                }
                
                if (parsedValue.doubleValue() == 0)
                    return true;
                    
                if (getAmountIn() != 0)
                    return false;
                return true;
            }
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.validator.IValidator#getHelp()
             */
            public String getHelp() {
                return help;
            }
        };
    }
    
    private String description;
    

    /**
     * Method getDescription.  
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription.  
     * @param string
     */
    public void setDescription(String string) {
        String oldValue = description;
        description = string;
        pcs.firePropertyChange("Description", oldValue, description);
    }

    public void addDescriptionListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("Description", l);
    }
    
    public void removeDescriptionListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("Description", l);
    }
    
    // ==== Property Unbudgeted ====
    
    private double lastUnbudgeted;

    /**
     * Method getUnbudgeted.  Returns the current value of the Unbudgeted
     * property.
     * 
     * @return double
     */
    public double getUnbudgeted() {
        double toBudget = Math.max(amountIn, amountOut);
        BigDecimal splitsTotal = new BigDecimal(0);
        for (Iterator i = splits.iterator(); i.hasNext();) {
            CategoryTransaction categoryTransaction = (CategoryTransaction) i.next();
            splitsTotal = splitsTotal.add(new BigDecimal(categoryTransaction.getAmount()));
        }
        BigDecimal unbudgeted = new BigDecimal(toBudget).subtract(splitsTotal);
        lastUnbudgeted = unbudgeted.doubleValue();
        long temp = (long)(lastUnbudgeted * (double)100);
        lastUnbudgeted = ((double)temp)/100;
        return lastUnbudgeted;
    }
    
    private PropertyChangeSupport unbudgetedChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addUnbudgetedListener.  Add a property change listener to the
     * Unbudgeted property.
     */
    public void addUnbudgetedListener(PropertyChangeListener l) {
        unbudgetedChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addUnbudgetedListener.  Remove a property change listener from the
     * Unbudgeted property.
     */
    public void removeUnbudgetedListener(PropertyChangeListener l) {
        unbudgetedChangeSupport.removePropertyChangeListener("prop", l);
    }

    /**
     * Method refreshUnbudgetedAmount.  Called to force a refreshed calculation
     * of the unbudgeted amount.
     */
    public void refreshUnbudgetedAmount() {
        unbudgetedChangeSupport.firePropertyChange("prop", new Double(lastUnbudgeted), new Double(getUnbudgeted()));
    }
    
    // ==== Property Splits ====
    
    private LinkedList splits = new LinkedList();

    /**
     * Method getSplits.  
     * @return
     */
    public LinkedList getSplits() {
        return splits;
    }
    
    private class SplitsObjectFactory implements IObjectFactory, Serializable {
        /* (non-Javadoc)
         * @see com.swtworkbench.ed.aware.model.IObjectFactory#getNewObject(java.lang.Object)
         */
        public NewObject getNewObject(Object collection) {
            CategoryTransaction ct = new CategoryTransaction(theThis);
            LinkedList splits = (LinkedList) collection;
            splits.addLast(ct);
            return new NewObject(ct, splits.size()-1);
        }
    }
    
    private SplitsObjectFactory splitsObjectFactory = new SplitsObjectFactory();
    
    public IObjectFactory getSplitsObjectFactory() {
        if (splitsObjectFactory == null)
            splitsObjectFactory = new SplitsObjectFactory();
        return splitsObjectFactory;
    }
    
    /**
     * Method newSplit.  
     * @return
     */
    public CategoryTransaction newSplit() {
        CategoryTransaction split = new CategoryTransaction(this);
        splits.addLast(split);
        return split;
    }


}


