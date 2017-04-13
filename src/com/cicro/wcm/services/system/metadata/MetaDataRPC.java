/*     */ package com.cicro.wcm.services.system.metadata;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.metadata.MetaDataBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class MetaDataRPC
/*     */ {
/*     */   public static String getMetaDataCount()
/*     */   {
/*  21 */     return MetaDataManager.getMetaDataCount();
/*     */   }
/*     */ 
/*     */   public static Map<Integer, MetaDataBean> getMetaDataMap()
/*     */   {
/*  31 */     return MetaDataManager.getMetaDataMap();
/*     */   }
/*     */ 
/*     */   public static String getMetaDataCountForDB(Map<String, String> m)
/*     */   {
/*  42 */     return MetaDataManager.getMetaDataCountForDB(m);
/*     */   }
/*     */ 
/*     */   public static List<MetaDataBean> getMetaDataListForDB(Map<String, String> m)
/*     */   {
/*  52 */     return MetaDataManager.getMetaDataListForDB(m);
/*     */   }
/*     */ 
/*     */   public static MetaDataBean getMetaDataBean(int metaData_id)
/*     */   {
/*  62 */     return MetaDataManager.getMetaDataBean(metaData_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMetaData(MetaDataBean mdb, HttpServletRequest request)
/*     */   {
/*  73 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  74 */     if (stl != null)
/*     */     {
/*  76 */       return MetaDataManager.insertMetaData(mdb, stl);
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMetaData(MetaDataBean mdb, HttpServletRequest request)
/*     */   {
/*  89 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  90 */     if (stl != null)
/*     */     {
/*  92 */       return MetaDataManager.updateMetaData(mdb, stl);
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMetaData(String meta_id, HttpServletRequest request)
/*     */   {
/* 104 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 105 */     if (stl != null)
/*     */     {
/* 107 */       return MetaDataManager.deleteMetaData(meta_id, stl);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 114 */     System.out.println(getMetaDataCount());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.metadata.MetaDataRPC
 * JD-Core Version:    0.6.2
 */