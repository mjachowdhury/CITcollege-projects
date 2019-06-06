/*    */ package com.dts.core.export;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class ExportXLS extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest req, HttpServletResponse res)
/*    */     throws ServletException, IOException
/*    */   {
/* 18 */     res.setContentType("application/vnd.ms-excel");
/* 19 */     PrintWriter pw = res.getWriter();
/* 20 */     String html = "";
/* 21 */     html = html + "Excel Report";
/* 22 */     pw.print(html);
/* 23 */     pw.close();
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.export.ExportXLS
 * JD-Core Version:    0.6.2
 */