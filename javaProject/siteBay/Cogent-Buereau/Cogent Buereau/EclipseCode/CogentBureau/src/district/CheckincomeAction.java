// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 5:56:08 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CheckincomeAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class CheckincomeAction extends Action
{

    public CheckincomeAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int f;
        String cid = request.getParameter("iid");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select  flag from incomcertificate where iid=?");
        stmt.setString(1, cid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        f = rs.getInt("flag");
        con.close();
        if(f == 1)
            return mapping.findForward("success");
        if(f == 0)
            return mapping.findForward("success1");
        return mapping.findForward("fail");
        
    }
}