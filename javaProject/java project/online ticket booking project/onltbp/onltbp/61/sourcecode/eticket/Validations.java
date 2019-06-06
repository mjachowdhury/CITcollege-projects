/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;

import javax.sql.DataSource;



/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Validations
{
	public static boolean verifyAdmin(String name,String pass,DataSource ds) throws Exception
	{
		boolean valid=false;
		System.out.println("In Logic evalution");
		String id=name;
		String pwd=pass;
		if(id.equals("nimmala") && pwd.equals("uvwxyz"))
			valid=true;
		else
			valid=false;
		System.out.println("In valid(boolean) return");
		return valid;		
	}
	
	public static boolean verifyAgent(String name,String pass,DataSource ds) throws Exception
	{
		boolean valid=false;
		System.out.println("In Logic evalution");
		String id=name;
		String pwd=pass;
		if(id.equals("krishna") && pwd.equals("bhaskara"))
			valid=true;
		else
			valid=false;
		System.out.println("In valid(boolean) return");
		return valid;		
	}
}