/*     */ package com.dts.dae.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.CoreHash;
/*     */ import com.dts.core.util.DateWrapper;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.dae.model.CustomerProfile;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CustomerDAO extends AbstractDataAccessObject
/*     */ {
/*     */   private boolean flag;
/*     */ 
/*     */   public CustomerDAO()
/*     */   {
/*  14 */     getConnection();
/*     */   }
/*     */ 
/*     */   public boolean registration(CustomerProfile regbean)
/*     */   {
/*  36 */     String loginname = regbean.getLoginname();
/*  37 */     String password = regbean.getPassword();
/*  38 */     String firstname = regbean.getFirstName();
/*  39 */     String lastname = regbean.getLastName();
/*  40 */     String secretquest = regbean.getOwnSecretQuestion();
/*  41 */     String secretans = regbean.getSecretAnswer();
/*  42 */     String bdate = DateWrapper.parseDate(regbean.getBirthDate());
/*  43 */     String hno = regbean.getHno();
/*  44 */     String street = regbean.getStreet();
/*  45 */     String city = regbean.getCity();
/*  46 */     String state = regbean.getState();
/*  47 */     String country = regbean.getCountry();
/*  48 */     String pincode = regbean.getPincode();
/*  49 */     String phoneno = regbean.getPhoneNo();
/*  50 */     String email = regbean.getEmail();
/*  51 */     String locale = regbean.getLocale();
/*  52 */     String creditcardno = regbean.getCreditcardNo();
/*     */     try {
/*  54 */       this.con.setAutoCommit(false);
/*  55 */       PreparedStatement pst = null;
/*  56 */       Statement st = this.con.createStatement();
/*  57 */       int i = 0;
/*  58 */       String newdate = DateWrapper.parseDate(new Date());
/*  59 */       pst = this.con.prepareStatement("insert into customerdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
/*  60 */       pst.setString(1, firstname);
/*  61 */       pst.setString(2, lastname);
/*  62 */       pst.setString(3, bdate);
/*  63 */       pst.setString(4, hno);
/*  64 */       pst.setString(5, street);
/*  65 */       pst.setString(6, city);
/*  66 */       pst.setString(7, state);
/*  67 */       pst.setString(8, country);
/*  68 */       pst.setString(9, pincode);
/*  69 */       pst.setString(10, phoneno);
/*  70 */       pst.setString(11, email);
/*  71 */       pst.setString(12, loginname);
/*  72 */       pst.setString(13, password);
/*  73 */       pst.setString(14, secretquest);
/*  74 */       pst.setString(15, secretans);
/*  75 */       pst.setString(16, creditcardno);
/*  76 */       pst.setString(17, "Y");
/*  77 */       i = pst.executeUpdate();
/*  78 */       if (i == 1)
/*     */       {
/*  80 */         this.flag = true;
/*  81 */         this.con.commit();
/*     */       }
/*     */       else {
/*  84 */         this.flag = false;
/*  85 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  90 */       e.printStackTrace();
/*     */     }
/*  92 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public CoreHash getCustomerDetails()
/*     */   {
/*  98 */     CoreHash ch = new CoreHash();
/*  99 */     ch.clear();
/* 100 */     String unique = "";
/* 101 */     CustomerProfile cp = null;
/*     */     try
/*     */     {
/* 104 */       Statement st = this.con.createStatement(1005, 1008);
/*     */ 
/* 107 */       String qry = "select * from customerdetails where status='Y'";
/* 108 */       System.out.println("qry-->" + qry);
/* 109 */       for (ResultSet rs = st.executeQuery(qry); 
/* 110 */         rs.next(); 
/* 110 */         ch.put(unique, cp))
/*     */       {
/* 112 */         unique = rs.getString(12);
/* 113 */         cp = new CustomerProfile();
/* 114 */         cp.setFirstName(rs.getString(1));
/* 115 */         cp.setLastName(rs.getString(2));
/* 116 */         cp.setBirthDate(rs.getString(3));
/* 117 */         cp.setHno(rs.getString(4));
/* 118 */         cp.setStreet(rs.getString(5));
/* 119 */         cp.setCity(rs.getString(6));
/* 120 */         cp.setState(rs.getString(7));
/* 121 */         cp.setCountry(rs.getString(8));
/* 122 */         cp.setPincode(rs.getString(9));
/* 123 */         cp.setPhoneNo(rs.getString(10));
/* 124 */         cp.setEmail(rs.getString(11));
/* 125 */         cp.setLoginname(unique);
/* 126 */         cp.setPassword(rs.getString(13));
/* 127 */         cp.setOwnSecretQuestion(rs.getString(14));
/* 128 */         cp.setSecretAnswer(rs.getString(15));
/* 129 */         cp.setCreditcardNo(rs.getString(16));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 136 */       LoggerManager.writeLogSevere(sqlex);
/* 137 */       sqlex.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 141 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 145 */       e.printStackTrace();
/*     */     }
/* 147 */     return ch;
/*     */   }
/*     */ 
/*     */   public boolean deleteCustomer(String loginname)
/*     */   {
/* 152 */     boolean flag = false;
/*     */     try
/*     */     {
/* 155 */       PreparedStatement pst = this.con.prepareStatement("update customerdetails set Status =? where loginname=?");
/* 156 */       pst.setString(1, "N");
/* 157 */       pst.setString(2, loginname);
/* 158 */       int i = pst.executeUpdate();
/* 159 */       if (i != 0)
/*     */       {
/* 161 */         flag = true;
/* 162 */         this.con.commit();
/*     */       }
/*     */       else {
/* 165 */         flag = false;
/* 166 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 171 */       sqlex.printStackTrace();
/* 172 */       LoggerManager.writeLogSevere(sqlex);
/* 173 */       flag = false;
/*     */     }
/*     */ 
/* 176 */     return flag;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.CustomerDAO
 * JD-Core Version:    0.6.2
 */