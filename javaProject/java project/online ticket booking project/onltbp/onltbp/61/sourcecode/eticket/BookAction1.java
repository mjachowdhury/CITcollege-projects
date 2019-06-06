//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts:action path="/bookAg1" name="bform" scope="request" validate="true"
 * @struts:action-forward name="suc" path="bookticketconf.jsp"
 * @struts:action-forward name="fail" path="bookticketfail.jsp"
 */
public class BookAction1 extends Action {

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
	/**	/BookForm bform=new BookForm();
	System.out.println(bform.getBdday());
	System.out.println(bform.getBdmonth());
	System.out.println(bform.getBdyear());
	
	System.out.println("in Action1111111111111111111111111111111111111");
	System.out.println("yera"+bform.getBdday());
	System.out.println("yera"+bform.getUsername1());
	System.out.println("yera"+bform.getBdyear());
	System.out.println("tid="+bform.getTickid());
	System.out.println(request.getParameter("bdyear"));**/
		bform.setBusno(request.getParameter("busno"));
	bform.setTickid(new Integer(request.getParameter("tickid")).intValue());
	bform.setUsername1(request.getParameter("username1"));
	bform.setBdday(request.getParameter("bdday"));
	bform.setBdmonth(request.getParameter("bdmonth"));
	bform.setBdyear(request.getParameter("bdyear"));
	bform.setNot(request.getParameter("not"));
	
	boolean flag=Validation.bookTemp1(bform,getDataSource(request));
		System.out.println("tid="+bform.getTickid());
		System.out.println("flag"+flag);
		
		if(flag)
		return mapping.findForward("suc");
		return mapping.findForward("fail");
		
	
	}

}