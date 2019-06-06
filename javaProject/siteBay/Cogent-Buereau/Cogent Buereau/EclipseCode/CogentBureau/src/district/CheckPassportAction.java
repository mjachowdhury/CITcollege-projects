package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class CheckPassportAction extends Action
{

    public CheckPassportAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        int f;
        String bcid = request.getParameter("bcid");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("select  flag from passportcertificate where bcid=?");
        stmt.setString(1, bcid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        f = rs.getInt("flag");
        con.close();
        if(f == 1)
            return mapping.findForward("success");
        if(f == 0)
            return mapping.findForward("success1");
        return mapping.findForward("fail");
       
    }
}