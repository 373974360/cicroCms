/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteAppBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.server.LicenseCheck;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteRPC
/*     */ {
/*     */   public static String isCreateSite()
/*     */   {
/*  29 */     int s_n = LicenseCheck.LICENSE_SITE_NUM - 1;
/*  30 */     if (!LicenseCheck.checkSiteCount(s_n))
/*     */     {
/*  32 */       return "true";
/*     */     }
/*  34 */     return LicenseCheck.LICENSE_SITE_NUM;
/*     */   }
/*     */ 
/*     */   public static String getSitePort()
/*     */   {
/*  40 */     return SiteManager.getSitePort();
/*     */   }
/*     */ 
/*     */   public static String getDefaultSiteDomainBySiteID(String site_id)
/*     */   {
/*  51 */     return "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static String getSiteDomain(String site_id)
/*     */   {
/*  57 */     return "http://" + SiteDomainManager.getSiteDomainBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static String getSiteIDByAppID(String app_id)
/*     */   {
/*  67 */     return SiteAppRele.getSiteIDByAppID(app_id);
/*     */   }
/*     */ 
/*     */   public static SiteAppBean getSiteAppBean(String app_id)
/*     */   {
/*  72 */     return SiteAppRele.getSiteAppBean(app_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(String site_id, String app_id)
/*     */   {
/*  82 */     return SiteAppRele.insertSiteReleApp(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(SiteAppBean sab)
/*     */   {
/*  92 */     return SiteAppRele.insertSiteReleApp(sab);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getSiteChildListByID(String site_id)
/*     */   {
/* 102 */     return SiteManager.getSiteChildListByID(site_id);
/*     */   }
/*     */ 
/*     */   public static String getSiteTreeJsonStr()
/*     */   {
/* 112 */     return SiteManager.getSiteTreeJsonStr();
/*     */   }
/*     */ 
/*     */   public static int getSiteSortByID(String site_id)
/*     */   {
/* 121 */     return SiteManager.getSiteSortByID(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean siteIDISExist(String site_id)
/*     */   {
/* 130 */     return SiteManager.siteIDISExist(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSite(SiteBean sb, HttpServletRequest request)
/*     */   {
/* 140 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 141 */     if (stl != null)
/*     */     {
/* 143 */       if (SiteOperationFactory.addSite(sb, stl)) {
/* 144 */         return true;
/*     */       }
/* 146 */       return false;
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSite(String site_id, HttpServletRequest request)
/*     */   {
/* 161 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 162 */     if (stl != null)
/*     */     {
/* 164 */       if (SiteOperationFactory.deleteSite(site_id, stl)) {
/* 165 */         return true;
/*     */       }
/* 167 */       return false;
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSiteSort(String site_ids, HttpServletRequest request)
/*     */   {
/* 182 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 183 */     if (stl != null)
/*     */     {
/* 185 */       if (SiteManager.saveSiteSort(site_ids, stl)) {
/* 186 */         return true;
/*     */       }
/* 188 */       return false;
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteStatus(String site_id, int site_status, HttpServletRequest request)
/*     */   {
/* 203 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 204 */     if (stl != null)
/*     */     {
/* 206 */       if (SiteOperationFactory.updateSiteStatus(site_id, site_status, stl)) {
/* 207 */         return true;
/*     */       }
/* 209 */       return false;
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public static SiteBean getSiteBeanBySiteID(String site_id)
/*     */   {
/* 222 */     return SiteManager.getSiteBeanBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSite(SiteBean sb, HttpServletRequest request)
/*     */   {
/* 232 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 233 */     if (stl != null)
/*     */     {
/* 235 */       if (SiteOperationFactory.updateSite(sb, stl)) {
/* 236 */         return true;
/*     */       }
/* 238 */       return false;
/*     */     }
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteUserManager(String app_id, String site_id, String insert_user_ids, String delete_user_ids, HttpServletRequest request)
/*     */   {
/* 256 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 257 */     if (stl != null)
/*     */     {
/* 259 */       if (SiteManager.insertSiteUserManager(app_id, site_id, insert_user_ids, delete_user_ids, stl)) {
/* 260 */         return true;
/*     */       }
/* 262 */       return false;
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getUsersBySiteId(String app_id, String site_id)
/*     */   {
/* 276 */     return SiteManager.getUsersBySiteId(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getSiteList()
/*     */   {
/* 286 */     return SiteManager.getSiteList();
/*     */   }
/*     */ 
/*     */   public static int getSiteHits(String hit_type, HttpServletRequest request)
/*     */   {
/* 296 */     return SiteVisitCountManager.getHit(hit_type, request);
/*     */   }
/*     */ 
/*     */   public static void addSiteHits(HttpServletRequest request)
/*     */   {
/* 306 */     SiteVisitCountManager.addSiteHits(request);
/*     */   }
/*     */ 
/*     */   public static boolean updateStep(String site_id, int step)
/*     */   {
/* 317 */     return SiteVisitCountManager.updateStep(site_id, step);
/*     */   }
/*     */ 
/*     */   public static boolean updateHitForSite(String site_id, int count)
/*     */   {
/* 328 */     return SiteOperationFactory.updateHitForSite(site_id, count);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteRPC
 * JD-Core Version:    0.6.2
 */