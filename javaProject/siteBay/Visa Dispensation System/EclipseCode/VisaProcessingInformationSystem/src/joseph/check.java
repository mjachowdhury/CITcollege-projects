package joseph;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class check extends HttpServlet
{

    public check()
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
        httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        try
        {
            String s = "31";
            String s1 = "7";
            String s2 = "1982";
            String s3 = (new StringBuilder()).append(s2).append("-").append(s1).append("-").append(s).toString();
            printwriter.println(s3);
        }
        catch(Exception exception)
        {
            printwriter.println(exception);
        }
        printwriter.close();
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