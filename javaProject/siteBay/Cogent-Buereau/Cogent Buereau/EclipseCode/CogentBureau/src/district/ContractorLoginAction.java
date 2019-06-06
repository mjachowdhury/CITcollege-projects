package district;

import java.io.PrintStream;
import java.sql.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class ContractorLoginAction extends Action
{

    public ContractorLoginAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String vuname;
        String vpwd;
        vuname = request.getParameter("uname");
        vpwd = request.getParameter("pwd");
        System.out.println((new StringBuilder("vuname=")).append(vuname).toString());
        System.out.println((new StringBuilder("vpwd=")).append(vpwd).toString());
        int f;
        f = 0;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery((new StringBuilder("select * from contractor where pk='")).append(vuname).append("'").append(" and pwd='").append(vpwd).append("'").toString());
        String profileid = "0";
        if(rs.next() && vuname.equals(rs.getString("pk")) && vpwd.equals(rs.getString("pwd")))
        {
            f = 1;
            profileid = "2";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("username", rs.getString("pk"));
        session.setAttribute("profileid", profileid);
        System.out.println(vuname);
        con.close();
        if(f == 1)
            return mapping.findForward("adminlogin");
        return mapping.findForward("loginfail");
        
    }
}