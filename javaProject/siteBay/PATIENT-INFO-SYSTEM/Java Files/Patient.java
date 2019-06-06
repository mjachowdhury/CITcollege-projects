//package doc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import dos.Main;

public class Patient extends JFrame implements ActionListener
{
JTextField tid, t20,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19, lbed, lward, lreg,lhead,ladmit;
JButton b2,b3,b4,b5,b6;
JFrame f1;
GridBagLayout gbl;
GridBagConstraints gbc;

 public Patient()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("InPatient Information");
f1.setSize(800,720);
f1.setLocation(100,0);
JPanel p=new JPanel();
f1.getContentPane().add(p);
f1.setBackground(Color.green);
p.setBackground(Color.white);
gbl=new GridBagLayout();
gbc=new GridBagConstraints();

p.setLayout(gbl);

gbc.gridx=0;
gbc.gridy=0;
lhead=new JLabel("IN PATIENT IFORMATION");
gbl.setConstraints(lhead,gbc);
Font fk=new Font("Algerian", Font.BOLD,30);
lhead.setFont(fk);
lhead.setForeground(Color.RED);


gbc.gridx=0;
gbc.gridy=20;
l2=new JLabel("Search Patient By RegNo");
gbl.setConstraints(l2,gbc);

gbc.gridx=15;
gbc.gridy=20;
lreg= new JLabel("Enter RegNo");
gbl.setConstraints(lreg,gbc);

gbc.gridx=30;
gbc.gridy=20;
t20=new JTextField(10);
gbl.setConstraints(t20,gbc);

gbc.gridx=45;
gbc.gridy=20;
b2=new JButton("Search");
gbl.setConstraints(b2,gbc);


gbc.gridx=0;
gbc.gridy=30;
l3=new JLabel("Registering No");
gbl.setConstraints(l3,gbc);

gbc.gridx=15;
gbc.gridy=30;
t2=new JTextField(10);
gbl.setConstraints(t2,gbc);


gbc.gridx=30;
gbc.gridy=30;
l4=new JLabel("Patient Name");
gbl.setConstraints(l4,gbc);


gbc.gridx=45;
gbc.gridy=30;
t3=new JTextField(10);
gbl.setConstraints(t3,gbc);

gbc.gridx=0;
gbc.gridy=40;
l5=new JLabel("Admission Date");
gbl.setConstraints(l5,gbc);

gbc.gridx=15;
gbc.gridy=40;
t4=new JTextField(10);
gbl.setConstraints(t4,gbc);

gbc.gridx=30;
gbc.gridy=40;
l6=new JLabel("Ward No");
gbl.setConstraints(l6,gbc);

gbc.gridx=45;
gbc.gridy=40;
t5=new JTextField(10);
gbl.setConstraints(t5,gbc);

gbc.gridx=0;
gbc.gridy=50;
l7=new JLabel("Room No") ;
gbl.setConstraints(l7,gbc);

gbc.gridx=15;
gbc.gridy=50;
t6=new JTextField(10);
gbl.setConstraints(t6,gbc);

gbc.gridx=30;
gbc.gridy=50;
l8=new JLabel("Bed No");
gbl.setConstraints(l8,gbc);

gbc.gridx=45;
gbc.gridy=50;
t7=new JTextField(10);
gbl.setConstraints(t7,gbc);

gbc.gridx=0;
gbc.gridy=80;
l9=new JLabel("Search By Ward No And Bed No");
gbl.setConstraints(l9,gbc);

gbc.gridx=30;
gbc.gridy=80;
lward=new JLabel("Enter Ward No");
gbl.setConstraints(lward,gbc);

gbc.gridx=45;
gbc.gridy=80;
t8=new JTextField(10);
gbl.setConstraints(t8,gbc);

gbc.gridx=0;
gbc.gridy=90;
lbed  =new JLabel("Enter Bed No");
gbl.setConstraints(lbed,gbc);

gbc.gridx=15;
gbc.gridy=90;
t9=new JTextField(10);
gbl.setConstraints(t9,gbc);

gbc.gridx=45;
gbc.gridy=90;
b3 =new JButton("Search");
gbl.setConstraints(b3,gbc);

gbc.gridx=0;
gbc.gridy=100;

l10=new JLabel("Registering No");
gbl.setConstraints(l10,gbc);

gbc.gridx=15;
gbc.gridy=100;
t10=new JTextField(10);
gbl.setConstraints(t10,gbc);

gbc.gridx=30;
gbc.gridy=100;

l11=new JLabel("Patient Name");
gbl.setConstraints(l11,gbc);

gbc.gridx=45;
gbc.gridy=100;
t11=new JTextField(10);
gbl.setConstraints(t11,gbc);


gbc.gridx=0;
gbc.gridy=110;
l12=new JLabel("Admission Date");
gbl.setConstraints(l12,gbc);

gbc.gridx=15;
gbc.gridy=110;
t12=new JTextField(10);
gbl.setConstraints(t12,gbc);

gbc.gridx=30;
gbc.gridy=110;
l13=new JLabel("Room No");
gbl.setConstraints(l13,gbc);

gbc.gridx=45;
gbc.gridy=110;
t13=new JTextField(10);
gbl.setConstraints(t13,gbc);

gbc.gridx=0;
gbc.gridy=120;
l14=new JLabel("Search by Admission Date");
gbl.setConstraints(l14,gbc);

gbc.gridx=15;
gbc.gridy=120;
l15=new JLabel("Enter Date");
gbl.setConstraints(l15,gbc);

gbc.gridx=30;
gbc.gridy=120;
t14=new JTextField("dd/mm/yyyy",10);
gbl.setConstraints(t14,gbc);


gbc.gridx=45;
gbc.gridy=120;
b4=new JButton("Search");
gbl.setConstraints(b4,gbc);

gbc.gridx=0;
gbc.gridy=130;

l16=new JLabel("Show Information Of Patient");
gbl.setConstraints(l16,gbc);

gbc.gridx=15;
gbc.gridy=130;
l17=new JLabel("Enter Registering No");
gbl.setConstraints(l17,gbc);

gbc.gridx=30;
gbc.gridy=130;
t15=new JTextField(10);
gbl.setConstraints(t15,gbc);

gbc.gridx=45;
gbc.gridy=130;
b5=new JButton("Show");
gbl.setConstraints(b5,gbc);

gbc.gridx=30;
gbc.gridy=140;

b6=new JButton("Cancel");
gbl.setConstraints(b6,gbc);

p.add(lhead);

