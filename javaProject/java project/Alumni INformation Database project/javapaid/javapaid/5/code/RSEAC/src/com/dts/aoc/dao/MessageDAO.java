package com.dts.aoc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dts.aoc.dto.MessageDTO;
import com.dts.core.util.DataObject;
import com.dts.core.util.LoggerManager;

public class MessageDAO extends DataObject{
	
	Connection con;
	public MessageDAO()
	{
		con=getConnection();
	}
 
	//send message
	
	public boolean sendMessage(MessageDTO msgdto)
	{
		boolean flag = false;
	    try
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select max(messageid) from ALUMNI_MESSAGES");
	    	int messageid=0;
	    	if(rs.next())
	    	     messageid = rs.getInt(1);
	        messageid++;
	        
	    	PreparedStatement pst = con.prepareStatement("insert into ALUMNI_MESSAGES values(?,?,?,?,?,?,1,1)");
	    	
	    	pst.setInt(1, messageid);
	    	pst.setString(2, msgdto.getFrom());
	    	pst.setString(3, msgdto.getTo());
	    	pst.setString(4, msgdto.getSenddate());
	    	pst.setString(5, msgdto.getSubject());
	    	pst.setString(6, msgdto.getMessage());
	    	
	    	int i = pst.executeUpdate();
	    	
	    	if(i!=0)
	    	   flag = true;
	    	else
	    		flag= false;
	    }
	    catch(SQLException sqx)
	    {
	    	flag = false;
	    	sqx.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(sqx);
	    }
	    catch(Exception e)
	    {
	    	flag = false;
	    	e.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(e);
	    }
	    return flag;
	}
	
	//View Inbox
	
	public ArrayList<MessageDTO> viewInbox(String loginname)
	{
		ArrayList<MessageDTO> al = new ArrayList<MessageDTO>();
		MessageDTO messagedto;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select messageid,fromname,subject,Senddate from ALUMNI_MESSAGES where toname='"+loginname+"' and Receiverstatus=1");
			
			while(rs.next())
			{
				messagedto = new MessageDTO();
				messagedto.setMessageid(rs.getInt(1));
				messagedto.setFrom(rs.getString(2));
				messagedto.setSubject(rs.getString(3));
				messagedto.setSenddate1(rs.getDate(4));
				
				al.add(messagedto);
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return al;
	}
	
//View Outbox
	
	public ArrayList<MessageDTO> viewOutbox(String loginname)
	{
		ArrayList<MessageDTO> al = new ArrayList<MessageDTO>();
		MessageDTO messagedto;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select messageid,toname,subject,Senddate from ALUMNI_MESSAGES where fromname='"+loginname+"' and senderstatus=1");
			
			while(rs.next())
			{
				messagedto = new MessageDTO();
				messagedto.setMessageid(rs.getInt(1));
				messagedto.setTo(rs.getString(2));
				messagedto.setSubject(rs.getString(3));
				messagedto.setSenddate1(rs.getDate(4));
				
				al.add(messagedto);
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return al;
	}
	
//View Message
	
	public MessageDTO viewMessage(int messageid)
	{
		MessageDTO messagedto = new MessageDTO();
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select fromname,toname,senddate,subject,message from ALUMNI_MESSAGES where messageid="+messageid);
			
			if(rs.next())
			{				
				messagedto.setFrom(rs.getString(1));
				messagedto.setTo(rs.getString(2));
				messagedto.setSenddate1(rs.getDate(3));
				messagedto.setSubject(rs.getString(4));
				messagedto.setMessage(rs.getString(5));
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return messagedto;
	}
	
//Change Status of message
	
	public boolean changeMessageStatus(int messageid,String field)
	{
		boolean flag = false;
		try
		{
			Statement st = con.createStatement();
			int i = st.executeUpdate("update ALUMNI_MESSAGES set "+field+"=0 where messageid="+messageid);
			
			if(i!=0)
			{				
				flag = true;
			}
		}
		catch(SQLException sqlex)
		{
			flag = false;
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			flag = false;
			LoggerManager.writeLogSevere(ex);
		}
		return flag;
	}
}
