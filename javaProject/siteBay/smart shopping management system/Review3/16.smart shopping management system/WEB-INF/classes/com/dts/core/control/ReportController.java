/*    */ package com.dts.core.control;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class ReportController extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest req, HttpServletResponse res)
/*    */     throws ServletException
/*    */   {
/*    */     try
/*    */     {
/* 21 */       res.sendRedirect("ReportDateSelector.jsp");
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 25 */       ioe.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.control.ReportController
 * JD-Core Version:    0.6.2
 */