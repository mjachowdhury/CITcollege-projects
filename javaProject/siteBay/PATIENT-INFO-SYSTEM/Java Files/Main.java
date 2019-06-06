//package dos;
//import doc.Registration;
//import doc.Doctor;
//import doc.Staff;
//import doc.HT;
//import doc.Patient;
//import doc.OutTable;
//import doc.Dis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main extends JFrame implements ActionListener
{

JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,lhead, lb;
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
JFrame f4;

 public Main()
{
setDefaultLookAndFeelDecorated(true);
f4=new JFrame("Welcome to 'SANJEEVANI HOSPITAL'");
f4.setSize(500,700);
f4.setLocation(50,50);
JPanel p=new JPanel();
f4.getContentPane().add(p);
f4.setBackground(Color.green);
p.setBackground(Color.white);
p.setLayout(new GridLayout(10,2));
f4.setResizable(false);

lhead=new JLabel("   SANJEEVANI");
	Font fk=new Font("Algerian", Font.BOLD,30);
	lhead.setFont(fk);
	lhead.setForeground(Color.RED);


l1=new JLabel("  Patient Registration Form");
b1=new JButton("Click Here");

l2=new JLabel("  Patient Discharge Form");
b2=new JButton("Click Here");

l3=new JLabel("  Show Registered Patient");
b3=new JButton("Click Here");

l4=new JLabel("  In Patient Details");
b4=new JButton("Click Here");

l5=new JLabel("  Out Patient Details");
b5=new JButton("Click Here");

l6=new JLabel("  Doctors Information");
b6=new JButton("Click Here");

l7=new JLabel("  Hospital Information");
b7=new JButton("Click Here");

l8=new JLabel("  Staff Information");
b8=new JButton("Click Here");

l9=new JLabel("  Show Discharge Patients");
b9=new JButton("Click Here");

lb = new JLabel("HOSPITAL");
Font fkk=new Font("Algerian", Font.BOLD,30);
	lb.setFont(fkk);
	lb.setForeground(Color.RED);



p.add(lhead);
p.add(lb);
p.add(l1);
p.add(b1);

p.add(l2);
p.add(b2);

p.add(l3);
p.add(b3);


p.add(l9);
p.add(b9);

p.add(l4);
p.add(b4);

p.add(l5);
p.add(b5);

p.add(l6);
p.add(b6);

p.add(l7);
p.add(b7);

p.add(l8);
p.add(b8);


b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b7.addActionListener(this);
b8.addActionListener(this);
b9.addActionListener(this);

//f2.setResizable(false);
f4.setVisible(true);
}

	public void actionPerformed(ActionEvent ae)

{
		if(ae.getSource()==b1)
		{

		  new Registration();
		 f4.setVisible(false);
		}

		else if(ae.getSource()==b2)
		{

		new Dis();
		 f4.setVisible(false);
		}

		else if(ae.getSource()==b3)
		{



	setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Table");
	f5.setSize(1020,750);
	f5.setLocation(0,0);
	JTextField []t1=new JTextField[400];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(41,10));

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
for(int i=0; i<400; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
t1[i].setEditable(false);
}
f5.setVisible(true);
try
{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");

                 PreparedStatement ps=con.prepareStatement("Select * from Registration" );

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
		System.out.println("error"+ex);
		}


		    }



		else if(ae.getSource()==b4)
		{

		new Patient();
		 f4.setVisible(false);
		}

		else if(ae.getSource()==b5)
		{

		new OutTable();
		 f4.setVisible(false);
		}

		else if(ae.getSource()==b6)
		{

			new Doctor();
		 f4.setVisible(false);
		}

			else if(ae.getSource()==b7)
		{
			new HT();
			f4.setVisible(false);
		}

		else if(ae.getSource()==b8)
		{

			new Staff();
		 f4.setVisible(false);
		}

		else if(ae.getSource()==b9)
		{

		setDefaultLookAndFeelDecorated(true);
	JFrame f5=new JFrame("Table");
	f5.setSize(1020,750);
	f5.setLocation(0,0);
	JTextField []t1=new JTextField[200];
	JPanel p5=new JPanel();
	f5.getContentPane().add(p5);
	p5.setLayout(new GridLayout(21,10));

	p5.add(new JLabel("Patient Reg No"));
	p5.add(new JLabel("Patient Name"));
	p5.add(new JLabel("Admission Date"));
	p5.add(new JLabel("Discharge Date"));
	p5.add(new JLabel("Diseases"));
	p5.add(new JLabel("Room Charges"));
	p5.add(new JLabel("Medicins Charges"));
	p5.add(new JLabel("Operation/Testing Charges"));
	p5.add(new JLabel("Doctors Charges"));
	p5.add(new JLabel("Total Amount"));
for(int i=0; i<200; i++)
{
t1[i]=new JTextField(10);
p5.add(t1[i]);
t1[i].setEditable(false);
}
f5.setVisible(true);
try
{
Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");                 PreparedStatement ps=con.prepareStatement("Select * from Discharge" );

	 ResultSet rs=ps.executeQuery();
		int i=0;
	while(rs.next())
	{
		String RegNo=rs.getString(1);
		String PatientName=rs.getString(2);
		String AdmissionDate=rs.getString(3);
		String DischargeDate=rs.getString(4);
		String Diseases=rs.getString(5);
		String RoomCharges=rs.getString(6);
		String MedicinsCharges=rs.getString(7);
		String OTCharges=rs.getString(8);
		String DoctorsCharges=rs.getString(9);
		String TotalAmount=rs.getString(10);


		t1[i].setText(RegNo);
		i++;
		t1[i].setText(PatientName);
		i++;
		t1[i].setText(AdmissionDate);
		i++;
		t1[i].setText(DischargeDate);
		i++;
		t1[i].setText(Diseases);
		i++;
		t1[i].setText(RoomCharges);
		i++;
		t1[i].setText(MedicinsCharges);
		i++;
		t1[i].setText(OTCharges);
		i++;
		t1[i].setText(DoctorsCharges);
		i++;
		t1[i].setText(TotalAmount);
		i++;

		}
	}
	catch(Exception ex)
	{
		System.out.println("error"+ex);
		}


		    }

}
	public static void main(String args[])
	{
		new Main();
	}
}