package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class AssignPoliceAction extends Action
{

    public AssignPoliceAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String bcid = request.getParameter("bcid");
        String pk = request.getParameter("pk");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("update passportcertificate set pk=? where bcid=?");
        stmt.setString(1, pk);
        stmt.setString(2, bcid);
        stmt.executeUpdate();
        request.setAttribute("bcid", bcid);
        con.close();
        return mapping.findForward("success");        
        
    }
}