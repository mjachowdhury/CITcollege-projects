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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import com.swtworkbench.ed.aware.marshaller.DataMarshallerRegistry;
import com.swtworkbench.ed.aware.messages.MessageSource;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.IObjectFactory;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.ed.aware.validator.ValidatorRegistry;
import com.swtworkbench.bus.Choreographer;
import com.swtworkbench.essentialbudget.model.typehandling.Account2StringMarshaller;
import com.swtworkbench.essentialbudget.model.typehandling.BudgetCategory2StringMarshaller;
import com.swtworkbench.essentialbudget.model.typehandling.Currency2StringMarshaller;
import com.swtworkbench.essentialbudget.model.typehandling.CurrencyValidator;
import com.swtworkbench.essentialbudget.model.typehandling.Date2StringMarshaller;
import com.swtworkbench.essentialbudget.model.typehandling.DateValidator;
import com.swtworkbench.essentialbudget.model.typehandling.String2CurrencyMarshaller;
import com.swtworkbench.essentialbudget.model.typehandling.String2DateMarshaller;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class BudgetAppData.  The Budget app's data.
 * 
 * @author daveo
 */
public class BudgetAppData implements Serializable {
    private static final long serialVersionUID = 0L;
    
    private static BudgetAppData singleton = null;
    
    private static Choreographer choreographer = null;

    private static void newSingleton() {
        singleton = new BudgetAppData();
        choreographer = new Choreographer();
    }

    private static final String dataFile = System.getProperty("user.dir") + "/budgetData.bdg";

    public static Choreographer choreographer() {
        if (choreographer != null)
            return choreographer;
        else
            throw new IllegalArgumentException("The model's communication's bus has not yet been created!");
    }
    
    public static BudgetAppData getDefault() {
        if (singleton == null) {
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(dataFile));
                singleton = (BudgetAppData)is.readObject();
                choreographer = (Choreographer) is.readObject();
                is.close();
            } catch (FileNotFoundException e) {
                newSingleton();
            } catch (IOException e) {
                newSingleton();
            } catch (ClassNotFoundException e) {
                newSingleton();
            }

            // Since these are internationalized/localized, we define them at
            // the application layer.
            DataMarshallerRegistry.registerMarshaller(Date.class, String.class, new String2DateMarshaller());
            DataMarshallerRegistry.registerMarshaller(String.class, Date.class, new Date2StringMarshaller());
            ValidatorRegistry.registerValidator(Date.class, new DateValidator());
            
            // Same for currency; we'll treat all doubles as currency, 
            // but you can use any data type (even your own) for this.
            DataMarshallerRegistry.registerMarshaller(Double.TYPE, String.class, new String2CurrencyMarshaller());
            DataMarshallerRegistry.registerMarshaller(String.class, Double.TYPE, new Currency2StringMarshaller());
            ValidatorRegistry.registerValidator(Double.TYPE, new CurrencyValidator());
            
