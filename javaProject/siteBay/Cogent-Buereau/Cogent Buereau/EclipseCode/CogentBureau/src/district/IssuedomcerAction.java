// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:04:07 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IssuedomcerAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class IssuedomcerAction extends Action
{

    public IssuedomcerAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String result;
        String cid;
        PreparedStatement stmt;
        result = request.getParameter("result");
        HttpSession session = request.getSession(true);
        cid = (String)session.getAttribute("cid");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        stmt = con.prepareStatement("update domcilecertificate set flag=? where did=?");
        if(!result.equals("approve"))
            break MISSING_BLOCK_LABEL_105;
        stmt.setInt(1, 1);
        stmt.setString(2, cid);
        stmt.executeUpdate();
        return mapping.findForward("success");
        if(!result.equals("decline"))
            break MISSING_BLOCK_LABEL_149;
        stmt.setInt(1, 2);
        stmt.setString(2, cid);
        stmt.executeUpdate();
        return mapping.findForward("success1");
        stmt.setInt(1, 0);
        stmt.setString(2, cid);
        stmt.executeUpdate();
        return mapping.findForward("success1");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}