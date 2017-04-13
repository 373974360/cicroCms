/*     */ package com.cicro.wcm.servlet.upload;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.UploadManager;
/*     */ import com.cicro.util.ZipManager;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
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
/*     */ public class TemplateResourcesUpload extends HttpServlet
/*     */ {
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  29 */     System.out.println(request.getSession().getId());
/*  30 */     response.getWriter().println("OK" + UserLogin.checkLoginBySession(request));
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  37 */     String sid = request.getParameter("sid");
/*  38 */     if ((sid == null) || ("".equals(sid)) || (!UploadManager.checkUploadSecretKey(sid)))
/*     */     {
/*  40 */       System.out.println("Upload validation errors " + sid);
/*  41 */       return;
/*     */     }
/*  43 */     String custom_folder = request.getParameter("custom_folder");
/*     */ 
/*  45 */     DiskFileItemFactory fac = new DiskFileItemFactory();
/*  46 */     ServletFileUpload upload = new ServletFileUpload(fac);
/*  47 */     upload.setHeaderEncoding("utf-8");
/*  48 */     List fileList = null;
/*     */     try {
/*  50 */       fileList = upload.parseRequest(request);
/*     */     } catch (FileUploadException ex) {
/*  52 */       return;
/*     */     }
/*     */ 
/*  55 */     String site_id = request.getParameter("site_id");
/*  56 */     String browser_path = "";
/*  57 */     String savePath = "";
/*  58 */     if ("shared_res".equals(site_id))
/*  59 */       savePath = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + "/wcm.files";
/*     */     else {
/*  61 */       savePath = SiteManager.getSiteBeanBySiteID(site_id).getSite_path();
/*     */     }
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
/*  81 */             if ((extName.equals(".gif")) || (extName.equals(".jpg")) || 
/*  82 */               (extName.equals(".jpeg")) || 
/*  83 */               (extName.equals(".png")) || (extName.equals(".swf"))) {
/*  84 */               savePath = FormatUtil.formatPath(savePath + "/images");
/*  85 */               browser_path = "/images";
/*     */             }
/*  87 */             if (extName.equals(".js")) {
/*  88 */               savePath = FormatUtil.formatPath(savePath + "/js");
/*  89 */               browser_path = "/js";
/*     */             }
/*  91 */             if (extName.equals(".css")) {
/*  92 */               savePath = FormatUtil.formatPath(savePath + "/styles");
/*  93 */               browser_path = "/styles";
/*     */             }
/*     */ 
/*  96 */             if ((custom_folder != null) && (!"".equals(custom_folder)))
/*     */             {
/*  98 */               savePath = custom_folder;
/*     */             }
/*     */ 
/* 101 */             if (extName.equals(".zip")) {
/* 102 */               savePath = SiteManager.getSiteBeanBySiteID(site_id).getSite_path();
/*     */             }
/*     */ 
/* 105 */             File f = new File(savePath);
/* 106 */             if (!f.exists())
/*     */             {
/* 108 */               f.mkdirs();
/*     */             }
/* 110 */             System.out.println(savePath);
/* 111 */             File saveFile = new File(savePath + File.separator + name);
/* 112 */             item.write(saveFile);
/*     */ 
/* 115 */             if ((!extName.equals(".zip")) || 
/* 116 */               (!ZipManager.decompress(savePath + File.separator + name, savePath)))
/*     */               continue;
/* 118 */             saveFile.delete();
/*     */           }
/*     */           catch (Exception e)
/*     */           {
/* 122 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */       } else {
/* 126 */         System.out.println("-------->    " + item.getFieldName() + "  " + item.getString());
/*     */       }
/*     */     }
/* 128 */     String outStr = "{\"att_path\":\"" + browser_path + "/" + name + "\"}";
/* 129 */     response.getWriter().print(outStr);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.upload.TemplateResourcesUpload
 * JD-Core Version:    0.6.2
 */