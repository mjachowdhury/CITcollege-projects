//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.*;
import org.apache.struts.validator.*;
import org.apache.struts.action.*;

/** 
 * MyEclipse Struts
 * Creation date: 11-07-2006
 * 
 * XDoclet definition:
 * @struts:action path="/Addbus" name="seeit" scope="request" validate="true"
 */
public class AddBusAction extends Action {

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
	   DynaValidatorForm busForm = (DynaValidatorForm) form;
	    BusEntryBean bus=new BusEntryBean();
	    System.out.println("Bean created1");
	    String busno=(String)busForm.get("busno");
	    //HttpSession hs=request.getSession();
	    request.setAttribute("busnumber",busno);
	bus.setBusno(busno);
	System.out.println("Bean created2");
	System.out.println(busForm.get("btype"));
	System.out.println((busForm.get("btype")).toString());
	System.out.println(busForm.get("btype"));
	int i=(new Integer(busForm.get("btype").toString())).intValue();
	System.out.println(i);
	bus.setBtype(i);
	System.out.println("Bean created3");
	bus.setBname((String)busForm.get("bname"));
	System.out.println("Bean created4");
	
	boolean valid=Validation.addBus(bus,getDataSource(request));
	System.out.println("after validation");
	System.out.println(valid);
	if(valid)
		return mapping.findForward("success");
	return mapping.findForward("fail");
			
	}

}