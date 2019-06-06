//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;
import javax.sql.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;

import org.apache.struts.validator.*;
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
 * @struts:action path="/Adminlogin" name="loginBean" input="/Adminlogin.jsp" scope="request" validate="true"
 * @struts:action-forward name="success" path="/Index.jsp"
 * @struts:action-forward name="fail" path="/Adminlogin.jsp"
 */
public class AdminLoginAction extends Action {

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
		try{
		if(isCancelled(request))
			return mapping.findForward("fail");
		System.out.println("In Action Admin");
		DynaValidatorForm loginBean = (DynaValidatorForm) form;
		System.out.println("In Action Admin");
		String name=(String)loginBean.get("name");
		String pass=(String)loginBean.get("pass");
		DataSource ds=getDataSource(request);
		System.out.println("new"+ds);
		Connection con=ds.getConnection();
		System.out.println(con);
		Statement st=con.createStatement();
		System.out.println(st);
		boolean valid=Validation.verifyAdmin(name,pass,getDataSource(request));
		System.out.println("In Action Admin after valid "+valid);
		if(valid)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("admin",name);
			ResultSet rs=st.executeQuery("select email from admindetails where username='"+name+"'");
			while(rs.next()){hs.setAttribute("from",rs.getString(1));}
			

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
		catch (Exception e){
			return mapping.findForward("relog");
}
		
	}
}