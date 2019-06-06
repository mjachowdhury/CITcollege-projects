//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-28-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/edittripdetails.jsp"
 * @struts:action-forward name="fail" path="/viewtripdetails.jsp"
 */
public class EditTripAction extends Action {

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

		DynaValidatorForm tripform1=(DynaValidatorForm)form;
		String trid=request.getParameter("trid");
		System.out.println(trid);
		BusTripBean obj2=Validation.viewTrip(trid,getDataSource(request));
		System.out.println("After returning from view Trip");
		if(obj2!=null)
		{
			BeanUtils.copyProperties(tripform1,obj2);
			System.out.println("Bean copied");
			HttpSession hs=request.getSession(false);
			hs.setAttribute(mapping.getAttribute(),tripform1);
			System.out.println("copying success");
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}