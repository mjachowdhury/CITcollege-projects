// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:20:20 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StorePassportAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package district:
//            PassportFB

public class StorePassportAction extends Action
{

    public StorePassportAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        PassportFB fb = (PassportFB)form;
        String first = (String)fb.get("first");
        String last = (String)fb.get("last");
        String fname = (String)fb.get("fname");
        String mname = (String)fb.get("mname");
        String sname = (String)fb.get("sname");
        String id = (String)fb.get("id");
        String emailid = (String)fb.get("emailid");
        String qul = (String)fb.get("qul");
        String prof = (String)fb.get("prof");
        String telno = (String)fb.get("telno");
        String mobileno = (String)fb.get("mobileno");
        String height = (String)fb.get("height");
        String resadd = (String)fb.get("resadd");
        String peradd = (String)fb.get("peradd");
        String dob = (String)fb.get("dob");
        Date d1 = Date.valueOf(dob);
        String sex = (String)fb.get("sex");
        String bplace = (String)fb.get("bplace");
        int range = 12345;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String birth = (new StringBuilder("DD")).append(n).toString();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("insert into passportcertificate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, birth);
        stmt.setString(2, first);
        stmt.setString(3, last);
        stmt.setDate(4, d1);
        stmt.setString(5, sex);
        stmt.setString(6, sname);
        stmt.setString(7, fname);
        stmt.setString(8, mname);
        stmt.setString(9, resadd);
        stmt.setString(10, peradd);
        stmt.setString(11, bplace);
        stmt.setString(12, telno);
        stmt.setString(13, mobileno);
        stmt.setString(14, emailid);
        stmt.setString(15, qul);
        stmt.setString(16, prof);
        stmt.setString(17, id);
        stmt.setString(18, height);
        stmt.setInt(19, 0);
        stmt.setInt(20, 0);
        stmt.setString(21, "");
        stmt.executeUpdate();
        request.setAttribute("cid", birth);
        con.close();
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}