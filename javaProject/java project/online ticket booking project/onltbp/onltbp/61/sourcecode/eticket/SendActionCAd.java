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
 * Creation date: 03-05-2007
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="suc" path="mailCad.jsp"
 */
public class SendActionCAd extends Action {

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
		HttpSession hs=request.getSession();
		DataSource ds=getDataSource(request);
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select email from admindetails");
		Statement st1=con.createStatement();
		ResultSet rs1=null;
		Vector v=new Vector();
		while (rs.next()){
		v.add(rs.getString(1));
		System.out.println(rs.getString(1));
		}
		
			System.out.println(v.size());
		hs.setAttribute("mailcad",v);

		return mapping.findForward("suc");
			}
	

		}

