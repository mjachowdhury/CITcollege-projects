/*     */ package com.dts.core.dao;
/*     */ 
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class AbstractDataAccessObject
/*     */ {
/*     */   public Connection con;
/*     */   static Properties props;
/*     */ 
/*     */   public Properties getProperties()
/*     */   {
/*  16 */     return props;
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties props)
/*     */   {
/*  21 */     props = props;
/*     */   }
/*     */ 
/*     */   public Connection getConnection()
/*     */   {
/*     */     try
/*     */     {
/*  28 */       Properties p = getProperties();
/*  29 */       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
/*  30 */       this.con = DriverManager.getConnection("jdbc:odbc:supermarket", "supermarket", "supermarket");
/*     */     }
/*     */     catch (ClassNotFoundException cnf)
/*     */     {
/*  36 */       LoggerManager.writeLogWarning(cnf);
/*  37 */       cnf.printStackTrace();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  41 */       LoggerManager.writeLogWarning(se);
/*  42 */       se.printStackTrace();
/*     */     }
/*  44 */     return this.con;
/*     */   }
/*     */ 
/*     */   public int getSequenceID(String tableName, String pkid)
/*     */   {
/*  50 */     int id = 0;
/*  51 */     this.con = getConnection();
/*     */     try {
/*  53 */       Statement st = this.con.createStatement();
/*  54 */       ResultSet rs = st.executeQuery("select max(" + pkid + ") from " + tableName);
/*  55 */       if (rs.next())
/*  56 */         id = rs.getInt(1);
/*  57 */       id++;
/*     */ 
/*  59 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  63 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*     */     try
/*     */     {
/*  67 */       this.con.close();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  71 */       LoggerManager.writeLogWarning(se);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  75 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*     */     try
/*     */     {
/*  79 */       this.con.close();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  83 */       LoggerManager.writeLogWarning(se);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  87 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*     */     try
/*     */     {
/*  91 */       this.con.close();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  95 */       LoggerManager.writeLogWarning(se);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  99 */       LoggerManager.writeLogWarning(e);
/*     */     }
/* 101 */     return id;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.dao.AbstractDataAccessObject
 * JD-Core Version:    0.6.2
 */