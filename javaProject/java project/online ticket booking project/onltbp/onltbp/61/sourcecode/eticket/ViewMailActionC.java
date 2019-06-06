//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-06-2007
 * 
 * XDoclet definition:
 * @struts:action path="ViewMailC" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/viewCm.jsp"
 */
public class ViewMailActionC extends Action {

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
		HttpSession hs=request.getSession();
	String name=(String)hs.getAttribute("fromc");
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
	hs.setAttribute("vmailc",v);
	return mapping.findForward("suc");
	}

	}

