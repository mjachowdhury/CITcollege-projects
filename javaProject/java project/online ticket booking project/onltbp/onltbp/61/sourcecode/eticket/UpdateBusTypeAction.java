//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @struts:action path="/UpdateBusType" name="addbustypeform" scope="request" validate="true"
 * @struts:action-forward name="success" path="/updatetypeconf.jsp"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class UpdateBusTypeAction extends Action {

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
		DynaValidatorForm addbustypeform1 = (DynaValidatorForm) form;
		int i=(new Integer((String)request.getParameter("tid"))).intValue();
		BustypeBean bus=new BustypeBean();
		bus.setTid(i);
		bus.setTname((String)addbustypeform1.get("tname"));
		bus.setCapacity((addbustypeform1.get("capacity")).toString());
		bus.setDesc((String)addbustypeform1.get("desc"));
		boolean valid=Validation.updateBusType(bus,getDataSource(request));
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
	}

}