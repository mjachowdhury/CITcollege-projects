 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;

 public class SearchByCat extends JFrame implements ActionListener
  {
   JLabel l1;
   JTextField t1;
   JButton b1,b2;
   Container cont;
  public SearchByCat()
  {
 cont=getContentPane();
 cont.setBackground(Color.cyan);
 cont.setLayout(new FlowLayout());
 cont.setLayout(null);
 l1=new JLabel("Category");
 b1=new JButton("Ok");
 b2=new JButton("Cancel");
 t1=new JTextField(20);
 l1.setBounds(100,50,100,30);
 t1.setBounds(250,50,100,30);
 b1.setBounds(100,150,100,30);
 b2.setBounds(250,150,100,30);
 b1.addActionListener(this);
 b2.addActionListener(this); 
  cont.add(l1);
  cont.add(t1);
  cont.add(b1);
  cont.add(b2);
  setBounds(400,200,300,300);
 setVisible(true);
 setSize(550,280);
 setTitle("Search By Category");
 }
 public void actionPerformed(ActionEvent ae)
 { 
  if(ae.getSource()==b1)
    {
      JOptionPane.showMessageDialog(null,"Book Available");
    }	
  
    
  if(ae.getSource()==b2)
    {
      this.dispose();
    }	
   }   
  public static void main(String args[])
    {
     new   SearchByCat();
    }
    }

