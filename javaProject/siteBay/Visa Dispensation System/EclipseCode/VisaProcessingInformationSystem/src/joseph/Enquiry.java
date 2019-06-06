package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class Enquiry extends HttpServlet
{

    public Enquiry()
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
        String s1 = httpservletrequest.getParameter("Query");
        String s2 = httpservletrequest.getParameter("Report");
        String s3 = httpservletrequest.getParameter("Status");
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s4 = httpsession.getAttribute("Ses_ID").toString();
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        String s8 = null;
        try
        {
            Database database = new Database();
            String s5 = "select max(EnqID) from Enquiry";
            ResultSet resultset = database.getResultSet(s5);
            if(!resultset.next())
            {
                s8 = "Enq31";
            } else
            {
                s8 = resultset.getString(1);
                if(s8 == null)
                {
                    s8 = "Enq31";
                    System.out.println(s8);
                } else
                {
                    s8 = (new StringBuilder()).append("Enq").append(Integer.parseInt(s8.substring(3, s8.length())) + 1).toString();
                }
            }
            database.closeResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Generating Enquiry ID : ").append(exception).toString());
        }
        try
        {
            String s6 = (new StringBuilder()).append("insert into Enquiry values('").append(s8).append("','").append(s).append("','").append(s4).append("','").append(s1).append("','").append(s2).append("','").append(date).append("')").toString();
            System.out.println((new StringBuilder()).append("Qry").append(s6).toString());
            Database database1 = new Database();
            int i = database1.setResultSet(s6);
            if(s3.equals("yes"))
            {
                String s7 = (new StringBuilder()).append("update ApplicationEntry set Enquiry='yes' where ApplnID='").append(s).append("'").toString();
                int j = database1.setResultSet(s7);
            }
            database1.closeSetResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Inserting Enquiry : ").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("HrManager/Enquiry.jsp");
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