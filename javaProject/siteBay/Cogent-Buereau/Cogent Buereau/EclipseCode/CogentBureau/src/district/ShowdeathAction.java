// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:13:11 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ShowdeathAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.district.DeathCer;

public class ShowdeathAction extends Action
{

    public ShowdeathAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        DeathCer bc = new DeathCer();
        String dcid = request.getParameter("dcid");
        HttpSession session = request.getSession(true);
        session.setAttribute("cid", dcid);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select *from  deathcertificate where dcid=?");
        stmt.setString(1, dcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        bc.setDcid(rs.getString("dcid"));
        bc.setFname(rs.getString("firstname"));
        bc.setLname(rs.getString("lastname"));
        bc.setFather(rs.getString("fname"));
        bc.setMname(rs.getString("mname"));
        bc.setRadd(rs.getString("resaddress"));
        bc.setPadd(rs.getString("peraddress"));
        Date d1 = rs.getDate("dob");
        String date = d1.toString();
        bc.setDob(date);
        Date d2 = rs.getDate("dod");
        String date1 = d2.toString();
        bc.setDod(date1);
        bc.setSex(rs.getString("sex"));
        bc.setCast(rs.getString("cast"));
        bc.setDplace(rs.getString("deathplace"));
        bc.setDrname(rs.getString("drname"));
        bc.setReason(rs.getString("reason"));
        bc.setOccup(rs.getString("occupation"));
        Date d3 = rs.getDate("doapp");
        String date2 = d3.toString();
        bc.setDor(date2);
        bc.setFlag(rs.getInt("flag"));
        bc.setHospital(rs.getString("hospital"));
        request.setAttribute("deathcer", bc);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}