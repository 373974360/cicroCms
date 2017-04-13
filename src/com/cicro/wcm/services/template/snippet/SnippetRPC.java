/*     */ package com.cicro.wcm.services.template.snippet;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.template.snippet.SnippetBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.app.AppManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SnippetRPC
/*     */ {
/*     */   public static List<SnippetBean> getSnippetList()
/*     */   {
/*  34 */     return SnippetManager.getAllSnippetList();
/*     */   }
/*     */ 
/*     */   public static String getSnippetCount()
/*     */   {
/*  44 */     return SnippetManager.getSnippetCount();
/*     */   }
/*     */ 
/*     */   public static SnippetBean getSnippetBean(int sni_id)
/*     */   {
/*  53 */     return SnippetManager.getSnippetBean(sni_id);
/*     */   }
/*     */ 
/*     */   public static int getInsertID()
/*     */   {
/*  62 */     return SnippetManager.getInsertID();
/*     */   }
/*     */ 
/*     */   public static boolean insertSnippet(SnippetBean bean, HttpServletRequest request)
/*     */   {
/*  73 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  74 */     if (stl != null)
/*     */     {
/*  76 */       return SnippetManager.insertSnippet(bean, stl);
/*     */     }
/*     */ 
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSnippet(SnippetBean bean, HttpServletRequest request)
/*     */   {
/*  91 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  92 */     if (stl != null)
/*     */     {
/*  94 */       return SnippetManager.updateSnippet(bean, stl);
/*     */     }
/*     */ 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSnippet(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 109 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 110 */     if (stl != null)
/*     */     {
/* 112 */       return SnippetManager.deleteSnippet(mp, stl);
/*     */     }
/*     */ 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppList()
/*     */   {
/* 126 */     return AppManager.getAppList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.template.snippet.SnippetRPC
 * JD-Core Version:    0.6.2
 */