/*     */ package com.cicro.wcm.services.control.domain;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.control.site.SiteOperationFactory;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteDomainRPC
/*     */ {
/*     */   public static String getDefaultSiteDomainBySiteID(String site_id)
/*     */   {
/*  22 */     return SiteDomainManager.getDefaultSiteDomainBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static String getSiteIDByUrl(HttpServletRequest request)
/*     */   {
/*  33 */     return SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());
/*     */   }
/*     */ 
/*     */   public static List<SiteDomainBean> getDomainListBySiteID(String site_id)
/*     */   {
/*  44 */     return SiteDomainManager.getDomainListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static SiteDomainBean getSiteDomainBeanByID(String domain_id)
/*     */   {
/*  55 */     return SiteDomainManager.getSiteDomainBeanByID(domain_id);
/*     */   }
/*     */ 
/*     */   public static boolean siteDomainISExist(String site_domain)
/*     */   {
/*  65 */     return SiteDomainManager.siteDomainISExist(site_domain);
/*     */   }
/*     */ 
/*     */   public static String getSiteDomainBySiteID(String site_id)
/*     */   {
/*  77 */     return SiteDomainManager.getSiteDomainBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean siteDomainISExistNoHost(String site_domain)
/*     */   {
/*  87 */     return SiteDomainManager.siteDomainISExistNoHost(site_domain);
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteDomain(SiteDomainBean sdb, HttpServletRequest request)
/*     */   {
/*  98 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  99 */     if (stl != null)
/*     */     {
/* 101 */       if (SiteOperationFactory.addAlias(sdb, stl)) {
/* 102 */         return true;
/*     */       }
/* 104 */       return false;
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteDomain(SiteDomainBean sdb, HttpServletRequest request)
/*     */   {
/* 118 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 119 */     if (stl != null)
/*     */     {
/* 121 */       if (SiteOperationFactory.updateAlias(sdb, stl)) {
/* 122 */         return true;
/*     */       }
/* 124 */       return false;
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSDomainStatus(String domain_id, String site_id, HttpServletRequest request)
/*     */   {
/* 139 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 140 */     if (stl != null)
/*     */     {
/* 142 */       if (SiteDomainManager.updateSDomainStatus(domain_id, site_id, stl)) {
/* 143 */         return true;
/*     */       }
/* 145 */       return false;
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteDomain(String domain_ids, String site_id, HttpServletRequest request)
/*     */   {
/* 160 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 161 */     if (stl != null)
/*     */     {
/* 163 */       if (SiteOperationFactory.deleteAlias(site_id, domain_ids, stl)) {
/* 164 */         return true;
/*     */       }
/* 166 */       return false;
/*     */     }
/* 168 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.domain.SiteDomainRPC
 * JD-Core Version:    0.6.2
 */