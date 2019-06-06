import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class SelectItem1 extends JFrame implements ActionListener
 {
        Container con;
        JButton b1,b2,l1,l2,l3,l4;
        JLabel l5;
     SelectItem1()
  {
    con=getContentPane();
   // con.setBackground(Color.cyan);
     setTitle("SelectItem  SEARCH RESULT");
     setSize(600,600);
     setLayout(null);
    l5=new JLabel("what do you want to search for ?");
    l5.setBounds(40,20,400,50);
    l5.setFont(new Font("SansSerif",Font.BOLD,24));

    l1=new JButton("Pictures or images");
    l1.setBounds(40,100,300,50);
    l1.setFont(new Font("SansSerif",Font.BOLD,24));
    l1.addActionListener(this);

    l2=new JButton("Music or video");
    l2.setBounds(40,200,300,50);
    l2.setFont(new Font("SansSerif",Font.BOLD,24));
    l2.addActionListener(this);

    l3=new JButton("All files and folders");
    l3.setBounds(40,300,300,50);
    l3.setFont(new Font("SansSerif",Font.BOLD,24));
    l3.addActionListener(this);

    l4=new JButton("Documents(word processing, spreadsheet)");
    l4.setBounds(40,400,600,50);
    l4.setFont(new Font("SansSerif",Font.BOLD,24));
    l4.addActionListener(this);

    b1=new JButton("SEARCH");
    b1.setBounds(120,500,150,25);
    b1.setFont(new Font("SansSerif",Font.BOLD,20));
    b1.addActionListener(this);

    b2=new JButton("EXIT");
    b2.setBounds(380,500,150,25);
    b2.setFont(new Font("SansSerif",Font.BOLD,20));
    b2.addActionListener(this);

   con.add(b1);
   con.add(b2);
   con.add(l1);
   con.add(l2);
   con.add(l3);
   con.add(l4);
   con.add(l5);
setVisible(true);
setResizable(false);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
new Direct();
}
if(ae.getSource()==b2)
{
System.exit(0);
}
if(ae.getSource()==l1)
{
new search();
}
   if(ae.getSource()==l2)
   {
     new songs();
    }
if(ae.getSource()==l3)
{
new Files();
}
if(ae.getSource()==l4)
{
new documents();
}
}
public static void main(String arg[])
 {
new SelectItem1();

  } 
}