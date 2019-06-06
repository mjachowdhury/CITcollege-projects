                // Program 


import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Address extends JApplet  
{

  JLabel AddRefNo,CompanyName,Location,City,State,Country;
  JTextField TAddRefNo,TCompanyName,TLocation,TCity,TState,TCountry;
  JButton bSubmit,bAdd,bDel,bModify;
 
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   AddRefNo=new JLabel("AddressReferenceNo",JLabel.LEFT);
   CompanyName =new JLabel("Company Name",JLabel.LEFT);                 // Allocating memory
   Location =new JLabel("Location",JLabel.LEFT);
   City=new JLabel("City",JLabel.LEFT);
   State=new JLabel("State",JLabel.LEFT);
   Country=new JLabel("Country",JLabel.LEFT);
     
 
   TAddRefNo=new JTextField();
   TCompanyName=new JTextField();
   TLocation=new JTextField();
   TCity=new JTextField();
   TState=new JTextField();
   TCountry=new JTextField();


   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
  


               // adding components to Applet
   
   AddRefNo.setBounds(40,50,160,20);
   con.add(AddRefNo);
   TAddRefNo.setBounds(180,50,200,30);
   con.add(TAddRefNo); 
   CompanyName.setBounds(40,90,160,30);
   con.add(CompanyName);
   TCompanyName.setBounds(180,90,200,30);
   con.add(TCompanyName);
   Location.setBounds(40,130,160,20);
   con.add(Location);  
   TLocation.setBounds(180,130,200,30);
   con.add(TLocation) ;
   City.setBounds(40,170,160,20);
   con.add(City);
   TCity.setBounds(180,170,200,30);
   con.add(TCity);
   State.setBounds(40,210,160,20);
   con.add(State);
   TState.setBounds(180,210,200,30);
   con.add(TState);
   Country.setBounds(40,250,160,30);
   con.add(Country); 
   TCountry.setBounds(180,250,200,30);
   con.add(TCountry);
   
   bAdd.setBounds(400,80,110,30);
   con.add(bAdd);
   bDel.setBounds(400,120,110,30);
   con.add(bDel);
   bModify.setBounds(400,160,110,30);
   con.add(bModify);
   


   //TAddRefNo,TCompanyName,TLocation,TCity,TState,TCountry;
   
  //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TAddRefNo="+TAddRefNo.getText()+"&TCompanyName="+TCompanyName.getText()+"&TLocation="+TLocation.getText()+"&TCity="+TCity.getText()+"&TState="+TState.getText()+"&TCountry="+TCountry.getText();
  URL u = new URL("http://localhost:2012/Addres"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TAddRefNo="+TAddRefNo.getText();
  URL u = new URL("http://localhost:2012/Addres"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TAddRefNo="+TAddRefNo.getText()+"&TCompanyName="+TCompanyName.getText()+"&TLocation="+TLocation.getText()+"&TCity="+TCity.getText()+"&TState="+TState.getText() +"&TCountry="+TCountry.getText();
  URL u = new URL("http://localhost:2012/Addres"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TAddRefNo.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});


  
  
 }//cons

  
}  //class




  
