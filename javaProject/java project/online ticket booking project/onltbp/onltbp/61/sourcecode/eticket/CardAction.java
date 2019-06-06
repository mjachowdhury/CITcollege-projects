package eticket;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

/** 
 * MyEclipse Struts
 * Creation date: 03-09-2008
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="true" path="/success.jsp"
 * @struts:action-forward name="false" path="/fail.jsp"
 */
public class CardAction extends Action {

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
		
		System.out.println("in action");
			
		int cno=Integer.parseInt(request.getParameter("cno"));
		ValidateDao dao=new ValidateDao();
		boolean valid=dao.cardcheck(cno);
		System.out.println("after checking");
		
		if(valid==true)
			return mapping.findForward("true");
		return mapping.findForward("fail");
		
		
		
		


		
	}

}