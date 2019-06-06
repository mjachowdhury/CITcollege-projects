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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.swtworkbench.ed.aware.adapters.pojo.POJOFieldAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.POJOObjectAdapter;
import com.swtworkbench.ed.aware.adapters.pojo.UnableToSaveException;
import com.swtworkbench.ed.aware.interfaces.IRecordObjectAdapter;
import com.swtworkbench.essentialbudget.model.Budget;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class BudgetSummary.  
 * 
 * @author daveo
 */
public class BudgetSummary extends Composite {
    
    /**
     * Constructor BudgetSummary.  
     * 
     * @param parent
     * @param style
     */
    public BudgetSummary(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout());
        
        budgetAdapter = new POJOObjectAdapter();
        
        Composite contents = new Composite(this, SWT.NULL);
        GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = 4;
        gridData1.verticalAlignment = 4;
        contents.setLayoutData(gridData1);
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 4;
        gridLayout1.marginHeight = 5;
        gridLayout1.marginWidth = 5;
        contents.setLayout(gridLayout1);
        Label label1 = new Label(contents, SWT.CENTER);
        label1.setText("Budget Summary");
        GridData gridData2 = new GridData();
        gridData2.horizontalSpan = 4;
        gridData2.grabExcessHorizontalSpace = true;
        gridData2.grabExcessVerticalSpace = true;
        gridData2.horizontalAlignment = 4;
        gridData2.verticalAlignment = 4;
        label1.setLayoutData(gridData2);
        Label label2 = new Label(contents, SWT.CENTER);
        GridData gridData3 = new GridData();
        gridData3.horizontalSpan = 4;
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.grabExcessVerticalSpace = true;
        gridData3.horizontalAlignment = 4;
        gridData3.verticalAlignment = 4;
        label2.setLayoutData(gridData3);
        new Label(contents, SWT.NULL);
        Label label4 = new Label(contents, SWT.RIGHT);
        label4.setText("Budget");
        GridData gridData4 = new GridData();
        gridData4.widthHint = 75;
        label4.setLayoutData(gridData4);
        Label label5 = new Label(contents, SWT.RIGHT);
        label5.setText("Actual");
        GridData gridData5 = new GridData();
        gridData5.widthHint = 75;
        label5.setLayoutData(gridData5);
        Label label6 = new Label(contents, SWT.RIGHT);
        label6.setText("Difference");
        GridData gridData6 = new GridData();
        gridData6.widthHint = 75;
        label6.setLayoutData(gridData6);
        Label label7 = new Label(contents, SWT.NULL);
        label7.setText("Current Inflow");
        
        Label ytdInflowBudget = new Label(contents, SWT.RIGHT);
        GridData gridData7 = new GridData();
        gridData7.widthHint = 75;
        ytdInflowBudget.setLayoutData(gridData7);
        budgetAdapter.add(new POJOFieldAdapter(ytdInflowBudget, "YTDInflowBudget"));
        
        Label ytdInflowActual = new Label(contents, SWT.RIGHT);
        GridData gridData8 = new GridData();
        gridData8.widthHint = 75;
        ytdInflowActual.setLayoutData(gridData8);
        budgetAdapter.add(new POJOFieldAdapter(ytdInflowActual, "YTDInflowActual"));
        
        Label ytdInflowDifference = new Label(contents, SWT.RIGHT);
        GridData gridData9 = new GridData();
        gridData9.widthHint = 75;
        ytdInflowDifference.setLayoutData(gridData9);
        budgetAdapter.add(new POJOFieldAdapter(ytdInflowDifference, "YTDInflowDifference"));
        
        Label label8 = new Label(contents, SWT.NULL);
        label8.setText("Current Outflow");

        Label ytdOutflowBudget = new Label(contents, SWT.RIGHT);
        GridData gridData10 = new GridData();
        gridData10.widthHint = 75;
        ytdOutflowBudget.setLayoutData(gridData10);
        budgetAdapter.add(new POJOFieldAdapter(ytdOutflowBudget, "YTDOutflowBudget"));

        Label ytdOutflowActual = new Label(contents, SWT.RIGHT);
        GridData gridData11 = new GridData();
        gridData11.widthHint = 75;
        ytdOutflowActual.setLayoutData(gridData11);
        budgetAdapter.add(new POJOFieldAdapter(ytdOutflowActual, "YTDOutflowActual"));
        
        Label ytdOutflowDifference = new Label(contents, SWT.RIGHT);
        GridData gridData12 = new GridData();
        gridData12.widthHint = 75;
        ytdOutflowDifference.setLayoutData(gridData12);
        budgetAdapter.add(new POJOFieldAdapter(ytdOutflowDifference, "YTDOutflowDifference"));

        Label label9 = new Label(contents, SWT.NULL);
        label9.setText("Current Difference");

