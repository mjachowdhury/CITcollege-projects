package com.taksila.javabyexample.projects.notepad;

import com.taksila.javabyexample.projects.text.JFontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;

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
public class NotepadApp extends JFrame implements Printable {

    JTextArea textArea;
    File currentFile;
    File lastFolder;

    public static void main( String[] args ) {
        NotepadApp notepadApp = new NotepadApp();
        notepadApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow( notepadApp );
        notepadApp.setVisible(true);
    }

    public NotepadApp() {
        this.setTitle( "Untitled - NotepadApp");
        this.setSize( 1000, 600 );
        addWindowListener( new ConfirmOnClose() );
        initializeGUI();

    }

    class ConfirmOnClose extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showConfirmDialog(e.getWindow(), "Are you sure you want to exit?");
            if( confirm == 0 ) {
                e.getWindow().dispose();
                System.exit(0);
            }
        }
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    private void initializeGUI() {
        //Set the menu bar
        NotepadMenu notepadMenu = new NotepadMenu();
        this.setJMenuBar( notepadMenu.createMenuBar());

        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new BorderLayout());
        textArea = new JTextArea();
        JScrollPane scrollingArea = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        content.add( scrollingArea, BorderLayout.CENTER );
        textArea.setLineWrap( true );
        textArea.setWrapStyleWord( true );
        notepadMenu.setTextArea( textArea );
        notepadMenu.setApp( this );
    }

    public void showFontDialog() {
        JFontChooser fd = new JFontChooser(this,textArea.getFont());
        fd.show();
        if(fd.getReturnStatus() == fd.RET_OK){
            textArea.setFont(fd.getFont());
        }
        fd.dispose();
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public File getLastFolder() {
        return lastFolder;
    }

    public void setLastFolder(File lastFolder) {
        this.lastFolder = lastFolder;
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.drawString( textArea.toString(), 0, 0 );
        return PAGE_EXISTS;
    }

}
