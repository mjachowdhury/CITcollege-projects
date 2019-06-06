//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-11-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AddBusType" name="addbustypeform" scope="request" validate="true"
 */
public class AddBusTypeAction extends Action {

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
		DynaValidatorForm addbustypeform = (DynaValidatorForm) form;
		BusBean bus=new BusBean();
		System.out.println("Bean created in addbusaction");
		bus.setTname((String)addbustypeform.get("tname"));
		bus.setCapacity(new Integer((addbustypeform.get("capacity")).toString()).intValue());
		bus.setDesc((String)addbustypeform.get("desc"));
		System.out.println("BeaAdding");
		boolean valid=Validation.addBusType(bus,getDataSource(request));		
		System.out.println("Bean Before Adding");
		System.out.println(valid);
		if(valid){
			
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}