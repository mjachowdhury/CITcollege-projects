package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class OnsiteArrival extends HttpServlet
{

    public OnsiteArrival()
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
        String s1 = httpservletrequest.getParameter("ticNo");
        String s2 = httpservletrequest.getParameter("fliNo");
        String s3 = httpservletrequest.getParameter("from");
        String s4 = httpservletrequest.getParameter("via");
        String s5 = httpservletrequest.getParameter("to");
        String s6 = httpservletrequest.getParameter("d");
        String s7 = httpservletrequest.getParameter("m");
        String s8 = httpservletrequest.getParameter("y");
        String s9 = (new StringBuilder()).append(s8).append("-").append(s7).append("-").append(s6).toString();
        String s10 = httpservletrequest.getParameter("hr");
        String s11 = httpservletrequest.getParameter("mi");
        String s12 = httpservletrequest.getParameter("time");
        String s13 = (new StringBuilder()).append(s10).append(" : ").append(s11).append(" ").append(s12).toString();
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s14 = httpsession.getAttribute("Ses_ID").toString();
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        String s17 = null;
        try
        {
            Database database = new Database();
            String s15 = "select max(OnSiteID) from OnsiteArrival";
            ResultSet resultset = database.getResultSet(s15);
            if(!resultset.next())
            {
                s17 = "Avl31";
            } else
            {
                s17 = resultset.getString(1);
                if(s17 == null)
                {
                    s17 = "Avl31";
                    System.out.println(s17);
                } else
                {
                    s17 = (new StringBuilder()).append("Avl").append(Integer.parseInt(s17.substring(3, s17.length())) + 1).toString();
                }
            }
            database.closeResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Generating Onsite Arrival ID : ").append(exception).toString());
        }
        try
        {
            String s16 = (new StringBuilder()).append("insert into OnsiteArrival values('").append(s17).append("','").append(s).append("','").append(s14).append("','").append(s1).append("','").append(s2).append("','").append(s3).append("','").append(s4).append("','").append(s5).append("','").append(s9).append("','").append(s13).append("','").append(date).append("')").toString();
            Database database1 = new Database();
            int i = database1.setResultSet(s16);
            s16 = (new StringBuilder()).append("update ApplicationEntry set OnsiteArrival='yes' where ApplnID='").append(s).append("'").toString();
            i = database1.setResultSet(s16);
            database1.closeSetResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Inserting OnsiteArrival : ").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("HrManager/OnsiteArrival.jsp");
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