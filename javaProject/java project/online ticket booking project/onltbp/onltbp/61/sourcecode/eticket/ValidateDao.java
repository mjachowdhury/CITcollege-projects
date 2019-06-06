package eticket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

/*
 * Created on Mar 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author subash
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateDao {

	/**
	 * @param cno
	 * 
	 * @return
	 */
	
	Connection con;
	public Connection getcon()
	{
		try{
			System.out.println("in try");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:server","scott","tiger");
			System.out.println(con);
			return con;
		}//try
		catch(Exception e){
			System.out.println("con fail");}
		return null;
		
	}//getcon
	
	public boolean cardcheck(int cno) {
		
		
		try
		{
			System.out.println("in check");
			
		con=getcon();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select cno from card where cno='"+cno+"'");
		System.out.println("result"+rs);
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			return true;
		}
	}//try
		catch(Exception e){
			e.printStackTrace();
		}
			
		
		
		
		
		
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