        Label ytdDifferenceBudget = new Label(contents, SWT.RIGHT);
        GridData gridData13 = new GridData();
        gridData13.widthHint = 75;
        ytdDifferenceBudget.setLayoutData(gridData13);
        budgetAdapter.add(new POJOFieldAdapter(ytdDifferenceBudget, "YTDDifferenceBudget"));

        Label ytdDifferenceActual = new Label(contents, SWT.RIGHT);
        GridData gridData14 = new GridData();
        gridData14.widthHint = 75;
        ytdDifferenceActual.setLayoutData(gridData14);
        budgetAdapter.add(new POJOFieldAdapter(ytdDifferenceActual, "YTDDifferenceActual"));

        Label ytdDifferenceDifference = new Label(contents, SWT.RIGHT);
        GridData gridData15 = new GridData();
        gridData15.widthHint = 75;
        ytdDifferenceDifference.setLayoutData(gridData15);
        budgetAdapter.add(new POJOFieldAdapter(ytdDifferenceDifference, "YTDDifferenceDifference"));

        Label label10 = new Label(contents, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData gridData16 = new GridData();
        gridData16.horizontalSpan = 4;
        gridData16.grabExcessHorizontalSpace = true;
        gridData16.grabExcessVerticalSpace = true;
        gridData16.horizontalAlignment = 4;
        gridData16.verticalAlignment = 4;
        label10.setLayoutData(gridData16);
        Label label11 = new Label(contents, SWT.NULL);
        label11.setText("Total Inflow");

        Label totalInflowBudget = new Label(contents, SWT.RIGHT);
        GridData gridData17 = new GridData();
        gridData17.widthHint = 75;
        totalInflowBudget.setLayoutData(gridData17);
        budgetAdapter.add(new POJOFieldAdapter(totalInflowBudget, "TotalInflowBudget"));

        Label totalInflowActual = new Label(contents, SWT.RIGHT);
        GridData gridData18 = new GridData();
        gridData18.widthHint = 75;
        totalInflowActual.setLayoutData(gridData18);
        budgetAdapter.add(new POJOFieldAdapter(totalInflowActual, "TotalInflowActual"));

        Label totalInflowDifference = new Label(contents, SWT.RIGHT);
        GridData gridData19 = new GridData();
        gridData19.widthHint = 75;
        totalInflowDifference.setLayoutData(gridData19);
        budgetAdapter.add(new POJOFieldAdapter(totalInflowDifference, "TotalInflowDifference"));

        Label label12 = new Label(contents, SWT.NULL);
        label12.setText("Total Outflow");

        Label totalOutflowBudget = new Label(contents, SWT.RIGHT);
        GridData gridData20 = new GridData();
        gridData20.widthHint = 75;
        totalOutflowBudget.setLayoutData(gridData20);
        budgetAdapter.add(new POJOFieldAdapter(totalOutflowBudget, "TotalOutflowBudget"));

        Label totalOutflowActual = new Label(contents, SWT.RIGHT);
        GridData gridData21 = new GridData();
        gridData21.widthHint = 75;
        totalOutflowActual.setLayoutData(gridData21);
        budgetAdapter.add(new POJOFieldAdapter(totalOutflowActual, "TotalOutflowActual"));

        Label totalOutflowDifference = new Label(contents, SWT.RIGHT);
        GridData gridData22 = new GridData();
        gridData22.widthHint = 75;
        totalOutflowDifference.setLayoutData(gridData22);
        budgetAdapter.add(new POJOFieldAdapter(totalOutflowDifference, "TotalOutflowDifference"));

        Label label13 = new Label(contents, SWT.NULL);
        label13.setText("Total Difference");

        Label totalDifferenceBudget = new Label(contents, SWT.RIGHT);
        GridData gridData23 = new GridData();
        gridData23.widthHint = 75;
        totalDifferenceBudget.setLayoutData(gridData23);
        budgetAdapter.add(new POJOFieldAdapter(totalDifferenceBudget, "TotalDifferenceBudget"));

        Label totalDifferenceActual = new Label(contents, SWT.RIGHT);
        GridData gridData24 = new GridData();
        gridData24.widthHint = 75;
        totalDifferenceActual.setLayoutData(gridData24);
        budgetAdapter.add(new POJOFieldAdapter(totalDifferenceActual, "TotalDifferenceActual"));

        Label totalDifferenceDifference = new Label(contents, SWT.RIGHT);
        GridData gridData25 = new GridData();
        gridData25.widthHint = 75;
        totalDifferenceDifference.setLayoutData(gridData25);
        budgetAdapter.add(new POJOFieldAdapter(totalDifferenceDifference, "TotalDifferenceDifference"));
    }

    IRecordObjectAdapter budgetAdapter;

    public void setInput(Budget budget) {
        try {
            budgetAdapter.setInput(budget);
        } catch (UnableToSaveException e) {
            Logger.log().error(e, "Read-only controls shouldn't need saving");
        }
    }

}

