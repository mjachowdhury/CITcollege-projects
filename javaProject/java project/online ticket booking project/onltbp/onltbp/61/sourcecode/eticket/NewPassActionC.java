//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-08-2007
 * 
 * XDoclet definition:
 * @struts:action path="/newpassC" name="ub" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/suc.jsp"
 */
public class NewPassActionC extends Action {

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
		UserBean ub =(UserBean)form;
		System.out.println("in Action");
DataSource ds=getDataSource(request);
Connection con=ds.getConnection();
PreparedStatement ps=con.prepareStatement("select password from etuserdetails where userid=? and secq=? and seca=?");
ps.setString(1,ub.getLogin());
ps.setInt(2,ub.getSecque());
ps.setString(3,ub.getSecans());
ResultSet rs=ps.executeQuery();
ResultSet rs1=null;
while(rs.next()){
	System.out.println(rs.getString(1));
Statement st=con.createStatement();
System.out.println(st.executeUpdate("update etuserdetails set password='"+ub.getPass()+"'where userid='"+ub.getLogin()+"'"));
System.out.println(1);
HttpSession hs=request.getSession();
rs1=st.executeQuery("select password from etuserdetails where userid='"+ub.getLogin()+"'");
System.out.println(2);
hs.setAttribute("userid",ub.getLogin());
while(rs1.next()){
hs.setAttribute("passc",rs1.getString(1));
System.out.println(3);
System.out.println("value of new password"+rs1.getString(1));
}
return mapping.findForward("suc");

}	
return mapping.findForward("fail");
	}

}