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
 * Creation date: 11-08-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/ViewPendingAgent.jsp"
 * @struts:action-forward name="fail" path="/Index.jsp"
 */
public class ViewPendingAgentAction extends Action {

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

		 ArrayList agents=null;
         agents=Validation.getPendingAgents(getDataSource(request));
         System.out.println("in agents!null before block");
         if(agents!=null)
         {
         	System.out.println("in agents!null block");
         	HttpSession hs=request.getSession();
         	hs.setAttribute("agents",agents);
         	return mapping.findForward("success");
         }
		return mapping.findForward("fail");
	}

}