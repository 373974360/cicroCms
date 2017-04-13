/*     */ package com.cicro.wcm.services.extendfunction.knowledgeTab;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class KnowledgeRPC
/*     */ {
/*     */   public static String getJSONTreeStr(String site_id, String app_id)
/*     */   {
/*  22 */     return KnowledgeCateManager.getJSONTreeStr(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static List<KnowledgeCateBean> getWareCateList(String id, Map<String, String> mp)
/*     */   {
/*  33 */     return KnowledgeCateManager.getWareCateList(id, mp);
/*     */   }
/*     */ 
/*     */   public static KnowledgeCateBean getWareCategoryByID(String id)
/*     */   {
/*  43 */     return KnowledgeCateManager.getKCategoryByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(KnowledgeCateBean wcb, HttpServletRequest request)
/*     */   {
/*  54 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  55 */     if (stl != null)
/*     */     {
/*  57 */       return KnowledgeCateManager.insertWareCate(wcb, stl);
/*     */     }
/*     */ 
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCate(KnowledgeCateBean wcb, HttpServletRequest request)
/*     */   {
/*  73 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  74 */     if (stl != null)
/*     */     {
/*  76 */       return KnowledgeCateManager.updateWareCategory(wcb, stl);
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
/*  95 */       return KnowledgeCateManager.saveSort(ids, stl);
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
/* 114 */       return KnowledgeCateManager.deleteWareCategory(mp, stl);
/*     */     }
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.knowledgeTab.KnowledgeRPC
 * JD-Core Version:    0.6.2
 */