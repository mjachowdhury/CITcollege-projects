//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.sql.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;/** 
 * MyEclipse Struts
 * Creation date: 04-06-2007
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="suc" path="/viewbusdes.jsp"
 */
public class ViewBusDesAction extends Action {

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
String src=request.getParameter("name");
String des=request.getParameter("name1");
//System.out.println(src);
//System.out.println(des);
DataSource ds=getDataSource(request);
ArrayList al=Validation.getDes(src,des,ds);
//System.out.println(al.size());
HttpSession hs=request.getSession();
hs.setAttribute("des",al);
return mapping.findForward("suc");

		
	}

}