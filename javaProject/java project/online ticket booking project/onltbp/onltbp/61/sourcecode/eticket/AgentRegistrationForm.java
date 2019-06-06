//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import org.apache.struts.validator.ValidatorForm;



/** 
 * MyEclipse Struts
 * Creation date: 11-01-2006
 * 
 * XDoclet definition:
 * @struts:form name="agentReg"
 */
public class AgentRegistrationForm extends ValidatorForm {

	// --------------------------------------------------------- Instance Variables

	/** login property */
	private String login;

	/** pass property */
	private String pass;

	/** rtpass property */
	private String rtpass;
	
	/** secque property */
	private int secque;

	/** secans property */
	private String secans;

	/** slt property */
	private String slt;

	/** fname property */
	private String fname;

	/** lname property */
	private String lname;

	/** gender property */
	private String gender;

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

	/** bdmonth property */
	private String drmonth;

	/** bdday property */
	private String drday;

	/** bdyear property */
	private String dryear;

	/** idno property */
	private String regno;
	private String dor="";
	

	// --------------------------------------------------------- Methods

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
		this.rtpass = rtpass;
	}

	/** 
	 * Returns the secque.
	 * @return String
	 */
	public int  getSecque() {
		return secque;
	}

	/** 
	 * Set the secque.
	 * @param secque The secque to set
	 */
	public void setSecque(int  secque) {
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
		this.lname = lname;
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
		this.gender = gender;
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
		this.state = state;
	}

	 public String getDor() {
		return dor;
	}

	 public void setDor() {
	 	
		dor =drmonth+"-"+drday+"-"+dryear;
		System.out.println("dor from arf=="+dor);
	}
public void setDor(String s)
{this.dor=s;}
	public String getCountry() {
		return country;
	}

	 public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	/** 
	 * Set the zip.
	 * @param zip The zip to set
	 */
	public void setZip(String zip) {
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
		this.email = email;
	}

	/** 
	 * Returns the drmonth.
	 * @return String
	 */
	public String getDrmonth() {
		return drmonth;
	}

	/** 
	 * Set the drmonth.
	 * @param drmonth The drmonth to set
	 */
	public void setDrmonth(String drmonth) {
		this.drmonth = drmonth;
	}

	/** 
	 * Returns the drday.
	 * @return String
	 */
	public String getDrday() {
		return drday;
	}

	/** 
	 * Set the drday.
	 * @param drday The drday to set
	 */
	public void setDrday(String drday) {
		this.drday = drday;
	}

	/** 
	 * Returns the dryear.
	 * @return String
	 */
	public String getDryear() {
		return dryear;
	}

	/** 
	 * Set the dryear.
	 * @param dryear The dryear to set
	 */
	public void setDryear(String dryear) {
		this.dryear = dryear;
		setDor();
	}

	/** 
	 * Returns the regno.
	 * @return String
	 */
	public String getRegno() {
		return regno;
	}

	/** 
	 * Set the regno.
	 * @param regno The regno to set
	 */
	public void setRegno(String regno) {
		this.regno = regno;
	}
}