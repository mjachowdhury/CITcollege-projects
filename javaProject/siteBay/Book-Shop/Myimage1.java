import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Myimage1 extends JFrame implements ActionListener
{
Container c;
JLabel lbl,l1,l2,l3;
JButton btn,b; 
Icon img1,img2,img3; 

 Myimage1()
 {
 c=getContentPane();
 c.setBackground(Color.yellow);
 setTitle("Book Shop ");
 setSize(1025,800);
 setLayout(null);
 
 lbl=new JLabel(" Welcome To Book Shop ");
 lbl.setBounds(250,10,550,200);
 lbl.setFont(new Font("TimesRoman", Font.BOLD, 50));
  
 btn=new JButton("Click Here to Login");
 btn.setBounds(50,350,200,30);
 btn.setFont(new Font("TimesRoman", Font.BOLD, 18));

 btn.addActionListener(this);
 
 b=new JButton("Exit");
 b.setBounds(750,350,100,30);
 b.setFont(new Font("TimesRoman", Font.BOLD, 18));
 
 b.addActionListener(this);

 img1=new ImageIcon("book5.jpg");
 img2=new ImageIcon("book8.jpg");
 img3=new ImageIcon("book10.jpg");
 
 l1=new JLabel(img1);
 l1.setBounds(5,20,250,350);
 l2=new JLabel(img2);
 l2.setBounds(780,20,250,360);
 l3=new JLabel(img3);
 l3.setBounds(250,200,500,600);
 
 c.add(lbl);
 c.add(btn);
 c.add(b);
 c.add(l1);
 c.add(l2);
 c.add(l3);

 setVisible(true);
}

public void actionPerformed(ActionEvent ae)
 {
 if(ae.getSource()==btn)
{ 
 this.dispose();
 new Login1();
 }
  if(ae.getSource()==b)
{
 System.exit(0);
}
}

public static void main(String args[])
 {
 Myimage1 obj=new Myimage1();
 }
}