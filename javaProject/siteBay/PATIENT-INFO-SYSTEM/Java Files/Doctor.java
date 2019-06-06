//package doc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import dos.Main;

public class Doctor extends JFrame implements ActionListener
{
JTextField tid, t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,lhead,lbb, lb, ls;
JButton b1,b2,b3,b4, b5;
JFrame f1;
JPanel p;
GridBagLayout gbl;
GridBagConstraints gbc;

public Doctor()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("Doctors Information");
f1.setSize(800,700);
f1.setLocation(100,0);
p=new JPanel();

p.setSize(800,600);
p.setLocation(0,200);

f1.getContentPane().add(p);
f1.setBackground(Color.green);
p.setBackground(Color.white);
gbl=new GridBagLayout();
gbc=new GridBagConstraints();

p.setLayout(gbl);

gbc.gridx=0;
gbc.gridy=0;
lhead=new JLabel("DOCTORS INFORMATION");
Font ft=new Font("Algerian", Font.BOLD,30);
lhead.setFont(ft);
lhead.setForeground(Color.RED);
gbl.setConstraints(lhead,gbc);


gbc.gridx=0;
gbc.gridy=14;
ls=new JLabel("                                         ");
gbl.setConstraints(ls,gbc);


gbc.gridx=0;
gbc.gridy=15;
l1=new JLabel("Show List Of Doctors");
gbl.setConstraints(l1,gbc);

gbc.gridx=40;
gbc.gridy=15;
b1=new JButton(" Show  ");
gbl.setConstraints(b1,gbc);

gbc.gridx=0;
gbc.gridy=25;
l2=new JLabel("Search By DoctorID");
gbl.setConstraints(l2,gbc);

gbc.gridx=15;
gbc.gridy=25;
t1=new JTextField(10);
gbl.setConstraints(t1,gbc);

gbc.gridx=40;
gbc.gridy=25;
b2=new JButton("Search");
gbl.setConstraints(b2,gbc);

gbc.gridx=0;
gbc.gridy=35;
lbb=new JLabel("DoctorID");
gbl.setConstraints(lbb,gbc);


gbc.gridx=15;
gbc.gridy=35;
t3=new JTextField(10);
gbl.setConstraints(t3,gbc);


gbc.gridx=0;
gbc.gridy=45;
l6=new JLabel("Name");
gbl.setConstraints(l6,gbc);


gbc.gridx=15;
gbc.gridy=45;
t4=new JTextField(10);
gbl.setConstraints(t4,gbc);


gbc.gridx=0;
gbc.gridy=55;
l7=new JLabel("Gender");
gbl.setConstraints(l7,gbc);


gbc.gridx=15;
gbc.gridy=55;
t5=new JTextField(10);
gbl.setConstraints(t5,gbc);


gbc.gridx=0;
gbc.gridy=65;
l8=new JLabel("Specialization/Department");
gbl.setConstraints(l8,gbc);


gbc.gridx=15;
gbc.gridy=65;
t6=new JTextField(10);
gbl.setConstraints(t6,gbc);


gbc.gridx=0;
gbc.gridy=75;
l9=new JLabel("PhoneNo");
gbl.setConstraints(l9,gbc);


gbc.gridx=15;
gbc.gridy=75;
t7=new JTextField(10);
gbl.setConstraints(t7,gbc);


gbc.gridx=0;
gbc.gridy=85;
l10=new JLabel("Timing");
gbl.setConstraints(l10,gbc);


gbc.gridx=15;
gbc.gridy=85;
t8=new JTextField(10);
gbl.setConstraints(t8,gbc);

gbc.gridx=0;
gbc.gridy=95;
l3=new JLabel("Search By Name");
gbl.setConstraints(l3,gbc);

gbc.gridx=15;
gbc.gridy=95;
t9=new JTextField(10);
gbl.setConstraints(t9,gbc);

gbc.gridx=40;
gbc.gridy=95;
b3=new JButton("Search");
gbl.setConstraints(b3,gbc);


gbc.gridx=0;
gbc.gridy=105;
l4=new JLabel("Search By Specialization/Department");
gbl.setConstraints(l4,gbc);

gbc.gridx=15;
gbc.gridy=105;
t10=new JTextField(10);
gbl.setConstraints(t10,gbc);

gbc.gridx=30;
gbc.gridy=105;
lb=new JLabel("                           ");
gbl.setConstraints(lb,gbc);


gbc.gridx=40;
gbc.gridy=105;
b4=new JButton("Search");
gbl.setConstraints(b4,gbc);




gbc.gridx=15;
gbc.gridy=115;
b5 = new JButton("Cancel");
gbl.setConstraints(b5,gbc);


p.add(lhead);
p.add(ls);
p.add(l1);
p.add(b1);


p.add(l2);
p.add(t1);

p.add(b2);
p.add(lbb);

p.add(t3);

p.add(l6);
p.add(t4);

p.add(l7);
p.add(t5);

p.add(l8);
p.add(t6);

p.add(l9);
p.add(t7);

p.add(l10);
p.add(t8);

p.add(l3);
p.add(t9);

p.add(b3);

p.add(l4);
p.add(t10);
p.add(lb);
p.add(b4);

p.add(b5);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
f1.setVisible(true);
}