 p.add(l2);
p.add(lreg);
p.add(t20);
 p.add(b2);

 p.add(l3);
p.add(t2);

p.add(l4);
p.add(t3);

p.add(l5);
p.add(t4);

p.add(l6);
p.add(t5);

p.add(l7);
p.add(t6);

p.add(l8);
p.add(t7);

p.add(l9);
p.add(lward);

p.add(t8);
p.add(lbed);
p.add(t9);
p.add(b3);

p.add(l10);
p.add(t10);
p.add(l11);
p.add(t11);
p.add(l12);
p.add(t12);
p.add(l13);
p.add(t13);
p.add(l14);
p.add(l15);
p.add(t14);
p.add(b4);


p.add(l16);
p.add(l17);
p.add(t15);
p.add(b5);

p.add(b6);


b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
f1.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource()==b2)
	{
		try
		{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");

	String str=t20.getText();
	PreparedStatement ps=con.prepareStatement("Select * from InPatient where RegNo='"+str+"'");
	 ResultSet rs=ps.executeQuery();
	boolean flag=rs.next();
	if(flag)
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AdmissionDate=rs.getString(3);
		String WardNo=rs.getString(4);
		String RoomNo=rs.getString(5);
		String BedNo=rs.getString(6);


		t2.setText(RegNo);
		t3.setText(PatientName);
		t4.setText(AdmissionDate);
		t5.setText(WardNo);
		t6.setText(RoomNo);
		t7.setText(BedNo);

JOptionPane.showMessageDialog(null,"Record Found Successfully");
}
	}
catch(Exception ex1)
	{
		JOptionPane.showMessageDialog(null,"required field must not be blank");
	}
	}

