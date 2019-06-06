package district;

import java.io.PrintStream;
import java.sql.*;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package district:
//            ContractorFB

public class ContractorAction extends Action
{

    public ContractorAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Connection con;
        ContractorFB fb;
        String method;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "distcol", "distcol");
       
        fb = (ContractorFB)form;
        method = (String)fb.get("method");
        String sqlSTMT = null;
        if(!method.equals("create"))
          
        String cname = (String)fb.get("cname");
        String caddr = (String)fb.get("caddr");
        String bid = (String)fb.get("bid");
        String bname = (String)fb.get("bname");
        String baddr = (String)fb.get("baddr");
        String years = (String)fb.get("years");
        String pwd = (String)fb.get("pwd");
        int range = 12345;
        Random rand = new Random();
        int n = rand.nextInt(range);
        String pk = (new StringBuilder("CID")).append(n).toString();
        sqlSTMT = (new StringBuilder("insert into contractor(pk,cname,caddr,bid,bname,baddr,years,pwd) values ('")).append(pk).append("','").append(cname).append("','").append(caddr).append("','").append(bid).append("','").append(bname).append("','").append(baddr).append("','").append(years).append("','").append(pwd).append("')").toString();
        stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        if(!method.equals("delete"))
          
        String pk = (String)fb.get("pk");
        String sqlSTMT = (new StringBuilder("delete from contractor where pk='")).append(pk).append("'").toString();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        if(method.equals("search"))
            return mapping.findForward("success");
        if(!method.equals("edit"))
          
        String pk = (String)fb.get("pk");
        String cname = (String)fb.get("cname");
        String caddr = (String)fb.get("caddr");
        String bid = (String)fb.get("bid");
        String bname = (String)fb.get("bname");
        String baddr = (String)fb.get("baddr");
        String years = (String)fb.get("years");
        String pwd = (String)fb.get("pwd");
        String sqlSTMT = (new StringBuilder("update contractor set cname='")).append(cname).append("',caddr='").append(caddr).append("',bid='").append(bid).append("',bname='").append(bname).append("',baddr='").append(baddr).append("',years='").append(years).append("',pwd='").append(pwd).append("'where pk='").append(pk).append("'").toString();
        System.out.println(sqlSTMT);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sqlSTMT);
        request.setAttribute("cid", pk);
        con.close();
        return mapping.findForward("success");
        con.close();
        return mapping.findForward("fail");
     
    }
}