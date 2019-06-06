//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 11-11-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/tripdetails.jsp"
 * @struts:action-forward name="fail" path="/addbus1.jsp"
 */
public class GetLocationAction extends Action {

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

		ArrayList loc=new ArrayList();
		ArrayList busal=new ArrayList();
		busal=Validation.getBusNo(getDataSource(request));
		System.out.println(request.getAttribute("busnumber"));
		loc=Validation.getLocations(getDataSource(request));
		if(loc!=null)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("loc",loc);
		hs.setAttribute("busal",busal);
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}