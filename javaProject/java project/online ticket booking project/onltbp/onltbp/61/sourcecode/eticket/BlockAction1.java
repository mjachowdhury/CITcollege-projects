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
import java.util.*;
import eticket.BookForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts:action path="/bookAg" name="bform" scope="request" validate="true"
 * @struts:action-forward name="suc" path="bookticket.jsp"
 */
public class BlockAction1 extends Action {

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
		BookForm bform = (BookForm) form;
		ArrayList al=Validation.getblockdet(bform,getDataSource(request));
		
		HttpSession hs=request.getSession();
		System.out.println("size befor session="+al.size());
		//System.out.println (bform.getUsername1());
		hs.setAttribute("block",al);
		//hs.setAttribute("",bform.getUsername());
		return mapping.findForward("suc");
		
		
		}
		
		
	}

