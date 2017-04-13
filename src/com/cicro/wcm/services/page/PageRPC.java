/*     */ package com.cicro.wcm.services.page;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.page.PageBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class PageRPC
/*     */ {
/*     */   public static int getNewPageID()
/*     */   {
/*  20 */     return PageManager.getNewPageID();
/*     */   }
/*     */ 
/*     */   public static String getPageJSONTreeStr(String app_id, String site_id)
/*     */   {
/*  31 */     return PageManager.getPageJSONTreeStr(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<PageBean> getPageList(String app_id, String site_id, int parent_id)
/*     */   {
/*  43 */     return PageManager.getPageList(app_id, site_id, parent_id);
/*     */   }
/*     */ 
/*     */   public static boolean pageIsExist(String page_path, String page_ename, String site_id)
/*     */   {
/*  55 */     if ((PageManager.pageFileIsExist(page_path, page_ename, site_id)) || (PageManager.pageIsExist(page_path, page_ename, site_id))) {
/*  56 */       return true;
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean createHtmlPage(int id)
/*     */     throws IOException
/*     */   {
/*  69 */     return PageManager.createHtmlPage(id);
/*     */   }
/*     */ 
/*     */   public static PageBean getPageBean(int id)
/*     */   {
/*  79 */     return PageManager.getPageBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertPage(PageBean pb, HttpServletRequest request)
/*     */   {
/*  84 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  85 */     if (stl != null)
/*     */     {
/*  87 */       return PageManager.insertPage(pb, stl);
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updatePage(PageBean pb, HttpServletRequest request)
/*     */   {
/*  94 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  95 */     if (stl != null)
/*     */     {
/*  97 */       return PageManager.updatePage(pb, stl);
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePage(int id, HttpServletRequest request)
/*     */   {
/* 104 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 105 */     if (stl != null)
/*     */     {
/* 107 */       return PageManager.deletePage(id, stl);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.page.PageRPC
 * JD-Core Version:    0.6.2
 */