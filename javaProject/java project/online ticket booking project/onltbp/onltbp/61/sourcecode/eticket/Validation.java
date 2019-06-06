/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;



/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Validation
{
	public static boolean verifyAdmin(String name,String pass,DataSource ds) throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs;
		System.out.println("in valid");

		try
		{
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("connectednnnnnnnnnn");
			//con=DriverManager.getConnection("jdbc:oracle:thin:@nit:1521:server","scott","tiger");
			con=ds.getConnection();
			System.out.println("connected");
			ps=con.prepareStatement("select password from admindetails where username=?");
			System.out.println("In val");
			ps.setString(1,name);
			System.out.println("In val2");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("In val while");
				String password=rs.getString("password");
				if(password.equals(pass))
					valid=true;
				else
					valid=false;
				System.out.println("In val return");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if(con!=null)
				con.close();
		}
		System.out.println("After return");
		return valid;		
	}
	
	public static boolean verifyAgent(String name,String pass,DataSource ds) throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			System.out.println(name);
			System.out.println(pass);
			
			con=ds.getConnection();
			ps=con.prepareStatement("select uname from etagentper where uname=? and password=?");
			ps.setString(1,name);
			ps.setString(2,pass);
			rs=ps.executeQuery();
			if(rs.next()){
				
			valid=true;}
					
		}
		catch(Exception e)
		{
			valid=false;
			}
		finally
		{ 
			con.close();
		}
		return valid;
	}
	
	public static boolean verifyUser(String name,String pass,DataSource ds) throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			System.out.println(name);
			System.out.println(name);
			ps=con.prepareStatement("select userid from etuserdetails where userid=? and password=?");
			ps.setString(1,name);
			ps.setString(2,pass);
			rs=ps.executeQuery();
			if(rs.next()){			System.out.println(rs.getString(1));
			valid=true;
			}
			
					
		}
		catch(Exception e)
		{
			valid=false;
			}
		finally
		{ 
			con.close();
		}
		return valid;
	}
	
	public static boolean addAdmin(AdminBean admin,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{	System.out.println("1 from valid");
			con=ds.getConnection();
			System.out.println("after connection");
			
			ps=con.prepareStatement("insert into admindetails values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,admin.getAfname());
			System.out.println(admin.getAfname());
			ps.setString(2,admin.getAlname());
			ps.setString(3,admin.getUsername());
			ps.setString(4,admin.getPass());
			ps.setString(5,admin.getAaddress1());
			ps.setString(6,admin.getAaddress2());
			ps.setString(7,admin.getAcity());
			ps.setString(8,admin.getAstate());
			ps.setString(9,admin.getAphno());
			ps.setString(10,admin.getAemail());
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("in true block");
				valid=true;
			}
			else
			{
			valid=false;
			System.out.println("in false block");
			}
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static ArrayList getAdmin(DataSource ds)throws Exception
	{
		ArrayList ad=new ArrayList();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select * from admindetails");
			rs=ps.executeQuery();
			while(rs.next())
			{
				AdminBean admin=new AdminBean();
				admin.setAfname(rs.getString(1));
				admin.setAlname(rs.getString(2));
				admin.setUsername(rs.getString(3));
				admin.setPass(rs.getString(4));
				admin.setAaddress1(rs.getString(5));
				admin.setAaddress2(rs.getString(6));
				admin.setAcity(rs.getString(7));
				admin.setAstate(rs.getString(8));
				admin.setAphno(rs.getString(9));
				admin.setAemail(rs.getString(10));
				ad.add(admin);
				
			}
			throw new Exception("Data Are Not Available.");
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		return ad;
	}
	
	
	public static boolean addUser(UserBean user,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		//UserBean user=null;
		System.out.println("In Adduser");
	  try
		{
	  	System.out.println("In Adduser try");
			con=ds.getConnection();
			System.out.println("In Adduser try after con");
		    ps=con.prepareStatement("insert into etuserdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    
			/*ps.setString(1,user.getLogin());
			//ps.setString(2,user.getPass());
			//ps.setString(3,user.getSecque());
			//ps.setString(4,user.getSecans());
			//ps.setString(5,user.getSlt());
			ps.setString(8,user.getBdmonth());
			ps.setString(9,user.getBdday());
			ps.setString(10,user.getBdyear());
			ps.setString(11,user.getGender());
			ps.setString(12,user.getEdu());
			ps.setString(13,user.getOcc());
			ps.setString(14,user.getInd());
			ps.setString(15,user.getAin());
			ps.setString(16,user.getAddr());
			ps.setString(17,user.getCity());
			ps.setString(18,user.getState());
			ps.setString(19,user.getCountry());
			ps.setString(20,user.getZip());
			ps.setString(21,user.getPhno());
			ps.setString(22,user.getEmail());
			ps.setString(23,user.getIdproof());
			ps.setString(24,user.getIdno());*/
		    System.out.println(1);
		    ps.setString(1,user.getLogin());
		    System.out.println(user.getLogin());
		    ps.setString(2,user.getFname());
		    System.out.println(user.getFname());
			ps.setString(3,user.getLname());
			System.out.println(user.getLname());
			ps.setString(4,user.getLogin());
			System.out.println(user.getLogin());
		    ps.setString(5,user.getPass());
		    System.out.println(user.getPass());
			ps.setString(6,user.getOcc());
			System.out.println(user.getOcc());
			ps.setString(7,user.getAddr());
			System.out.println(8);
			ps.setString(8,user.getAddr());
			System.out.println(user.getAddr());
			ps.setString(9,user.getCity());
			System.out.println(user.getCity());
			ps.setString(11,user.getState());
			System.out.println(user.getState());
			ps.setString(10,user.getZip());
			System.out.println(user.getZip());
			ps.setString(12,user.getPhno());
			System.out.println(user.getPhno());
			ps.setString(13,user.getPhno());
			System.out.println(user.getPhno());
			ps.setString(14,user.getEmail());
			System.out.println(user.getEmail());
			ps.setString(15,user.getDor());
			System.out.println(user.getDor());
			System.out.println(user.getGender());
			ps.setString(16,user.getGender());
			System.out.println(user.getIdproof());
			ps.setString(17,user.getIdproof());
			System.out.println(user.getIdno());
			ps.setString(18,user.getIdno());
			System.out.println(user.getSecque());
			ps.setInt(19,user.getSecque());
			System.out.println(user.getSecans());
			ps.setString(20,user.getSecans());
			 
		    System.out.println("before execute()");
			if(ps.executeUpdate()==1)
				valid=true;
			else
				valid=false;
		}
		catch(Exception e){e.printStackTrace();}
		finally
		{
			con.close();
		}
		return valid;
	}

		
	public static boolean addAgent(AgentBean agent,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		//AgentBean agent=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("insert into etagenttemp values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			/*ps.setString(1,agent.getLogin());
			ps.setString(2,agent.getPass());
			ps.setString(3,agent.getSecque());
			ps.setString(4,agent.getSecans());
			ps.setString(5,agent.getSlt());
			ps.setString(6,agent.getFname());
			ps.setString(7,agent.getLname());
			ps.setString(8,agent.getGender());
			ps.setString(9,agent.getOcc());
			ps.setString(10,agent.getInd());
			ps.setString(11,agent.getAin());
			ps.setString(16,agent.getZip());
			ps.setString(12,agent.getAddr());
			ps.setString(13,agent.getCity());
			ps.setString(14,agent.getState());
			ps.setString(15,agent.getCountry());
			ps.setString(17,agent.getPhno());
			ps.setString(18,agent.getEmail());
			ps.setString(19,agent.getDrmonth());
			ps.setString(20,agent.getDrday());
			ps.setString(21,agent.getDryear());
			ps.setString(22,agent.getRegno());*/
			ps.setString(1,agent.getFname());
			ps.setString(2,agent.getLname());
			ps.setString(3,agent.getAddr());
			ps.setString(4,agent.getCountry());
			ps.setString(5,agent.getCity());
			ps.setString(6,agent.getState());
			String dor=(agent.getDrday()+"-"+agent.getDrmonth()+"-"+agent.getDryear());
			System.out.println("getthe data was:"+dor);
			ps.setString(7,dor);
			ps.setString(8,agent.getRegno());
			ps.setString(10,agent.getPhno());
			ps.setString(9,agent.getEmail());
			ps.setInt(11,agent.getSecque());
			ps.setString(12,agent.getSecans());
			ps.setString(13,agent.getLogin());
			ps.setString(14,agent.getPass());
			if(ps.executeUpdate()==1)
			{
				valid=true;
			System.out.println(valid);
			}
			else
			
				{
				valid=false;
							System.out.println(valid);
				}
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	
	
	
	
	
	
	
	public static boolean acceptAgent(String regno,DataSource ds)throws Exception
	{
		AgentBean accept=null;
		boolean flag1=false,flag2=false,flag3=false;
		boolean valid=false;
		String id="";
		Connection con=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null,rs2=null;
		try
		{
			String rno=new String();	//rno=regno
			con=ds.getConnection();
			con.setAutoCommit(false);
			System.out.println(1);
			ps1=con.prepareStatement("select * from etagenttemp where agregno=?");
			ps1.setString(1,regno);
			rs1=ps1.executeQuery();
			System.out.println(2);
			while(rs1.next())
			{
				flag1=true;
				//accept=new AgentBean();
				String fname=rs1.getString("fname");
				String lname=rs1.getString("lname");
				String address1=rs1.getString("address1");
				String address2=rs1.getString("address2");
				String city=rs1.getString("city");
				String state=rs1.getString("state");
				String dateofreg=rs1.getString("dateofreg");
				rno=rs1.getString("agregno");
				String email=rs1.getString("email");
				String phno=rs1.getString("phno");
				int  secq=rs1.getInt("secq");
				String seca=rs1.getString("seca");
				String uname=rs1.getString("uname");
				String pass=rs1.getString("password");
				
				System.out.println(3);					
				//System.out.println("After seq");
				ps1=con.prepareStatement("insert into etagentper values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				/*create table etagentper(uname varchar2(20),password varchar2(10),
				 * fname varchar2(30),lname varchar2(30),address1 varchar2(30),
				 * address2 varchar2(30),city varchar2(15),state varchar2(15),
				 * dateofreg varchar2(10),regno varchar2(10),email varchar2(35),
				 * phno varchar2(15)); */
		 
				ps1.setString(1,uname);
				ps1.setString(2,pass);
				ps1.setString(3,fname);
				ps1.setString(4,lname);
				ps1.setString(5,address1);
				ps1.setString(6,address2);
				ps1.setString(7,city);
				ps1.setString(8,state);
				ps1.setString(9,dateofreg);
				ps1.setString(10,regno);
				ps1.setString(11,email);
				ps1.setString(12,phno);
				ps1.setInt(13,secq);
				ps1.setString(14,seca);
				
			//System.out.println("in before flag2");
			//System.out.println("update=="+ps1.executeUpdate());
				if(ps1.executeUpdate()==1)
		{
					flag2=true;
					System.out.println("flag 2"+flag2);	
		}
	     	System.out.println("flag1"+flag1);
			ps1=con.prepareStatement("delete from etagenttemp where agregno=?");
			ps1.setString(1,regno);
			if(ps1.executeUpdate()==1)
			{
				flag3=true;
				System.out.println("flag"+flag3);
			}
			if(flag1&&flag2&&flag3)
			{
				con.commit();
				valid=true;System.out.println("in final loop before commit;"+valid);
			}//if
			else
			{valid=false;System.out.println(valid);}
			}
		}
		catch(Exception e){}
		finally
		{			con.close();		}//finally
		return valid;
	}//acept agent
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	public static boolean rejectAgent(String regno,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		//AgentBean agent=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("delete from etagenttemp where agregno=?");
			ps.setString(1,regno);
			
			if(ps.executeUpdate()==1)
			{
			System.out.println("in sucess");
				valid=true;
			}
				else
			valid=false;
			
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	public static ArrayList getPendingAgents(DataSource ds)throws Exception
	{
		ArrayList agents=new ArrayList();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select * from etagenttemp");
			rs=ps.executeQuery();
			System.out.println("in validation.getpendingagent");
			while(rs.next())
			{
				AgentBean agent=new AgentBean();
				System.out.println(rs.getString(1));
				/*agent.setLogin(rs.getString(1));
				agent.setPass(rs.getString(2));
				agent.setFname(rs.getString(3));
				agent.setLname(rs.getString(4));
				agent.setAddr(rs.getString(5));
				agent.setCity(rs.getString(6));
				agent.setState(rs.getString(7));
				agent.setCountry(rs.getString(8));
				agent.setDrday(rs.getString(9));
				agent.setDrmonth(rs.getString(10));
				agent.setDryear(rs.getString(11));
				agent.setZip(rs.getString(12));
				agent.setEmail(rs.getString(13));
				agent.setPhno(rs.getString(14));
				System.out.println("in 15"+rs.getString(15));
				agent.setRegno(rs.getString(15));*/
				agent.setFname(rs.getString(1));
				agent.setLname(rs.getString(2));
				agent.setAddr(rs.getString(3));
				agent.setCity(rs.getString(5));
				agent.setState(rs.getString(6));
				agent.setDor(rs.getString(7));
				agent.setRegno(rs.getString(8));
				agent.setEmail(rs.getString(9));
				agent.setPhno(rs.getString(10));
				
				agents.add(agent);
			}
			throw new Exception("Data Are Not Available.");
		}
		catch(Exception e){}
		finally
		{
			System.out.println(agents.size());
			con.close();
		}
		return agents;
	}
	
	public static ArrayList getPermanentAgents(DataSource ds)throws Exception
	{
		ArrayList agents=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			con.commit();
			rs=st.executeQuery("select * from etagentper");
			System.out.println(1);
			int i=1;
			while(rs.next())
			{
				System.out.println(++i);
				AgentBean agent=new AgentBean();
				System.out.println(rs.getString(1));
				agent.setLogin(rs.getString(1));
				agent.setPass(rs.getString(2));
				System.out.println(rs.getString(1));
				//System.out.println("in while of validation .getpending"+rs.getString(2));
				agent.setFname(rs.getString(3));
				agent.setLname(rs.getString(4));
				agent.setAddr(rs.getString(5));
				agent.setCity(rs.getString(7));
				System.out.println(rs.getString(1));
				agent.setState(rs.getString(8));
				//agent.setCountry(rs.getString(9));
			//	System.out.println("day"+rs.getString(9));
				//agent.setDrday(rs.getString(9));
				//System.out.println("month"+rs.getString(10));
				//agent.setDrmonth(rs.getString(10));
				//agent.setDryear(rs.getString(11));
				//System.out.println("year"+rs.getString(11));
				agent.setDor(rs.getString(9));
				//agent.setZip(rs.getString(12));
				System.out.println(rs.getString(1));
				agent.setEmail(rs.getString(11));
				//System.out.println("email"+rs.getString(13));
				agent.setPhno(rs.getString(12));
				agent.setRegno(rs.getString(10));
				agents.add(agent);
				System.out.println(rs.getString(1));
			}
			throw new Exception("Data Are Not Available.");
		}
		catch(Exception e){}
		finally
		{
			System.out.println("afetre2");
			System.out.println("size"+agents.size());
			
			con.close();
		}
		return agents;
	}
	
	public static AgentBean getAgent(String regno,DataSource ds)
	{
		AgentBean agent=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			System.out.println("In getAgent1");
			con=ds.getConnection();
			System.out.println(regno);
			ps=con.prepareStatement("select * from etagentper where agregno=?");
			ps.setString(1,regno);
			rs=ps.executeQuery();
			System.out.println("In getAgent2");
			//boolean e=rs.next();
			//System.out.println(e);
			while(rs.next())
			{
				System.out.println("in while of getAgentdetails");
				agent=new AgentBean();
				agent.setLogin(rs.getString(1));
				agent.setPass(rs.getString(2));
				agent.setFname(rs.getString(3));
				agent.setLname(rs.getString(4));
				agent.setAddr(rs.getString(5));
				agent.setCity(rs.getString(6));
				agent.setState(rs.getString(7));
				agent.setDor(rs.getString(9));
				//agent.setCountry(rs.getString(8));
				//agent.setDrday(rs.getString(9));
				//agent.setDrmonth(rs.getString(10));
				//agent.setDryear(rs.getString(11));
				//agent.setZip(rs.getString(12));
				agent.setEmail(rs.getString(11));
				agent.setPhno(rs.getString(12));
				agent.setRegno(rs.getString(10));
			}
		}
			catch(Exception e){}
			finally
			{try
				{
				con.close();
				}catch(Exception e){}
			}
			return agent;
	}
	
	public static boolean updateAgent(AgentBean ag,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("update etagentper set fname=?,lname=?,address1=?,address2=?,city=?,state=?,dateofreg=?,email=?,phno=? where agregno=?");
			
			
			
			ps.setString(1,ag.getFname());
			ps.setString(2,ag.getLname());
			ps.setString(3,ag.getAddr());
			ps.setString(4,ag.getCountry());
			ps.setString(5,ag.getCity());
			ps.setString(6,ag.getState());
			ps.setString(7,ag.getDor());
			System.out.println("dor="+ag.getDor());
			System.out.println("state"+ag.getState());
			
			//ps.setString(7,ag.getDrmonth());
		//	ps.setString(8,ag.getDryear());
			ps.setString(8,ag.getEmail());
			ps.setString(9,ag.getPhno());
			ps.setString(10,ag.getRegno());
			
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static boolean deleteAgent(String s,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			System.out.println("in try of remove agent");
			con=ds.getConnection();
			ps=con.prepareStatement("delete from etagentper where agregno=?");
			ps.setString(1,s);
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("removed agent");
				valid=true;
			}
			else
			valid=false;
		}
		catch(Exception e){e.printStackTrace();}
		finally
		{
			con.close();
		}
		return valid;
	}
	
	public static boolean addBusType(BusBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		int j=0;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st;
		ResultSet rs=null;
		try
		{
			System.out.println("In addbustype enter");
			con=ds.getConnection();
			st=con.createStatement();
			String query="select tid.nextval from dual";
			System.out.println("after sequense");
			rs=st.executeQuery(query);
			System.out.println("after executing seq");
			BusBean bus1=new BusBean();
			System.out.println("object is created");
			while(rs.next())
			 j=rs.getInt(1);
			System.out.println(j);
			bus1.setTid(j);
			System.out.println("hello1");
			ps=con.prepareStatement("insert into bustypedetails values(?,?,?,?)");
			ps.setInt(1,bus1.getTid());
			ps.setString(2,bus.getTname());
			ps.setInt(3,bus.getCapacity());
			ps.setString(4,bus.getDesc());
			System.out.println("hello2");
			if(ps.executeUpdate()==1)
			{
				valid=true;
			}
			else
			{
			valid=false;
			}
			System.out.println(valid);
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		return valid;
	}
	
	public static ArrayList getBusType(DataSource ds)throws Exception
	{
		ArrayList bustype=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			System.out.println("In Validsation");
			rs=st.executeQuery("select * from bustypedetails");
			while(rs.next())
			{
				System.out.println(" check");
				BustypeBean bus=new BustypeBean();
				System.out.println("Bean Created");
				bus.setTid(rs.getInt(1));
				bus.setTname(rs.getString(2));
				bus.setCapacity(rs.getString(3));
				bus.setDesc(rs.getString(4));
				System.out.println("Bean Before Adding");
				
				bustype.add(bus);
				System.out.println("Bean after Adding");
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return bustype;
	}
	public static ArrayList getBusNo(DataSource ds)throws Exception
	{
		ArrayList bustype=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			System.out.println("In Validsation");
			rs=st.executeQuery("select * from busdetails");
			while(rs.next())
			{
				System.out.println(" check");
				BusEntryBean bus=new BusEntryBean();
				System.out.println("Bean Created");
				bus.setBid(rs.getInt(1));
				bus.setBusno(rs.getString(2));
				System.out.println("Bean Before Adding");
				
				bustype.add(bus);
				System.out.println("Bean after Adding");
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return bustype;
	}

	public static boolean addLocation(BusLocBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		 
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			System.out.println("in add location");
			ps=con.prepareStatement("select lid.nextval from dual");
			rs=ps.executeQuery();
			System.out.println("in add location");
			if(rs.next()){
			bus.setLid(rs.getInt(1));
			}
		    int i=bus.getLid();
		    System.out.println(i);
			ps=con.prepareStatement("insert into location values(?,?,?)");
			System.out.println("in add location after query");
			ps.setInt(1,bus.getLid());
			ps.setString(2,bus.getLname());
			ps.setString(3,bus.getLaddress());
			if(ps.executeUpdate()==1)
			valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		
			return valid;
	}
	
	
	public static int addTripDetails(BusTripBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		int i=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=ds.getConnection();
		try
		{System.out.println("==================================================");
			
			ps=con.prepareStatement("select trid.nextval from dual");
			rs=ps.executeQuery();
			while(rs.next())
			bus.setTrid(rs.getInt(1));
			ps=con.prepareStatement("insert into tripdetails values(?,?,?,?,?,?)");
			 i=bus.getTrid();
			ps.setInt(1,i);
			ps.setString(2,bus.getBno());
			ps.setInt(3,bus.getStartpoint());
			ps.setInt(4,bus.getEndpoint());
			ps.setString(5,bus.getStarttime());
			ps.setString(6,bus.getEndtime());
			if(ps.executeUpdate()==1){
				valid=true;
			}
			else{
				valid=false;
			}
			ps.close();
			System.out.println("***************************");
			Statement st=con.createStatement();
			rs=st.executeQuery("select seqno.nextVal from dual");
			int y=0;			
			while(rs.next())
				y=rs.getInt(1);			
			String q1="insert into triphalts values("+bus.getTrid()+","+bus.getStartpoint()+","+y+")";
			System.out.println(q1);
			int temp=0;
			//boolean flag=
			//System.out.println("temp=="+flag);
			
			System.out.println(st.execute("insert into triphalts values("+bus.getTrid()+","+bus.getStartpoint()+","+y+")"));
			System.out.println("valid"+valid);
			
			rs=st.executeQuery("select seqno.nextVal from dual");
			while(rs.next())
				 y=rs.getInt(1);
			
				
			 q1="insert into triphalts values("+bus.getTrid()+","+bus.getEndpoint()+","+y+")";
			 System.out.println(q1);
			 temp=st.executeUpdate(q1);
			
		
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		if(valid)
			return i;
		return 0;
	}
	public static boolean addTripHalt(TriphaltBean tb,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			System.out.println("Addtriphalt");
			con=ds.getConnection();
			ps=con.prepareStatement("select seqno.nextVal from dual");
			rs=ps.executeQuery();
			while(rs.next())
			tb.setSeqno(rs.getInt(1));
			System.out.println(tb.getSeqno());
			ps=con.prepareStatement("insert into triphalts values(?,?,?)");
			ps.setInt(1,tb.getTrid());
			ps.setInt(2,tb.getLid());
			ps.setInt(3,tb.getSeqno());
			
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			System.out.println("valid========"+valid);
			con.close();
		}
		return valid;
	}
	public static ArrayList getTripDetails(DataSource ds)throws Exception
	{
		ArrayList al=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from tripdetails");
			while(rs.next())
			{
				BusTripBean bus=new BusTripBean();
				bus.setTrid(rs.getInt(1));
				
				
				al.add(bus);
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return al;
	}
	public static ArrayList getTripDetailsC(DataSource ds)throws Exception
	{
		ArrayList al=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from tripdetails");
			PreparedStatement ps=con.prepareStatement("select busdetails.bname from busdetails,tripdetails where busdetails.busno=tripdetails.busno");
			
			while(rs.next())
			{
				System.out.println(1);
				BusFormTemp bus=new BusFormTemp();
				bus.setTrid(rs.getInt(1));
				bus.setBusno(rs.getString(2));
				Statement st1=con.createStatement();
				ResultSet rs2=st1.executeQuery("select lname from location,tripdetails where location.lid="+rs.getInt("startpoint"));
				rs2.next();	System.out.println(1);	
				System.out.println(rs2.getString(1));
				bus.setStartpoint(rs2.getString(1));
				rs2.close();
				ResultSet rs3=st1.executeQuery("select lname from location,tripdetails where location.lid="+rs.getInt("endpoint"));
				rs3.next();	
				System.out.println(rs3.getString(1));
				System.out.println(1);						
				//bus.setStartpoint(rs2.getString(1));
				bus.setEndpoint(rs3.getString(1));
				rs3.close();System.out.println(1);
				bus.setStarttime(rs.getString(5));
				bus.setEndtime(rs.getString(6));
				ResultSet rs1=ps.executeQuery();System.out.println(1);
				if(rs1.next())
					bus.setBname(rs1.getString(1));
				rs1.close();System.out.println(1);
				System.out.println(1);
				al.add(bus);
				
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return al;
	}
	
	public static ArrayList getLocations(DataSource ds)throws Exception
	{
		ArrayList loc=new ArrayList();
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from location");
			while(rs.next())
			{
				System.out.println("in location while");
				BusLocBean bus=new BusLocBean();
				bus.setLid(rs.getInt(1));
				bus.setLname(rs.getString(2));							
				loc.add(bus);
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return loc;
	}
	public static ArrayList getBuses(DataSource ds)throws Exception
	{
		ArrayList buses=new ArrayList();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select busdetails.bid,busdetails.bname,busdetails.busno,bustypedetails.tname from busdetails,bustypedetails where busdetails.bustype=bustypedetails.tid");
			rs=ps.executeQuery();
			System.out.println("query executed");
			while(rs.next())
			{
				System.out.println("in while of getBuses");
				BusEntryBean bus=new BusEntryBean();
				bus.setBid(rs.getInt("bid"));
				System.out.println(bus.getBid());
				bus.setBname(rs.getString("bname"));
				System.out.println(bus.getBname());
				bus.setBusno(rs.getString("busno"));
				System.out.println(bus.getBusno());
				bus.setBustype(rs.getString("tname"));
				buses.add(bus);
				System.out.println(bus.getBustype());
			}
			throw new Exception("Data Are Not Available.");
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		return buses;
	}
	
	public static BustypeBean getBustypedetails(String bustype,DataSource ds)
	{
		BustypeBean bus=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select * from bustypedetails where tname=?");
			ps.setString(1,bustype);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("in while of getBustypedetails");
				 bus=new BustypeBean();
				bus.setTid(rs.getInt(1));
				bus.setTname(rs.getString(2));
				System.out.println(bus.getTname());
				bus.setCapacity(rs.getString(3));
				bus.setDesc(rs.getString(4));
				
			}
		}
			catch(Exception e){}
			finally
			{try
				{
				con.close();
				}catch(Exception e){}
			}
			return bus;
	}
	
    public static ArrayList getTripdetails(String busno,DataSource ds)
    {
   	ArrayList obj=new ArrayList();
    	BusBean bus=new BusBean();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select tripdetails.trid,tripdetails.busno,location.lname from tripdetails,location where tripdetails.startpoint=location.lid and busno=?");
			ps.setString(1,busno);
			System.out.println("BusNo=="+busno);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("in while of getTripDetails");
				bus.setTrid(rs.getInt(1));
				System.out.println(bus.getTrid());
				System.out.println("before temp");
				System.out.println(rs.getString(2));
				
				bus.setBusno(rs.getString(2));
				System.out.println(bus.getBusno());
				bus.setStartpoint(rs.getString(3));                            
				System.out.println(bus.getStartpoint());
			
			}
			ps=con.prepareStatement("select location.lname,tripdetails.starttime, tripdetails.endtime from tripdetails,location where tripdetails.endpoint=location.lid and busno=?");
			ps.setString(1,busno);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("in while of getTripDetails");
				bus.setEndpoint(rs.getString(1));
				System.out.println(bus.getEndpoint());
				bus.setStarttime(rs.getString(2));
				bus.setEndtime(rs.getString(3));                            
				System.out.println(bus.getEndtime());
				obj.add(bus);
			
			}
			
			
		}
			catch(Exception e){}
			finally
			{try
				{
				con.close();
				}catch(Exception e){}
			}
			return obj;
	
    	 
    }
    
    public static BusEntryBean viewBus(String busno,DataSource ds)
	{
		BusEntryBean bus=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select * from busdetails where busno=?");
			ps.setString(1,busno);
			rs=ps.executeQuery();
			while(rs.next())
			{
				 bus=new BusEntryBean();
				bus.setBusno(rs.getString(2));
				bus.setBtype(rs.getInt(3));
				bus.setBname(rs.getString(4));
				
			}
		}
			catch(Exception e){}
			finally
			{try
				{
				con.close();
				}catch(Exception e){}
			}
			return bus;
	}
    
	public static boolean addBus(BusEntryBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		int j=0;
		Statement st=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			
			String query="select bid.nextval from dual";
			System.out.println("after sequense");
			rs=st.executeQuery(query);
			while(rs.next())
				j=rs.getInt(1);
			System.out.println("after executing seq");
			System.out.println(j);
			ps=con.prepareStatement("insert into busdetails values(?,?,?,?)");
			ps.setInt(1,j);
			ps.setString(2,bus.getBusno());
			ps.setInt(3,bus.getBtype());
			ps.setString(4,bus.getBname());
			System.out.println("j");
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
			System.out.println(valid);
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		return valid;
	}
     
	public static boolean removeBus(String busno,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			System.out.println("in try of remove bus");
			con=ds.getConnection();
			ps=con.prepareStatement("delete from busdetails where busno=?");
			ps.setString(1,busno);
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("removed bus");
				valid=true;
			}
			else
			valid=false;
		}
		catch(Exception e){e.printStackTrace();}
		finally
		{
			con.close();
		}
		return valid;
	}

	public static boolean updateBus(BusEntryBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("update busdetails set bustype=?,bname=? where busno=?");
			
			ps.setString(3,bus.getBusno());
			ps.setInt(1,bus.getBtype());
			ps.setString(2,bus.getBname());
			
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	public static boolean updateBusType(BustypeBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("update bustypedetails set tname=?,capacity=?,descr=? where tid=?");
			
			ps.setInt(4,bus.getTid());
			ps.setString(1,bus.getTname());
			ps.setString(2,bus.getCapacity());
			ps.setString(3,bus.getDesc());
			
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static boolean removeBusType(String tid,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			System.out.println("in try of remove busType");
			con=ds.getConnection();
			ps=con.prepareStatement("delete from bustypedetails where tid=?");
			int j=Integer.parseInt(tid);
			ps.setInt(1,j);
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("removed busType");
				valid=true;
			}
			else
			valid=false;
		}
		catch(Exception e){e.printStackTrace();}
		finally
		{
			con.close();
		}
		return valid;
	}
	public static ArrayList viewTripHalts(String trid,DataSource ds)throws Exception
	{
		ArrayList obj1=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			obj1=new ArrayList();
			System.out.println("in validation view triphalts");
			ps=con.prepareStatement("select triphalts.trid,location.lname,triphalts.seqno from triphalts,location where triphalts.lid=location.lid and triphalts.trid=?");
			int j=Integer.parseInt(trid);
			System.out.println(j);
			ps.setInt(1,j);
			rs=ps.executeQuery();
			while(rs.next())
			{								
				System.out.println("inside while of view triphalts");
				BusBean bus=new BusBean();
				bus.setTrid(rs.getInt(1));
				System.out.println(bus.getTrid());
				bus.setLname(rs.getString(2));
				bus.setSeqno(rs.getInt(3));
				System.out.println(bus.getSeqno());
				obj1.add(bus);
				System.out.println("obj1 added");
			}
		}catch(Exception e){System.out.println(e);}
		finally
		{
			try
			{
				con.close();
			}catch(Exception e){}
		}
		return obj1;
	}
	
	public static BusTripBean viewTrip(String trid,DataSource ds)
	{
		BusTripBean obj2=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("select * from tripdetails where trid=?");
			ps.setInt(1,Integer.parseInt(trid));
			rs=ps.executeQuery();
			while(rs.next())
			{
				 obj2=new BusTripBean();
				 
				 obj2.setBno(rs.getString(2));
				 obj2.setStartpoint(rs.getInt(3));
				 obj2.setEndpoint(rs.getInt(4));
				 obj2.setStarttime(rs.getString(5));
				 obj2.setEndtime(rs.getString(6));
			}
		}
			catch(Exception e){}
			finally
			{try
				{
				con.close();
				}catch(Exception e){}
			}
			return obj2;
	}
	
	public static boolean updateTrip(BusTripBean bus,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			con=ds.getConnection();
			ps=con.prepareStatement("update tripdetails set startpoint=?,endpoint=?,starttime=?,endtime=? where busno=?");
			
			ps.setString(5,bus.getBno());
			ps.setInt(1,bus.getStartpoint());
			ps.setInt(2,bus.getEndpoint());
			ps.setString(3,bus.getStarttime());
			ps.setString(4,bus.getEndtime());
			if(ps.executeUpdate()==1)
				valid=true;
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static boolean deleteTrip(String i,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			System.out.println("in try of remove trip");
			con=ds.getConnection();
			ps=con.prepareStatement("delete from tripdetails where trid=?");
			int j=Integer.parseInt(i);
			System.out.println("in deleteTrip Validation :"+j);
			ps.setInt(1,j);
			int k=ps.executeUpdate();
			System.out.println(k);
			if(k==1)
			{
				System.out.println("removed Trip");
				valid=true;
			}
			else
			valid=false;
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}

	
	public static boolean addOffer_Form(OfferBean ob,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Statement st=null;
		int j=0;
		try
		{
			con=ds.getConnection();
			st=con.createStatement();
			
			String query="select offer_id.nextval from dual";
			System.out.println("after sequense");
			rs=st.executeQuery(query);
			while(rs.next())
			{
				System.out.println("In offer add while");
				j=rs.getInt(1);
				ob.setOfferid((new Integer(j)).toString());
				System.out.println(ob.getOfferid());
			}
			Date obj1=new Date(DateAccept(ob.getStartdate()));
			Date end=new Date(DateAccept(ob.getEnddate()));
			//System.out.println(obj1);				
			//System.out.println("obj1.before(end)? end=="+end);				
			
			if(obj1.before(end)){
			ps=con.prepareStatement("insert into offer_details values(?,?,?,?,?,?)");
			ps.setString(1,ob.getOfferid());
			ps.setString(2,ob.getOffername());
			ps.setString(3,ob.getStartdate());
			ps.setString(4,ob.getEnddate());
			ps.setString(5,ob.getApplfor());
			ps.setString(6,ob.getDescr());
			
			System.out.println(ob.getDescr());
			System.out.println(ob.getApplfor());
			System.out.println(ob.getEnddate());
			System.out.println(ob.getStartdate());
			System.out.println(ob.getOfferid());
			System.out.println(ob.getDescr());
					
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("values inserted");
				valid=true;
			}
			else
			valid=false;
		}
			else
			{
				valid =false;
			}
			}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static ArrayList getOffers(DataSource ds)throws Exception
	{
		ArrayList offer=new ArrayList();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Date obj=null;
		
		try
		{
			System.out.println("in getoffer validation");
			con=ds.getConnection();
			ps=con.prepareStatement("select * from offer_Details");
			
			obj=new Date();
			
			rs=ps.executeQuery();
			System.out.println("result");
			while(rs.next())
			{
			
				OfferBean ob=new OfferBean();
				System.out.println("offer bean created");		
				ob.setOfferid(rs.getString(1));
				ob.setOffername(rs.getString(2));
				String str1=rs.getString(4);
				ob.setEnddate(str1);
				String str=rs.getString(3);
				ob.setStartdate(str);
				System.out.println(str);	
				ob.setApplfor(rs.getString(5));
				ob.setDescr(rs.getString(6));
				//date base date 
				
				Date obj1=new Date(DateAccept(str));
				Date end=new Date(DateAccept(str1));
				System.out.println(obj1);				
				System.out.println("obj1.before(end)? end=="+end);				
				
				if(obj1.before(end))
				ob.setValid(1);
				else
				{
					System.out.println("in false block");
					ob.setValid(0);
					//throw new Exception("Invalid data");
								
				}
				offer.add(ob);
				System.out.println(offer.size());
				System.out.println("from ob.startdate::"+ob.getStartdate());
				
				//int i1=obj1.getDate();
				//int i2=obj1.getMonth();
				//int i3=obj1.getYear();
				//if((result3<=i3)&&(result2<=i2)&&(result1<=i1))
				//if(obj.before(obj1))
				//{
				//System.out.println("in if");
				//}
			}
			throw new Exception("Data Are Not Available.");
		}
		catch(Exception e){}
		finally
		{con.close();}
		System.out.println("Returning"+offer.size());
		return offer;
	}
	//Conversion of date accept
	public static String DateAccept(String s)
	{
	char ch[]=new char[s.length()];		
	ch=s.toCharArray();
	for(int i=0;i<ch.length;i++)
	{
	if(ch[i]=='-')
		ch[i]='/';
	}
	System.out.println(ch);
	String s1=new String(ch);
	return s1;
	}
//delete offer	
	public static boolean deleteOffer(int id,DataSource ds)throws Exception
	{
		boolean valid=false;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			System.out.println("In deleteOffer");
			con=ds.getConnection();
			
			ps=con.prepareStatement("delete from offer_details where offer_id=?");
			
			ps.setInt(1,id);
			System.out.println("delete offer_id");
			if(ps.executeUpdate()==1)
			{valid=true;}
			else
			{
			valid=false;
			}
			System.out.println(valid);
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		return valid;
	}
	

	
	public static boolean updateOffer(OfferBean ob,DataSource ds)throws Exception
	{
		boolean valid=false;
		Connection con=null;
		Statement ps=null;
		ResultSet rs=null;
		
		
		try
		{
			System.out.println("in getoffer validation");
			con=ds.getConnection();
			Integer i= new Integer(ob.getOfferid());
			
			ps=con.createStatement();
						
			//System.out.println("update offer_details set offer_name='"+ob.getOffername()+"'","offer_start_dt='"+ob.getStartdate()+"'","offer_end_dt='"+ob.getEnddate()+"'","applicable_for='"+ob.getApplfor()+"'","descr='"+ob.getDescr()+"'where offer_id=?");
			System.out.println(ob.getOfferid());
			
			 System.out.println("after ps.setInt(6)"+i);
			 String query="update offer_details set offer_name='"+ob.getOffername()+"',offer_start_dt='"+ob.getStartdate()+"',offer_end_dt='"+ob.getEnddate()+"',applicable_for='"+ob.getApplfor()+"',descr='"+ob.getDescr()+"'  where offer_id="+i;
			 System.out.println(query);
			int result=ps.executeUpdate(query);
			System.out.println(result);
			if(result==1)
			{	valid=true;}
		    else
		    	valid=false;
			System.out.println(valid);
			
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println(valid);
		return valid;
	}
	
	public static OfferBean modifyOffer(String id,DataSource ds)throws Exception
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i1=Integer.parseInt(id);
		OfferBean ob=new OfferBean();
		try
		{
			System.out.println("in getoffer validation");
			con=ds.getConnection();
			ps=con.prepareStatement("select *   from offer_details where offer_id=?");
			ps.setInt(1,i1);
			rs=ps.executeQuery();
			while(rs.next())
			{
				
				
				ob.setOffername(rs.getString(2));
				ob.setStartdate(rs.getString(3));
				ob.setEnddate(rs.getString(4));
		        ob.setApplfor(rs.getString(5));
		        ob.setDescr(rs.getString(6));
		        
			}
		}
		catch(Exception e){}
		finally
		{
			con.close();
		}
		System.out.println("Returning");
		return ob;
	}
	public static ArrayList getCustomers(DataSource ds)throws Exception
	{
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
		ArrayList al=new ArrayList();
		int i=0;
		ResultSet rs=st.executeQuery("select * from etuserdetails");
		while(rs.next()){
			UserBean ub=new UserBean();
			ub.setLogin(rs.getString(1));
			ub.setFname(rs.getString("fname"));
			ub.setDor(rs.getString("dob"));
			ub.setAddr(rs.getString("addressline1"));
			ub.setCity(rs.getString("city"));
			ub.setState(rs.getString("state"));
			ub.setEmail(rs.getString("email"));
			ub.setPhno(rs.getString("phno"));
			ub.setIdno(rs.getString("lppno"));
			al.add(ub);
			i=1;
		}
		return al;
	}
	
	public static SrcDesForm getSrcDes(DataSource ds)throws Exception
	{
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
		HashSet al=new HashSet();
		int i=0;
		ResultSet rs=st.executeQuery("select lname from location");
		SrcDesForm ub=new SrcDesForm();
		while(rs.next())		{			al.add(rs.getString(1));    	}
		ub.setSrc(al);
	 //rs=st.executeQuery("select lname from tripdetails,location where tripdetails.endpoint=location.lid");
	 //while(rs.next())
	 //{		al.add(rs.getString(1));   	 	}
		ub.setDes(al);				
		return ub;
	}
	public static ArrayList getDes(String src,String des,DataSource ds)throws Exception{
		Connection con=ds.getConnection();
		Statement st=con.createStatement();
	ArrayList al=new ArrayList();
	System.out.println("in getDes");
		int slid=0,dlid=0;
		int stid[],dtid[];
		ResultSet rs=st.executeQuery("select lid from location where lname='"+src+"'");
		while(rs.next()){			 slid=rs.getInt(1);				}
		//System.out.println("slid"+slid);
		rs.close();
		 rs=st.executeQuery("select lid from location where lname='"+des+"'");
		while(rs.next()){			 dlid=rs.getInt(1);		}
		//System.out.println("dlid"+dlid);
		rs.close();
		/*rs=st.executeQuery("select  a.trid from triphalts a,triphalts b where a.lid="+slid+" and b.lid="+dlid);
//select distinct a.trid from triphalts a,triphalts b where a.lid=1096 and b.lid=1104

		int i=0;		
		while(rs.next()){
			System.out.println("trid=="+rs.getInt(1)+"\ti==="+i);i++;}
		
		rs.close();*/
	//	System.out.println("Sizef"+rs.getFetchSize());
		rs=st.executeQuery("select  trid from triphalts  where lid="+slid);
		int i=0;
		while(rs.next()){i++;}
	stid=new int[i];
	rs.close();
	rs=st.executeQuery("select  trid from triphalts  where lid="+slid);
i=0;
	while(rs.next()){stid[i]=rs.getInt(1);i++;}
rs.close();
rs=st.executeQuery("select  trid from triphalts  where lid="+dlid);
 i=0;
while(rs.next()){i++;}
dtid=new int[i];
rs.close();
rs=st.executeQuery("select  trid from triphalts  where lid="+dlid);
i=0;
while(rs.next()){dtid[i]=rs.getInt(1);i++;}
rs.close();
int j=0,k=0;
i=0;
for (j=0;j<stid.length;j++){
	for(k=0;k<dtid.length;k++){
		if(stid[j]==dtid[k]){i++;
		//System.out.println("in if Source \t"+stid[j]+"\tDestination\t"+dtid[k]);
		}	
	}
}

int tid[]=new int[i];
i=0;
for (j=0;j<stid.length;j++){
	for(k=0;k<dtid.length;k++){
		if(stid[j]==dtid[k]){
			tid[i]=dtid[k];i++;
			}	
	}
}
//System.out.println("final trips"+tid);
	//i=0;
		//rs=st.executeQuery("select distinct a.trid from triphalts a,triphalts b where a.lid="+slid+" and b.lid="+dlid);
	//	while(rs.next()){stid[i]=rs.getInt(1);i++;}
		//rs.close();
		
		for(j=0;j<tid.length;j++)
		{
			System.out.println("stid"+tid[j]);
			rs=st.executeQuery("select * from tripdetails where trid="+tid[j]);
			BusFormTemp b1=new BusFormTemp();
			//System.out.println("before while()");
			while(rs.next())
			{
			b1.setTrid(rs.getInt(1));
			//System.out.println("before while()");
			b1.setBusno(rs.getString(2));
			//System.out.println("before while()");
			int sp=rs.getInt(3);
			//System.out.println("before while()");
			int dp=rs.getInt(4);
			//System.out.println("before while()");
			String sname="",dname="";
			System.out.println("before while()");
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select lname from location where lid="+sp);
			while(rs1.next()){sname=rs1.getString(1);}rs1.close();
			//System.out.println("rs1()"+sname);
			rs1=st1.executeQuery("select lname from location where lid="+dp);
			while(rs1.next()){dname=rs1.getString(1);}rs1.close();
			//System.out.println("rs1()"+dname);
			b1.setStartpoint(sname);
			b1.setEndpoint(dname);
			System.out.println("rs"+rs.getString(5));
			b1.setStarttime(rs.getString(5));
			b1.setEndtime(rs.getString(6));
			rs1=st1.executeQuery("select bname from busdetails where busno='"+rs.getString(2)+"'");
			while(rs1.next()){b1.setBname(rs1.getString(1));}
			rs1.close();			
			
		}//while
			al.add(b1);
		}	//for	
		//System.out.println("Sizee"+al.size());
		return al;
	}
public static  boolean booktemp(BookForm bform,DataSource ds)throws Exception{
	
Connection con=ds.getConnection();
Statement st=con.createStatement();
System.out.println(1);
ResultSet rs=st.executeQuery("select userid from etuserdetails");
ArrayList al=new ArrayList();
System.out.println(59);
while(rs.next()){	al.add(rs.getString(1));	}
bform.setUsername(al);
rs.close();
System.out.println(2);
System.out.println(bform.getBusno());
int capacity=0;
rs=st.executeQuery("select  capacity  from busdetails,bustypedetails where bustypedetails.tid=busdetails.bustype and busno='"+bform.getBusno()+"'");

while(rs.next()){capacity=rs.getInt(1);}
rs.close();
System.out.println("in setting existing capacity"+capacity);
bform.setCapacity(capacity);
System.out.println(3);
rs=st.executeQuery("select ticketid.nextval from dual");
while(rs.next()){bform.setTickid(rs.getInt(1));}
rs.close();
Collection day=new ArrayList();
day.add("1");day.add("2");		day.add("3");		day.add("4");		day.add("5");		day.add("6");		day.add("7");
day.add("8");		day.add("9");		day.add("10");		day.add("11");		day.add("12");		day.add("13");
day.add("14");day.add("15");day.add("16");day.add("17");day.add("18");day.add("19");
day.add("20");day.add("21");day.add("22");day.add("23");day.add("24");day.add("25");
day.add("26");day.add("27");day.add("28");day.add("29");day.add("30");day.add("31");
bform.setDay(day);

ArrayList month=new ArrayList();
month.add("Jan");month.add("Feb");month.add("Mar");month.add("Apr");month.add("May");month.add("Jun");
month.add("July");month.add("Aug");month.add("Sept");month.add("Oct");month.add("Nov");month.add("Dec");
bform.setMonth(month);
ArrayList year=new ArrayList();
year.add("2007");
year.add("2008");
bform.setYear(year);
ArrayList not1=new ArrayList();
not1.add("1");		not1.add("2");		not1.add("3");		not1.add("4");		not1.add("5");
not1.add("6");		not1.add("7");		not1.add("8");		not1.add("9");		not1.add("10");		not1.add("11");
not1.add("12");		not1.add("13");		not1.add("14");		not1.add("15");
bform.setNot1(not1);
System.out.println(4);
return true;
}	
public static boolean bookTemp1(BookForm bform,DataSource ds)throws Exception{
	Connection con=ds.getConnection();
	System.out.println(1);
	Statement st=con.createStatement();
	System.out.println("==========================================================");
	ResultSet rs=st.executeQuery("select email from etuserdetails where userid='"+bform.getUsername1()+"'");
	String email="";
	while(rs.next()){		email=rs.getString(1);	}
	rs.close();
	st.close();
	//System.out.println(email);
	 st=con.createStatement();
	 Statement sty=con.createStatement();
		ResultSet rsy=sty.executeQuery("select max(tid) from book_ticket where date_of_journey='"+bform.getDate()+"' and busno='"+bform.getBusno()+"'");
		int tempy=0;
		while(rsy.next()){tempy=rsy.getInt(1);}
		System.out.println("maxtid==tempy=="+tempy);
		rsy.close();
		sty.close();
		
rs=st.executeQuery("select date_of_journey,vacant,busno,tid from book_ticket");
	while(rs.next())
	{
		if(rs.getString(1).equals(bform.getDate())&& bform.getBusno().equals(rs.getString(3))&&(rs.getInt(4)==tempy))
		{	
			System.out.println("\t{in if of equal day and busno vacant=="+rs.getInt(2));
			bform.setCapacity(rs.getInt(2));		
		System.out.println("getCapacity\t}"+bform.getCapacity());	
		}
	}
rs.close();
st=con.createStatement();
rs=st.executeQuery("select tid.nextval from dual");
int tid=0;	
while(rs.next()){bform.setTickid(rs.getInt(1));		}
rs.close();
	PreparedStatement ps=con.prepareStatement("insert into book_ticket values(?,?,?,?,?,?,?,?,?)");
	ps.setString(1,bform.getBusno());
	//System.out.println(bform.getBusno());
	ps.setString(2,bform.getDate());
	//System.out.println(bform.getDate());
	System.out.println("current capacity::"+bform.getCapacity());
	ps.setInt(3,bform.getCapacity());
	//System.out.println("reserved"+bform.getNot());
	//System.out.println(59);
	ps.setInt(4,bform.getNot());
	bform.setVacant(bform.getCapacity()- bform.getNot());
	//bform.setVacant(bform.get)
	System.out.println("vacant "+bform.getVacant());
	//System.out.println(60);
	ps.setInt(5,bform.getVacant());
	//System.out.println("email===userid"+bform.getEmail());
	ps.setString(6,bform.getUsername1());
	ps.setString(7,bform.getEmail());
	//System.out.println(69);
	ps.setInt(8,bform.getTickid());
	ps.setInt(9,bform.getNot());
	//System.out.println(70);
	if(bform.getVacant()<0){
		System.out.println("Inside if of getvacant when getvacant<0");
			return false;
		}
	int i=ps.executeUpdate();
	//System.out.println("i====="+i);
	System.out.println("==========================================================");
	if(i==1)
				return true;

	return false;
	
}
public static ArrayList getblockdet(BookForm bfm,DataSource ds)throws Exception{
	Connection con=ds.getConnection();
	Statement st=con.createStatement();
	System.out.println("in getblockdet");
	ResultSet rs=st.executeQuery("select busno,tid,date_of_journey,vacant,no_of_tickets from book_ticket where userid='"+bfm.getUsername1()+"'");
	BookForm bfm1=null;
	ArrayList al=new ArrayList();
	while(rs.next()){
		bfm1=new BookForm();
		bfm1.setBusno(rs.getString(1));
		bfm1.setTickid(new Integer(rs.getString(2)).intValue());
		System.out.println("ticketif"+rs.getString(2));
		bfm1.setDate(rs.getString(3));
		bfm1.setVacant(new Integer(rs.getString(4)).intValue());
		bfm1.setNot(rs.getString(5));
			al.add(bfm1);
			}
	return al;
}
public static  boolean getuser(BookForm bfm,DataSource ds)throws Exception{
	
Connection con=ds.getConnection();
Statement st=con.createStatement();
System.out.println(1);
ResultSet rs=st.executeQuery("select userid from etuserdetails");
ArrayList al=new ArrayList();
System.out.println(59);
while(rs.next()){	al.add(rs.getString(1));	}
bfm.setUsername(al);
return true;
}
}