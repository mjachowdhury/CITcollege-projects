import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener
{

Container cont;
JLabel l1;
Icon img;
JButton b;
MenuBar mb;
Menu mnu1,mnu2,mnu3,mnu4;
MenuItem mitem1,mitem2,mitem3,mitem4,mitem5,mitem6,mitem7;

public MainPage()
{
cont=getContentPane();
cont.setBackground(Color.orange);
cont.setLayout(new FlowLayout());
cont.setLayout(null);
b=new JButton("Exit/Cancel");
b.setBounds(160,330,150,40);
b.setFont(new Font("Arial",Font.BOLD,18));
b.addActionListener(this);
img=new ImageIcon("book6.jpg");
l1=new JLabel(img);
l1.setBounds(100,50,250,350);
cont.add(b);
cont.add(l1);
 

mb=new MenuBar();
mnu1=new Menu("Entry A New Book");
mnu2=new Menu("Search Books");
mnu3=new Menu("Report of All Books");
mnu4=new Menu("Sale A Book");


mitem1=new MenuItem("Entry A New Book by Title");
mitem1.addActionListener(this);
mitem2=new MenuItem("Entry A New Book by Auther Name");
mitem2.addActionListener(this);
mitem3=new MenuItem("Entry A New Book by Category");
mitem3.addActionListener(this);

mitem4=new MenuItem("Search Book by Title");
mitem4.addActionListener(this);
mitem5=new MenuItem("Search Book by Auther Name");
mitem5.addActionListener(this);
mitem6=new MenuItem("Search Book by Category");
mitem6.addActionListener(this);
mitem7=new MenuItem("sale a book");
mitem7.addActionListener(this);


setMenuBar(mb);
mb.add(mnu1);
mb.add(mnu2);
mb.add(mnu3);
mb.add(mnu4);

mnu1.add(mitem1);
mnu1.add(mitem2);
mnu1.add(mitem3);
mnu2.add(mitem4);
mnu2.add(mitem5);
mnu2.add(mitem6);
mnu4.add(mitem7);

setVisible(true);
setSize(500,450);
setResizable(false);
setTitle("MAIN PAGE");
}
public void actionPerformed(ActionEvent ae)

{
 if(ae.getSource()==mitem1)
{
 new EntryByTitle();
 }

 if(ae.getSource()==mitem2)
{
 new EntryByAName();
 }
 if(ae.getSource()==mitem3)
{
 new EntryByCat();
 }
 if(ae.getSource()==mitem4)
{setVisible(true);
 new SearchByTitle();
 }
 if(ae.getSource()==mitem5)
{
 new SearchByAName();
 }
 if(ae.getSource()==mitem6)
{
 new SearchByCat();
 }
 if(ae.getSource()==mitem7)
{
 new SaleaBook();
 }
 
 if(ae.getSource()==b)
{
 this.dispose();
 }

 }

public static void main(String args[])

{
  new MainPage();
}
}

