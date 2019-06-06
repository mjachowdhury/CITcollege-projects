package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class DecideBirthAction extends Action
{

    public DecideBirthAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String bcid = request.getParameter("bcid");
        String pk = request.getParameter("pk");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        PreparedStatement stmt = con.prepareStatement("update birthcertificate set flag=? where bcid=?");
        if(request.getParameter("submit").equals("approved"))
            stmt.setString(1, "1");
        else
        if(request.getParameter("submit").equals("deny"))
            stmt.setString(1, "2");
        stmt.setString(2, bcid);
        stmt.executeUpdate();
        request.setAttribute("bcid", bcid);
        request.setAttribute("decided", request.getParameter("submit"));
        con.close();
        return mapping.findForward("success");
        
    }
}