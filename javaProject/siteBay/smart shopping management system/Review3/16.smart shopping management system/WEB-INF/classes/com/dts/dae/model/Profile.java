/*     */ package com.dts.dae.model;
/*     */ 
/*     */ import java.sql.Date;
/*     */ 
/*     */ public class Profile
/*     */ {
/*     */   private int secretqid;
/*     */   private String id;
/*     */   private String password;
/*     */   private String newpassword;
/*     */   private String firstname;
/*     */   private String lastname;
/*     */   private String logintype;
/*     */   private int status;
/*     */   private String date;
/*     */   private int roleid;
/*     */   private int flogin;
/*     */   private String timezone;
/*     */   private String city;
/*     */   private String hno;
/*     */   private String street;
/*     */   private String phoneNo;
/*     */   private String email;
/*     */   private String bdate;
/*     */   private Date birthDate1;
/*     */   private String state;
/*     */   private String country;
/*     */   private String pincode;
/*     */   private String locale;
/*     */   private String secretqans;
/*     */   private String ownsecretq;
/*     */   private String passwordmoddate;
/*     */   private String profilemoddate;
/*     */ 
/*     */   public void setLoginID(String id)
/*     */   {
/*  14 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/*  19 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public void setNewPassword(String newpassword)
/*     */   {
/*  24 */     this.newpassword = newpassword;
/*     */   }
/*     */ 
/*     */   public void setFirstName(String firstname)
/*     */   {
/*  29 */     this.firstname = firstname;
/*     */   }
/*     */ 
/*     */   public void setLastName(String lastname)
/*     */   {
/*  34 */     this.lastname = lastname;
/*     */   }
/*     */ 
/*     */   public void setLoginType(String logintype)
/*     */   {
/*  39 */     this.logintype = logintype;
/*     */   }
/*     */ 
/*     */   public void setStatus(int status)
/*     */   {
/*  44 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public void setRegDate(String date)
/*     */   {
/*  49 */     this.date = date;
/*     */   }
/*     */ 
/*     */   public void setSecretQuestionID(int secretqid)
/*     */   {
/*  54 */     this.secretqid = secretqid;
/*     */   }
/*     */ 
/*     */   public void setOwnSecretQuestion(String ownsecretq)
/*     */   {
/*  59 */     this.ownsecretq = ownsecretq;
/*     */   }
/*     */ 
/*     */   public void setSecretAnswer(String secretqans)
/*     */   {
/*  64 */     this.secretqans = secretqans;
/*     */   }
/*     */ 
/*     */   public void setFirstLogin(int flogin)
/*     */   {
/*  69 */     this.flogin = flogin;
/*     */   }
/*     */ 
/*     */   public void setRoleId(int roleid)
/*     */   {
/*  74 */     this.roleid = roleid;
/*     */   }
/*     */ 
/*     */   public void setBirthDate(String bdate)
/*     */   {
/*  79 */     this.bdate = bdate;
/*     */   }
/*     */ 
/*     */   public void setCity(String city)
/*     */   {
/*  84 */     this.city = city;
/*     */   }
/*     */ 
/*     */   public void setState(String state)
/*     */   {
/*  89 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public void setCountry(String country)
/*     */   {
/*  94 */     this.country = country;
/*     */   }
/*     */ 
/*     */   public void setLocale(String locale)
/*     */   {
/*  99 */     this.locale = locale;
/*     */   }
/*     */ 
/*     */   public void setTimeZone(String timezone)
/*     */   {
/* 104 */     this.timezone = timezone;
/*     */   }
/*     */ 
/*     */   public void setPasswordModifiedDate(String passwordmoddate)
/*     */   {
/* 109 */     this.passwordmoddate = passwordmoddate;
/*     */   }
/*     */ 
/*     */   public void setProfileModifiedDate(String profilemoddate)
/*     */   {
/* 114 */     this.profilemoddate = profilemoddate;
/*     */   }
/*     */ 
/*     */   public String getLoginID()
/*     */   {
/* 119 */     return this.id;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 124 */     return this.password;
/*     */   }
/*     */ 
/*     */   public String getNewPassword()
/*     */   {
/* 129 */     return this.newpassword;
/*     */   }
/*     */ 
/*     */   public String getFirstName()
/*     */   {
/* 134 */     return this.firstname;
/*     */   }
/*     */ 
/*     */   public String getLastName()
/*     */   {
/* 139 */     return this.lastname;
/*     */   }
/*     */ 
/*     */   public String getLoginType()
/*     */   {
/* 144 */     return this.logintype;
/*     */   }
/*     */ 
/*     */   public int getStatus()
/*     */   {
/* 149 */     return this.status;
/*     */   }
/*     */ 
/*     */   public String getRegDate()
/*     */   {
/* 154 */     return this.date;
/*     */   }
/*     */ 
/*     */   public int getSecretQuestionID()
/*     */   {
/* 159 */     return this.secretqid;
/*     */   }
/*     */ 
/*     */   public String getOwnSecretQuestion()
/*     */   {
/* 164 */     return this.ownsecretq;
/*     */   }
/*     */ 
/*     */   public String getSecretAnswer()
/*     */   {
/* 169 */     return this.secretqans;
/*     */   }
/*     */ 
/*     */   public int getFirstLogin()
/*     */   {
/* 174 */     return this.flogin;
/*     */   }
/*     */ 
/*     */   public int getRoleId()
/*     */   {
/* 179 */     return this.roleid;
/*     */   }
/*     */ 
/*     */   public String getBirthDate()
/*     */   {
/* 184 */     return this.bdate;
/*     */   }
/*     */ 
/*     */   public String getCity()
/*     */   {
/* 189 */     return this.city;
/*     */   }
/*     */ 
/*     */   public String getState()
/*     */   {
/* 194 */     return this.state;
/*     */   }
/*     */ 
/*     */   public String getCountry()
/*     */   {
/* 199 */     return this.country;
/*     */   }
/*     */ 
/*     */   public String getLocale()
/*     */   {
/* 204 */     return this.locale;
/*     */   }
/*     */ 
/*     */   public String getTimeZone()
/*     */   {
/* 209 */     return this.timezone;
/*     */   }
/*     */ 
/*     */   public String getPasswordModifiedDate()
/*     */   {
/* 214 */     return this.passwordmoddate;
/*     */   }
/*     */ 
/*     */   public String getProfileModifiedDate()
/*     */   {
/* 219 */     return this.profilemoddate;
/*     */   }
/*     */ 
/*     */   public Date getBirthDate1()
/*     */   {
/* 224 */     return this.birthDate1;
/*     */   }
/*     */ 
/*     */   public void setBirthDate1(Date birthDate1)
/*     */   {
/* 229 */     this.birthDate1 = birthDate1;
/*     */   }
/*     */ 
/*     */   public String getHno()
/*     */   {
/* 234 */     return this.hno;
/*     */   }
/*     */ 
/*     */   public void setHno(String hno)
/*     */   {
/* 239 */     this.hno = hno;
/*     */   }
/*     */ 
/*     */   public String getStreet()
/*     */   {
/* 244 */     return this.street;
/*     */   }
/*     */ 
/*     */   public void setStreet(String street)
/*     */   {
/* 249 */     this.street = street;
/*     */   }
/*     */ 
/*     */   public String getPhoneNo()
/*     */   {
/* 254 */     return this.phoneNo;
/*     */   }
/*     */ 
/*     */   public void setPhoneNo(String phoneNo)
/*     */   {
/* 259 */     this.phoneNo = phoneNo;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 264 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 269 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getPincode()
/*     */   {
/* 274 */     return this.pincode;
/*     */   }
/*     */ 
/*     */   public void setPincode(String pincode)
/*     */   {
/* 279 */     this.pincode = pincode;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.model.Profile
 * JD-Core Version:    0.6.2
 */