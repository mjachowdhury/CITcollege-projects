//package doc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import dos.Main;

public class HT extends JFrame implements ActionListener
{
JTable tb1;
JScrollPane sp1;
String[] field={ "Department", "ICU", "CCU", "Operation Theater", "Medical Store", "Wards", "Rooms", "Beds"};
JFrame f1;
JButton b1;

public HT()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("Hospital Table");
f1.setSize(500,300);
f1.setLocation(100,100);
JPanel p=new JPanel();
f1.getContentPane().add(p);
f1.setBackground(Color.white);


Object[][] data={
{"General","0","0","0","1","2","6", "30"},
{"Child","0","0","0","1","2","4", "20"},
{"Eye","0","0","1","1","1","5", "20"},
{"Heart","1","1","2","2","3","15", "100"},
{"Surgeon","2","1","2","1","4","12", "60"},
{"Maternity","2","1","2","1","4","20","120"},
{"Laboratory ","0","0","0","0","0","2","0"},
{"Skin","0","0","0","1","0","1","5"},
{"Medical","0","0","0","0","0","1", "0"},
{"Brain","1","1","1","1","2","10","40"},
{"Dental","0","0","1","1","1","2","10"},
{"Total","6","4","9","10","19","78", "405"},

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
new HT();
}
}