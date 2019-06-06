

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
//import dos.Main;

public class Registration extends JFrame implements ActionListener
{
JTextField  t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
JLabel l,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12, l13;
JButton b1,b2,b3,b4;
JFrame f1;
GridBagLayout gbl;
GridBagConstraints gbc;



 public Registration()
{
setDefaultLookAndFeelDecorated(true);
f1=new JFrame("Registration Form");
f1.setSize(850,600);
f1.setLocation(100,100);

JPanel p=new JPanel();
f1.getContentPane().add(p);
f1.setBackground(Color.green);
p.setBackground(Color.white);
gbl=new GridBagLayout();
gbc=new GridBagConstraints();
p.setLayout(gbl);

	gbc.gridx=0;
	gbc.gridy=0;

	l = new JLabel(" PATIENT REGISTRATION FORM");
	gbl.setConstraints(l,gbc);

	Font fk=new Font("Algerian", Font.BOLD,30);
	l.setFont(fk);
	l.setForeground(Color.RED);

	gbc.gridx=0;
	gbc.gridy=19;
	l13=new JLabel("          ");
	gbl.setConstraints(l13,gbc);


	gbc.gridx=0;
	gbc.gridy=20;
	l1=new JLabel("Patient Registering No");
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
	l3=new JLabel("Age");
	gbl.setConstraints(l3,gbc);

	gbc.gridx=30;
	gbc.gridy=40;
	t3=new JTextField(15);
	gbl.setConstraints(t3,gbc);

	gbc.gridx=0;
	gbc.gridy=50;
	l4=new JLabel("Gender");
	gbl.setConstraints(l4,gbc);

	gbc.gridx=30;
	gbc.gridy=50;
	t4=new JTextField(15);
	gbl.setConstraints(t4,gbc);

	gbc.gridx=0;
	gbc.gridy=60;
	l5=new JLabel("DOB");
	gbl.setConstraints(l5,gbc);

	gbc.gridx=30;
	gbc.gridy=60;
	t5=new JTextField(15);
	gbl.setConstraints(t5,gbc);

	gbc.gridx=0;
	gbc.gridy=70;
	l6=new JLabel("Blood Group");
	gbl.setConstraints(l6,gbc);

	gbc.gridx=30;
	gbc.gridy=70;
	t6=new JTextField(15);
	gbl.setConstraints(t6,gbc);

	gbc.gridx=0;
	gbc.gridy=80;
	l7=new JLabel("Phone No");
	gbl.setConstraints(l7,gbc);

	gbc.gridx=30;
	gbc.gridy=80;
	t7=new JTextField(15);
	gbl.setConstraints(t7,gbc);

	gbc.gridx=0;
	gbc.gridy=90;
	l8=new JLabel("Address");
	gbl.setConstraints(l8,gbc);

	gbc.gridx=30;
	gbc.gridy=90;
	t8=new JTextField(15);
	gbl.setConstraints(t8,gbc);

	gbc.gridx=0;
	gbc.gridy=100;
	l9=new JLabel("EmailID");
	gbl.setConstraints(l9,gbc);

	gbc.gridx=30;
	gbc.gridy=100;
	t9=new JTextField(15);
	gbl.setConstraints(t9,gbc);

	gbc.gridx=0;
	gbc.gridy=110;
	l10=new JLabel("Admission Date");
	gbl.setConstraints(l10,gbc);

	gbc.gridx=30;
	gbc.gridy=110;
	t10=new JTextField("dd/mm/yyyy",15);
	gbl.setConstraints(t10,gbc);

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


	p.add(l);
	p.add(l13);
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
	p.add(l7);
	p.add(t7);
	p.add(l8);
	p.add(t8);
	p.add(l9);
	p.add(t9);
	p.add(l10);
	p.add(t10);
	p.add(b1);
	p.add(b2);
	p.add(b3);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);

f1.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
	if(ae.getSource()==b1)
	{
		try
		{
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");

                 PreparedStatement ps=con.prepareStatement("insert into registration(RegNo,PatientName,Age,Gender,DOB,BloodGroup, PhoneNo, Address, EmailID, AdmissionDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

	String RegNo=t1.getText();
	String PatientName=t2.getText();
	int Age=Integer.parseInt(t3.getText());
	String Gender=t4.getText();
	String DOB=t5.getText();
	String BloodGroup=t6.getText();
	String PhoneNo=t7.getText();
	String Address=t8.getText();
	String EmailID=t9.getText();
        String AdmissionDate=t10.getText();
                  ps.setString(1, RegNo);
	ps.setString(2, PatientName);
	ps.setInt(3, Age);
	ps.setString(4, Gender);
	ps.setString(5, DOB);
	ps.setString(6, BloodGroup);
	ps.setString(7, PhoneNo);
	ps.setString(8, Address);
	ps.setString(9, EmailID);
	ps.setString(10, AdmissionDate);
	ps.executeUpdate();

JOptionPane.showMessageDialog(null,"Record Insert Successfully");
}

catch(Exception ex)
	{

		JOptionPane.showMessageDialog(f1,ex.toString());
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
	t7.setText("");
	t8.setText("");
	t9.setText("");
	t10.setText("");

}

else if(ae.getSource()==b3)
{
new Main();
f1.setVisible(false);





}
}

	public static void main(String args[])
	{
		new Registration();
		}
	}