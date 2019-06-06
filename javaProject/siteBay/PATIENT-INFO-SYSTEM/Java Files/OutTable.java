//package doc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import dos.Main;

public class OutTable extends JFrame implements ActionListener
{
JTextField  t2,t3,t4,t5,t6;
JLabel l, l1,l2,l3,l4,l5,lhead, l6, l7, l8;
JButton b1,b2,b3,b4,b5;
JFrame f1;
JPanel p, p1;
GridBagLayout gbl;
GridBagConstraints gbc;


public OutTable()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("Out Patient Information");
f1.setSize(700,600);
f1.setLocation(200,100);
p=new JPanel();
p1=new JPanel();
f1.getContentPane().add(p);
f1.getContentPane().add(p1);
f1.setBackground(Color.green);
p.setBackground(Color.white);
p1.setBackground(Color.white);
p.setSize(600,100);
p.setLocation(0,100);
p1.setSize(600,500);
p1.setLocation(0,100);
gbl=new GridBagLayout();
gbc=new GridBagConstraints();
p.setLayout(gbl);
p1.setLayout(gbl);





gbc.gridx=10;
gbc.gridy=0;
l=new JLabel("       OUT PATIENT IFORMATION");
gbl.setConstraints(l,gbc);

gbc.gridx=10;
gbc.gridy=5;
lhead=new JLabel("    (PREVIOUS HISTORY OF PATIENTS)");
gbl.setConstraints(lhead,gbc);

Font ft=new Font("Algerian", Font.BOLD,30);
l.setFont(ft);
l.setForeground(Color.RED);

Font fk=new Font("Algerian", Font.BOLD,30);
lhead.setFont(fk);
lhead.setForeground(Color.RED);




gbc.gridx=0;
gbc.gridy=15;
l1=new JLabel("Search by Name               ");
gbl.setConstraints(l1,gbc);

gbc.gridx=15;
gbc.gridy=15;
t2=new JTextField(15);
gbl.setConstraints(t2,gbc);

gbc.gridx=30;
gbc.gridy=15;
l5=new JLabel("            ");
gbl.setConstraints(l5,gbc);


gbc.gridx=45;
gbc.gridy=15;
b1=new JButton("Search");
gbl.setConstraints(b1,gbc);

gbc.gridx=0;
gbc.gridy=25;
l2=new JLabel("Search by Patient ID         ");
gbl.setConstraints(l2,gbc);

gbc.gridx=15;
gbc.gridy=25;
t3=new JTextField(15);
gbl.setConstraints(t3,gbc);


gbc.gridx=30;
gbc.gridy=25;
l6=new JLabel("            ");
gbl.setConstraints(l6,gbc);


gbc.gridx=45;
gbc.gridy=25;
b2=new JButton("Search");
gbl.setConstraints(b2,gbc);


gbc.gridx=0;
gbc.gridy=35;
l3=new JLabel("Search by Admission Date      ");
gbl.setConstraints(l3,gbc);


gbc.gridx=15;
gbc.gridy=35;
t4=new JTextField("dd/mm/yyyy",15);
gbl.setConstraints(t4,gbc);


gbc.gridx=30;
gbc.gridy=35;
l7=new JLabel("            ");
gbl.setConstraints(l7,gbc);


gbc.gridx=45;
gbc.gridy=35;
b3=new JButton("Search");
gbl.setConstraints(b3,gbc);



gbc.gridx=0;
gbc.gridy=45;
l4=new JLabel("Search by Discharge Date       ");
gbl.setConstraints(l4,gbc);


gbc.gridx=15;
gbc.gridy=45;
t5=new JTextField("dd/mm/yyyy",15);
gbl.setConstraints(t5,gbc);

gbc.gridx=30;
gbc.gridy=45;
l8=new JLabel("            ");
gbl.setConstraints(l8,gbc);


gbc.gridx=45;
gbc.gridy=45;
b4=new JButton("Search");
gbl.setConstraints(b4,gbc);


gbc.gridx=15;
gbc.gridy=55;
b5=new JButton("Cancel");
gbl.setConstraints(b5,gbc);



p.add(l);
p.add(lhead);

p1.add(l1);
p1.add(t2);
p1.add(l5);
p1.add(b1);

p1.add(l2);
p1.add(t3);
p1.add(l6);
p1.add(b2);

p1.add(l3);
p1.add(t4);
p1.add(l7);
p1.add(b3);

p1.add(l4);
p1.add(t5);
p1.add(l8);
p1.add(b4);


p1.add(b5);



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
	JFrame f5=new JFrame("Search By Name");
	f5.setSize(700,200);
	f5.setLocation(200,500);
	JTextField []t1=new JTextField[28];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,7));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Admission Date"));
	p5.add(new JLabel("Discharge Date"));
	p5.add(new JLabel("DoctorName"));
	p5.add(new JLabel("Diseases"));
	p5.add(new JLabel("BillingAmount"));
