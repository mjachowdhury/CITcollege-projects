//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package eticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import eticket.BookForm;
import java.sql.*;
/** 
 * MyEclipse Struts
 * Creation date: 04-09-2007
 * 
 * XDoclet definition:
 * @struts:action path="/bookAg" name="bform" scope="request" validate="true"
 * @struts:action-forward name="suc" path="bookticket.jsp"
 */
public class DelTickAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)throws Exception {
		System.out.println("in deltik");
		//BookForm bform = (BookForm) form;
		//ArrayList al=Validation.getblockdet(bform,getDataSource(request));
		String s[]=request.getParameterValues("id");
		DataSource ds=getDataSource(request);
		Connection con=ds.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from book_ticket where tid=?");
	int i=0;
		for(int j=0;i<s.length;i++)
		{
		ps.setInt(1,new Integer(s[i]).intValue());
		i=ps.executeUpdate();
		System.out.println("i=="+i);
		}
		/*Statement st1=con.createStatement();
		Statement st2=con.createStatement();
		Statement st3=con.createStatement();
		Statement st4=con.createStatement();
		Statement st5=con.createStatement();
		
		//con.setAutoCommit(false);
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
				int ya=0,ya1=0,a1=0,a2=0,a3=0;
				int res=0,tid=0,not=0;
				String busno="";
				String doj="";
		for(int i=0;i<s.length;i++)
		{
			System.out.println(1);
			rs=st1.executeQuery("select * from book_ticket where tid="+new Integer(s[i]).intValue());
			while(rs.next()){
				 res=rs.getInt(9);
				 tid=rs.getInt(8);
				 doj=rs.getString(2);
				 busno=rs.getString(1);				
			}
			System.out.println(2);
			//rs1=st2.executeQuery("select min(vacant) from book_ticket where busno='"+rs.getString(1)+"'and date_of_journey="+rs.getString(9));
			//while(rs1.next()){ya=rs1.getInt(1);}//ya==minimu;
			 //rs2=st3.executeQuery("select reserved from book_ticket where tid="+s[i]);
			 //System.out.println(3);
			//while(rs2.next()){ya1=rs2.getInt(1);}//ya1=reserved
			//ya+=ya1;
			//System.out.println(rs3.getString(1));
			PreparedStatement ps=con.prepareStatement("delete from book_ticket where tid=?");
			ps.setInt(1,new Integer(s[i]).intValue());
			System.out.println(4);
			a1=ps.executeUpdate();
			System.out.println(5);
			rs.next();
			rs3=st5.executeQuery("select min(tid) from book_ticket where busno='"+busno+"'and date_of_journey='"+doj+"'");
			System.out.println(6);
			while(rs3.next()){			
				a3=rs3.getInt(1);}
			rs3=st5.executeQuery("select vacant from book_ticket where tid="+a3);
			while(rs3.next()){			
			ya=rs3.getInt(1);}
			ya+=res;
			System.out.println(7);
			a2=st4.executeUpdate("update book_ticket set vacant="+ya+"where busno='"+busno+"'and date_of_journey='"+doj+"'and tid="+a3);
			System.out.println(8);
					
		}
		System.out.println(a1);
		System.out.println(a2);*/
	//	if(a1==1&&a2==1)
		//st.executeUpdate("delete book_ticket  where tid=")
		//con.commit();
	
		return mapping.findForward("suc");
		
		//HttpSession hs=request.getSession();
		//System.out.println (bform.getUsername1());
	//	hs.setAttribute("block",al);
		//hs.setAttribute("",bform.getUsername());
		
		
		
		}
		
		
	}