public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource()==b1)
	{
	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("List Of Doctors");
	f5.setSize(800,700);
	f5.setLocation(100,0);
	JTextField []t1=new JTextField[180];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(31,6));
	p5.add(new JLabel("DoctorID"));
	p5.add(new JLabel("Name"));
	p5.add(new JLabel("Gender"));
	p5.add(new JLabel("Specialization"));
	p5.add(new JLabel("PhoneNo"));
	p5.add(new JLabel("Timing"));

for(int i=0; i<180; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
 	PreparedStatement ps=con.prepareStatement("Select * from Doctor ");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String DoctorID=rs.getString(1);
		String Name=rs.getString(2);
		String Gender=rs.getString(3);
		String Specialization=rs.getString(4);
		String PhoneNo=rs.getString(5);
		String Timing=rs.getString(6);


		t1[i].setText(DoctorID);
		i++;
		t1[i].setText(Name);
		i++;
		t1[i].setText(Gender);
		i++;
		t1[i].setText(Specialization);
		i++;
		t1[i].setText(PhoneNo);
		i++;
		t1[i].setText(Timing);
		i++;

		}
	}
	catch(Exception ex)
	{
JOptionPane.showMessageDialog(null,"Record Not Found ");
		}

}

	else if(ae.getSource()==b2)
	{
		try
		{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");

           	String str=t1.getText();
	PreparedStatement ps=con.prepareStatement("Select * from Doctor where DoctorID='"+str+"'");

	 ResultSet rs=ps.executeQuery();
	boolean flag=rs.next();
	if(flag)
	{
		String DoctorID=rs.getString(1);
		String Name=rs.getString(2);
		String Gender=rs.getString(3);
		String Specialization=rs.getString(4);
		String PhoneNo=rs.getString(5);
		String Timing=rs.getString(6);


		t3.setText(DoctorID);
		t4.setText(Name);
		t5.setText(Gender);
		t6.setText(Specialization);
		t7.setText(PhoneNo);
		t8.setText(Timing);

JOptionPane.showMessageDialog(null,"Record Found Successfully");
	}
	}
catch(Exception ex)
	{
		JOptionPane.showMessageDialog(null,"Please Enter This 'DR000' Format");
	}

}
	else if(ae.getSource()==b3)
	{
	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("search By Name");
	f5.setSize(800,200);
	f5.setLocation(100,525);
	JTextField []t1=new JTextField[24];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,6));
	p5.add(new JLabel("DoctorID"));
	p5.add(new JLabel("Name"));
	p5.add(new JLabel("Gender"));
	p5.add(new JLabel("Specialization"));
	p5.add(new JLabel("PhoneNo"));
	p5.add(new JLabel("Timing"));

for(int i=0; i<24; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
	String str1 = t9.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from Doctor where Name='"+str1+"'");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String DoctorID=rs.getString(1);
		String Name=rs.getString(2);
		String Gender=rs.getString(3);
		String Specialization=rs.getString(4);
		String PhoneNo=rs.getString(5);
		String Timing=rs.getString(6);


		t1[i].setText(DoctorID);
		i++;
		t1[i].setText(Name);
		i++;
		t1[i].setText(Gender);
		i++;
		t1[i].setText(Specialization);
		i++;
		t1[i].setText(PhoneNo);
		i++;
		t1[i].setText(Timing);
		i++;

		}
	}
	catch(Exception ex2)
	{
		JOptionPane.showMessageDialog(null,"error"+ex2);
		}

}
	else if(ae.getSource()==b4)
	{
	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By Specialization And Department");
	f5.setSize(800,200);
	f5.setLocation(100,525);
	JTextField []t1=new JTextField[24];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,6));
	p5.add(new JLabel("DoctorID"));
	p5.add(new JLabel("Name"));
	p5.add(new JLabel("Gender"));
	p5.add(new JLabel("Specialization"));
	p5.add(new JLabel("PhoneNo"));
	p5.add(new JLabel("Timing"));

for(int i=0; i<24; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:PIS");
	String str2 = t10.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from Doctor where Specialization='"+str2+"'");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String DoctorID=rs.getString(1);
		String Name=rs.getString(2);
		String Gender=rs.getString(3);
		String Specialization=rs.getString(4);
		String PhoneNo=rs.getString(5);
		String Timing=rs.getString(6);


		t1[i].setText(DoctorID);
		i++;
		t1[i].setText(Name);
		i++;
		t1[i].setText(Gender);
		i++;
		t1[i].setText(Specialization);
		i++;
		t1[i].setText(PhoneNo);
		i++;
		t1[i].setText(Timing);
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
new Main();
f1.setVisible(false);
}


	}

	public static void main(String args[])
	{
		new Doctor();
	}
}