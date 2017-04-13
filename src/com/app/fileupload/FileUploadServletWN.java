/*     */ package com.app.fileupload;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.FileItemFactory;
/*     */ import org.apache.commons.fileupload.FileUploadException;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ public class FileUploadServletWN extends HttpServlet
/*     */ {
/*     */   public void destroy()
/*     */   {
/*  37 */     super.destroy();
/*     */   }
/*     */ 
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/*  42 */     System.out.println("--------FileUploadServlet----------doGet---------start--------");
/*     */ 
/*  44 */     FileItemFactory factory = new DiskFileItemFactory();
/*  45 */     ServletFileUpload upload = new ServletFileUpload(factory);
/*  46 */     List items = null;
/*     */     try {
/*  48 */       items = upload.parseRequest(request);
/*     */     } catch (FileUploadException e1) {
/*  50 */       e1.printStackTrace();
/*     */     }
/*     */     try
/*     */     {
/*  54 */       String rootPath = request.getSession().getServletContext().getRealPath("/");
/*     */ 
/*  56 */       String saveReportPath = "/cicro/wcm/vhosts/paike.weinan.gov.cn/ROOT/phone/";
/*  57 */       MyUtils.mkDirectory(saveReportPath);
/*  58 */       boolean flag = false;
/*  59 */       Iterator iter = items.iterator();
/*  60 */       while (iter.hasNext()) {
/*  61 */         FileItem item = (FileItem)iter.next();
/*     */ 
/*  66 */         if ((item.getContentType() != null) && 
/*  67 */           (item.getName() != null) && (!item.getName().equals("")))
/*     */         {
/*  70 */           String newFileName = MyUtils.randomRename(item.getName(), saveReportPath);
/*  71 */           String reportAbsFilePath = saveReportPath + newFileName;
/*  72 */           File file = new File(reportAbsFilePath);
/*     */           try {
/*  74 */             item.write(file);
/*  75 */             flag = true;
/*     */           } catch (Exception e) {
/*  77 */             flag = false;
/*  78 */             e.printStackTrace();
/*     */           } finally {
/*  80 */             file = null;
/*  81 */             item = null;
/*  82 */             if (flag)
/*     */             {
/*  84 */               response.getWriter().write(newFileName);
/*     */             }
/*     */             else {
/*  87 */               response.getWriter().write(newFileName);
/*     */             }
/*  89 */             flag = false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  95 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 102 */     doGet(request, response);
/*     */   }
/*     */ 
/*     */   public void init()
/*     */     throws ServletException
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.app.fileupload.FileUploadServletWN
 * JD-Core Version:    0.6.2
 */