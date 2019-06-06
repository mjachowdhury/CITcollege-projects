/*     */ package com.dts.dae.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.CoreHash;
/*     */ import com.dts.core.util.DateWrapper;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.dae.model.StaffBean;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class StaffDAO extends AbstractDataAccessObject
/*     */ {
/*     */   private boolean flag;
/*     */ 
/*     */   public StaffDAO()
/*     */   {
/*  14 */     getConnection();
/*     */   }
/*     */ 
/*     */   public boolean insertStaff(StaffBean staffbean)
/*     */   {
/*  27 */     int empid = 0;
/*  28 */     String empname = staffbean.getEmpName();
/*  29 */     String designation = staffbean.getDesignatin();
/*  30 */     String salary = staffbean.getSalary();
/*  31 */     String jdate = staffbean.getDoj();
/*  32 */     String accno = staffbean.getAccno();
/*  33 */     String contactaddress = staffbean.getContactAddress();
/*  34 */     String phoneno = staffbean.getPhoneNo();
/*     */     try {
/*  36 */       this.con.setAutoCommit(false);
/*  37 */       PreparedStatement pst = null;
/*  38 */       Statement st = this.con.createStatement();
/*  39 */       int i = 0;
/*  40 */       String newdate = DateWrapper.parseDate(new Date());
/*  41 */       ResultSet rs = st.executeQuery("select max(empid) from employeedetails");
/*  42 */       if (rs.next())
/*  43 */         empid = rs.getInt(1);
/*  44 */       empid++;
/*  45 */       pst = this.con.prepareStatement("insert into employeedetails values(?,?,?,?,?,?,?,?,?)");
/*  46 */       pst.setInt(1, empid);
/*  47 */       pst.setString(2, empname);
/*  48 */       pst.setString(3, designation);
/*  49 */       pst.setString(4, salary);
/*  50 */       pst.setString(5, jdate);
/*  51 */       pst.setString(6, accno);
/*  52 */       pst.setString(7, contactaddress);
/*  53 */       pst.setString(8, phoneno);
/*  54 */       pst.setString(9, "Y");
/*  55 */       i = pst.executeUpdate();
/*  56 */       if (i == 1)
/*     */       {
/*  58 */         this.flag = true;
/*  59 */         this.con.commit();
/*     */       }
/*     */       else {
/*  62 */         this.flag = false;
/*  63 */         this.con.rollback();
/*     */       }
/*  65 */       this.flag = false;
/*     */ 
/*  67 */       this.con.rollback();
/*     */     }
/*     */     catch (SQLException sex)
/*     */     {
/*  71 */       sex.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/*  75 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  79 */       e.printStackTrace();
/*     */     }
/*  81 */     this.flag = false;
/*     */     try
/*     */     {
/*  84 */       this.con.rollback();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  88 */       se.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/*  92 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 100 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 104 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 108 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 112 */       e.printStackTrace();
/*     */     }
/* 114 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public CoreHash getStaffDetails()
/*     */   {
/* 120 */     CoreHash ch = new CoreHash();
/* 121 */     ch.clear();
/* 122 */     int unique = 0;
/* 123 */     StaffBean sdto = null;
/*     */     try
/*     */     {
/* 126 */       Statement st = this.con.createStatement();
/* 127 */       for (ResultSet rs = st.executeQuery("select * from employeedetails where status='Y'"); rs.next(); ch.put(unique, sdto))
/*     */       {
/* 129 */         unique = rs.getInt(1);
/* 130 */         sdto = new StaffBean();
/* 131 */         sdto.setEmpId(unique);
/* 132 */         sdto.setEmpName(rs.getString(2));
/* 133 */         sdto.setDesignatin(rs.getString(3));
/* 134 */         sdto.setSalary(rs.getString(4));
/* 135 */         sdto.setDoj(rs.getString(5));
/* 136 */         sdto.setAccno(rs.getString(6));
/* 137 */         sdto.setContactAddress(rs.getString(7));
/* 138 */         sdto.setPhoneNo(rs.getString(8));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 144 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 148 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 152 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 156 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 160 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 164 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 168 */       e.printStackTrace();
/*     */     }
/* 170 */     return ch;
/*     */   }
/*     */ 
/*     */   public boolean deleteStaff(int empid)
/*     */   {
/* 175 */     boolean flag = false;
/*     */     try
/*     */     {
/* 178 */       PreparedStatement pst = this.con.prepareStatement("update employeedetails set Status =? where empid=?");
/* 179 */       pst.setString(1, "N");
/* 180 */       pst.setInt(2, empid);
/* 181 */       int i = pst.executeUpdate();
/* 182 */       if (i != 0)
/*     */       {
/* 184 */         flag = true;
/* 185 */         this.con.commit();
/*     */       }
/*     */       else {
/* 188 */         flag = false;
/* 189 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 194 */       sqlex.printStackTrace();
/* 195 */       LoggerManager.writeLogSevere(sqlex);
/* 196 */       flag = false;
/*     */     }
/*     */     try
/*     */     {
/* 200 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 204 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 208 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 212 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 216 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 220 */       e.printStackTrace();
/*     */     }
/* 222 */     return flag;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.StaffDAO
 * JD-Core Version:    0.6.2
 */