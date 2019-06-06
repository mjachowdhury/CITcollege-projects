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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.swtworkbench.ed.aware.adapters.pojo.POJOFieldAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.POJOObjectAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.interfaces.IDataAwareField;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.ed.aware.model.IModelChangeListener;
import com.swtworkbench.ed.aware.model.Model;
import com.swtworkbench.ed.aware.model.ModelChangeAdapter;
import com.swtworkbench.ed.aware.model.NewObject;
import com.swtworkbench.essentialbudget.EssentialBudget;
import com.swtworkbench.essentialbudget.model.Budget;
import com.swtworkbench.essentialbudget.model.BudgetAppData;
import com.swtworkbench.essentialbudget.model.BudgetCategory;
import com.swtworkbench.essentialbudget.model.BudgetItem;
import com.swtworkbench.essentialbudget.views.FocusPartHandler;
import com.swtworkbench.essentialbudget.views.PartCreationErrorPart;
import com.swtworkbench.rcplite.Application;
import com.swtworkbench.rcplite.actions.Action;
import com.swtworkbench.rcplite.actions.ActionManager;
import com.swtworkbench.rcplite.actions.MenuPos;
import com.swtworkbench.rcplite.views.View;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class BudgetsView.  
 * 
 * @author daveo
 */
public class BudgetsView extends View {
    
    public BudgetsView(Composite parent) {
        super(parent, 
            new Image(Display.getDefault(), EssentialBudget.class.getResourceAsStream("icons/budget_e.gif")), 
            "Budget");

        // Add some actions and action handlers to the UI
        ActionManager actionManager = Application.getDefault().getActionManager();
        Display display = Display.getDefault();
        actionManager.add(new Action("ViewBudget", 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/budget_e.gif")), 
                new Image(display, EssentialBudget.class.getResourceAsStream("icons/budget_g.gif")), 
                "View",
                new MenuPos("View",
                new MenuPos("ViewBudget", "&Budget"))));
        
        Application.choreographer().register(new FocusPartHandler(this, "ViewBudget"), Action.class);
    }
    
    protected Budget budget = null;

    /**
     * Method getBudget.  
     * @return
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Method setBudget.  
     * @param budget
     */
    public void setBudget(Budget budget) {
        if (this.budget != null) {
            Model.getDefault().removeModelChangeListener(this.budget.getInflowCategories(), changeListener);
            Model.getDefault().removeModelChangeListener(this.budget.getOutflowCategories(), changeListener);
        }
        this.budget = budget;
        Model.getDefault().addModelChangeListener(budget.getInflowCategories(), changeListener);
        Model.getDefault().addModelChangeListener(budget.getOutflowCategories(), changeListener);
        
        attachUI(budget);
    }

    /*
     * If categories are added/removed, refresh the budget UI
     */
    private IModelChangeListener changeListener = new ModelChangeAdapter() {
        public void objectCreated(Object container, NewObject newObject) {
            attachUI(budget);
        }
        public void objectDeleted(
            Object container,
            int position,
            Object removed) 
        {
            attachUI(budget);
        }
    };


    protected void attachUI(Budget budget) {
        // Build the categories list
        categories = new LinkedList();
        for (Iterator i = budget.getInflowCategories().iterator(); i.hasNext();) {
            BudgetCategory category = (BudgetCategory) i.next();
            categories.addLast(category);
        }
        for (Iterator i = budget.getOutflowCategories().iterator(); i.hasNext();) {
            BudgetCategory category = (BudgetCategory) i.next();
            categories.addLast(category);
        }
        try {
            if (!categories.isEmpty()) {
                setCurrentCategory((BudgetCategory)categories.getFirst());
                categoryListAdapter.setInput(this);
            }
        } catch (UnableToSaveException e) {
            Logger.log().error(e, "Unable to refresh input");
        }
        /*
         * FIXME: Not enough time to implement this, so commenting out
         */
//        budgetSummary.setInput(budget);
    }


    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
            "Aug", "Sep", "Oct", "Nov", "Dec", "Total"};

    // The row data
    private IRecordObjectAdapter[] rows = new IRecordObjectAdapter[13];
    
    // The categories
    protected LinkedList categories;
    private List categoryList;
    private IDataAwareField categoryListAdapter;
    
    // FIXME: Not enough time to implement; commenting out
