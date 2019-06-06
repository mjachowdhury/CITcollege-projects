 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;

 public class Login1 extends JFrame implements ActionListener
  {
   JLabel l1,l2;
   JTextField t1;
   JPasswordField t2;
   JButton b1,b2;
   Container c;
  public Login1()
  {
 c=getContentPane();
 c.setBackground(Color.green);
 c.setLayout(new FlowLayout());
 c.setLayout(null);
 l1=new JLabel("User Name ");
 l2=new JLabel("Password ");
 b1=new JButton("Login");
 b2=new JButton("Exit");
 t1=new JTextField(15);
 t1.setToolTipText("Enter USER ID");
 t2=new JPasswordField(15);
 t2.setToolTipText("Enter Password");
 l1.setBounds(150,40,100,30);
 l2.setBounds(150,80,100,30);
 b1.setBounds(150,160,80,30);
 b2.setBounds(320,160,80,30);
 t1.setBounds(320,40,100,30);
 t2.setBounds(320,80,100,30);
 b1.addActionListener(this);
 b2.addActionListener(this); 
  c.add(l1);
  c.add(l2);
  c.add(t1);
  c.add(t2);
  c.add(b1);
  c.add(b2);
   setBounds(400,200,300,300);
 setVisible(true);
 setSize(550,280);
 setTitle("Login Form");
 }
 public void actionPerformed(ActionEvent ae)
 {
 
 if(ae.getSource()==b1)
 {
 try 
    { 
  String s1=t1.getText();
  String s2=t2.getText();
  if((s1.equals("")) && (s2.equals("")))
   {
  JOptionPane.showMessageDialog(null,"Please Enter User Name/Password");
  return;
   }  
  
      if((s1.equals("s")) && (s2.equals("s")))
      {
        this.dispose();
        new MainPage();
      }
      else
      {
       JOptionPane.showMessageDialog(null,"Invalid User Name/Password");
       t1.setText("");
       t2.setText("");

      }
    }catch(Exception e)   {}      

   }
    
  if(ae.getSource()==b2)
    {
      this.dispose();
    }	
   }   
  public static void main(String args[])
    {
  Login1 l= new Login1();
    }
    }

