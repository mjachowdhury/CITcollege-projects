package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class CheckdeathAction extends Action
{

    public CheckdeathAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int f;
        String dcid = request.getParameter("dcid");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select  flag from deathcertificate where dcid=?");
        stmt.setString(1, dcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        f = rs.getInt("flag");
        if(f == 1)
            return mapping.findForward("success");
        if(f == 0)
            return mapping.findForward("success1");
        return mapping.findForward("fail");
       
    }
}