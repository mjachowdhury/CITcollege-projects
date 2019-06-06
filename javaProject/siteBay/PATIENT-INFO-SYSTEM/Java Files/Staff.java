//package doc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import dos.Main;

public class Staff extends JFrame implements ActionListener
{
JTable tb1;
JScrollPane sp1;
String[] field={ "Department" , "Doctors", "Nurses", "WardBoys", "Helpers", "Guards", "Sweepers"};
JFrame f1;
JButton b1;

public Staff()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("Staff Table");
f1.setSize(500,300);
f1.setLocation(100,100);
JPanel p=new JPanel();
f1.getContentPane().add(p);
f1.setBackground(Color.white);


Object[][] data={
{"General","4","6","4","2","2","2"},
{"Child","2","6","4","2","2","2"},
{"Eye","1","2","1","2","1","2"},
{"Heart","2","6","4","6","4","4"},
{"Surgeon","2","6","5","6","4","2"},
{"Maternity","4","6","0","0","0","0"},
{"Laboratory ","4","2","2","2","2","2"},
{"Skin","2","2","1","1","0","1"},
{"Medical","2","2","1","1","0","1"},
{"Brain","2","2","1","1","0","1"},
{"Dental","1","2","1","1","0","1"},
{"Total","26","42","24","24","15","18"},

};

b1= new JButton("Exit");
b1.addActionListener(this);
 tb1=new JTable(data, field);


 tb1.setEnabled(false);


sp1=new JScrollPane(tb1);
p.add(b1);


p.add(sp1);

f1.setVisible(true);



}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
new Main();
f1.setVisible(false);
}
}


public static void main(String args[])
{
new Staff();
}
}