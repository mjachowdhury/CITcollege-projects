// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 2/1/2004 3:53:02 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PoliceLoginAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class PoliceLoginAction extends Action
{

    public PoliceLoginAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
		try
		{
		System.out.println("kk");
        String vuname;
        String vpwd;
        vuname = request.getParameter("uname");
        vpwd = request.getParameter("pwd");
        System.out.println((new StringBuilder("vuname=")).append(vuname).toString());
        System.out.println((new StringBuilder("vpwd=")).append(vpwd).toString());
      
		System.out.println("kk");int f;
        f = 0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
       
		System.out.println("kk");
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery((new StringBuilder("select * from policest where login='")).append(vuname).append("'").append(" and pwd='").append(vpwd).append("'").toString());
        String profileid = "0";
		System.out.println("kk");
        if(rs.next() && vuname.equals(rs.getString("login")) && vpwd.equals(rs.getString("pwd")))
        {
            f = 1;
            profileid = "2";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", rs.getString("pk"));
        session.setAttribute("profileid", profileid);
        System.out.println(vuname);
        if(f == 1)
            return mapping.findForward("adminlogin");
        return mapping.findForward("loginfail");
	}
		catch(Exception e)
		{
       
        System.out.println((new StringBuilder("Problem ---->")).append(e).toString());
        return mapping.findForward("loginfail");
    }
	}
}