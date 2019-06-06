//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;
import java.sql.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-05-2007
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="suc" path="/sch1.jsp"
 */
public class ChangeHaltAction extends Action {

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
int sno=new Integer(request.getParameter("seqno")).intValue();
String id=request.getParameter("id");
HttpSession hs=request.getSession(); 
hs.setAttribute("id",id);
Connection con=getDataSource(request).getConnection();
Statement st=con.createStatement();
int i=st.executeUpdate("delete from triphalts where seqno="+sno);
return mapping.findForward("suc");
	}

}