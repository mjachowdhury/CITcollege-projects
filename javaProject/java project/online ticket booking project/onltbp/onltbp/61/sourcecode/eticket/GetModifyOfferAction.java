//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
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
 * @struts:action path="/GetModifyOffer" name="Offer" scope="request" validate="true"
 * @struts:action-forward name="success" path="/.jsp"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class GetModifyOfferAction extends Action {

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
		DynaValidatorForm offer =(DynaValidatorForm)form;
		String id=request.getParameter("offerid");
		OfferBean ob=Validation.modifyOffer(id,getDataSource(request));
		BeanUtils.copyProperties(offer,ob);
		/*
		offer.set("offerid",ob.getOfferid());
		offer.set("offername",ob.getOffername());
		offer.set("startdate",ob.getStartdate());
		offer.set("enddate",ob.getEnddate());
		offer.set("applfor",ob.getApplfor());
		offer.set("descr",ob.getDescr());
		*/
		if(offer!=null)
		{
			HttpSession hs=request.getSession();
			//HttpSession hs=request.getSession();
			request.setAttribute("offerid",id);
			System.out.println("setAttribute offerid,id)"+id);
			
			hs.setAttribute(mapping.getAttribute(),offer);
			return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}