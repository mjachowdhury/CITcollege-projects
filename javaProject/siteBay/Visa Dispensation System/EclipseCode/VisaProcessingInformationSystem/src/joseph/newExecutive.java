package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class newExecutive extends HttpServlet
{

    public newExecutive()
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
        String s = httpservletrequest.getParameter("name");
        String s1 = httpservletrequest.getParameter("d");
        String s2 = httpservletrequest.getParameter("m");
        String s3 = httpservletrequest.getParameter("y");
        String s4 = (new StringBuilder()).append(s3).append("-").append(s2).append("-").append(s1).toString();
        String s5 = httpservletrequest.getParameter("email");
        String s6 = httpservletrequest.getParameter("phno");
        String s7 = httpservletrequest.getParameter("add");
        Database database = null;
        Object obj = null;
        Object obj1 = null;
        String s10 = null;
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
            String s8 = "select max(ExeID) from Executive";
            ResultSet resultset = database.getResultSet(s8);
            if(!resultset.next())
            {
                s10 = "HrEx4331";
            } else
            {
                s10 = resultset.getString(1);
                if(s10 == null)
                {
                    s10 = "HrEx4331";
                    System.out.println(s10);
                } else
                {
                    s10 = (new StringBuilder()).append("HrEx").append(Integer.parseInt(s10.substring(4, s10.length())) + 1).toString();
                }
            }
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Generating HrExecutiveID").append(exception1).toString());
        }
        try
        {
            String s9 = (new StringBuilder()).append("insert into Executive values('").append(s10).append("', '").append(s).append("','").append(s4).append("','").append(s5).append("','").append(s6).append("','").append(s7).append("','").append(date).append("')").toString();
            int i = database.setResultSet(s9);
            s9 = (new StringBuilder()).append("insert into LoginDetails values('").append(s10).append("','").append(s10).append("','HrManager')").toString();
            i = database.setResultSet(s9);
            database.closeResultSet();
        }
        catch(Exception exception2)
        {
            System.out.println((new StringBuilder()).append("Unable to insert into table Executive").append(exception2).toString());
        }
        httpservletresponse.sendRedirect((new StringBuilder()).append("Admin/AddNewExecutive.jsp?ExeID=").append(s10).toString());
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