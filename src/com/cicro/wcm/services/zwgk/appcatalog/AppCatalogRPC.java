/*     */ package com.cicro.wcm.services.zwgk.appcatalog;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.RegulationBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class AppCatalogRPC
/*     */ {
/*     */   public static String getAppCatalogTree(int cata_id)
/*     */   {
/*  22 */     return AppCatalogManager.getAppCatalogTree(cata_id);
/*     */   }
/*     */ 
/*     */   public static int getNewAppCatalogID()
/*     */   {
/*  27 */     return AppCatalogManager.getNewAppCatalogID();
/*     */   }
/*     */ 
/*     */   public static AppCatalogBean getAppCatalogBean(int cata_id)
/*     */   {
/*  37 */     return AppCatalogManager.getAppCatalogBean(cata_id);
/*     */   }
/*     */ 
/*     */   public static List<AppCatalogBean> getChildCatalogList(int cata_id)
/*     */   {
/*  47 */     return AppCatalogManager.getChildCatalogList(cata_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKAppCatelog(AppCatalogBean acb, HttpServletRequest request)
/*     */   {
/*  57 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  58 */     if (stl != null)
/*     */     {
/*  60 */       return AppCatalogManager.insertGKAppCatelog(acb, stl);
/*     */     }
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKAppCatelog(AppCatalogBean acb, HttpServletRequest request)
/*     */   {
/*  72 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  73 */     if (stl != null)
/*     */     {
/*  75 */       return AppCatalogManager.updateGKAppCatelog(acb, stl);
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKAppCatelog(String ids, HttpServletRequest request)
/*     */   {
/*  87 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  88 */     if (stl != null)
/*     */     {
/*  90 */       return AppCatalogManager.sortGKAppCatelog(ids, stl);
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKAppCatelog(int parent_id, String ids, HttpServletRequest request)
/*     */   {
/* 103 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 104 */     if (stl != null)
/*     */     {
/* 106 */       return AppCatalogManager.moveGKAppCatelog(parent_id, ids, stl);
/*     */     }
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKAppCatelog(String ids, HttpServletRequest request)
/*     */   {
/* 118 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 119 */     if (stl != null)
/*     */     {
/* 121 */       return AppCatalogManager.deleteGKAppCatelog(ids, stl);
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean copyShareCategory(int parent_id, String selected_cat_ids, HttpServletRequest request)
/*     */   {
/* 135 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 136 */     if (stl != null)
/*     */     {
/* 138 */       return AppCatalogManager.copyShareCategory(parent_id, selected_cat_ids, stl);
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<RegulationBean> getAppCataReguList(int cata_id)
/*     */   {
/* 150 */     return RegulationManager.getAppCataReguList(cata_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertAppCateRegu(List<RegulationBean> l, String cata_id, HttpServletRequest request)
/*     */   {
/* 162 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 163 */     if (stl != null)
/*     */     {
/* 165 */       return RegulationManager.insertAppCateRegu(l, cata_id, stl);
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 172 */     System.out.println(getAppCatalogTree(3));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.appcatalog.AppCatalogRPC
 * JD-Core Version:    0.6.2
 */