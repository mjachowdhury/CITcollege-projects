 //import java.sql.*;
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 public class EntryByAName extends JFrame implements ActionListener
 {

 JLabel l1,l2;
 JButton b,b1,b2,b3;
 JTable tab;
 JScrollPane sp;
 JTextField t1,t2;
String[][] data=new String[100][100];
String[] col={"Auther Name","Auther ID"};
 Container cont;
 public EntryByAName ()
 {
 cont=getContentPane();
 cont.setBackground(Color.blue);
 cont.setLayout(new FlowLayout());
 cont.setLayout(null);
 tab=new JTable(data,col);
 sp=new JScrollPane(tab);
 sp.setBounds(260,40,200,170);
 cont.add(sp);
 l1=new JLabel("Auther Name");
 l1.setBounds(20,40,100,30);
 l2=new JLabel("Auther ID");
 l2.setBounds(20,80,100,30);
 t1=new JTextField();
 t1.setBounds(140,40,100,30);

  t2=new JTextField();
  t2.setBounds(140,80,100,30);
  //t2.setEditable(false);
  b=new JButton("save");
  b.setBounds(20,140,70,30);
  b.addActionListener(this);

   b1=new JButton("cancel");
   b1.setBounds(100,140,70,30);
   b1.addActionListener(this);

   b2=new JButton("edit");
   b2.setBounds(180,140,70,30);
   b2.addActionListener(this);

   b3=new JButton("delete");
   b3.setBounds(20,180,230,30);
   b3.addActionListener(this);

   cont.add(l1);
   cont.add(l2);
   cont.add(t1);
   cont.add(t2);
   cont.add(b);
   cont.add(b1);
   cont.add(b3);
   cont.add(b2);
   setTitle("EntryByAutherName");
   setResizable(false);
   setVisible(true);
   setSize(500,250);  
}
  
   public void actionPerformed(ActionEvent ae)
   {

   if(ae.getSource()==b1)
   {
   this.dispose();
   }
}
}