else if(ae.getSource()==b3)
	{
		try
		{
	Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
          	String str1=t8.getText();
          	int str2=Integer.parseInt(t9.getText());
	PreparedStatement ps=con.prepareStatement("Select * from InPatient where WardNo='"+str1+"' and BedNo='"+str2+"'");
	 ResultSet rs=ps.executeQuery();
	boolean flag=rs.next();
	if(flag)
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AdmissionDate=rs.getString(3);
		String RoomNo=rs.getString(4);



		t10.setText(RegNo);
		t11.setText(PatientName);
		t12.setText(AdmissionDate);
		t13.setText(RoomNo);

JOptionPane.showMessageDialog(null,"Record Fount Successfully");
	}
	}
catch(Exception ex)
	{
		JOptionPane.showMessageDialog(null,"required field must not be blank");
	}
}
else if(ae.getSource()==b4)
		{


	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By Admission Date");
	f5.setSize(800,200);
	f5.setLocation(100,520);
	JTextField []t1=new JTextField[40];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,10));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Age"));
	p5.add(new JLabel("Gender"));
	p5.add(new JLabel("DOB"));
	p5.add(new JLabel("BloodGroup"));
	p5.add(new JLabel("PhoneNo"));
	p5.add(new JLabel("Address"));
	p5.add(new JLabel("EmailID"));
	p5.add(new JLabel("AdmissionDate"));


for(int i=0; i<40; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);

try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:PIS");
	String str2=t14.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from Registration where AdmissionDate='"+str2+"'" );

	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String Age=rs.getString(3);
		String Gender=rs.getString(4);
		String DOB=rs.getString(5);
		String BloodGroup=rs.getString(6);
		String PhoneNo=rs.getString(7);
		String Address=rs.getString(8);
		String EmailID=rs.getString(9);
		String AdmissionDate=rs.getString(10);



		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(Age);
		i++;
		t1[i].setText(Gender);
		i++;
		t1[i].setText(DOB);
		i++;
		t1[i].setText(BloodGroup);
		i++;
		t1[i].setText(PhoneNo);
		i++;
		t1[i].setText(Address);
		i++;
		t1[i].setText(EmailID);
		i++;
		t1[i].setText(AdmissionDate);
		i++;
		}
	}
	catch(Exception ex)
	{
		System.out.println("error"+ex);
		}

}


else if(ae.getSource()==b5)
		{

	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By Registration No");
	f5.setSize(800,100);
	f5.setLocation(100,620);
	JTextField []t1=new JTextField[10];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(2,10));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Age"));
	p5.add(new JLabel("Gender"));
	p5.add(new JLabel("DOB"));
	p5.add(new JLabel("BloodGroup"));
	p5.add(new JLabel("PhoneNo"));
	p5.add(new JLabel("Address"));
	p5.add(new JLabel("EMailID"));
	p5.add(new JLabel("AdmissionDate"));
for(int i=0; i<10; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:PIS");
		String str = t15.getText();
                 PreparedStatement ps=con.prepareStatement("Select * from Registration where RegNo='"+str+"'");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String Age=rs.getString(3);
		String Gender=rs.getString(4);
		String DOB=rs.getString(5);
		String BloodGroup=rs.getString(6);
		String PhoneNo=rs.getString(7);
		String Address=rs.getString(8);
		String EMailID=rs.getString(9);
		String AdmissionDate=rs.getString(10);


		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(Age);
		i++;
		t1[i].setText(Gender);
		i++;
		t1[i].setText(DOB);
		i++;
		t1[i].setText(BloodGroup);
		i++;
		t1[i].setText(PhoneNo);
		i++;
		t1[i].setText(Address);
		i++;
		t1[i].setText(EMailID);
		i++;
		t1[i].setText(AdmissionDate);
		i++;

		}
	}
	catch(Exception ex)
		{
		System.out.println("Error"+ex);
		}
}

	else if(ae.getSource()==b6)
{

new Main();
f1.setVisible(false);
}

}
	public static void main(String args[])
	{
		new Patient();
	}
}