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
 * Creation date: 03-04-2007
 * 
 * XDoclet definition:
 * @struts:action path="/sendmaila" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="sucagentm.jsp"
 */
public class SendMailActionA extends Action {

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
	Statement st;
	java.util.Date d=new java.util.Date();
System.out.println(1);
	String to=sf.getTo();	
	String from=sf.getFrom();	
	String desc=sf.getDesc();	
	String sub=sf.getSub();
DataSource ds=getDataSource(request);	
Connection con=ds.getConnection();	
ResultSet rs1=null,rs2=null;
System.out.println(2);	System.out.println(sf.getTo());	PreparedStatement ps;	int i=0;
	
ps=con.prepareStatement("insert into mess_notice values(msgid.nextval,?,?,?,?,?)");
System.out.println(3);
ps.setString(1,to);
ps.setString(2,from);
ps.setString(3,sub);
ps.setString(4,desc);
ps.setString(5,d.toLocaleString());
i=ps.executeUpdate();
return mapping.findForward("suc");	
}	

	}