//    private BudgetSummary budgetSummary;
    
    // ==== Property CurrentCategory ====

    private BudgetCategory currentCategory = null;

    /**
     * Method getCurrentCategoryValidValues.  Return the set of valid values
     * that can be stored in the currentCategory fields.
     * 
     * @return Object[] the list of valid BudgetCategory objects
     */
    public Object[] getCurrentCategoryValidValues() {
        return categories.toArray();
    }
    
    /**
     * Method getCurrentCategoryChoices.  
     * @return
     */
    public String[] getCurrentCategoryChoices() {
        String[] result = new String[categories.size()];
        int num=0;
        for (Iterator i = categories.iterator(); i.hasNext();) {
            BudgetCategory category = (BudgetCategory) i.next();
            result[num] = category.getName();
            ++num;
        }
        return result;
    }

    /**
     * Method getCurrentCategory.  Returns the current value of the CurrentCategory
     * property.
     * 
     * @return BudgetCategory 
     */
    public BudgetCategory getCurrentCategory() {
        return currentCategory;
    }

    /**
     * Method setCurrentCategory.  Sets the value of the CurrentCategory property.
     */
    public void setCurrentCategory(BudgetCategory newValue) {
        BudgetCategory oldValue = currentCategory;
        this.currentCategory = newValue;
        setCategoryName(newValue.getName());
        currentCategoryChangeSupport.firePropertyChange(
            "prop",
            oldValue,
            newValue);
    }

    private PropertyChangeSupport currentCategoryChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addCurrentCategoryListener.  Add a property change listener to the
     * CurrentCategory property.
     */
    public void addCurrentCategoryListener(PropertyChangeListener l) {
        currentCategoryChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addCurrentCategoryListener.  Remove a property change listener from the
     * CurrentCategory property.
     */
    public void removeCurrentCategoryListener(PropertyChangeListener l) {
        currentCategoryChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    
    // ==== Property CategoryName ====

    private String categoryName;

    /**
     * Method getCategoryName.  Returns the current value of the CategoryName
     * property.
     * 
     * @return String 
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Method setCategoryName.  Sets the value of the CategoryName property.
     */
    public void setCategoryName(String newValue) {
        String oldValue = categoryName;
        this.categoryName = newValue;
        categoryNameChangeSupport.firePropertyChange(
            "prop",
            oldValue,
            newValue);
    }

    private PropertyChangeSupport categoryNameChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Method addCategoryNameListener.  Add a property change listener to the
     * CategoryName property.
     */
    public void addCategoryNameListener(PropertyChangeListener l) {
        categoryNameChangeSupport.addPropertyChangeListener("prop", l);
    }

    /**
     * Method addCategoryNameListener.  Remove a property change listener from the
     * CategoryName property.
     */
    public void removeCategoryNameListener(PropertyChangeListener l) {
        categoryNameChangeSupport.removePropertyChangeListener("prop", l);
    }
    
    // --- Category changing ---
    
    protected PropertyChangeListener categoryChangeListener = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent e) {
            if (e.getNewValue() == budgetCategory)
                return;
                
            if (validateBudgetItems()) {
                refreshBudgetItems();
            } else {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        setCurrentCategory(budgetCategory);
                    }
                });
            }
        }
    };
    
    // --- Data validation ---
    
    protected BudgetCategory budgetCategory = null;
    
    /**
     * Method validateBudgetItems.  
     * @throws UnableToSaveException
     */
    protected boolean validateBudgetItems() {
        BudgetItem[] monthBudgets = currentCategory.getBudget();
        for (int monthNo=0; monthNo < monthBudgets.length; ++monthNo) {
            try {
                rows[monthNo].validateAllFields();
            } catch (UnableToSaveException e) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Method refreshBudgetItems.  
     * 
     */
    protected void refreshBudgetItems() {
        if (!categories.isEmpty()) {
            setCategoryName(currentCategory.getName());
            BudgetItem[] monthBudgets = currentCategory.getBudget();
            for (int monthNo=0; monthNo < monthBudgets.length; ++monthNo) {
                try {
                    rows[monthNo].setInput(monthBudgets[monthNo]);
                } catch (UnableToSaveException e) {
                    Logger.log().error(e, "Already validated this data: it should be able to save");
                }
            }
        }
        budgetCategory = currentCategory;
    }
    
    // --- Create the UI ---
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.demo.rcplite.views.Part#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    protected Composite createPartControl(Composite xswtParent) {
        Composite contents = null;
        try {
            contents = new Composite(xswtParent, SWT.NULL);
            GridData gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = true;
            gd.horizontalAlignment = 4;
            gd.verticalAlignment = 4;
            contents.setLayoutData(gd);
            GridLayout gl = new GridLayout();
            contents.setLayout(gl);
            
            // Page header
            Label headerSeparator = new Label(contents, SWT.NULL);
            gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalAlignment = 4;
            headerSeparator.setLayoutData(gd);
            
            // bodyHolder holds the body of the view plus the scroll bar 
            Composite bodyHolder = new Composite(contents, SWT.NULL);
            gd = new GridData();
            gd.horizontalSpan = 3;
            gd.horizontalAlignment = 2;
            bodyHolder.setLayoutData(gd);
            gl = new GridLayout();
            gl.numColumns = 3;
            gl.marginWidth = 0;
            gl.marginHeight = 0;
            gl.horizontalSpacing = 20;
            bodyHolder.setLayout(gl);

            // The categories list box
            Composite categoryComposite = new Composite(bodyHolder, SWT.NULL);
            gd = new GridData();
            gd.verticalAlignment = GridData.FILL;
            categoryComposite.setLayoutData(gd);
            gl = new GridLayout();
            gl.marginWidth = 0;
            gl.marginHeight = 0;
            gl.horizontalSpacing = 20;
            categoryComposite.setLayout(gl);

            new Label(categoryComposite, SWT.NULL).setText("Choose a Category");
            Composite categoryListHolder = new Composite(categoryComposite, SWT.NULL);
            gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalAlignment = GridData.FILL;
            gd.verticalAlignment = GridData.FILL;
            gd.grabExcessVerticalSpace = true;
            categoryListHolder.setLayoutData(gd);
            categoryListHolder.setLayout(new Layout() {
                /* (non-Javadoc)
				 * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
				 */
				protected Point computeSize(Composite composite, int wHint,
						int hHint, boolean flushCache) {
					return new Point(1, 1);
				}
                /* (non-Javadoc)
				 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
				 */
				protected void layout(Composite composite, boolean flushCache) {
					Control child = composite.getChildren()[0];
                    if (child != null) {
                        Rectangle parentBounds = composite.getBounds();
                        child.setBounds(0, 0, parentBounds.width, parentBounds.height);
                    }
				}
            });
            categoryList = new List(categoryListHolder, SWT.BORDER | SWT.V_SCROLL);
            categoryListAdapter = new POJOFieldAdapter(categoryList, "CurrentCategory", this);
            addCategoryNameListener(categoryChangeListener);

            /*
             * FIXME: Not enough time to implement this now, so commenting out...
             */
//            new Label(categoryComposite, SWT.NULL);
//            
//            budgetSummary = new BudgetSummary(categoryComposite, SWT.BORDER);
//            gd = new GridData();
//            gd.horizontalAlignment = GridData.FILL;
//            gd.verticalAlignment = GridData.FILL;
//            budgetSummary.setLayoutData(gd);

            Label label4 = new Label(bodyHolder, SWT.SEPARATOR | SWT.VERTICAL);
            gd = new GridData();
            gd.grabExcessVerticalSpace = true;
            gd.verticalAlignment = 4;
            label4.setLayoutData(gd);

            // The body of the view
            Composite body = new Composite(bodyHolder, SWT.NULL);
            gd = new GridData();
            gd.horizontalAlignment = 2;
            body.setLayoutData(gd);
            gl = new GridLayout();
            gl.numColumns = 4;
            gl.marginWidth = 10;
            gl.horizontalSpacing = 20;
            body.setLayout(gl);
            
            // Body Header
            Label categoryName = new Label(body, SWT.CENTER);
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.horizontalSpan = 4;
            gd.grabExcessHorizontalSpace = true;
            categoryName.setLayoutData(gd);
            new POJOFieldAdapter(categoryName, "CategoryName", this);

            Label spacer = new Label(body, SWT.CENTER);
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.horizontalSpan = 4;
            gd.grabExcessHorizontalSpace = true;
            spacer.setLayoutData(gd);
            spacer.setText(" ");

            new Label(body, SWT.NULL);
            Label label5 = new Label(body, SWT.RIGHT);
            label5.setText("Budget");
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.widthHint = 100;
            label5.setLayoutData(gd);
            Label label6 = new Label(body, SWT.RIGHT);
            label6.setText("Actual");
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.widthHint = 100;
            label6.setLayoutData(gd);
            Label label7 = new Label(body, SWT.RIGHT);
            label7.setText("Difference");
            gd = new GridData();
            gd.widthHint = 100;
            label7.setLayoutData(gd);
            
            // Body Rows
            for (int monthNum=0; monthNum < 12; ++monthNum) {
                rows[monthNum] = new POJOObjectAdapter();
                
                Label month = new Label(body, SWT.NULL);
                month.setText(months[monthNum]);
                gd = new GridData();
                gd.horizontalAlignment = 4;
                gd.widthHint = 50;
                month.setLayoutData(gd);
                
                Text budget = new Text(body, SWT.BORDER | SWT.RIGHT);
                gd = new GridData();
                gd.horizontalAlignment = 4;
                budget.setLayoutData(gd);
                rows[monthNum].add(new POJOFieldAdapter(budget, "Budget"));
                
                Label actual = new Label(body, SWT.RIGHT);
                gd = new GridData();
                gd.horizontalAlignment = 4;
                actual.setLayoutData(gd);
                rows[monthNum].add(new POJOFieldAdapter(actual, "Actual"));
                
                Label difference = new Label(body, SWT.RIGHT);
                gd = new GridData();
                gd.widthHint = 100;
                difference.setLayoutData(gd);
                rows[monthNum].add(new POJOFieldAdapter(difference, "Difference"));
            }
            
            // Body footer
            rows[12] = new POJOObjectAdapter();

            Label total = new Label(body, SWT.SEPARATOR | SWT.HORIZONTAL);
            gd = new GridData();
            gd.horizontalSpan = 4;
            gd.grabExcessHorizontalSpace = true;
            gd.horizontalAlignment = 4;
            total.setLayoutData(gd);
            
            Label label9 = new Label(body, SWT.NULL);
            label9.setText("Total");
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.widthHint = 50;
            label9.setLayoutData(gd);
            
            Label totalBudget = new Label(body, SWT.RIGHT);
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.widthHint = 100;
            totalBudget.setLayoutData(gd);
            rows[12].add(new POJOFieldAdapter(totalBudget, "Budget"));
            
            Label totalActual = new Label(body, SWT.RIGHT);
            gd = new GridData();
            gd.horizontalAlignment = 4;
            gd.widthHint = 100;
            totalActual.setLayoutData(gd);
            rows[12].add(new POJOFieldAdapter(totalActual, "Actual"));
            
            Label totalDifference = new Label(body, SWT.RIGHT);
            gd = new GridData();
            gd.widthHint = 100;
            totalDifference.setLayoutData(gd);
            rows[12].add(new POJOFieldAdapter(totalDifference, "Difference"));
            
            // Hook up the UI to the data...
            setBudget(BudgetAppData.getDefault().getBudget());
            setText(getBudget().getYear() + " Budget");
            
        } catch (Exception e) {
            Logger.log().error(e, "Error creating layout.");
            if (contents != null) {
                contents.dispose();
            }
            contents = new PartCreationErrorPart(parent, SWT.NULL);
        }
        return contents;
    }

}
