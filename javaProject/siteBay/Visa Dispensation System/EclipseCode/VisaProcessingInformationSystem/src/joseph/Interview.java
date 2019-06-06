package joseph;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class Interview extends HttpServlet
{

    public Interview()
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
        String s1 = httpservletrequest.getParameter("d");
        String s2 = httpservletrequest.getParameter("m");
        String s3 = httpservletrequest.getParameter("y");
        String s4 = (new StringBuilder()).append(s3).append("-").append(s2).append("-").append(s1).toString();
        String s5 = httpservletrequest.getParameter("hr");
        String s6 = httpservletrequest.getParameter("mi");
        String s7 = httpservletrequest.getParameter("time");
        String s8 = (new StringBuilder()).append(s5).append(" : ").append(s6).append(" ").append(s7).toString();
        String s9 = httpservletrequest.getParameter("Venue");
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s10 = httpsession.getAttribute("Ses_ID").toString();
        Object obj = null;
        Object obj1 = null;
        Object obj2 = null;
        String s13 = null;
        try
        {
            Database database = new Database();
            String s11 = "select max(IntID) from Interview";
            ResultSet resultset = database.getResultSet(s11);
            if(!resultset.next())
            {
                s13 = "Int31";
            } else
            {
                s13 = resultset.getString(1);
                if(s13 == null)
                {
                    s13 = "Int31";
                    System.out.println(s13);
                } else
                {
                    s13 = (new StringBuilder()).append("Int").append(Integer.parseInt(s13.substring(3, s13.length())) + 1).toString();
                }
            }
            database.closeResultSet();
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error While Generating Interview ID : ").append(exception).toString());
        }
        try
        {
            String s12 = (new StringBuilder()).append("insert into Interview values('").append(s13).append("','").append(s).append("','").append(s10).append("','").append(s4).append("','").append(s8).append("','").append(s9).append("','").append(date).append("')").toString();
            Database database1 = new Database();
            int i = database1.setResultSet(s12);
            s12 = (new StringBuilder()).append("update ApplicationEntry set Interview='yes' where ApplnID='").append(s).append("'").toString();
            i = database1.setResultSet(s12);
            database1.closeSetResultSet();
        }
        catch(Exception exception1)
        {
            System.out.println((new StringBuilder()).append("Error While Inserting Interview : ").append(exception1).toString());
        }
        httpservletresponse.sendRedirect("HrManager/Interview.jsp");
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