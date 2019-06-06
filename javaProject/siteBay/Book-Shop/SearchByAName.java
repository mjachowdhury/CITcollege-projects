 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 public class SearchByAName extends JFrame 
    implements ActionListener,ItemListener
  {
   JLabel l1;
   JTextField t1;
   JButton b1,b2;
   JComboBox cmb;
   Container c;
  
  public SearchByAName()
  {
   
 c=getContentPane();
 c.setBackground(Color.green);
 c.setLayout(new FlowLayout());
 c.setLayout(null);
 l1=new JLabel("Auther Name");
 l1.setBounds(10,15,100,60);
 b1=new JButton("Ok");
 b1.setBounds(10,100,60,30);
 t1=new JTextField(30);
 t1.setToolTipText("Enter Auther Name");
 t1.setBounds(100,30,100,30);
 
 b2=new JButton("Cancel");
 b2.setBounds(100,100,80,30);
 cmb =new JComboBox();
 cmb.setBounds(240,30,240,30);
 b1.addActionListener(this);
 b2.addActionListener(this); 
 
 cmb.setEditable(true);
 cmb.addItem("Galvin");
 cmb.addItem("Herbert Shieldt");
 cmb.addItemListener(this);
  c.add(l1);
  c.add(t1);
  c.add(b1);
  c.add(b2);
  c.add(cmb);
 setTitle("Search Book by Author Name");
 setSize(500,300);
 setVisible(true);
 
 }
  
 public void itemStateChanged(ItemEvent ie)
{
    
  String str=(String)ie.getItem();
  	
  }  
   
 public void actionPerformed(ActionEvent ae)
 { 
  if(ae.getSource()==b1)
    {
     JOptionPane.showMessageDialog(null,"Book Available");
    }	
  
    
  if(ae.getSource()==b2)
    {
      System.exit(0);
    }	
   }   

public static void main(String str[])
{
  new SearchByAName();
}
}