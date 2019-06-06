//package doc;
//import dos.Main;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener
{
JLabel l1,l2,l3,l4,image;
JTextField t1, t2;
JPasswordField jp1;
JButton b1,b2, b3;
JFrame f;
ImageIcon icon;

LoginForm()
{

//setDefaultLookAndFeelDecorated(true);
f=new JFrame("LoginForm");
f.setSize(750,330);
f.setLocation(200,200);
f.setResizable(false);
JPanel p=new JPanel();
f.getContentPane().add(p);
p.setBackground(Color.white);
icon = new ImageIcon("img.jpg");
image = new JLabel(icon);
image.setSize(100, 100);
l2=new JLabel(" ");
t1=new JTextField(16);
l3=new JLabel("Password");
jp1 = new JPasswordField(16);
//img = getImage(getDocumentBase(), getParameter("img"));
b1=new JButton("Login");
b2=new JButton("Reset");
b3 = new JButton("Cancel");
l1=new JLabel("       SANJEEVANI HOSPITAL");
Font fk=new Font("Algerian", Font.BOLD,40);
	l1.setFont(fk);
	l1.setForeground(Color.RED);
l2=new JLabel("UserName");
l4=new JLabel("                                               ");
p.add(l1);
p.add(l4);
p.add(l2);
p.add(t1);
p.add(l3);
p.add(jp1);

p.add(b1);
p.add(b2);
p.add(b3);
p.add(image, BorderLayout.SOUTH);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
f.setVisible(true);
f.addWindowListener(new ExitListener());
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
try
{
Class.forName("org.gjt.mm.mysql.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/snh","root","root");
PreparedStatement ps=con.prepareStatement("select * from LoginForm where UserName=? and Password=?");
String UserName=t1.getText();
String Password=jp1.getText();
ps.setString(1, UserName);
ps.setString(2, Password);
ResultSet rs=ps.executeQuery();
boolean flag=rs.next();
if (flag)
{
new Main();
f.setVisible(false);
}
else
{
JOptionPane.showMessageDialog(null,"Please Enter valid Name And Password");
}

}
catch(Exception e)
{
System.out.println("Error:"+e);
}
}
else if(ae.getSource()==b2)
{
t1.setText("");
jp1.setText("");
}

else if(ae.getSource()==b3)
{

f.setVisible(false);
}

}
public static void main(String []args)
{
new LoginForm();
}
}
