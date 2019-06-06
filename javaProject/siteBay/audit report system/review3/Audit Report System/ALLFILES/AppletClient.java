import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.applet.*;


public class AppletClient extends Applet implements ActionListener
{
Label l1,l2,l3;
TextField t1,t2,t3;
Button b1,b2;

public void init()
	{
l1 = new Label("Emp No:");
l2 = new Label("Emp Name:");
l3 = new Label("Basic pay:");

t1= new TextField(20);
t2= new TextField(20);
t3= new TextField(20);

b1 = new Button("Save");
b2 = new Button("Insert");

add(l1);add(t1);
add(l2);add(t2);
add(l3);add(t3);
add(b2); add(b1);
b1.addActionListener(this);
b2.addActionListener(this);

}//init

public void actionPerformed(ActionEvent e){
if(e.getSource() == b1)
	{
try{
String qstr = "?ENO="+t1.getText()+"&ENAME="+t2.getText()+"&BASIC="+t3.getText();

URL url= new URL("http://localhost:2012/Insert.jsp"+qstr);

InputStream in = url.openStream();

DataInputStream dis = new DataInputStream(in);

showStatus(dis.readLine());

}//try
catch(Exception ee)
	{
t3.setText(ee.toString());
}
	}//if
else if(e.getSource() == b2)
	{
// display max empno
try{
URL url=new URL("http://localhost:2012/maxeno.jsp");

InputStream in = url.openStream();

DataInputStream dis = new DataInputStream(in);
//while((dis.readLine()) != null)
t2.setText("No=" + dis.available());
t1.setText(dis.readLine());
System.out.println(dis.readLine());
}
catch(Exception ee){
t1.setText(ee.toString());
}


}//if
}//action

}//class