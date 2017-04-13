/*     */ package com.cicro.wcm.services.control.server;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteServerBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteServerRPC
/*     */ {
/*     */   public static List<SiteServerBean> getSiteServerListByPage(Map<String, String> m)
/*     */   {
/*  32 */     return SiteServerManager.getSiteServerListByPage(m);
/*     */   }
/*     */ 
/*     */   public static List<SiteServerBean> getServerList()
/*     */   {
/*  40 */     return SiteServerManager.getServerList();
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteServer(SiteServerBean ssb, HttpServletRequest request)
/*     */   {
/*  51 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  52 */     if (stl != null)
/*     */     {
/*  54 */       if (SiteServerManager.insertSiteServer(ssb, stl)) {
/*  55 */         return true;
/*     */       }
/*  57 */       return false;
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public static SiteServerBean getServerBeanByID(String server_id)
/*     */   {
/*  69 */     return SiteServerManager.getServerBeanByID(server_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteServer(SiteServerBean ssb, HttpServletRequest request)
/*     */   {
/*  80 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  81 */     if (stl != null)
/*     */     {
/*  83 */       if (SiteServerManager.updateSiteServer(ssb, stl)) {
/*  84 */         return true;
/*     */       }
/*  86 */       return false;
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteServer(String server_ids, HttpServletRequest request)
/*     */   {
/* 101 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 102 */     if (stl != null)
/*     */     {
/* 104 */       if (SiteServerManager.deleteSiteServer(server_ids, stl)) {
/* 105 */         return true;
/*     */       }
/* 107 */       return false;
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.server.SiteServerRPC
 * JD-Core Version:    0.6.2
 */