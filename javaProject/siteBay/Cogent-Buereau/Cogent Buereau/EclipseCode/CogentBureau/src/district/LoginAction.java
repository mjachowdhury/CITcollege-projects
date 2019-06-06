// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:05:43 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   LoginAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

// Referenced classes of package district:
//            LoginFB

public class LoginAction extends Action
{

    public LoginAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String vuname;
        String vpwd;
        LoginFB fb = (LoginFB)form;
        vuname = (String)fb.get("uname");
        vpwd = (String)fb.get("pwd");
        int f;
        f = 0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery((new StringBuilder("select * from USERS_DETAILS where user_name='")).append(vuname).append("'").append(" and USER_PWD='").append(vpwd).append("'").toString());
        String profileid = "0";
        if(rs.next() && vuname.equals(rs.getString("user_name")) && vpwd.equals(rs.getString("user_pwd")) && rs.getString(3).equals("1"))
        {
            f = 1;
            profileid = "1";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", vuname);
        session.setAttribute("profileid", profileid);
        System.out.println(vuname);
        if(f == 1)
            return mapping.findForward("adminlogin");
        return mapping.findForward("loginfail");
        Exception e;
        e;
        System.out.println((new StringBuilder("Problem ---->")).append(e).toString());
        return mapping.findForward("loginfail");
    }
}