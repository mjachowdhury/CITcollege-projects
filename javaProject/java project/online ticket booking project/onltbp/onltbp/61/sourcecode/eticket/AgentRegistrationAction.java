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
 * Creation date: 11-01-2006
 * 
 * XDoclet definition:
 * @struts:action path="/AgentReg" name="Agentreg" input="/AgentRegistration.jsp" scope="request" validate="true"
 * @struts:action-forward name="success" path="/AgentIndex.jsp"
 * @struts:action-forward name="fail" path="/AgentRegistration.jsp"
 */
public class AgentRegistrationAction extends Action {

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
		if(isCancelled(request))
			return mapping.findForward("fail");
		AgentRegistrationForm agentReg = (AgentRegistrationForm) form;
		AgentBean agent=new AgentBean();
		
		agent.setLogin(agentReg.getLogin());
		agent.setPass(agentReg.getPass());
		agent.setRtpass(agentReg.getRtpass());
		agent.setSecque(agentReg.getSecque());
		agent.setSecans(agentReg.getSecans());
		agent.setSlt(agentReg.getSlt());
		agent.setFname(agentReg.getFname());
		agent.setLname(agentReg.getLname());
		agent.setDrmonth(agentReg.getDrmonth());
		agent.setDrday(agentReg.getDrday());
		agent.setDryear(agentReg.getDryear());
		agent.setGender(agentReg.getGender());
		agent.setOcc(agentReg.getOcc());
		agent.setInd(agentReg.getInd());
		agent.setAin(agentReg.getAin());
		agent.setAddr(agentReg.getAddr());
		agent.setCity(agentReg.getCity());
		agent.setState(agentReg.getState());
		agent.setCountry(agentReg.getCountry());
		agent.setZip(agentReg.getZip());
		agent.setPhno(agentReg.getPhno());
		agent.setEmail(agentReg.getEmail());
		agent.setRegno(agentReg.getRegno());
		System.out.println("after filling in agent bean from arform"+agentReg.getDor());
	    agent.setDor(agentReg.getDor());
	    boolean valid=Validation.addAgent(agent,getDataSource(request));
	    System.out.println(valid);
	    if(valid)
	    	return mapping.findForward("success");
	    return mapping.findForward("fail");
		
		
	}

}