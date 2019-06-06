//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-07-2006
 * 
 * XDoclet definition:
 * @struts:form name="UserRegBean"
 */
public class UserRegistrationBean extends ValidatorForm {

	// --------------------------------------------------------- Instance Variables



	/** login property */
	private String login;

	/** pass property */
	private String pass;

	/** rtpass property */
	private String rtpass;
	
	/** secque property */
	private int  secque;

	/** secans property */
	private String secans;

	/** slt property */
	private String slt;

	/** fname property */
	private String fname;

	/** lname property */
	private String lname;

	/** bdmonth property */
	private String bdmonth;

	/** bdday property */
	private String bdday;

	/** bdyear property */
	private String bdyear;

	/** gender property */
	private String gender;

	/** edu property */
	private String edu;

	/** occ property */
	private String occ;

	/** ind property */
	private String ind;

	/** ain property */
	private String ain;

	/** addr property */
	private String addr;

	/** city property */
	private String city;

	/** state property */
	private String state;

	/** country property */
	private String country;

	/** zip property */
	private String zip;

	/** phno property */
	private String phno;

	/** email property */
	private String email;

	/** idproof property */
	private String idproof;

	/** idno property */
	private String idno;

	// --------------------------------------------------------- Methods

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	
	

	/** 
	 * Returns the login.
	 * @return String
	 */
	public String getLogin() {
		return login;
	}

	/** 
	 * Set the login.
	 * @param login The login to set
	 */
	public void setLogin(String login) {
		System.out.println(1);
		this.login = login;
	}

	/** 
	 * Returns the pass.
	 * @return String
	 */
	public String getPass() {
		return pass;
	}

	/** 
	 * Set the pass.
	 * @param pass The pass to set
	 */
	public void setPass(String pass) {
		System.out.println(1);
		this.pass = pass;
	}

	/** 
	 * Returns the rtpass.
	 * @return String
	 */
	public String getRtpass() {
		return rtpass;
	}

	/** 
	 * Set the pass.
	 * @param pass The pass to set
	 */
	public void setRtpass(String rtpass) {
		System.out.println(1);
		this.rtpass = rtpass;
	}

	/** 
	 * Returns the secque.
	 * @return String
	 */
	public int getSecque() {
		
		System.out.println("in get secq"+secque);
		return secque;
	}

	/** 
	 * Set the secque.
	 * @param secque The secque to set
	 */
	public void setSecque(int  secque) {
		System.out.println(1);
		System.out.println("in get secq"+secque);
		
		this.secque = secque;
	}

	/** 
	 * Returns the secans.
	 * @return String
	 */
	public String getSecans() {
		return secans;
	}

	/** 
	 * Set the secans.
	 * @param secans The secans to set
	 */
	public void setSecans(String secans) {
		System.out.println(1);
		this.secans = secans;
	}

	/** 
	 * Returns the slt.
	 * @return String
	 */
	public String getSlt() {
		return slt;
	}

	/** 
	 * Returns the Fname.
	 * @return String
	 */
	public String getFname() {
		return fname;
	}

	/** 
	 * Set the Fname.
	 * @param Fname The Fname to set
	 */
	public void setFname(String fname) {
		System.out.println("In Fname"+fname);
		this.fname = fname;
	}

	/** 
	 * Returns the lname.
	 * @return String
	 */
	public String getLname() {
		return lname;
	}

	/** 
	 * Set the lname.
	 * @param lname The lname to set
	 */
	public void setLname(String lname) {
		System.out.println(1);
		this.lname = lname;
	}

	/** 
	 * Returns the bdmonth.
	 * @return String
	 */
	public String getBdmonth() {
		return bdmonth;
	}

	/** 
	 * Set the bdmonth.
	 * @param bdmonth The bdmonth to set
	 */
	public void setBdmonth(String bdmonth) {
		System.out.println(1);
		this.bdmonth = bdmonth;
	}

	/** 
	 * Returns the bdday.
	 * @return String
	 */
	public String getBdday() {
		return bdday;
	}

