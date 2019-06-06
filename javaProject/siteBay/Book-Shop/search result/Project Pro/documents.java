import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class documents extends JFrame implements ActionListener 
{
JButton b1,b2,b3;
JLabel l1,l2;
JTextField t1;
documents()
{
setTitle("Search Documents  SEARCH RESULT");
setSize(600,600);
setLayout(null);
b1=new JButton("BACK");
b1.setBounds(50,300,100,25);
b1.addActionListener(this);

b2=new JButton("SEARCH");
b2.setBounds(150,300,100,25);

l1=new JLabel("All or part of the file name");
l1.setBounds(100,50,300,25);
l1.setFont(new Font("SansSerif",Font.BOLD,20));

t1=new JTextField("");
t1.setBounds(100,100,200,25);

l2=new JLabel("Use advanced search ?");
l2.setBounds(100,200,300,25);
l2.setFont(new Font("SansSerif",Font.BOLD,20));

b3=new JButton("EXIT");
b3.setBounds(250,300,100,25);
b3.addActionListener(this);
add(b1);
add(b2);
add(t1);
add(l1);
add(l2);
add(b3);
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
public static void main(String Args[])
{
new documents();
}
}