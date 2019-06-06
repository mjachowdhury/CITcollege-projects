//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;
import javax.sql.*;
import java.sql.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-08-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/Userservices.jsp"
 * @struts:action-forward name="fail" path="Userlogin.jsp"
 */
public class UserLoginAction extends Action {

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
		
			DynaValidatorForm userlogin = (DynaValidatorForm) form;
		String name=(String)userlogin.get("name");
		String pass=(String)userlogin.get("pass");
DataSource ds=getDataSource(request);
Connection con=ds.getConnection();
Statement st=con.createStatement();
		boolean valid=Validation.verifyUser(name,pass,getDataSource(request));
		System.out.println(valid);
		ResultSet rs=st.executeQuery("select  email from etuserdetails where uname='"+name+"'");
		if(valid)
		{
			System.out.println(valid);
			rs.next();
			HttpSession hs=request.getSession();
		hs.setAttribute("name",name);
		hs.setAttribute("fromc",rs.getString(1));
		return mapping.findForward("success");
		}
		return mapping.findForward("fail");


			}

}