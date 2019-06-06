package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class Renewal extends HttpServlet
{

    public Renewal()
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
        String s = httpservletrequest.getParameter("ApplnID");
        String s1 = httpservletrequest.getParameter("txtRenewal");
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s2 = httpsession.getAttribute("Ses_ID").toString();
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        String s5 = null;
        try
        {
            Database database = new Database();
            String s3 = "select max(RenID) from Renewal";
            ResultSet resultset = database.getResultSet(s3);
            if(!resultset.next())
            {
                s5 = "Ren31";
            } else
            {
                s5 = resultset.getString(1);
                if(s5 == null)
                {
                    s5 = "Ren31";
                    System.out.println(s5);
                } else
                {
                    s5 = (new StringBuilder()).append("Ren").append(Integer.parseInt(s5.substring(3, s5.length())) + 1).toString();
                }
            }
            database.closeResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Generating Renewal ID : ").append(exception).toString());
        }
        try
        {
            String s4 = (new StringBuilder()).append("insert into Renewal values('").append(s5).append("','").append(s).append("','").append(s1).append("','Unread','").append(date).append("')").toString();
            System.out.println((new StringBuilder()).append("Qry").append(s4).toString());
            Database database1 = new Database();
            int i = database1.setResultSet(s4);
            database1.closeSetResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Inserting Renewal : ").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("Employee/VisaRenewal.jsp");
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