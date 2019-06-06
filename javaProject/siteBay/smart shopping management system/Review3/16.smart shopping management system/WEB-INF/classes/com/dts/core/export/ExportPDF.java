/*    */ package com.dts.core.export;
/*    */ 
/*    */ import com.dts.core.util.LoggerManager;
/*    */ import com.lowagie.text.Document;
/*    */ import com.lowagie.text.DocumentException;
/*    */ import com.lowagie.text.Paragraph;
/*    */ import com.lowagie.text.Phrase;
/*    */ import com.lowagie.text.pdf.PdfWriter;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class ExportPDF extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest req, HttpServletResponse res)
/*    */     throws ServletException
/*    */   {
/* 20 */     res.setContentType("application/pdf");
/* 21 */     Document document = new Document();
/* 22 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*    */     try
/*    */     {
/* 25 */       PdfWriter.getInstance(document, buffer);
/* 26 */       document.open();
/* 27 */       document.add(new Paragraph("Report"));
/* 28 */       document.add(new Phrase((String)req.getSession().getAttribute("export")));
/* 29 */       document.close();
/*    */     }
/*    */     catch (DocumentException de)
/*    */     {
/* 33 */       LoggerManager.writeLogWarning(de);
/*    */     }
/*    */     try
/*    */     {
/* 37 */       DataOutput output = new DataOutputStream(res.getOutputStream());
/* 38 */       byte[] bytes = buffer.toByteArray();
/* 39 */       res.setContentLength(bytes.length);
/* 40 */       for (int i = 0; i < bytes.length; i++) {
/* 41 */         output.writeByte(bytes[i]);
/*    */       }
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 46 */       LoggerManager.writeLogWarning(ioe);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\16.Online Marketing\WEB-INF\classes\
 * Qualified Name:     com.dts.core.export.ExportPDF
 * JD-Core Version:    0.6.2
 */