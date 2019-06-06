package com.dts.em.fun;
import java.sql.*;

import com.dts.core.util.DataObject;
import com.dts.core.util.LoggerManager;
import java.util.*;

import com.dts.es.dto.*;

import java.sql.SQLException;
import com.dts.core.util.DateWrapper;

public class HospitalizedStatus extends DataObject
{
	
	public Connection con;
	
	public HospitalizedStatus()
	{
		con=getConnection();
	}
	public boolean registerHospitalizedStatus(HospitalizedStatusDTO hsdto)
	{
	   boolean flag=false;
	   PreparedStatement pst =null;
	   
	   int pid=hsdto.getPid();
	   int docid=hsdto.getDocid();
	   int hid=hsdto.getHid();
	   String roomNo=hsdto.getRoomNo();
	   String discease=hsdto.getDiscease();
	   String medicine=hsdto.getMedicine();
	   try
	   {
		   pst = con.prepareStatement("insert into patient_info values(?,?,?,?,?,?,?)");
		   pst.setInt(1, pid);
		   pst.setInt(2, docid);
		   pst.setInt(3,hid);
		   pst.setString(4,roomNo);
		   pst.setString(5,discease);
		   pst.setString(6, medicine);
		   pst.setString(7,"A");
		   int i = pst.executeUpdate();
		   if(i!=0)
			   flag=true;
		   else 
			   flag=false;
		   
	   }catch(SQLException e)
	   {
		   LoggerManager.writeLogSevere(e);
			  flag=false;
	   }
	      
	   return flag;
	   
	}
	public boolean getHospitalizedStatus(String id)
	{
	  boolean flag=false;
	  
	  try{
		  PreparedStatement pst = con.prepareStatement("select pid,docid,hid from patient_info where pid=?");
		  pst.setInt(1, Integer.parseInt(id));
		  ResultSet rs= pst.executeQuery();
		  if(rs.next())
		  {
		     flag =true;
		  }
	     }catch(SQLException e)
	     {
		  LoggerManager.writeLogSevere(e);
		  flag=false;
	     }
	  return flag;	
	}
	
	public ArrayList availableHelpers()
	{
		ArrayList hlist=new ArrayList();
		try{
			PreparedStatement pst = con.prepareStatement("select sid,FirstName from Staff_det where Stafftype='Helper'  and status='Y' and sid not in (select hid from patient_info where pstatus <> 'D') and sid not in (select hid from Ambulance_info where astatus <> 'R') ");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				hlist.add(rs.getInt(1));
				hlist.add(rs.getString(2));
			}	
		}catch(Exception e)
		{
			LoggerManager.writeLogSevere(e);
		}
		return hlist;
	}
	public ArrayList availableDoctors()
	{
		ArrayList doclist=new ArrayList();
		try{
			PreparedStatement pst = con.prepareStatement("select sid,FirstName from Staff_det where Stafftype='Doctor' and  status='Y' and sid not in (select docid from patient_info where pstatus <> 'D') and sid not in (select docid from Ambulance_info where astatus <> 'R')");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				doclist.add(rs.getInt(1));
				doclist.add(rs.getString(2));
			}	
		}catch(Exception e)
		{
			LoggerManager.writeLogSevere(e);
		}
		return doclist;
	}
	/*public boolean updateHospitalizedStatus(HospitalizedStatusDTO  asdto)
	{
		boolean flag=false;
		PreparedStatement pst =null;
		try
		{
			pst =  con.prepareStatement("update Hospitalized_info set astatus='R' where pid = ?");
			pst.setInt(1, asdto.getPid());
			int i=pst.executeUpdate();
			if(i!=0)
			flag=true;
			else 
			flag=false;
					
		}catch(SQLException sqle)
		{
			LoggerManager.writeLogSevere(sqle);
			flag=false;
		}
		return flag;
	}
	*/
	// pid -- patient id
	public String hospitalizedStatus(int pid)
	{
		String status="";
		PreparedStatement pst =null;
		ResultSet rs=null;
		try
		{
			pst =  con.prepareStatement("select pstatus from patient_info where pid = ?");
			pst.setInt(1,pid);
			rs = pst.executeQuery();
		    while(rs.next())
		    {
		    	status = rs.getString(1);
		    }	
		}catch(SQLException e)
		{
			LoggerManager.writeLogSevere(e);
		}	
		return status;
	}
	public boolean registerDischargeInfo(PatientDischargeDTO pddto)
	{
		boolean flag=false;
		boolean flag1=false;
		PreparedStatement pst =null;
		
		int pid=pddto.getPid();
		String state=pddto.getState();
		String date = DateWrapper.parseDate(pddto.getDischargeDate());
		try
		{
			pst = con.prepareStatement("insert into patient_discharge_info values(?,?,?)");
			pst.setInt(1,pid);
			pst.setString(2,state);
			pst.setString(3,date);
			
			int i = pst.executeUpdate();
			if(i!=0)
				flag=true;
			else 
				flag=false;
			
		    pst.clearParameters();
		    
		    pst = con.prepareStatement("update patient_info set pstatus='D' where pid=?");
		    pst.setInt(1,pid);
		    int j = pst.executeUpdate();
			if(j!=0)
				flag1=true;
			else 
				flag1=false;
		    	
		}catch(SQLException e)
		{
			LoggerManager.writeLogSevere(e);
			flag=false;
		}
		return (flag && flag1);
	}
	public ArrayList getDischargeReports(String fromdate,String todate)
	{
		ArrayList al = new ArrayList();
		PatientDischargeDTO pddto = null;
		 try
		 {
			String pattern = "";	
			if(getProperties().getProperty("dbname").equals("access"))
			{ 
				 pattern ="#";
			} 	  

			String query = "select * from patient_discharge_info where dateofdischarge >= '"+pattern+fromdate.trim()+pattern+"'and dateofdischarge <= '"+pattern+todate.trim()+pattern+"'";

			System.out.println(query);

			PreparedStatement pst = con.prepareStatement(query); 
			
			 ResultSet rs = pst.executeQuery();
			 while(rs.next())
			 {
				 	pddto = new PatientDischargeDTO();
				 	pddto.setPid(rs.getInt(1));
					pddto.setState(rs.getString(2));
					pddto.setDischargeDate(rs.getString(3));
					al.add(pddto);
			 }
		 }catch(SQLException e)
		 {
			 LoggerManager.writeLogSevere(e);
		 }	 
	   return al;	
	}
	
	
}