	/** 
	 * Set the bdday.
	 * @param bdday The bdday to set
	 */
	public void setBdday(String bdday) {
		System.out.println(1);
		this.bdday = bdday;
	}

	/** 
	 * Returns the bdyear.
	 * @return String
	 */
	public String getBdyear() {
		return bdyear;
	}

	/** 
	 * Set the bdyear.
	 * @param bdyear The bdyear to set
	 */
	public void setBdyear(String bdyear) {
		System.out.println(1);
		this.bdyear = bdyear;
	}

	/** 
	 * Returns the gender.
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/** 
	 * Set the gender.
	 * @param gender The gender to set
	 */
	public void setGender(String gender) {
		System.out.println(1);
		this.gender = gender;
	}

	/** 
	 * Returns the edu.
	 * @return String
	 */
	public String getEdu() {
		return edu;
	}

	/** 
	 * Set the edu.
	 * @param edu The edu to set
	 */
	public void setEdu(String edu) {
		System.out.println(1);
		this.edu = edu;
	}

	/** 
	 * Returns the occ.
	 * @return String
	 */
	public String getOcc() {
		return occ;
	}

	/** 
	 * Set the occ.
	 * @param occ The occ to set
	 */
	public void setOcc(String occ) {
		System.out.println(1);
		this.occ = occ;
	}

	/** 
	 * Returns the ind.
	 * @return String
	 */
	public String getInd() {
		return ind;
	}

	/** 
	 * Set the ind.
	 * @param ind The ind to set
	 */
	public void setInd(String ind) {
		System.out.println(1);
		this.ind = ind;
	}

		/** 
	 * Returns the ain.
	 * @return String
	 */
	public String getAin() {
		return ain;
	}

	/** 
	 * Set the ain.
	 * @param ain The ain to set
	 */
	public void setAin(String ain) {
		System.out.println(1);
		this.ain = ain;
	}

	/** 
	 * Returns the addr.
	 * @return String
	 */
	public String getAddr() {
		return addr;
	}

	/** 
	 * Set the addr.
	 * @param addr The addr to set
	 */
	public void setAddr(String addr) {
		System.out.println(1);
		this.addr = addr;
	}

	/** 
	 * Returns the city.
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	 public void setCity(String city) {
	 	System.out.println(1);
		this.city = city;
	}

	/** 
	 * Returns the state.
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/** 
	 * Set the state.
	 * @param state The state to set
	 */
	 public void setState(String state) {
	 	System.out.println(1);
		this.state = state;
	}

	/** 
	 * Returns the country.
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/** 
	 * Set the country.
	 * @param country The country to set
	 */
	 public void setCountry(String country) {
	 	System.out.println(1);
		this.country = country;
	}

	/** 
	 * Returns the zip.
	 * @return String
	 */
	public String getZip() {
		return zip;
	}

	/** 
	 * Set the zip.
	 * @param zip The zip to set
	 */
	public void setZip(String zip) {
		System.out.println(1);
		this.zip = zip;
	}

	/** 
	 * Returns the phno.
	 * @return String
	 */
	public String getPhno() {
		return phno;
	}

	/** 
	 * Set the phno.
	 * @param phno The phno to set
	 */
	public void setPhno(String phno) {
		System.out.println(1);
		this.phno = phno;
	}

	/** 
	 * Returns the email.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * Set the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		System.out.println(1);
		this.email = email;
	}

		/** 
	 * Returns the idproof.
	 * @return String
	 */
	public String getIdproof() {
		return idproof;
	}

	/** 
	 * Set the idproof.
	 * @param idproof The idproof to set
	 */
	public void setIdproof(String idproof) {
		System.out.println("Id proof");
		this.idproof = idproof;
	}

		/** 
	 * Returns the idno.
	 * @return String
	 */
	public String getIdno() {
		return idno;
	}

	/** 
	 * Set the idno.
	 * @param idno The idno to set
	 */
	public void setIdno(String idno) {
		System.out.println("In Idno1");
		
		this.idno = idno;
	}
}