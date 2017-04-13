/*     */ package com.cicro.util;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.fileupload.DiskFileUpload;
/*     */ import org.apache.commons.fileupload.FileItem;
/*     */ 
/*     */ public class UploadFile
/*     */ {
/*  20 */   private static JconfigUtil bcConfig = JconfigFactory.getJconfigUtilInstance("bashConfig");
/*     */ 
/*  22 */   private static String web_access_path = bcConfig.getProperty("web_access_path", "", "upload_file_path");
/*     */ 
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/*  26 */     System.out.println("\tUploadFile\t\tbegin\t");
/*  27 */     Hashtable h = uploadFiles(request);
/*  28 */     if (h == null)
/*     */     {
/*  30 */       response.getWriter().println("{\"err\":\"\",\"上传失败，请选择正确的文件类型\":\"\"}");
/*     */     }
/*     */     else
/*  33 */       response.getWriter().println("{\"err\":\"\",\"msg\":\"" + (String)h.get("filedata") + "\"}");
/*     */   }
/*     */ 
/*     */   public static Hashtable<String, String> uploadFiles(HttpServletRequest request)
/*     */   {
/*     */     try
/*     */     {
/*  45 */       DiskFileUpload upload = new DiskFileUpload();
/*  46 */       upload.setHeaderEncoding("utf-8");
/*  47 */       List items = upload.parseRequest(request);
/*  48 */       Iterator iter = items.iterator();
/*  49 */       Hashtable fields = new Hashtable();
/*  50 */       int num = 0;
/*  51 */       while (iter.hasNext())
/*     */       {
/*  53 */         FileItem item = (FileItem)iter.next();
/*  54 */         String name = item.getFieldName();
/*     */ 
/*  56 */         if (!item.isFormField())
/*     */         {
/*  58 */           num++;
/*  59 */           FileItem uplFile = item;
/*  60 */           String fileNameLong = uplFile.getName();
/*  61 */           if ((fileNameLong != null) && (!"".equals(fileNameLong)))
/*     */           {
/*  63 */             String url = uploadFile(uplFile, fileNameLong, num);
/*  64 */             if (url == null)
/*  65 */               return null;
/*  66 */             fields.put(name, url);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/*  71 */           fields.put(name, item.getString());
/*     */         }
/*     */       }
/*  74 */       return fields;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  78 */       e.printStackTrace();
/*  79 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String uploadFile(FileItem uplFile, String fileNameLong, int newFileName)
/*     */   {
/*  86 */     String fileName = "";
/*  87 */     String uploadType = "";
/*     */     try {
/*  89 */       fileNameLong = fileNameLong.replace('\\', '/');
/*  90 */       String[] pathParts = fileNameLong.split("/");
/*  91 */       fileName = pathParts[(pathParts.length - 1)];
/*  92 */       String ext = getExtension(fileName).toLowerCase();
/*     */ 
/*  95 */       uploadType = getUploadType(ext);
/*  96 */       if ("".equals(uploadType)) {
/*  97 */         return "error file type";
/*     */       }
/*  99 */       String currentDirPath = bcConfig.getProperty(uploadType + "_path", "", "upload_file_path");
/*     */ 
/* 101 */       File currentDir = new File(currentDirPath);
/* 102 */       if (!currentDir.exists()) {
/* 103 */         currentDir.mkdirs();
/*     */       }
/*     */ 
/* 106 */       fileName = "_" + getCurrentDateTime() + newFileName + "." + ext;
/* 107 */       File pathToSave = new File(currentDirPath, fileName);
/* 108 */       while (pathToSave.exists()) {
/* 109 */         pathToSave = new File(currentDirPath, fileName);
/*     */       }
/* 111 */       uplFile.write(pathToSave);
/* 112 */       return web_access_path + uploadType + "/" + fileName;
/*     */     } catch (Throwable ex) {
/* 114 */       ex.printStackTrace(System.out);
/* 115 */     }return "upload fail";
/*     */   }
/*     */ 
/*     */   public static String getUploadType(String ext)
/*     */   {
/* 122 */     String exts = bcConfig.getProperty("img_type", "", "upload_file_path");
/* 123 */     if (exts.contains(ext))
/*     */     {
/* 125 */       return "img";
/*     */     }
/* 127 */     exts = bcConfig.getProperty("flash_type", "", "upload_file_path");
/* 128 */     if (exts.contains(ext))
/*     */     {
/* 130 */       return "flash";
/*     */     }
/* 132 */     exts = bcConfig.getProperty("media_type", "", "upload_file_path");
/* 133 */     if (exts.contains(ext))
/*     */     {
/* 135 */       return "media";
/*     */     }
/* 137 */     exts = bcConfig.getProperty("file_type", "", "upload_file_path");
/* 138 */     if (exts.contains(ext))
/*     */     {
/* 140 */       return "file";
/*     */     }
/* 142 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateTime() {
/* 146 */     Calendar cal = Calendar.getInstance();
/* 147 */     SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
/* 148 */     return sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String getExtension(String fileName) {
/* 152 */     return fileName.replaceAll(".*\\.(.*?)", "$1");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.UploadFile
 * JD-Core Version:    0.6.2
 */