/*    */ package com.dts.core.db;
/*    */ 
/*    */ import com.dts.core.dao.AbstractDataAccessObject;
/*    */ 
/*    */ public class DBFactory
/*    */ {
/*    */   public DBFactory()
/*    */   {
/* 10 */     new AbstractDataAccessObject().getConnection();
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.db.DBFactory
 * JD-Core Version:    0.6.2
 */