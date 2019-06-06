package com.dts.em.fun;
import java.sql.*;
import com.dts.core.util.DataObject;
import com.dts.core.util.LoggerManager;
import java.util.*;
import com.dts.es.dto.*;
import java.sql.SQLException;
public class AmbulanceStatus extends DataObject
{
	
	public Connection con;
	
	public AmbulanceStatus()
	{
		con=getConnection();
	}
	public boolean registerAbulanceSatus(AmbulanceStatusDTO absdto)
	{
	   boolean flag=false;
	   PreparedStatement pst =null;
	   int pid=absdto.getPid();
	   int aid=absdto.getAid();
	   int did=absdto.getDid();
	   int hid=absdto.getHid();
	   int docid=absdto.getDocid();
	   
	   try
	   {
		   pst = con.prepareStatement("insert into Ambulance_info values(?,?,?,?,?,?)");
		   pst.setInt(1, pid);
		   pst.setInt(2,aid);
		   pst.setInt(3, did);
		   pst.setInt(4,hid);
		   pst.setInt(5, docid);
		   pst.setString(6,"S");
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
	public boolean getAmbulanceStatus(String id)
	{
	  boolean flag=false;
	  
	  try{
		  PreparedStatement pst = con.prepareStatement("select aid from ambulance_info where pid=?");
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
	public ArrayList availableAmbulances()
	{
		ArrayList alist=new ArrayList();
		try{
			PreparedStatement pst = con.prepareStatement("select aid,AmbulanceNo from ambulance_det where  status='Y' and aid not in (select aid from ambulance_info where astatus <> 'R') ");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				alist.add(rs.getInt(1));
				alist.add(rs.getString(2));
			}	
		}catch(Exception e)
		{
			LoggerManager.writeLogSevere(e);
		}
		return alist;
	}
	public ArrayList availableDrivers()
	{
		ArrayList dlist=new ArrayList();
		try{
			PreparedStatement pst = con.prepareStatement("select sid,FirstName from Staff_det where Stafftype='Driver' and Status='Y' and sid not in (select did from ambulance_info where astatus <> 'R') ");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				dlist.add(rs.getInt(1));
				dlist.add(rs.getString(2));
			}	
		}catch(Exception e)
		{
			LoggerManager.writeLogSevere(e);
		}
		return dlist;
	}
	public ArrayList availableHelpers()
	{
		ArrayList hlist=new ArrayList();
		try{
			PreparedStatement pst = con.prepareStatement("select sid,FirstName from Staff_det where Stafftype='Helper'  and status='Y' and sid not in (select hid from ambulance_info where astatus <> 'R')and sid not in (select hid from patient_info where pstatus <> 'D')");
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
			PreparedStatement pst = con.prepareStatement("select sid,FirstName from Staff_det where Stafftype='Doctor' and  status='Y' and sid not in (select docid from ambulance_info where astatus <> 'R') and sid not in (select docid from patient_info where pstatus <> 'D')");
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
	public boolean updateAmbulanceStatus(AmbulanceStatusDTO  asdto)
	{
		boolean flag=false;
		PreparedStatement pst =null;
		try
		{
			pst =  con.prepareStatement("update Ambulance_info set astatus='R' where pid = ?");
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
	// pid -- patient id
	public String ambulanceStatus(int pid)
	{
		String status="";
		PreparedStatement pst =null;
		ResultSet rs=null;
		try
		{
			pst =  con.prepareStatement("select astatus from Ambulance_info where pid = ?");
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
}
