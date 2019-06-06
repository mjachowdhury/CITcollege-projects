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

/** 
 * MyEclipse Struts
 * Creation date: 12-06-2006
 * 
 * XDoclet definition:
 * @struts:action path="/UpdateAgent" name="AgentRegistrationForm" scope="request" validate="true"
 * @struts:action-forward name="success" path="/updateagentconf.jsp"
 * @struts:action-forward name="fail" path="/modifyperagent.jsp"
 */
public class UpdateAgentAction extends Action {

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
		HttpServletResponse response) throws Exception {
		 AgentRegistrationForm arf= (AgentRegistrationForm) form;
		 AgentBean ag=new AgentBean();
		 System.out.println(" in updateagent"+ arf.getDor());
		
		BeanUtils.copyProperties(ag,arf);
		boolean valid=Validation.updateAgent(ag,getDataSource(request));
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
		}

}