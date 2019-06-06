 import java.sql.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;

 class SaleaBook
 {
 JFrame f;
 JLabel l1,l2,l3,l4;
 JButton b,b1,b2;
 JTextField t1,t2,t3,t4;
 Container cont;
 String s1,s2,s3,s4;
 public SaleaBook()
 {
  f=new JFrame("sale a Book");
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
  b2=new JButton("click here to check book availablity");
  b2.setBounds(20,200,300,30);
 
  b=new JButton("click here to sale a book");
  b.setBounds(20,240,300,30);

  b1=new JButton("cancel");
  b1.setBounds(120,280,100,30);

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
  f.add(b2);
   
  f.setResizable(false);
  f.setLayout(null);
  f.setVisible(true);
  f.setSize(360,350);

        Handler h=new Handler();
        b.addActionListener(h);
        b1.addActionListener(h);
        b2.addActionListener(h);
 
 }

private class Handler implements ActionListener
 {
   public void actionPerformed(ActionEvent ae)
     {

   try {

      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      String url = "jdbc:odbc:msaccessDSN";
      Connection c = DriverManager.getConnection(url);
      remove(c);
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

public  void remove(Connection c) throws SQLException
{        
           s1=t1.getText();
 String q = "select * from EntryByTitle where booktitle='"+s1+"'";
 Statement ss = c.createStatement();
 ResultSet rs = ss.executeQuery(q);  
 if (rs.next()) 
{
t2.setText(rs.getString(2));
t3.setText(rs.getString(3));
t4.setText(rs.getString(4));
JOptionPane.showMessageDialog(null,"Book Available");
String q1 = "delete from EntryByTitle where booktitle='"+s1+"'";
Statement s = c.createStatement();
ResultSet rs1 = s.executeQuery(q1);
JOptionPane.showMessageDialog(null,"Book Deleted");
}
else if(s1!=t1.getText())
{
JOptionPane.showMessageDialog(null,"Book not Available");
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
}
else
{
JOptionPane.showMessageDialog(null,"Book deleted");
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
}       
}
}  
  
public static void main(String str[])
 {
   new SaleaBook();
 }
}