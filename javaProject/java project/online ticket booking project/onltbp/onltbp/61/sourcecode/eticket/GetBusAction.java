//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-07-2006
 * 
 * XDoclet definition:
 * @struts:action path="/Getbus" name="busForm" scope="request"
 * @struts:action-forward name="success" path="UpdateBus.jsp"
 * @struts:action-forward name="fail" path="viewbus.jsp"
 */
public class GetBusAction extends Action {

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
		
		
			String busno=request.getParameter("busno");
			DynaValidatorForm busForm1=(DynaValidatorForm)form; 
			BusEntryBean bus=Validation.viewBus(busno,getDataSource(request));
			if(bus!=null)
			{
				System.out.println("in getbusaction in if");
				BeanUtils.copyProperties(busForm1,bus);
				/*busForm.setBusno(bus.getBusno());
				busForm.setBustype(bus.getBustype());
				busForm.setSeats(bus.getSeats());
				busForm.setStartpoint(bus.getStartpoint());
				busForm.setEndpoint(bus.getEndpoint());
				busForm.setStarttime(bus.getStarttime());
				busForm.setEndtime(bus.getEndtime());
				busForm.setReturntime(bus.getReturntime());
				busForm.setReachingtime(bus.getReachingtime());
				busForm.setVia1(bus.getVia1());
				busForm.setVia2(bus.getVia2());
				busForm.setVia3(bus.getVia3());
				*/
				System.out.println(busForm1.get("bname"));
				HttpSession hs=request.getSession();
				hs.setAttribute(mapping.getAttribute(),busForm1);
				return mapping.findForward("success");
			}
			else
			{
				//throw new Exception(busno+"Not Found.");
				return mapping.findForward("fail");
			}
			
			
		}
		
	}

