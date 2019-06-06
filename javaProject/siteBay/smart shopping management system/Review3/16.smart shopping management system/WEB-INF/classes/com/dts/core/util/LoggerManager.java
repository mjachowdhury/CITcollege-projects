/*    */ package com.dts.core.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.logging.FileHandler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogManager;
/*    */ import java.util.logging.Logger;
/*    */ import java.util.logging.SimpleFormatter;
/*    */ 
/*    */ public class LoggerManager
/*    */ {
/*    */   public static Logger logger;
/*    */ 
/*    */   public Logger getLogger(String aFilePath)
/*    */   {
/* 15 */     String aLogDir = aFilePath.substring(0, aFilePath.lastIndexOf("/"));
/* 16 */     logger = Logger.getLogger("Logger");
/*    */     try
/*    */     {
/* 19 */       File aFile = new File(aLogDir);
/* 20 */       boolean success = aFile.exists();
/* 21 */       if (!success)
/* 22 */         success = aFile.mkdir();
/* 23 */       LogManager lm = LogManager.getLogManager();
/* 24 */       FileHandler fh = new FileHandler(aFilePath, true);
/* 25 */       logger = Logger.getLogger("LoggerManager");
/* 26 */       logger.setUseParentHandlers(false);
/* 27 */       lm.addLogger(logger);
/* 28 */       logger.setLevel(Level.INFO);
/* 29 */       fh.setFormatter(new SimpleFormatter());
/* 30 */       logger.addHandler(fh);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 34 */       logger.log(Level.INFO, e.toString(), e.fillInStackTrace());
/*    */     }
/* 36 */     return logger;
/*    */   }
/*    */ 
/*    */   public static void writeLogInfo(Exception e)
/*    */   {
/* 41 */     logger.log(Level.INFO, e.toString(), e.fillInStackTrace());
/*    */   }
/*    */ 
/*    */   public static void writeLogSevere(Exception e)
/*    */   {
/* 46 */     logger.log(Level.SEVERE, e.toString(), e.fillInStackTrace());
/*    */   }
/*    */ 
/*    */   public static void writeLogWarning(Exception e)
/*    */   {
/* 51 */     logger.log(Level.WARNING, e.toString(), e.fillInStackTrace());
/*    */   }
/*    */ 
/*    */   public static void writeLogInfo(String info)
/*    */   {
/* 56 */     logger.log(Level.INFO, info);
/*    */   }
/*    */ 
/*    */   public static void writeLogSevere(String severe)
/*    */   {
/* 61 */     logger.log(Level.SEVERE, severe);
/*    */   }
/*    */ 
/*    */   public static void writeLogWarning(String warning)
/*    */   {
/* 66 */     logger.log(Level.WARNING, warning);
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.util.LoggerManager
 * JD-Core Version:    0.6.2
 */