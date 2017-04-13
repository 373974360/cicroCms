/*     */ package com.cicro.wcm.services.query;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.query.QueryConfBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class QueryConfRPC
/*     */ {
/*     */   public static List<QueryConfBean> getAllQueryConfList()
/*     */   {
/*  22 */     return QueryConfManager.getAllQueryConfList();
/*     */   }
/*     */ 
/*     */   public static String getQueryConfCount(Map<String, String> m)
/*     */   {
/*  32 */     return QueryConfManager.getQueryConfCount(m);
/*     */   }
/*     */ 
/*     */   public static List<QueryConfBean> getQueryConfLists(Map<String, String> m)
/*     */   {
/*  42 */     return QueryConfManager.getQueryConfLists(m);
/*     */   }
/*     */ 
/*     */   public static QueryConfBean getQueryConfBean(int conf_id)
/*     */   {
/*  52 */     return QueryConfManager.getQueryConfBean(conf_id);
/*     */   }
/*     */ 
/*     */   public static int getQueryConfID()
/*     */   {
/*  62 */     return QueryConfManager.getQueryConfID();
/*     */   }
/*     */ 
/*     */   public static boolean insertQueryConf(QueryConfBean ob, HttpServletRequest request)
/*     */   {
/*  75 */     System.out.println("insertQueryConf===" + ob);
/*  76 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  77 */     if (stl != null)
/*     */     {
/*  79 */       return QueryConfManager.insertQueryConf(ob, stl);
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateQueryConf(QueryConfBean ob, HttpServletRequest request)
/*     */   {
/*  93 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  94 */     if (stl != null)
/*     */     {
/*  96 */       return QueryConfManager.updateQueryConf(ob, stl);
/*     */     }
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteQueryConf(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 108 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 109 */     if (stl != null)
/*     */     {
/* 111 */       return QueryConfManager.deleteQueryConf(m, stl);
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 118 */     Map m = new HashMap();
/* 119 */     getQueryConfLists(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryConfRPC
 * JD-Core Version:    0.6.2
 */