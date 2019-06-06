/*    */ package com.dts.dae.dao;
/*    */ 
/*    */ import com.dts.core.dao.AbstractDataAccessObject;
/*    */ import com.dts.core.util.LoggerManager;
/*    */ import com.dts.dae.model.CustomerProfile;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ 
/*    */ public class SecurityDAO extends AbstractDataAccessObject
/*    */ {
/*    */   public SecurityDAO()
/*    */   {
/* 13 */     getConnection();
/*    */   }
/*    */ 
/*    */   public String recoverPasswordByExistQuestion(CustomerProfile cp)
/*    */   {
/* 22 */     String password = "";
/* 23 */     String loginname = cp.getLoginname();
/* 24 */     String secretquestion = cp.getOwnSecretQuestion();
/* 25 */     String secretanswer = cp.getSecretAnswer();
/*    */     try
/*    */     {
/* 28 */       PreparedStatement pst = this.con.prepareStatement("SELECT password FROM customerdetails  WHERE loginname=? and secretquestion=? and secretanswer=?");
/* 29 */       pst.setString(1, loginname);
/* 30 */       pst.setString(2, secretquestion);
/* 31 */       pst.setString(3, secretanswer);
/* 32 */       ResultSet rs = pst.executeQuery();
/* 33 */       if (rs.next())
/* 34 */         password = rs.getString(1);
/*    */       else
/* 36 */         password = "";
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 40 */       LoggerManager.writeLogSevere(e);
/* 41 */       password = "";
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
/*    */     try
/*    */     {
/* 61 */       this.con.close();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 65 */       e.printStackTrace();
/*    */     }
/* 67 */     return password;
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.SecurityDAO
 * JD-Core Version:    0.6.2
 */