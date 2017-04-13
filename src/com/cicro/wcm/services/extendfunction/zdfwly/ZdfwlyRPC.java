/*     */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ZdfwlyRPC
/*     */ {
/*     */   public static String getJSONTreeStr(String site_id, String app_id)
/*     */   {
/*  22 */     return ZdfwlyCateManager.getJSONTreeStr(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static List<ZdfwlyCateBean> getWareCateList(String id, Map<String, String> mp)
/*     */   {
/*  33 */     return ZdfwlyCateManager.getWareCateList(id, mp);
/*     */   }
/*     */ 
/*     */   public static ZdfwlyCateBean getWareCategoryByID(String id)
/*     */   {
/*  43 */     return ZdfwlyCateManager.getKCategoryByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(ZdfwlyCateBean wcb, HttpServletRequest request)
/*     */   {
/*  54 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  55 */     if (stl != null)
/*     */     {
/*  57 */       return ZdfwlyCateManager.insertWareCate(wcb, stl);
/*     */     }
/*     */ 
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCate(ZdfwlyCateBean wcb, HttpServletRequest request)
/*     */   {
/*  73 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  74 */     if (stl != null)
/*     */     {
/*  76 */       return ZdfwlyCateManager.updateWareCategory(wcb, stl);
/*     */     }
/*     */ 
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, HttpServletRequest request)
/*     */   {
/*  92 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  93 */     if (stl != null)
/*     */     {
/*  95 */       return ZdfwlyCateManager.saveSort(ids, stl);
/*     */     }
/*     */ 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareCategory(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 111 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 112 */     if (stl != null)
/*     */     {
/* 114 */       return ZdfwlyCateManager.deleteWareCategory(mp, stl);
/*     */     }
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeRPC
 * JD-Core Version:    0.6.2
 */