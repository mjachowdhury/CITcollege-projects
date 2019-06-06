// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:11:31 PM
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

    public ActionForward execute(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        boolean flag;
        System.out.println("kk");
        String s = httpservletrequest.getParameter("uname");
        String s1 = httpservletrequest.getParameter("pwd");
        System.out.println((new StringBuilder("vuname=")).append(s).toString());
        System.out.println((new StringBuilder("vpwd=")).append(s1).toString());
        System.out.println("kk");
        flag = false;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        System.out.println("kk");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery((new StringBuilder("select * from policest where login='")).append(s).append("'").append(" and pwd='").append(s1).append("'").toString());
        String s2 = "0";
        System.out.println("kk");
        if(resultset.next() && s.equals(resultset.getString("login")) && s1.equals(resultset.getString("pwd")))
        {
            flag = true;
            s2 = "2";
        }
        HttpSession httpsession = httpservletrequest.getSession(true);
        httpsession.setAttribute("username", resultset.getString("pk"));
        httpsession.setAttribute("profileid", s2);
        System.out.println(s);
        if(flag)
            return actionmapping.findForward("adminlogin");
        return actionmapping.findForward("loginfail");
        Exception exception;
        exception;
        System.out.println((new StringBuilder("Problem ---->")).append(exception).toString());
        return actionmapping.findForward("loginfail");
    }
}