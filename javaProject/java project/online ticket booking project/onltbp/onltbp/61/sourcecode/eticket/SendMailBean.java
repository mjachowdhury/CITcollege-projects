//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-02-2007
 * 
 * XDoclet definition:
 * @struts:form name="sf"
 */
public class SendMailBean extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** to property */
	private String to;
	private int id;
	/** from property */
	private String from;

	/** desc property */
	private String desc;
	private String sub;
	private String per;

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
	 * Returns the to.
	 * @return String
	 */
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		System.out.println("in to of set");
		this.to = to;
	}
	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		System.out.println("in to of set");
		this.per = per;
		}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println("in to of set");
		this.id = id;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	/** 
	 * Returns the from.
	 * @return String
	 */
	public String getFrom() {
		return from;
	}

	/** 
	 * Set the from.
	 * @param from The from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/** 
	 * Returns the desc.
	 * @return String
	 */
	public String getDesc() {
		return desc;
	}

	/** 
	 * Set the desc.
	 * @param desc The desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}