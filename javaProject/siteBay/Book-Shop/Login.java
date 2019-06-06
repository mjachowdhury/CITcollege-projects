import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class Login extends Frame implements ActionListener
{
 Label l1,l2;
TextField t1,t2;
Button b;

Login(String s)
{
super(s);
l1=new Label("User Name  ");
l2=new Label("Password   ");
t1=new TextField(" ");
t2=new TextField(" ");
b=new Button("Ok");
/*setLayout(null);
l1.setBounds(100,50,20,20);
l2.setBounds(100,60,20,20);
t1.setBounds(150,50,20,20);
t2.setBounds(150,60,20,20);
b.setBounds(130,240,50,50);*/
add(l1);
add(l2);
add(t1);
add(t2);
add(b);
l1.addActionListener(this);
l2.addActionListener(this);
t1.addActionListener(this);
t2.addActionListener(this);
b.addActionListener(this);
setVisible(true);
setSize(300,300);
}
public static void main(String s[])
{
Login l=new Login("Login Form");
public void actionPerformed(ActoinEvent ae)
{
Label l=ae.getSource();
TextField t
if(l1==l)
t1=t;
else
t2=t;
}
}
}
