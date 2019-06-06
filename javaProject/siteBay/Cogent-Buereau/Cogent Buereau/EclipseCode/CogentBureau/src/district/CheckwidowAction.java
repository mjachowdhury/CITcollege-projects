package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class CheckwidowAction extends Action
{

    public CheckwidowAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int f;
        String wcid = request.getParameter("wid");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select  flag from widowcertificate where wid=?");
        stmt.setString(1, wcid);
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