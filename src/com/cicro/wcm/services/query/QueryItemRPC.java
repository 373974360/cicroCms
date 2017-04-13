/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryItemBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class QueryItemRPC
/*     */ {
/*     */   public static List<List<QueryItemBean>> getQueryItemValueList(String conf_id, int page_size)
/*     */   {
/*  22 */     return QueryItemManager.getQueryItemValueList(conf_id, page_size);
/*     */   }
/*     */ 
/*     */   public static String getQueryItemCount(Map<String, String> m)
/*     */   {
/*  31 */     return QueryItemManager.getQueryItemCount(m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemLists(Map m)
/*     */   {
/*  42 */     return QueryItemManager.getQueryItemLists(m);
/*     */   }
/*     */ 
/*     */   public static String getQueryItemCounts(Map m)
/*     */   {
/*  47 */     return QueryItemManager.getQueryItemCounts(m);
/*     */   }
/*     */ 
/*     */   public static List<QueryItemBean> getQueryItemBeans(int item_id, int conf_id)
/*     */   {
/*  57 */     return QueryItemManager.getQueryItemBeans(item_id, conf_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryItemByConf_id(int conf_id, List<QueryItemBean> l, HttpServletRequest request)
/*     */   {
/*  68 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  69 */     if (stl != null)
/*     */     {
/*  71 */       return QueryItemManager.insertQueryItemByConf_id(conf_id, l, stl);
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryItem(List<QueryItemBean> l, HttpServletRequest request)
/*     */   {
/*  84 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  85 */     if (stl != null)
/*     */     {
/*  87 */       return QueryItemManager.updateQueryItem(l, stl);
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItem(Map m, HttpServletRequest request)
/*     */   {
/*  99 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 100 */     if (stl != null)
/*     */     {
/* 102 */       return QueryItemManager.deleteQueryItem(m, stl);
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryItemByConf_id(int conf_id, HttpServletRequest request)
/*     */   {
/* 117 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 118 */     if (stl != null)
/*     */     {
/* 120 */       return QueryItemManager.deleteQueryItemByConf_id(conf_id, stl);
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean writeExcelItems(String file_name, int conf_id, String site_id, HttpServletRequest request)
/*     */   {
/* 134 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 135 */     if (stl != null)
/*     */     {
/* 137 */       return QueryItemManager.writeExcel(file_name, conf_id, site_id, stl);
/*     */     }
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 145 */     Map m = new HashMap();
/* 146 */     m.put("conf_id", "24");
/* 147 */     m.put("site_id", "HIWCMdemo");
/* 148 */     System.out.println(getQueryItemCount(m));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryItemRPC
 * JD-Core Version:    0.6.2
 */