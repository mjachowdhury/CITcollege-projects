import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class EmailList extends JApplet  
{

  JLabel AddRefNo,ContactPerson,EmailId1,EmailId2,Others;
  JTextField TAddRefNo,TContactPerson,TEmailId1,TEmailId2,TOthers;


  JButton bAdd,bModify,bDel;
 
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   AddRefNo=new JLabel("AddressReferenceNo",JLabel.LEFT);
   ContactPerson =new JLabel("Contact Person",JLabel.LEFT);// Allocating memory
   EmailId1 =new JLabel("EmailId1",JLabel.LEFT);
   EmailId2=new JLabel("EmailId2",JLabel.LEFT);
   Others=new JLabel("Others",JLabel.LEFT);
   
   TAddRefNo=new JTextField();
   TContactPerson=new JTextField();
   TEmailId1=new JTextField();
   TEmailId2=new JTextField();
   TOthers=new JTextField();
   
   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
   
   // adding components to Applet

   AddRefNo.setBounds(40,50,160,20);
   con.add(AddRefNo);
   TAddRefNo.setBounds(180,50,200,30);
   con.add(TAddRefNo); 
   ContactPerson.setBounds(40,90,160,30);
   con.add(ContactPerson);
   TContactPerson.setBounds(180,90,200,30);
   con.add(TContactPerson);
   EmailId1.setBounds(40,130,160,20);
   con.add(EmailId1);  
   TEmailId1.setBounds(180,130,200,30);
   con.add(TEmailId1) ;
   EmailId2.setBounds(40,170,160,20);
   con.add(EmailId2);
   TEmailId2.setBounds(180,170,200,30);
   con.add(TEmailId2);
   Others.setBounds(40,210,160,20);
   con.add(Others);
   TOthers.setBounds(180,210,200,30);
   con.add(TOthers);
     
 
   bAdd.setBounds(400,100,110,30);
   con.add(bAdd);
   bDel.setBounds(400,140,110,30);
   con.add(bDel);
   bModify.setBounds(400,180,110,30);
   con.add(bModify);
   


  //AddRefNo,ContactPerson,EmailId1,EmailId2,Others;
  //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TAddRefNo="+TAddRefNo.getText()+"&TContactPerson="+TContactPerson.getText()  +"&TEmailId1="+TEmailId1.getText()+"&TEmailId2="+TEmailId2.getText()+"&TOthers="+TOthers.getText();
  URL u = new URL("http://localhost:2012/Email"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TAddRefNo="+TAddRefNo.getText();
  URL u = new URL("http://localhost:2012/Email"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TAddRefNo="+TAddRefNo.getText()+"&TContactPerson="+TContactPerson.getText()+"&TEmailId1="+TEmailId1.getText()+"&TEmailId2="+TEmailId2.getText()+"&TOthers="+TOthers.getText();
  URL u = new URL("http://localhost:2012/Email"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

  

  
 }//cons

  
}  //class

   

  
