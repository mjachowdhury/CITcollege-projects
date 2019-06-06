/*
 * Created on Nov 1, 2006
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
public class UserBean  extends ActionForm
{
	protected String login,pass,rtpass,secans,slt,fname,lname,bdmonth,bdday,bdyear,gender,
	edu,occ,ind,ain,addr,city,state,country,zip,phno,dor,email,idproof,idno;
	int secque;
	public void setLogin(String s) {this.login=s;}
	public void setPass(String s) {this.pass=s;}
	public void setRtpass(String s) {this.rtpass=s;}
	public void setSecque(int s) {
		System.out.println("in set secq"+s);
		this.secque=s;}
	public void setSecans(String s) {this.secans=s;}
	public void setSlt(String s) {this.slt=s;}
	public void setFname(String s) {this.fname=s;}
	public void setLname(String s) {this.lname=s;}
	public void setBdmonth(String s) {this.bdmonth=s;}
	public void setBdday(String s) {this.bdday=s;}
	public void setBdyear(String s) {this.bdyear=s;
	setDor();}
	public void setGender(String s) {this.gender=s;}
	public void setEdu(String s) {this.edu=s;}
	public void setOcc(String s) {this.occ=s;}
	public void setInd(String s) {this.ind=s;}
	public void setAin(String s) {this.ain=s;}
	public void setAddr(String s) {this.addr=s;}
	public void setCity(String s) {this.city=s;}
	public void setState(String s) {this.state=s;}
	public void setCountry(String s) {this.country=s;}
	public void setZip(String s) {this.zip=s;}
	public void setPhno(String s) {this.phno=s;}
	public void setEmail(String s) {this.email=s;}
	public void setIdproof(String s) {this.idproof=s;}
	public void setIdno(String s) {this.idno=s;}
	public void setDor()
	{dor=bdmonth+"-"+bdday+"-"+bdyear;
	}
	public void setDor(String s)
	{dor=s;
	}

	public String getLogin() {return login;}
	public String getPass() {return pass;}
	public String getRtpass() {return rtpass;}
	public int  getSecque() {return secque;}
	public String getSecans() {return secans;}
	public String getSlt() {return slt;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getBdmonth() {return bdmonth;}
	public String getBdday() {return bdday;}
	public String getBdyear() {return bdyear;}
	public String getGender() {return gender;}
	public String getEdu() {return edu;}
	public String getOcc() {return occ;}
	public String getInd() {return ind;}
	public String getAin() {return ain;}
	public String getDor(){return dor;}
	public String getAddr() {return addr;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public String getCountry() {return country;}
	public String getZip() {return zip;}
	public String getPhno() {return phno;}
	public String getEmail() {return email;}
	public String getIdproof() {return idproof;}
	public String getIdno() {return idno;}

}
