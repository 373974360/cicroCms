/*    */ package com.app.fileupload;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.commons.fileupload.FileItem;
/*    */ import org.apache.commons.fileupload.FileItemFactory;
/*    */ import org.apache.commons.fileupload.FileUploadException;
/*    */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*    */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*    */ 
/*    */ public class AppFileUploadServlet extends HttpServlet
/*    */ {
/*    */   public void destroy()
/*    */   {
/* 31 */     super.destroy();
/*    */   }
/*    */ 
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*    */   {
/* 36 */     System.out.println("--------AppFileUploadServlet----------doGet---------start--------");
/* 37 */     String serverPath = "";
/* 38 */     FileItemFactory factory = new DiskFileItemFactory();
/* 39 */     ServletFileUpload upload = new ServletFileUpload(factory);
/* 40 */     List items = null;
/*    */     try {
/* 42 */       items = upload.parseRequest(request);
/*    */     } catch (FileUploadException e1) {
/* 44 */       e1.printStackTrace();
/*    */     }
/*    */     try {
/* 47 */       String rootPath = request.getSession().getServletContext().getRealPath("/");
/* 48 */       String saveReportPath = rootPath + "AppUpload/";
/* 49 */       MyUtils.mkDirectory(saveReportPath);
/* 50 */       boolean flag = false;
/* 51 */       Iterator iter = items.iterator();
/* 52 */       while (iter.hasNext()) {
/* 53 */         FileItem item = (FileItem)iter.next();
/*    */ 
/* 58 */         if ((item.getContentType() != null) && 
/* 59 */           (item.getName() != null) && (!item.getName().equals("")))
/*    */         {
/* 62 */           String newFileName = MyUtils.randomRename(item.getName(), saveReportPath);
/* 63 */           String reportAbsFilePath = saveReportPath + newFileName;
/* 64 */           File file = new File(reportAbsFilePath);
/*    */           try {
/* 66 */             item.write(file);
/* 67 */             flag = true;
/* 68 */             serverPath = reportAbsFilePath;
/*    */           } catch (Exception e) {
/* 70 */             flag = false;
/* 71 */             e.printStackTrace();
/*    */           } finally {
/* 73 */             file = null;
/* 74 */             item = null;
/* 75 */             if (flag)
/*    */             {
/* 77 */               response.getWriter().write(newFileName);
/*    */             }
/*    */             else {
/* 80 */               response.getWriter().write(newFileName);
/*    */             }
/* 82 */             flag = false;
/*    */           }
/*    */         }
/*    */       }
/*    */     } catch (Exception e) {
/* 87 */       e.printStackTrace();
/*    */     }
/* 89 */     System.out.println("AppFileUploadServlet ===== serverPath===" + serverPath);
/* 90 */     response.getWriter().print(serverPath);
/*    */   }
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 93 */     doGet(request, response);
/*    */   }
/*    */ 
/*    */   public void init()
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.fileupload.AppFileUploadServlet
 * JD-Core Version:    0.6.2
 */