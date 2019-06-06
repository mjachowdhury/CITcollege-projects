//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-05-2007
 * 
 * XDoclet definition:
 * @struts:form name="busformtemp"
 */
public class BusFormTemp extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** startpoint property */
	private String startpoint;

	/** bname property */
	private String bname;

	/** endpoint property */
	private String endpoint;

	/** starttime property */
	private String starttime;

	/** endtime property */
	private String endtime;

	/** busno property */
	private String busno;

	/** trid property */
	private int  trid;

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	

	/** 
	 * Returns the startpoint.
	 * @return String
	 */
	public String getStartpoint() {
		return startpoint;
	}

	/** 
	 * Set the startpoint.
	 * @param startpoint The startpoint to set
	 */
	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

	/** 
	 * Returns the bname.
	 * @return String
	 */
	public String getBname() {
		return bname;
	}

	/** 
	 * Set the bname.
	 * @param bname The bname to set
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}

	/** 
	 * Returns the endpoint.
	 * @return String
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/** 
	 * Set the endpoint.
	 * @param endpoint The endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/** 
	 * Returns the starttime.
	 * @return String
	 */
	public String getStarttime() {
		return starttime;
	}

	/** 
	 * Set the starttime.
	 * @param starttime The starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/** 
	 * Returns the endtime.
	 * @return String
	 */
	public String getEndtime() {
		return endtime;
	}

	/** 
	 * Set the endtime.
	 * @param endtime The endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/** 
	 * Returns the busno.
	 * @return String
	 */
	public String getBusno() {
		return busno;
	}

	/** 
	 * Set the busno.
	 * @param busno The busno to set
	 */
	public void setBusno(String busno) {
		this.busno = busno;
	}

	/** 
	 * Returns the trid.
	 * @return Integer
	 */
	public int  getTrid() {
		return trid;
	}

	/** 
	 * Set the trid.
	 * @param trid The trid to set
	 */
	public void setTrid(int trid) {
		this.trid = trid;
	}

}