/*    */ package com.dts.core.control;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class UIController extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest req, HttpServletResponse res)
/*    */     throws ServletException
/*    */   {
/*    */     try
/*    */     {
/* 20 */       res.sendRedirect("index.jsp");
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 24 */       ioe.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.control.UIController
 * JD-Core Version:    0.6.2
 */