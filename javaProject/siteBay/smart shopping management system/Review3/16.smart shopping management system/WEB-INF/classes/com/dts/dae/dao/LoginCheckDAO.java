/*    */ package com.dts.dae.dao;
/*    */ 
/*    */ import com.dts.core.dao.AbstractDataAccessObject;
/*    */ import com.dts.core.util.LoggerManager;
/*    */ import com.dts.dae.model.CustomerProfile;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class LoginCheckDAO extends AbstractDataAccessObject
/*    */ {
/*    */   private boolean flag;
/*    */ 
/*    */   public LoginCheckDAO()
/*    */   {
/* 13 */     getConnection();
/*    */   }
/*    */ 
/*    */   public boolean loginCheck(String username, String password)
/*    */   {
/* 19 */     boolean flag = false;
/*    */     try
/*    */     {
/* 22 */       Statement st = this.con.createStatement();
/* 23 */       for (ResultSet rs = st.executeQuery("select * from customerdetails where status='Y' and loginname='" + username + "' and password='" + password + "'"); rs.next(); )
/*    */       {
/* 25 */         CustomerProfile cp = new CustomerProfile();
/* 26 */         cp.setLoginname(rs.getString(12));
/* 27 */         flag = true;
/*    */       }
/*    */ 
/*    */     }
/*    */     catch (SQLException sqlex)
/*    */     {
/* 33 */       LoggerManager.writeLogSevere(sqlex);
/*    */     }
/*    */     try
/*    */     {
/* 37 */       this.con.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 41 */       e.printStackTrace();
/*    */     }
/*    */     try
/*    */     {
/* 45 */       this.con.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 49 */       e.printStackTrace();
/*    */     }
/*    */     try
/*    */     {
/* 53 */       this.con.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 57 */       e.printStackTrace();
/*    */     }
/* 59 */     return flag;
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.LoginCheckDAO
 * JD-Core Version:    0.6.2
 */