//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-02-2007
 * 
 * XDoclet definition:
 * @struts:action validate="true"
 * @struts:action-forward name="suc" path="/srcdes.jsp"
 */
public class SrcDesAction extends Action {

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
		DataSource ds=getDataSource(request);
		SrcDesForm sdform=(SrcDesForm)form;
		SrcDesForm sdform1= new SrcDesForm();
		sdform1=Validation.getSrcDes(ds);
		sdform.setName("Yathindraa");
		//System.out.println(sdform.getName());
		sdform.setSrc(sdform1.getSrc());
		sdform.setDes(sdform1.getDes());
		//System.out.println(sdform.getName());
		//System.out.println(sdform.getSrc());
		//System.out.println(sdform.getDes());
		//sdform.setSrc(sdform.getDes());
		//sdform.setDes(sdform.getDes());
		//HttpSession hs=request.getSession();
		//hs.setAttribute("sd",sdform);
		//System.out.println(1);
		return mapping.findForward("suc");
		
	}

}