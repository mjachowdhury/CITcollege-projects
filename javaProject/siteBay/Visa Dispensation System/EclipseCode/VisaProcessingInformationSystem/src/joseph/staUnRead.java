package joseph;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

// Referenced classes of package joseph:
//            Database

public class staUnRead extends HttpServlet
{

    public staUnRead()
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
        String s = httpservletrequest.getParameter("RenID");
        Object obj = null;
        Object obj1 = null;
        try
        {
            String s1 = (new StringBuilder()).append("Update Renewal set Status='Read' where RenID='").append(s).append("'").toString();
            Database database = new Database();
            int i = database.setResultSet(s1);
            database.closeSetResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Updating Status in Renewal : ").append(exception).toString());
        }
        httpservletresponse.sendRedirect("Admin/VisaRenewal.jsp");
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