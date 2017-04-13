/*     */ package com.cicro.wcm.services.material;
/*     */ 
/*     */ import com.cicro.util.UploadManager;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateFolderBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.app.AppManager;
/*     */ import com.cicro.wcm.services.system.config.ConfigManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class MateFolderRPC
/*     */ {
/*     */   public static String getImgDomain(String site_id)
/*     */   {
/*  25 */     return UploadManager.getImgBrowserUrl(site_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getValues(Map<String, String> map)
/*     */   {
/*  34 */     return ConfigManager.getConfigMap(map);
/*     */   }
/*     */ 
/*     */   public static Map<String, MateFolderBean> getMateFolderMap()
/*     */   {
/*  43 */     return MateFolderManager.getMateFolderMap();
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppList()
/*     */   {
/*  52 */     return AppManager.getAppList();
/*     */   }
/*     */ 
/*     */   public static int getMateFolderID()
/*     */   {
/*  61 */     return MateFolderManager.getMateFolderID();
/*     */   }
/*     */ 
/*     */   public static String getMateFolderTreeJsonStr(String f_id, String site_id, String user_id)
/*     */   {
/*  69 */     return MateFolderManager.getMateFolderTreeJsonStr(f_id, site_id, user_id);
/*     */   }
/*     */ 
/*     */   public static String getMFTreeJsonStrForCustom(String site_id, String user_id)
/*     */   {
/*  78 */     return MateFolderManager.getMFTreeJsonStrForCustom(site_id, user_id);
/*     */   }
/*     */ 
/*     */   public static MateFolderBean getMateFolderBean(String opt_id)
/*     */   {
/*  87 */     return MateFolderManager.getMateFolderBean(opt_id);
/*     */   }
/*     */ 
/*     */   public static String getChildMateFolderCount(String opt_id, String user_id)
/*     */   {
/*  96 */     return MateFolderManager.getChildMateFolderCount(opt_id, user_id);
/*     */   }
/*     */ 
/*     */   public static List<MateFolderBean> getMateFolderList(String f_id, String site_id, String user_id)
/*     */   {
/* 116 */     return MateFolderManager.getMateFolderList(f_id, site_id, user_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMateFolder(MateFolderBean ob, HttpServletRequest request)
/*     */   {
/* 126 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 127 */     if (stl != null)
/*     */     {
/* 129 */       return MateFolderManager.insertMateFolder(ob, stl);
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMateFolder(MateFolderBean ob, HttpServletRequest request)
/*     */   {
/* 141 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 142 */     if (stl != null)
/*     */     {
/* 144 */       return MateFolderManager.updateMateFolder(ob, stl);
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMateFolder(String parent_id, String opt_id, HttpServletRequest request, String site_id, String user_id)
/*     */   {
/* 155 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 156 */     if (stl != null)
/*     */     {
/* 158 */       return MateFolderManager.moveMateFolder(parent_id, opt_id, stl, site_id, user_id);
/*     */     }
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMateFolder(String opt_id, HttpServletRequest request)
/*     */   {
/* 170 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 171 */     if (stl != null)
/*     */     {
/* 173 */       return MateFolderManager.deleteMateFolder(opt_id, stl);
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getChildOptCount(String opt_id, String user_id)
/*     */   {
/* 185 */     return MateFolderManager.getChildMateFolderCount(opt_id, user_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] agrs)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.material.MateFolderRPC
 * JD-Core Version:    0.6.2
 */