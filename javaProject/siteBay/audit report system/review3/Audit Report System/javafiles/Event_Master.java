                // Program 

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Event_Master extends JApplet 
{

  JLabel EvtId,EvtName,Venue,date,Address,Location,City,Phno;
  JTextField TEvtId,TEvtName,TVenue,TDate,TLocation,TPhno,TCity;
  JTextArea TAddress;
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
  
   EvtId=new JLabel("EventID",JLabel.LEFT);
   EvtName =new JLabel("EventName",JLabel.LEFT);                 // Allocating memory
   Venue =new JLabel("Venue",JLabel.LEFT);
   date=new JLabel("Date",JLabel.LEFT);
   Address=new JLabel("Address",JLabel.LEFT);
   Location=new JLabel("Location",JLabel.LEFT);
   City=new JLabel("City",JLabel.LEFT);
   Phno=new JLabel("Phno",JLabel.LEFT);
   TEvtId=new JTextField();
   TEvtName=new JTextField();
   TVenue=new JTextField();
   TDate=new JTextField();
   TAddress=new JTextArea("",15,30);
   TLocation=new JTextField();
   TCity=new JTextField();
   TPhno=new JTextField();
   
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   
    
			   // adding components to Applet

   EvtId.setBounds(20,50,80,20);
   con.add(EvtId);
   TEvtId.setBounds(100,40,140,30);
   con.add(TEvtId); 
   
   EvtName.setBounds(20,80,90,30);
   con.add(EvtName);
   TEvtName.setBounds(100,80,140,30);
   con.add(TEvtName);
   
   Venue.setBounds(20,120,80,20);
   con.add(Venue);  
   TVenue.setBounds(100,120,140,30);
   con.add(TVenue) ;
   
   date.setBounds(20,160,80,20);
   con.add(date);
   TDate.setBounds(100,160,140,30);
   con.add(TDate);
   
   Address.setBounds(20,200,80,20);
   con.add(Address);
   TAddress.setBounds(100,200,140,30);
   con.add(TAddress);
   
   Location.setBounds(20,240,80,30);
   con.add(Location); 
   TLocation.setBounds(100,240,140,30);
   con.add(TLocation);
   
   City.setBounds(20,280,80,20);
   con.add(City);
   TCity.setBounds(100,280,140,30);
   con.add(TCity);
   
   Phno.setBounds(20,320,80,20);
   con.add(Phno);
   TPhno.setBounds(100,320,140,30);
   con.add(TPhno);



   bAdd.setBounds(360,100,120,30);
   con.add(bAdd);

   bDel.setBounds(360,150,120,30);
   con.add(bDel);
   bModify.setBounds(360,200,120,30);
   con.add(bModify);
   


//event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TEvtId="+TEvtId.getText()+"&TEvtName="+TEvtName.getText()+"&TVenue"+TVenue.getText()+"&TDate"+TDate.getText()+"&TLocation="+TLocation.getText()+"&TAddress="+TAddress.getText()+"&TPhno="+TPhno.getText()+"&TCity="+TCity.getText();
  URL u = new URL("http://localhost:2012/EventMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TEvtId="+TEvtId.getText();
  URL u = new URL("http://localhost:2012/EventMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TEvtId="+TEvtId.getText()+"&TEvtName="+TEvtName.getText()+"&TVenue"+TVenue.getText()+"&TDate"+TDate.getText()+"&TLocation="+TLocation.getText()+"&TAddress="+TAddress.getText()+"&TPhno="+TPhno.getText()+"&TCity="+TCity.getText();
  URL u = new URL("http://localhost:2012/EventMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});
}//cons
}//class
