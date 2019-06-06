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
	
	public class PatientDAO   extends DataObject{
		
		public PatientDTO eventdto;
		public Connection con;
		public PatientDAO()
		{
		   	con = getConnection();
		}
		//Registering Patients
		
		public boolean registerPatients(PatientDTO pdto)
		{
			boolean flag=false;
			
			int pid = 0;
			String firstname=pdto.getFirstname();
			String lastname=pdto.getLastname();
			String dob=DateWrapper.parseDate(pdto.getDob());
			String location = pdto.getLocation();
			String city = pdto.getCity();
			String state =pdto.getState();
			String address = pdto.getAddress();
			String phone= pdto.getPhone();
			String height=pdto.getHeight();
			String weight=pdto.getWeight();
			String jdate = DateWrapper.parseDate(pdto.getJdate());
			String type=pdto.getType();
			//String status ="N"; 
			try 
			{	
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select max(pid) from Pateint_det");
				
				if(rs.next())
				    pid = rs.getInt(1);	
				pid++;
								
				PreparedStatement pst = con.prepareStatement("insert into Pateint_det values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setInt(1, pid);
				
				pst.setString(2, firstname);
				pst.setString(3, lastname);
				pst.setString(4, dob);
				pst.setString(5, location);
				pst.setString(6, city);
				pst.setString(7, state);
				pst.setString(8, address);
				pst.setString(9, phone);
				pst.setString(10,height);
				pst.setString(11,weight);
				pst.setString(12,jdate);
				pst.setString(13, type);
				
				pst.setString(14,"Y");
				
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
	    
	//get Patients details
		
			public ArrayList getPatientDetails()
			{
				ArrayList al = new ArrayList();
				PatientDTO pdto = null;
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from Pateint_det where status='Y' and pid not in(select pid from patient_discharge_info)");
					while(rs.next())
					{
						pdto = new PatientDTO();
						pdto.setPid(rs.getInt(1));
						pdto.setFirstname(rs.getString(2));
						pdto.setLastname(rs.getString(3));
						pdto.setDob1(rs.getDate(4));
						pdto.setLocation(rs.getString(5));
						pdto.setCity(rs.getString(6));
						pdto.setState(rs.getString(7));
						pdto.setAddress(rs.getString(8));
						pdto.setPhone(rs.getString(9));
						pdto.setHeight(rs.getString(10));
						pdto.setWeight(rs.getString(11));
						pdto.setJdate1(rs.getDate(12));
						pdto.setType(rs.getString(13));
						al.add(pdto);
					}
				} catch (SQLException sqlex) {
					LoggerManager.writeLogSevere(sqlex);
				}
				return al;
			}
		
		
		//update Patients details
		
		public boolean updatePatients(PatientDTO pdto)
		{
			boolean flag=true;
			
			int pid= pdto.getPid();
			String firstname=pdto.getFirstname();
			String lastname=pdto.getLastname();
			String dob=DateWrapper.parseDate(pdto.getDob());
			String location = pdto.getLocation();
			String city = pdto.getCity();
			String state =pdto.getState();
			String address = pdto.getAddress();
			String phone= pdto.getPhone();
			String height=pdto.getHeight();
			String weight=pdto.getWeight();
			String jdate = DateWrapper.parseDate(pdto.getJdate());
			String type=pdto.getType();
			
			try 
			{
				PreparedStatement pst = con.prepareStatement("update Pateint_det set FirstName=?,LastName=?,BirthDate=?,Location=?,City=?,State=?,Address=?,Phone=?,Height=?,weight=?,JoinDate=?,Type=? where pid=?");
					
				pst.setString(1, firstname);
				pst.setString(2, lastname);
				pst.setString(3,dob);
				pst.setString(4, location);
				pst.setString(5, city);
				pst.setString(6, state);
				pst.setString(7, address);
				pst.setString(8,phone);
				pst.setString(9,height);
				pst.setString(10, weight);
				pst.setString(11,jdate);
				pst.setString(12, type);
				pst.setInt(13, pid);
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
		
		public boolean deletePatients(int id)
		{  
			boolean flag=false;
			
			
			try{
			PreparedStatement pst = con.prepareStatement("update Pateint_det set Status =? where pid=?");
			pst.setString(1,"N");
			pst.setInt(2, id);
			
			int i =pst.executeUpdate();
			/* pst.addBatch();
			pst.clearParameters();
			
			pst = con.prepareStatement("delete from Ambulance_info where  pid=?");
			pst.setInt(1,id);
			    
			pst.addBatch();
			pst.clearParameters();
			    
			pst = con.prepareStatement("delete from Patient_info where  pid=?");
			pst.setInt(1,id);
			
			pst.addBatch();
			int pstvalues[]=pst.executeBatch();   
			
			*/
			pst.clearParameters();
			
			pst = con.prepareStatement("delete from Ambulance_info where  pid=?");
			pst.setInt(1,id);
			
			int j =pst.executeUpdate();
			pst.clearParameters();
			
			pst = con.prepareStatement("delete from Patient_info where  pid=?");
			pst.setInt(1,id);
			
			int k =pst.executeUpdate();
			
			if(i!=0)
			{
				flag=true;
				con.commit();
			}
			else{
				flag=false;
			    con.rollback();
			}
			}catch (SQLException sqlex) {
				sqlex.printStackTrace();
				LoggerManager.writeLogSevere(sqlex);
				flag=false;
			}
			return flag;
		}
		public PatientDTO getPatientDetails(String id)
		{
			PatientDTO pdto = null;
			try {
				PreparedStatement pst = con.prepareStatement("select * from Pateint_det where pid =?");
				pst.setInt(1, Integer.parseInt(id));
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					pdto = new PatientDTO();
					pdto.setFirstname(rs.getString(2));
					pdto.setLastname(rs.getString(3));
					pdto.setDob1(rs.getDate(4));
					pdto.setLocation(rs.getString(5));
					pdto.setCity(rs.getString(6));
					pdto.setState(rs.getString(7));
					pdto.setAddress(rs.getString(8));
					pdto.setPhone(rs.getString(9));
					pdto.setHeight(rs.getString(10));
					pdto.setWeight(rs.getString(11));
					pdto.setJdate1(rs.getDate(12));
					pdto.setType(rs.getString(13));
					
				}
			} catch (SQLException sqlex) {
				LoggerManager.writeLogSevere(sqlex);
			}
			return pdto;
		}
	 public String getFirstLastName(String id)
	 {
		 String firstLastName =null;
		 try
		 {
			 PreparedStatement pst = con.prepareStatement("select FirstName,LastName from Pateint_det where pid =?");
			 pst.setInt(1,Integer.parseInt(id));
			 ResultSet rs = pst.executeQuery();
			 while(rs.next())
			 {
				 firstLastName = rs.getString(1)+" "+rs.getString(2);
			 }
		 }catch(SQLException e)
		 {
			 LoggerManager.writeLogSevere(e);
		 }	 
		 return firstLastName;
	 }
	 public ArrayList getPatientRePorts(String fdate,String tdate)
	 {
		ArrayList al = new ArrayList();
		PatientDTO pdto = null;
		 try
		 {
			String pattern = "";	
			if(getProperties().getProperty("dbname").equals("access"))
			{ 
				 pattern ="#";
			} 	  
			String query = "select * from Pateint_det where JoinDate >= '"+pattern+fdate.trim()+pattern+"'and JoinDate <= '"+pattern+tdate.trim()+pattern+"' and Status='Y'";
			PreparedStatement pst = con.prepareStatement(query); 
			
			 ResultSet rs = pst.executeQuery();
			 while(rs.next())
			 {
				 	pdto = new PatientDTO();
				 	pdto.setPid(rs.getInt(1));
					pdto.setFirstname(rs.getString(2));
					pdto.setLastname(rs.getString(3));
					pdto.setDob1(rs.getDate(4));
					pdto.setLocation(rs.getString(5));
					pdto.setCity(rs.getString(6));
					pdto.setState(rs.getString(7));
					pdto.setAddress(rs.getString(8));
					pdto.setPhone(rs.getString(9));
					pdto.setHeight(rs.getString(10));
					pdto.setWeight(rs.getString(11));
					pdto.setJdate1(rs.getDate(12));
					pdto.setType(rs.getString(13));
					al.add(pdto);
			 }
		 }catch(SQLException e)
		 {
			 LoggerManager.writeLogSevere(e);
		 }	 
		return al;
	 }
}//class



