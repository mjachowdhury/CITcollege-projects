//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-05-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/updatebustype.jsp"
 * @struts:action-forward name="fail" path="/viewbustype.jsp"
 */
public class BusTypeAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)throws Exception {
        
		DynaValidatorForm addbustypeform1=(DynaValidatorForm)form;
		HttpSession hs=request.getSession(false); 
		BustypeBean busty=(BustypeBean)hs.getAttribute("b");
		addbustypeform1.set("tname",busty.getTname());
		addbustypeform1.set("capacity",(new Integer(busty.getCapacity())));
		addbustypeform1.set("desc",busty.getDesc());
		hs.setAttribute(mapping.getAttribute(),addbustypeform1);
		return mapping.findForward("success");
	}

}