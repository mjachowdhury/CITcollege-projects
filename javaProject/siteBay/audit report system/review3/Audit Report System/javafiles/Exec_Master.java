
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Exec_Master extends JApplet  
{

   JLabel ExecId,ExecName,Location,MobileNo;
   JTextField TExecId,TExecName,TLocation,TMobileNo;
   JButton bAdd,bDel,bModify;    

 
  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   ExecId=new JLabel("Executive Id",JLabel.LEFT);
   ExecName =new JLabel("Executive Name",JLabel.LEFT);                 // Allocating memory
   Location =new JLabel("Location",JLabel.LEFT);
   MobileNo=new JLabel("MobileNo",JLabel.LEFT);
   
 
   TExecId=new JTextField();
   TExecName=new JTextField();
   TLocation=new JTextField();
   TMobileNo=new JTextField();

   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
  
   
                 // adding components to Applet

   
   ExecId.setBounds(40,50,120,20);
   con.add(ExecId);
   TExecId.setBounds(160,50,150,30);
   con.add(TExecId); 
   ExecName.setBounds(40,90,120,30);
   con.add(ExecName);
   TExecName.setBounds(160,90,150,30);
   con.add(TExecName);
   Location.setBounds(40,130,120,20);
   con.add(Location);  
   TLocation.setBounds(160,130,150,30);
   con.add(TLocation) ;
   MobileNo.setBounds(40,170,120,20);
   con.add(MobileNo);
   TMobileNo.setBounds(160,170,150,30);
   con.add(TMobileNo);

   bAdd.setBounds(400,70,110,30);
   con.add(bAdd);
   bDel.setBounds(400,110,110,30);
   con.add(bDel);
   bModify.setBounds(400,150,110,30);
   con.add(bModify);
  


  //TExecId,TExecName,TLocation,TMobileNo
   
//event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TExecId="+TExecId.getText()+"&TExecName="+TExecName.getText()+"&TLocation="+TLocation.getText()
   +"&TMobileNo="+TMobileNo.getText();
  URL u = new URL("http://localhost:2012/ExecMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TExecId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:2012/ExecMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TExecId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TExecId="+TExecId.getText()+"&TExecName="+TExecName.getText()
    +"&TLocation="+TLocation.getText()+"&TMobileNo="+TMobileNo.getText();
  URL u = new URL("http://localhost:2012/ExecMaster"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TExecId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

    
  
 }//cons

  
}  //class




