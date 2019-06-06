//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-05-2007
 * 
 * XDoclet definition:
 * @struts:action path="/viewTiming" name="BusTripBean" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/viewtiming.jsp"
 */
public class ViewTimingAction extends Action {

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
		HttpServletResponse response) throws Exception{
		System.out.println(1);
		BusFormTemp busformtemp= (BusFormTemp) form;
		ArrayList al=Validation.getTripDetailsC(getDataSource(request));
		HttpSession hs=request.getSession();
		hs.setAttribute("td",al);
		System.out.println(1);
		return mapping.findForward("suc");
	}

}