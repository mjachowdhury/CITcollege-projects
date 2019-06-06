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
	
public class AmbulanceDAO  extends DataObject{
		
		public AmbulanceDTO adto;
		public Connection con;
		public AmbulanceDAO()
		{
		   	con = getConnection();
		}
		//Registering staff
		
		public boolean registerAmbulance(AmbulanceDTO adto)
		{
			boolean flag=false;
			
			int aid = 0;
			String ano=adto.getAno();
			String atype=adto.getAtype();
			String alocation=adto.getAlocation();
			String aequipement=adto.getAequipement();
			
			try 
			{	
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select max(aid) from Ambulance_det");
				
				if(rs.next())
					aid = rs.getInt(1);	
				
				aid++;
				PreparedStatement pst = con.prepareStatement("insert into Ambulance_det values(?,?,?,?,?,?)");
				
				pst.setInt(1, aid);
				pst.setString(2, ano);
				pst.setString(3, atype);
				pst.setString(4, alocation);
				pst.setString(5, aequipement);
				pst.setString(6,"Y");
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
		//get Ambulance details
		
		public ArrayList getAmbulanceDetails()
		{
			ArrayList al = new ArrayList();
			AmbulanceDTO adto = null;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Ambulance_det where status='Y'");
				while(rs.next())
				{
					adto = new AmbulanceDTO();
					adto.setAid(rs.getInt(1));
					adto.setAno(rs.getString(2));
					adto.setAtype(rs.getString(3));
					adto.setAlocation(rs.getString(4));
					adto.setAequipement(rs.getString(5));
					al.add(adto);
				}
			} catch (SQLException sqlex) {
				LoggerManager.writeLogSevere(sqlex);
			}
			return al;
		}
	
	
	//update doctors details
	
	public boolean updateAmbulance(AmbulanceDTO adto)
	{
		boolean flag=true;
		
		int aid= adto.getAid();
		String ano=adto.getAno();
		String atype=adto.getAtype();
		String alocation=adto.getAlocation();
		String aequipement=adto.getAequipement();
		
		
		try 
		{
			PreparedStatement pst = con.prepareStatement("update Ambulance_det set AmbulanceNo=?,AmbulanceType=?,Location=?,Equipement=? where aid=?");
			
			pst.setString(1, ano);
			pst.setString(2, atype);
			pst.setString(3, alocation);
			pst.setString(4, aequipement);
			
			pst.setInt(5,aid);
			
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
	public boolean deleteAmbulance(int id)
	{  
		boolean flag=false;
		
		
		try{
		PreparedStatement pst = con.prepareStatement("update Ambulance_det set Status = ? where aid=?");
		pst.setString(1,"N");
		pst.setInt(2, id);
		int i = pst.executeUpdate();
		pst.clearParameters();
		
		pst = con.prepareStatement("delete from Ambulance_info where  aid=?");
		pst.setInt(1,id);
		
		int j = pst.executeUpdate();
		
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
	public AmbulanceDTO getAmbulanceDetails(String id)
	{
		AmbulanceDTO adto = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from Ambulance_det where aid =? and status=?");
			pst.setInt(1, Integer.parseInt(id));
			pst.setString(2, "Y");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				adto = new AmbulanceDTO();
				adto.setAid(rs.getInt(1));	
				adto.setAno(rs.getString(2));
				adto.setAtype(rs.getString(3));
				adto.setAlocation(rs.getString(4));
				adto.setAequipement(rs.getString(5));
		    }
		} catch (SQLException sqlex) {
			LoggerManager.writeLogSevere(sqlex);
		}
		return adto;
	}

	public ArrayList ambulanceInformation(String id)
	{
		ArrayList al=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			al = new ArrayList();
			pst = con.prepareStatement("select AmbulanceNo from Ambulance_det where aid in (select aid from Ambulance_info where pid= ? ) ");
			pst.setInt(1,Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(rs.getString(1));
			}
			
			pst.clearParameters();
			
			pst = con.prepareStatement("select FirstName,LastName from Staff_det where sid in (select did from Ambulance_info where pid= ?) and Stafftype='Driver'");
			pst.setInt(1,Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(rs.getString(1)+""+rs.getString(2));
			}
			
			pst.clearParameters();
			
			pst = con.prepareStatement("select FirstName,LastName from Staff_det where sid in (select hid from Ambulance_info where pid= ?) and Stafftype='Helper'");
			pst.setInt(1,Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(rs.getString(1)+""+rs.getString(2));
			}
			
			pst.clearParameters();
			
			pst = con.prepareStatement("select FirstName,LastName from Staff_det where sid in (select docid from Ambulance_info where pid= ?) and Stafftype='Doctor'");
			pst.setInt(1,Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(rs.getString(1)+""+rs.getString(2));
			}
			
		}catch(SQLException sqlex) {
			LoggerManager.writeLogSevere(sqlex);
		}
		return al;
	}
	public ArrayList getAvailableAmbulances(String location)
	{
		ArrayList al = new ArrayList(); 
		AmbulanceDTO adto = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from Ambulance_det where Location = ? and status=?");
			pst.setString(1, location);
			pst.setString(2, "Y");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				adto = new AmbulanceDTO();
				adto.setAid(rs.getInt(1));	
				adto.setAno(rs.getString(2));
				adto.setAtype(rs.getString(3));
				adto.setAlocation(rs.getString(4));
				adto.setAequipement(rs.getString(5));
				al.add(adto);
		    }
		} catch (SQLException sqlex) {
			LoggerManager.writeLogSevere(sqlex);
		}
		return al;
	}
	public String getAmbulanceStatus(int id)
	{
		String status ="";
		try {
			PreparedStatement pst = con.prepareStatement("select astatus from Ambulance_info where aid =?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				status = rs.getString(1);
		    }
		} catch (SQLException sqlex) {
			LoggerManager.writeLogSevere(sqlex);
		}
		return status;
	}
	
}//class


	
	
	
	
	
	
	