for(int i=0; i<28; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");

	String str2=t2.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from OutPatient where PatientName='"+str2+"'");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AddmissionDate=rs.getString(3);
		String DischargeDate=rs.getString(4);
		String DoctorName=rs.getString(5);
		String Diseases=rs.getString(6);
		String BillingAmount=rs.getString(7);

		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(AddmissionDate);
		i++;
		t1[i].setText(DischargeDate);
		i++;
		t1[i].setText(DoctorName);
		i++;
		t1[i].setText(Diseases);
		i++;
		t1[i].setText(BillingAmount);
		i++;

		}
	}
	catch(Exception ex)
	{
		System.out.println("error"+ex);
		}
		    }

else if(ae.getSource()==b2)
{
	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By ID");
	f5.setSize(700,200);
	f5.setLocation(200,500);
	JTextField []t1=new JTextField[28];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,7));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Admission Date"));
	p5.add(new JLabel("Discharge Date"));
	p5.add(new JLabel("DoctorName"));
	p5.add(new JLabel("Diseases"));
	p5.add(new JLabel("BillingAmount"));
for(int i=0; i<28; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
	String str1=t3.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from OutPatient where RegNo='"+str1+"'" );
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AddmissionDate=rs.getString(3);
		String DischargeDate=rs.getString(4);
		String DoctorName=rs.getString(5);
		String Diseases=rs.getString(6);
		String BillingAmount=rs.getString(7);

		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(AddmissionDate);
		i++;
		t1[i].setText(DischargeDate);
		i++;
		t1[i].setText(DoctorName);
		i++;
		t1[i].setText(Diseases);
		i++;
		t1[i].setText(BillingAmount);
		i++;

		}
	}
	catch(Exception ex)
	{
		System.out.println("error"+ex);
		}

}

 else if(ae.getSource()==b3)
   	{


	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By Admission Date");
	f5.setSize(700,200);
	f5.setLocation(200,500);
	JTextField []t1=new JTextField[28];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,7));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Admission Date"));
	p5.add(new JLabel("Discharge Date"));
	p5.add(new JLabel("DoctorName"));
	p5.add(new JLabel("Diseases"));
	p5.add(new JLabel("BillingAmount"));


for(int i=0; i<28; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);

try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:PIS");
	String str2=t4.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from OutPatient where AdmissionDate='"+str2+"'" );

	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AddmissionDate=rs.getString(3);
		String DischargeDate=rs.getString(4);
		String DoctorName=rs.getString(5);
		String Diseases=rs.getString(6);
		String BillingAmount=rs.getString(7);

		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(AddmissionDate);
		i++;
		t1[i].setText(DischargeDate);
		i++;
		t1[i].setText(DoctorName);
		i++;
		t1[i].setText(Diseases);
		i++;
		t1[i].setText(BillingAmount);
		i++;
		}
	}
	catch(Exception ex)
	{
		System.out.println("error"+ex);
		}

}


else if(ae.getSource()==b4)

	{
setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Search By Discharge Date");
	f5.setSize(700,200);
	f5.setLocation(200,500);
	JTextField []t1=new JTextField[28];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(5,7));
	p5.add(new JLabel("Patient Registration No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Admission Date"));
	p5.add(new JLabel("Discharge Date"));
	p5.add(new JLabel("DoctorName"));
	p5.add(new JLabel("Diseases"));
	p5.add(new JLabel("BillingAmount"));
for(int i=0; i<28; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
}
f5.setVisible(true);
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:PIS");
	String str3=t5.getText();
 	PreparedStatement ps=con.prepareStatement("Select * from OutPatient where DischargeDate='"+str3+"'");
	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AddmissionDate=rs.getString(3);
		String DischargeDate=rs.getString(4);
		String DoctorName=rs.getString(5);
		String Diseases=rs.getString(6);
		String BillingAmount=rs.getString(7);

		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(AddmissionDate);
		i++;
		t1[i].setText(DischargeDate);
		i++;
		t1[i].setText(DoctorName);
		i++;
		t1[i].setText(Diseases);
		i++;
		t1[i].setText(BillingAmount);
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
		new OutTable();
	}
}