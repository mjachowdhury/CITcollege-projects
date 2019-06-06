/*     */ package com.dts.dae.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.CoreHash;
/*     */ import com.dts.core.util.DateWrapper;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.dae.model.GenerateBillBean;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class GenerateBillDAO extends AbstractDataAccessObject
/*     */ {
/*     */   private boolean flag;
/*     */   private boolean flag1;
/*     */ 
/*     */   public GenerateBillDAO()
/*     */   {
/*  15 */     getConnection();
/*     */   }
/*     */ 
/*     */   public CoreHash generateBill(GenerateBillBean gbb)
/*     */   {
/*  23 */     CoreHash ch = new CoreHash();
/*  24 */     ch.clear();
/*  25 */     String unique = "";
/*  26 */     Date todaydate = new Date();
/*  27 */     String date = DateWrapper.parseDate(todaydate);
/*  28 */     String custname = gbb.getCustomername();
/*     */     try
/*     */     {
/*  31 */       Statement st = this.con.createStatement();
/*     */ 
/*  33 */       for (ResultSet rs = st.executeQuery("select pl.productname,pl.price,sc.quantity from productlist pl,shoppingcart sc where pl.status='Y' and pl.productname=sc.prodname and sc.purchasedate='" + date + "' and sc.paid_status='No' and sc.custname='" + custname + "'"); rs.next(); ch.put(unique, gbb))
/*     */       {
/*  35 */         unique = rs.getString(1);
/*  36 */         gbb = new GenerateBillBean();
/*  37 */         gbb.setProductname(unique);
/*  38 */         gbb.setPrice(rs.getString(2));
/*  39 */         gbb.setQuantity(rs.getString(3));
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/*  44 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/*  48 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  52 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/*  56 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  60 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/*  64 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  68 */       e.printStackTrace();
/*     */     }
/*  70 */     return ch;
/*     */   }
/*     */ 
/*     */   public boolean insertProductsBought(GenerateBillBean gbb)
/*     */   {
/*  78 */     Date date = new Date();
/*  79 */     String boughtdate = DateWrapper.parseDate(date);
/*  80 */     String custname = gbb.getCustomername();
/*  81 */     String grandtotal = gbb.getGrandtotal();
/*     */     try
/*     */     {
/*  84 */       PreparedStatement pst = null;
/*  85 */       int i = 0;
/*  86 */       pst = this.con.prepareStatement("insert into productsbought values(?,?,?)");
/*  87 */       pst.setString(1, custname);
/*  88 */       pst.setString(2, grandtotal);
/*  89 */       pst.setString(3, boughtdate);
/*  90 */       i = pst.executeUpdate();
/*  91 */       if (i == 1)
/*     */       {
/*  93 */         this.flag = true;
/*  94 */         updatePaidStatus(gbb);
/*     */       }
/*     */       else {
/*  97 */         this.flag = false;
/*     */       }
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 102 */       ex.printStackTrace();
/* 103 */       this.flag = false;
/*     */       try
/*     */       {
/* 106 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException sex)
/*     */       {
/* 110 */         sex.printStackTrace();
/* 111 */         LoggerManager.writeLogSevere(ex);
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 116 */       this.con.close();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 120 */       LoggerManager.writeLogSevere(ex);
/*     */     }
/*     */     try
/*     */     {
/* 124 */       this.con.close();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 128 */       LoggerManager.writeLogSevere(ex);
/*     */     }
/*     */     try
/*     */     {
/* 132 */       this.con.close();
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 136 */       LoggerManager.writeLogSevere(ex);
/*     */     }
/* 138 */     return this.flag;
/*     */   }
/*     */ 
/*     */   private boolean updatePaidStatus(GenerateBillBean gbb)
/*     */   {
/* 143 */     String purchasedate = DateWrapper.parseDate(new Date());
/* 144 */     String custname = gbb.getCustomername();
/*     */     try
/*     */     {
/* 147 */       PreparedStatement pst = this.con.prepareStatement("UPDATE shoppingcart SET paid_status='Yes' WHERE custname='" + custname + "' and purchasedate='" + purchasedate + "'");
/* 148 */       int i = pst.executeUpdate();
/* 149 */       if (i != 0)
/* 150 */         this.flag1 = true;
/*     */       else
/* 152 */         this.flag1 = false;
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 156 */       ex.printStackTrace();
/* 157 */       LoggerManager.writeLogSevere(ex);
/* 158 */       this.flag1 = false;
/*     */       try
/*     */       {
/* 161 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 165 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 170 */       LoggerManager.writeLogSevere(e);
/* 171 */       this.flag1 = false;
/*     */       try
/*     */       {
/* 174 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 178 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/* 181 */     return this.flag1;
/*     */   }
/*     */ 
/*     */   public CoreHash getDailyReport(GenerateBillBean gbb)
/*     */   {
/* 189 */     CoreHash ch = new CoreHash();
/* 190 */     ch.clear();
/* 191 */     int unique = 0;
/* 192 */     String date = gbb.getDate();
/* 193 */     System.out.println("************************** date:" + date);
/*     */     try
/*     */     {
/* 196 */       Statement st = this.con.createStatement();
/* 197 */       String query = "select custname,grandtotal from productsbought where boughtdate='" + date + "'";
/* 198 */       for (ResultSet rs = st.executeQuery(query); rs.next(); ch.put(unique, gbb))
/*     */       {
/* 200 */         unique++;
/* 201 */         gbb = new GenerateBillBean();
/* 202 */         gbb.setCustomername(rs.getString(1));
/* 203 */         gbb.setGrandtotal(rs.getString(2));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 209 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 213 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 217 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 221 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 225 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 229 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 233 */       e.printStackTrace();
/*     */     }
/* 235 */     return ch;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.GenerateBillDAO
 * JD-Core Version:    0.6.2
 */