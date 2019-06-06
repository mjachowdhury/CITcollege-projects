//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-23-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AddTripHalt" name="triphaltform" scope="request" validate="true"
 * @struts:action-forward name="success" path="/businsertconf.jsp"
 * @struts:action-forward name="fail" path="/addtriphalts.jsp"
 */
public class AddTripHaltAction extends Action {

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
		DynaActionForm triphaltform = (DynaActionForm) form;
		TriphaltBean tb=new TriphaltBean();
		tb.setLid(Integer.parseInt((triphaltform.get("lid")).toString()));
		tb.setTrid(Integer.parseInt((triphaltform.get("trid")).toString()));
		System.out.println(tb.getLid()+"     "+tb.getTrid());
		boolean valid=Validation.addTripHalt(tb,getDataSource(request));
		System.out.println(valid);
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
		
	}

}