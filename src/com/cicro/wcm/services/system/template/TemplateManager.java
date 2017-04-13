/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateBean;
/*     */ import com.cicro.wcm.dao.system.template.TemplateDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateManager
/*     */ {
/*     */   public static TemplateBean getTemplateBean(int t_id)
/*     */   {
/*  27 */     return TemplateDAO.getTemplateBean(t_id);
/*     */   }
/*     */ 
/*     */   public static String getTemplateCount(Map<String, String> con_map)
/*     */   {
/*  37 */     return TemplateDAO.getTemplateCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplate(TemplateBean template, SettingLogsBean stl)
/*     */   {
/*  46 */     return TemplateDAO.updateTemplate(template, stl);
/*     */   }
/*     */ 
/*     */   public static boolean addTemplate(TemplateBean template, SettingLogsBean stl)
/*     */   {
/*  55 */     if (template == null) {
/*  56 */       return false;
/*     */     }
/*  58 */     template.setT_ver(1);
/*     */ 
/*  61 */     return TemplateDAO.addTemplate(template, stl);
/*     */   }
/*     */ 
/*     */   public static List<TemplateBean> getAllTemplateList()
/*     */   {
/*  70 */     return TemplateDAO.getAllTemplateList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateBean> getTemplateListForDB(Map<String, String> con_map)
/*     */   {
/*  80 */     return TemplateDAO.getTemplateListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateById(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/*  89 */     if ((t_ids == null) || (t_ids.equals(""))) {
/*  90 */       return false;
/*     */     }
/*  92 */     return TemplateDAO.delTemplate(t_ids, site_id, stl);
/*     */   }
/*     */ 
/*     */   public static String readFileToString(String site_id, String file_path)
/*     */   {
/* 100 */     String str = "";
/*     */     try {
/* 102 */       SiteBean siteBean = SiteManager.getSiteBeanBySiteID(site_id);
/* 103 */       String uploadpath = siteBean.getSite_path();
/* 104 */       str = FileOperation.readFileToString(uploadpath + file_path, "utf-8");
/*     */     } catch (IOException e) {
/* 106 */       e.printStackTrace();
/*     */     }
/* 108 */     return str;
/*     */   }
/*     */ 
/*     */   public static boolean delFile(String site_id, String file_path)
/*     */   {
/* 118 */     SiteBean siteBean = SiteManager.getSiteBeanBySiteID(site_id);
/* 119 */     String uploadpath = siteBean.getSite_path();
/* 120 */     return FileOperation.deleteFile(uploadpath + file_path);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateManager
 * JD-Core Version:    0.6.2
 */