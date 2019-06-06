import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class welcome1 extends JFrame implements ActionListener  
 {
        Container con;
        JLabel l1,l2,l3,l4;
        JButton b1,b2;
        Icon img1,img2,img3;

    welcome1()
  {
     con = getContentPane();
     con.setBackground(Color.yellow);

         setTitle("welcomeForm  SEARCH RESULT");
         setSize(1290,750);
         setLayout(null);  
   
         l1=new JLabel("SEARCH  RESULT");
         l1.setBounds(450,20,500,100);
         l1.setFont(new Font("SansSerif",Font.BOLD,48));

                        
  b1=new JButton("GO FOR LOGIN");
  b1.setBounds(50,200,250,50);
  b1.setFont(new Font("SansSerif",Font.BOLD,24));
b1.setToolTipText("Click for login");
 b1.addActionListener(this);
 

    b2=new JButton("EXIT");
    b2.setBounds(50,400,150,50);
    b2.setFont(new Font("SansSerif",Font.BOLD,24));
    b2.addActionListener(this);

    img1=new ImageIcon("icontexto-search-pack-01.jpg");
    l2=new JLabel(img1);
    l2.setBounds(10,0,1000,600);
    
    img2=new ImageIcon("icontexto-search-pack-01.jpg");
    l3=new JLabel(img2);
    l3.setBounds(400,0 ,1000,600);
    
    img3=new ImageIcon("icontexto-search-pack-01.jpg");
    l4=new JLabel(img3);
    l4.setBounds(780,0 ,1000,600);


   con.add(b1);
   con.add(b2);
   con.add(l1);
   con.add(l2);
   con.add(l3);
   con.add(l4);  
  setVisible(true);
setResizable(false);       
  }
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
new LoginForm1();
}
if(ae.getSource()==b2)
{
System.exit(0);
}
}
public static void main(String arg[])
 {
new welcome1();

 }
}