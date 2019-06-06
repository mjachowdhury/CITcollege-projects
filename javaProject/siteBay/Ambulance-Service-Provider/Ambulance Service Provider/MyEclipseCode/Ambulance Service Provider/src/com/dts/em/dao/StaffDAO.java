package com.dts.em.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	import com.dts.es.dto.*;
	import com.dts.core.util.DataObject;
	import com.dts.core.util.DateWrapper;
	import com.dts.core.util.LoggerManager;
	
	public class StaffDAO  extends DataObject{
		
		public StaffDTO eventdto;
		public Connection con;
		public StaffDAO()
		{
		   	con = getConnection();
		}
		//Registering staff
		
		public boolean registerStaff(StaffDTO sdto)
		{
			boolean flag=false;
			
			int sid = 0;
			String firstname=sdto.getFirstname();
			String lastname=sdto.getLastname();
			String type=sdto.getStaffRole();
			String phone=sdto.getPhone();
			String email=sdto.getEmail();
			String location = sdto.getLocation();
			String address = sdto.getAddress();
			String workinghours = sdto.getWorkinghours();
			String jdate=DateWrapper.parseDate(sdto.getJdate());
			
			try 
			{	
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select max(sid) from Staff_det");
				
				if(rs.next())
					sid = rs.getInt(1);	
				
				sid++;
				PreparedStatement pst = con.prepareStatement("insert into Staff_det values(?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setInt(1, sid);
				pst.setString(2, firstname);
				pst.setString(3, lastname);
				pst.setString(4, type);
				pst.setString(5, phone);
				pst.setString(6, email);
				pst.setString(7, location);
				pst.setString(8, address);
				pst.setString(9, workinghours);
				pst.setString(10, jdate);
				pst.setString(11,"Y");
				int i=pst.executeUpdate();
			
				if(i!=0)
			        flag=true;
				else
					flag=false;
			} catch (SQLException sqlex) {
				LoggerManager.writeLogSevere(sqlex);
				flag=false;
			}
			System.out.println("flag"+flag);
			return flag;
		}
	    
	//get doctors details
		
			public ArrayList getStaffDetails()
			{
				ArrayList al = new ArrayList();
				StaffDTO sdto = null;
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from Staff_det where status='Y'");
					while(rs.next())
					{
						sdto = new StaffDTO();
						sdto.setSid(rs.getInt(1));
						sdto.setFirstname(rs.getString(2));
						sdto.setLastname(rs.getString(3));
						sdto.setStaffRole(rs.getString(4));
						sdto.setPhone(rs.getString(5));
						sdto.setEmail(rs.getString(6));
						sdto.setLocation(rs.getString(7));
						sdto.setAddress(rs.getString(8));
						sdto.setWorkinghours(rs.getString(9));
						sdto.setJdate1(rs.getDate(10));
						al.add(sdto);
					}
				} catch (SQLException sqlex) {
					LoggerManager.writeLogSevere(sqlex);
				}
				return al;
			}
		
		
		//update doctors details
		
		public boolean updateStaff(StaffDTO sdto)
		{
			boolean flag=true;
			
			int sid= sdto.getSid();
			String firstname=sdto.getFirstname();
			String lastname=sdto.getLastname();
			String type=sdto.getStaffRole();
			String phone=sdto.getPhone();
			String email=sdto.getEmail();
			String location = sdto.getLocation();
			String address = sdto.getAddress();
			String workinghours = sdto.getWorkinghours();
			String jdate=DateWrapper.parseDate(sdto.getJdate());
			
			
			try 
			{
				PreparedStatement pst = con.prepareStatement("update Staff_det set FirstName=?,LastName=?,Stafftype=?,phone=?,email=?,Location=?,Address=?,WorkingHours=?,JoiningDate=? where sid=?");
				
				pst.setString(1, firstname);
				pst.setString(2, lastname);
				pst.setString(3, type);
				pst.setString(4, phone);
				pst.setString(5, email);
				pst.setString(6, location);
				pst.setString(7,address);
				pst.setString(8, workinghours);
				pst.setString(9,jdate);
				pst.setInt(10, sid);
				
				int i=pst.executeUpdate();
				
				if(i!=0)
			        flag=true;
				else
					flag=false;
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
				LoggerManager.writeLogSevere(sqlex);
				flag=false;
			}
			return flag;
		}
		public boolean deleteStaff(int id)
		{  
			boolean flag=false;
			
			
			try{
			PreparedStatement pst = con.prepareStatement("update Staff_det set Status = ? where sid=? ");
			pst.setString(1,"N");
			pst.setInt(2, id);
			
			int i = pst.executeUpdate();
			
			pst.clearParameters();
		
			
			pst = con.prepareStatement("delete from Patient_info where hid=? or docid=? ");
			pst.setInt(1,id);
			pst.setInt(2,id);
			
			int k =pst.executeUpdate();
			
			if(i!=0)
			{
				flag=true;
			}else flag=false;
			}catch (SQLException sqlex) {
				sqlex.printStackTrace();
				LoggerManager.writeLogSevere(sqlex);
				flag=false;
			}
			return flag;
		}
		public StaffDTO getStaffDetail(String id)
		{
			StaffDTO sdto = null;
			try {
				PreparedStatement pst = con.prepareStatement("select * from Staff_det where sid =? and status=?");
				pst.setInt(1, Integer.parseInt(id));
				pst.setString(2, "Y");
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					sdto = new StaffDTO();
					sdto.setSid(rs.getInt(1));
					sdto.setFirstname(rs.getString(2));
					sdto.setLastname(rs.getString(3));
					sdto.setStaffRole(rs.getString(4));
					sdto.setPhone(rs.getString(5));
					sdto.setEmail(rs.getString(6));
					sdto.setLocation(rs.getString(7));
					sdto.setAddress(rs.getString(8));
					sdto.setWorkinghours(rs.getString(9));
					sdto.setJdate1(rs.getDate(10));
				}
			} catch (SQLException sqlex) {
				LoggerManager.writeLogSevere(sqlex);
			}
			return sdto;
		}
		public ArrayList getStaffRePorts(String fdate,String tdate)
		 {
			ArrayList al = new ArrayList();
			StaffDTO sdto = null;
			 try
			 {
				String pattern = "";	
				if(getProperties().getProperty("dbname").equals("access"))
				{ 
					 pattern ="#";
				} 	  
				String query = "select * from Staff_det where JoiningDate >= '"+pattern+fdate.trim()+pattern+"'and JoiningDate <= '"+pattern+tdate.trim()+pattern+"' and Status='Y'";
				PreparedStatement pst = con.prepareStatement(query); 
				
				 ResultSet rs = pst.executeQuery();
				 while(rs.next())
				 {
					 	sdto = new StaffDTO();
						sdto.setSid(rs.getInt(1));
						sdto.setFirstname(rs.getString(2));
						sdto.setLastname(rs.getString(3));
						sdto.setStaffRole(rs.getString(4));
						sdto.setPhone(rs.getString(5));
						sdto.setEmail(rs.getString(6));
						sdto.setLocation(rs.getString(7));
						sdto.setAddress(rs.getString(8));
						sdto.setWorkinghours(rs.getString(9));
						sdto.setJdate1(rs.getDate(10));
						al.add(sdto);
				 }
			 }catch(SQLException e)
			 {
				 LoggerManager.writeLogSevere(e);
			 }	 
			return al;
		 }

}//class

