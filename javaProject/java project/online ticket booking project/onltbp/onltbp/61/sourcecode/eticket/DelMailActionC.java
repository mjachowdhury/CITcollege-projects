//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @struts:action path="delMailc" name="sf" scope="request" validate="true"
 * @struts:action-forward name="suc" path="/suc.jsp"
 */
public class DelMailActionC extends Action {

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
		DataSource ds=getDataSource(request);
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=null;
		System.out.println(1);
		String s[]=request.getParameterValues("ch");
		System.out.println(s.length);
		int i=0,j=0;
		while(i<s.length)
		{
		System.out.println(s[i]);
		 j=new Integer(s[i]).intValue();
		 System.out.println(j);
		 j=st.executeUpdate("delete from mess_notice where msgid="+j);
		 System.out.println("j==="+j);
			
		i++;
		 	
		}
		con.commit();
	System.out.println("In DELMAILACTION C");
		return mapping.findForward("suc");	
	}


}