            // Some read-only marshallers for our internal object types...
            DataMarshallerRegistry.registerMarshaller(String.class, Account.class, new Account2StringMarshaller());
            DataMarshallerRegistry.registerMarshaller(String.class, BudgetCategory.class, new BudgetCategory2StringMarshaller());
        }
        return singleton;
    }
    
    public static void save() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dataFile));
            os.writeObject(singleton);
            os.writeObject(choreographer);
            os.close();
        } catch (IOException e) {
            Logger.log().error(e, "Unable to save!");
        }
    }
    
    public BudgetAppData() {
        BudgetAppData.singleton = this;
        Budget defaultBudget = new Budget();
        Date today = new Date();
        int realYear = today.getYear()-100+2000;
        defaultBudget.setYear(realYear);
        budgets.addLast(defaultBudget);
    }
    
    private LinkedList assets = new LinkedList();
    private LinkedList liabilities = new LinkedList();
    private IObjectFactory accountObjectFactory = new LinkedListObjectFactory(Account.class);
    
    /**
     * Method getAssets.  
     * @return
     */
    public LinkedList getAssets() {
        return assets;
    }
    
    /**
     * Method getAssetsObjectFactory.  Returns an object factory for the Assets
     * collection.  This method is called automatically by Essential Data
     * 
     * @return IObjectFactory
     */
    public IObjectFactory getAssetsObjectFactory() {
        return accountObjectFactory;
    }
    
    /**
     * Method getAssetsChangeListener.  Returns a change listener that implements
     * our deletion business model.
     * 
     * @return
     */
    public IModelChangeListener getAssetsChangeListener() {
        return new ModelChangeAdapter() {
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.model.ModelChangeAdapter#checkObjectDeletion(java.lang.Object, int, java.lang.Object)
             */
            public boolean checkObjectDeletion(
                Object container,
                int selection,
                Object toDelete) 
            {
                Account account = (Account) toDelete;
                if (account.getTransactions().isEmpty())
                    return true;
                else {
                    MessageSource.getDefault().sendMessage("Can't delete an account that has transactions");
                    return false;
                }
            }
        };
    }
    
    /**
     * Method newAccount.  Creates a new Account and returns it...  In this
     * application, we know that this will always work because the Assets
     * LinkedList is always being edited in the UI.
     * 
     * @return NewObject the new Account object wrapped in a NewObject
     * @throws IllegalArgumentException
     */
    public NewObject newAsset() {
        return Model.getDefault().create(assets);
    }
    
    /**
     * Method isAssetAccount.  Returns if the specified object is an asset account
     * object.  If this method returns false, then the specified account 
     * is a liability account object.
     * 
     * @param account The account to test
     * @return true if account is an asset; false if it is a liability.
     */
    public boolean isAssetAccount(Account account) {
        for (Iterator i = assets.iterator(); i.hasNext();) {
            Account toCheck = (Account) i.next();
            if (account == toCheck)
                return true;
        }
        return false;
    }
    
    /**
     * Method getLiabilities.  
     * @return
     */
    public LinkedList getLiabilities() {
        return liabilities;
    }
    
    /**
     * Method getLiabilitiesObjectFactory.  Returns an object factory for the
     * Liabilities collection.  This method is called automatically by Essential
     * Data.
     * 
     * @return IObjectFactory
     */
    public IObjectFactory getLiabilitiesObjectFactory() {
        return accountObjectFactory;
    }
    
    /**
     * Method getLiabilitiesChangeListener.  
     * @return
     */
    public IModelChangeListener getLiabilitiesChangeListener() {
        return new ModelChangeAdapter() {
            public boolean checkObjectDeletion(
                Object container,
                int selection,
                Object toDelete) 
            {
                Account account = (Account) toDelete;
                if (account.getTransactions().isEmpty())
                    return true;
                else {
                    MessageSource.getDefault().sendMessage("Can't delete an account that has transactions");
                    return false;
                }
            }
        };
    }

    /**
     * Method newLiability.  
     * @return
     */
    public NewObject newLiability() {
        return Model.getDefault().create(liabilities);
    }
    
    private LinkedList budgets = new LinkedList();
    private IObjectFactory budgetObjectFactory = new LinkedListObjectFactory(Budget.class);
    
    /**
     * Method getAssets.  
     * @return
     */
    public LinkedList getBudgets() {
        return budgets;
    }
    
    /**
     * Method getBudgetsObjectFactory.  Returns an object factory for the Assets
     * collection.  This method is called automatically by Essential Data
     * 
     * @return IObjectFactory
     */
    public IObjectFactory getBudgetsObjectFactory() {
        return budgetObjectFactory;
    }
    
    /**
     * Method getBudgetsChangeListener.  Returns a change listener that implements
     * our deletion business model.
     * 
     * @return IModelChangeListener
     */
    public IModelChangeListener getBudgetsChangeListener() {
        return new ModelChangeAdapter() {
            /* (non-Javadoc)
             * @see com.swtworkbench.ed.aware.model.ModelChangeAdapter#checkObjectDeletion(java.lang.Object, int, java.lang.Object)
             */
            public boolean checkObjectDeletion(
                Object container,
                int selection,
                Object toDelete) 
            {
                // It's never okay to delete a budget
                return false;
            }
        };
    }
    
    /**
     * Method getBudget.  Returns the Budget for a specified year or null if
     * none exists for that year.
     * 
     * @param year The year for which to request a budget.
     * @return The Budget for that year or null if none.
     */
    public Budget getBudget(int year) {
        for (Iterator i = budgets.iterator(); i.hasNext();) {
            Budget budget = (Budget) i.next();
            if (budget.getYear() == year)
                return budget;
        }
        return null;
    }
    
    /**
     * Method getBudget.  Returns the active budget.  Currently this is defined
     * as the most recent Budget.  Returns null if none exists.
     * 
     * FIXME: Need to better-define the concepts of the "current" budget ie:
     * the one against which transactions are logged versus historical or 
     * "what-if" budgets...
     * 
     * @return The most recent budget or null if none.
     */
    public Budget getBudget() {
        if (budgets.size() == 0)
            return null;
        Budget result = null;
        for (Iterator i = budgets.iterator(); i.hasNext();) {
            Budget budget = (Budget) i.next();
            if (result == null) {
                result = budget;
                continue;
            }
            if (budget.getYear() > result.getYear()) {
                result = budget;
            }
        }
        return result;
    }
    
}



