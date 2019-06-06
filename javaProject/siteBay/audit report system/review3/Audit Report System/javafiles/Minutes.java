import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.*;


public class Minutes extends JApplet  
{

  JLabel MeetingId,Minutes,N_Name,St_Time,End_Time;
  JTextField TMeetingId,TMinutes,TN_Name,TSt_Time,TEnd_Time;
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   MeetingId=new JLabel("Meeting ID",JLabel.LEFT);
   Minutes =new JLabel("Minutes",JLabel.LEFT);// Allocating memory
   N_Name =new JLabel("Noter Name",JLabel.LEFT);
   St_Time =new JLabel("Starting Time",JLabel.LEFT);   
   End_Time =new JLabel("End Time",JLabel.LEFT);

   
   TMeetingId=new JTextField();
   TMinutes=new JTextField();
   TN_Name=new JTextField();
   TSt_Time=new JTextField();
   TEnd_Time=new JTextField();   


   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   

   
               // adding components to Applet

   MeetingId.setBounds(40,50,230,20);
   con.add(MeetingId);
   TMeetingId.setBounds(140,50,280,30);
   con.add(TMeetingId); 
   Minutes.setBounds(40,90,230,30);
   con.add(Minutes);
   TMinutes.setBounds(140,90,280,30);
   con.add(TMinutes);
   N_Name.setBounds(40,130,230,30);
   con.add(N_Name);
   TN_Name.setBounds(140,130,280,30);
   con.add(TN_Name);
   St_Time.setBounds(40,170,230,30);
   con.add(St_Time);
   TSt_Time.setBounds(140,170,280,30);
   con.add(TSt_Time);
   End_Time.setBounds(40,210,230,20);
   con.add(End_Time);
   TEnd_Time.setBounds(140,210,280,30);
   con.add(TEnd_Time);



   bAdd.setBounds(440,90,110,30);
   con.add(bAdd);
   bDel.setBounds(440,140,110,30);
   con.add(bDel);
   bModify.setBounds(440,190,110,30);
   con.add(bModify);
 

bAdd.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){

	try{
//TMeetingId,TMinutes,TN_Name,TSt_Time,TEnd_Time
		String QStr="?TYPE=INSERT&TMeetingId="+TMeetingId.getText()+"&TMinutes="+TMinutes.getText()+"&TN_Name="+TN_Name.getText()+"&TSt_Time="+TSt_Time.getText()+"&TEnd_Time="+TEnd_Time.getText();
	URL u = new URL("http://localhost:7001/Minute"+QStr);
	showStatus("Record Inserted");
	DataInputStream dis = new DataInputStream(u.openStream());
	}catch(Exception ee){showStatus(ee.toString());}
	}});

bDel.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){

	try{
String QStr="?TYPE=DELETE&TMeetingId="+TMeetingId.getText();
URL u = new URL("http://localhost:7001/Minute"+QStr);
DataInputStream dis = new DataInputStream(u.openStream());
}catch(Exception ee){showStatus(ee.toString());}
	}});

bModify.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){

	try{
		String QStr="?TYPE=MODIFY&TMeetingId="+TMeetingId.getText()+"&TMinutes="+TMinutes.getText()+"&TN_Name="+TN_Name.getText()+"&TSt_Time="+TSt_Time.getText()+"&TEnd_Time="+TEnd_Time.getText();

	URL u = new URL("http://localhost:7001/Minute"+QStr);
	DataInputStream dis = new DataInputStream(u.openStream());

showStatus(dis.readLine());
	}catch(Exception ee){}
	}});

bSubmit.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){

	try{
		String QStr="?TYPE=FIND";
	URL u = new URL("http://localhost:7001/Minute"+QStr);
	DataInputStream dis = new DataInputStream(u.openStream());


	}catch(Exception ee){}
	}});

 
 }
}  
