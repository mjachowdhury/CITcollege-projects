/*    */ package com.dts.core.util;
/*    */ 
/*    */ import com.dts.core.dao.AbstractDataAccessObject;
/*    */ import com.dts.core.db.DBFactory;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import javax.servlet.ServletConfig;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ 
/*    */ public class InitServlet extends HttpServlet
/*    */ {
/*    */   AbstractDataAccessObject dobject;
/*    */ 
/*    */   public void init(ServletConfig sc)
/*    */   {
/* 23 */     ServletContext ctx = sc.getServletContext();
/* 24 */     InputStream fis = ctx.getResourceAsStream(sc.getInitParameter("config"));
/* 25 */     Properties props = new Properties();
/*    */     try
/*    */     {
/* 28 */       props.load(fis);
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 32 */       ioe.printStackTrace();
/*    */     }
/* 34 */     this.dobject = new AbstractDataAccessObject();
/* 35 */     this.dobject.setProperties(props);
/* 36 */     LoggerManager.logger = new LoggerManager().getLogger(props.getProperty("logfile"));
/* 37 */     LoggerManager.writeLogInfo("Logger Instantiated");
/*    */     try
/*    */     {
/* 40 */       new DBFactory();
/*    */     }
/*    */     catch (NullPointerException npe)
/*    */     {
/* 44 */       LoggerManager.writeLogWarning("Connection to database FAILED");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.util.InitServlet
 * JD-Core Version:    0.6.2
 */