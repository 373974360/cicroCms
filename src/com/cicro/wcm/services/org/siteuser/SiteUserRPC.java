/*     */ package com.cicro.wcm.services.org.siteuser;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.siteuser.SiteUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteUserRPC
/*     */ {
/*     */   public static List<SiteUserBean> getSiteUserList(String site_id, String app_id)
/*     */   {
/*  21 */     return SiteUserManager.getSiteUserList(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserBeanBySite(String app_id, String site_id)
/*     */   {
/*  26 */     return SiteUserManager.getUserBeanBySite(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getSiteUserListBySiteAppID(String app_id, String site_id)
/*     */   {
/*  37 */     return SiteUserManager.getSiteUserListBySiteAppID(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, List<String>> getSiteUserInfo(List<SiteUserBean> lt)
/*     */   {
/*  48 */     return SiteUserManager.getSiteUserInfo(lt);
/*     */   }
/*     */ 
/*     */   public static String getUserIDS(String site_id, String app_id)
/*     */   {
/*  59 */     return SiteUserManager.getUserIDS(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static boolean linkSiteUser(String insert_user_ids, String delete_user_ids, String site_id, String app_id, HttpServletRequest request)
/*     */   {
/*  73 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  74 */     if (stl != null)
/*     */     {
/*  76 */       return SiteUserManager.linkSiteUser(insert_user_ids, delete_user_ids, site_id, app_id, stl);
/*     */     }
/*     */ 
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteUser(String user_ids, String site_id, String app_id, HttpServletRequest request)
/*     */   {
/*  94 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  95 */     if (stl != null)
/*     */     {
/*  97 */       return SiteUserManager.deleteSiteUser(user_ids, site_id, app_id, stl);
/*     */     }
/*     */ 
/* 101 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.siteuser.SiteUserRPC
 * JD-Core Version:    0.6.2
 */