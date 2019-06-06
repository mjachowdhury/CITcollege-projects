// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:20:34 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StoreresdAction.java

package district;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// Referenced classes of package district:
//            ResdFB

public class StoreresdAction extends Action
{

    public StoreresdAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ResdFB fb = (ResdFB)form;
        String first = (String)fb.get("first");
        String last = (String)fb.get("last");
        String fname = (String)fb.get("fname");
        String dob = (String)fb.get("dob");
        Date d1 = Date.valueOf(dob);
        String resadd = (String)fb.get("resadd");
        String peradd = (String)fb.get("peradd");
        String caste = (String)fb.get("caste");
        String lsince = (String)fb.get("lsince");
        int ls = Integer.parseInt(lsince);
        String occup = (String)fb.get("occup");
        String purpose = (String)fb.get("purpose");
        String reason = (String)fb.get("reason");
        java.util.Date sdate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(sdate);
        Date d2 = Date.valueOf(str);
        int range = 45678;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String resd = (new StringBuilder("RC")).append(n).toString();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("insert into residentialcertificate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, resd);
        stmt.setString(2, first);
        stmt.setString(3, last);
        stmt.setString(4, fname);
        stmt.setDate(5, d1);
        stmt.setString(6, caste);
        stmt.setString(7, occup);
        stmt.setInt(8, ls);
        stmt.setString(9, resadd);
        stmt.setString(10, peradd);
        stmt.setString(11, reason);
        stmt.setString(12, purpose);
        stmt.setDate(13, d2);
        stmt.setInt(14, 0);
        stmt.executeUpdate();
        request.setAttribute("cid", resd);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}