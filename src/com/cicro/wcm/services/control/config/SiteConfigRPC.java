/*     */ package com.cicro.wcm.services.control.config;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteConfigBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteConfigRPC
/*     */ {
/*     */   public static List getConfigListBySiteID(String site_id)
/*     */   {
/*  32 */     return SiteConfigManager.getConfigListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfig(List<SiteConfigBean> l, HttpServletRequest request)
/*     */   {
/*  43 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  44 */     if (stl != null)
/*     */     {
/*  46 */       if (SiteConfigManager.insertSiteConfig(l, stl)) {
/*  47 */         return true;
/*     */       }
/*  49 */       return false;
/*     */     }
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteConfig(String config_ids)
/*     */   {
/*  63 */     return SiteConfigManager.deleteSiteConfig(config_ids);
/*     */   }
/*     */ 
/*     */   public static SiteConfigBean getConfigByConfigID(String config_id)
/*     */   {
/*  74 */     return SiteConfigManager.getConfigByConfigID(config_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteConfig(SiteConfigBean scb)
/*     */   {
/*  85 */     return SiteConfigManager.updateSiteConfig(scb);
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfig(SiteConfigBean scb)
/*     */   {
/*  95 */     return SiteConfigManager.insertSiteConfig(scb);
/*     */   }
/*     */ 
/*     */   public static SiteConfigBean getConfigValues(String site_id, String key)
/*     */   {
/* 106 */     return SiteConfigManager.getConfigValues(site_id, key);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.config.SiteConfigRPC
 * JD-Core Version:    0.6.2
 */