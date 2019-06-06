// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:21:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TenderAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package district:
//            TenderFB

public class TenderAction extends Action
{

    public TenderAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Connection con;
        TenderFB fb;
        String method;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        Statement stmt = null;
        fb = (TenderFB)form;
        method = (String)fb.get("method");
        String sqlSTMT = null;
        if(!method.equals("create"))
            break MISSING_BLOCK_LABEL_277;
        String tname = (String)fb.get("tname");
        String wplace = (String)fb.get("wplace");
        String tdesc = (String)fb.get("tdesc");
        String sflag = (String)fb.get("flag");
        int flag = 0;
        if(sflag.equals("open"))
            flag = 0;
        if(sflag.equals("cancel"))
            flag = 2;
        int range = 12345;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String pk = (new StringBuilder("TID")).append(n).toString();
        sqlSTMT = (new StringBuilder("insert into tender(tid,tname,workplace,tdesc,flag) values ('")).append(pk).append("','").append(tname).append("','").append(wplace).append("','").append(tdesc).append("','").append(flag).append("')").toString();
        stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        if(!method.equals("delete"))
            break MISSING_BLOCK_LABEL_366;
        String pk = (String)fb.get("tid");
        String sqlSTMT = (new StringBuilder("delete from tender where tid='")).append(pk).append("'").toString();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        if(method.equals("search"))
            return mapping.findForward("success");
        if(!method.equals("edit"))
            break MISSING_BLOCK_LABEL_597;
        String pk = (String)fb.get("tid");
        String tname = (String)fb.get("tname");
        String wplace = (String)fb.get("wplace");
        String tdesc = (String)fb.get("tdesc");
        String sflag = (String)fb.get("flag");
        int flag = 0;
        if(sflag.equals("open"))
            flag = 0;
        if(sflag.equals("cancel"))
            flag = 2;
        String sqlSTMT = (new StringBuilder("update tender set tname='")).append(tname).append("',workplace='").append(wplace).append("',tdesc='").append(tdesc).append("',flag='").append(flag).append("'where tid='").append(pk).append("'").toString();
        System.out.println(sqlSTMT);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        con.close();
        return mapping.findForward("fail");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}