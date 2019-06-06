package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

// Referenced classes of package joseph:
//            Database

public class newEmploy extends HttpServlet
{

    public newEmploy()
    {
    }

    public void init(ServletConfig servletconfig)
        throws ServletException
    {
        super.init(servletconfig);
    }

    public void destroy()
    {
    }

    protected void processRequest(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        Date date = new Date(System.currentTimeMillis());
        System.out.println((new StringBuilder()).append("sysDate").append(date).toString());
        String s = httpservletrequest.getParameter("empid");
        String s1 = httpservletrequest.getParameter("empname");
        String s2 = httpservletrequest.getParameter("password");
        String s3 = httpservletrequest.getParameter("designation");
        String s4 = httpservletrequest.getParameter("passno");
        Database database = null;
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        try
        {
            database = new Database();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Cannot Create Connection : newEmployee.class").append(exception).toString());
        }
        try
        {
            String s5 = "select max(ExeID) from Executive";
            ResultSet resultset = database.getResultSet(s5);
            String s7;
            if(!resultset.next())
            {
                s7 = "HrEx4331";
            } else
            {
                String s8 = resultset.getString(1);
                if(s8 == null)
                {
                    s8 = "HrEx4331";
                    System.out.println(s8);
                } else
                {
                    s8 = (new StringBuilder()).append("HrEx").append(Integer.parseInt(s8.substring(4, s8.length())) + 1).toString();
                }
            }
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Generating HrExecutiveID").append(exception1).toString());
        }
        try
        {
            String s6 = (new StringBuilder()).append("insert into empdetails values('").append(s).append("', '").append(s1).append("','").append(s2).append("','").append(s3).append("','").append(s4).append("')").toString();
            int i = database.setResultSet(s6);
            s6 = (new StringBuilder()).append("insert into LoginDetails values('").append(s).append("','").append(s2).append("','Employee')").toString();
            i = database.setResultSet(s6);
            database.closeResultSet();
        }
        catch(Exception exception2)
        {
            System.out.println((new StringBuilder()).append("Unable to insert into table Executive").append(exception2).toString());
        }
        httpservletresponse.sendRedirect("Admin/AddEmploy.jsp");
    }

    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        processRequest(httpservletrequest, httpservletresponse);
    }

    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        processRequest(httpservletrequest, httpservletresponse);
    }

    public String getServletInfo()
    {
        return "Short description";
    }
}