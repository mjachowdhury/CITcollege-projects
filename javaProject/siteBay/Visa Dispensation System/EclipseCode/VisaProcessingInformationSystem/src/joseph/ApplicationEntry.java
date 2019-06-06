package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class ApplicationEntry extends HttpServlet
{

    public ApplicationEntry()
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
        String s = httpservletrequest.getParameter("EmpID");
        System.out.println((new StringBuilder()).append("EmpID").append(s).toString());
        String s1 = httpservletrequest.getParameter("r1");
        String s2 = httpservletrequest.getParameter("time");
        String s3 = httpservletrequest.getParameter("count");
        String s4 = httpservletrequest.getParameter("type");
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s5 = httpsession.getAttribute("Ses_ID").toString();
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        String s8 = null;
        try
        {
            Database database = new Database();
            String s6 = "select max(ApplnID) from ApplicationEntry";
            ResultSet resultset = database.getResultSet(s6);
            if(!resultset.next())
            {
                s8 = "Apln331";
            } else
            {
                s8 = resultset.getString(1);
                if(s8 == null)
                    s8 = "Apln331";
                else
                    s8 = (new StringBuilder()).append("Apln").append(Integer.parseInt(s8.substring(4, s8.length())) + 1).toString();
            }
            database.closeResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Generating Auto ID HrExecutiveID").append(exception).toString());
        }
        try
        {
            Database database1 = new Database();
            String s7 = (new StringBuilder()).append("insert into ApplicationEntry values ('").append(s8).append("','").append(s).append("','").append(s5).append("',").append(s2).append(",'").append(s3).append("','").append(s4).append("','No','No','No','No','No','No','").append(date).append("')").toString();
            System.out.println((new StringBuilder()).append("query").append(s7).toString());
            int i = database1.setResultSet(s7);
            database1.closeSetResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While inserting HrExecutiveID").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("HrManager/ApplicationEntry.jsp");
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