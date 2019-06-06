
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Attendee_Master extends JApplet  
{

   JLabel Name,Desg,MeetingId,Address,Phno,MailId;
   JTextField TName,TDesg,TMeetingId,TPhno,TMailId;
   JTextArea TAddress; 
   JButton bAdd,bDel,bModify;
   
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   Name=new JLabel("Attendee Name",JLabel.LEFT);
   MeetingId =new JLabel("Meeting Id",JLabel.LEFT);                 // Allocating memory
   Address =new JLabel("Address",JLabel.LEFT);
   MailId=new JLabel("MailId",JLabel.LEFT);
   Phno=new JLabel("Phno",JLabel.LEFT);
   Desg=new JLabel("Designition",JLabel.LEFT);
 
   TMeetingId=new JTextField();
   TName=new JTextField();
   TDesg=new JTextField();
   TAddress=new JTextArea("",20,100);
   TPhno=new JTextField();
   TMailId=new JTextField();


   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");

 
   
   
                 // adding components to Applet

   
   Name.setBounds(40,50,120,20);
   con.add(Name);
   TName.setBounds(160,50,150,30);
   con.add(TName); 
   Desg.setBounds(40,90,120,30);
   con.add(Desg);
   TDesg.setBounds(160,90,150,30);
   con.add(TDesg);
   MeetingId.setBounds(40,130,120,20);
   con.add(MeetingId);  
   TMeetingId.setBounds(160,130,150,30);
   con.add(TMeetingId) ;
   Address.setBounds(40,170,120,20);
   con.add(Address);
   TAddress.setBounds(160,170,150,30);
   con.add(TAddress);
   Phno.setBounds(40,210,120,20);
   con.add(Phno);
   TPhno.setBounds(160,210,150,30);
   con.add(TPhno);
   MailId.setBounds(40,250,120,20);
   con.add(MailId);
   TMailId.setBounds(160,250,150,30);
   con.add(TMailId);
    

   bAdd.setBounds(400,120,110,30);
   con.add(bAdd);
   bDel.setBounds(400,160,110,30);
   con.add(bDel);
   bModify.setBounds(400,200,110,30);
   con.add(bModify);


   //TName,TDesg,TMeetingId,TAddress,TPhno,TMailId;
  //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TName="+TName.getText()+"&TDesg="+TDesg.getText()+"&TMeetingId="+TMeetingId.getText()+"&TAddress="+TAddress.getText()+"&TPhno="+TPhno.getText()+"&TMailId="+TMailId.getText();
 URL u = new URL("http://localhost:2012/AttendeeMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TName.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TMeetingId="+TMeetingId.getText();
  URL u = new URL("http://localhost:2012/MeetingMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TName="+TName.getText()+"&TDesg="+TDesg.getText()+"&TMeetingId="+TMeetingId.getText()+"&TAddress="+TAddress.getText()+"&TPhno="+TPhno.getText()+"&TMailId="+TMailId.getText();
  URL u = new URL("http://localhost:2012/AttendeeMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TName.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

  
  
  
 }//cons

  
}  //class


  

