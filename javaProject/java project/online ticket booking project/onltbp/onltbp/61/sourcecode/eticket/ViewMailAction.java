//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-03-2007
 * 
 * XDoclet definition:
 * @struts:action path="/viewMail" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="view1.jsp"
 */
public class ViewMailAction extends Action {

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
		SendMailBean sf = (SendMailBean) form;
		HttpSession hs=request.getSession();
	String name=(String)hs.getAttribute("from");
	System.out.println("name="+name);
	DataSource ds=getDataSource(request);
	Connection con=ds.getConnection();
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from mess_notice where to1='"+name+"'");
	System.out.println("name="+name);
	Vector v=new Vector();
	while(rs.next())
	{
		SendMailBean sf1=new SendMailBean();
		sf1.setId(rs.getInt(1));
		sf1.setFrom(rs.getString(3));
		sf1.setSub(rs.getString(4));
		sf1.setDesc(rs.getString(5));
		sf1.setPer(rs.getString(6));
		v.add(sf1);
		System.out.println(rs.getString(4));
	}
	System.out.println(v.size());
	hs.setAttribute("vmail",v);
	return mapping.findForward("suc");
	}

}