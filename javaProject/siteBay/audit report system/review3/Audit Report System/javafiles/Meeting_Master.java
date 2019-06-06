                // Program 


import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Meeting_Master extends JApplet  
{

  JLabel MeetingId,MeetingDesc,Time,Date,Address,Location,City,Phno,State;
  JTextField TMeetingId,TMeetingDesc,TDate,TTime,TLocation,TPhno,TCity,TState;
  JTextArea TAddress;
  JButton bAdd,bDel,bModify;
 
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   MeetingId=new JLabel("MeetingID",JLabel.LEFT);
   MeetingDesc =new JLabel("MeetingDescription",JLabel.LEFT);                 // Allocating memory
   Time =new JLabel("Time",JLabel.LEFT);
   Date=new JLabel("Date",JLabel.LEFT);
   Address=new JLabel("Address",JLabel.LEFT);
   Location=new JLabel("Location",JLabel.LEFT);
   City=new JLabel("City",JLabel.LEFT);
   Phno=new JLabel("Phno",JLabel.LEFT);
   State=new JLabel("State",JLabel.LEFT);
 
   TMeetingId=new JTextField();
   TMeetingDesc=new JTextField();
   TTime=new JTextField();
   TDate=new JTextField();
   TAddress=new JTextArea("",20,100);
   TLocation=new JTextField();
   TCity=new JTextField();
   TPhno=new JTextField();
   TState=new JTextField();
     

   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   
               // adding components to Applet

   MeetingId.setBounds(40,50,160,20);
   con.add(MeetingId);
   TMeetingId.setBounds(160,50,200,30);
   con.add(TMeetingId); 
   MeetingDesc.setBounds(40,90,160,30);
   con.add(MeetingDesc);
   TMeetingDesc.setBounds(160,90,200,30);
   con.add(TMeetingDesc);
   Date.setBounds(40,130,160,20);
   con.add(Date);  
   TDate.setBounds(160,130,200,30);
   con.add(TDate) ;
   Time.setBounds(40,170,160,20);
   con.add(Time);
   TTime.setBounds(160,170,200,30);
   con.add(TTime);

   Address.setBounds(40,210,160,20);
   con.add(Address);
   TAddress.setBounds(160,210,200,30);
   con.add(TAddress);
   Location.setBounds(40,250,160,30);
   con.add(Location); 
   TLocation.setBounds(160,250,200,30);
   con.add(TLocation);
   City.setBounds(40,290,160,20);
   con.add(City);
   TCity.setBounds(160,290,200,30);
   con.add(TCity);
   Phno.setBounds(40,330,160,20);
   con.add(Phno);
   TPhno.setBounds(160,330,200,30);
   con.add(TPhno);
   State.setBounds(40,370,160,20);
   con.add(State);
   TState.setBounds(160,370,200,30);
   con.add(TState);

   
   bAdd.setBounds(400,120,110,30);
   con.add(bAdd);
   bDel.setBounds(400,175,110,30);
   con.add(bDel);
   bModify.setBounds(400,230,110,30);
   con.add(bModify);


   
//TMeetingId,TMeetingDesc,TDate,TTime,TLocation,TPhno,TCity,TState

   //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TMeetingId="+TMeetingId.getText()+"&TMeetingDesc="+TMeetingDesc.getText()+"&TDate="+TDate.getText()+"&TTime="+TTime.getText()+"&TLocation="+TLocation.getText()
  +"&TAddress="+TAddress.getText()+"&TCity="+TCity.getText()+"&TPhno="+TPhno.getText()
  +"&TState="+TState.getText();
  URL u = new URL("http://localhost:7001/MeetingMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TMeetingId="+TMeetingId.getText();
  URL u = new URL("http://localhost:7001/MeetingMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TMeetingId="+TMeetingId.getText()+"&TMeetingDesc="+TMeetingDesc.getText()+"&TDate="+TDate.getText()+"&TTime="+TTime.getText()+"&TLocation="+TLocation.getText()+"&TAddress="+TAddress.getText()+"&TCity="+TCity.getText()+"&TPhno="+TPhno.getText()+"&TState="+TState.getText();
  URL u = new URL("http://localhost:7001/MeetingMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
  TMeetingId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

 
  
  
 }//cons

  
}  //class

  
