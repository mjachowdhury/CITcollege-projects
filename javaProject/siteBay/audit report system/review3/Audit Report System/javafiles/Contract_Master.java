import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.*;


public class Contract_Master extends JApplet  
{

   JLabel ContractId,ContractCategory;
   JTextField TContractId,TContractCategory;
   JButton bAdd,bDel,bModify;
     
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   ContractId=new JLabel("Contract Id",JLabel.LEFT);
   ContractCategory=new JLabel("Contract Category",JLabel.LEFT);            // Allocating memory
     
 
   TContractId=new JTextField();
   TContractCategory=new JTextField();

   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");

    
     
                 // adding components to Applet

   
   ContractId.setBounds(40,50,120,20);
   con.add(ContractId);
   TContractId.setBounds(160,50,150,30);
   con.add(TContractId); 
   ContractCategory.setBounds(40,90,120,30);
   con.add(ContractCategory);
   TContractCategory.setBounds(160,90,150,30);
   con.add(TContractCategory);


   bAdd.setBounds(60,180,110,30);
   con.add(bAdd);
   bDel.setBounds(180,180,110,30);
   con.add(bDel);
   bModify.setBounds(300,180,110,30);
   con.add(bModify);



 //TContractId,TContractCategory

  //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TContractId="+TContractId.getText()+"&TContractCategory="+TContractCategory.getText();
  URL u = new URL("http://localhost:2012/ContractMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TContractId="+TContractId.getText();
  URL u = new URL("http://localhost:2012/ContractMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TContractId="+TContractId.getText()+"&TContractCategory="+TContractCategory.getText();
  URL u = new URL("http://localhost:2012/ContractMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TContractId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

  
  
  
 }//cons

  
}  //class


   

 