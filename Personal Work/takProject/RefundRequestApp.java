package com.taksila.javabyexample.projects.refund;

import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;

import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.mail.*;

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
 * Created by Taksila LLC on 12/12/2016.
 */
public class RefundRequestApp extends JFrame implements ActionListener {

    JTextField orderDateFld;
    JTextField orderNumberFld;
    JTextField customerName;
    JTextField customerCompany;
    JTextField customerAddress1;
    JTextField customerAddress2;
    JTextField customerZipCode;
    JTextField customerEmail;
    JTextField customerPhoneNumber;
    JTextArea complaintComments;

    public static void main( String[] args ) {
        /*RefundRequestApp refundReqApp = new RefundRequestApp();
        refundReqApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow( refundReqApp );
        refundReqApp.setVisible(true);*/

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
                    //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
                } catch (Exception e) {
                    System.out.println("Failed to Initialize Skin");
                }
                RefundRequestApp refundReqApp = new RefundRequestApp();
                refundReqApp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                centreWindow( refundReqApp );
                refundReqApp.setVisible(true);
            }
        });
    }

    public RefundRequestApp() {
        this.setTitle( "Customer Refund Request Application");
        this.setSize( 900, 800 );
        addWindowListener( new ConfirmOnClose() );
        initializeGUI();
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
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

    public void initializeGUI() {
        JPanel content = (JPanel)this.getContentPane();
        content.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel( "Customer Refund Request Form", SwingConstants.CENTER );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 25f );
        label.setFont( bigFont );
        tempPanel.add( label );
        Border border = BorderFactory.createLineBorder( Color.BLACK, 2 );
        tempPanel.setBorder( border );
        addComponent( content, c, tempPanel, 0,0,5 );
        c.anchor = GridBagConstraints.WEST;
        addComponent( content,c,createOrderPanel(), 0,1,5 );
        addComponent( content,c,createCustomerInfoPanel(), 0,2,5 );
        addComponent( content,c,createComplaintPanel(), 0,3,5 );
        addComponent( content,c,createButtonsPanel(), 0,4,5 );
    }

    public JPanel createOrderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        JLabel label = new JLabel( "Order Date: " );
        orderDateFld = new JTextField("", 10 );
        addComponent( panel, c, label, 0,0,1 );
        addComponent( panel, c, orderDateFld, 1,0,1 );
        label = new JLabel( "Order Number: ");
        orderNumberFld = new JTextField( "", 20 );
        addComponent( panel, c, label, 2,0,1 );
        addComponent( panel, c, orderNumberFld, 3,0,1 );
        return panel;
    }

    public JPanel createCustomerInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        Border border = BorderFactory.createTitledBorder( "Customer Information: " );
        panel.setBorder( border );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.WEST;
        JLabel label = new JLabel( "Name: " );
        customerName = new JTextField( "", 20 );
        addComponent( panel, c, label, 0,0,1 );
        addComponent( panel, c, customerName, 1,0,1 );
        label = new JLabel( "Company: " );
        customerCompany = new JTextField( "", 20 );
        addComponent( panel, c, label, 0,2,1 );
        addComponent( panel, c, customerCompany, 1,2,1 );
        label = new JLabel( "Address: " );
        customerAddress1 = new JTextField( "", 20 );
        addComponent( panel, c, label, 0,3,1 );
        addComponent( panel, c, customerAddress1, 1,3,1 );
        customerAddress2 = new JTextField( "", 20 );
        addComponent( panel, c, customerAddress2, 1,4,1 );
        label = new JLabel( "Zip Code: " );
        customerZipCode = new JTextField( "", 10 );
        addComponent( panel, c, label, 0,5,1 );
        addComponent( panel, c, customerZipCode, 1,5,1 );
        label = new JLabel( "Email ID: " );
        customerEmail = new JTextField( "", 20 );
        addComponent( panel, c, label, 0,6,1 );
        addComponent( panel, c, customerEmail, 1,6,1 );
        label = new JLabel( "Phone Number: " );
        customerPhoneNumber = new JTextField( "", 20 );
        addComponent( panel, c, label, 0,7,1 );
        addComponent( panel, c, customerPhoneNumber, 1,7,1 );
        return panel;
    }

    public JPanel createComplaintPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        Border border = BorderFactory.createTitledBorder( "Please provide detailed explanations of the reasons why you are requesting for refund: " );
        panel.setBorder( border );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.WEST;
        JLabel label = new JLabel( "We may contact you to collect further details about your refund request to improve our product and customer service");
        addComponent( panel, c, label, 0,0,1 );
        complaintComments = new JTextArea( "", 10, 60 );
        JScrollPane scroll = new JScrollPane (complaintComments,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addComponent( panel, c, scroll, 0,1,1 );
        return panel;
    }

    public JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        JButton resetButton = new JButton( "Reset" );
        resetButton.setActionCommand( "reset" );
        resetButton.addActionListener( this );
        JButton submitButton = new JButton( "Submit" );
        submitButton.setActionCommand( "submit" );
        submitButton.addActionListener( this );
        addComponent( panel, c, resetButton, 0,0,1 );
        addComponent( panel, c, submitButton, 1,0,1 );
        return panel;
    }

    public void addComponent( JPanel content, GridBagConstraints c, Component comp, int column, int row, int gridwidth ) {
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = gridwidth;
        content.add( comp, c );
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if (actCommand.equalsIgnoreCase("reset")) {
            reset();
        }
        else if (actCommand.equalsIgnoreCase("submit")) {
            submit();
        }
    }

    public void reset() {
        //reset all the fields
        orderDateFld.setText( "");
        orderNumberFld.setText( "" );
        customerName.setText( "" );
        customerCompany.setText( "" );
        customerAddress1.setText( "" );
        customerAddress2.setText( "" );
        customerZipCode.setText( "" );
        customerEmail.setText( "" );
        customerPhoneNumber.setText( "" );
        complaintComments.setText( "" );
    }

    public void submit() {
        String msg = createMessage();
        sendMessage( msg );
    }

    public String createMessage() {
        StringBuffer msg = new StringBuffer();
        msg.append( "Customer Refund Request application \n" )
                .append( "OrderID: ").append( orderNumberFld.getText() )
                .append( " Order Date: " ).append( orderDateFld.getText() ).append( "\n")
                .append( "Customer Name: " ).append( customerName.getText() )
                .append( "\nCustomer Company: " ).append( customerCompany.getText() )
                .append( "\nAddress: " ).append( customerAddress1.getText() )
                .append( "  " ).append( customerAddress2.getText() )
                .append( "  " ).append( customerZipCode.getText() )
                .append( "\nEmail ID:  " ).append( customerEmail.getText() )
                .append( "\nPhone Number " ).append( customerPhoneNumber.getText() )
                .append( "\n\nComments: \n" ).append( complaintComments.getText() );
        return msg.toString();
    }

    public void sendMessage( String message ) {
        //My account --> Sing into Google -->  Allow less secure apps
        String to = "testtaksila@gmail.com";
        String from = customerEmail.getText();
        String password = "**********";
        String cc = from;
        Properties properties = new Properties();
        properties.setProperty( "mail.smtp.host", "smtp.gmail.com" );
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");

        /** CHANGE the email ID and password to appropriate email id and corresponding password **/
        Session session = Session.getInstance( properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(to, password);
            }
        } );
        try {
            MimeMessage mimeMessage = new MimeMessage( session );
            mimeMessage.setFrom( new InternetAddress( from ));
            mimeMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( to ));
            mimeMessage.addRecipient( Message.RecipientType.CC, new InternetAddress( cc ));
            mimeMessage.setSubject( "Customer Refund Request Application" );
            mimeMessage.setText( message );
            System.out.println( "Before sending email" );
            Transport.send( mimeMessage );
            System.out.println( "Successfully sent the message" );
        } catch (Exception ex ) {
            ex.printStackTrace();
        }
    }
}
