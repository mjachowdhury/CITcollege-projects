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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.ed.aware.adapters.pojo.POJOFieldAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.POJOObjectAdapter;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.controls.mrotable.IPartControlFactory;
import com.swtworkbench.ed.controls.mrotable.MROTable;
import com.swtworkbench.ed.controls.mrotable.TableLayout;
import com.swtworkbench.essentialbudget.EssentialBudget;
import com.swtworkbench.essentialbudget.model.Account;
import com.swtworkbench.rcplite.views.ViewFolder;
import com.swtworkbench.rcplite.views.ViewFolderPage;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class TransactionRegister.  An instance of TransactionRegister shows the
 * transactions for a particular account inside the TransactionView
 * 
 * @author daveo
 */
public class TransactionRegister extends ViewFolderPage {
    
    /**
     * Constructor TransactionRegister.  
     * 
     * @param parent
     * @param icon
     * @param title
     */
    public TransactionRegister(ViewFolder parent, Account account) {
        super(parent, new Image(Display.getDefault(), 
            EssentialBudget.class.getResourceAsStream("icons/accounts_e.gif")), 
            account.getName());
        this.account = account;
        account.addNameListener(nameListener);
    }
    

    /*
     * Update the tab when the account name changes
     */
    private PropertyChangeListener nameListener = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent nameChangeEvent) {
            setText((String)nameChangeEvent.getNewValue());
        }
    };

    private Account account;

    /**
     * Method getAccount.  
     * @return
     */
    public Account getAccount() {
        return account;
    }
    
    MROTable transactionLog = null;

    private Color white = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
    private Color darkgray = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
    
    // ---- The Transaction Register -------------------------------------------
    
    private int[] getTransactionRegisterColumnWeights() {
        return new int[] {5, 10, 3, 52, 10, 10, 10,};
    }
    
    private class TransactionRegisterHeaderFactory implements IPartControlFactory {
		public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            part.setLayout(new TableLayout(
							getTransactionRegisterColumnWeights(), SWT.CENTER,
							darkgray));

            new Label(part, SWT.RIGHT).setText("Num");
            new Label(part, SWT.NULL).setText("Date");
            new Label(part, SWT.CENTER).setText("X");
            new Label(part, SWT.NULL).setText("Description");
            new Label(part, SWT.RIGHT).setText("Inflow");
            new Label(part, SWT.RIGHT).setText("Outflow");
            new Label(part, SWT.RIGHT).setText("Unbudgeted");

            return part;
		}
    }
    
    private class TransactionRegisterRowFactory implements IPartControlFactory {
        public Composite createPartControl(Composite parent) {
            Composite part = new Composite(parent, SWT.NULL);
            part.setBackground(white);
            part.setLayout(new TableLayout(getTransactionRegisterColumnWeights(), SWT.CENTER | SWT.BORDER));
            
            Text text = new Text(part, SWT.RIGHT);
            text.setData("boundTo", "CheckNumber");

            text = new Text(part, SWT.NULL);
            text.setData("boundTo", "Date");
            
            Button checkbox = new Button(part, SWT.CHECK);
            checkbox.setBackground(part.getBackground());
            checkbox.setData("boundTo", "Cleared");
            
            text = new Text(part, SWT.NULL);
            text.setData("boundTo", "Description");
            
            text = new Text(part, SWT.RIGHT);
            text.setData("boundTo", "AmountIn");
            
            text = new Text(part, SWT.RIGHT);
            text.setData("boundTo", "AmountOut");
            
            Label label = new Label(part, SWT.RIGHT);
            label.setBackground(part.getBackground());
            label.setData("boundTo", "Unbudgeted");

            return part;
        }
    }

    private Composite createTable(Composite parent, Object input, String property) {
        Composite contents = null;
        try {
            contents = new Composite(parent, SWT.NULL);
            contents.setLayout(new GridLayout(1, true));
            
            // Make the actual table
            transactionLog = new MROTable(contents, SWT.BORDER);
            transactionLog.setHelpText("Click to highlight; [INS] to insert a row; [Ctrl-DEL] to delete a row");
            transactionLog.setLayoutData(new GridData(GridData.FILL_BOTH));
            transactionLog.setHeaderFactory(new TransactionRegisterHeaderFactory());
            transactionLog.getControl().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
            try {
                transactionLog.edit(input, property, new TransactionRegisterRowFactory());
            } catch (Exception e) {
                Logger.log().error(e, "Error creating MROTable");
            }
            
            return contents;

        } catch (Exception e) {
            Logger.log().error(e, "Error creating MROTable");
            if (contents != null) {
                contents.dispose();
            }
            contents = new PartCreationErrorPart(parent, SWT.NULL);
            return contents;
        }
    }

    // ---- The Splits Area ----------------------------------------------------

    private int[] getSplitsColumnWeights() {
        return new int[] {40, 45, 15};
    }
    
    private Composite createSplitsArea(Composite parent) {
        Composite contents=null;
        try {
            // The contents Composite
            contents = new Composite(parent, SWT.NULL);
            contents.setLayoutData(new GridData(GridData.FILL_BOTH));
            GridLayout gl = new GridLayout();
            gl.marginHeight = 0;
            gl.marginWidth = 0;
            gl.numColumns = 1;
            contents.setLayout(gl);
            
            // The transaction area
            Composite transactionArea = new Composite(contents, SWT.NULL);
            transactionArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            transactionArea.setLayout(new GridLayout(2, false));
            Label date = new Label(transactionArea, SWT.NULL);
            GridData gd = new GridData();
            gd.widthHint = 80;
            date.setLayoutData(gd);
            Label description = new Label(transactionArea, SWT.NULL);
            description.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

            // The splits area
            SashForm splitsSash = new SashForm(contents, SWT.HORIZONTAL);
            gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = true;
            gd.horizontalAlignment = 4;
            gd.verticalAlignment = 4;
            splitsSash.setLayoutData(gd);
            
            // The category pane
            Composite categoryPane = new Composite(splitsSash, SWT.NULL);
            categoryPane.setLayout(new GridLayout());
            
            Label prompt = new Label(categoryPane, SWT.NULL);
            prompt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            prompt.setText("Double-click a category or account to add it to this transaction");
            
            CategoryPicker categoryPicker = new CategoryPicker(categoryPane, SWT.BORDER);
            gd = new GridData();
            gd.grabExcessVerticalSpace = true;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalAlignment = 4;
            gd.verticalAlignment = 4;
            categoryPicker.setLayoutData(gd);
            
            // The splits pane
            Composite splitsPane = new Composite(splitsSash, SWT.NULL);
            splitsPane.setLayout(new GridLayout());
            
            prompt = new Label(splitsPane, SWT.NULL);
            prompt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            prompt.setText("Categories/Accounts applying to this transaction");
            
            // ---- The splits table -------------------------------------------
            
            // Splits table header
            IPartControlFactory splitsTableHeaderFactory = new IPartControlFactory() {
				public Composite createPartControl(Composite parent) {
                    Composite part = new Composite(parent, SWT.NULL);
                    part.setLayout(new TableLayout(getSplitsColumnWeights(), SWT.CENTER, darkgray));
                    
                    new Label(part, SWT.NULL).setText("Category/Account");
                    new Label(part, SWT.NULL).setText("Description");
                    new Label(part, SWT.RIGHT).setText("Amount");
                    return part;
				}
            };
            
            // Splits table rows
            IPartControlFactory splitsTableRowFactory = new IPartControlFactory() {
                public Composite createPartControl(Composite parent) {
                    Composite part = new Composite(parent, SWT.NULL);
                    part.setBackground(white);
                    part.setLayout(new TableLayout(getSplitsColumnWeights(), SWT.CENTER | SWT.BORDER));
                    
                    Label category = new Label(part, SWT.CHECK);
                    category.setBackground(part.getBackground());
                    category.setData("boundTo", "AssignedTo");

                    Text splitDescription = new Text(part, SWT.NULL);
                    splitDescription.setData("boundTo", "Description");
                    
                    Text amount = new Text(part, SWT.RIGHT);
                    amount.setData("boundTo", "Amount");
                    
                    return part;
                }
            };
            
            MROTable categoryTransactions = new MROTable(splitsPane, SWT.BORDER | SWT.MULTI);
            categoryTransactions.setHelpText("Click to highlight; [Ctrl-DEL] to delete a row");
            categoryTransactions.setLayoutData(new GridData(GridData.FILL_BOTH));
            categoryTransactions.setHeaderFactory(splitsTableHeaderFactory);
            categoryTransactions.setRowFactory(splitsTableRowFactory);
            categoryTransactions.setInsertEnabled(false);
            
            splitsSash.setWeights(new int[]{40, 60});
            
            /*
             * Now hook up the data model
             */
             
            // The master row objects...
            IRecordObjectAdapter masterRow = new POJOObjectAdapter();
            masterRow.add(new POJOFieldAdapter(date, "Date"));
            masterRow.add(new POJOFieldAdapter(description, "Description"));
            masterRow.setMaster(transactionLog);
            
            // The category picker...
            categoryPicker.setMaster(transactionLog);
            
            // The splits area...
            categoryTransactions.setInputProperty("Splits");
            categoryTransactions.setMaster(transactionLog);
            
        } catch (Exception e) {
            Logger.log().error(e, "Error creating Splits area");
            if (!contents.isDisposed())
                contents.dispose();
            contents = new PartCreationErrorPart(parent, SWT.NULL);
        }
        return contents;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.ViewFolder#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    protected Composite createPartControl(Composite parent) {
        SashForm contents = new SashForm(parent, SWT.VERTICAL);
        createTable(contents, account, "Transactions");
        createSplitsArea(contents);
        contents.setWeights(new int[]{66, 33});
        
        return contents;
    }

}


