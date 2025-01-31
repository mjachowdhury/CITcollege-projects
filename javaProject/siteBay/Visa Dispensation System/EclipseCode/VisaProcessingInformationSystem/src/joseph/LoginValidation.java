package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class LoginValidation extends HttpServlet
{

    public LoginValidation()
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
        String s = httpservletrequest.getParameter("uname");
        String s1 = httpservletrequest.getParameter("pass");
        Database database = null;
        ResultSet resultset = null;
        Object obj = null;
        try
        {
            database = new Database();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Cannot Create Connection : ").append(exception).toString());
        }
        try
        {
            String s2 = (new StringBuilder()).append("select Category from LoginDetails where UserName='").append(s).append("' and Password='").append(s1).append("'").toString();
            resultset = database.getResultSet(s2);
        }
        catch(Exception exception1)
        {
            System.out.println("Cannot get ResultSet:");
        }
        try
        {
            if(resultset.next())
            {
                String s3 = resultset.getString("Category");
                if(s3.equals("Administrator"))
                    httpservletresponse.sendRedirect("Admin/home.jsp");
                else
                if(s3.equals("HrManager"))
                {
                    HttpSession httpsession = httpservletrequest.getSession(true);
                    httpsession.setAttribute("Ses_ID", s);
                    httpservletresponse.sendRedirect("HrManager/home.jsp");
                } else
                if(s3.equals("Employee"))
                {
                    HttpSession httpsession1 = httpservletrequest.getSession(true);
                    httpsession1.setAttribute("Ses_ID", s);
                    httpservletresponse.sendRedirect("Employee/home.jsp");
                } else
                {
                    httpservletresponse.sendRedirect("eIndex.jsp");
                }
            } else
            {
                httpservletresponse.sendRedirect("eIndex.jsp");
            }
        }
        catch(Exception exception2)
        {
            System.out.println("Cannot check Login : ");
        }
        try
        {
            database.closeResultSet();
        }
        catch(Exception exception3)
        {
            System.out.println("Cannot Close Database : joseph");
        }
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