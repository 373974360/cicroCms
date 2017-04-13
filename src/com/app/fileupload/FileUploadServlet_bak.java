/*    */ package com.app.fileupload;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.fileupload.FileItem;
/*    */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*    */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*    */ 
/*    */ public class FileUploadServlet_bak extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 25 */     System.out.println("----------FileUploadServlet-----------");
/* 26 */     doPost(request, response);
/*    */   }
/*    */ 
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*    */   {
/* 31 */     DiskFileItemFactory factory = new DiskFileItemFactory();
/*    */ 
/* 33 */     factory.setSizeThreshold(10240000);
/*    */ 
/* 35 */     String base = "d:/upload";
/* 36 */     File file = new File(base);
/* 37 */     if (!file.exists())
/* 38 */       file.mkdirs();
/* 39 */     factory.setRepository(file);
/* 40 */     ServletFileUpload upload = new ServletFileUpload(factory);
/*    */ 
/* 42 */     upload.setFileSizeMax(10002400000L);
/*    */ 
/* 44 */     upload.setSizeMax(10002400000L);
/* 45 */     upload.setHeaderEncoding("UTF-8");
/*    */     try
/*    */     {
/* 48 */       List fileItems = upload.parseRequest(request);
/* 49 */       Iterator iter = fileItems.iterator();
/* 50 */       while (iter.hasNext()) {
/* 51 */         FileItem item = (FileItem)iter.next();
/* 52 */         if (!item.isFormField()) {
/* 53 */           String name = item.getName();
/* 54 */           System.out.println(name);
/* 55 */           System.out.println(item.getFieldName());
/*    */           try {
/* 57 */             item.write(new File(base + "/" + name));
/*    */           }
/*    */           catch (Exception e)
/*    */           {
/* 61 */             e.printStackTrace();
/*    */           }
/* 63 */           response.getWriter().write(base + "/" + name);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 69 */       e.printStackTrace();
/* 70 */       System.out.println(e.getMessage() + "����");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.fileupload.FileUploadServlet_bak
 * JD-Core Version:    0.6.2
 */