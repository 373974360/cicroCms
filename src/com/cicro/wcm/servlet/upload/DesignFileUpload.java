/*     */ package com.cicro.wcm.servlet.upload;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.UploadManager;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ import org.apache.commons.fileupload.FileUploadException;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ 
/*     */ public class DesignFileUpload extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  26 */     System.out.println(request.getSession().getId());
/*  27 */     response.getWriter().println("OK");
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  33 */     String sid = request.getParameter("sid");
/*  34 */     if ((sid == null) || ("".equals(sid)) || (!UploadManager.checkUploadSecretKey(sid)))
/*     */     {
/*  36 */       System.out.println("Upload validation errors");
/*  37 */       return;
/*     */     }
/*  39 */     String up_type = request.getParameter("up_type");
/*  40 */     String css_ename = request.getParameter("css_ename");
/*  41 */     DiskFileItemFactory fac = new DiskFileItemFactory();
/*  42 */     ServletFileUpload upload = new ServletFileUpload(fac);
/*  43 */     upload.setHeaderEncoding("utf-8");
/*  44 */     List fileList = null;
/*     */     try {
/*  46 */       fileList = upload.parseRequest(request);
/*     */     } catch (FileUploadException ex) {
/*  48 */       return;
/*     */     }
/*     */ 
/*  51 */     String browser_path = "";
/*  52 */     String savePath = "";
/*  53 */     if ("thumb".equals(up_type))
/*     */     {
/*  55 */       browser_path = "/wcm.files/design/thumb/";
/*  56 */       savePath = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "wcm_files") + "/design/thumb/");
/*     */     }
/*     */     else
/*     */     {
/*  60 */       savePath = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "wcm_files") + "/design/theme/" + css_ename + "/");
/*     */     }
/*  62 */     System.out.println("DesignFileUpload--------------" + savePath);
/*  63 */     Iterator it = fileList.iterator();
/*  64 */     String name = "";
/*  65 */     String extName = "";
/*  66 */     while (it.hasNext()) {
/*  67 */       FileItem item = (FileItem)it.next();
/*  68 */       if (!item.isFormField()) {
/*  69 */         name = item.getName();
/*  70 */         if ((name != null) && (!name.trim().equals("")))
/*     */         {
/*  74 */           if (name.lastIndexOf(".") >= 0) {
/*  75 */             extName = name.substring(name.lastIndexOf("."))
/*  76 */               .toLowerCase();
/*  77 */             if (UploadFileIfy.NOTUPLOAT_FILE_EXT.contains("," + extName.substring(1) + ","))
/*  78 */               return;
/*     */           }
/*     */           try {
/*  81 */             if ("thumb".equals(up_type))
/*     */             {
/*  83 */               name = DateUtil.getCurrentDateTime("yyyyMMddhhmmsss") + extName;
/*     */             }
/*     */ 
/*  86 */             File f = new File(savePath);
/*  87 */             if (!f.exists())
/*     */             {
/*  89 */               f.mkdirs();
/*     */             }
/*  91 */             System.out.println(savePath);
/*  92 */             File saveFile = new File(savePath + "/" + name);
/*  93 */             item.write(saveFile);
/*     */           }
/*     */           catch (Exception e) {
/*  96 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       } else {
/* 100 */         System.out.println("-------->    " + item.getFieldName() + "  " + item.getString());
/*     */       }
/*     */     }
/* 102 */     String outStr = "{\"att_path\":\"" + browser_path + name + "\"}";
/* 103 */     System.out.println("DesignFileUpload--------------" + savePath);
/* 104 */     response.getWriter().print(outStr);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.upload.DesignFileUpload
 * JD-Core Version:    0.6.2
 */