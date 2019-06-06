// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:19:36 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StoredomcileAction.java

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
//            DomicileFB

public class StoredomcileAction extends Action
{

    public StoredomcileAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        DomicileFB fb = (DomicileFB)form;
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
        java.util.Date sdate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(sdate);
        Date d2 = Date.valueOf(str);
        int range = 45678;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String domc = (new StringBuilder("DC")).append(n).toString();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("insert into domcilecertificate values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, domc);
        stmt.setString(2, first);
        stmt.setString(3, last);
        stmt.setString(4, fname);
        stmt.setDate(5, d1);
        stmt.setString(6, occup);
        stmt.setInt(7, ls);
        stmt.setString(8, resadd);
        stmt.setString(9, peradd);
        stmt.setString(10, purpose);
        stmt.setDate(11, d2);
        stmt.setInt(12, 0);
        stmt.setString(13, caste);
        stmt.executeUpdate();
        request.setAttribute("cid", domc);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}