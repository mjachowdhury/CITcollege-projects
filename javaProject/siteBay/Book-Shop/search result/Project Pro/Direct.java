import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Direct extends JFrame implements ActionListener 
{
JButton b1,b2,b3;
JCheckBox c1,c2,c3;
JLabel l1,l2,l3;
JTextField t1,t2,t3,t4;
String s1,s2,s3,s4;
Direct()
{
setTitle("Search   SEARCH RESULT");
setSize(600,600);
setLayout(null);
l3=new JLabel("Search by type or name");
l3.setBounds(100,25,300,25);
l3.setFont(new Font("SansSerif",Font.BOLD,20));
b1=new JButton("BACK");
b1.setBounds(50,500,100,25);
b1.addActionListener(this);
b2=new JButton("SEARCH");
b2.setBounds(200,500,100,25);
ButHandler h=new ButHandler();
        b2.addActionListener(h);
c1=new JCheckBox("Images");
c1.setBounds(100,70,250,25);
c2=new JCheckBox("Photos");
c2.setBounds(100,120,100,25);
c3=new JCheckBox("Video");
c3.setBounds(100,170,100,25);
l1=new JLabel("All or part of the file name");
l1.setBounds(100,200,300,25);
l1.setFont(new Font("SansSerif",Font.BOLD,20));
t1=new JTextField("");
t1.setBounds(100,250,200,25);
t2=new JTextField("");
t2.setBounds(100,370,100,25);
t3=new JTextField("");
t3.setBounds(200,370,100,25);
t4=new JTextField("");
t4.setBounds(300,370,100,25);
l2=new JLabel("Use advanced search ?");
l2.setBounds(100,330,300,25);
l2.setFont(new Font("SansSerif",Font.BOLD,20));
b3=new JButton("EXIT");
b3.setBounds(350,500,100,25);
b3.addActionListener(this);
add(b1);
add(b2);
add(c1);
add(c2);
add(c3);
add(l1);
add(t1);
add(t2);
add(t3);
add(t4);
add(l2);
add(b3);
add(l3);
setVisible(true);
setResizable(false);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b3)
{
System.exit(0);
}
if(ae.getSource()==b1)
{
setVisible(false);
}
}
 private class ButHandler implements ActionListener
 {
   public void actionPerformed(ActionEvent ae)
     {

   try {

      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      String url = "jdbc:odbc:project";
      Connection c = DriverManager.getConnection(url);
      show(c);
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

   }
    }
 public  void show(Connection c) throws SQLException
   {
if(true)
{
     s1=t1.getText();
   
String query= "select * from Pictures where P_name= '" +s1+ "'";
Statement s = c.createStatement();
ResultSet rs=s.executeQuery(query);

//JOptionPane.showMessageDialog(null,"Record Saved in Database");
if(rs.next())
{
//t1.setText(s1);
t2.setText(rs.getString(2));
t3.setText(rs.getString(3));
t4.setText(rs.getString(4));
JOptionPane.showMessageDialog(null,"Search Item is exist");
t2.setText("");
t3.setText("");
t4.setText("");
}
else if(true)
{
     s1=t1.getText();

String query1= "select * from Songs where S_name= '" +s1+ "'";
Statement ss = c.createStatement();
ResultSet rs1=ss.executeQuery(query1);

//JOptionPane.showMessageDialog(null,"Record Saved in Database");
if(rs1.next())
{
//t1.setText(s1);
t2.setText(rs1.getString(2));
t3.setText(rs1.getString(3));
t4.setText(rs1.getString(4));
JOptionPane.showMessageDialog(null,"Search Item is exist");
t2.setText("");
t3.setText("");
t4.setText("");
}
else if(true)
{
     s1=t1.getText();

String query2= "select * from Files where F_name= '" +s1+ "'";
Statement sss = c.createStatement();
ResultSet rs2=sss.executeQuery(query2);

//JOptionPane.showMessageDialog(null,"Record Saved in Database");
if(rs2.next())
{
//t1.setText(s1);
t2.setText(rs2.getString(2));
t3.setText(rs2.getString(3));
t4.setText(rs2.getString(4));
JOptionPane.showMessageDialog(null,"Search Item is exist");
t2.setText("");
t3.setText("");
t4.setText("");
}
else
{
    JOptionPane.showMessageDialog(null,"Search Item does not exist");
}
 }
 }
 }
 }
public static void main(String Args[])
{
new Direct();
}
}