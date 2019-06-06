// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:24:32 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ViewwidowAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.district.WidowCer;

public class ViewwidowAction extends Action
{

    public ViewwidowAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        WidowCer bc = new WidowCer();
        String wcid = request.getParameter("wcid");
        HttpSession session = request.getSession(true);
        session.setAttribute("cid", wcid);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select *from  widowcertificate where wid=?");
        stmt.setString(1, wcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        bc.setWcid(rs.getString("wid"));
        bc.setFname(rs.getString("firstname"));
        bc.setLname(rs.getString("lastname"));
        bc.setHname(rs.getString("husname"));
        bc.setRadd(rs.getString("resaddress"));
        bc.setPadd(rs.getString("peraddress"));
        bc.setDcid(rs.getString("deathcerid"));
        Date d1 = rs.getDate("dod");
        String date1 = d1.toString();
        bc.setDod(date1);
        bc.setDplace(rs.getString("deathplace"));
        bc.setDrname(rs.getString("drname"));
        bc.setReason(rs.getString("reason"));
        bc.setOccup(rs.getString("occupation"));
        Date d2 = rs.getDate("doapp");
        String date2 = d2.toString();
        bc.setDor(date2);
        bc.setFlag(rs.getInt("flag"));
        bc.setHospital(rs.getString("hosname"));
        request.setAttribute("widowcer", bc);
        con.close();
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}