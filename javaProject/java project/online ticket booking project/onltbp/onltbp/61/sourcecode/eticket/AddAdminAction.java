//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

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
 * Creation date: 12-02-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AddAdmin" name="adminform" scope="request" validate="true"
 * @struts:action-forward name="success" path="/addadminconf.jsp"
 * @struts:action-forward name="fail" path="/addadmin.jsp"
 */
public class AddAdminAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// ------------------------------------------	--------------- Methods

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
		DynaValidatorForm adminform = (DynaValidatorForm) form;
		AdminBean admin=new AdminBean();
		System.out.println("1 from add aDMINACTION");
		BeanUtils.copyProperties(admin,adminform);
		boolean valid=Validation.addAdmin(admin,getDataSource(request));
		System.out.println(valid);
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
	}

}