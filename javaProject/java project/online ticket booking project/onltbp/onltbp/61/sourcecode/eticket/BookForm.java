//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts:form name="bform"
 */
public class BookForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** capacity property */
	private int capacity;

	/** no_of_tickets property */
	private Collection no_of_tickets;
	private Collection day=new ArrayList();
	private Collection month=new ArrayList();
	private Collection year=new ArrayList();

	/** username property */
	private Collection username=new ArrayList();
	private int tickid;
	private int bdday;
	private String bdmonth;
	private int bdyear;
	private int not;
	private Collection not1=new ArrayList();
	private String email;
	private int vacant;
	private String date;
	private String busno;
	private int reserved;
	private String username1;

		
	public int getBdday() {
		return bdday;
	}
	public void setBdday(String bdday) {
		System.out.println("in day"+bdday);
		this.bdday = new Integer(bdday).intValue();
	}
	
	public String getBdmonth() {
		return bdmonth;
	}	
	public void setBdmonth(String bdmonth) {
		System.out.println("int mon");
			this.bdmonth = bdmonth;	}
	
	public int getBdyear() {
		
		 	return bdyear;
	}
	public void setBdyear(String bdyear) {
		System.out.println(bdyear);
		this.bdyear = new Integer(bdyear).intValue();
		date=bdday+"-"+bdmonth+"-"+bdyear;
		System.out.println("year==="+bdyear);
	}
	
		
	public int getNot() {		return not;	}
	
	public void setNot(String not) {				
		this.not = new Integer(not).intValue();
	System.out.print("not"+this.not);	}
	
	 
	
	public int  getCapacity() {		return capacity;	}

	
	public void setCapacity(String capacity) {
		this.capacity = new Integer(capacity).intValue();
		System.out.println("capacity"+this.capacity);
	}

	
	public String getUsername1() {
		System.out.println("username1"+username1);
		return username1;
	}

	
	public void setUsername1(String username1) {
		this.username1 = username1;
	}


	public Collection getUsername() {
		System.out.println("username:L"+username);
		return username;
	}

	
	public void setUsername(Collection username) {
		
		this.username = username;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		this.email = email;
		System.out.println("email"+this.email);
	}

	
	public int getVacant() {
		return vacant;
	}

	
	public void setVacant(int vacant) {
		this.vacant = vacant;
		System.out.println("vacant"+this.vacant);
	}

	
	public String getDate() {
		System.out.println("date new "+date);
		return date;
	}

	
	
	public String getBusno() {
		return busno;
	}

	
	public void setBusno(String busno) {
		System.out.print("In Form Bean");
		this.busno = busno;
		System.out.println("busno"+this.busno);
	}

	
	public int  getReserved() {
		return reserved;
	}

	
	public void setReserved(int reserved) {
		this.reserved = reserved;
		System.out.print("reserved"+this.reserved);
	}

	
	public int getTickid() {
		return tickid;
	}
		public void setTickid(int  tickid) {
		
		this.tickid = tickid;
		System.out.print("tickid"+this.tickid);
	}

	public Collection getNo_of_tickets() {
		return no_of_tickets;
	}
	
	public void setNo_of_tickets(Collection no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}
	public void setCapacity(int capacity) {
		System.out.println("capacity"+capacity);
		this.capacity = capacity;
	}
	public void setDate(String date1) {
		date=date1;
			}
	
	public Collection getDay() {
		return day;
	}
	public void setDay(Collection day) {
		this.day = day;
	}
	public Collection getMonth() {
		return month;
	}
	public void setMonth(Collection month) {
		this.month = month;
	}
	public Collection getNot1() {
		return not1;
	}
	public void setNot1(Collection not1) {
		this.not1 = not1;
	}
	public Collection getYear() {
		return year;
	}
	public void setYear(Collection year) {
		this.year = year;
	}
}