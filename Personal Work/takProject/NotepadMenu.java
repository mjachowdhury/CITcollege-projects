package com.taksila.javabyexample.projects.notepad;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

import com.taksila.javabyexample.projects.notepad.NotepadApp;

/**
 * Copyright (c) 2016, Taksila LLC. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <p>
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * <p>
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p>
 * - Neither the name of Taksila LLC or the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * Created by Taksila LLC on 7/2/2016.
 */
public class NotepadMenu implements ActionListener {

    NotepadApp app;
    JTextArea textArea;
    boolean wordWrapFlag = true;
    PageFormat pf;
    PrinterJob job;

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    public boolean isWordWrapFlag() {
        return wordWrapFlag;
    }

    public void setWordWrapFlag(boolean wordWrapFlag) {
        this.wordWrapFlag = wordWrapFlag;
    }

    public void setApp(NotepadApp app) {
        this.app = app;
    }


    public JMenuBar createMenuBar() {
        JMenuBar notepadMenuBar = new JMenuBar();
        notepadMenuBar.add( createFileMenu() );
        notepadMenuBar.add( createEditMenu() );
        notepadMenuBar.add( createFormatMenu() );
        return notepadMenuBar;
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu( "File");
        JMenuItem menuItem;
        menuItem = new JMenuItem( "New" );
        menuItem.setActionCommand( "new");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Open" );
        menuItem.setActionCommand( "open");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Save" );
        menuItem.setActionCommand( "save");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Save As" );
        menuItem.setActionCommand( "saveas");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menu.addSeparator();
        menuItem = new JMenuItem( "Page Setup" );
        menuItem.setActionCommand( "pagesetup");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Print" );
        menuItem.setActionCommand( "print");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menu.addSeparator();
        menuItem = new JMenuItem( "Exit" );
        menuItem.setActionCommand( "exit");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        return menu;
    }

    private JMenu createEditMenu() {
        JMenu menu = new JMenu( "Edit");
        JMenuItem menuItem;
        menuItem = new JMenuItem( "Copy" );
        menuItem.setActionCommand( "copy");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Paste" );
        menuItem.setActionCommand( "paste");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Select All" );
        menuItem.setActionCommand( "selectall");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        return menu;
    }

    private JMenu createFormatMenu() {
        JMenu menu = new JMenu( "Format");
        JMenuItem menuItem;
        menuItem = new JMenuItem( "Word Wrap" );
        menuItem.setActionCommand( "wordwrap");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        menuItem = new JMenuItem( "Font" );
        menuItem.setActionCommand( "font");
        menuItem.addActionListener( this );
        menu.add( menuItem );
        return menu;
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if( actCommand.equalsIgnoreCase( "new" ) ) {
            newFile();
        }
        else if( actCommand.equalsIgnoreCase( "open" ) ) {
            openFile();
        }
        else if( actCommand.equalsIgnoreCase( "save" ) ) {
            saveFile();
        }
        else if( actCommand.equalsIgnoreCase( "saveas" ) ) {
            saveAsFile( null );
        }
        else if( actCommand.equalsIgnoreCase( "pagesetup" ) ) {
            pagesetup();
        }
        else if( actCommand.equalsIgnoreCase( "print" ) ) {
            print();
        }
        if( actCommand.equalsIgnoreCase( "exit" ) ) {
            closeWindow();
        }
        else if( actCommand.equalsIgnoreCase( "copy" ) ) {
            textArea.copy();
        }
        else if( actCommand.equalsIgnoreCase( "paste" ) ) {
            textArea.paste();
        }
        else if( actCommand.equalsIgnoreCase( "selectall" ) ) {
            textArea.selectAll();
        }
        else if(actCommand.equalsIgnoreCase( "wordwrap" ) ) {
            toggleWordWrap();
        }
        else if(actCommand.equalsIgnoreCase( "font" ) ) {

            setFont();
        }
    }

    private void toggleWordWrap() {
        boolean flag = isWordWrapFlag();
        System.out.println( "Setting wordwrap flag to: " + (!flag) );
        setWordWrapFlag( !flag );
        textArea.setLineWrap( !flag );
        textArea.setWrapStyleWord( !flag );
    }

    private void setFont() {
        app.showFontDialog();
    }

    private void closeWindow() {
        app.dispatchEvent( new WindowEvent( app, WindowEvent.WINDOW_CLOSING));
    }

    private void newFile() {
        File currentFile = app.getCurrentFile();
        if( currentFile != null ) {
            app.setLastFolder( currentFile.getParentFile() );
            System.out.println( "Setting last folder: " + app.getLastFolder() );
            app.setCurrentFile( null );
            textArea.setText( "" );
            app.setTitle( "Untitled - NotepadApp" );
        }
    }

    private void openFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt", "props", "java");
        File file = showFileChooser( "Open File", filter, "Open" );
        if( file != null ) {
            //load the file
            FileReader fileReader;
            BufferedReader buffReader;

            try {
                //Read the file in default encoding
                fileReader = new FileReader(file);
                buffReader = new BufferedReader( fileReader );
                textArea.read(buffReader, "Reading File");
                app.setCurrentFile( file );
                app.setLastFolder( file.getParentFile() );
                app.setTitle( file.getName() + " - NotepadApp"  );
            }
            catch( IOException ioex ) {
                System.out.println( "Error in reading file: " + file );
                System.out.println( ioex.getMessage() );
                ioex.printStackTrace();
            }
            finally {
                fileReader = null;
                buffReader = null;
            }
        }

    }

    private void saveFile() {
        saveAsFile( app.getCurrentFile() );
        app.setTitle( app.getCurrentFile() + " - NotepadApp" );
    }

    private void saveAsFile(File file ) {
        if( file == null ) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt", "props", "java");
            file = showFileChooser("Save File", filter, "Save");
        }
        if( file != null ) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter buffWriter = new BufferedWriter( fileWriter );
                textArea.write( buffWriter );
                app.setCurrentFile( file );
                app.setLastFolder( file.getParentFile() );
            }
            catch( IOException ioex ) {
                System.out.println( "Error in writing to file: " + file );
                System.out.println( ioex.getMessage() );
                ioex.printStackTrace();
            }
        }
    }

    private File showFileChooser( String dialogTile, FileNameExtensionFilter filter, String operation ) {
        File file = null;
        JFileChooser fc = new JFileChooser(app.getLastFolder());
        int returnVal = 0;
        if( operation.equalsIgnoreCase( "open" ) ) {
            fc = new JFileChooser(app.getLastFolder());
            fc.setFileFilter(filter);
            returnVal = fc.showDialog(app, "Open File");
        }
        else if( operation.equalsIgnoreCase( "save" ) ) {
            fc = new JFileChooser(app.getLastFolder());
            fc.setFileFilter(filter);
            returnVal = fc.showSaveDialog(app );
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        return file;
    }

    private void pagesetup() {
        job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        pf = job.pageDialog(aset);
    }

    private void print() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable( app, pf );
        boolean printFlag = job.printDialog();
        if( printFlag ) {
            try {
                job.print();
            }
            catch( PrinterException pe ) {
                System.out.println( "There is an exception: " + pe.getMessage() );
                pe.printStackTrace();
            }
        }

    }

}
