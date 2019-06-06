/*     */ package com.dts.project.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.CoreHash;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.project.model.Sample;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class SampleDAO extends AbstractDataAccessObject
/*     */ {
/*     */   Connection con;
/*     */   Sample sample;
/*     */ 
/*     */   public boolean addSample(Sample aSample)
/*     */   {
/*  19 */     boolean flag = false;
/*     */     try
/*     */     {
/*  22 */       this.con = getConnection();
/*  23 */       PreparedStatement st = this.con.prepareStatement("insert into test values(?,?,?,?)");
/*  24 */       st.setInt(1, aSample.getSno());
/*  25 */       st.setString(2, aSample.getSname());
/*  26 */       st.setInt(3, aSample.getAge());
/*  27 */       st.setString(4, aSample.getAddress());
/*  28 */       int i = st.executeUpdate();
/*  29 */       this.con.commit();
/*  30 */       if (i > 0)
/*  31 */         flag = true;
/*  32 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  36 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*  38 */     return flag;
/*     */   }
/*     */ 
/*     */   public void updateSample(Sample sample1)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean deleteSample(String s)
/*     */   {
/*  47 */     boolean flag = false;
/*     */     try
/*     */     {
/*  50 */       this.con = getConnection();
/*  51 */       PreparedStatement st = this.con.prepareStatement("delete from test where sno=?");
/*  52 */       st.setString(1, s);
/*  53 */       int i = st.executeUpdate();
/*  54 */       if (i > 0)
/*  55 */         flag = true;
/*  56 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  60 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*  62 */     return flag;
/*     */   }
/*     */ 
/*     */   public Sample viewSample(String s)
/*     */   {
/*     */     try
/*     */     {
/*  69 */       this.con = getConnection();
/*  70 */       Statement st = this.con.createStatement();
/*  71 */       for (ResultSet rs = st.executeQuery("select * from test where sno = " + s); rs.next(); this.sample.setAddress(rs.getString(4)))
/*     */       {
/*  73 */         this.sample = new Sample();
/*  74 */         this.sample.setSno(rs.getInt(1));
/*  75 */         this.sample.setSname(rs.getString(2));
/*  76 */         this.sample.setAge(rs.getInt(3));
/*     */       }
/*     */ 
/*  79 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  83 */       LoggerManager.writeLogWarning(e);
/*     */     }
/*  85 */     return this.sample;
/*     */   }
/*     */ 
/*     */   public CoreHash listSample()
/*     */   {
/*  90 */     System.out.println("in list sample");
/*  91 */     CoreHash aCoreHash = new CoreHash();
/*  92 */     aCoreHash.clear();
/*  93 */     System.out.println("aCoreHash--" + aCoreHash.isEmpty());
/*     */     try
/*     */     {
/*  96 */       this.con = getConnection();
/*  97 */       Statement st = this.con.createStatement();
/*     */       int sno;
/*  99 */       for (ResultSet rs = st.executeQuery("select * from test"); rs.next(); aCoreHash.put(new Integer(sno), this.sample))
/*     */       {
/* 101 */         sno = rs.getInt(1);
/* 102 */         this.sample = new Sample();
/* 103 */         this.sample.setSno(sno);
/* 104 */         this.sample.setSname(rs.getString(2));
/* 105 */         this.sample.setAge(rs.getInt(3));
/* 106 */         this.sample.setAddress(rs.getString(4));
/*     */       }
/*     */ 
/* 109 */       this.con.close();
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 113 */       LoggerManager.writeLogWarning(e);
/*     */     }
/* 115 */     return aCoreHash;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.project.dao.SampleDAO
 * JD-Core Version:    0.6.2
 */