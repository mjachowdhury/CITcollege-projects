                // Program 


import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Event_Desc extends JApplet  
{

  JLabel EvtId,EvtDesc,ExecId;
  JTextField TEvtId,TEvtDesc,TExecId;
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   EvtId=new JLabel("EventID",JLabel.LEFT);
   EvtDesc =new JLabel("EventDescription",JLabel.LEFT);                 // Allocating memory
   ExecId =new JLabel("Executive Id",JLabel.LEFT);
   
   TEvtId=new JTextField();
   TEvtDesc=new JTextField();
   TExecId=new JTextField();
   
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   
               // adding components to Applet

   EvtId.setBounds(40,50,50,20);
   con.add(EvtId);
   TEvtId.setBounds(140,50,180,30);
   con.add(TEvtId); 
   EvtDesc.setBounds(40,80,100,60);
   con.add(EvtDesc);
   TEvtDesc.setBounds(140,90,180,30);
   con.add(TEvtDesc);
   ExecId.setBounds(40,130,80,35);
   con.add(ExecId);
   TExecId.setBounds(140,130,180,30);
   con.add(TExecId);
   


   bAdd.setBounds(340,50,110,30);
   con.add(bAdd);
   bDel.setBounds(340,90,110,30);
   con.add(bDel);
   bModify.setBounds(340,130,110,30);
   con.add(bModify);
  


   //TEvtId,TEvtDesc,TExecId
   //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){
	 try{
String qstr="?TYPE=INSERT&TEvtId="+TEvtId.getText()+"&TEvtDesc="+TEvtDesc.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:2012/EventDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());

 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TEvtId="+TEvtId.getText();
  URL u = new URL("http://localhost:2012/EventDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());

  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TEvtId="+TEvtId.getText()+"&TEvtDesc="+TEvtDesc.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:2012/EventDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());

 }catch(Exception ex){showStatus(ee.toString());}
}
});

  
  
  
 }//cons

  
}  //class







 
  
