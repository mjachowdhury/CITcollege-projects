//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import java.util.ArrayList;
import javax.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 11-27-2006
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="success" path="/.jsp"
 * @struts:action-forward name="fail" path="/.jsp"
 */
public class ViewCusAction extends Action {

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
		HttpServletResponse response){
		 System.out.println(1);
			
		 UserBean ub=new UserBean();
		 DataSource ds=getDataSource(request);
		 try{
		 	
		 ArrayList al=Validation.getCustomers(ds);
		 System.out.println(al.size());
		 HttpSession hs=request.getSession();
 hs.setAttribute("al",al);
		 return mapping.findForward("suc");
		 }
		 catch(Exception e){
		 	return mapping.findForward("fail");
			 
		 }
		  
		 
		 
		 
	}	 

}