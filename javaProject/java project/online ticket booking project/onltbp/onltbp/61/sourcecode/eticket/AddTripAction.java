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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-13-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AddTrip" name="tripform" scope="request" validate="true"
 * @struts:action-forward name="success" path="/"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class AddTripAction extends Action {

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
		System.out.println("in trip action");
		DynaValidatorForm tripform = (DynaValidatorForm) form;
		System.out.println("in action trip");
		BusTripBean bus=new BusTripBean();
		String busnumber=(String)tripform.get("bno");
		request.setAttribute("busnumber",busnumber);
		bus.setBno(busnumber);
		System.out.println("bno");
		bus.setStartpoint(Integer.parseInt(tripform.get("startpoint").toString()));
		System.out.println("startpoint");
		bus.setEndpoint(Integer.parseInt(tripform.get("endpoint").toString()));
		System.out.println("one");
		if(bus.getStartpoint()==bus.getEndpoint())
		{
			ActionMessages msgs=new ActionMessages();
			ActionMessage msg=new ActionMessage("Form.greet");
			ActionMessage msg1=new ActionMessage("Form.error1");
			msgs.add(ActionMessages.GLOBAL_MESSAGE,msg);
			msgs.add(ActionMessages.GLOBAL_MESSAGE,msg1);
			if(msgs!=null)
			  saveMessages(request,msgs);
		return mapping.findForward("fail");
		}
		bus.setStarttime((String)tripform.get("starttime"));
		System.out.println("two");
		bus.setEndtime((String)tripform.get("endtime"));
		System.out.println("three");
		int i=Validation.addTripDetails(bus,getDataSource(request));
		System.out.println("four");
		System.out.println("i=="+i);
		if(i!=0)
		{
			System.out.println("inside if :one");
			HttpSession hs=request.getSession(false);
			System.out.println("inside if:two");
			hs.setAttribute("trid",(new Integer(i)).toString());
			System.out.println("inside if:three");
			return mapping.findForward("success");	
		}
		return mapping.findForward("fail");
	}

}