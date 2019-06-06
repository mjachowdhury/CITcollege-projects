
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Meeting_Desc extends JApplet  
{

  JLabel MeetingId,Agenda,ExecId;
  JTextField TMeetingId,TAgenda,TExecId;
  JButton bAdd,bDel,bModify;
 

  public void init()
  {
   
   Container con=getContentPane();
   con.setLayout(null);
   con.setBackground(new Color(255,228,196));
   MeetingId=new JLabel("Meeting ID",JLabel.LEFT);
   Agenda =new JLabel("Agenda",JLabel.LEFT);                 // Allocating memory
   ExecId =new JLabel("Executive Id",JLabel.LEFT);
   
   TMeetingId=new JTextField();
   TAgenda=new JTextField();
   TExecId=new JTextField();

   bAdd=new JButton("Addition");
   bDel=new JButton("Deletion");
   bModify=new JButton("Modification");
  
    
             // adding components to Applet

   MeetingId.setBounds(40,50,80,20);
   con.add(MeetingId);
   TMeetingId.setBounds(140,50,280,30);
   con.add(TMeetingId); 
   Agenda.setBounds(40,90,80,30);
   con.add(Agenda);
   TAgenda.setBounds(140,90,280,30);
   con.add(TAgenda);
   ExecId.setBounds(40,130,80,30);
   con.add(ExecId);
   TExecId.setBounds(140,130,280,30);
   con.add(TExecId);


   bAdd.setBounds(100,180,110,30);
   con.add(bAdd);
   bDel.setBounds(220,180,110,30);
   con.add(bDel);
   bModify.setBounds(340,180,110,30);
   con.add(bModify);
   

//TMeetingId,TAgenda,TExecId
   //event code
bAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=INSERT&TMeetingId="+TMeetingId.getText()+"&TAgenda="+TAgenda.getText()+"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:7001/MeetingDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});

bDel.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=DELETE&TMeetingId="+TMeetingId.getText();
  URL u = new URL("http://localhost:7001/MeetingDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
  }catch(Exception ex){showStatus(ee.toString());}
}
});

bModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ee){

	 try{
String qstr="?TYPE=MODIFY&TMeetingId="+TMeetingId.getText()+"&TAgenda="+TAgenda.getText()
   +"&TExecId="+TExecId.getText();
  URL u = new URL("http://localhost:7001/MeetingDesc"+qstr);
  DataInputStream dis = new DataInputStream(u.openStream());
TMeetingId.setText("Test::"+dis.readLine());
 }catch(Exception ex){showStatus(ee.toString());}
}
});


  
  
 }//cons

  
}  //class

   

  
