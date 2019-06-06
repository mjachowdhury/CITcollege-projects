package joseph;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class delEmploy extends HttpServlet
{

    public delEmploy()
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
        String s = httpservletrequest.getParameter("ExeID");
        Database database = null;
        Object obj = null;
        Object obj1 = null;
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
            String s1 = (new StringBuilder()).append("delete from LoginDetails where UserName='").append(s).append("'").toString();
            System.out.println((new StringBuilder()).append("Qry").append(s1).toString());
            int i = database.setResultSet(s1);
            String s2 = (new StringBuilder()).append("delete from empdetails where empid='").append(s).append("'").toString();
            System.out.println((new StringBuilder()).append("Qry").append(s2).toString());
            int j = database.setResultSet(s2);
            database.closeResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Generating HrExecutiveID").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("Admin/DeleteEmploy.jsp");
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