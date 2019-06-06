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
public class BusEntryBean implements Serializable{
	
	protected String busno,bname,bustype;
	protected int bid,btype;
	
	public void setBusno(String s) {this.busno=s;}
	public void setBname(String s) {this.bname=s;}
	public void setBid(int i) {this.bid=i;}
	public void setBtype(int i) {this.btype=i;}
	public void setBustype(String s) {this.bustype=s;}
	
	public String getBusno() {return busno;}
	public String getBname() {return bname;}
	public int getBid() {return bid;}
	public int getBtype() {return btype;}
	public String getBustype() {return bustype;}
	

}
