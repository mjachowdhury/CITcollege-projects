/*
 * This file is part of com.swtworkbench.ed.
 *
 * com.swtworkbench.ed is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License
 * as published by the Free Software Foundation.
 *
 * com.swtworkbench.ed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with com.swtworkbench.ed; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.swtworkbench.ed.aware.fields;

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.swtworkbench.ed.aware.interfaces.IAwareWidget;
import com.swtworkbench.ed.aware.interfaces.IFieldAdapter;
import com.swtworkbench.ed.aware.marshaller.MarshallException;
import com.swtworkbench.ed.aware.maskededit.MaskMatchException;
import com.swtworkbench.ed.aware.maskededit.internal.MaskMachine;
import com.swtworkbench.swtutils.logger.Logger;

/**
 * Class AwareTextField.  
 * 
 * @author daveo
 */
public class AwareTextField extends AbstractAwareField implements IAwareWidget {

    // Methods for getting and setting the value of the referenced control 
    private Method setText = null;
    private Method getText = null;
    
    // VerifyListener / ModifyListener
    private Method addVerifyListener = null;
    private Method addModifyListener = null;
    private Method removeVerifyListener = null;
    private Method removeModifyListener = null;
    
    // Setting the selection range
    private Method setSelectionRange = null;

    /**
     * Constructor AbstractAwareField.  Create an AbstractAwareField object on some data source
     * and control.  
     * 
     * @param priority
     */
    public AwareTextField(IFieldAdapter dataPersister, Control control) {
        super(dataPersister, control);
        getMethods();
        addListeners(control);
    }
    
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getType()
     */
    public Class getType() {
        return String.class;
    }
    
