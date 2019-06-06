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
 * Creation date: 11-07-2006
 * 
 * XDoclet definition:
 * @struts:action path="/see" name="UserRegBean" scope="request" validate="true"
 */
public class UserRegistrationAction extends Action {

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
		System.out.println("befo");
		if(isCancelled(request))
			return mapping.findForward("fail");
		UserRegistrationBean UserRegBean = (UserRegistrationBean) form;
		System.out.println("before Adduser");
		UserBean user=new UserBean();
		System.out.println("before Adduser");
		user.setLogin(UserRegBean.getLogin());
		user.setPass(UserRegBean.getPass());
		user.setRtpass(UserRegBean.getRtpass());
		user.setSecque(UserRegBean.getSecque());
		user.setSecans(UserRegBean.getSecans());
		user.setSlt(UserRegBean.getSlt());
		user.setFname(UserRegBean.getFname());
		user.setLname(UserRegBean.getLname());
		System.out.println("before Adduser");
		user.setBdmonth(UserRegBean.getBdmonth());
		user.setBdday(UserRegBean.getBdday());
		user.setBdyear(UserRegBean.getBdyear());
		user.setGender(UserRegBean.getGender());
		user.setEdu(UserRegBean.getEdu());
		user.setOcc(UserRegBean.getOcc());
		user.setInd(UserRegBean.getInd());
		user.setAin(UserRegBean.getAin());
		user.setAddr(UserRegBean.getAddr());
		user.setCity(UserRegBean.getCity());
		user.setState(UserRegBean.getState());
		System.out.println("before Adduser");
		user.setCountry(UserRegBean.getCountry());
		user.setZip(UserRegBean.getZip());
		user.setPhno(UserRegBean.getPhno());
		user.setEmail(UserRegBean.getEmail());
		user.setIdproof(UserRegBean.getIdproof());
		user.setIdno(UserRegBean.getIdno());
System.out.println("before Adduser");
		boolean valid=Validation.addUser(user,getDataSource(request));
		if(valid)
			return mapping.findForward("success");
		return mapping.findForward("fail");
				
	}

}