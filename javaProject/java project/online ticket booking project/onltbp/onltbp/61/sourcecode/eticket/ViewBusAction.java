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
 * Creation date: 11-08-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="viewbus.jsp"
 * @struts:action-forward name="fail" path="/Index.jsp"
 */
public class ViewBusAction extends Action {

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
		
         ArrayList busdetails=null;
         busdetails=Validation.getBuses(getDataSource(request));
         System.out.println("after returning from getBuses");
         if(busdetails!=null)
         {
         	System.out.println("inside if(busdetails!=null)");
         	HttpSession hs=request.getSession();
         	hs.setAttribute("busdetails",busdetails);
         	return mapping.findForward("success");
         }
		return mapping.findForward("fail");
	}

}