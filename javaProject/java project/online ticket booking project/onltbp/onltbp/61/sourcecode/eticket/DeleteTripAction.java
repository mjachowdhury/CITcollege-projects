//Created by MyEclipse Struts
//XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
* MyEclipse Struts
* Creation date: 11-30-2006
* 
* XDoclet definition:
* @struts:action validate="true"
* @struts:action-forward name="success" path="/tripdeleteconf.jsp"
* @struts:action-forward name="fail" path="/viewtripdetails.jsp"
*/
public class DeleteTripAction extends Action {

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
		HttpServletResponse response)throws Exception{

		String i=request.getParameter("trid");
		System.out.println(i);
		boolean valid=Validation.deleteTrip(i,getDataSource(request));
		System.out.println("afetr returning from deletetrip");
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
	}

}