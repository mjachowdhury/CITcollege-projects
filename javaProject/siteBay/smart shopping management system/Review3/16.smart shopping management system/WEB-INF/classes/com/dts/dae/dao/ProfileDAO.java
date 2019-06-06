/*     */ package com.dts.dae.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.DateWrapper;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.dae.model.Profile;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class ProfileDAO extends AbstractDataAccessObject
/*     */ {
/*     */   public Connection con;
/*     */   private boolean flag;
/*     */ 
/*     */   public boolean registration(Profile regbean)
/*     */   {
/*  19 */     String loginid = regbean.getLoginID();
/*  20 */     String password = regbean.getPassword();
/*  21 */     String firstname = regbean.getFirstName();
/*  22 */     String lastname = regbean.getLastName();
/*  23 */     String logintype = regbean.getLoginType();
/*  24 */     int status = regbean.getStatus();
/*  25 */     int secretquest = regbean.getSecretQuestionID();
/*  26 */     String ownsecretquest = regbean.getOwnSecretQuestion();
/*  27 */     String secretans = regbean.getSecretAnswer();
/*  28 */     int firstlogin = regbean.getFirstLogin();
/*  29 */     String bdate = DateWrapper.parseDate(regbean.getBirthDate());
/*  30 */     String hno = regbean.getHno();
/*  31 */     String street = regbean.getStreet();
/*  32 */     String city = regbean.getCity();
/*  33 */     String state = regbean.getState();
/*  34 */     String country = regbean.getCountry();
/*  35 */     String pincode = regbean.getPincode();
/*  36 */     String phoneno = regbean.getPhoneNo();
/*  37 */     String email = regbean.getEmail();
/*  38 */     String locale = regbean.getLocale();
/*  39 */     String passmdate = regbean.getPasswordModifiedDate();
/*  40 */     String profilemdate = regbean.getProfileModifiedDate();
/*     */     try
/*     */     {
/*  43 */       this.con = getConnection();
/*  44 */       this.con.setAutoCommit(false);
/*  45 */       PreparedStatement pst = null;
/*  46 */       Statement st = this.con.createStatement();
/*  47 */       int i = 0;
/*  48 */       if (secretquest == 0)
/*     */       {
/*  50 */         ResultSet rs = st.executeQuery("select (max(questionid))+1 from questionbase");
/*  51 */         if (rs.next())
/*  52 */           secretquest = rs.getInt(1);
/*  53 */         pst = this.con.prepareStatement("INSERT INTO questionbase VALUES(?,?)");
/*  54 */         pst.setInt(1, secretquest);
/*  55 */         pst.setString(2, ownsecretquest);
/*  56 */         pst.executeUpdate();
/*     */       }
/*  58 */       String newdate = DateWrapper.parseDate(new Date());
/*  59 */       pst = this.con.prepareStatement("insert into LOGINDETAILS values(?,?,?,?,?,?,?,?,?,?,?)");
/*  60 */       pst.setString(1, loginid);
/*  61 */       pst.setString(2, password);
/*  62 */       pst.setString(3, firstname);
/*  63 */       pst.setString(4, lastname);
/*  64 */       pst.setString(5, logintype);
/*  65 */       pst.setInt(6, status);
/*  66 */       pst.setString(7, newdate);
/*  67 */       pst.setInt(8, secretquest);
/*  68 */       pst.setString(9, secretans);
/*  69 */       pst.setInt(10, firstlogin);
/*  70 */       pst.setString(11, newdate);
/*  71 */       i = pst.executeUpdate();
/*  72 */       if (i == 1)
/*     */       {
/*  74 */         pst = this.con.prepareStatement("insert into LOGINPROFILE values(?,?,?,?,?,?,?,?,?,?,?,?)");
/*  75 */         pst.setString(1, loginid);
/*  76 */         pst.setString(2, bdate);
/*  77 */         pst.setString(3, hno);
/*  78 */         pst.setString(4, street);
/*  79 */         pst.setString(5, city);
/*  80 */         pst.setString(6, state);
/*  81 */         pst.setString(7, country);
/*  82 */         pst.setString(8, pincode);
/*  83 */         pst.setString(9, phoneno);
/*  84 */         pst.setString(10, email);
/*  85 */         pst.setString(11, locale);
/*  86 */         pst.setString(12, newdate);
/*  87 */         i = pst.executeUpdate();
/*     */       }
/*  89 */       if (i == 1)
/*     */       {
/*  91 */         this.flag = true;
/*  92 */         this.con.commit();
/*     */       }
/*     */       else {
/*  95 */         this.flag = false;
/*  96 */         this.con.rollback();
/*     */       }
/*  98 */       this.con.close();
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
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 115 */       e.printStackTrace();
/* 116 */       this.flag = false;
/*     */       try
/*     */       {
/* 119 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 123 */         se.printStackTrace();
/*     */       }
/*     */     }
/* 126 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public Profile getProfile(String loginname)
/*     */   {
/* 131 */     Profile rb = null;
/*     */     try
/*     */     {
/* 134 */       this.con = getConnection();
/* 135 */       Statement st = this.con.createStatement();
/* 136 */       ResultSet rs = st.executeQuery("select ld.firstname,ld.lastname,lp.birthdate,lp.city,lp.state,lp.country from logindetails ld,loginprofile lp where ld.loginname=lp.loginid and ld.loginname='" + loginname + "'");
/* 137 */       if (rs.next())
/*     */       {
/* 139 */         rb = new Profile();
/* 140 */         rb.setLoginID(loginname);
/* 141 */         rb.setFirstName(rs.getString(1));
/* 142 */         rb.setLastName(rs.getString(2));
/* 143 */         rb.setBirthDate1(rs.getDate(3));
/* 144 */         rb.setCity(rs.getString(4));
/* 145 */         rb.setState(rs.getString(5));
/* 146 */         rb.setCountry(rs.getString(6));
/*     */       }
/* 148 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 152 */       LoggerManager.writeLogSevere(e);
/*     */     }
/* 154 */     return rb;
/*     */   }
/*     */ 
/*     */   public boolean modifyProfile(Profile regbean)
/*     */   {
/* 159 */     String loginid = regbean.getLoginID();
/* 160 */     String bdate = DateWrapper.parseDate(regbean.getBirthDate());
/* 161 */     String city = regbean.getCity();
/* 162 */     String state = regbean.getState();
/* 163 */     String country = regbean.getCountry();
/* 164 */     String firstname = regbean.getFirstName();
/* 165 */     String lastname = regbean.getLastName();
/*     */     try
/*     */     {
/* 168 */       this.con = getConnection();
/* 169 */       this.con.setAutoCommit(false);
/* 170 */       PreparedStatement pst = this.con.prepareStatement("UPDATE loginprofile SET birthdate=?,city=?,state=?,country=?,profilemodifieddate=? WHERE loginid=?");
/* 171 */       PreparedStatement pst1 = this.con.prepareStatement("UPDATE logindetails SET firstname=?,lastname=? WHERE loginname=?");
/* 172 */       pst.setString(1, bdate);
/* 173 */       pst.setString(2, city);
/* 174 */       pst.setString(3, state);
/* 175 */       pst.setString(4, country);
/* 176 */       pst.setString(5, DateWrapper.parseDate(new Date()));
/* 177 */       pst.setString(6, loginid);
/* 178 */       pst1.setString(1, firstname);
/* 179 */       pst1.setString(2, lastname);
/* 180 */       pst1.setString(3, loginid);
/* 181 */       int i = pst.executeUpdate();
/* 182 */       if (i != 0)
/*     */       {
/* 184 */         i = pst1.executeUpdate();
/* 185 */         if (i != 0)
/*     */         {
/* 187 */           this.flag = true;
/* 188 */           this.con.commit();
/*     */         }
/*     */         else {
/* 191 */           this.flag = false;
/* 192 */           this.con.rollback();
/*     */         }
/*     */       }
/*     */       else {
/* 196 */         this.flag = false;
/* 197 */         this.con.rollback();
/*     */       }
/* 199 */       this.con.close();
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 203 */       ex.printStackTrace();
/* 204 */       LoggerManager.writeLogSevere(ex);
/* 205 */       this.flag = false;
/*     */       try
/*     */       {
/* 208 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 212 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 217 */       LoggerManager.writeLogSevere(e);
/* 218 */       this.flag = false;
/*     */       try
/*     */       {
/* 221 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 225 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/* 228 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public boolean changeAccountStatus(String loginid, int status)
/*     */   {
/*     */     try
/*     */     {
/* 235 */       this.con = getConnection();
/* 236 */       this.con.setAutoCommit(false);
/* 237 */       if (status == 0)
/* 238 */         status = 1;
/*     */       else
/* 240 */         status = 0;
/* 241 */       PreparedStatement pst = this.con.prepareStatement("UPDATE logindetails SET loginstatus=? WHERE loginname=?");
/* 242 */       pst.setInt(1, status);
/* 243 */       pst.setString(2, loginid);
/* 244 */       int i = pst.executeUpdate();
/* 245 */       if (i == 1)
/*     */       {
/* 247 */         this.flag = true;
/* 248 */         this.con.commit();
/*     */       }
/*     */       else {
/* 251 */         this.flag = false;
/* 252 */         this.con.rollback();
/*     */       }
/* 254 */       this.con.close();
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/* 258 */       LoggerManager.writeLogSevere(ex);
/* 259 */       this.flag = false;
/*     */       try
/*     */       {
/* 262 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 266 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 271 */       LoggerManager.writeLogSevere(e);
/* 272 */       this.flag = false;
/*     */       try
/*     */       {
/* 275 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 279 */         LoggerManager.writeLogSevere(se);
/*     */       }
/*     */     }
/* 282 */     return this.flag;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.ProfileDAO
 * JD-Core Version:    0.6.2
 */