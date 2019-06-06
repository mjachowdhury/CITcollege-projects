// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:21:46 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ViewbirthAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.district.BirthCer;

public class ViewbirthAction extends Action
{

    public ViewbirthAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        BirthCer bc = new BirthCer();
        String bcid = request.getParameter("bcid");
        HttpSession session = request.getSession(true);
        session.setAttribute("cid", bcid);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select *from  birthcertificate where bcid=?");
        stmt.setString(1, bcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        bc.setBcid(rs.getString("bcid"));
        bc.setFname(rs.getString("firstname"));
        bc.setLname(rs.getString("lastname"));
        bc.setFather(rs.getString("fname"));
        bc.setMname(rs.getString("mname"));
        bc.setRadd(rs.getString("resaddress"));
        bc.setPadd(rs.getString("peraddress"));
        Date d1 = rs.getDate("dob");
        String date = d1.toString();
        bc.setDob(date);
        bc.setSex(rs.getString("sex"));
        bc.setCast(rs.getString("cast"));
        bc.setBplace(rs.getString("birthplace"));
        bc.setDrname(rs.getString("drname"));
        bc.setFoccup(rs.getString("foccup"));
        Date d2 = rs.getDate("dor");
        String date1 = d2.toString();
        bc.setDor(date1);
        bc.setFlag(rs.getInt("flag"));
        bc.setHospital(rs.getString("hospital"));
        request.setAttribute("birthcer", bc);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}