package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class CheckdomAction extends Action
{

    public CheckdomAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int f;
        String cid = request.getParameter("did");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select  flag from domcilecertificate where did=?");
        stmt.setString(1, cid);
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