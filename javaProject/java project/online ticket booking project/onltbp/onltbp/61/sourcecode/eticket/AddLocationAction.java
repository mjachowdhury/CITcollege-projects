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
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-13-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AddLocation" name="addloc" scope="request" validate="true"
 * @struts:action-forward name="success" path="/tripdetails.jsp"
 * @struts:action-forward name="fail" path="/addlocation.jsp"
 */
public class AddLocationAction extends Action {

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
		DynaValidatorForm locform = (DynaValidatorForm) form;
		String busnumber=request.getParameter("busnumber");
		System.out.println(busnumber);
		BusLocBean bus=new BusLocBean();
		System.out.println("in add location bean created");
		bus.setLname((String)locform.get("lname"));
		bus.setLaddress((String)locform.get("laddress"));
		System.out.println("in add location action");
		System.out.println(bus.getLname());
		System.out.println(bus.getLaddress());
		
		boolean valid=Validation.addLocation(bus,getDataSource(request));
		System.out.println("vali=="+valid);
		if(valid)
		{
			HttpSession hs=request.getSession(false);
			ArrayList loc=(ArrayList)hs.getAttribute("loc");
			loc.add(bus);
			request.setAttribute("busnumber",busnumber);
			System.out.println(busnumber);
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}