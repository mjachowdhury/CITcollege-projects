package com.javatpoint;
import java.sql.*;
public class GetCon {
	private static String uname="onlinelibrary";
	private static String pass="onlinelibrary";
	private static Connection con;
private GetCon(){}

public static Connection getCon()
    {
	
        try
        {
			
            Class.forName("oracle.jdbc.driver.OracleDriver");
			
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",uname,pass);
        
		}
        catch(SQLException sqlexception)
        {
            System.out.println(sqlexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            System.out.println(classnotfoundexception);
        }
		
        return con;
	
}



public static int getPrimaryKey(){
	int nextvalue=0;
	Connection con=GetCon.getCon();
	PreparedStatement ps2;
	try {
	
	ps2=con.prepareStatement("select Javatpoint.nextval from dual");
	
	ResultSet rs=ps2.executeQuery();
	rs.next();
	nextvalue=rs.getInt(1);

	
	
} catch (SQLException e) {
		
		e.printStackTrace();
	}
return nextvalue;

}
}

