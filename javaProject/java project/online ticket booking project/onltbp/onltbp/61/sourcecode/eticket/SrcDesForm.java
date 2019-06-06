//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-02-2007
 * 
 * XDoclet definition:
 * @struts:form name="sdform"
 */
public class SrcDesForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** src property */
	private Collection src;

	/** name property */
	private String name;
	private String name1="yathi";

	/** des property */
	private Collection des;

	// --------------------------------------------------------- Methods

	/** 
	 * Returns the src.
	 * @return Collection
	 */
	public Collection getSrc() {
		return src;
	}

	/** 
	 * Set the src.
	 * @param src The src to set
	 */
	public void setSrc(Collection src) {
		System.out.println("in set src");
		this.src = src;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName(String s) {
		this.name =s;
	}public String getName1() {
		return name1;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName1(String s) {
		this.name1 =s;
	}

	/** 
	 * Returns the des.
	 * @return Collection
	 */
	public Collection getDes() {
		return des;
	}

	/** 
	 * Set the des.
	 * @param des The des to set
	 */
	public void setDes(Collection des) {
		System.out.println("in set desc");
		this.des = des;
	}

}