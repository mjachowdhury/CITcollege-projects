/*    */ package com.dts.core.control;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class DispatchController extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest req, HttpServletResponse res)
/*    */   {
/* 16 */     String page = req.getParameter("page");
/*    */     try
/*    */     {
/* 19 */       res.sendRedirect(page);
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 23 */       ioe.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.control.DispatchController
 * JD-Core Version:    0.6.2
 */