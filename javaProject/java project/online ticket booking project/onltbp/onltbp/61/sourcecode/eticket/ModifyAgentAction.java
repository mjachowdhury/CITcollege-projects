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

/** 
 * MyEclipse Struts
 * Creation date: 12-06-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/modifyperagent.jsp"
 * @struts:action-forward name="fail" path="/permanentagentdetails.jsp"
 */
public class ModifyAgentAction extends Action {

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

		System.out.println("In ModifyAgentAction");
		AgentRegistrationForm arf=(AgentRegistrationForm)form;
		System.out.println("In ModifyAgentAction1");
		String agregno=(String)request.getParameter("agregno");
		System.out.println(agregno);
		System.out.println("In ModifyAgentAction2");
		AgentBean obj7=Validation.getAgent(agregno,getDataSource(request));
		System.out.println("In ModifyAgentAction3"+obj7);
		if(obj7!=null)
		{
			System.out.println("In modify agent if not null");
		  HttpSession hs=request.getSession(false);
		  System.out.println("from onj7"+obj7.getDor());
		  arf.setLogin(obj7.getLogin());
		  arf.setPass(obj7.getPass());
	      arf.setFname(obj7.getFname());
	       arf.setLname(obj7.getLname());
	      arf.setAddr(obj7.getAddr());
	      arf.setCity(obj7.getCity());
	      arf.setState(obj7.getState());
	       //System.out.println(obj7.getDor().substring(0,2));
	        //System.out.println(obj7.getDor().substring(3,5));
	        //System.out.println(obj7.getDor().substring(6));
	       
	       // arf.setDrday(obj7.getDor().substring(0,2));
	        //arf.setDrmonth(obj7.getDor().substring(3,5));
	        //arf.setDryear(obj7.getDor().substring(6));
	      arf.setDor(obj7.getDor()); 
	       arf.setRegno(obj7.getRegno());
	      arf.setEmail(obj7.getEmail());
	      arf.setPhno(obj7.getPhno());
		  //BeanUtils.copyProperties(arf,obj7);
		  
		  System.out.println("from arf"+arf.getDor());
		  
		  return mapping.findForward("success");
		}
		return mapping.findForward("fail");
	}

}