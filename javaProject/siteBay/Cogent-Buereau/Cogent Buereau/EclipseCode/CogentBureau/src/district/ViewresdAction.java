// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:23:46 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ViewresdAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.district.ResdCer;

public class ViewresdAction extends Action
{

    public ViewresdAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ResdCer bc = new ResdCer();
        String rcid = request.getParameter("rcid");
        HttpSession session = request.getSession(true);
        session.setAttribute("cid", rcid);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select *from  residentialcertificate where iid=?");
        stmt.setString(1, rcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        bc.setRcid(rs.getString("iid"));
        bc.setFname(rs.getString("firstname"));
        bc.setLname(rs.getString("lastname"));
        bc.setFather(rs.getString("fname"));
        bc.setRadd(rs.getString("resaddress"));
        bc.setPadd(rs.getString("peraddress"));
        Date d1 = rs.getDate("dob");
        String date1 = d1.toString();
        bc.setDob(date1);
        bc.setCast(rs.getString("caste"));
        bc.setLs(rs.getInt("livingsince"));
        bc.setPurpose(rs.getString("purpose"));
        bc.setReason(rs.getString("reason"));
        bc.setOccup(rs.getString("occupation"));
        Date d2 = rs.getDate("doapp");
        String date2 = d2.toString();
        bc.setDor(date2);
        bc.setFlag(rs.getInt("flag"));
        con.close();
        request.setAttribute("resdcer", bc);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}