package eticket;
import java.io.*;

public class OfferBean implements Serializable
	{
protected String offerid,offername,startdate,enddate,applfor,descr;
protected int valid;
	             

public void setOfferid(String s) {this.offerid=s; }
public void  setOffername(String  i){this.offername=i;}
public void setStartdate(String i){this.startdate=i;}
public void setEnddate(String i){this.enddate=i;}
public void setValid(int i){this.valid=i;}
public void setApplfor(String i) {this.applfor=i; }
public void setDescr(String i) {this.descr=i; }//For tripdetails.jsp

public String  getOfferid()
{return offerid;}
public String  getOffername()
{return offername;}
public String getStartdate()
{return startdate;}
public String getEnddate()
{return enddate;}
public String getApplfor()
{return applfor;}
public String getDescr()
{return descr;}
public int getValid(){return valid;}
}