/*
 * ProfileDAO.java
 *
 * 
 * 
 */

package com.dts.dae.dao;

import com.dts.dae.dto.RegistrationBean;
import com.dts.core.db.DbInterface;
import com.dts.core.util.DataObject;
import com.dts.core.util.DateWrapper;
import com.dts.core.util.LoggerManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 *
 * @author 
 */
public class ProfileDAO extends DataObject{
    
    static Connection con;
    public DbInterface db;

    private boolean flag;
    /** Creates a new instance of ProfileDAO */
    public ProfileDAO() 
    {
           //getting Database Connection
           con=getConnection();  
    }
    
       
    //User Registration
    public boolean registration(RegistrationBean regbean)
    {
        String loginid=regbean.getLoginID();
        String password=regbean.getPassword();
        String firstname=regbean.getFirstName();
        String lastname=regbean.getLastName();
        String logintype=regbean.getLoginType();
        int status=regbean.getStatus();
        //String regdate=regbean.getRegDate();
        int secretquest=regbean.getSecretQuestionID();
        String ownsecretquest=regbean.getOwnSecretQuestion();
        String secretans=regbean.getSecretAnswer();
        int firstlogin=regbean.getFirstLogin();
        String bdate=DateWrapper.parseDate(regbean.getBirthDate());
        String city=regbean.getCity();
        String state=regbean.getState();
        String country=regbean.getCountry();
        String locale=regbean.getLocale();
        String passmdate=regbean.getPasswordModifiedDate();
        String profilemdate=regbean.getProfileModifiedDate();
        
        try 
        {
           con.setAutoCommit(false);
           PreparedStatement pst=null;
           Statement st=con.createStatement();
           int i=0;
           if(secretquest==0)
           {
                ResultSet rs=st.executeQuery("select (max(question_id))+1 from question_base");
                if(rs.next())
                    secretquest=rs.getInt(1);
                pst = con.prepareStatement("INSERT INTO question_base VALUES(?,?)");
        
                pst.setInt(1,secretquest);
                pst.setString(2,ownsecretquest);
        
                pst.executeUpdate();
           }
           String newdate=DateWrapper.parseDate(new Date());
            
           //st.executeUpdate("insert into LOGIN_DETAILS values('"+loginid+"','"+password+"','"+firstname+"','"+lastname+"','"+logintype+"',"+status+",'"+newdate+"',"+secretquest+",'"+secretans+"',"+firstlogin+",'"+newdate+"')");
           pst=con.prepareStatement("insert into LOGIN_DETAILS values(?,?,?,?,?,?,?,?,?,?,?)");
                        
           pst.setString(1,loginid);
           pst.setString(2,password);
           pst.setString(3,firstname);
           pst.setString(4,lastname);
           pst.setString(5,logintype);
           pst.setInt(6,status);
           pst.setString(7,newdate);
           pst.setInt(8,secretquest);
           pst.setString(9,secretans);
           pst.setInt(10,firstlogin);
           pst.setString(11,newdate);
           i=pst.executeUpdate();
           if(i==1)
           {
                pst=con.prepareStatement("insert into LOGIN_PROFILE values(?,?,?,?,?,?,?)");
                pst.setString(1,loginid);
                pst.setString(2,bdate);
                pst.setString(3,city);
                pst.setString(4,state);
                pst.setString(5,country);
                pst.setString(6,locale);
                pst.setString(7,newdate);
                i=pst.executeUpdate();
            }
            if(i==1)
            {
                flag=true;
                con.commit();
            }
            else
            {
                flag=false;
                con.rollback();
            }      
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException sex) 
            {
                sex.printStackTrace();
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
                se.printStackTrace();
            }
        }
        return flag;
    }
    
    //Getting profile
    public RegistrationBean getProfile(String loginname)
    {
        RegistrationBean rb=null;
        try
        {
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select ld.first_name,ld.last_name,lp.birthdate,lp.city,lp.state,lp.country from login_details ld,login_profile lp where ld.loginname=lp.loginid and ld.loginname='"+loginname+"'");
           if(rs.next())
           {
        	   rb=new RegistrationBean();
        	   rb.setLoginID(loginname);
        	   rb.setFirstName(rs.getString(1));
        	   rb.setLastName(rs.getString(2));
        	   rb.setBirthDate1(rs.getDate(3));
        	   rb.setCity(rs.getString(4));
        	   rb.setState(rs.getString(5));
        	   rb.setCountry(rs.getString(6));
           }
        }
        catch(Exception e)
        {
        	LoggerManager.writeLogSevere(e);
        }
        return rb;
    } 
    
    // Modify Profile
    public boolean modifyProfile(RegistrationBean regbean)
    {
        String loginid=regbean.getLoginID();
        String bdate=DateWrapper.parseDate(regbean.getBirthDate());
        String city=regbean.getCity();
        String state=regbean.getState();
        String country=regbean.getCountry();
        
        String firstname=regbean.getFirstName();
        String lastname=regbean.getLastName();
        
        
        try 
        {
            con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement("UPDATE login_profile SET birthdate=?,city=?,state=?,country=?,profilemodifieddate=? WHERE loginid=?");
            PreparedStatement pst1=con.prepareStatement("UPDATE login_details SET first_name=?,last_name=? WHERE loginname=?");
            
            pst.setString(1,bdate);
            pst.setString(2,city);
            pst.setString(3,state);
            pst.setString(4,country);
            pst.setString(5, DateWrapper.parseDate(new Date()));
            pst.setString(6,loginid);
           
            pst1.setString(1, firstname);
            pst1.setString(2, lastname);
            pst1.setString(3,loginid);
            
            int i=pst.executeUpdate();
            
            if(i!=0)
            {
            	i=pst1.executeUpdate();
            	if(i!=0)
            	{
                flag=true;
                con.commit();
            	}
            	else
            	{
            		flag=false;
            		con.rollback();
            	}
            }
            else
            {
                flag=false;
                con.rollback();
            }
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
            LoggerManager.writeLogSevere(ex);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
                LoggerManager.writeLogSevere(se);
            }
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        return flag;
    }
    
    public boolean changeAccountStatus(String loginid,int status)
    {
        try 
        {
            con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement("UPDATE login_details SET login_status=? WHERE loginid=?");
            
            pst.setInt(1,status);
            pst.setString(2,loginid);
            int i=pst.executeUpdate();

            if(i==1)
            {
                flag=true;
                con.commit();
            }
            else
            {
                flag=false;
                con.rollback();
            }
        } 
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        return flag;
    }
}
