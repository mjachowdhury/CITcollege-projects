//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import java.sql.*;
import javax.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-02-2007
 * 
 * XDoclet definition:
 * @struts:action path="/sendMail.do" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/suc.jsp"
 */
public class SendMailAction extends Action {

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
		Statement st;
		java.util.Date d=new java.util.Date();
	System.out.println(1);
		String to=sf.getTo();	String from=sf.getFrom();	String desc=sf.getDesc();	String sub=sf.getSub();
	DataSource ds=getDataSource(request);	Connection con=ds.getConnection();	ResultSet rs1=null,rs2=null;
	System.out.println(2);	System.out.println(sf.getTo());	PreparedStatement ps;	int i=0;
if(sf.getTo().equals("All"))
{
	
	st=con.createStatement();
ResultSet rs=st.executeQuery("select email from etagentper");
while(rs.next())
{
	st=con.createStatement();
	rs1=st.executeQuery("select msgid.nextval from dual");
	while(rs1.next()){i=rs1.getInt(1);System.out.println(i);}
	ps=con.prepareStatement("insert into mess_notice values(?,?,?,?,?,?)");
	ps.setInt(1,i);
	ps.setString(2,rs.getString(1));
	ps.setString(3,from);
	ps.setString(4,sub);
	ps.setString(5,desc);
	ps.setString(6,d.toLocaleString());
	i=ps.executeUpdate();
}//while
ps=null;

System.out.println("before rs2 while");
rs2=st.executeQuery("select email from etuserdetails");
while(rs2.next())
{
	System.out.println("in rs2");
	 st=con.createStatement();
	 rs1=st.executeQuery("select msgid.nextval from dual");
	 while(rs1.next()){i=rs1.getInt(1);System.out.println(i);}

	ps=con.prepareStatement("insert into mess_notice values(?,?,?,?,?,?)");
	ps.setInt(1,i);
	System.out.println(rs2.getString(1));
	ps.setString(2,rs2.getString(1));
	ps.setString(3,from);
	ps.setString(4,sub);
	ps.setString(5,desc);
	ps.setString(6,d.toLocaleString());
	i=ps.executeUpdate();
	
}
return mapping.findForward("suc");
}//if
else
{	
ps=con.prepareStatement("insert into mess_notice values(msgid.nextval,?,?,?,?,?)");
System.out.println("In else1");
ps.setString(1,to);
ps.setString(2,from);
ps.setString(3,sub);
ps.setString(4,desc);
ps.setString(5,d.toLocaleString());
i=ps.executeUpdate();
return mapping.findForward("suc");	
}	
}

}