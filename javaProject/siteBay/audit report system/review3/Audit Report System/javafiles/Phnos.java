import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.*;


public class Phnos extends JApplet  
{

  JLabel AddRefNo,Phno1,Phno2,Mobile;
  JTextField TAddRefNo,TPhno1,TPhno2,TMobile;


  JButton bAdd,bModify,bDel;
 
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   AddRefNo=new JLabel("AddressReferenceNo",JLabel.LEFT);
   Phno1 =new JLabel("Phone No1",JLabel.LEFT);                 // Allocating memory
   Phno2 =new JLabel("Phone No2",JLabel.LEFT);
   Mobile=new JLabel("Mobile",JLabel.LEFT);
    
    
   TAddRefNo=new JTextField();
   TPhno1=new JTextField();
   TPhno2=new JTextField();
   TMobile=new JTextField();
   
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
  
   



                 // adding components to Applet

   AddRefNo.setBounds(40,50,140,20);
   con.add(AddRefNo);
   TAddRefNo.setBounds(180,50,140,30);
   con.add(TAddRefNo); 
   Phno1.setBounds(40,90,140,30);
   con.add(Phno1);
   TPhno1.setBounds(180,90,140,30);
   con.add(TPhno1);
   Phno2.setBounds(40,130,140,20);
   con.add(Phno2);  
   TPhno2.setBounds(180,130,140,30);
   con.add(TPhno2);
   Mobile.setBounds(40,170,140,30);
   con.add(Mobile);
   TMobile.setBounds(180,170,140,30);
   con.add(TMobile) ;
      


   bAdd.setBounds(400,60,110,30);
   con.add(bAdd);
   bDel.setBounds(400,110,110,30);
   con.add(bDel);
   bModify.setBounds(400,160,110,30);
   con.add(bModify);

//TAddRefNo,TPhno1,TPhno2,TMobile;


   //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TAddRefNo="+TAddRefNo.getText()+"&TPhno1="+TPhno1.getText()+"&TPhno2="+TPhno2.getText()+"&TMobile="+TMobile.getText();
  URL u = new URL("http://localhost:7001/Phno"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TAddRefNo="+TAddRefNo.getText();
  URL u = new URL("http://localhost:7001/Phno"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TAddRefNo="+TAddRefNo.getText()+"&TPhno1="+TPhno1.getText()+"&TPhno2="+TPhno2.getText()+"&TMobile="+TMobile.getText();
  URL u = new URL("http://localhost:7001/Phno"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});
  
  
  
 }//cons

  
}  //class

  
