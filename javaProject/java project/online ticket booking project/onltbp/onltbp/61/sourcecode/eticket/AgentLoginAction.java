//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.validator.*;
import javax.sql.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/** 
 * MyEclipse Struts
 * Creation date: 11-01-2006
 * 
 * XDoclet definition:
 * @struts:action path="/Agentlogin" name="loginBean" scope="request" validate="true"
 */
//public class AgentLoginAction extends Action {

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
/*	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)throws Exception {
		if(isCancelled(request))
			return mapping.findForward("fail");
		DynaValidatorForm loginBean = (DynaValidatorForm) form;
		String name=(String)loginBean.get("name");
		String pass=(String)loginBean.get("pass");
		boolean valid=Validation.verifyAgent(name,pass,getDataSource(request));
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("relog");
		
	}

}*/


public class AgentLoginAction extends Action {

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
		HttpServletResponse response)throws Exception
	{
		if(isCancelled(request))
			return mapping.findForward("fail");
		System.out.println("In Action Agent");
		DynaValidatorForm loginBean = (DynaValidatorForm) form;
		System.out.println("In Action Agent");
		String name=(String)loginBean.get("name");
		String pass=(String)loginBean.get("pass");
DataSource ds=getDataSource(request);
Connection con=ds.getConnection();
Statement st=con.createStatement();

		boolean valid=Validation.verifyAgent(name,pass,getDataSource(request));
		System.out.println("In Action Agent after valid "+valid);
		if(valid)
		{
			HttpSession hs=request.getSession();
					
			hs.setAttribute("agent",name);
			ResultSet rs=st.executeQuery("select email from etagentper where uname='"+name+"'");
			while(rs.next()){hs.setAttribute("froma",rs.getString(1));}
					
			return mapping.findForward("success");
		}
		else
		{
			ActionMessages msgs=new ActionMessages();
			ActionMessage msg5=new ActionMessage("Form.greet");
			ActionMessage msg6=new ActionMessage("Form.uperr");
			msgs.add("upstar",msg5);
			msgs.add("uperror",msg6);
			System.out.println("In uname and pass");
			
			if(msgs!=null)
				saveMessages(request,msgs);
			return mapping.findForward("relog");
		}
	}
}