 import java.sql.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;

 class EntryByTitle 
 {
 JFrame f;
 JLabel l1,l2,l3,l4;
 JButton b,b1;
 JTextField t1,t2,t3,t4;
 String s1,s2,s3,s4;
 public EntryByTitle()
 {
   f=new JFrame("Entry By Book Title "); 
 l1=new JLabel("Book Title");
 l1.setBounds(20,40,100,30);
 l2=new JLabel("Book Author");
 l2.setBounds(20,80,100,30);
 l3=new JLabel("Edition");
 l3.setBounds(20,120,100,30);
 l4=new JLabel("Book Price");
 l4.setBounds(20,160,100,30);
 t1=new JTextField();
 t1.setBounds(140,40,180,30);

  t2=new JTextField();
  t2.setBounds(140,80,180,30);
  t3=new JTextField();
  t3.setBounds(140,120,180,30);
  t4=new JTextField();
  t4.setBounds(140,160,180,30);
  b=new JButton("SAVE");
  b.setBounds(20,200,170,30);
  b1=new JButton("CANCEL");
  b1.setBounds(150,200,170,30);
 
   f.add(l1);
   f.add(l2);
   f.add(l3);
   f.add(l4);
   f.add(t1);
   f.add(t2);
   f.add(t3);
   f.add(t4);
   f.add(b);
   f.add(b1);
   f.setResizable(false);
   f.setLayout(null);
   f.setVisible(true);
   f.setSize(360,350);  

        ButHandler h=new ButHandler();
        b.addActionListener(h);
        b1.addActionListener(h);
        
    }

private class ButHandler implements ActionListener
 {
   public void actionPerformed(ActionEvent ae)
     {
                       
   try {
            
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      String url = "jdbc:odbc:msaccessDSN";
      Connection c = DriverManager.getConnection(url);
      insert(c);
      c.close();
       }
  catch(SQLException e) 
     {
      System.out.println("Error: " + e.getMessage());
     }
  catch(Exception e) 
     {
      System.out.println("Error: " + e.getMessage());
     }
  if(ae.getSource()==b1)
   {
     System.exit(0);
   }
 }
        
 public  void insert(Connection c) throws SQLException
   {         
     s1=t1.getText();
     s2=t2.getText();
     s3=t3.getText();
     s4=t4.getText();
Statement s=c.createStatement();
String query="insert into EntryByTitle values('"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
        
s.executeUpdate(query);
JOptionPane.showMessageDialog(null,"Record Saved in Database");
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
        
   }
  }

public static void main(String str[])
 {
   new EntryByTitle();
 }
}