    /**
     * Method getMethods.  Get Method objects for each of the event hooks.  
     */
    protected void getMethods() {
        super.getMethods();
        setText = getMethod("setText", new Class[] {String.class});
        getText = getMethod("getText", new Class[] {});
        addVerifyListener = getMethod("addVerifyListener", new Class[]{VerifyListener.class});
        addModifyListener = getMethod("addModifyListener", new Class[]{ModifyListener.class});
        removeVerifyListener = getMethod("removeVerifyListener", new Class[]{VerifyListener.class});
        removeModifyListener = getMethod("removeModifyListener", new Class[]{ModifyListener.class});
        setSelectionRange = getMethod("setSelection", new Class[]{Integer.TYPE, Integer.TYPE});
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getStringValue()
     */
    public String getStringValue() {
        return (String)invoke(getText, new Object[]{});        
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#getValue()
     */
    public Object getValue() {
        ensureMarshallers();
        
        // Get the text out of the field
        String text;
        if (dataPersister.getEditMask() != null) {
            text = dataPersister.getEditMask().getInput();
        } else {
            text = getStringValue();
        }
        if (text == null) text = "";
        
        // Convert it to the desired type
        Object result = null;
        try {
            result = marshall2Property.convert(text);
        } catch (MarshallException e) {
            Logger.log().error(e, "Unable to convert " + getType().getName() + " to " + dataPersister.getType().getName() + " using source data: " + text);
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#setValue(java.lang.Object)
     */
    public void setValue(Object newValue) {
        ensureMarshallers();
        
        String stringValue = "";
        try {
            stringValue = (String)marshall2Field.convert(newValue);
            if (stringValue == null) stringValue = "";
        } catch (MarshallException e) {
            Logger.log().error(e, "Unable to convert " + dataPersister.getType().getName() + " to " + getType().getName() + " using source data: " + newValue);
        }
        setText(stringValue);
        this.original = newValue;
        setDirty(false);
        if (dataPersister.getEditMask() == null)
            selectAll();
        else {
            try {
                dataPersister.getEditMask().setInput(stringValue);
            } catch (MaskMatchException e1) {
                Logger.log().debug(AwareTextField.class, "Can't set value: '" + stringValue + "'. " + 
                        dataPersister.getEditMask().getHelpMessage(dataPersister.getPropertyName()));
            }
        }
    }
    
    protected void setText(String newValue) {
        invoke(setText, new Object[]{newValue});
    }
    
    /**
     * Method setSelection.  Sets the beginning and ending of the selection
     * in the underlying control if this is possible.
     * 
     * @param begin The beginning of the selection
     * @param end The end of the selection
     */
    public void setSelection(int begin, int end) {
        Integer start = new Integer(begin);
        Integer finish = new Integer(end);
        invoke(setSelectionRange, new Object[] {start, finish});
    }
    
    protected void setMaskSelection(int position) {
        if (control.isDisposed()) return;
//        if ((control.getStyle() & SWT.RIGHT_TO_LEFT) != 0) {
//            position = getStringValue().length() - position;
//        }
        setSelection(position, position);
    }
    
    /**
     * Method selectAll.  Tell the field to select its entire contents.  
     */
    public void selectAll() {
        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                try {
                    int textLength = getStringValue().length();
                    setSelection(0, textLength);
                } catch (Throwable t) {
                    // If a window containing a Table is being resized 
                    // smaller, sometimes this runnable won't run until the 
                    // control it's targeting has been disposed, so this method
                    // will log NullPointerExceptions in that case...
                    //Logger.log().error(new Exception(t), "");
                }
            }
        });
    }
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.interfaces.IAwareWidget#clear()
     */
    public void clear() {
        setText("");
    }

    /**
     * Method addVerifyListener.  Adds a verify listener to the underlying control
     * @param handler The VerifyListener handler
     */
    private void addVerifyListener(VerifyListener handler) {
        invoke(addVerifyListener, new Object[] {handler});
    }
    
    /**
     * Method addModifyListener.  Adds a modify listener to the underlying control
     * @param handler The ModifyListener handler
     */
    private void addModifyListener(ModifyListener handler) {
        invoke(addModifyListener, new Object[] {handler});
    }
    
    /**
     * Method removeVerifyListener.  Removes a verify listener from the underlying
     * control.
     * @param handler The VerifyListener handler.
     */
    private void removeVerifyListener(VerifyListener handler) {
        invoke(removeVerifyListener, new Object[] {handler});
    }
    
    /**
     * Method removeModifyListener.  Removes a modify listener from the underlying
     * control.
     * @param handler The ModifyListener handler.
     */
    private void removeModifyListener(ModifyListener handler) {
        invoke(removeModifyListener, new Object[] {handler});
    }
    
    /**
     * Method hookEvents.  Hook up listeners to the control
     * @param control
     */
    protected void addListeners(Control control) {
        super.addListeners(control);
        control.getShell().addShellListener(shellListener);
        control.addKeyListener(keyListener);
        control.addTraverseListener(traverseListener);
        control.addFocusListener(focusListener);
        addVerifyListener(verifyListener);
        addModifyListener(modifyListener);
    }

    /* (non-Javadoc)
     * @see com.swtworkbench.choreographer.aware.IAwareWidget#removeListeners()
     */
    public void removeListeners() {
        super.removeListeners();
        control.getShell().removeShellListener(shellListener);
        control.removeKeyListener(keyListener);
        control.removeTraverseListener(traverseListener);
        control.removeFocusListener(focusListener);
        removeVerifyListener(verifyListener);
        removeModifyListener(modifyListener);
    }
    
    protected KeyListener keyListener = new KeyListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
         */
        public void keyPressed(KeyEvent e) {
            printEvent(e);
            if (e.keyCode == SWT.ESC) {
                setDirty(false);
                setValue(original);
//                if (dataPersister.getEditMask() == null)
//                    selectAll();
            }
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
         */
        public void keyReleased(KeyEvent e) {
            printEvent(e);
        }
    };
    
    protected TraverseListener traverseListener = new TraverseListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.TraverseListener#keyTraversed(org.eclipse.swt.events.TraverseEvent)
         */
        public void keyTraversed(TraverseEvent e) {
            printEvent(e);
            if ((e.detail & (SWT.TRAVERSE_RETURN | SWT.TRAVERSE_PAGE_NEXT |
                    SWT.TRAVERSE_PAGE_PREVIOUS | SWT.TRAVERSE_TAB_NEXT |
                    SWT.TRAVERSE_TAB_PREVIOUS | SWT.TRAVERSE_MNEMONIC)) != 0)
                if (isDirty()) {
                    if (dataPersister.getEditMask() != null) {
                        setText(dataPersister.getEditMask().getInput());
                        Logger.log().debug(AwareTextField.class, "Saved edit mask input");
                    }
                    e.doit = validateField(getStringValue());
                    if (e.doit) {
                        dataPersister.save(awareField, getValue());
                        setDirty(false);
                    }
                } else {
                    if (dataPersister.getEditMask() != null) {
                        setText(dataPersister.getEditMask().getInput());
                        Logger.log().debug(AwareTextField.class, "Saved edit mask input");
                    }
                    dataPersister.clearMessage();
                }
        }
    };
    
    private Color yellow = Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
    protected Color oldBackground;
    protected boolean backgroundSet = false;

