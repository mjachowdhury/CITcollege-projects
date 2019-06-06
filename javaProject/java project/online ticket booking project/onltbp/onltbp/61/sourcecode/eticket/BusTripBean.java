/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;
import java.io.*;
import org.apache.struts.action.ActionForm;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusTripBean extends ActionForm implements Serializable  {
	
	protected String starttime,bno,endtime,bname;;
	protected int trid,startpoint,endpoint;
	
	public void setStartpoint(int i) {this.startpoint=i;}
	public void setStarttime(String s) {this.starttime=s;}
	public void setEndpoint(int i) {this.endpoint=i;}
	public void setEndtime(String s) {this.endtime=s;}
	public void setTrid(int i) {this.trid=i;}
	public void setBno(String i) {this.bno=i;}
    public void setBname(String s){this.bname=s;}
    
	public int getStartpoint() {return startpoint;}
	public String getStarttime() {return starttime;}
	public int getEndpoint() {return endpoint;}
	public String getEndtime() {return endtime;}
	public int getTrid() {return trid;}
	public String getBname() {return bname;}
	public String getBno() {return bno;}

}
