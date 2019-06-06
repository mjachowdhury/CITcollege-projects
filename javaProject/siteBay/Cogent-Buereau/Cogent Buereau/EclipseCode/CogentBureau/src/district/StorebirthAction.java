// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:17:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StorebirthAction.java

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
//            BirthFB

public class StorebirthAction extends Action
{

    public StorebirthAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        BirthFB fb = (BirthFB)form;
        String first = (String)fb.get("first");
        String last = (String)fb.get("last");
        String fname = (String)fb.get("fname");
        String mname = (String)fb.get("mname");
        String resadd = (String)fb.get("resadd");
        String peradd = (String)fb.get("peradd");
        String dob = (String)fb.get("dob");
        Date d1 = Date.valueOf(dob);
        String sex = (String)fb.get("sex");
        String caste = (String)fb.get("caste");
        String bplace = (String)fb.get("bplace");
        String drname = (String)fb.get("drname");
        String foccup = (String)fb.get("foccup");
        String hname = (String)fb.get("hname");
        java.util.Date sdate = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = df.format(sdate);
        Date d2 = Date.valueOf(str);
        int range = 12345;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String birth = (new StringBuilder("DD")).append(n).toString();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("insert into birthcertificate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, birth);
        stmt.setString(2, first);
        stmt.setString(3, last);
        stmt.setString(4, fname);
        stmt.setString(5, mname);
        stmt.setString(6, resadd);
        stmt.setString(7, peradd);
        stmt.setDate(8, d1);
        stmt.setString(9, sex);
        stmt.setString(10, caste);
        stmt.setString(11, bplace);
        stmt.setString(12, drname);
        stmt.setString(13, foccup);
        stmt.setDate(14, d2);
        stmt.setInt(15, 0);
        stmt.setString(16, hname);
        stmt.executeUpdate();
        request.setAttribute("cid", birth);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}