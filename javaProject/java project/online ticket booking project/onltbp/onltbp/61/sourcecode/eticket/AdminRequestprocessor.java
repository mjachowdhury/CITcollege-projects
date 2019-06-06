/*
 * Created on Dec 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;

import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.RequestProcessor;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminRequestprocessor extends RequestProcessor {

	public boolean processPreprocess(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("In extended Requestprocessor");
		HttpSession hs=request.getSession(false);
		if(hs.getAttribute("admin")==null)
		{
			System.out.println("in if 1 admin=null");
			String s2=request.getServletPath();
			System.out.print(s2);
			
			StringTokenizer st=new StringTokenizer(s2,"/");
			int i=st.countTokens();
			for(int j=1;j<i;j++)
				System.out.println(st.nextToken());
			if("Adminlogin.do".equals(st.nextToken()))
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
		
		
			
		
		/*if(request.isRequestedSessionIdValid())
		{
			HttpSession hs=request.getSession(false);
              String s=hs.getId();
              String s1=request.getRequestedSessionId();
              if(s.equals(s1))
		       	return true;
              return false;
		}
		else
		{
		String s2=request.getContextPath();
		StringTokenizer st=new StringTokenizer(s2,"/");
		int i=st.countTokens();
		for(int j=1;j<i;j++)
			System.out.println(st.nextToken());
		if("Adminlogin.do".equals(st.nextToken()))
		    return true;
		else
		{
			try 
			{
				PrintWriter out=response.getWriter();
				out.println("<center>");
				out.println("Sorry!!");
				out.println("Your Session Has Expired or You are not a Valid User.");
				out.println("</center>");
			}catch(Exception e){}
			
			return false;
		}
		
		}*/
	}
	
}
