/*    */ package com.dts.core.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class DateWrapper
/*    */ {
/* 35 */   static String[] month = { 
/* 36 */     "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", 
/* 37 */     "NOV", "DEC" };
/*    */ 
/*    */   public static String parseDate(java.util.Date date)
/*    */   {
/* 15 */     int monthid = date.getMonth();
/* 16 */     String newdate = date.getDate() + "-" + month[monthid] + "-" + (date.getYear() + 1900);
/* 17 */     System.out.println("new date==" + newdate);
/* 18 */     return newdate;
/*    */   }
/*    */ 
/*    */   public static String parseDate(String date)
/*    */   {
/* 23 */     int monthid = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
/* 24 */     String newdate = date.substring(0, date.indexOf("-")) + "-" + month[(monthid - 1)] + "-" + date.substring(date.lastIndexOf("-") + 1, date.length());
/* 25 */     return newdate;
/*    */   }
/*    */ 
/*    */   public static String parseDate(java.sql.Date date)
/*    */   {
/* 30 */     String olddate = date.toString();
/* 31 */     String newdate = olddate.substring(olddate.lastIndexOf("-") + 1, olddate.length()) + "-" + olddate.substring(olddate.indexOf("-") + 1, olddate.lastIndexOf("-")) + "-" + olddate.substring(0, olddate.indexOf("-"));
/* 32 */     return newdate;
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.util.DateWrapper
 * JD-Core Version:    0.6.2
 */