    protected FocusListener focusListener = new FocusListener() {
        
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
         */
        public void focusGained(FocusEvent e) {
            printEvent(e);
            if (backgroundSet) {
                backgroundSet = false;
                control.setBackground(oldBackground);
            }
            if (dataPersister.getEditMask() != null) {
                // Update what's on the screen
                final MaskMachine maskMachine = dataPersister.getEditMask();
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        try {
                            maskMachine.setInput(getStringValue());
                        } catch (MaskMatchException e) {
                            Logger.log().error(e, "Unable to set edit mask machine value");
                        }
                        setText(maskMachine.getMaskedInput());
                        Logger.log().debug(AwareTextField.class, "Initialized masked editing");
                        int position = maskMachine.getPosition();
                        setMaskSelection(position);
                    }
                });
            } //else
                //selectAll();
        }
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
         */
        public void focusLost(FocusEvent e) {
            printEvent(e);
            /*
             * If the user clicks away, we never get a TraverseEvent.  So we
             * try to validate/save the data anyway and if this isn't possible,
             * we leave the field dirty.
             */
            if (dataPersister.getEditMask() != null) {
                Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                        setText(dataPersister.getEditMask().getInput());
                        System.out.println("Saved edit mask!");
                    }
                });
            }
            if (isDirty()) {
                validateField();
            }
        }
    };
    
    /* (non-Javadoc)
     * @see com.swtworkbench.ed.aware.fields.AbstractAwareField#validateField()
     */
    public void validateField() {
        boolean valid = validateField(getStringValue());
        if (valid) {
            dataPersister.save(awareField, getValue());
            setDirty(false);
        } else {
            oldBackground = control.getBackground();
            control.setBackground(yellow);
            backgroundSet = true;
        }
    }

    
    protected boolean validateField(String value) {
        boolean valid = dataPersister.validateField(value);
        if (dataPersister.getEditMask() != null && valid) {
            valid = dataPersister.getEditMask().isValid();
            if (!valid)
                dataPersister.sendMessage(dataPersister.getEditMask().getHelpMessage(dataPersister.getPropertyName()));
        }
        return valid;
    }
    
    protected Point fixSelection(int start, int end) {
//        if ((control.getStyle() & SWT.RIGHT_TO_LEFT) != 0) {
//            int length = getStringValue().length();
//            start = length-start;
//            end = length-end;
//        }
        return new Point(start, end);
    }
    
    protected VerifyListener verifyListener = new VerifyListener() {
        // Used to prevent unwanted recursion
        protected boolean verifying=false;
        
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.VerifyListener#verifyText(org.eclipse.swt.events.VerifyEvent)
         */
        public void verifyText(VerifyEvent e) {
            printEvent(e);

            final Control control = ((Control)e.widget);
            if (dataPersister.getEditMask() != null && !verifying) {
                final MaskMachine maskMachine = dataPersister.getEditMask();
                verifying=true;
                
                fixSelection(e.start, e.end);

                // Update the state of the mask state machine
                if (e.start == e.end && e.text.length() == 1)
                    maskMachine.append(e.text);
                else {
                    for (int i=0; i < e.end-e.start; ++i)
                        maskMachine.backspace();
                    for (int pos=0; pos < e.text.length(); ++pos)
                        maskMachine.append(e.text.substring(pos, pos+1));
                }
                
                // Update what's on the screen
                Display.getCurrent().asyncExec(new Runnable() {
                    public void run() {
                        boolean hasFocus = ((Control)control).isFocusControl();
                        if (hasFocus) {
                            setText(maskMachine.getMaskedInput());
                            Logger.log().debug(AwareTextField.class, "VerifyText: Initialized masked editing");
                        } else {
                            setText(maskMachine.getInput());
                            Logger.log().debug(AwareTextField.class, "VerifyText: Saved edit mask!");
                        }
                        int position = maskMachine.getPosition();
                        setMaskSelection(position);
                        verifying=false;
                    }
                });
            } else
                dataPersister.validate(getStringValue(), e);
            
        }
    };
    
    protected ModifyListener modifyListener = new ModifyListener() {
        /* (non-Javadoc)
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(ModifyEvent e) {
            printEvent(e);
            setDirty(true);
        }
    };
    
    protected ShellListener shellListener = new ShellListener() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.ShellListener#shellIconified(org.eclipse.swt.events.ShellEvent)
             */
            public void shellIconified(ShellEvent e) {
                printEvent(e);
            }
    
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.ShellListener#shellDeiconified(org.eclipse.swt.events.ShellEvent)
             */
            public void shellDeiconified(ShellEvent e) {
                printEvent(e);
            }
            
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.ShellListener#shellActivated(org.eclipse.swt.events.ShellEvent)
             */
            public void shellActivated(ShellEvent e) {
                printEvent(e);
            }
    
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.ShellListener#shellDeactivated(org.eclipse.swt.events.ShellEvent)
             */
            public void shellDeactivated(ShellEvent e) {
                printEvent(e);
            }
    
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.ShellListener#shellClosed(org.eclipse.swt.events.ShellEvent)
             */
            public void shellClosed(ShellEvent e) {
                printEvent(e);
                if (isDirty() && e.doit) {
                    e.doit = validateField(getStringValue());
                    if (e.doit) {
                        if (dataPersister.getEditMask() != null) {
                            dataPersister.save(awareField, dataPersister.getEditMask().getInput());
                        } else {
                            dataPersister.save(awareField, getValue());
                        }
                        setDirty(false);
                    }
                }
            }
        };

}



