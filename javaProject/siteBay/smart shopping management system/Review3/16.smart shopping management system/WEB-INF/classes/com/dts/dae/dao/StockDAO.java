/*     */ package com.dts.dae.dao;
/*     */ 
/*     */ import com.dts.core.dao.AbstractDataAccessObject;
/*     */ import com.dts.core.util.CoreHash;
/*     */ import com.dts.core.util.LoggerManager;
/*     */ import com.dts.dae.model.StockBean;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ public class StockDAO extends AbstractDataAccessObject
/*     */ {
/*     */   private boolean flag;
/*     */ 
/*     */   public StockDAO()
/*     */   {
/*  14 */     getConnection();
/*     */   }
/*     */ 
/*     */   public int getUniqueId()
/*     */   {
/*  19 */     int iMaxCount = 0;
/*     */     try
/*     */     {
/*  22 */       Statement st = this.con.createStatement();
/*  23 */       ResultSet resultSet = st.executeQuery("select max(productid) from productlist");
/*  24 */       if (resultSet.next())
/*  25 */         iMaxCount = resultSet.getInt(1);
/*  26 */       iMaxCount++;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  30 */       e.printStackTrace();
/*  31 */       this.flag = false;
/*     */       try
/*     */       {
/*  34 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/*  38 */         se.printStackTrace();
/*     */       }
/*     */     }
/*  41 */     return iMaxCount;
/*     */   }
/*     */ 
/*     */   public boolean insertStock(StockBean stockbean)
/*     */   {
/*  50 */     int pid = getUniqueId();
/*  51 */     String pname = stockbean.getProductname();
/*  52 */     String price = stockbean.getPrice();
/*  53 */     String category = stockbean.getCategory();
/*     */     try
/*     */     {
/*  56 */       PreparedStatement pst = null;
/*  57 */       int i = 0;
/*  58 */       pst = this.con.prepareStatement("insert into productlist values(?,?,?,?,?,?)");
/*  59 */       pst.setInt(1, pid);
/*  60 */       pst.setString(2, pname);
/*  61 */       pst.setString(3, price);
/*  62 */       pst.setString(4, category);
/*  63 */       pst.setString(5, "Y");
/*  64 */       pst.setString(6, "20");
/*  65 */       i = pst.executeUpdate();
/*  66 */       if (i == 1)
/*     */       {
/*  68 */         this.flag = true;
/*  69 */         this.con.commit();
/*     */       }
/*     */       else {
/*  72 */         this.flag = false;
/*  73 */         this.con.rollback();
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  79 */       e.printStackTrace();
/*  80 */       this.flag = false;
/*     */       try
/*     */       {
/*  83 */         this.con.rollback();
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/*  87 */         se.printStackTrace();
/*     */       }
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
/*     */   public CoreHash getStockDetails()
/*     */   {
/* 120 */     CoreHash ch = new CoreHash();
/* 121 */     ch.clear();
/* 122 */     int unique = 0;
/* 123 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 126 */       Statement st = this.con.createStatement();
/* 127 */       for (ResultSet rs = st.executeQuery("select * from productlist where status='Y'"); rs.next(); ch.put(unique, stkb))
/*     */       {
/* 129 */         unique = rs.getInt(1);
/* 130 */         stkb = new StockBean();
/* 131 */         stkb.setProductid(unique);
/* 132 */         stkb.setProductname(rs.getString(2));
/* 133 */         stkb.setPrice(rs.getString(3));
/* 134 */         stkb.setCategory(rs.getString(4));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 140 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 144 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 148 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 152 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 156 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 160 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 164 */       e.printStackTrace();
/*     */     }
/* 166 */     return ch;
/*     */   }
/*     */ 
/*     */   public CoreHash getProductDetails(String category)
/*     */   {
/* 172 */     CoreHash ch1 = new CoreHash();
/* 173 */     ch1.clear();
/* 174 */     int unique = 0;
/* 175 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 178 */       Statement st = this.con.createStatement();
/* 179 */       for (ResultSet rs = st.executeQuery("select productid,productname from productlist where status='Y'and category='" + category + "'"); rs.next(); ch1.put(unique, stkb))
/*     */       {
/* 181 */         unique = rs.getInt(1);
/* 182 */         stkb = new StockBean();
/* 183 */         stkb.setProductid(unique);
/* 184 */         stkb.setProductname(rs.getString(2));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 190 */       sqlex.printStackTrace();
/* 191 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 195 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 199 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 203 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 207 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 211 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 215 */       e.printStackTrace();
/*     */     }
/* 217 */     return ch1;
/*     */   }
/*     */ 
/*     */   public boolean deleteStock(int pid)
/*     */   {
/* 222 */     boolean flag = false;
/*     */     try
/*     */     {
/* 225 */       PreparedStatement pst = this.con.prepareStatement("update productlist set Status =? where productid=?");
/* 226 */       pst.setString(1, "N");
/* 227 */       pst.setInt(2, pid);
/* 228 */       int i = pst.executeUpdate();
/* 229 */       if (i != 0)
/*     */       {
/* 231 */         flag = true;
/* 232 */         this.con.commit();
/*     */       }
/*     */       else {
/* 235 */         flag = false;
/* 236 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 241 */       sqlex.printStackTrace();
/* 242 */       LoggerManager.writeLogSevere(sqlex);
/* 243 */       flag = false;
/*     */     }
/*     */     try
/*     */     {
/* 247 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 251 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 255 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 259 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 263 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 267 */       e.printStackTrace();
/*     */     }
/* 269 */     return flag;
/*     */   }
/*     */ 
/*     */   public CoreHash getAvailableStockDetails()
/*     */   {
/* 275 */     CoreHash ch = new CoreHash();
/* 276 */     ch.clear();
/* 277 */     int unique = 0;
/* 278 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 281 */       Statement st = this.con.createStatement();
/* 282 */       for (ResultSet rs = st.executeQuery("select * from productlist where status='Y'"); rs.next(); ch.put(unique, stkb))
/*     */       {
/* 284 */         unique = rs.getInt(1);
/* 285 */         stkb = new StockBean();
/* 286 */         stkb.setProductid(unique);
/* 287 */         stkb.setProductname(rs.getString(2));
/* 288 */         stkb.setPrice(rs.getString(3));
/* 289 */         stkb.setCategory(rs.getString(4));
/* 290 */         stkb.setAvailablestock(rs.getString(6));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 296 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 300 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 304 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 308 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 312 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 316 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 320 */       e.printStackTrace();
/*     */     }
/* 322 */     return ch;
/*     */   }
/*     */ 
/*     */   public CoreHash getProductDetails()
/*     */   {
/* 328 */     CoreHash ch1 = new CoreHash();
/* 329 */     ch1.clear();
/* 330 */     int unique = 0;
/* 331 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 334 */       Statement st = this.con.createStatement();
/* 335 */       for (ResultSet rs = st.executeQuery("select productid,productname from productlist where status='Y'"); rs.next(); ch1.put(unique, stkb))
/*     */       {
/* 337 */         unique = rs.getInt(1);
/* 338 */         stkb = new StockBean();
/* 339 */         stkb.setProductid(unique);
/* 340 */         stkb.setProductname(rs.getString(2));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 346 */       sqlex.printStackTrace();
/* 347 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 351 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 355 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 359 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 363 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 367 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 371 */       e.printStackTrace();
/*     */     }
/* 373 */     return ch1;
/*     */   }
/*     */ 
/*     */   public CoreHash getProductDetails(int prodid)
/*     */   {
/* 379 */     CoreHash ch1 = new CoreHash();
/* 380 */     ch1.clear();
/* 381 */     int unique = 0;
/* 382 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 385 */       Statement st = this.con.createStatement();
/* 386 */       for (ResultSet rs = st.executeQuery("select * from productlist where status='Y' and productid=" + prodid); rs.next(); ch1.put(unique, stkb))
/*     */       {
/* 388 */         unique = rs.getInt(1);
/* 389 */         stkb = new StockBean();
/* 390 */         stkb.setProductid(unique);
/* 391 */         stkb.setProductname(rs.getString(2));
/* 392 */         stkb.setPrice(rs.getString(3));
/* 393 */         stkb.setCategory(rs.getString(4));
/* 394 */         stkb.setStatus(rs.getString(5));
/* 395 */         stkb.setAvailablestock(rs.getString(6));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 401 */       sqlex.printStackTrace();
/* 402 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 406 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 410 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 414 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 418 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 422 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 426 */       e.printStackTrace();
/*     */     }
/* 428 */     return ch1;
/*     */   }
/*     */ 
/*     */   public boolean addQuantity(int prodid, String available)
/*     */   {
/* 433 */     boolean flag = false;
/*     */     try
/*     */     {
/* 436 */       PreparedStatement pst = this.con.prepareStatement("update productlist set availablestock =? where productid=?");
/* 437 */       pst.setString(1, available);
/* 438 */       pst.setInt(2, prodid);
/* 439 */       int i = pst.executeUpdate();
/* 440 */       if (i != 0)
/*     */       {
/* 442 */         flag = true;
/* 443 */         this.con.commit();
/*     */       }
/*     */       else {
/* 446 */         flag = false;
/* 447 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 452 */       sqlex.printStackTrace();
/* 453 */       LoggerManager.writeLogSevere(sqlex);
/* 454 */       flag = false;
/*     */     }
/*     */     try
/*     */     {
/* 458 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 462 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 466 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 470 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 474 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 478 */       e.printStackTrace();
/*     */     }
/* 480 */     return flag;
/*     */   }
/*     */ 
/*     */   public void updateQuantity(String prodname, String availablestock)
/*     */   {
/* 485 */     boolean flag = false;
/*     */     try
/*     */     {
/* 488 */       PreparedStatement pst = this.con.prepareStatement("update productlist set availablestock =? where productname=?");
/* 489 */       pst.setString(1, availablestock);
/* 490 */       pst.setString(2, prodname);
/* 491 */       int i = pst.executeUpdate();
/* 492 */       if (i != 0)
/*     */       {
/* 494 */         flag = true;
/* 495 */         this.con.commit();
/*     */       }
/*     */       else {
/* 498 */         flag = false;
/* 499 */         this.con.rollback();
/*     */       }
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 504 */       sqlex.printStackTrace();
/* 505 */       LoggerManager.writeLogSevere(sqlex);
/* 506 */       flag = false;
/*     */     }
/*     */     try
/*     */     {
/* 510 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 514 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 518 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 522 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 526 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 530 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public StockBean getAvailableStockDetails(String prodname)
/*     */   {
/* 537 */     StockBean stkb = null;
/*     */     try
/*     */     {
/* 540 */       Statement st = this.con.createStatement();
/* 541 */       for (ResultSet rs = st.executeQuery("select * from productlist where status='Y' and productname='" + prodname + "'"); rs.next(); stkb.setAvailablestock(rs.getString(6)))
/*     */       {
/* 543 */         int unique = rs.getInt(1);
/* 544 */         stkb = new StockBean();
/* 545 */         stkb.setProductid(unique);
/* 546 */         stkb.setProductname(rs.getString(2));
/* 547 */         stkb.setPrice(rs.getString(3));
/* 548 */         stkb.setCategory(rs.getString(4));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException sqlex)
/*     */     {
/* 554 */       LoggerManager.writeLogSevere(sqlex);
/*     */     }
/*     */     try
/*     */     {
/* 558 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 562 */       e.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/* 566 */       this.con.close();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 570 */       e.printStackTrace();
/*     */     }
/* 572 */     return stkb;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.dae.dao.StockDAO
 * JD-Core Version:    0.6.2
 */