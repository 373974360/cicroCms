/*     */ package com.cicro.wcm.dao.control;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteAppBean;
/*     */ import com.cicro.wcm.bean.control.SiteConfigBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteConfigDAO
/*     */ {
/*     */   public static List<SiteConfigBean> getAllSiteConfigList()
/*     */   {
/*  33 */     return DBManager.queryFList("getAllSiteConfigList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfig(List<SiteConfigBean> l, SettingLogsBean stl)
/*     */   {
/*  43 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*     */       try
/*     */       {
/*  47 */         for (int i = 0; i < l.size(); i++)
/*     */         {
/*  49 */           insertSiteConfigHandl((SiteConfigBean)l.get(i));
/*     */         }
/*  51 */         PublicTableDAO.insertSettingLogs("添加", "站点配置 站点", ((SiteConfigBean)l.get(0)).getSite_id(), stl);
/*  52 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/*  55 */         return false;
/*     */       }
/*     */     }
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfigHandl(SiteConfigBean scb)
/*     */   {
/*  68 */     scb.setConfig_id(PublicTableDAO.getIDByTableName(PublicTableDAO.SITECONFIG_TABLE_NAME));
/*  69 */     return DBManager.insert("insert_site_config", scb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteConfigHandl(String site_id)
/*     */   {
/*  79 */     return DBManager.delete("delete_site_config", site_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteConfig(String config_ids)
/*     */   {
/*  90 */     Map m = new HashMap();
/*  91 */     m.put("config_ids", config_ids);
/*  92 */     return DBManager.delete("delete_site_configbyids", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteConfig(SiteConfigBean scb)
/*     */   {
/* 102 */     return DBManager.update("update_site_configbyid", scb);
/*     */   }
/*     */ 
/*     */   public static List<SiteAppBean> getSiteAppReleList()
/*     */   {
/* 113 */     return DBManager.queryFList("getSiteAppReleList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(String site_id, String app_id)
/*     */   {
/* 123 */     Map m = new HashMap();
/* 124 */     m.put("site_id", site_id);
/* 125 */     m.put("app_id", app_id);
/* 126 */     m.put("sa_id", PublicTableDAO.getIDByTableName(PublicTableDAO.SITEAPP_TABLE_NAME));
/* 127 */     if (DBManager.delete("delete_site_rele_app", app_id)) {
/* 128 */       return DBManager.insert("insety_site_rele_app", m);
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(SiteAppBean sab)
/*     */   {
/* 140 */     sab.setSa_id(PublicTableDAO.getIDByTableName(PublicTableDAO.SITEAPP_TABLE_NAME));
/* 141 */     if (DBManager.delete("delete_site_rele_app", sab.getApp_id())) {
/* 142 */       return DBManager.insert("insert_site_rele_app", sab);
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteConfigDAO
 * JD-Core Version:    0.6.2
 */