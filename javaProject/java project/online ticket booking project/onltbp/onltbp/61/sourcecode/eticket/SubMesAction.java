//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

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
 * Creation date: 03-03-2007
 * 
 * XDoclet definition:
 * @struts:action path="/sub" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/viewdesc.jsp"
 */
public class SubMesAction extends Action {

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
		SendMailBean sf = (SendMailBean) form;
		Integer i=new Integer(request.getParameter("id"));
	DataSource ds=getDataSource(request);
	Connection con=ds.getConnection();
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select desc1 from mess_notice where msgid="+i.intValue());
	while(rs.next()){
	sf.setDesc(rs.getString(1));	
	}
	return mapping.findForward("suc");
	}

}