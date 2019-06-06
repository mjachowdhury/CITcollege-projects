/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;
import java.io.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusLocBean implements Serializable {
	
	protected String lname,laddress;
	protected int lid;
	
	public String getLname() {return lname;}
	public String getLaddress() {return laddress;}
	public int getLid() {return lid;}
	
	public void setLid(int i) {this.lid=i;}
	public void setLname(String s) {this.lname=s;}
	public void setLaddress(String s) {this.laddress=s;}

}
