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
 * Creation date: 11-13-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/.jsp"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class GetTripDetailsAction extends Action {

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
     
		ArrayList loc1=new ArrayList();
		//ArrayList al=new ArrayList();
		//al=Validation.getTripDetails(getDataSource(request));
		
		loc1=Validation.getLocations(getDataSource(request));
		if(loc1!=null)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("loc1",loc1);
			//hs.setAttribute("busal",al);
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
		
	}

}