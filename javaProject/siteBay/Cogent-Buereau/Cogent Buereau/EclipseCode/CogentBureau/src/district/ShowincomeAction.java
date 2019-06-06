// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/1/2009 6:14:28 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ShowincomeAction.java

package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.district.IncomeCer;

public class ShowincomeAction extends Action
{

    public ShowincomeAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        IncomeCer bc = new IncomeCer();
        String icid = request.getParameter("icid");
        HttpSession session = request.getSession(true);
        session.setAttribute("cid", icid);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select *from  incomcertificate where iid=?");
        stmt.setString(1, icid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        bc.setIcid(rs.getString("iid"));
        bc.setFname(rs.getString("firstname"));
        bc.setLname(rs.getString("lastname"));
        bc.setFather(rs.getString("fname"));
        bc.setRadd(rs.getString("resaddress"));
        bc.setPadd(rs.getString("peraddress"));
        Date d1 = rs.getDate("dob");
        String date1 = d1.toString();
        bc.setDob(date1);
        bc.setQual(rs.getString("qualf"));
        bc.setIncome(rs.getDouble("income"));
        bc.setPurpose(rs.getString("purpose"));
        bc.setOccup(rs.getString("occupation"));
        Date d2 = rs.getDate("doapp");
        String date2 = d2.toString();
        bc.setDor(date2);
        bc.setFlag(rs.getInt("flag"));
        request.setAttribute("incomecer", bc);
        return mapping.findForward("success");
        Exception e;
        e;
        System.out.println((new StringBuilder("Exception")).append(e).toString());
        return mapping.findForward("fail");
    }
}