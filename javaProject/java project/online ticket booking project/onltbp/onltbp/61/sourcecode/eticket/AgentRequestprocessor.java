package eticket;

import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.http.*;
import org.apache.struts.action.RequestProcessor;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentRequestprocessor extends RequestProcessor {

	public boolean processPreprocess(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("this is agent req. processor");
		System.out.println("In extended Requestprocessor");
		HttpSession hs=request.getSession(false);
		if(hs.getAttribute("agent")==null)
		{
			System.out.println("in if 1 agent=null");
			String s2=request.getServletPath();
			System.out.print(s2);
			StringTokenizer st=new StringTokenizer(s2,"/");
			int i=st.countTokens();
			for(int j=1;j<i;j++)
				System.out.println(st.nextToken());
			if("Agentlogin.do".equals(st.nextToken()))
			    return true;
			else
			{
			 try 
			  {
				PrintWriter out=response.getWriter();
				out.println("<CENTER><FONT COLOR=\"#00028\" FACE=\"VERDANA\" SIZE=\"2\">");
				out.println("Sorry..");
				out.println("Your Session Has Expired or You are not a Valid User.");
				out.println("</CENTER></FONT>");
			  }catch(Exception e){}
		      return false;
			}
		}
		else 
			return true;
	}
}