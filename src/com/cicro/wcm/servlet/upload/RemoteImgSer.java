/*     */ package com.cicro.wcm.servlet.upload;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.UploadManager;
/*     */ import com.cicro.util.img.ImageUtils;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateInfoBean;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import com.cicro.wcm.services.material.MateInfoManager;
/*     */ import com.cicro.wcm.services.system.config.ConfigManager;

/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
/*     */ 
/*     */ public class RemoteImgSer extends HttpServlet
/*     */ {
/*  32 */   private static String hd_url = "";
/*  33 */   private static String thum_url = "";
/*  34 */   private static String att_cname = "";
/*  35 */   private static long file_size = 0L;
/*  36 */   private static String att_ename = "";
/*     */ 
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
/*     */   {
/*  40 */     String num = request.getParameter("num");
/*  41 */     String callback = request.getParameter("jsonp");
/*  42 */     String sid = request.getParameter("sid");
/*  43 */     String site_id = request.getParameter("site_id");
/*  44 */     String user_id = request.getParameter("user_id");
/*  45 */     String src = request.getParameter("src");
/*  46 */     String savePath = UploadManager.getUploadFilePath(site_id) + "/";
/*  47 */     String img_domain = UploadManager.getImgBrowserUrl(site_id);
/*  48 */     sid = sid.replaceAll("cicro", "#");
/*     */ 
/*  50 */     if ((sid != null) && (!"".equals(sid)) && (!UploadManager.checkUploadSecretKey(sid)))
/*     */     {
/*  52 */       System.out.println("Upload validation errors");
/*  53 */       return;
/*     */     }
/*     */     try
/*     */     {
/*  57 */       thum_url = "";
/*  58 */       hd_url = "";
/*  59 */       att_cname = "";
/*  60 */       file_size = 0L;
/*     */ 
/*  62 */       String fileName = num + DateUtil.getCurrentDateTime("yyyyMMddhhmmsss");
/*  63 */       String extName = "";
/*  64 */       if (src.lastIndexOf(".") >= 0) {
/*  65 */         extName = src.substring(src.lastIndexOf("."))
/*  66 */           .toLowerCase();
/*  67 */         if (UploadFileIfy.NOTUPLOAT_FILE_EXT.contains("," + extName.substring(1) + ","))
/*  68 */           return;
/*     */       }
/*  70 */       if (saveImgUrlAs(src, savePath, fileName, extName, site_id))
/*     */       {
/*  72 */         att_ename = fileName + extName;
/*  73 */         //String tmpUploadFilePath = UploadManager.getUploadFilePath2();
/*  74 */         //savePath = savePath.substring(tmpUploadFilePath.length());
				  savePath = UploadManager.getUploadFileSitePath(site_id);
/*  75 */         if (ServerManager.isWindows())
/*     */         {
/*  77 */           savePath = savePath.replaceAll("\\\\", "\\/");
/*  78 */           if (savePath.startsWith("//"))
/*  79 */             savePath = savePath.substring(1);
/*     */         }
/*  81 */         addMateInfo(site_id, savePath, Integer.parseInt(user_id));
/*     */ 
/*  83 */         response.getWriter().println(callback + "({\"img_path\":\"" + img_domain + savePath + fileName + extName + "\",\"num\":\"" + num + "\"})");
/*     */       } else {
/*  85 */         response.getWriter().println("");
/*     */       }
/*     */     }
/*     */     catch (Exception ex) {
/*  89 */       ex.printStackTrace(System.out);
/*  90 */       response.getWriter().println("");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean saveImgUrlAs(String fileUrl, String savePath, String fileName, String extName, String site_id)
/*     */   {
/*     */     try {
/*  97 */       att_cname = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
/*  98 */       String att_ename = fileName + extName;
/*  99 */       URL url = new URL(fileUrl);
/* 100 */       BufferedImage image = ImageIO.read(url);
/*     */ 
/* 103 */       return processImage(new File(FormatUtil.formatPath(savePath + att_ename)), image, fileName, extName, savePath, "0", "0", site_id, fileUrl);
/*     */     }
/*     */     catch (Exception e) {
/* 106 */       System.out.println(e + fileUrl + savePath);
/* 107 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean processImage(File file, BufferedImage bis, String name, String extName, String savePath, String is_press, String press_osition, String site_id, String fileUrl)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 115 */       int w = bis.getWidth();
/* 116 */       int content_img_width = ImageUtils.getImgWidth();
/* 117 */       int prev_img_width = ImageUtils.getImgPreWidth();
/* 118 */       Map m = new HashMap();
/* 119 */       m.put("group", "attachment");
/* 120 */       m.put("site_id", site_id);
/* 121 */       m.put("app_id", "cms");
/* 122 */       Map con_map = ConfigManager.getConfigMap(m);
/* 123 */       String normal_width = (String)con_map.get("normal_width");
/* 124 */       if ((normal_width != null) && (!"".equals(normal_width))) {
/* 125 */         content_img_width = Integer.parseInt(normal_width);
/*     */       }
/* 127 */       String thumb_width = (String)con_map.get("thumb_width");
/* 128 */       if ((thumb_width != null) && (!"".equals(thumb_width))) {
/* 129 */         prev_img_width = Integer.parseInt(thumb_width);
/*     */       }
/* 131 */       String is_compress = "1";
/* 132 */       if (con_map.containsKey("is_compress"))
/* 133 */         is_compress = (String)con_map.get("is_compress");
/*     */       File saveFile;
/* 136 */       if ((w > content_img_width)) 
				{
/* 137 */         hd_url = name + "_b" + extName;
/* 138 */         saveFile = new File(savePath + hd_url);
/* 139 */         ImageIO.write(bis, extName.substring(1), saveFile);
/* 141 */         ImageUtils.cutImage(ImageUtils.getImgWidth(),savePath + hd_url, savePath + name + extName);
/*     */       }
/*     */       else {
/* 149 */         saveFile = file;
				  ImageIO.write(bis, extName.substring(1), saveFile);
/*     */       }
/*     */ 
/* 170 */       file_size = FileOperation.getFileSize(file);
/*     */ 
/* 173 */       if (w > prev_img_width)
/*     */       {
/* 175 */         thum_url = name + "_s" + extName;
/* 182 */         ImageUtils.cutImage(ImageUtils.getImgPreWidth(), savePath + name + extName, savePath+thum_url);
/*     */       }
/* 185 */       return true;
/*     */     } catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean addMateInfo(String site_id, String att_path, int user_id)
/*     */   {
/* 194 */     MateInfoBean bean = new MateInfoBean();
/* 195 */     bean.setAtt_id(Integer.parseInt(MateInfoManager.getArrIdFromTable()));
/* 196 */     bean.setF_id(0);
/* 197 */     bean.setSite_id(site_id);
/* 198 */     bean.setUser_id(user_id);
/* 199 */     bean.setAtt_path(att_path);
/* 200 */     bean.setAtt_ename(att_ename);
/* 201 */     bean.setAtt_cname(att_cname);
/* 202 */     bean.setThumb_url(thum_url);
/* 203 */     bean.setHd_url(hd_url);
/* 204 */     bean.setAlias_name(att_cname);
/* 205 */     bean.setFilesize(Long.valueOf(file_size));
/* 206 */     SettingLogsBean stl = new SettingLogsBean();
/* 207 */     stl.setUser_id(user_id);
/* 208 */     return MateInfoManager.insertMateInfo(bean, stl);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.upload.RemoteImgSer
 * JD-Core Version:    0.6.2
 */