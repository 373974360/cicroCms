/*     */ package com.cicro.wcm.services.search_bak_20151106;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.control.site.SiteRPC;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SearchForInterface
/*     */ {
/*  26 */   private static String searchPath = JconfigUtilContainer.bashConfig().getProperty("path", "", "root_path") + File.separator + "search";
/*     */ 
/*     */   public static String getSearchXmlPath()
/*     */   {
/*  36 */     String searchfile = new SearchForInterface().getClass().getClassLoader().getResource("search.xml").getFile();
/*  37 */     System.out.println(searchfile);
/*  38 */     return searchfile;
/*     */   }
/*     */ 
/*     */   public static List<Map> getSiteList()
/*     */   {
/*  49 */     List list = new ArrayList();
/*     */ 
/*  51 */     List beans = SiteManager.getSiteList();
/*  52 */     for (SiteBean bean : beans)
/*     */     {
/*  54 */       if (!bean.getParent_id().equals("0")) {
/*  55 */         Map map = new HashMap();
/*  56 */         map.put("site_id", bean.getSite_id());
/*  57 */         map.put("site_name", bean.getSite_name());
/*  58 */         list.add(map);
/*     */       }
/*     */     }
/*  61 */     return list;
/*     */   }
/*     */ 
/*     */   public static String getIndexPathRoot()
/*     */   {
/*  71 */     return searchPath;
/*     */   }
/*     */ 
/*     */   public static String getCategoryNameById(String cat_id)
/*     */   {
/*  80 */     CategoryBean categoryBean = CategoryManager.getCategoryBean(Integer.valueOf(cat_id).intValue());
/*  81 */     if (categoryBean == null) {
/*  82 */       return "";
/*     */     }
/*  84 */     return categoryBean.getCat_cname();
/*     */   }
/*     */ 
/*     */   public static String getDomainBySiteId(String site_id)
/*     */   {
/*  95 */     String url = SiteRPC.getDefaultSiteDomainBySiteID(site_id);
/*  96 */     if (url.startsWith("http://")) {
/*  97 */       url = url.substring(7);
/*     */     }
/*  99 */     return url;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 108 */     String path = getSearchXmlPath();
/* 109 */     System.out.println(path);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.SearchForInterface
 * JD-Core Version:    0.6.2
 */