//package doc;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import dos.Main;
//import doc.LoginForm;
 public class Dis extends JFrame implements ActionListener
{
JTextField tid,tdis, t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
JLabel lid,ldis,lhead,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13, l14;
JButton b1,b2,b3,b4;
JFrame f3;
GridBagLayout gbl;
GridBagConstraints gbc;


public Dis()
{
setDefaultLookAndFeelDecorated(true);
f3=new JFrame("Discharge Form");
f3.setSize(800,600);
f3.setLocation(100,100);
JPanel p=new JPanel();
f3.getContentPane().add(p);
f3.setBackground(Color.green);
p.setBackground(Color.white);
gbl=new GridBagLayout();
gbc=new GridBagConstraints();
p.setLayout(gbl);


gbc.gridx=0;
gbc.gridy=0;
lhead=new JLabel(" PATIENT DISCHARGE FORM");
gbl.setConstraints(lhead,gbc);
Font ft=new Font("Algerian", Font.BOLD,30);
lhead.setFont(ft);
lhead.setForeground(Color.RED);


gbc.gridx=0;
	gbc.gridy=19;
	l14=new JLabel("          ");
	gbl.setConstraints(l14,gbc);




gbc.gridx=0;
gbc.gridy=20;
l1=new JLabel("Patient Reg No");
gbl.setConstraints(l1,gbc);


gbc.gridx=30;
gbc.gridy=20;
t1=new JTextField(15);
gbl.setConstraints(t1,gbc);


gbc.gridx=0;
gbc.gridy=30;
l2=new JLabel("Patient Name");
gbl.setConstraints(l2,gbc);


gbc.gridx=30;
gbc.gridy=30;
t2=new JTextField(15);
gbl.setConstraints(t2,gbc);


gbc.gridx=0;
gbc.gridy=40;
l3=new JLabel("Admission Date");
gbl.setConstraints(l3,gbc);

gbc.gridx=30;
gbc.gridy=40;
t3=new JTextField(15);
gbl.setConstraints(t3,gbc);


gbc.gridx=0;
gbc.gridy=50;
l4=new JLabel("Discharge Date");
gbl.setConstraints(l4,gbc);


gbc.gridx=30;
gbc.gridy=50;
t4=new JTextField(15);
gbl.setConstraints(t4,gbc);


gbc.gridx=0;
gbc.gridy=60;
l5=new JLabel("Diseases");
gbl.setConstraints(l5,gbc);



gbc.gridx=30;
gbc.gridy=60;
t5=new JTextField(15);
gbl.setConstraints(t5,gbc);



gbc.gridx=0;
gbc.gridy=70;
l6=new JLabel("Room Charges");
gbl.setConstraints(l6,gbc);


gbc.gridx=30;
gbc.gridy=70;
t6=new JTextField(15);
gbl.setConstraints(t6,gbc);



gbc.gridx=0;
gbc.gridy=80;
l8=new JLabel("Medicine Charges");
gbl.setConstraints(l8,gbc);

gbc.gridx=30;
gbc.gridy=80;
t8=new JTextField(15);
gbl.setConstraints(t8,gbc);


gbc.gridx=0;
gbc.gridy=90;
l9=new JLabel("Operation/Testing Charges");
gbl.setConstraints(l9,gbc);


gbc.gridx=30;
gbc.gridy=90;
t9=new JTextField(15);
gbl.setConstraints(t9,gbc);



gbc.gridx=0;
gbc.gridy=100;
l12=new JLabel("Docter Charges");
gbl.setConstraints(l12,gbc);



gbc.gridx=30;
gbc.gridy=100;
t12=new JTextField(15);
gbl.setConstraints(t12,gbc);


gbc.gridx=0;
gbc.gridy=110;
l13=new JLabel("Total Amount");
gbl.setConstraints(l13,gbc);


gbc.gridx=30;
gbc.gridy=110;
t13=new JTextField(15);
gbl.setConstraints(t13,gbc);

gbc.gridx=0;
gbc.gridy=120;
b1=new JButton("Submit");
gbl.setConstraints(b1,gbc);


gbc.gridx=28;
gbc.gridy=120;
b2=new JButton("Reset");
gbl.setConstraints(b2,gbc);


gbc.gridx=29;
gbc.gridy=120;
b3=new JButton("Cancel");
gbl.setConstraints(b3,gbc);

p.add(lhead);
p.add(l14);

p.add(l1);
p.add(t1);

p.add(l2);
p.add(t2);

p.add(l3);
p.add(t3);

p.add(l4);
p.add(t4);

p.add(l5);
p.add(t5);

p.add(l6);
p.add(t6);


p.add(l8);
p.add(t8);

p.add(l9);
p.add(t9);

p.add(l12);
p.add(t12);

p.add(l13);
p.add(t13);


p.add(b1);
p.add(b2);
p.add(b3);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);

f3.setVisible(true);

}

public void actionPerformed(ActionEvent ae)
{

if(ae.getSource()==b1)
{
try
{
Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
PreparedStatement ps=con.prepareStatement("insert into Discharge(RegNo, PatientName, AdmissionDate, DischargeDate,Diseases, RoomCharges, MedicineCharges, OTCharges, DoctorCharges, TotalAmount)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");


String RegNo=t1.getText();
String PatientName= t2.getText();
String AdmissionDate=t3.getText();
String DischargeDate=t4.getText();
String Diseases=t5.getText();
int RoomCharges=Integer.parseInt(t6.getText());
int MedicineCharges=Integer.parseInt(t8.getText());
int OTCharges=Integer.parseInt(t9.getText());
int DoctorCharges=Integer.parseInt(t12.getText());
int TotalAmount=Integer.parseInt(t13.getText());


ps.setString(1, RegNo);
ps.setString(2, PatientName);
ps.setString(3, AdmissionDate);
ps.setString(4, DischargeDate);
ps.setString(5, Diseases);
ps.setInt(6, RoomCharges);
ps.setInt(7, MedicineCharges);
ps.setInt(8, OTCharges);
ps.setInt(9, DoctorCharges);
ps.setInt(10, TotalAmount);
ps.executeUpdate();

JOptionPane.showMessageDialog(null,"Record Insert Successfully");

}

catch(Exception ex)
	{
		JOptionPane.showMessageDialog(null,"required field must not be blank");
	}
	}


else if(ae.getSource()==b2)
{
t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");
t6.setText("");
t8.setText("");
t9.setText("");
t12.setText("");
t13.setText("");
}

else if(ae.getSource()==b3)
{
new Main();
f3.setVisible(false);

}


}

public static void main(String args[])
{
new Dis();
}
}