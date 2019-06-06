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
 * Creation date: 11-22-2006
 * 
 * XDoclet definition:
 * @struts:action path="/UpdateOffer" name="Offer" scope="request" validate="true"
 * @struts:action-forward name="success" path="/.jsp"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class UpdateOfferAction extends Action {

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
		HttpServletResponse response) throws Exception	 {
		DynaValidatorForm offer = (DynaValidatorForm) form;
		
	String id=request.getParameter("offerid");
	System.out.println(id);
	    OfferBean ob=new OfferBean();
		ob.setOfferid(id);
		System.out.println(ob.getOfferid());
		ob.setOffername((String)offer.get("offername"));
		ob.setStartdate(((String)offer.get("startdate")));
        ob.setEnddate(((String)offer.get("enddate")));
        ob.setApplfor((String)offer.get("applfor"));
        ob.setDescr((String)offer.get("descr"));
        boolean valid=Validation.updateOffer(ob,getDataSource(request));
        System.out.println("After valid in UpdateoFFER");
        System.out.println(valid);
        if(valid){
        	
        	return mapping.findForward("success");}
        
        return mapping.findForward("fail");
		
		
		
		
			}

}