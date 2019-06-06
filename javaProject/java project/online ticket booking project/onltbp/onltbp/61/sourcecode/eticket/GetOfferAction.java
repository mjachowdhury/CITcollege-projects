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

import java.util.*;
/** 
 * MyEclipse Struts
 * Creation date: 11-21-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/viewofferdetails.jsp"
 * @struts:action-forward name="fail" path="/offer_form.jsp"
 */
public class GetOfferAction extends Action {

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
		HttpServletResponse response) throws Exception  {
		System.out.println("in getofferAction");
		ArrayList offer=Validation.getOffers(getDataSource(request));
		System.out.println("in getofferAction");
		if(offer!=null)
		{
			System.out.println("not null");
			
		HttpSession hs=request.getSession();
		
		hs.setAttribute("offer",offer);
		System.out.println(offer.size());
		
		return mapping.findForward("success");
		}
		return mapping.findForward("fail");
		}

}