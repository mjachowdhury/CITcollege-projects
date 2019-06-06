// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:10:57 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PoliceAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package district:
//            PoliceFB

public class PoliceAction extends Action
{

    public PoliceAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Connection con;
        PoliceFB fb;
        String method;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        Statement stmt = null;
        fb = (PoliceFB)form;
        method = (String)fb.get("method");
        String sqlSTMT = null;
        if(!method.equals("create"))
            break MISSING_BLOCK_LABEL_241;
        String pname = (String)fb.get("pname");
        String addr = (String)fb.get("addr");
        String login = (String)fb.get("login");
        String pwd = (String)fb.get("pwd");
        int range = 12345;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String pk = (new StringBuilder("PST")).append(n).toString();
        sqlSTMT = (new StringBuilder("insert into policest(pk,pname,addr,login,pwd) values ('")).append(pk).append("','").append(pname).append("','").append(addr).append("','").append(login).append("','").append(pwd).append("')").toString();
        stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        return mapping.findForward("success");
        if(!method.equals("delete"))
            break MISSING_BLOCK_LABEL_323;
        String pk = (String)fb.get("pk");
        String sqlSTMT = (new StringBuilder("delete from policest where pk='")).append(pk).append("'").toString();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        return mapping.findForward("success");
        if(method.equals("search"))
            return mapping.findForward("success");
        if(!method.equals("edit"))
            break MISSING_BLOCK_LABEL_518;
        String pk = (String)fb.get("pk");
        String pname = (String)fb.get("pname");
        String addr = (String)fb.get("addr");
        String login = (String)fb.get("login");
        String pwd = (String)fb.get("pwd");
        String sqlSTMT = (new StringBuilder("update policest set pname='")).append(pname).append("',addr='").append(addr).append("',login='").append(login).append("',pwd='").append(pwd).append("'where pk='").append(pk).append("'").toString();
        System.out.println(sqlSTMT);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        return mapping.findForward("success");
        return mapping.findForward("fail");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}