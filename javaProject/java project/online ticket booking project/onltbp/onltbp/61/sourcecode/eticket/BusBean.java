package eticket;
import java.io.*;

import org.apache.struts.action.ActionForm;

public class BusBean extends ActionForm 
	{
protected String busno,bname,tname,desc,
	             startpoint,endpoint,lname,laddress,
	             starttime,endtime,returntime,
	             reachingtime,via1,via2,via3;
protected int bid,btype,capacity,bno,tid,lid,trid,seqno,stpoint,epoint;

public void setBusno(String s) {
	System.out.println("in set busno"+s);
	busno=s; }
public void  setVia1(String  i){via1=i;}
public void setVia2(String i){via2=i;}
public void setVia3(String i){this.via3=i;}

public void setBtype(int i) {this.btype=i; }
public void setBno(int i) {this.bno=i; }//For tripdetails.jsp
public void setStpoint(int i) {this.stpoint=i; }//For tripdetails.jsp
public void setEpoint(int i) {this.epoint=i; }//For tripdetails.jsp
public void setCapacity(int i) {this.capacity=i; }
public void setStartpoint(String s) {startpoint=s; }
public void setEndpoint(String s) {endpoint=s; }
public void setLname(String s) {this.lname=s; }
public void setLaddress(String s) {this.laddress=s; }
public void setStarttime(String s) {starttime=s; }
public void setEndtime(String s) {endtime=s; }
public void setReturntime(String s) {this.returntime=s; }
public void setReachingtime(String s) {this.reachingtime=s; }
public void setTname(String s) {this.tname=s; }
public void setBname(String s) {bname=s; }
public void setDesc(String s) {this.desc=s; }

public void setBid(int i) {this.bid=i; }
public void setTid(int i) {this.tid=i; }
public void setLid(int i) {this.lid=i; }
public void setTrid(int i) {this.trid=i; }
public void setSeqno(int i) {this.seqno=i; }


public String  getVia1()
{return via1;}
public String  getVia2()
{return via2;}
public String getVia3()
{return via3;}
	


public String getBusno() { return busno; }

public int getBtype() { return btype; }
public int getBno() { return bno; }
public int getStpoint() { return stpoint; }
public int getEpoint() { return epoint; }
public int getCapacity() { return capacity; }
public String getStartpoint() { return startpoint; }
public String getEndpoint() { return endpoint; }
public String getLname() { return lname; }
public String getLaddress() { return laddress; }
public String getStarttime() { return starttime; }
public String getEndtime() { return endtime; }
public String getReturntime() { return returntime; }
public String getReachingtime() { return reachingtime; }
public String getTname() { return tname; }
public String getBname() { return bname; }
public String getDesc() { return desc; }

public int getBid() {return bid; }
public int getTid() {return tid; }
public int getLid() {return lid; }
public int getTrid() {return trid; }
public int getSeqno() {return seqno; }
	}