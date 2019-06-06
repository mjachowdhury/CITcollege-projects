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

import eticket.BookForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts:action path="/bookAg" name="bform" scope="request" validate="true"
 * @struts:action-forward name="suc" path="bookticket.jsp"
 */
public class BookAction extends Action {

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
		
		bform.setBusno(request.getParameter("busno"));
		//BookForm bform1 = new BookForm();
		boolean flag=Validation.booktemp(bform,getDataSource(request));
		/**bform.setCapacity(bform1.getCapacity());
		bform.setDay(bform1.getDay());
		bform.setMonth(bform1.getMonth());
		bform.setYear(bform1.getDay())zz**/
		
		//System.out.println("flag"+flag);
		HttpSession hs=request.getSession();
		//hs.setAttribute("tid",nbform.getTickid());
		System.out.println (bform.getUsername1());
		System.out.println ("username=========="+bform.getUsername1());
		
		hs.setAttribute("uname",bform.getUsername());
		//hs.setAttribute("",bform.getUsername());
		
		return mapping.findForward("suc");
		
		
		}
		
		
	}

