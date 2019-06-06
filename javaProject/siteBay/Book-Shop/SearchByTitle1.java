import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class SearchByTitle1{
        JFrame f;
        JButton b1,b2;
        JTextField t1,t2,t3,t4;
        JLabel l1,l2,l3,l4;
        //JComboBox cmb;
        String s1,s2,s3,s4;
    SearchByTitle1()
    {
       
        f=new JFrame("");
        b1=new JButton("search");
        b2=new JButton("cancel");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        l1=new JLabel("Book Title");
        l2=new JLabel("Book Author");
        l3=new JLabel("Edition");
        l4=new JLabel("Book Price");
        //cmb=new JComboBox();   
        f.add(l1);f.add(l2);f.add(l3);
        f.add(l4);f.add(t1);f.add(t2);
        f.add(t3);f.add(t4);f.add(b1);
        f.add(b2);
        //f.add(cmb);
    
        l1.setBounds(20,40,100,30);t1.setBounds(150,40,150,30); 
        l2.setBounds(20,80,100,30);t2.setBounds(150,80,150,30); 
        l3.setBounds(20,120,100,30);t3.setBounds(150,120,150,30); 
        l4.setBounds(20,160,100,30);t4.setBounds(150,160,150,30);
        b1.setBounds(20,200,150,30);b2.setBounds(150,200,150,30);
       // cmb.setBounds(350,40,150,30); 
        
        
        BHandler h=new BHandler();
        b1.addActionListener(h);
        b2.addActionListener(h);
        //CHandler ab=new CHandler();
        //cmb.addItemListener(ab);  
        
       /* Consume c = new Consume();
        t4.addKeyListener(c);
        Consumet d = new Consumet();
        t3.addKeyListener(d);
        t2.addKeyListener(d);
        */
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(550,500);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }      
            
  private class BHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
                       
        try {
            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url = "jdbc:odbc:msaccessDSN";
            Connection c = DriverManager.getConnection(url);
            search(c);
            c.close();
           }
        catch(SQLException a) {
            System.out.println("Error: " + a.getMessage());
        }
        catch(Exception b) {
            System.out.println("Error: " + b.getMessage());
        }
        
       }
        
        public  void search(Connection c) throws SQLException{
            
        s1=t1.getText();
        s2=t2.getText();
        s3=t3.getText();
        s4=t4.getText();
        
  String q = "select * from EntryByTitle where t1 = '" + s1 +"'";
    if(s1==t1.getText())
{       s2=t2.getText();
        s3=t3.getText();
        s4=t4.getText();
  JOptionPane.showMessageDialog(null,"Book Not Available");
     /* s1=t1.setText();
        s2=t2.setText();
        s3=t3.setText();
        s4=t4.setText();
    */
}        

else
{
 JOptionPane.showMessageDialog(null,"Book Available");
}
    Statement ss = c.createStatement();
  ResultSet rs = ss.executeQuery(q);
        
        if  (rs.next()) {
            JOptionPane.showMessageDialog(null,"Book Available");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            return;
        }
    }
  }
      public static void main(String s[])
    {
        
        new SearchByTitle1();
         
    }
}     
   