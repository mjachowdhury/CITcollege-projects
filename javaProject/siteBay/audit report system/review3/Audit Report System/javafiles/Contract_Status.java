            // Program 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.net.*;


public class Contract_Status extends JApplet  
{

  JLabel ContractId,ExecId,Status;
  JTextField TContractId,TStatus,TExecId;
  JButton bSubmit,bAdd,bDel,bModify;    
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   ContractId=new JLabel("Contract ID",JLabel.LEFT);
   Status =new JLabel("Status",JLabel.LEFT);                 // Allocating memory
   ExecId =new JLabel("Executive Id",JLabel.LEFT);
   
   TContractId=new JTextField();
   TStatus=new JTextField();
   TExecId=new JTextField();


    
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
  

   
   
   
               // adding components to Applet

   ContractId.setBounds(40,50,120,20);
   con.add(ContractId);
   TContractId.setBounds(140,50,180,30);
   con.add(TContractId); 
   Status.setBounds(40,80,120,30);
   con.add(Status);
   TStatus.setBounds(140,90,180,30);
   con.add(TStatus);
   ExecId.setBounds(40,120,120,30);
   con.add(ExecId);
   TExecId.setBounds(140,130,180,30);
   con.add(TExecId);

   bAdd.setBounds(100,210,110,30);
   con.add(bAdd);
   bDel.setBounds(220,210,110,30);
   con.add(bDel);
   bModify.setBounds(340,210,110,30);
   con.add(bModify);
   
    
   //TContractId,TStatus,TExecId;

    //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TContractId="+TContractId.getText()+"&TStatus="+TStatus.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:7001/ContractStatus"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TContractId="+TContractId.getText();
  URL u = new URL("http://localhost:7001/ContractStatus"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TContractId="+TContractId.getText()+"&TStatus="+TStatus.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:7001/ContractStatus"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});


  
  
 }//cons

  
}  //class
   
 
   


   
 
