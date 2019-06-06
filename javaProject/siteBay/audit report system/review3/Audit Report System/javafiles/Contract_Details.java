                // Program 


import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Contract_Details extends JApplet  
{

  JLabel ContractId,ContractDesc,ConTask,ExecId;
  JTextField TContractId,TContractDesc,TConTask,TExecId;
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   ContractId=new JLabel("Contract ID",JLabel.LEFT);
   ContractDesc =new JLabel("Contract Description",JLabel.LEFT);                 // Allocating memory
   ConTask=new JLabel("Contract Task",JLabel.LEFT);
   ExecId =new JLabel("Executive Id",JLabel.LEFT);
   
   TContractId=new JTextField();
   TContractDesc=new JTextField();
   TConTask=new JTextField();
   TExecId=new JTextField();
   
   
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");

   
   

               // adding components to Applet

   ContractId.setBounds(40,50,200,20);
   con.add(ContractId);
   TContractId.setBounds(170,50,180,30);
   con.add(TContractId); 
   ContractDesc.setBounds(40,90,200,30);
   con.add(ContractDesc);
   TContractDesc.setBounds(170,90,180,30);
   con.add(TContractDesc);
   ConTask.setBounds(40,130,200,30);
   con.add(ConTask);
   TConTask.setBounds(170,130,180,30);
   con.add(TConTask);
   ExecId.setBounds(40,170,200,35);
   con.add(ExecId);
   TExecId.setBounds(170,170,180,30);
   con.add(TExecId);
   
   
  
   bAdd.setBounds(370,70,110,30);
   con.add(bAdd);
   bDel.setBounds(370,110,110,30);
   con.add(bDel);
   bModify.setBounds(370,150,110,30);
   con.add(bModify);



   //TContractId,TContractDesc,TConTask,TExecId;

//event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TContractId="+TContractId.getText()+"&TContractDesc="+TContractDesc.getText()+"&TConTask="+TConTask.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:2012/ContractDetails"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TContractId="+TContractId.getText();
  URL u = new URL("http://localhost:2012/ContractDetails"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TContractId="+TContractId.getText()+"&TContractDesc="+TContractDesc.getText()
  +"&TConTask="+TConTask.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:2012/ContractDetails"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

  

  
 }//cons

  
}  //class



  
  
