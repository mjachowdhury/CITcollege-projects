import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
class LoginForm1 extends JFrame implements ActionListener 
 {
    String name;
    String pass;
    String n="abcd";
    String p="1234";

        Container con;
        JLabel l1,l2;
        JButton b1,b2;
        JTextField t1;
         JPasswordField t2;
    LoginForm1()
  {
        con=getContentPane();
        //con.setBackground(Color.cyan);

         setTitle("LoginForm   SEARCH RESULT");
         setSize(600,600);
         setLayout(null);
  
      l1=new JLabel("USER NAME");
      l1.setBounds(100,80,200,25);
      l1.setFont(new Font("SansSerif",Font.BOLD,24));
           t1=new JTextField("");
           t1.setBounds(325,80,200,35);
           t1.setEditable(true);
            t1.setToolTipText("Enter user name");
   
      l2=new JLabel("PASSWORD");
      l2.setBounds(100,180,200,25);
      l2.setFont(new Font("SansSerif",Font.BOLD,24));
           t2=new JPasswordField(10);
           t2.setBounds(325,180,200,35);
           t2.setEditable(true);
         // t2.setEchoChar('#');
           t2.setToolTipText("Enter password");   
  b1=new JButton("OK");
  b1.setBounds(150,400,150,50);
  b1.setFont(new Font("SansSerif",Font.BOLD,24));
  b1.addActionListener(this);


    b2=new JButton("EXIT");
    b2.setBounds(400,400,150,50);
    b2.setFont(new Font("SansSerif",Font.BOLD,24));
    b2.addActionListener(this);


   con.add(l1);
   con.add(l2);
   con.add(b1);
   con.add(b2);
   con.add(t1);
   con.add(t2);
       setVisible(true);
       setResizable(false);
  }
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
   name=t1.getText();
   pass=t2.getText();
     if(name.equalsIgnoreCase(n)) 
         {
                if (pass.equalsIgnoreCase(p))
                {
                  new SelectItem1();
                  }
                     else 
                    {
                   JOptionPane.showMessageDialog(null,"WRONG PASSWORD");
                     }
         }
         else
               {
                 JOptionPane.showMessageDialog(null,"WRONG USER NAME");
                 }
           
}
if(ae.getSource()==b2)
{
System.exit(0);
}
}
public static void main(String arg[])
 {
new LoginForm1();

  }
}