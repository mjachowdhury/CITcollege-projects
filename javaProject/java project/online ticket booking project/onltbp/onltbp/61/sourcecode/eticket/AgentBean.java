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
public class AgentBean extends ActionForm{
	protected String login,pass,rtpass,secans,slt,fname,lname,gender,
	occ,ind,ain,addr,city,state,country,zip,phno,email,drmonth,drday,dryear,regno,dor;
	int secque;
	public void setLogin(String s) {this.login=s;}
	public void setPass(String s) {this.pass=s;}
	public void setRtpass(String s) {this.rtpass=s;}
	public void setSecque(int  s) {this.secque=s;}
	public void setSecans(String s) {this.secans=s;}
	public void setSlt(String s) {this.slt=s;}
	public void setFname(String s) {this.fname=s;}
	public void setLname(String s) {this.lname=s;}
	public void setGender(String s) {this.gender=s;}
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
	public void setDrmonth(String s) {this.drmonth=s;}
	public void setDrday(String s) {this.drday=s;}
	public void setDryear(String s) {this.dryear=s;}
	public void setRegno(String s) {this.regno=s;}
    public void setDor(String s){this.dor=s;}
	
	public String getLogin() {return login;}
	public String getPass() {return pass;}
	public String getRtpass() {return rtpass;}
	public int  getSecque() {return secque;}
	public String getSecans() {return secans;}
	public String getSlt() {return slt;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getGender() {return gender;}
	public String getOcc() {return occ;}
	public String getInd() {return ind;}
	public String getAin() {return ain;}
	public String getAddr() {return addr;}
	public String getCity() {return city;}
	public String getState() {return state;}
	public String getCountry() {return country;}
	public String getZip() {return zip;}
	public String getPhno() {return phno;}
	public String getEmail() {return email;}
	public String getDrmonth() {return drmonth;}
	public String getDrday() {return drday;}
	public String getDryear() {return dryear;}
	public String getRegno() {return regno;}
	public String getDor(){
		return dor;
	}
	
}
