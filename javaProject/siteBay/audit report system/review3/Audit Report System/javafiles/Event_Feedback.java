                // Program 


import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Event_Feedback extends JApplet  
{

  JLabel EvtId,Fname,Feedback,MailId,ContNo,Address;
  JTextField TEvtId,TFname,TFeedback,TMailId,TContNo;
  JTextArea TAddress; 
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   EvtId=new JLabel("EventID",JLabel.LEFT);
   Fname =new JLabel("Name",JLabel.LEFT);    
             // allocating memory
   Feedback =new JLabel("Feedback",JLabel.LEFT);
   MailId =new JLabel("MailId",JLabel.LEFT);
   ContNo =new JLabel("ContNo",JLabel.LEFT);
   Address =new JLabel("Address",JLabel.LEFT);
   
   TEvtId=new JTextField();
   TFname=new JTextField();
   TFeedback=new JTextField();
   TMailId=new JTextField();
   TContNo=new JTextField();
   TAddress=new JTextArea();
   

   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   
   

               // adding components to Applet

   EvtId.setBounds(40,50,280,20);
   con.add(EvtId);
   TEvtId.setBounds(140,50,180,30);
   con.add(TEvtId); 
   Fname.setBounds(40,80,280,60);
   con.add(Fname);
   TFname.setBounds(140,90,180,30);
   con.add(TFname);
   Feedback.setBounds(40,130,280,40);
   con.add(Feedback);
   TFeedback.setBounds(140,130,180,30);
   con.add(TFeedback);
   MailId.setBounds(40,170,280,30);
   con.add(MailId);
   TMailId.setBounds(140,170,180,30);
   con.add(TMailId);
   ContNo.setBounds(40,210,280,30);
   con.add(ContNo);
   TContNo.setBounds(140,210,180,30);
   con.add(TContNo);
   Address.setBounds(40,250,280,35);
   con.add(Address);
   TAddress.setBounds(140,250,180,30);
   con.add(TAddress);

   bAdd.setBounds(340,100,110,30);
   con.add(bAdd);
   bDel.setBounds(340,150,110,30);
   con.add(bDel);
   bModify.setBounds(340,200,110,30);
   con.add(bModify);
   
  

//TEvtId,TFname,TFeedback,TMailId,TContNo,TAddress


   //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TEvtId="+TEvtId.getText()+"&TFname="+TFname.getText()+"&TFeedback"+TFeedback.getText()+"&TMailId"+TMailId.getText()+"&TContNo="+TContNo.getText()+"&TAddress="+TAddress.getText();
  URL u = new URL("http://localhost:2012/EventFeedback"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TEvtId="+TEvtId.getText();
  URL u = new URL("http://localhost:2012/EventFeedback"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TEvtId="+TEvtId.getText()+"&TFname="+TFname.getText()+"&TFeedback"+TFeedback.getText()+"&TMailId"+TMailId.getText()+"&TContNo="+TContNo.getText()+"&TAddress="+TAddress.getText();
  URL u = new URL("http://localhost:2012/EventFeedback"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TEvtId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

 
  
  
 }//cons

  
}  //class








 
